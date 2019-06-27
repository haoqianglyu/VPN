package com.haoqiang.vpn.repository;


import com.haoqiang.vpn.domain.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Haoqiang Lyu
 * @date 2019-06-26 16:24
 */

@Repository
@Transactional
public class AuthorityDaoImpl extends CRUDDaoImpl<Authority,Long> implements AuthorityDao{

    @Autowired
    @Override
    public void setHQLEntityClazz() {
        this.hQLEntityClazz = Authority.class;
    }
}
