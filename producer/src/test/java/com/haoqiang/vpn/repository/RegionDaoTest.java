package com.haoqiang.vpn.repository;

import com.haoqiang.vpn.config.AppConfig;
import com.haoqiang.vpn.domain.Region;
import com.haoqiang.vpn.domain.Server;
import com.haoqiang.vpn.service.RegionService;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * @author Haoqiang Lyu
 * @date 2019-06-11 11:32
 */
@ContextConfiguration(classes = {AppConfig.class})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("unit")
@WebAppConfiguration
@Transactional
public class RegionDaoTest {
    @Autowired
    private RegionService regionService;

    @Autowired
    private ServerDao serverDao;

    @Autowired
    private SessionFactory sessionFactory;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void findByIdTest(){
        Region expectedResult = new Region();
        expectedResult.setCountryName("United States");
        regionService.save(expectedResult);
        logger.debug("id is: " + expectedResult.getId());

        Region actualResult = regionService.findById(expectedResult.getId());
        logger.debug("actual id is: " + actualResult.getId());
        assertNotNull(expectedResult.getId());
        assertEquals(actualResult, expectedResult);
    }

    @Test
    @Transactional
    public void findByIdEagerTest(){
        Region expectedResult = new Region();
        expectedResult.setCountryName("China");
        regionService.save(expectedResult);


        Server server = new Server();
        server.setRegion(expectedResult);
        serverDao.save(server);

        Server server2 = new Server();
        server2.setRegion(expectedResult);
        serverDao.save(server2);

        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().refresh(expectedResult);

        assertNotNull(server.getId());
        Region actualResult = regionService.findByIdEager(expectedResult.getId());
        List<Server> servers = actualResult.getServers();
        assertEquals(servers.size(), 2);

    }


}
