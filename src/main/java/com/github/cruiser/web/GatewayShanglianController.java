package com.github.cruiser.web;

import com.github.cruiser.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/shanglian_gateway")
public class GatewayShanglianController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private OrdersService ordersService;

    @Autowired
    private WeixinService weixinService;

    @Autowired
    private UtilService utilService;

    @Autowired
    private MerchantsService merchantsService;

    @Autowired
    private CashiersService cashiersService;

    /**
     * 交易结果主动通知的接口
     *
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
    @RequestMapping(value = "/notices", method = RequestMethod.POST)
    public ResponseEntity<String> notice(
            @RequestParam("signature") String signature,
            @RequestParam("txnType") String txnType,
            @RequestParam("merId") String merId,
            @RequestParam("merName") String merName,
            @RequestParam("orderId") String orderId,
            @RequestParam("txnTime") String txnTime,
            @RequestParam("orderQid") String orderQid,
            @RequestParam("txnAmt") BigDecimal txnAmt,
            @RequestParam("currencyCode") String currencyCode,
            @RequestParam("orderStau") String orderStau,
            @RequestParam("respTime") String respTime,
            @RequestParam("settleAmount") String settleAmount,
            @RequestParam("settleCurrency") String settleCurrency,
            @RequestParam("settleDate") String settleDate,
            @RequestParam("cupReserved") String cupReserved,
            @RequestParam("cardType") String cardType,
            HttpServletRequest request
    ) {
        final String SUCCESS_RESPONSE = "sl notice success!";
        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("signature", signature);
        paramsMap.put("txnType", txnType);
        paramsMap.put("merId", merId);
        paramsMap.put("merName", merName);
        paramsMap.put("orderId", orderId);
        paramsMap.put("txnTime", txnTime);
        paramsMap.put("orderQid", orderQid);
        paramsMap.put("txnAmt", txnAmt.toString());
        paramsMap.put("currencyCode", currencyCode);
        paramsMap.put("orderStau", orderStau);
        paramsMap.put("respTime", respTime);
        paramsMap.put("settleAmount", settleAmount);
        paramsMap.put("settleCurrency", settleCurrency);
        paramsMap.put("settleDate", settleDate);
        paramsMap.put("cupReserved", cupReserved);
        paramsMap.put("cardType", cardType);
        LOG.info("notices: " + paramsMap.toString());
        LOG.info("**QueryString: " + request.getQueryString());

        //对签名进行校验，检验不通过，返回失败
        String plantText = utilService.getKeysValuesContent(paramsMap);
        boolean result = utilService.checkShanglianSignContent(plantText, signature, "utf-8");
        if (!result) {
            LOG.info("error signature!");
            return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
        }
        //判断如果之前已经有记录即返回而不做任何操作
        if (ordersService.getEntityListByOrderNumber(orderId).size() > 0) {
            LOG.info("already one record!");
            return ResponseEntity.ok(SUCCESS_RESPONSE);
        }
        paramsMap.put("upstreamAlias", "SHANGLIAN_DEV");
        String merchantCode = merchantsService
                .queryMerchantCodeByUpstreamAliasThirdMerchantCode(
                        paramsMap.get("upstreamAlias"),
                        paramsMap.get("merId"));
        paramsMap.put("merchantCode", merchantCode);
        ordersService.insertEntityByParams(paramsMap);

        Long merchantId = merchantsService
                .queryMerchantIdByUpstreamAliasThirdMerchantCode(
                        paramsMap.get("upstreamAlias"),
                        paramsMap.get("merId"));
        String openId = cashiersService.getActiveEntityById(merchantId.longValue()).getOpenId();
        LOG.debug("openId: " + openId);
        weixinService.pushTemplateMsg(openId, orderId, merName,
                txnTime, txnAmt.divide(new BigDecimal("100")).toString());
        return ResponseEntity.ok(SUCCESS_RESPONSE);
    }

}
