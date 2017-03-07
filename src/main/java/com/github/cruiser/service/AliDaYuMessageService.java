package com.github.cruiser.service;

import com.alibaba.fastjson.JSON;
import com.github.cruiser.enums.ResultEnum;
import com.github.cruiser.exception.CustomException;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

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
	private String overTime;

	@Value("${server.ali.dayu.sms.vcode.template}")
	private String vcodeTemplate;

	/**
	 *
	 * @return
	 */
	public String sendVcode(String customName, String receiveNumber) {
		String url="http://gw.api.taobao.com/router/rest";
		String smsType = "normal";
		//String smsTemplate = "SMS_46220001";
		TaobaoClient client = new DefaultTaobaoClient(url, this.appKey, this.appSecret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("原样回传");
		req.setSmsType(smsType);
		req.setSmsFreeSignName(this.smsSigname);
		req.setSmsParamString("{\"name\":\""+customName+"\",\"v_code\":\"123456\",\"e_time\":\""+overTime+"\"}");
		req.setRecNum(receiveNumber);
		req.setSmsTemplateCode(this.vcodeTemplate);
		AlibabaAliqinFcSmsNumSendResponse rsp = null;
		try {
			rsp = client.execute(req);
		} catch (ApiException e) {
			e.printStackTrace();
		}
		return null;
	}

}
