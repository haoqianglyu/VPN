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



import static junit.framework.TestCase.*;


/**
 * @author Haoqiang Lyu
 * @date 2019-06-06 00:46
 */

@ContextConfiguration(classes = {AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
@WebAppConfiguration

public class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Test
    @Transactional //after doing unit test, roll back
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

}
