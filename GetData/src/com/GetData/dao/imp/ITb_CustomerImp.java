package com.GetData.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.GetData.dao.ITb_Customer;
import com.GetData.domain.Tb_Customer;
import com.GetData.unit.unitMysql;

public class ITb_CustomerImp implements ITb_Customer {
	public Connection conn1 = null;
	public ResultSet rs = null;
    public PreparedStatement ps = null;
    
    //查询所有数据
    @Override
    public List<Tb_Customer> queryAllData()
    {
    	conn1 =unitMysql.getConnection();// 链接数据库
    	List<Tb_Customer> list=new ArrayList<Tb_Customer>();
    	try
    	{
    		String sql="select Code,Name from t_sxidc_customer";
    		 ps = conn1.prepareStatement(sql);
    		 rs = ps.executeQuery();
    		 Tb_Customer customer=null;
    		 while(rs.next())
    		 {
    			 customer=new Tb_Customer();
    			 customer.setCode(rs.getString("Code"));
    			 customer.setName(rs.getString("Name"));
    			 list.add(customer);
    		 }
    	}
    	catch(SQLException e)
    	{
    		 e.printStackTrace();
    	}
    	finally
    	{
    		unitMysql.Close(rs, ps, conn1);
    	}
   	
    	return list;
    }
    
    //新增
    @Override
    public int insertData(Tb_Customer t)
    {
    	conn1 =unitMysql.getConnection();// 链接数据库
    	int i=0;
    	try
    	{
    	String sqlinsert="insert into t_sxidc_customer (code,name) values (?,?)";
    	ps=conn1.prepareStatement(sqlinsert,PreparedStatement.RETURN_GENERATED_KEYS);
    	ps.setString(1, t.code);
    	ps.setString(2, t.name);
    	ps.executeUpdate();
    	rs=ps.getGeneratedKeys();
    	if(rs.next())
    	{
    		i=rs.getInt(1);
    	}
    	}
    	catch(SQLException ex)
    	{
    		ex.printStackTrace();
    	}
    	finally
    	{
    		unitMysql.Close(rs, ps, conn1);
    	}
    	return i;
    }
    //修改
    @Override
    public int updateData(Tb_Customer t)
    {
    	 conn1 = unitMysql.getConnection();
    	          int i = 0;
    	          try {
    	              String sqlUpdate = "update t_sxidc_customer set Name=?  where Code=?";
    	             ps = conn1.prepareStatement(sqlUpdate);
    	              ps.setString(1, t.getName());
    	              ps.setString(2, t.getCode());
    	             i = ps.executeUpdate();
    	          } catch (SQLException e) {
    	            e.printStackTrace();
    	          } finally {
    	        	  unitMysql.Close(null, ps, conn1);
    	          }
    	          return i;
    }
    //删除
    @Override
    public int deleteData(String code)
    {
    	 conn1 = unitMysql.getConnection();
    	          int i = 0;
    	         try 
    	         {
    	             String sqlDelete = "delete from t_sxidc_customer where code=?";
    	              ps = conn1.prepareStatement(sqlDelete);
    	              ps.setString(1, code);
    	             i = ps.executeUpdate();
    	             if (i == 1)
    	             {
    	                  return i;
    	             }
    	          }
    	         catch (SQLException e)
    	          {
    	              e.printStackTrace();
    	          } finally 
    	          {
    	        	  unitMysql.Close(null, ps, conn1);
    	          }
    	         return i;
    }
    //通过ID查询
    @Override
    public Tb_Customer quertDataByid(String code)
    {
    	conn1 = unitMysql.getConnection();
    	         String sql = "select * from  t_sxidc_customer  where code=?";
    	         Tb_Customer Customer = null;
    	         if (code.length() > 0) {
    	             try {
    	            	 ps=conn1.prepareStatement(sql);
    	                 ps.setString(1, code);
    	                 rs = ps.executeQuery();
    	                 if (rs.next()) {
    	                	 Customer = new Tb_Customer();
    	                	 Customer.setCode(rs.getString("Code"));
    	                	 Customer.setName(rs.getString("Name"));

    	                 }
    	             } catch (SQLException e) {
    	                 e.printStackTrace();
    	             } finally {
    	            	 unitMysql.Close(null, ps, conn1);
    	             }
    	         }
    	         return Customer;
    }
}
