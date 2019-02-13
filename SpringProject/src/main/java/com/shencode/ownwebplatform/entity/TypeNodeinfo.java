package com.shencode.ownwebplatform.entity;

import com.shencode.ownwebplatform.entity.enumClass.AlarmFlags;
import com.shencode.ownwebplatform.entity.enumClass.ShowFlags;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

//设备类型监控节点
@Entity
@Table(name = "t_type_nodeinfo")
public class TypeNodeinfo extends  BaseEntity{
    //private  Integer id;    //监控节点ID
    @Column(name = "name")
    private  String name;   //监控节点名称
    @Column(name = "pid")
    private  Integer pid;    //父ID
    @Column(name = "pointno")
    private  Integer  pointno;  //设备监控节点编号
    @Column(name = "unit")
    private  String unit;       //监控项单位
    @Column(name = "ty")
    private  String ty;         //数据类型
    @Column(name = "generalalarmlower")
    private  Float generalalarmlower= Float.valueOf(0);    //一般告警下限
    @Column(name = "generalalarmupper")
    private  Float generalalarmupper= Float.valueOf(100);    //一般告警上限
    @Column(name = "seriousalarmlower")
    private  Float seriousalarmlower= Float.valueOf(0);         //严重警告下限
    @Column(name = "seriousalarmupper")
    private  Float seriousalarmupper= Float.valueOf(100);         //严重警告上限
    @Column(name = "emergencyalarmlower")
    private  Float emergencyalarmlower= Float.valueOf(0);   //紧急告警下限
    @Column(name = "emergencyalarmupper")
    private  Float emergencyalarmupper= Float.valueOf(100);   //紧急告警上限
    @Column(name = "showFlags")
    private ShowFlags showFlags=ShowFlags.VISUAL;          //数据是否可视
    @Column(name = "alarmFlags")
    private AlarmFlags alarmFlags=AlarmFlags.Give_an_alarm;       //是否告警

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

    public Integer getPointno() {
        return pointno;
    }

    public void setPointno(Integer pointno) {
        this.pointno = pointno;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getTy() {
        return ty;
    }

    public void setTy(String ty) {
        this.ty = ty;
    }

    public Float getGeneralalarmlower() {
        return generalalarmlower;
    }

    public void setGeneralalarmlower(Float generalalarmlower) {
        this.generalalarmlower = generalalarmlower;
    }

    public Float getGeneralalarmupper() {
        return generalalarmupper;
    }

    public void setGeneralalarmupper(Float generalalarmupper) {
        this.generalalarmupper = generalalarmupper;
    }

    public Float getSeriousalarmlower() {
        return seriousalarmlower;
    }

    public void setSeriousalarmlower(Float seriousalarmlower) {
        this.seriousalarmlower = seriousalarmlower;
    }

    public Float getSeriousalarmupper() {
        return seriousalarmupper;
    }

    public void setSeriousalarmupper(Float seriousalarmupper) {
        this.seriousalarmupper = seriousalarmupper;
    }

    public Float getEmergencyalarmlower() {
        return emergencyalarmlower;
    }

    public void setEmergencyalarmlower(Float emergencyalarmlower) {
        this.emergencyalarmlower = emergencyalarmlower;
    }

    public Float getEmergencyalarmupper() {
        return emergencyalarmupper;
    }

    public void setEmergencyalarmupper(Float emergencyalarmupper) {
        this.emergencyalarmupper = emergencyalarmupper;
    }

    public ShowFlags getShowFlags() {
        return showFlags;
    }

    public void setShowFlags(ShowFlags showFlags) {
        this.showFlags = showFlags;
    }

    public AlarmFlags getAlarmFlags() {
        return alarmFlags;
    }

    public void setAlarmFlags(AlarmFlags alarmFlags) {
        this.alarmFlags = alarmFlags;
    }
}
