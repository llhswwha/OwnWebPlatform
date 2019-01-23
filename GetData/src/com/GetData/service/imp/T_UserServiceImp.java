package com.GetData.service.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;





import com.GetData.dao.ITb_User;
import com.GetData.dao.imp.ITb_UserImp;
import com.GetData.domain.Tb_User;
import com.GetData.service.ITb_UserService;

public class T_UserServiceImp implements ITb_UserService{

	public Connection conn1 = null;
	public ResultSet rs = null;
    public PreparedStatement ps = null;
    boolean b=false;
    
    ITb_User My_User=new ITb_UserImp();
	
	@Override
	public List<Tb_User> queryAllData() {
		// TODO Auto-generated method stub
		return My_User.queryAllData();
	}

	@Override
	public boolean insertData(Tb_User t) {
		// TODO Auto-generated method stub
		int i=My_User.insertData(t);
		if(i==0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean updateData(Tb_User t) {
		// TODO Auto-generated method stub
		int i=My_User.updateData(t);
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
	public boolean deleteData(String loginName) {
		// TODO Auto-generated method stub
		int i=My_User.deleteData(loginName);
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
	public Tb_User quertDataByid(String loginName) {
		// TODO Auto-generated method stub
		return My_User.quertDataByid(loginName);
	}


}
