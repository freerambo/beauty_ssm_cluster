package com.github.cruiser.web;

import com.github.cruiser.entity.Order;
import com.github.cruiser.enums.CommonValue;
import com.github.cruiser.enums.CurrencyCode;
import com.github.cruiser.enums.SettleOrderState;
import com.github.cruiser.enums.SettleState;
import com.github.cruiser.service.OrdersService;
import com.github.cruiser.service.UtilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    private UtilService utilService;

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
            @RequestParam("cardType") String cardType
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
        LOG.info(paramsMap.toString());
        if (!utilService.checkShanglianSignContent(paramsMap, signature, "UTF-8")) {
            return new ResponseEntity<String>("", HttpStatus.BAD_REQUEST);
        }
        if (ordersService.getEntityListByOrderNumber(orderId).size()>0){
            return ResponseEntity.ok(SUCCESS_RESPONSE);
        }
        Order order = new Order();
        order.setOrderNumber(orderId);
        order.setMerchantCode(merId);
        order.setMerchantName(merName);
        order.setTxnAmt(txnAmt.divide(new BigDecimal("100")));
        order.setCurrencyCode(
                currencyCode.equals(CurrencyCode.SHANG_LIAN_CNY_CODE.getValue()) ?
                        CurrencyCode.YIN_MA_TONG_CNY_CODE.getValue() :
                        CurrencyCode.DEFAULT_ERROR.getValue());
        order.setUpstreamOrderState(orderStau);
        order.setSettleOrderState(SettleOrderState.UNCONFIRM.getValue());
        order.setSettleState(SettleState.UN_CLEARED.getValue());
        order.setGmtCreate(new Date());
        order.setGmtModified(new Date());
        order.setModifiedPerson(CommonValue.SYSTEM_USER.getValue());
        ordersService.insertEntity(order);
        return ResponseEntity.ok(SUCCESS_RESPONSE);
    }

}
