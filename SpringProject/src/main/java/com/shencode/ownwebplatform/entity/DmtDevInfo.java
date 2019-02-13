package com.shencode.ownwebplatform.entity;

import javax.persistence.*;

import java.util.List;
import java.util.Set;

//监控设备表
@Entity
@Table(name = "t_dmt_devinfo")
public class DmtDevinfo  extends  BaseEntity {

   // private  Integer id;           //设备ID
    @Column(name = "name")
    private  String name;          //设备名称
    @ManyToOne
    @JoinColumn(name = "typeid")//设备类型ID
    private  Type type;

    @Transient
    private  Integer typeid;
    @Transient
    public Integer getTypeid() {
        return typeid;
    }
    @Transient
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    @Column(name = "nflag")
    private  Integer nflag;        //采集方式
    @Column(name = "collecttime")
    private  Integer collecttime;   //采集周期
    @Column(name = "collectparameter")
    private  String  collectparameter;  //采集方式

    @OneToMany(cascade = {CascadeType.ALL},fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Set<DmtGroupinfo>  dmtGroupinfoSet;  //监控组节点

    @Transient
    private List<Integer> dmtGroupIdList;
    @Transient
    public List<Integer> getDmtGroupIdList() {
        return dmtGroupIdList;
    }
    @Transient
    public void setDmtGroupIdList(List<Integer> dmtGroupIdList) {
        this.dmtGroupIdList = dmtGroupIdList;
    }

    @Column(name = "ollectsvrip")
    private  String ollectsvrip;            //采集服务器IP

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getNflag() {
        return nflag;
    }

    public void setNflag(Integer nflag) {
        this.nflag = nflag;
    }

    public Integer getCollecttime() {
        return collecttime;
    }

    public void setCollecttime(Integer collecttime) {
        this.collecttime = collecttime;
    }

    public String getCollectparameter() {
        return collectparameter;
    }

    public void setCollectparameter(String collectparameter) {
        this.collectparameter = collectparameter;
    }

    public Set<DmtGroupinfo> getDmtGroupinfoSet() {
        return dmtGroupinfoSet;
    }

    public void setDmtGroupinfoSet(Set<DmtGroupinfo> dmtGroupinfoSet) {
        this.dmtGroupinfoSet = dmtGroupinfoSet;
    }

    public String getOllectsvrip() {
        return ollectsvrip;
    }

    public void setOllectsvrip(String ollectsvrip) {
        this.ollectsvrip = ollectsvrip;
    }
}
