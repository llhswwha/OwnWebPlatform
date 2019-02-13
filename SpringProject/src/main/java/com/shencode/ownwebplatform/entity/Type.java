package com.shencode.ownwebplatform.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

//设备类型
@Entity
@Table(name = "t_type")
public class Type extends  BaseEntity {

   // private  Integer id;  //设备类型ID

    @Column(name = "name",nullable = false)
    private String name;   //设备类型名称

    @Column(name = "iconname")
    private  String iconname; //图片名称

    @Column(name = "dllname")
    private String dllname;   //Dll名称

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIconname() {
        return iconname;
    }

    public void setIconname(String iconname) {
        this.iconname = iconname;
    }

    public String getDllname() {
        return dllname;
    }

    public void setDllname(String dllname) {
        this.dllname = dllname;
    }
}
