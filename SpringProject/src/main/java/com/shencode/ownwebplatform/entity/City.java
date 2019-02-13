package com.shencode.ownwebplatform.entity;

import javax.persistence.*;

@Entity
@Table(name="t_City")
public class City extends BaseEntity {
    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;*/
    @Column(name = "pid")
    private Integer pid;
    @Column(name = "name",nullable = false)
    private String name;

   /* public void setId(Integer id) {
        this.id = id;
    }*/



    public void setName(String name) {
        this.name = name;
    }

//    public Integer getId() {
//        return id;
//    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getPid() {
        return pid;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "City{" +
                "pid=" + pid +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
