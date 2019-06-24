package com.haoqiang.vpn.repository;

import com.haoqiang.vpn.domain.User;

import java.util.List;

/**
 * @author Haoqiang Lyu
 * @date 2019-06-05 23:36
 */

public interface UserDao{

//    User save(User user);
//
//    List<User> saveUsers(List<User> users);
//
//    List<User> findAll();

    User findByIdEager(Long id);

//    User findById(Long id);

    List<User> findByUsernameIgnoreCase(String username);
    User findByUsername(String username);
}
