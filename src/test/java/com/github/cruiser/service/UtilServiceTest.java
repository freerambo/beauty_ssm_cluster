package com.github.cruiser.service;

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

}
