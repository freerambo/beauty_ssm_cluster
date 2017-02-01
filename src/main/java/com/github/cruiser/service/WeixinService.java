package com.github.cruiser.service;

import com.alibaba.fastjson.JSON;
import com.github.cruiser.cache.RedisCache;
import com.github.cruiser.dto.WeixinAccessToken;
import com.github.cruiser.dto.WeixinError;
import com.github.cruiser.util.HttpMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Service
public class WeixinService {

	private final Logger LOG = LoggerFactory.getLogger(WeixinService.class);

	@Autowired
	private HttpMethod httpMethod;

	@Value("${server.weixin.appid}")
	private String appId;

	@Value("${server.weixin.appsecret}")
	private String appSecret;

	@Value("${server.weixin.api.url}")
	private String apiUrl;

	@Autowired
	private RedisCache cache;

	public String getAccessToken() {
		String cachedKey = RedisCache.CAHCENAME + "|accessToken";
		String result_cache = cache.getCache(cachedKey, String.class);
		if (result_cache == null) {
			String url = apiUrl +
					"token?grant_type=client_credential"
					+ "&appid=" + appId
					+ "&secret=" + appSecret;

			LOG.debug(url);
			String response = httpMethod.sendHttpRequest(url, "GET");
			LOG.debug(response);
			if (isError(response)) {
				LOG.info("微信返回失败：" + response);
				return null;
			}
			WeixinAccessToken weixinAccessToken = JSON.parseObject
					(response, WeixinAccessToken.class);

			LOG.debug("access token: "+weixinAccessToken.getAccess_token());
			LOG.debug("expires time: "+weixinAccessToken.getExpires_in());
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
		if (result.contains("errcode")){
			WeixinError err = JSON.parseObject(result, WeixinError.class);
			return err.getErrcode()==0?false:true;
		}else {
			return false;
		}
	}
}
