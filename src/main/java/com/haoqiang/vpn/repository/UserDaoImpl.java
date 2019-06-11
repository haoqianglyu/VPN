package com.haoqiang.vpn.repository;

import com.haoqiang.vpn.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Haoqiang Lyu
 * @date 2019-06-05 23:42
 */

@Repository // new UserDaoImpl()
public class UserDaoImpl implements UserDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public User save(User user) {
        Session session = sessionFactory.getCurrentSession();
        session.save(user);
        return user;

    }

    @Override
    @Transactional
    public List<User> findAll() {
        String hql = "FROM User";
        Session s = sessionFactory.getCurrentSession();
        TypedQuery<User> query = s.createQuery(hql);
        return query.getResultList();
    }

    @Override
    public User findByIdEager(Long id) {
        return null;
    }

    @Override
    public User findById(Long id) {
        String hql = "FROM User u where u.id = :userId";
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("userId",id);
        return query.getSingleResult();
    }
}
