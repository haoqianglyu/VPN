package com.haoqiang.vpn.repository;

import com.haoqiang.vpn.config.AppConfig;
import com.haoqiang.vpn.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;


import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.*;


/**
 * @author Haoqiang Lyu
 * @date 2019-06-06 00:46
 */

@ContextConfiguration(classes = {AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
@WebAppConfiguration
@Transactional //after doing unit test, roll back
public class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void findByIdTest(){
        User expectedResult = new User();
        expectedResult.setEmail("haoqianglyu@gmail.com");
        expectedResult.setFirstName("haoqiang");
        expectedResult.setLastName("lyu");
        expectedResult.setUserName("Ares");
        userDao.save(expectedResult);
        User actualResult = userDao.findById(expectedResult.getId());
        assertNotNull(expectedResult.getId());
        assertEquals(actualResult,expectedResult);
    }

    @Test
    public void findByUsernameIgnoreCase(){
        List<User> expectedResult = new ArrayList<>();
        expectedResult.add(new User());
        expectedResult.get(0).setEmail("haoqianglyu@gmail.com");
        expectedResult.get(0).setFirstName("haoqiang");
        expectedResult.get(0).setLastName("lyu");
        expectedResult.get(0).setUserName("Ares");
        System.out.println("---------->expectedResult:"+expectedResult);
        userDao.saveUsers(expectedResult);
        List<User> actualResult = userDao.findByUsernameIgnoreCase(expectedResult.get(0).getUserName());
        System.out.println("---------->expectedResult:"+expectedResult);
        System.out.println("---------->actualResult:"+actualResult);
        assertEquals(actualResult,expectedResult);
    }

}
