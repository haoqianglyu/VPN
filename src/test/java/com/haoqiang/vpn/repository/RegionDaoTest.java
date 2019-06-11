package com.haoqiang.vpn.repository;

import com.haoqiang.vpn.config.AppConfig;
import com.haoqiang.vpn.domain.Region;
import com.haoqiang.vpn.domain.Server;
import com.haoqiang.vpn.domain.User;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

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

public class RegionDaoTest {
    @Autowired
    private RegionDao regionDao;

    @Autowired
    private ServerDao serverDao;

    @Autowired
    private SessionFactory sessionFactory;

    @Test
    @Transactional //after doing unit test, roll back
    public void findByIdTest(){
        Region expectedResult = new Region();
        expectedResult.setCountryName("China");
        regionDao.save(expectedResult);
        Region actualResult = regionDao.findById(expectedResult.getId());
        assertNotNull(expectedResult.getId());
        assertEquals(actualResult,expectedResult);
    }

    @Test
    @Transactional
    public void findByIdEagerTest(){
        Region expectedResult = new Region();
        expectedResult.setCountryName("China");
        regionDao.save(expectedResult);

        Server server = new Server();
        server.setRegion(expectedResult);
        serverDao.save(server);

        assertNotNull(server.getId());
        sessionFactory.getCurrentSession().flush();
        sessionFactory.getCurrentSession().refresh(expectedResult);
        Region actualResult = regionDao.findByIdEager(expectedResult.getId());
        List servers = actualResult.getServers();
        assertEquals(servers.size(), 1);

    }
}
