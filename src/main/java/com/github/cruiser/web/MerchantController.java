package com.github.cruiser.web;

import com.github.cruiser.entity.Cashier;
import com.github.cruiser.entity.Merchant;
import com.github.cruiser.entity.Route;
import com.github.cruiser.enums.ResultEnum;
import com.github.cruiser.exception.BizException;
import com.github.cruiser.service.MerchantsService;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/merchants")
public class MerchantController implements IController<Merchant> {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    MerchantsService merchantsService;
    @Autowired
    RoutesService routesService;

    @Override
    @RequestMapping(value = "",
            params = {"limit", "offset"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Merchant>> getEntityListByLimit(@RequestParam("limit") int limit,
                                                               @RequestParam("offset") int offset) {

        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return ResponseEntity.ok(merchantsService.getEntityListByLimit(offset, limit));
    }

    @RequestMapping(value = "",
            params = {"merchant_code", "merchant_type", "boss_certificate_number",
                    "settlement_account", "saler_code", "limit", "offset"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Merchant>> queryBySelective(@RequestParam Map params) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        int limit = null == params.get("limit") ? 10 : (int) params.get("limit");
        int offset = null == params.get("offset") ? 0 : (int) params.get("offset");
        return ResponseEntity.ok(merchantsService.queryBySelective(
                (String) params.get("merchant_code"),
                (String) params.get("merchant_type"),
                (String) params.get("boss_certificate_number"),
                (String) params.get("settlement_account"),
                (String) params.get("saler_code"),
                limit,
                offset
        ));
    }

    @Override
    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Merchant> getEntityById(@PathVariable("id") long id) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return ResponseEntity.ok(merchantsService.getEntityById(id));
    }

    @RequestMapping(value = "/{id}/routes",
            params = {"limit", "offset"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Route>> getRouteEntityByMerchantId(@PathVariable("id") long id,
                                                                  @RequestParam("limit") int limit,
                                                                  @RequestParam("offset") int offset) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return ResponseEntity.ok(routesService.getRouteEntityByMerchantId(id, offset, limit));
    }

    @RequestMapping(value = "/{merchant_id}/routes/{route_id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Route> getRouteEntityByRouteId(@PathVariable("merchant_id") long merchant_id,
                                                         @PathVariable("route_id") long route_id) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        Route route = routesService.getEntityById(route_id);
        if (null == route || merchant_id != route.getMerchantId()) {
            throw new BizException(ResultEnum.PARAM_ERROR.getMsg());
        }
        return ResponseEntity.ok(route);
    }

    @Override
    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createEntity(@RequestBody Merchant entity, UriComponentsBuilder ucBuilder) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        merchantsService.insertEntity(entity);
        return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.CREATED);
    }

    @Override
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Merchant> updateEntity(@PathVariable("id") long id,
                                                 @RequestBody Merchant entity, UriComponentsBuilder ucBuilder) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return new ResponseEntity<Merchant>(merchantsService.updateEntity(id, entity), HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PATCH,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Merchant> updateEntityBySelective(@PathVariable("id") long id, @RequestBody Merchant entity, UriComponentsBuilder
            ucBuilder) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return new ResponseEntity<Merchant>(merchantsService.updateEntityBySelective(id, entity), HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteEntity(@PathVariable("id") long id) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        merchantsService.deleteEntity(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }


}