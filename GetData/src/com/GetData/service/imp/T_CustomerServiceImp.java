package com.GetData.service.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import com.GetData.dao.ITb_Customer;
import com.GetData.dao.imp.ITb_CustomerImp;
import com.GetData.service.ITb_CustomerService;
import com.GetData.domain.Tb_Customer;

public  class T_CustomerServiceImp implements ITb_CustomerService {

	public Connection conn1 = null;
	public ResultSet rs = null;
    public PreparedStatement ps = null;
    boolean b=false;
    
    ITb_Customer My_Customer=new ITb_CustomerImp();
    
    //查询所有数据
         @Override 
         public List<Tb_Customer> queryAllData() {
              
             return My_Customer.queryAllData();
         }
         //新增
	@Override
	public boolean insertData(Tb_Customer t) {
		// TODO Auto-generated method stub
		int i=My_Customer.insertData(t);
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
	public boolean updateData(Tb_Customer t) {
		// TODO Auto-generated method stub
		int i=My_Customer.updateData(t);
		if(i==0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	 //删除
	@Override
	public boolean deleteData(String code) {
		// TODO Auto-generated method stub
		int i=My_Customer.deleteData(code);
		if(i==0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	  //获取一条数据
	@Override
	public Tb_Customer quertDataByid(String code) {
		// TODO Auto-generated method stub
		return My_Customer.quertDataByid(code);
	}
      

   
   
    
   
}
