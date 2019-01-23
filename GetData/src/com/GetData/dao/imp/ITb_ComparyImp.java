package com.GetData.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.GetData.dao.ITb_Compary;
import com.GetData.domain.Tb_Compary;
import com.GetData.domain.Tb_Customer;
import com.GetData.domain.Tb_User;
import com.GetData.unit.unitMysql;

public class ITb_ComparyImp implements ITb_Compary{

	public Connection conn1 = null;
	public ResultSet rs = null;
    public PreparedStatement ps = null;
	//获取数据
	@Override
	public List<Tb_Compary> queryAllData() {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				conn1 =unitMysql.getConnection();// 链接数据库
		    	List<Tb_Compary> list=new ArrayList<Tb_Compary>();
		    	try
		    	{
		    		String sql="select * from t_Compary";
		    		 ps = conn1.prepareStatement(sql);
		    		 rs = ps.executeQuery();
		    		 Tb_Compary compary=null;
		    		 while(rs.next())
		    		 {
		    			 compary=new Tb_Compary();
		    			 compary.setID(rs.getString("ID"));
		    			 compary.setPID(rs.getString("PID"));
		    			 compary.setName(rs.getString("Name"));
		    			 list.add(compary);
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
   //添加
	@Override
	public int insertData(Tb_Compary t) {
		// TODO Auto-generated method stub
		conn1 =unitMysql.getConnection();// 链接数据库
    	int i=0;
    	try
    	{
    	String sqlinsert="insert into t_Compary (ID,PID,name) values (?,?,?)";
    	ps=conn1.prepareStatement(sqlinsert,PreparedStatement.RETURN_GENERATED_KEYS);
    	
    	ps.setString(1, t.id);
    	ps.setString(2, t.pid);
    	ps.setString(3, t.name);
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
	public int updateData(Tb_Compary t) {
		// TODO Auto-generated method stub
		 conn1 = unitMysql.getConnection();
         int i = 0;
         try {
             String sqlUpdate = "update t_Compary set Name=?,PID=?  where ID=?";
            ps = conn1.prepareStatement(sqlUpdate);
             ps.setString(1, t.getName());
             ps.setString(2, t.getPid());
             ps.setString(3, t.getID());
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
	public int deleteData(String id) {
		// TODO Auto-generated method stub
		 conn1 = unitMysql.getConnection();
         int i = 0;
        try 
        {
            String sqlDelete = "delete from t_Compary where ID=?";
             ps = conn1.prepareStatement(sqlDelete);
             ps.setString(1, id);
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
//获取一条，根据ID
	@Override
	public Tb_Compary quertDataByid(String code) {
		// TODO Auto-generated method stub
		conn1 = unitMysql.getConnection();
        String sql = "select * from  t_sxidc_customer  where code=?";
        Tb_Compary Compary = null;
        if (code.length() > 0) {
            try {
           	 ps=conn1.prepareStatement(sql);
                ps.setString(1, code);
                rs = ps.executeQuery();
                if (rs.next()) {
                	Compary = new Tb_Compary();
                	Compary.setID(rs.getString("ID"));
                	Compary.setPID(rs.getString("PID"));
                	Compary.setName(rs.getString("Name"));

                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
           	 unitMysql.Close(null, ps, conn1);
            }
        }
        return Compary;
	}

}
