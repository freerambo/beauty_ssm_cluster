package com.github.cruiser.web;

import com.github.cruiser.entity.Cashier;
import com.github.cruiser.service.CashiersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/merchants")
public class CashierController {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    CashiersService cashiersService;

    @RequestMapping(value = "/{merchant_id}/cashiers",
            params = {"limit", "offset"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Cashier>> getEntityListByLimit(@RequestParam("limit") int limit,
                                                              @RequestParam("offset") int offset,
                                                              @PathVariable("merchant_id") long merchantId) {

        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return ResponseEntity.ok(cashiersService.getEntityListByLimit(merchantId, offset, limit));
    }

    @RequestMapping(value = "/{merchant_id}/cashiers/{cashier_id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cashier> getEntityById(@PathVariable("merchant_id") long merchantId, @PathVariable("cashier_id") long cashierId) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return ResponseEntity.ok(cashiersService.getEntityById(merchantId, cashierId));
    }

    @RequestMapping(value = "/{merchant_id}/cashiers",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createEntity(@RequestBody Cashier entity, UriComponentsBuilder ucBuilder, @PathVariable("merchant_id") long merchantId) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        cashiersService.insertEntity(entity, merchantId);
        return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{merchant_id}/cashiers/{cashier_id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cashier> updateEntity(@PathVariable("merchant_id") long merchantId, @PathVariable("cashier_id") long cashierId,
                                                @RequestBody Cashier entity, UriComponentsBuilder ucBuilder) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return new ResponseEntity<Cashier>(cashiersService.updateEntity(merchantId, cashierId, entity), HttpStatus.OK);
    }

    @RequestMapping(value = "/{merchant_id}/cashiers/{cashier_id}",
            method = RequestMethod.PATCH,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Cashier> updateEntityBySelective(@PathVariable("merchant_id") long merchantId, @PathVariable("cashier_id") long cashierId,
                                                           @RequestBody Cashier entity, UriComponentsBuilder ucBuilder) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return new ResponseEntity<Cashier>(cashiersService.updateEntityBySelective(merchantId, cashierId, entity), HttpStatus.OK);
    }

    @RequestMapping(value = "/{merchant_id}/cashiers/{cashier_id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteEntity(@PathVariable("merchant_id") long merchantId, @PathVariable("cashier_id") long cashierId) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        cashiersService.deleteEntity(merchantId, cashierId);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }


    @RequestMapping(value = "/{merchant_id}?action=signin",
            params = {"open_id"},
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> signinCashier(@PathVariable("merchant_id") long merchantId,
                                                 @RequestParam("open_id") String openId,
                                                 @RequestBody Cashier entity,
                                                 UriComponentsBuilder ucBuilder) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return new ResponseEntity<Boolean>(cashiersService.signIn(merchantId, openId), HttpStatus.OK);
    }
}