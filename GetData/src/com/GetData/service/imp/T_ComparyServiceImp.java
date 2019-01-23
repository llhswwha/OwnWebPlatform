package com.GetData.service.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.GetData.dao.ITb_Compary;
import com.GetData.dao.imp.ITb_ComparyImp;
import com.GetData.domain.Tb_Compary;
import com.GetData.service.ITb_ComparyService;

public class T_ComparyServiceImp  implements ITb_ComparyService {
	
	public Connection conn1 = null;
	public ResultSet rs = null;
    public PreparedStatement ps = null;
    boolean b=false;
    
    ITb_Compary My_Compary=new ITb_ComparyImp();

	//获取所有数据
	@Override
	public List<Tb_Compary> queryAllData() {
		// TODO Auto-generated method stub
		return My_Compary.queryAllData();
	}
    //删除
	@Override
	public boolean deleteData(String id) {
		// TODO Auto-generated method stub
		int i=My_Compary.deleteData(id);
		if(i==0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
     //新增
	@Override
	public boolean insertData(Tb_Compary t) {
		// TODO Auto-generated method stub
		int i=My_Compary.insertData(t);
		if(i==0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
     //修改
	@Override
	public boolean updateData(Tb_Compary t) {
		// TODO Auto-generated method stub
		int i=My_Compary.updateData(t);
		if(i==0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
    //获取ID
	@Override
	public Tb_Compary quertDataByid(String id) {
		// TODO Auto-generated method stub
		return My_Compary.quertDataByid(id);
	}

}
