package com.github.cruiser.web;

import com.github.cruiser.service.UtilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/shanglian")
public class ShanglianController {

	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private UtilService utilService;

	/**
	 * 交易结果主动通知的接口
	 * @param signature
	 * @param txnType
	 * @param merId
	 * @param merName
	 * @param orderId
	 * @param txnTime
	 * @param orderQid
	 * @param txnAmt
	 * @param currencyCode
	 * @param orderStau
	 * @param respTime
	 * @param settleAmount
	 * @param settleCurrency
	 * @param settleDate
	 * @param cupReserved
	 * @param cardType
	 * @return
	 */
	@RequestMapping(value = "/notice", method = RequestMethod .POST)
	@ResponseBody
	public String notice(
			@RequestParam("signature") String signature,
			@RequestParam("txnType") String txnType,
			@RequestParam("merId") String merId,
			@RequestParam("merName") String merName,
			@RequestParam("orderId") String orderId,
			@RequestParam("txnTime") String txnTime,
			@RequestParam("orderQid") String orderQid,
			@RequestParam("txnAmt") String txnAmt,
			@RequestParam("currencyCode") String currencyCode,
			@RequestParam("orderStau") String orderStau,
			@RequestParam("respTime") String respTime,
			@RequestParam("settleAmount") String settleAmount,
			@RequestParam("settleCurrency") String settleCurrency,
			@RequestParam("settleDate") String settleDate,
			@RequestParam("cupReserved") String cupReserved,
			@RequestParam("cardType") String cardType
			) {
		String SUCCESS_RESPONSE = "sl notice success!";
	    Map<String, String> paramsMap = new HashMap<>();
		paramsMap.put("signature", signature);
		paramsMap.put("txnType", txnType);
		paramsMap.put("merId", merId);
		paramsMap.put("merName", merName);
		paramsMap.put("orderId", orderId);
		paramsMap.put("txnTime", txnTime);
		paramsMap.put("orderQid", orderQid);
		paramsMap.put("txnAmt", txnAmt);
		paramsMap.put("currencyCode", currencyCode);
		paramsMap.put("orderStau", orderStau);
		paramsMap.put("respTime", respTime);
		paramsMap.put("settleAmount", settleAmount);
		paramsMap.put("settleCurrency", settleCurrency);
		paramsMap.put("settleDate", settleDate);
		paramsMap.put("cupReserved", cupReserved);
		paramsMap.put("cardType", cardType);
		LOG.info(paramsMap.toString());
		//return utilService.checkShanglianSignContent(paramsMap,signature,
		//		"UTF-8")? SUCCESS_RESPONSE: "";
		return SUCCESS_RESPONSE;
	}

}
