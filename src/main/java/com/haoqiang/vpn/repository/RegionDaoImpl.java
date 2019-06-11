package com.haoqiang.vpn.repository;

import com.haoqiang.vpn.domain.Region;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Haoqiang Lyu
 * @date 2019-06-10 23:02
 */

//T = region, ID = Long
@Repository
public class RegionDaoImpl implements CRUDDao<Region,Long>,RegionDao{

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    @Transactional(readOnly = true)
    public Region save(Region region) {
        Session session = sessionFactory.getCurrentSession();
        session.save(region);
        return region;
    }

    @Override
    @Transactional
    public List<Region> findAll() {
        String hql = "FROM Region";
        Session s = sessionFactory.getCurrentSession();
        TypedQuery<Region> query = s.createQuery(hql);
        return query.getResultList();
    }

    @Override
    public Region findByIdEager(Long id) {
        String hql = "FROM Region r LEFT JOIN FETCH r.servers where r.id = :regionId";
        Session s = sessionFactory.getCurrentSession();
        TypedQuery<Region> query = s.createQuery(hql);
        query.setParameter("regionId", id);
        return query.getSingleResult();
    }

    @Override
    public Region findById(Long id) {
        String hql = "FROM Region r where r.id = :regionId";
        Session s = sessionFactory.getCurrentSession();
        TypedQuery<Region> query = s.createQuery(hql);
        query.setParameter("regionId", id);
        return query.getSingleResult();
    }
}
