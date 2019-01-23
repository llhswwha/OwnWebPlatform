package com.shencode.ownwebplatform.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;

import javax.persistence.*;
import java.util.Set;



//角色表
@Entity
@Table(name = "t_role")
public class Role implements BaseEntity<Integer> {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "represent")
    private String represent;

    @ManyToMany
    @JoinTable(name = "t_menurole",joinColumns = {@JoinColumn(name = "roleid")},inverseJoinColumns = {@JoinColumn(name = "menuid")})
    private Set<Menu> menuSet;

    @ManyToMany
    @JoinTable(name = "t_userrole",joinColumns = {@JoinColumn(name = "roleid")},inverseJoinColumns = {@JoinColumn(name = "loginName")})
    //@JsonIgnore
    private  Set<User>  userSet;



    public void setMenuSet(Set<Menu> menuSet) {
        this.menuSet = menuSet;
    }

    public Set<Menu> getMenuSet() {
        return menuSet;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getRepresent() {
        return represent;
    }


}
