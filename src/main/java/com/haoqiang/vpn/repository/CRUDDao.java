package com.haoqiang.vpn.repository;


import java.util.List;

/**
 * @author Haoqiang Lyu
 * @date 2019-06-10 23:43
 */
public interface CRUDDao<T,ID> {

    T save(T t);

    List<T> findAll();

    T findById(ID id);
}
