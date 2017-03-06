package com.github.cruiser.service;

import com.alibaba.fastjson.JSON;
import com.github.cruiser.cache.RedisCache;
import com.github.cruiser.dto.WeixinAccessToken;
import com.github.cruiser.dto.WeixinError;
import com.github.cruiser.dto.WeixinTemplate;
import com.github.cruiser.dto.WeixinTemplateStringPair;
import com.github.cruiser.enums.ResultEnum;
import com.github.cruiser.exception.CustomException;
import com.github.cruiser.util.OkHttpUtil;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class WeixinService {

	private final Logger LOG = LoggerFactory.getLogger(WeixinService.class);

	@Value("${server.weixin.appid}")
	private String appId;

	@Value("${server.weixin.appsecret}")
	private String appSecret;

	@Value("${server.weixin.api.url}")
	private String apiUrl;

	@Autowired
	private RedisCache cache;

	/**
	 * 获取微信Access Token
	 *
	 * @return
	 */
	public String getAccessToken() {
		String cachedKey = RedisCache.CAHCENAME + "|accessToken";
		String result_cache = cache.getCache(cachedKey, String.class);
		if (result_cache == null) {
			String url = apiUrl +
					"token?grant_type=client_credential"
					+ "&appid=" + appId
					+ "&secret=" + appSecret;

			LOG.debug(url);
			String response = OkHttpUtil.getStringFromServer(url);
			LOG.debug(response);
			if (isError(response) || "".equals(response)) {
				LOG.info("微信返回失败：" + response);
				throw new CustomException(ResultEnum.HTTP_RESPONSE_ERROR.getResultCode());
			}
			WeixinAccessToken weixinAccessToken = JSON.parseObject
					(response, WeixinAccessToken.class);

			LOG.debug("access token: " + weixinAccessToken.getAccess_token());
			LOG.debug("expires time: " + weixinAccessToken.getExpires_in());
			cache.putCacheWithExpireTime(cachedKey,
					weixinAccessToken.getAccess_token(),
					weixinAccessToken.getExpires_in());
			LOG.info("put cache with key:" + cachedKey);
			return weixinAccessToken.getAccess_token();
		}
		else {
			LOG.info("get cache with key:" + cachedKey);
			LOG.debug("get result in cache:" + result_cache);
		}
		return result_cache;

	}

	/**
	 * 判断返回报文是否为错误报文
	 *
	 * @param result
	 * @return
	 */
	private boolean isError(String result) {
		if (result.contains("errcode")) {
			WeixinError err = JSON.parseObject(result, WeixinError.class);
			return err.getErrcode() == 0 ? false : true;
		}
		else {
			return false;
		}
	}

	@Value("${server.weixin.api.template.id}")
	private String templateId;

	@Value("${server.weixin.api.template.callback_url}")
	private String templateCallbackUrl;

	@Value("${server.weixin.api.template.topcolor}")
	private String templateTopColor;

	@Value("${server.weixin.api.template.fontcolor}")
	private String templateFontColor;

	/**
	 * 推送模板消息
	 * @param touser
	 * @param orderId
	 */
	public void pushTemplateMsg(String touser, String orderId, String
			merchantName, String time, String remark) {
		String url = apiUrl +
				"message/template/send?access_token="
				+ getAccessToken();

		WeixinTemplate weixinTemplate = new WeixinTemplate();
		weixinTemplate.setTouser(touser);
		weixinTemplate.setTemplate_id(templateId);
		weixinTemplate.setUrl(templateCallbackUrl);//TODO DAO获取
		weixinTemplate.setTopcolor(templateTopColor);

		Map<String, WeixinTemplateStringPair> map = new HashMap<>();
		map.put("order", new WeixinTemplateStringPair(orderId,
				templateFontColor));
		map.put("merchant", new WeixinTemplateStringPair(merchantName,
				templateFontColor));
		map.put("time", new WeixinTemplateStringPair(time,
				templateFontColor));
		map.put("remark", new WeixinTemplateStringPair(remark,
				templateFontColor));
		weixinTemplate.setData(map);

		String jsonString = JSON.toJSONString(weixinTemplate);
		LOG.debug("请求url为："+url);
		LOG.debug("请求json为："+jsonString);
		RequestBody body = RequestBody
				.create(MediaType.parse("application/json; charset=utf-8"),
						jsonString);
		Request request = new Request.Builder()
				.url(url)
				.post(body)
				.build();
		String response = OkHttpUtil.getStringFromServer(request);
		LOG.info("返回报文为: "+response);

	}
}
