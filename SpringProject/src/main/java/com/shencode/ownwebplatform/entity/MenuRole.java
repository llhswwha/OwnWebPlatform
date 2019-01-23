package com.shencode.ownwebplatform.entity;

import javax.persistence.*;


@Entity
@Table(name = "t_menurole")
public class MenuRole implements BaseEntity<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Integer id;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    @Column(name = "roleid")
    private  Integer roleid;
    @Column(name = "menuid")
    private  Integer menuid;

    public void setRoleid(Integer roleid) {
        this.roleid = roleid;
    }

    public void setMenuid(Integer menuid) {
        this.menuid = menuid;
    }

    public Integer getRoleid() {
        return roleid;
    }

    public Integer getMenuid() {
        return menuid;
    }
}
