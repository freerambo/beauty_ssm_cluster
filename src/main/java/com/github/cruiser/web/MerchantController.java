package com.github.cruiser.web;

import com.github.cruiser.entity.Merchant;
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
import java.util.Map;

@RestController
@RequestMapping(value = "/merchants")
public class MerchantController implements IController<Merchant> {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    //@Autowired
    //UserService userService;  //Service which will do all data retrieval/manipulation work

    @Override
    @RequestMapping(value = "",
            params = {"limit", "offset"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Merchant>> getByLimit(@RequestParam("limit") int limit,
                                                     @RequestParam("offset") int offset) {

        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return null;
    }

    @RequestMapping(value = "",
            params = {"merchant_code", "merchant_type", "boss_certificate_number",
                    "settlement_account", "cashiers_mobile", "saler_code", "open_id",
                    "limit", "offset"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Merchant>> getBySelective(@RequestParam Map params) {
        //TODO 判断是否有limit和offset两个参数，如果没有的话自动赋10，0
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return null;
    }

    @Override
    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Merchant> getEntityById(@PathVariable("id") long id) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return null;
    }

    @RequestMapping(value = "/{id}/routes",
            params = {"limit", "offset"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Merchant> getRouteEntityByMerchantId(@PathVariable("id") long id,
                                                               @RequestParam("limit") int limit,
                                                               @RequestParam("offset") int offset) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return null;
    }

    @RequestMapping(value = "/{merchant_id}/routes/{route_id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Merchant> getRouteEntityByRouteId(@PathVariable("merchant_id") long merchant_id,
                                                            @PathVariable("route_id") long route_id) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return null;
    }

    @Override
    @RequestMapping(value = "",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createEntity(@RequestBody Merchant entity, UriComponentsBuilder ucBuilder) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        /**
        System.out.println("Creating User " + user.getName());

        if (userService.isUserExist(user)) {
            System.out.println("A User with name " + user.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        userService.saveUser(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
         */
        return null;
    }

    @Override
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Merchant> updateEntity(@PathVariable("id")long id, @RequestBody Merchant entity, UriComponentsBuilder ucBuilder) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        /*
        System.out.println("Updating User " + id);

        User currentUser = userService.findById(id);

        if (currentUser == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        currentUser.setName(user.getName());
        currentUser.setAge(user.getAge());
        currentUser.setSalary(user.getSalary());

        userService.updateUser(currentUser);
        return new ResponseEntity<User>(currentUser, HttpStatus.OK);

         */
        return null;
    }

    @Override
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PATCH,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Merchant> updateEntityBySelective(@PathVariable("id")long id, @RequestBody Merchant entity, UriComponentsBuilder
            ucBuilder) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        return null;
    }

    @Override
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Merchant> deleteEntity(@PathVariable("id") long id) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        /*
        System.out.println("Fetching & Deleting User with id " + id);

        User user = userService.findById(id);
        if (user == null) {
            System.out.println("Unable to delete. User with id " + id + " not found");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }

        userService.deleteUserById(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);

         */
        return null;
    }
}