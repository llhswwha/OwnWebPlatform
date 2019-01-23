package com.shencode.ownwebplatform.entity;

import javax.persistence.*;


@Entity
@Table(name = "t_menurole")
public class MenuRole extends BaseEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id")
//    private  Integer id;

//    public void setId(Integer id) {
//        this.id = id;
//    }
//
//    public Integer getId() {
//        return id;
//    }

    @Column(name = "roleId")
    private  Integer roleId;
    @Column(name = "menuId")
    private  Integer menuId;

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public Integer getMenuId() {
        return menuId;
    }
}
