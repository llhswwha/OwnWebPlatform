package com.shencode.ownwebplatform.entity.enumClass;
//告警
public enum  AlarmLevel {
    NORMAL(0,"正常"),COMMONLY(1,"一般警告"),SERIOUS(2,"严重警告"),URGENT(3,"紧急警告");

    private Integer index;
    private String name;

    private AlarmLevel(Integer index,String name)
    {
        this.index=index;
        this.name=name;
    }

}
