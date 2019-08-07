package com.haoqiang.vpn.extend.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Haoqiang Lyu
 * @date 2019-07-03 10:50
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private JwtTokenUtil jwtTokenUtil;


    @Autowired
    UserDetailsServiceImpl userDetailsService;

    private String tokenHeader = "TokenAuthorization";
    private String bearer = "Bearer";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterchain) throws ServletException, IOException {
        //TODO 1.extract token
        String tokenHeader = request.getHeader(this.tokenHeader);
        if(tokenHeader!=null && tokenHeader.startsWith(bearer)){
            String authToken = tokenHeader.substring(7);

            //TODO 2.verify token
            String username = jwtTokenUtil.getUsernameFromToken(authToken);

            //TODO 3.verify token payload whether it is legit username in database
            //my own logic
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken fullyAuthenticated = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(fullyAuthenticated);


        }
        filterchain.doFilter(request,response);
    }
}
