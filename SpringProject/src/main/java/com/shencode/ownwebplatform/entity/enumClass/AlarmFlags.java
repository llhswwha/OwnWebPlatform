package com.shencode.ownwebplatform.entity.enumClass;

public enum AlarmFlags {
    Give_an_alarm(0,"告警"),NoWarning(1,"不告警");

    private Integer index;
    private String name;

    private AlarmFlags(Integer index,String name)
    {
        this.index=index;
        this.name=name;
    }
}
