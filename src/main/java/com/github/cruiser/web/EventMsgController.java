package com.github.cruiser.web;

import com.github.cruiser.entity.EventMsg;
import com.github.cruiser.service.EventMsgsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping(value = "/event_msgs")
public class EventMsgController implements IController<EventMsg> {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    EventMsgsService service;

    @Override
    @RequestMapping(value = "",
            params = {"limit", "offset"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EventMsg>> getEntityListByLimit(@RequestParam("limit") int limit,
                                                               @RequestParam("offset") int offset) {

        LOG.debug("enter: " + Thread.currentThread().getStackTrace()[1].getMethodName());
        return ResponseEntity.ok(service.getEntityListByLimit(offset, limit));
    }

    @Override
    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventMsg> getEntityById(@PathVariable("id") long id) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return ResponseEntity.ok(service.getEntityById(id));
    }

    @RequestMapping(value = "",
            params = {"user_openid", "limit", "offset"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<List<EventMsg>> getEntityListByOpenid(@RequestParam("user_openid") String userOpenid,
                                                                @RequestParam("limit") int limit,
                                                                @RequestParam("offset") int offset) {

        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return ResponseEntity.ok(service.getEntityListByOpenid(userOpenid, limit, offset));
    }

    @RequestMapping(value = "",
            params = {"order_number", "limit", "offset"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_USER','ROLE_ADMIN')")
    public ResponseEntity<List<EventMsg>> getEntityListByOrderNumber(@RequestParam("order_number") String orderNumber,
                                                                     @RequestParam("limit") int limit,
                                                                     @RequestParam("offset") int offset) {

        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return ResponseEntity.ok(service.getEntityListByOpenid(orderNumber, limit, offset));
    }

    @Override
    /*@RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)*/
    public ResponseEntity<EventMsg> updateEntity(@PathVariable("id") long id, @RequestBody EventMsg entity, UriComponentsBuilder ucBuilder) {
        return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
    }

    @Override
    /*@RequestMapping(value = "/{id}",
            method = RequestMethod.PATCH,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)*/
    public ResponseEntity<EventMsg> updateEntityBySelective(@PathVariable("id") long id, @RequestBody EventMsg entity, UriComponentsBuilder
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
    public ResponseEntity<Void> createEntity(@RequestBody EventMsg entity, UriComponentsBuilder ucBuilder) {
        return new ResponseEntity(HttpStatus.METHOD_NOT_ALLOWED);
    }

}