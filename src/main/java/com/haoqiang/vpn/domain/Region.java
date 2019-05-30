package com.haoqiang.vpn.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * @author Haoqiang Lyu
 * @date 2019-05-29 21:46
 */

@Entity
@Table(name="regions")
public class Region {


    @Id
    @GeneratedValue(strategy = SEQUENCE , generator = "regions_id_gen")
    @SequenceGenerator(name = "regions_id_gen",sequenceName = "regions_id_seq",allocationSize = 1)
    private long id;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "server_name")
    private String serverName;


    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
}
