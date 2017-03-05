package com.github.cruiser.dao;


import com.github.cruiser.entity.Merchant;
import com.github.cruiser.entity.MerchantExample;
import org.junit.Assert;
import org.junit.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

public class MerchantDaoTest extends AbstractDaoTest {

    private final Logger LOG = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MerchantDao dao;

    private Merchant entity;
    @Before
    public void initEntity(){
        entity = new Merchant();
        entity.setMerchantCode("W0144005331999999");
        entity.setMerchantName("广东省内经营杂货铺的顺序号为1的商户");
        entity.setMerchantAddr("地址很长地址很长地址很长地址很长地址很长地址很长地址很长地址很长地址很长地址很长地址很长地址很长地址很长地址很长地址很长地址很长地址很长地址很长地址很长地址很长地址很长地址很长地址很长地址很长地址很长");
        entity.setMerchantType("百货商店");
        entity.setBossName("张三");
        entity.setBossMobile("13500000000");
        entity.setBossCertificateType("01");
        entity.setBossCertificateNumber("440104190001011011");
        entity.setSettlementBank("中国建设银行浙江省嘉兴分行");
        entity.setSettlementAccount("6217001430005271533");
        entity.setSettlementName("小彬");
        entity.setHandlingCharge(new BigDecimal("0.025"));
        entity.setSalerCode("S000000000");
        entity.setSalerReward(new BigDecimal(".5"));
        entity.setSalerRewardBegin(new Date());
        entity.setIsEnable(true);
        entity.setAttachmentUrl("http://163.com");
        entity.setQrCode("http://wozhixixi.com/route_app/merchant_code/W0144005331000001");
        entity.setGmtCreate(new Date());
        entity.setGmtModified(new Date());
        entity.setModifiedPerson("admin");

    }

    @Override
    public void testInsertEntity() {
        int cnt = dao.countByExample(new MerchantExample());
        Assert.assertEquals(1, dao.insert(entity));
        Assert.assertEquals(cnt + 1, dao.countByExample(new MerchantExample()));

    }

    @Override
    public void testUpdateEntity() {
        Assert.assertEquals(1, dao.insert(entity));
        //需要直接指定date对象，不然时间值会一直变
        Date date = new Date(1000000L);
        LOG.debug("**date: "+date);
        entity.setGmtModified(date);
        dao.updateByPrimaryKey(entity);
        Merchant updatedEntity = dao.selectByPrimaryKey(entity.getMerchantId());
        Assert.assertEquals(date, updatedEntity.getGmtModified());
    }

    @Override
    public void testUpdateEntityBySelective() {

    }

    @Override
    public void testDeleteEntity() {
        int cnt = dao.countByExample(new MerchantExample());
        Assert.assertEquals(1, dao.insert(entity));
        Assert.assertEquals(cnt + 1, dao.countByExample(new MerchantExample()));
        Assert.assertNotNull(entity.getMerchantId());
        Assert.assertEquals(1, dao.deleteByPrimaryKey(entity.getMerchantId()));
        Assert.assertEquals(cnt, dao.countByExample(new MerchantExample()));

    }
}
