package com.GetData.domain;

public class Tb_Message {
	
	public int state=0;
	public String describe="";
	
	public int getState()
	{
		return state;
	}

	public String getDescribe()
	{
		return describe;
	}
	 
	public void setState(int state)
	{
		this.state=state;
	}
	
	public void setDescirbe(String describe)
	{
		this.describe=describe;
	}
}
