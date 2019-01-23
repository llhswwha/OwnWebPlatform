package com.shencode.ownwebplatform.entity;

import java.util.List;

//返回信息
public class Message<T> {

    private  int state;
    private  String describe;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private T data;

    public void setState(int state) {
        this.state = state;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public int getState() {
        return state;
    }

    public String getDescribe() {
        return describe;
    }

    public Message(int state,String describe)
    {
         this.state=state;
         this.describe=describe;
    }


}
