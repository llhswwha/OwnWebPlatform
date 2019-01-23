package com.GetData.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.GetData.dao.ITb_SpaceRes;
import com.GetData.dao.imp.ITb_SpaceResImp;
import com.GetData.domain.Tb_SpaceRes;

public class ITb_SpaceResServiceImp implements ITb_SpaceResService{
	public Connection conn1 = null;
	public ResultSet rs = null;
    public PreparedStatement ps = null;
    boolean b=false;
    
    ITb_SpaceRes  My_SpaceRes=new ITb_SpaceResImp();

	@Override
	public List<Tb_SpaceRes> queryAllData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteData(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertData(Tb_SpaceRes t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateData(Tb_SpaceRes t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Tb_SpaceRes quertDataByid(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
