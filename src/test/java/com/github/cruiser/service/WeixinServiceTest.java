package com.github.cruiser.service;

import com.github.cruiser.cache.RedisCache;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


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
				"2016年06月07日19时24分","6504.09元");
	}

}
