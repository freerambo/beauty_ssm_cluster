package com.github.cruiser.web;

import com.github.cruiser.entity.Order;
import com.github.cruiser.service.OrdersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController implements IController<Order> {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    OrdersService service;

    @Override
    @RequestMapping(value = "",
            params = {"limit", "offset"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> getEntityListByLimit(@RequestParam("limit") int limit,
                                                            @RequestParam("offset") int offset) {

        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return ResponseEntity.ok(service.getEntityListByLimit(offset, limit));
    }

    @RequestMapping(value = "",
            params = {"limit", "offset", "settleOrderState", "merchantCode"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> getEntityListByMerchantCode(@RequestParam("limit") int limit,
                                                                   @RequestParam("offset") int offset,
                                                                   @RequestParam("settleOrderState") String settleOrderState,
                                                                   @RequestParam("merchantCode") String merchantCode) {

        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return ResponseEntity.ok(service.getEntityListByMerchantCode(offset, limit, settleOrderState, merchantCode));
    }

    @Override
    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> getEntityById(@PathVariable("id") long id) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return ResponseEntity.ok(service.getEntityById(id));
    }

    @RequestMapping(value = "/{id}",
            params = {"settle_order_state", "settle_state", "settle_amt", "settle_time"},
            method = RequestMethod.PATCH,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> updateEntityByParams(@PathVariable("id") long id,
                                                      @RequestParam("settle_order_state") String settleOrderState,
                                                      @RequestParam("settle_state") String settleState,
                                                      @RequestParam("settle_amt") BigDecimal settleAmt,
                                                      @RequestParam("settle_time") Date settleTime) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return ResponseEntity.ok(service.updateEntityByParams(id,
                settleOrderState, settleState, settleAmt, settleTime));
    }

    @Override
    /*@RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)*/
    public ResponseEntity<Order> updateEntity(@PathVariable("id") long id, @RequestBody Order entity, UriComponentsBuilder ucBuilder) {
        return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    /*@RequestMapping(value = "/{id}",
            method = RequestMethod.PATCH,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)*/
    public ResponseEntity<Order> updateEntityBySelective(@PathVariable("id") long id, @RequestBody Order entity, UriComponentsBuilder
            ucBuilder) {
        return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    /*@RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)*/
    public ResponseEntity<Void> deleteEntity(@PathVariable("id") long id) {
        return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    /*@RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)*/
    public ResponseEntity<Void> createEntity(@RequestBody Order entity, UriComponentsBuilder ucBuilder) {
        return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @RequestMapping(value = "",
            params = {"limit", "offset", "order_by"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> getEntityListByOrder(@RequestParam("limit") int limit,
                                                            @RequestParam("offset") int offset,
                                                            @RequestParam("order_by") String orderBy) {

        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return ResponseEntity.ok(service.getEntityListByOrder(offset, limit, orderBy));
    }

}