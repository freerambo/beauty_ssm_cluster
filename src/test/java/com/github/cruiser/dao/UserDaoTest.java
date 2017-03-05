package com.github.cruiser.dao;


import java.util.List;


import com.github.cruiser.entity.User;
import com.github.cruiser.entity.UserExample;
import org.apache.ibatis.session.RowBounds;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author yingjun
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-dao.xml")
@Deprecated
public class UserDaoTest {

    @Autowired
    private UserDao userDao;
	
	//@Test
	public void testQueryById() {
		User user=userDao.queryByPhone(18768128888L);
		System.out.println(user);
		System.out.println("--------------------------");
	}

	//@Test
	public void testQueryAll() {
		List<User> list=userDao.queryAll(0, 100);
		for (User user : list) {
			System.out.println(user);
		}
	}

	//@Test
	public void testAddScore() {
		userDao.addScore(10);
		List<User> list=userDao.queryAll(0, 100);
		for (User user : list) {
			System.out.println(user);
		}
	}

	@Test
	public void test() {
		RowBounds rowBounds = new RowBounds(0,10);
		UserExample userExample = new UserExample();
		List<User> list=userDao.selectByExampleWithRowbounds(userExample, rowBounds);
		for (User user : list) {
			System.out.println(user);
		}
	}

}
