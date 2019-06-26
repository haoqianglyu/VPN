package com.haoqiang.vpn.repository;

import com.haoqiang.vpn.config.AppConfig;
import com.haoqiang.vpn.domain.User;
import com.haoqiang.vpn.extend.security.UserDetailsServiceImpl;
import com.haoqiang.vpn.extend.security.exception.NotFoundException;
import com.haoqiang.vpn.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Test
    public void findByIdTest(){
        User expectedResult = new User();
        expectedResult.setEmail("haoqianglyu@gmail.com");
        expectedResult.setFirstName("haoqiang");
        expectedResult.setLastName("lyu");
        expectedResult.setUsername("Ares");
        userService.save(expectedResult);
        User actualResult = userService.findById(expectedResult.getId());
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
        expectedResult.get(0).setUsername("Ares");
        logger.debug("---------->expectedResult:"+expectedResult);
        userService.saveUsers(expectedResult);
        List<User> actualResult = userService.findByUsernameIgnoreCase(expectedResult.get(0).getUsername());
        logger.debug("---------->expectedResult:"+expectedResult);
        logger.debug("---------->actualResult:"+actualResult);
        assertEquals(actualResult,expectedResult);
    }

    @Test
    public void findByEmailorUsername() throws NotFoundException {
        User expectedResult = new User();
        expectedResult.setEmail("chenbo@gmail.com");
        expectedResult.setFirstName("chen");
        expectedResult.setLastName("bo");
        expectedResult.setUsername("cb");
        expectedResult.setPassword("549831");
        userService.save(expectedResult);
        User actualResult_email = userService.findByEmailorUsername(expectedResult.getEmail());
        User actualResult_username = userService.findByEmailorUsername(expectedResult.getUsername());
        assertEquals(actualResult_email,expectedResult);
        assertEquals(actualResult_username,expectedResult);
    }

    @Test
    public void createUserTest(){
        User expectedResult = new User();

        expectedResult.setUsername("cb");
        expectedResult.setPassword("549831");
        User u = userService.createUser(expectedResult);
        //assertEquals(u.getPassword(),"549831");
    }
}
