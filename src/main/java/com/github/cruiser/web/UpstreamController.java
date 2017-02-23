package com.github.cruiser.web;

import com.github.cruiser.entity.Cashier;
import com.github.cruiser.entity.Upstream;
import com.github.cruiser.entity.Route;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping(value = "/upstreams")
public class UpstreamController implements IController<Upstream> {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    //@Autowired
    //UserService userService;  //Service which will do all data retrieval/manipulation work

    @Override
    @RequestMapping(value = "",
            params = {"limit", "offset"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Upstream>> getByLimit(@RequestParam("limit") int limit,
                                                     @RequestParam("offset") int offset) {

        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        Upstream upstream = new Upstream();
        upstream.setGmtCreate(new Date());
        upstream.setGmtModified(new Date());
        upstream.setModifiedPerson("王五");
        List<Upstream> list = new ArrayList<>();
        list.add(upstream);
        return ResponseEntity.ok(list);
    }

    @Override
    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Upstream> getEntityById(@PathVariable("id") long id) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        Upstream upstream = new Upstream();
        upstream.setGmtCreate(new Date());
        upstream.setGmtModified(new Date());
        upstream.setModifiedPerson("王五");
        return ResponseEntity.ok(upstream);
    }

    @Override
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Upstream> updateEntity(@PathVariable("id")long id, @RequestBody Upstream entity, UriComponentsBuilder ucBuilder) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        Upstream upstream = new Upstream();
        upstream.setGmtCreate(new Date());
        upstream.setGmtModified(new Date());
        return new ResponseEntity<Upstream>(upstream, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PATCH,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Upstream> updateEntityBySelective(@PathVariable("id")long id, @RequestBody Upstream entity, UriComponentsBuilder
            ucBuilder) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        Upstream upstream = new Upstream();
        upstream.setGmtCreate(new Date());
        upstream.setGmtModified(new Date());
        return new ResponseEntity<Upstream>(upstream, HttpStatus.OK);
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
    public ResponseEntity<Void> createEntity(@RequestBody Upstream entity, UriComponentsBuilder ucBuilder) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
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
        Route route = new Route();
        route.setRouteId(1L);
        route.setUpstreamId(2L);
        route.setUpstreamId(3L);
        route.setPriority(50);
        route.setGatewayType("W1");
        route.setQueryString("W017984721847218");
        route.setGmtCreate(new Date());
        route.setGmtModified(new Date());
        route.setModifiedPerson("王五");
        List<Route> list = new ArrayList<>();
        list.add(route);
        return ResponseEntity.ok(list);
    }

    @RequestMapping(value = "/{upstream_id}/routes/{route_id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Route> getRouteEntityByRouteId(@PathVariable("upstream_id") long upstream_id,
                                                            @PathVariable("route_id") long route_id) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        Route route = new Route();
        route.setRouteId(1L);
        route.setUpstreamId(2L);
        route.setUpstreamId(3L);
        route.setPriority(50);
        route.setGatewayType("W1");
        route.setQueryString("W017984721847218");
        route.setGmtCreate(new Date());
        route.setGmtModified(new Date());
        route.setModifiedPerson("王五");
        return ResponseEntity.ok(route);
    }

    @RequestMapping(value = "?action=search",
            params = {"upstream_name", "limit", "offset"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Upstream> signinCashier(@RequestParam("upstream_name") String upstreamName,
                                                 @RequestParam("limit") int limit,
                                                 @RequestParam("offset") int offset) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return new ResponseEntity<>(new Upstream(), HttpStatus.OK);
    }

}