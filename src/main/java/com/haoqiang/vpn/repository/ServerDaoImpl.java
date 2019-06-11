package com.haoqiang.vpn.repository;

import com.haoqiang.vpn.domain.Server;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Haoqiang Lyu
 * @date 2019-06-10 23:01
 */
@Repository
public class ServerDaoImpl implements ServerDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Server save(Server server) {
        Session session = sessionFactory.getCurrentSession();
        session.save(server);
        return server;
    }

    @Override
    public List<Server> findAll() {
        return null;
    }

    @Override
    public Server findByIdEager(Long id) {
        return null;
    }

    @Override
    public Server findById(Long id) {
        return null;
    }
}
