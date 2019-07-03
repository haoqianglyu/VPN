package com.haoqiang.vpn.extend.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Haoqiang Lyu
 * @date 2019-07-02 12:04
 */
@Component
public class JwtTokenUtil {

    private String secret = "shuaige";
    static final String CLAIM_KEY_USERNAME = "sub";
    static final String CLAIM_KEY_CREATED = "created";
    static final String CLAIM_KEY_AUDIENCE = "audience";

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private String generateToken(Map<String,Object> claims){
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(GenerateExpirationDate())
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
    }

    private Date GenerateExpirationDate(){
        return new Date(System.currentTimeMillis()+86400*1000);
    }

    public String generateToken(UserDetails userDetails){
        Map<String,Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME,userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED,new Date());
        return generateToken(claims);
    }

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
            logger.warn("unable to decrypt token",e);
        }
        return claims;
    }

    public String getUsernameFromToken(String token){
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
            logger.warn("get username from token fail!",e);
        }
        return username;
    }
}
