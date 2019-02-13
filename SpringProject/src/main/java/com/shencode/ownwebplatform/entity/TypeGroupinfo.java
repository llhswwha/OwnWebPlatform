package com.shencode.ownwebplatform.entity;

import javax.persistence.*;
import java.util.Set;

//设备类型监控组
@Entity
@Table(name = "t_type_groupinfo")
public class TypeGroupinfo extends  BaseEntity{

   // private  Integer id;             //监控组节点id
    @Column(name = "name")
    private  String name;            //监控组节点名称
    @Column(name = "pid")
    private  Integer pid;            //上级组节点id

    @Transient
    private  Integer typeid;         //设备类型id
    @Transient
    public Integer getTypeid() {
        return typeid;
    }
    @Transient
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    @ManyToOne
    @JoinColumn(name = "typeid")
    private  Type  type;             //设备类型id
    @Column(name = "groupno")
    private  Integer groupno;        //组节点编号

    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    @JoinColumn(name = "pid")
    private Set<TypeGroupinfo>  typeGroupinfoSet;    //二级组节点

    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Set<TypeNodeinfo>  typeNodeinfoSet;    //监控节点

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getGroupno() {
        return groupno;
    }

    public void setGroupno(Integer groupno) {
        this.groupno = groupno;
    }

    public Set<TypeGroupinfo> getTypeGroupinfoSet() {
        return typeGroupinfoSet;
    }

    public void setTypeGroupinfoSet(Set<TypeGroupinfo> typeGroupinfoSet) {
        this.typeGroupinfoSet = typeGroupinfoSet;
    }

    public Set<TypeNodeinfo> getTypeNodeinfoSet() {
        return typeNodeinfoSet;
    }

    public void setTypeNodeinfoSet(Set<TypeNodeinfo> typeNodeinfoSet) {
        this.typeNodeinfoSet = typeNodeinfoSet;
    }
}
