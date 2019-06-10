package com.haoqiang.vpn.domain;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * @author Haoqiang Lyu
 * @date 2019-05-31 19:35
 */


@Entity
@Table(name="servers")
public class Server {
    @Id
    @GeneratedValue(strategy = SEQUENCE , generator = "server_id_gen")
    @SequenceGenerator(name = "server_id_gen",sequenceName = "server_id_seq",allocationSize = 1)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "region_id")
    private Region region;

    public Region getRegion() {
        return region;
    }


    public void setRegion(Region region) {
        this.region = region;
    }
}
