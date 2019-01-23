package com.shencode.ownwebplatform.entity;

import javax.persistence.*;
@Entity
@Table(name = "t_userrole")
public class UserRole {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private  Integer id;
    @Column(name = "loginname")
   private  String loginName;
    @Column(name = "roleid")
   private  Integer roleId;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getId() {
        return id;
    }

    public String getLoginName() {
        return loginName;
    }

    public Integer getRoleId() {
        return roleId;
    }
}
