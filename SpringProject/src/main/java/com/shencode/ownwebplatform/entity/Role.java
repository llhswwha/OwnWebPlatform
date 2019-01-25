package com.shencode.ownwebplatform.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Set;



//角色表
@Entity
@Table(name = "t_role")
public class Role extends BaseEntity {


    /*@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;*/

    @Column(name = "name",nullable = false,length = 40)
    private String name; //角色名称
    @Column(name = "represent")
    private String represent;  //角色说明

    @ManyToMany
    @JoinTable(name = "t_menurole",joinColumns = {@JoinColumn(name = "roleId")},inverseJoinColumns = {@JoinColumn(name = "menuId")})
    private Set<Menu> menuSet;   //关联菜单

    @ManyToMany
    @JoinTable(name = "t_userrole",joinColumns = {@JoinColumn(name = "roleId")},inverseJoinColumns = {@JoinColumn(name = "userId")})
    //@JsonIgnore
    private  Set<User>  userSet;   //关联用户

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    //修改角色下用户(通常就是加减用户，很少用到)
    @Transient
    private List<Integer> userIdeList;
    @Transient
    public List<Integer> getUserIdeList() {
        return userIdeList;
    }
    @Transient
    public void setUserIdeList(List<Integer> userIdeList) {
        this.userIdeList = userIdeList;
    }

    //修改该角色下菜单时用
    @Transient
    private List<Integer> menuIdList;
    @Transient
    public List<Integer> getMenuIdList() {
        return menuIdList;
    }
    @Transient
    public void setMenuIdList(List<Integer> menuIdList) {
        this.menuIdList = menuIdList;
    }

    public void setMenuSet(Set<Menu> menuSet) {
        this.menuSet = menuSet;
    }

    public Set<Menu> getMenuSet() {
        return menuSet;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setRepresent(String represent) {
        this.represent = represent;
    }



    public String getName() {
        return name;
    }

    public String getRepresent() {
        return represent;
    }


}
