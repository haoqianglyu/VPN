package com.haoqiang.vpn.repository;

import com.haoqiang.vpn.domain.Region;
import com.haoqiang.vpn.domain.User;

import java.util.List;

/**
 * @author Haoqiang Lyu
 * @date 2019-06-10 23:01
 */
public interface RegionDao {
    Region save(Region region);

    List<Region> findAll();

    Region findByIdEager(Long id);

    Region findById(Long id);
}
