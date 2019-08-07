package com.haoqiang.vpn.domain;


import org.springframework.security.core.GrantedAuthority;


import javax.persistence.*;
import java.io.Serializable;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * @author Haoqiang Lyu
 * @date 2019-06-26 11:37
 */

@Entity
@Table(name="authorities")
public class Authority implements GrantedAuthority,Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = SEQUENCE , generator = "authority_id_gen")
    @SequenceGenerator(name = "authority_id_gen",sequenceName = "authority_id_seq",allocationSize = 1)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "user_id")
    private User user;

    @Column(name = "role")
    private String role;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public Long getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return role;
    }
}
