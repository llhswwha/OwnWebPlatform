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
    
    //��ѯ��������
         @Override 
         public List<Tb_Customer> queryAllData() {
              
             return My_Customer.queryAllData();
         }
         //����
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
	  //�޸�
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
	 //ɾ��
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
	  //��ȡһ������
	@Override
	public Tb_Customer quertDataByid(String code) {
		// TODO Auto-generated method stub
		return My_Customer.quertDataByid(code);
	}
      

   
   
    
   
}
