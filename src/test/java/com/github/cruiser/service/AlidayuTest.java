package com.github.cruiser.service;

import com.alibaba.fastjson.JSON;
import com.taobao.api.domain.BizResult;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Qiming on 2016/9/25.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-*.xml")
public class AlidayuTest {
	final static Logger logger = Logger.getLogger(AlidayuTest.class);

    @Autowired
	private AliDaYuMessageService service;

	@Test
	public void aliDayuMsgTest() {

        BizResult result = service.sendRegisterCode("叮当",
                "15627862871");
        Assert.assertEquals(true, result.getSuccess());
        logger.info(result.getMsg());
        logger.info(result.getErrCode());
        logger.info(result.getModel());
        logger.info(result.getSuccess());
	}

    @Test
    public void test() {
        Map<String, String> paramString = new HashMap<>();
        paramString.put("name", "1");
        paramString.put("v_code", "2");
        paramString.put("e_time", "3");
        logger.info(JSON.toJSONString(paramString));
    }
}
