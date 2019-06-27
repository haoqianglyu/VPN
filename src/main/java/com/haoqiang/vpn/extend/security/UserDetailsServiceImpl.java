package com.haoqiang.vpn.extend.security;

import com.haoqiang.vpn.domain.Authority;
import com.haoqiang.vpn.domain.User;
import com.haoqiang.vpn.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Haoqiang Lyu
 * @date 2019-06-24 11:43
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public UserDetails loadUserByUsername(String emailorUsername) throws UsernameNotFoundException {
        User domainUser = null;
        try {
            domainUser = userService.findByEmailorUsername(emailorUsername);
        } catch (Exception repositoryProblem) {
            logger.debug("catch AuthenticationServiceException from Authentication Provider");
            throw new UsernameNotFoundException("can't find username in database:"+domainUser.getUsername());
        }
        if(domainUser == null){
            throw new BadCredentialsException("AbstractUserDetailsAuthenticationProvider.UsernameNotFound");
        }
//        User newuser = userService.findByIdEager(domainUser.getId());
//        domainUser.setAuthorities((List<Authority>) newuser.getAuthorities());

        return domainUser;

    }
}
