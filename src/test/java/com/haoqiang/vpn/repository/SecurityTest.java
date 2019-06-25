package com.haoqiang.vpn.repository;

import com.haoqiang.vpn.config.AppConfig;
import com.haoqiang.vpn.domain.User;
import com.haoqiang.vpn.extend.security.UserDetailsServiceImpl;
import com.haoqiang.vpn.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import static junit.framework.TestCase.assertEquals;

/**
 * @author Haoqiang Lyu
 * @date 2019-06-25 10:07
 */

@ContextConfiguration(classes = {AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
@WebAppConfiguration
@Transactional //after doing unit test, roll back

public class SecurityTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Test
    public void loadUserByUsernameTest(){

        User expectedResult = new User();
        expectedResult.setEmail("chenbo@gmail.com");
        expectedResult.setFirstName("chen");
        expectedResult.setLastName("bo");
        expectedResult.setUsername("cb");
        expectedResult.setPassword("549831");

        userService.save(expectedResult);

        UserDetails actualResult_email = userDetailsServiceImpl.loadUserByUsername(expectedResult.getEmail());
        UserDetails actualResult_username = userDetailsServiceImpl.loadUserByUsername(expectedResult.getUsername());

        assertEquals(actualResult_email,expectedResult);
        assertEquals(actualResult_username,expectedResult);
    }
}
