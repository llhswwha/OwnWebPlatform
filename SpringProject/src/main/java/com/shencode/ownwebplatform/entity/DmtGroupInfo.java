package com.shencode.ownwebplatform.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

//监控设备组节点表
@Entity
@Table(name = "t_dmt_groupinfo")
public class DmtGroupinfo extends BaseEntity{

   // private  Integer id;         //监控组节点ID
    @Column(name = "name")
    private  String name;        //监控组节点名称
    @Column(name = "pid")
    private  Integer pid;        //上级组节点ID


    @Column(name = "")
    private  Integer devid;       //设备ID,只有一级组节点有设备



    @Column(name = "groupno")
    private  Integer groupno;     //组节点编号

    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    @JoinColumn(name = "pid")
    private  Set<DmtGroupinfo>  dmtGroupinfoSet;  //二级组节点

    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    //@JsonIgnore
    private Set<DmtNodeinfo> dmtNodeinfoSet;        //监控节点

    @Transient
    private List<Integer> nodeInfoidList;   //监控节点列表，组节点添加节点时用
    @Transient
    public List<Integer> getNodeInfoidList() {
        return nodeInfoidList;
    }
    @Transient
    public void setNodeInfoidList(List<Integer> nodeInfoidList) {
        this.nodeInfoidList = nodeInfoidList;
    }

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

    public Integer getDevid() {
        return devid;
    }

    public void setDevid(Integer devid) {
        this.devid = devid;
    }

    public Integer getGroupno() {
        return groupno;
    }

    public void setGroupno(Integer groupno) {
        this.groupno = groupno;
    }

    public Set<DmtGroupinfo> getDmtGroupinfoSet() {
        return dmtGroupinfoSet;
    }

    public void setDmtGroupinfoSet(Set<DmtGroupinfo> dmtGroupinfoSet) {
        this.dmtGroupinfoSet = dmtGroupinfoSet;
    }

    public Set<DmtNodeinfo> getDmtNodeinfoSet() {
        return dmtNodeinfoSet;
    }

    public void setDmtNodeinfoSet(Set<DmtNodeinfo> dmtNodeinfoSet) {
        this.dmtNodeinfoSet = dmtNodeinfoSet;
    }
}
