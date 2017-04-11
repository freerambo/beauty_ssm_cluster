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
public class UtilServiceTest {
	final static Logger logger = Logger.getLogger(UtilServiceTest.class);

	@Autowired
	UtilService service;

	@Test
	public void testCheckShanglianSignContent() {
		String content = "https://api.wozhixixi.com/common_apps/notices/shanglian?signature=CwWuuKXFlVpBxrKkF%2FnApsKT%2BBvY5b%2BOMBdzkiKVL0p0r2BDFWGGXG%2F8yfiozXHh%2FxrZxTUgV3qDaeqsuqZ%2FA%2BBgTshx4hA6Jbuv%2B%2Bcu07CqqPTCIOZ9%2FfqIXPgeu8AjgqG%2FC%2BjJUY3XSImyZWyZW%2FO7Z4D%2F5kx56PkuX3rTfb8%3D&txnType=00&merId=812440048120327&merName=李动涛个体户&orderId=149180745410001498&txnTime=20170410145735&orderQid=1020170410145735124135&txnAmt=123&currencyCode=156&orderStau=0006&respTime=null&settleAmount=123&settleCurrency=156&settleDate=null&cupReserved=&cardType=&outTransactionNo=";
		String signature = "CwWuuKXFlVpBxrKkF%2FnApsKT%2BBvY5b%2BOMBdzkiKVL0p0r2BDFWGGXG%2F8yfiozXHh%2FxrZxTUgV3qDaeqsuqZ%2FA%2BBgTshx4hA6Jbuv%2B%2Bcu07CqqPTCIOZ9%2FfqIXPgeu8AjgqG%2FC%2BjJUY3XSImyZWyZW%2FO7Z4D%2F5kx56PkuX3rTfb8%3D";
	    boolean result = service.checkShanglianSignContent(content, signature, "UTF-8");
		Assert.assertTrue(result);
	}

}
