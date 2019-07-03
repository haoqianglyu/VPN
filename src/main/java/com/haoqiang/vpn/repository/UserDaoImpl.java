package com.haoqiang.vpn.repository;

import com.haoqiang.vpn.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Haoqiang Lyu
 * @date 2019-06-05 23:42
 */

@Repository // new UserDaoImpl()
@Transactional
//B=User, IB=Long
public class UserDaoImpl extends CRUDDaoImpl<User,Long> implements UserDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findByIdEager(Long id) {
        String hql = "FROM User u LEFT JOIN FETCH u.authorities where u.id = :userId";
        Session s = sessionFactory.getCurrentSession();
        TypedQuery<User> query = s.createQuery(hql);
        query.setParameter("userId", id);
        return query.getSingleResult();
    }


    @Override
    public List<User> findByUsernameIgnoreCase(String username) {
        String hql = "FROM User u where lower(u.userName) = :username2";
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("username2",username.toLowerCase());
        if(query.getResultList().size()==0 ){
            return null;
        }else{
            return query.getResultList();
        }

    }

    @Override
    public User findByEmailIgnoreCase(String email) {
        String hql = "FROM User u LEFT JOIN FETCH u.authorities where lower(u.email) = :email2";
        Query<User> query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("email2",email.toLowerCase());
        //null
        return query.uniqueResult();
    }

    @Override
    public User findByUsernameIgnoreC(String username) {
        String hql = "FROM User u LEFT JOIN FETCH u.authorities where lower(u.username) = :username2";
        Query<User> query = sessionFactory.getCurrentSession().createQuery(hql).setParameter("username2",username.toLowerCase());
        return query.uniqueResult();
    }

    @Override
    @Autowired
    public void setHQLEntityClazz() {
        this.hQLEntityClazz = User.class;
    }
}
