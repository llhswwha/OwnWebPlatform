package com.shencode.ownwebplatform.entity;

import com.shencode.ownwebplatform.entity.enumClass.AlarmLevel;

import javax.persistence.*;

//实时告警表
@Entity
@Table(name = "t_alarm")
public class Alarm  extends BaseEntity {

    //private  Integer id;                   //告警id
    @Column(name = "nodeid")
    private  Integer nodeId;               //节点id
    @Column(name = "level")
    private AlarmLevel level;              //告警等级
    @Column(name = "text")
    private String text;                   //告警正文


    @ManyToOne
    @JoinColumn(name = "devid")
    private DmtDevinfo devinfo;            //设备id

    @Transient
    private Integer devid;                 //设备id
    @Transient
    public Integer getDevid() {
        return devid;
    }
    @Transient
    public void setDevid(Integer devid) {
        this.devid = devid;
    }


    @Column(name = "createTimeStamp")
    private Long createTimeStamp;          //告警生成时间戳

    public Integer getNodeId() {
        return nodeId;
    }

    public void setNodeId(Integer nodeId) {
        this.nodeId = nodeId;
    }

    public AlarmLevel getLevel() {
        return level;
    }

    public void setLevel(AlarmLevel level) {
        this.level = level;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public Long getCreateTimeStamp() {
        return createTimeStamp;
    }

    public void setCreateTimeStamp(Long createTimeStamp) {
        this.createTimeStamp = createTimeStamp;
    }

    public DmtDevinfo getDevinfo() {
        return devinfo;
    }

    public void setDevinfo(DmtDevinfo devinfo) {
        this.devinfo = devinfo;
    }
}
