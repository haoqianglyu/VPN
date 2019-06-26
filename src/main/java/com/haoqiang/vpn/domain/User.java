package com.haoqiang.vpn.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * @author Haoqiang Lyu
 * @date 2019-05-29 11:03
 */

@Entity
@Table(name="users")

public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = SEQUENCE , generator = "users_id_gen")
    @SequenceGenerator(name = "users_id_gen",sequenceName = "users_id_seq",allocationSize = 1)
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "last_name")

    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    public Long getId() {
        return id;
    }

    @Column(name = "username")
    private String username;

    //default false
    @JsonIgnore
    @Column(name = "expired")
    private Boolean expired = false;

    //default false
    @JsonIgnore
    @Column(name = "locked")
    private Boolean locked = false;

    //default true
    @JsonIgnore
    @Column(name = "enable")
    private Boolean enable = true;

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    //role
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return enable;
    }
}
