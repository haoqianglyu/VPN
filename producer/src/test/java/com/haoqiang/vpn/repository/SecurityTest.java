package com.haoqiang.vpn.repository;

import com.haoqiang.vpn.config.AppConfig;
import com.haoqiang.vpn.domain.Authority;
import com.haoqiang.vpn.domain.AuthorityRole;
import com.haoqiang.vpn.domain.User;
import com.haoqiang.vpn.extend.security.UserDetailsServiceImpl;
import com.haoqiang.vpn.service.UserService;
import org.hibernate.SessionFactory;
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

import java.util.List;

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
    private SessionFactory sessionFactory;

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

        User user = userService.createUser(expectedResult);
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().refresh(expectedResult);

        //UserDetails actualResult_email = userDetailsServiceImpl.loadUserByUsername(expectedResult.getEmail());
        //UserDetails actualResult_username = userDetailsServiceImpl.loadUserByUsername(expectedResult.getUsername());
//        logger.debug("----->1"+actualResult_email);
//        logger.debug("----->2"+actualResult_username);
//        logger.debug("----->3"+userDetailsServiceImpl.loadUserByUsername(expectedResult.getEmail()));
        //assertEquals(actualResult_email,expectedResult);
        //assertEquals(actualResult_username,expectedResult);

        List<Authority> tmpList = (List<Authority>) user.getAuthorities();
        assertEquals(tmpList.get(0).getAuthority(), AuthorityRole.ROLE_REGISTERED_USER);
    }
}
