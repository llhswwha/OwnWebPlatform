package com.shencode.ownwebplatform.entity;

import javax.persistence.*;
@Entity
@Table(name = "t_userrole")
public class UserRole extends BaseEntity {
   /*@Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private  Integer id;*/
    @Column(name = "userId")
   private  Integer userId;
    @Column(name = "roleId")
   private  Integer roleId;

//    public void setId(Integer id) {
//        this.id = id;
//    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

//    public Integer getId() {
//        return id;
//    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getRoleId() {
        return roleId;
    }
}
