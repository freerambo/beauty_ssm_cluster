package com.github.cruiser.web;

import com.github.cruiser.entity.Upstream;
import com.github.cruiser.entity.Route;
import com.github.cruiser.service.UpstreamsService;
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
@RequestMapping(value = "/upstreams")
public class UpstreamController implements IController<Upstream> {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    @Autowired
    UpstreamsService service;

    @Override
    @RequestMapping(value = "",
            params = {"limit", "offset"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Upstream>> getEntityListByLimit(@RequestParam("limit") int limit,
                                                               @RequestParam("offset") int offset) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return ResponseEntity.ok(service.getEntityListByLimit(offset, limit));
    }

    @Override
    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Upstream> getEntityById(@PathVariable("id") long id) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return ResponseEntity.ok(service.getEntityById(id));
    }

    @Override
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Upstream> updateEntity(@PathVariable("id") long id,
                                                 @RequestBody Upstream entity,
                                                 UriComponentsBuilder ucBuilder) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return new ResponseEntity<Upstream>(service.updateEntity(id, entity), HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PATCH,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Upstream> updateEntityBySelective(@PathVariable("id") long id,
                                                            @RequestBody Upstream entity,
                                                            UriComponentsBuilder ucBuilder) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return new ResponseEntity<Upstream>(service.updateEntityBySelective(id, entity), HttpStatus.OK);
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
    public ResponseEntity<Void> createEntity(@RequestBody Upstream entity,
                                             UriComponentsBuilder ucBuilder) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        service.insertEntity(entity);
        return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}/routes",
            params = {"limit", "offset"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Route>> getRouteEntityByUpstreamId(@PathVariable("id") long id,
                                                                  @RequestParam("limit") int limit,
                                                                  @RequestParam("offset") int offset) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return ResponseEntity.ok(service.getRouteEntityByUpstreamId(id, limit, offset));
    }

    @RequestMapping(value = "/{upstream_id}/routes/{route_id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Route> getRouteEntityByRouteId(@PathVariable("upstream_id") long upstreamId,
                                                         @PathVariable("route_id") long routeId) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return ResponseEntity.ok(service.getRouteEntityByRouteId(upstreamId, routeId));
    }

    @RequestMapping(value = "?action=search",
            params = {"upstream_name", "limit", "offset"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Upstream>> queryUpstreamByName(@RequestParam("upstream_name") String upstreamName,
                                                              @RequestParam("limit") int limit,
                                                              @RequestParam("offset") int offset) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return new ResponseEntity<>(service.queryUpstreamByName(upstreamName, limit, offset), HttpStatus.OK);
    }

}