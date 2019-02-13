package com.shencode.ownwebplatform.entity.enumClass;

public enum DevOperate {
    ADD(0,"添加"),UPDATE(1,"修改"),DELETE(2,"删除");

    private Integer index;
    private String name;

    private DevOperate(Integer index,String name)
    {
        this.index=index;
        this.name=name;
    }
}
