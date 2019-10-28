package com.haoqiang.vpn.domain;

import javax.persistence.*;

import java.util.Objects;

import static javax.persistence.GenerationType.SEQUENCE;

/**
 * @author Haoqiang Lyu
 * @date 2019-05-31 19:35
 */


@Entity
@Table(name="servers")
public class Server {
    @Id //primary key
    @GeneratedValue(strategy = SEQUENCE , generator = "server_id_gen")
    @SequenceGenerator(name = "server_id_gen",sequenceName = "server_id_seq",allocationSize = 1)
    private Long id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn( name = "region_id")
    private Region region;

    public Long getId() {
        return id;
    }

    public Region getRegion() {
        return region;
    }


    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Server server = (Server) o;
        return Objects.equals(id, server.id);
        //&& Objects.equals(region, server.region);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, region);
    }
}
