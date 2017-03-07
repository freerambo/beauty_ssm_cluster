package com.github.cruiser.service;

import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.AlibabaAliqinFcSmsNumSendRequest;
import com.taobao.api.response.AlibabaAliqinFcSmsNumSendResponse;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Qiming on 2016/9/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-*.xml")
public class AlidayuTest {
	final static Logger logger = Logger.getLogger(AlidayuTest.class);

	@Test
	public void aliDayuMsgTest() {
	    String url="http://gw.api.taobao.com/router/rest";
	    String appkey="23631269";
	    String secret ="d1cfd25de31e9926110a1f01465611ec";
		TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
		AlibabaAliqinFcSmsNumSendRequest req = new AlibabaAliqinFcSmsNumSendRequest();
		req.setExtend("原样回传");
		req.setSmsType("normal");
		req.setSmsFreeSignName("阿里大于");
		req.setSmsParamString("{\"name\":\"咸蛋超人\",\"v_code\":\"123456\",\"e_time\":\"1\"}");
		req.setRecNum("13570959854");
		req.setSmsTemplateCode("SMS_46220001");
        //req.setSmsTemplateCode("SMS_46115050");
        AlibabaAliqinFcSmsNumSendResponse rsp = null;
        try {
            rsp = client.execute(req);
        } catch (ApiException e) {
            e.printStackTrace();
        }
        System.out.println(rsp.getBody());
	}

}
