package com.github.cruiser.dao;


import com.github.cruiser.entity.AbstractEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml",
        "classpath:spring/spring-redis.xml"})
@Transactional
@Rollback
public abstract class AbstractDaoTest {


    @Test
    public abstract void testInsertEntity();


    @Test
    public abstract void testUpdateEntity();


    @Test
    public abstract void testUpdateEntityBySelective();

    @Test
    public abstract void testDeleteEntity();
}
