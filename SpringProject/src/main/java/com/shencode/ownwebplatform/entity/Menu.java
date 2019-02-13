package com.shencode.ownwebplatform.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

//菜单表
@Entity
@Table(name = "t_menu")
public class Menu extends BaseEntity {

    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "code")
    private String code;
    @Column(name = "showOrder")
    private Integer showOrder;
    @Column(name = "pid")
    private Integer pid;
    @Column(name = "description")
    private String description;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "t_menuRole"                          //中间表
            , joinColumns = {@JoinColumn(name = "menuId")}     //与本表关联字段
            , inverseJoinColumns = {@JoinColumn(name = "roleId")})    //与角色表关联字段
    @JsonIgnore
    private Set<Role> roleSet;

    @Transient
    private List<Integer> roleIdList;

    @Transient
    private List<Menu> items;

    public Menu(){

    }

    public Menu(String name,String code,Integer pid){
        this.name=name;
        this.code=code;
        this.pid=pid;
    }

    @Transient
    public void setRoleIdList(List<Integer> roleIdList) {
        this.roleIdList = roleIdList;
    }

    @Transient
    public List<Integer> getRoleIdList() {
        return roleIdList;
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

    public void setShowOrder(Integer showOrder) {
        this.showOrder = showOrder;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Integer getShowOrder() {
        return showOrder;
    }

    public Integer getPid() {
        return pid;
    }

    public String getDescription() {
        return description;
    }

    public List<Menu> getItems() {
        return items;
    }

    public void setItems(List<Menu> items) {
        this.items = items;
    }

    public void addItem(Menu menu){
        if(items==null){
            items=new ArrayList<>();
        }
        items.add(menu);
    }
}
