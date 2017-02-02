package com.github.cruiser.service;

import com.github.cruiser.cache.RedisCache;
import com.github.cruiser.dto.xml.WeixinTextMsg;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

/**
 * Created by Qiming on 2016/9/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-*.xml")
public class WeixinServiceTest {
	final static Logger logger = Logger.getLogger(WeixinServiceTest.class);

	@Autowired
	WeixinService weixinService;

	@Autowired
	RedisCache redisCache;

	@Test
	public void testGetAccessToken() {
		redisCache.clearCache();
		Assert.assertNotNull(weixinService.getAccessToken());
		Assert.assertNotNull(weixinService.getAccessToken());
	}

	@Test
	public void testPushTemplateMsg() {
		weixinService.pushTemplateMsg("o3g--vnH0WoX9KF0EyTeOa1REKhM",
				"hehe1234567890",
				"商户A",
				"2016年06月07日19时24分", "6504.09元");
	}

	@Test
	public void testWeixinTextMsg() throws JAXBException {
		String xml = "<xml><ToUserName><![CDATA[toUser]]></ToUserName><FromUserName><![CDATA[fromUser]]></FromUserName><CreateTime>1348831860</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[thisisatest]]></Content><MsgId>1234567890123456</MsgId></xml>";
		JAXBContext jc = JAXBContext.newInstance(WeixinTextMsg.class);
		Unmarshaller u = jc.createUnmarshaller();
		StringBuffer xmlStr = new StringBuffer(xml);
		WeixinTextMsg textMsg = (WeixinTextMsg) u.unmarshal(new StreamSource(new
				StringReader (xml)));
		logger.info(textMsg.getToUserName());
		logger.info(textMsg.getFromUserName());
		logger.info(textMsg.getCreateTime());
		logger.info(textMsg.getMsgType());
		logger.info(textMsg.getContent());
		logger.info(textMsg.getMsgId());

	}
}
