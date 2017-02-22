package com.github.cruiser.web;

import com.github.cruiser.entity.Merchant;
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
        Merchant merchant = new Merchant();
        merchant.setMerchantCode("W0100000");
        merchant.setMerchantName("商户a");
        merchant.setMerchantAddr("地址1");
        merchant.setMerchantType("餐饮");
        merchant.setBossName("张三");
        merchant.setBossMobile("13800000000");
        merchant.setBossCertificateType("01");
        merchant.setBossCertificateNumber("440104190001011000");
        merchant.setSettlementBank("人民银行广东省支行");
        merchant.setSettlementAccount("4001122341432432");
        merchant.setSettlementName("李四");
        merchant.setCashiersMobile("13800000001,13800000002");
        merchant.setHandlingCharge(new BigDecimal("0.01"));
        merchant.setSalerCode("1234566");
        merchant.setSalerReward(new BigDecimal("0.5"));
        merchant.setSalerRewardBegin(new Date());
        merchant.setIsEnable(true);
        merchant.setAttachmentUrl("/upload/pics/1111.png");
        merchant.setQrCode("http://wozhixixi.com/");
        merchant.setOpenId("ffdsakjfjalkjfdsaljfdksalj");
        merchant.setGmtCreate(new Date());
        merchant.setGmtModified(new Date());
        merchant.setModifiedPerson("王五");
        List<Merchant> list = new ArrayList<>();
        list.add(merchant);
        return ResponseEntity.ok(list);
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
        Merchant merchant = new Merchant();
        merchant.setMerchantCode("W0100000");
        merchant.setMerchantName("商户a");
        merchant.setMerchantAddr("地址1");
        merchant.setMerchantType("餐饮");
        merchant.setBossName("张三");
        merchant.setBossMobile("13800000000");
        merchant.setBossCertificateType("01");
        merchant.setBossCertificateNumber("440104190001011000");
        merchant.setSettlementBank("人民银行广东省支行");
        merchant.setSettlementAccount("4001122341432432");
        merchant.setSettlementName("李四");
        merchant.setCashiersMobile("13800000001,13800000002");
        merchant.setHandlingCharge(new BigDecimal("0.01"));
        merchant.setSalerCode("1234566");
        merchant.setSalerReward(new BigDecimal("0.5"));
        merchant.setSalerRewardBegin(new Date());
        merchant.setIsEnable(true);
        merchant.setAttachmentUrl("/upload/pics/1111.png");
        merchant.setQrCode("http://wozhixixi.com/");
        merchant.setOpenId("ffdsakjfjalkjfdsaljfdksalj");
        merchant.setGmtCreate(new Date());
        merchant.setGmtModified(new Date());
        merchant.setModifiedPerson("王五");
        List<Merchant> list = new ArrayList<>();
        list.add(merchant);
        return ResponseEntity.ok(list);
    }

    @Override
    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Merchant> getEntityById(@PathVariable("id") long id) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        Merchant merchant = new Merchant();
        merchant.setMerchantCode("W0100000");
        merchant.setMerchantName("商户a");
        merchant.setMerchantAddr("地址1");
        merchant.setMerchantType("餐饮");
        merchant.setBossName("张三");
        merchant.setBossMobile("13800000000");
        merchant.setBossCertificateType("01");
        merchant.setBossCertificateNumber("440104190001011000");
        merchant.setSettlementBank("人民银行广东省支行");
        merchant.setSettlementAccount("4001122341432432");
        merchant.setSettlementName("李四");
        merchant.setCashiersMobile("13800000001,13800000002");
        merchant.setHandlingCharge(new BigDecimal("0.01"));
        merchant.setSalerCode("1234566");
        merchant.setSalerReward(new BigDecimal("0.5"));
        merchant.setSalerRewardBegin(new Date());
        merchant.setIsEnable(true);
        merchant.setAttachmentUrl("/upload/pics/1111.png");
        merchant.setQrCode("http://wozhixixi.com/");
        merchant.setOpenId("ffdsakjfjalkjfdsaljfdksalj");
        merchant.setGmtCreate(new Date());
        merchant.setGmtModified(new Date());
        merchant.setModifiedPerson("王五");
        return ResponseEntity.ok(merchant);
    }

    @RequestMapping(value = "/{id}/routes",
            params = {"limit", "offset"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Route>> getRouteEntityByMerchantId(@PathVariable("id") long id,
                                                                  @RequestParam("limit") int limit,
                                                                  @RequestParam("offset") int offset) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        Route route = new Route();
        route.setRouteId(1L);
        route.setUpstreamId(2L);
        route.setMerchantId(3L);
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

    @RequestMapping(value = "/{merchant_id}/routes/{route_id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Route> getRouteEntityByRouteId(@PathVariable("merchant_id") long merchant_id,
                                                            @PathVariable("route_id") long route_id) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        Route route = new Route();
        route.setRouteId(1L);
        route.setUpstreamId(2L);
        route.setMerchantId(3L);
        route.setPriority(50);
        route.setGatewayType("W1");
        route.setQueryString("W017984721847218");
        route.setGmtCreate(new Date());
        route.setGmtModified(new Date());
        route.setModifiedPerson("王五");
        return ResponseEntity.ok(route);
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
        return new ResponseEntity<Void>(new HttpHeaders(), HttpStatus.CREATED);
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
        Merchant merchant = new Merchant();
        merchant.setMerchantCode("W0100000");
        merchant.setMerchantName("商户a");
        merchant.setMerchantAddr("地址1");
        merchant.setMerchantType("餐饮");
        merchant.setBossName("张三");
        merchant.setBossMobile("13800000000");
        merchant.setBossCertificateType("01");
        merchant.setBossCertificateNumber("440104190001011000");
        merchant.setSettlementBank("人民银行广东省支行");
        merchant.setSettlementAccount("4001122341432432");
        merchant.setSettlementName("李四");
        merchant.setCashiersMobile("13800000001,13800000002");
        merchant.setHandlingCharge(new BigDecimal("0.01"));
        merchant.setSalerCode("1234566");
        merchant.setSalerReward(new BigDecimal("0.5"));
        merchant.setSalerRewardBegin(new Date());
        merchant.setIsEnable(true);
        merchant.setAttachmentUrl("/upload/pics/1111.png");
        merchant.setQrCode("http://wozhixixi.com/");
        merchant.setOpenId("ffdsakjfjalkjfdsaljfdksalj");
        merchant.setGmtCreate(new Date());
        merchant.setGmtModified(new Date());
        return new ResponseEntity<Merchant>(merchant, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/{id}",
            method = RequestMethod.PATCH,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Merchant> updateEntityBySelective(@PathVariable("id")long id, @RequestBody Merchant entity, UriComponentsBuilder
            ucBuilder) {
        LOG.debug(Thread.currentThread().getStackTrace()[1].getMethodName());
        Merchant merchant = new Merchant();
        merchant.setMerchantCode("W0100000");
        merchant.setMerchantName("商户a");
        merchant.setMerchantAddr("地址1");
        merchant.setMerchantType("餐饮");
        merchant.setBossName("张三");
        merchant.setBossMobile("13800000000");
        merchant.setBossCertificateType("01");
        merchant.setBossCertificateNumber("440104190001011000");
        merchant.setSettlementBank("人民银行广东省支行");
        merchant.setSettlementAccount("4001122341432432");
        merchant.setSettlementName("李四");
        merchant.setCashiersMobile("13800000001,13800000002");
        merchant.setHandlingCharge(new BigDecimal("0.01"));
        merchant.setSalerCode("1234566");
        merchant.setSalerReward(new BigDecimal("0.5"));
        merchant.setSalerRewardBegin(new Date());
        merchant.setIsEnable(true);
        merchant.setAttachmentUrl("/upload/pics/1111.png");
        merchant.setQrCode("http://wozhixixi.com/");
        merchant.setOpenId("ffdsakjfjalkjfdsaljfdksalj");
        merchant.setGmtCreate(new Date());
        merchant.setGmtModified(new Date());
        return new ResponseEntity<Merchant>(merchant, HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteEntity(@PathVariable("id") long id) {
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
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}