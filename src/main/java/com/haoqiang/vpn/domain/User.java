package com.haoqiang.vpn.domain;


import javax.persistence.*;

/**
 * @author Haoqiang Lyu
 * @date 2019-05-29 11:03
 */

import static javax.persistence.GenerationType.SEQUENCE;

@Entity
@Table(name="users")

public class User {

    @Id
    @GeneratedValue(strategy = SEQUENCE , generator = "users_id_gen")
    @SequenceGenerator(name = "users_id_gen",sequenceName = "users_id_seq",allocationSize = 1)
    private Long id;

    @Column
    private String email;

    @Column(name = "last_name")

    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    public Long getId() {
        return id;
    }

    @Column(name = "username")
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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


}
