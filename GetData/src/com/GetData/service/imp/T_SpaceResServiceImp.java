package com.GetData.service.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.GetData.dao.ITb_SpaceRes;
import com.GetData.dao.imp.ITb_SpaceResImp;
import com.GetData.domain.Tb_SpaceRes;
import com.GetData.service.ITb_SpaceResService;

public class T_SpaceResServiceImp implements ITb_SpaceResService{
	
	public Connection conn1 = null;
	public ResultSet rs = null;
    public PreparedStatement ps = null;
    boolean b=false;
    
    ITb_SpaceRes My_SpaceRes=new ITb_SpaceResImp();

	@Override
	public List<Tb_SpaceRes> queryAllData() {
		// TODO Auto-generated method stub
		return My_SpaceRes.queryAllData();
	}

	@Override
	public boolean deleteData(String id) {
		// TODO Auto-generated method stub
		int i=My_SpaceRes.deleteData(id);
		if(i==0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	@Override
	public boolean insertData(Tb_SpaceRes t) {
		// TODO Auto-generated method stub
		int i=My_SpaceRes.insertData(t);
		if(i==0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
  //ÐÞ¸Ä
	@Override
	public boolean updateData(Tb_SpaceRes t) {
		// TODO Auto-generated method stub
		int i=My_SpaceRes.updateData(t);
		if(i==0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	@Override
	public Tb_SpaceRes quertDataByid(String id) {
		// TODO Auto-generated method stub
		return My_SpaceRes.quertDataByid(id);
	}

}
