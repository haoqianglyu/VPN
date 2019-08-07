package com.haoqiang.vpn.domain;

import javax.persistence.*;

import java.util.List;
import java.util.Objects;

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

//    @Column(name = "flag")
//    private String flag;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "region")
    private List<Server> servers;

    public List<Server> getServers() {
        return servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
    }

//    public String getFlag() {
//        return flag;
//    }
//
//    public void setFlag(String flag) {
//        this.flag = flag;
//    }

    public long getId() {
        return id;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", countryName='" + countryName + '\'' +
                //", servers=" + servers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Region region = (Region) o;
        return id == region.id ;
                //&& Objects.equals(countryName, region.countryName);
                //&& Objects.equals(servers, region.servers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, countryName, servers);
    }
}
