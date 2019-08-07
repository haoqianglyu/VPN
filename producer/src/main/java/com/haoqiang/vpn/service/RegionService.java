package com.haoqiang.vpn.service;

import com.haoqiang.vpn.domain.Region;
import com.haoqiang.vpn.repository.RegionDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Haoqiang Lyu
 * @date 2019-06-26 15:39
 */

@Service
@Transactional
public class RegionService {

    @Autowired
    private RegionDaoImpl regionDao;

    public Region save(Region region){
        return regionDao.save(region);
    }

    public List<Region> findAll(){
        List<Region> regions = regionDao.findAll();
        return regions;
    }

    public Region findById(Long id){
        Region region = regionDao.findById(id);
        return region;
    }

    public Region findByIdEager(Long id){
        Region region = regionDao.findByIdEager(id);
        return region;
    }
}
