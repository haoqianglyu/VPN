package com.haoqiang.vpn.service;

import com.haoqiang.vpn.domain.Authority;
import com.haoqiang.vpn.domain.AuthorityRole;
import com.haoqiang.vpn.domain.User;
import com.haoqiang.vpn.extend.security.exception.NotFoundException;
import com.haoqiang.vpn.repository.AuthorityDao;
import com.haoqiang.vpn.repository.AuthorityDaoImpl;
import com.haoqiang.vpn.repository.UserDaoImpl;
import com.sun.xml.internal.ws.api.policy.AlternativeSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Haoqiang Lyu
 * @date 2019-06-19 13:20
 */

@Service
@Transactional
public class UserService {

    @Autowired
    private UserDaoImpl userDao;

    @Autowired
    private AuthorityDaoImpl authorityDao;

    public User save(User user){
        //TODO add logic
        return userDao.save(user);
    }

    public List<User> saveUsers(List<User> users){
        return userDao.saveAll(users);
    }

    public List<User> findAll(){
        List<User> users = userDao.findAll();
        return users;
    }

    public User findById(Long id){
        User user = userDao.findById(id);
        return user;
    }

    public User findByIdEager(Long id){
        User user = userDao.findByIdEager(id);
        return user;
    }

    public List<User> findByUsernameIgnoreCase(String username){
        List<User> users = userDao.findByUsernameIgnoreCase(username);
        return users;
    }


    public User findByEmailorUsername(String keyword) throws NotFoundException,NullPointerException {
        if (keyword == null || "".equals(keyword.trim())){
            throw new NullPointerException("search keyword is null");
        }
        User user = userDao.findByEmailIgnoreCase(keyword);
        if(user == null){
            user = userDao.findByUsernameIgnoreC(keyword);
        }
        if(user == null){
            throw new NotFoundException();
        }
        return user;
    }

    public User createUser(User newUser){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String originalPassword = newUser.getPassword();
        String encodedPass = encoder.encode(originalPassword);
        newUser.setPassword(encodedPass);
        userDao.save(newUser);
        addAuthority(newUser, AuthorityRole.ROLE_REGISTERED_USER);

        return newUser;
    }

    public Authority addAuthority(User user, String role){
        Authority authority = new Authority();
        authority.setRole(role);
        authority.setUser(user);
        authorityDao.save(authority);
        Authority auth = authorityDao.findById(authority.getId());
        return authority;
    }



}
