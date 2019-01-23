package com.shencode.ownwebplatform.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

//菜单表
@Entity
@Table(name = "t_menu")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Integer   id;
    @Column(name = "name")
    private String name;
    @Column(name = "code")
    private String code;
    @Column(name = "showorder")
    private String showOrder;
    @Column(name = "pid")
    private String pid;
    @Column(name = "represent")
    private String  represent;



    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name = "t_menurole"                          //中间表
            ,joinColumns ={@JoinColumn(name = "menuid")}     //与本表关联字段
            ,inverseJoinColumns = {@JoinColumn(name="roleid")})    //与角色表关联字段
    @JsonIgnore
    private Set<Role>  roleSet;


    public void setId(Integer id) {
        this.id = id;
    }

    public void setRoleSet(Set<Role> roleSet) {
        this.roleSet = roleSet;
    }

    public Set<Role> getRoleSet() {
        return roleSet;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setShowOrder(String showOrder) {
        this.showOrder = showOrder;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public void setRepresent(String represent) {
        this.represent = represent;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getShowOrder() {
        return showOrder;
    }

    public String getPid() {
        return pid;
    }

    public String getRepresent() {
        return represent;
    }
}
