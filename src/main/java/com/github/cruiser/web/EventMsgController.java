package com.github.cruiser.web;

import com.github.cruiser.entity.EventMsg;
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
@RequestMapping(value = "/event_msgs")
public class EventMsgController implements IController<EventMsg> {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    //@Autowired
    //UserService userService;  //Service which will do all data retrieval/manipulation work

    @Override
    @RequestMapping(value = "",
            params = {"limit", "offset"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<EventMsg>> getByLimit(@RequestParam("limit") int limit,
                                                     @RequestParam("offset") int offset) {

        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        EventMsg eventMsg = new EventMsg();
        eventMsg.setGmtCreate(new Date());
        eventMsg.setGmtModified(new Date());
        List<EventMsg> list = new ArrayList<>();
        list.add(eventMsg);
        return ResponseEntity.ok(list);
    }

    @Override
    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventMsg> getEntityById(@PathVariable("id") long id) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        EventMsg eventMsg = new EventMsg();
        eventMsg.setGmtCreate(new Date());
        eventMsg.setGmtModified(new Date());
        return ResponseEntity.ok(eventMsg);
    }

    @Override
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventMsg> updateEntity(@PathVariable("id")long id, @RequestBody EventMsg entity, UriComponentsBuilder ucBuilder) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        EventMsg eventMsg = new EventMsg();
        eventMsg.setGmtCreate(new Date());
        eventMsg.setGmtModified(new Date());
        return new ResponseEntity<EventMsg>(eventMsg, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PATCH,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<EventMsg> updateEntityBySelective(@PathVariable("id")long id, @RequestBody EventMsg entity, UriComponentsBuilder
            ucBuilder) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        EventMsg eventMsg = new EventMsg();
        eventMsg.setGmtCreate(new Date());
        eventMsg.setGmtModified(new Date());
        return new ResponseEntity<EventMsg>(eventMsg, HttpStatus.OK);
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
    public ResponseEntity<Void> createEntity(@RequestBody EventMsg entity, UriComponentsBuilder ucBuilder) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.CREATED);
    }

}