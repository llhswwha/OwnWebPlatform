package com.shencode.ownwebplatform.model;

import java.util.List;

//返回信息
public class Message<T> {

    private  int state;
    private  String describe;

    private  String exception;

    private T data;

    public Message(){

    }

    public Message(int state,String describe)
    {
        this.state=state;
        this.describe=describe;
    }

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

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }
    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setSuccess(T data){
        setData(data);
        this.state=0;
        this.describe="成功";
    }
    public void setFailure(Exception ex) {
        System.out.println("setFailure");
        System.out.println(ex);
        ex.printStackTrace();
        this.state=1;
        this.describe="失败";
        this.exception=ex.toString();
    }
}
