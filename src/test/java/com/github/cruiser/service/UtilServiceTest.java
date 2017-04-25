package com.github.cruiser.service;

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
public class UtilServiceTest {
    final static Logger logger = Logger.getLogger(UtilServiceTest.class);

    @Autowired
    UtilService service;

    @Test
    public void testCheckShanglianSignContent() {
        boolean result = service.checkShanglianSignContent
                ("currencyCode=156&merId=812440048120327&merName" +
                                "=李动涛个体户&orderId=149198154010001518&orderQid=1020170412151900124155&orderStau=0006&respTime=null&settleAmount=1&settleCurrency=156&settleDate=null&txnAmt=1&txnTime=20170412151900&txnType=00",
                        "LiMaxkPF0kQeqwOJE4oMxBoxJgy7mLDypwXbi3d8wKFoYuwdXoEpVWPXt4lfD10yV00aDdESKSHnHQiBFLFw8LiNPW7GnI2Y7qVLD988CB/M4PnXPE3pL4HEfsfPhYDK5gX6gX7RzhggN/z+BXXbb9yKO89A8IfdwRdctK/zOvE=",
                        "");
        Assert.assertTrue(result);
    }

    @Test
    public void testKeysValuesContent() {
        String signature = "LiMaxkPF0kQeqwOJE4oMxBoxJgy7mLDypwXbi3d8wKFoYuwdXoEpVWPXt4lfD10yV00aDdESKSHnHQiBFLFw8LiNPW7GnI2Y7qVLD988CB/M4PnXPE3pL4HEfsfPhYDK5gX6gX7RzhggN/z+BXXbb9yKO89A8IfdwRdctK/zOvE=";
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("signature", "LiMaxkPF0kQeqwOJE4oMxBoxJgy7mLDypwXbi3d8wKFoYuwdXoEpVWPXt4lfD10yV00aDdESKSHnHQiBFLFw8LiNPW7GnI2Y7qVLD988CB/M4PnXPE3pL4HEfsfPhYDK5gX6gX7RzhggN/z+BXXbb9yKO89A8IfdwRdctK/zOvE=");
        paramsMap.put("txnType", "00");
        paramsMap.put("merId", "812440048120327");
        paramsMap.put("merName", "李动涛个体户");
        paramsMap.put("orderId", "149198154010001518");
        paramsMap.put("txnTime", "20170412151900");
        paramsMap.put("orderQid", "1020170412151900124155");
        paramsMap.put("txnAmt", "1");
        paramsMap.put("currencyCode", "156");
        paramsMap.put("orderStau", "0006");
        paramsMap.put("respTime", "null");
        paramsMap.put("settleAmount", "1");
        paramsMap.put("settleCurrency", "156");
        paramsMap.put("settleDate", "null");
        paramsMap.put("cupReserved", null);
        paramsMap.put("cardType", null);
        paramsMap.put("outTransactionNo", null);
        String plantString = service.getKeysValuesContent(paramsMap);
        boolean result = service.checkShanglianSignContent(plantString, signature, "");
        Assert.assertTrue(result);
    }

}
