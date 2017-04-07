package com.github.cruiser.web;

import com.alibaba.fastjson.JSON;
import com.github.cruiser.dto.SmsVcodeMessage;
import com.github.cruiser.service.AliDaYuMessageService;
import com.taobao.api.domain.BizResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/sms_msgs")
public class SmsMessageController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    AliDaYuMessageService service;

    @RequestMapping(value = "",
            params = {"action", "receive_number", "v_code"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> getEntityListByLimit(@RequestParam("receive_number") String receiveNumber,
                                                     @RequestParam("v_code") String vCode) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        if (service.verrifyVcode(receiveNumber, vCode)) {
            return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.OK);
        } else {
            return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "",
            params = {"action"},
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createEntity(@RequestBody String message,
                                             @RequestParam("action") String action,
                                             UriComponentsBuilder ucBuilder) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        if (action.equals("v_code")) {
            SmsVcodeMessage smsVcodeMessage = JSON
                    .parseObject(message, SmsVcodeMessage.class);
            LOG.debug(smsVcodeMessage.getCustom_name());
            LOG.debug(smsVcodeMessage.getReceive_number());
            BizResult result = service
                    .sendRegisterCode(smsVcodeMessage.getCustom_name(),
                            smsVcodeMessage.getReceive_number());
            if (result.getSuccess()) {
                return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.CREATED);
            } else {
                return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.BAD_REQUEST);
        }

    }

    //http://www.ruanyifeng.com/blog/2016/04/cors.html
    //https://www.tianmaying.com/tutorial/cross-origin-rest-service
    //https://segmentfault.com/a/1190000007366644
    //https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Access_control_CORS
    //https://bitbucket.org/thetransactioncompany/cors-filter
    //https://github.com/eBay/cors-filter/blob/master/src/main/java/org/ebaysf/web/cors/CORSFilter.java
    @RequestMapping(value = "",
            params = {"action"},
            method = RequestMethod.OPTIONS)
    public ResponseEntity<Void> beforePost() {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        HttpHeaders headers = new HttpHeaders();
        headers.setAccessControlAllowOrigin("*");

        List<HttpMethod> httpMethodList = new ArrayList<>();
        httpMethodList.add(HttpMethod.OPTIONS);
        httpMethodList.add(HttpMethod.GET);
        httpMethodList.add(HttpMethod.POST);

        headers.setAccessControlAllowMethods(httpMethodList);
        headers.setAccessControlMaxAge(3600L);

        List<String> allowHeaders = new ArrayList<>();
        allowHeaders.add("x-requested-with");
        headers.setAccessControlAllowHeaders(allowHeaders);
        return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.OK);

    }


}