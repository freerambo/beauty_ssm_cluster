package com.github.cruiser.service;

import com.alibaba.fastjson.JSON;
import com.github.cruiser.cache.RedisCache;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.domain.BizResult;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class AliDaYuMessageService {

	private final Logger LOG = LoggerFactory.getLogger(AliDaYuMessageService.class);

	@Value("${server.ali.dayu.appkey}")
	private String appKey;

	@Value("${server.ali.dayu.secret}")
	private String appSecret;

	@Value("${server.ali.dayu.sms.signame}")
	private String smsSigname;

	@Value("${server.ali.dayu.sms.overtime}")
	private int overTime;

	@Value("${server.ali.dayu.sms.vcode.template}")
	private String vcodeTemplate;

	@Autowired
    private RedisCache cache;

	/**
	 *
	 * @return
	 */
	public BizResult sendRegisterCode(String customName, String receiveNumber) {
	    LOG.debug(this.appKey);
        LOG.debug(this.appSecret);
        LOG.debug(this.smsSigname);
        LOG.debug(String.valueOf(this.overTime));
        LOG.debug(this.vcodeTemplate);

		String url="http://gw.api.taobao.com/router/rest";
		String smsType = "normal";

        //保存验证码到redis
        String vCode = getVcode();
        String cachedKey = RedisCache.CAHCENAME + "|v_code"+"|"+receiveNumber;
        cache.putCacheWithExpireTime(cachedKey, vCode, Integer.valueOf(this.overTime));

        //调用淘宝sdk发送短信
		TaobaoClient client = new DefaultTaobaoClient(url, this.appKey, this.appSecret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("原样回传");
		req.setSmsType(smsType);
		req.setSmsFreeSignName(this.smsSigname);
        Map<String, String> paramString = new HashMap<>();
        paramString.put("name", customName);
        paramString.put("v_code", vCode);
        paramString.put("e_time", String.valueOf(overTime/60));
		req.setSmsParamString(JSON.toJSONString(paramString));
		req.setRecNum(receiveNumber);
		req.setSmsTemplateCode(this.vcodeTemplate);
		AlibabaAliqinFcSmsNumSendResponse rsp = null;
		try {
			rsp = client.execute(req);
		} catch (ApiException e) {
		    LOG.error(e.getErrMsg(), e);
		}
		return rsp.getResult();
	}

	public boolean verrifyVcode(String receiveNumber, String vcode) {
        String cachedKey = RedisCache.CAHCENAME + "|v_code"+"|"+receiveNumber;
        String result_cache = cache.getCache(cachedKey, String.class);
        LOG.debug(result_cache);
        return vcode.equals(result_cache);
    }

    private String getVcode(){
	    return String.valueOf(new Random().nextInt(1000000));
    }
}
