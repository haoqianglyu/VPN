package com.haoqiang.vpn.repository;

import com.haoqiang.vpn.domain.Region;
import com.haoqiang.vpn.domain.Server;

import java.util.List;

/**
 * @author Haoqiang Lyu
 * @date 2019-06-10 23:01
 */
public interface ServerDao {
    Server save(Server server);

    List<Server> findAll();

    Server findByIdEager(Long id);

    Server findById(Long id);

}
