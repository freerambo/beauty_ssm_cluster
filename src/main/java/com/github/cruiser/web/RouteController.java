package com.github.cruiser.web;

import com.github.cruiser.entity.Route;
import com.github.cruiser.entity.Route;
import com.github.cruiser.enums.GatewayType;
import com.github.cruiser.service.RoutesService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/routes")
public class RouteController implements IController<Route> {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    RoutesService service;

    @Override
    @RequestMapping(value = "",
            params = {"limit", "offset"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Route>> getEntityListByLimit(@RequestParam("limit") int limit,
                                                            @RequestParam("offset") int offset) {

        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return ResponseEntity.ok(service.getEntityListByLimit(offset, limit));
    }

    @Override
    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Route> getEntityById(@PathVariable("id") long id) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return ResponseEntity.ok(service.getEntityById(id));
    }

    @Override
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Route> updateEntity(@PathVariable("id")long id, @RequestBody Route entity, UriComponentsBuilder ucBuilder) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return new ResponseEntity<Route>(service.updateEntity(id, entity), HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PATCH,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Route> updateEntityBySelective(@PathVariable("id")long id, @RequestBody Route entity, UriComponentsBuilder
            ucBuilder) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return new ResponseEntity<Route>(service.updateEntityBySelective(id, entity), HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteEntity(@PathVariable("id") long id) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        service.deleteEntity(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @Override
    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createEntity(@RequestBody Route entity, UriComponentsBuilder ucBuilder) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        service.insertEntity(entity);
        return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.CREATED);
    }

    @Autowired
    private RoutesService routesService;

    /**
     * 根据路由获取优先级最高的路由记录
     * @param heads
     * @param merchantId
     * @return
     */
    @RequestMapping(value = "/merchant_id/{merchantId}", method = RequestMethod
            .GET)
    public String getById(@RequestHeader Map<String, String> heads,
                          @PathVariable("merchantId") long merchantId) {
        LOG.info("heads: ");
        LOG.info("-------------------------------");
        LOG.info(heads.toString());
        LOG.info("-------------------------------");

        String result = "";
        String userAgent = heads.get("user-agent");
        if (userAgent.toLowerCase().contains("MicroMessenger".toLowerCase())) {
            result = "redirect:" + routesService.getHightestPriorityById(merchantId, GatewayType.WEIXIN_GATEWAY);
        } else if (userAgent.toLowerCase()
                .contains("AlipayClient".toLowerCase())) {
            result = "redirect:" + routesService.getHightestPriorityById(merchantId, GatewayType.ALIPAY_GATEWAY);
        } else {
            //TODO 404的页面其实还没有做
            result = "404";
        }
        return result;
    }
}