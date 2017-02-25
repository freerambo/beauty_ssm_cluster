package com.github.cruiser.web;

import com.github.cruiser.entity.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderController implements IController<Order> {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    //@Autowired
    //UserService userService;  //Service which will do all data retrieval/manipulation work

    @Override
    @RequestMapping(value = "",
            params = {"limit", "offset"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Order>> getByLimit(@RequestParam("limit") int limit,
                                                     @RequestParam("offset") int offset) {

        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        Order order = new Order();
        order.setGmtCreate(new Date());
        order.setGmtModified(new Date());
        order.setModifiedPerson("王五");
        List<Order> list = new ArrayList<>();
        list.add(order);
        return ResponseEntity.ok(list);
    }

    @Override
    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> getEntityById(@PathVariable("id") long id) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        Order order = new Order();
        order.setGmtCreate(new Date());
        order.setGmtModified(new Date());
        order.setModifiedPerson("王五");
        return ResponseEntity.ok(order);
    }

    @Override
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> updateEntity(@PathVariable("id")long id, @RequestBody Order entity, UriComponentsBuilder ucBuilder) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        Order order = new Order();
        order.setGmtCreate(new Date());
        order.setGmtModified(new Date());
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PATCH,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Order> updateEntityBySelective(@PathVariable("id")long id, @RequestBody Order entity, UriComponentsBuilder
            ucBuilder) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        Order order = new Order();
        order.setGmtCreate(new Date());
        order.setGmtModified(new Date());
        return new ResponseEntity<Order>(order, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteEntity(@PathVariable("id") long id) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

    @Override
    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createEntity(@RequestBody Order entity, UriComponentsBuilder ucBuilder) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.CREATED);
    }

}