package com.shencode.ownwebplatform.entity.enumClass;

public enum ShowFlags {
    VISUAL(0,"数据可视"),INVISIBLE(1,"数据不可视");

    private Integer index;
    private String name;

    private ShowFlags(Integer index,String name)
    {
        this.index=index;
        this.name=name;
    }
}
