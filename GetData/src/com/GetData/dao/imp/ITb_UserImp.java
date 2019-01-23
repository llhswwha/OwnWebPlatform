package com.GetData.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.GetData.dao.ITb_User;
import com.GetData.domain.Tb_User;
import com.GetData.unit.unitMysql;

public class ITb_UserImp implements ITb_User {

	public Connection conn1 = null;
	public ResultSet rs = null;
    public PreparedStatement ps = null;
    
	//获取所有数据
	@Override
	public  List<Tb_User> queryAllData() {
		// TODO Auto-generated method stub
		conn1 =unitMysql.getConnection();// 链接数据库
    	List<Tb_User> list=new ArrayList<Tb_User>();
    	try
    	{
    		String sql="select * from t_User";
    		 ps = conn1.prepareStatement(sql);
    		 rs = ps.executeQuery();
    		 Tb_User user=null;
    		 while(rs.next())
    		 {
    			 user=new Tb_User();
    			 user.setloginName(rs.getString("LoginName"));
    			 user.setname(rs.getString("Name"));
    			 user.setpw(rs.getString("PW"));
    			 user.setsex(rs.getString("sex"));
    			 user.setbirthday(rs.getString("birthday"));
    			 user.setcompany(rs.getString("company"));
    			 user.setdep(rs.getString("dep"));
    			 user.setcpZJ(rs.getString("cpZJ"));
    			 user.settel(rs.getString("tel"));
    			 user.setcpEmail(rs.getString("cpEmail"));
    			 user.setemail(rs.getString("email"));
    			 user.setdescribe(rs.getString("describe"));
    			 user.setvalidSityData(rs.getString("validSityData"));
    			 user.setstate(rs.getInt("state"));
    			 list.add(user);
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
	public int insertData(Tb_User t) {
		// TODO Auto-generated method stub
		conn1 =unitMysql.getConnection();// 链接数据库
    	int i=0;
    	try
    	{
    	String sqlinsert="insert into t_User(LoginName,Name,PW,SEX,Birthday,Company,Dep,CpZJ,HomeZJ,CpTel,Tel,CpEmail,Email,`Describe`,ValidityData,State) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    	ps=conn1.prepareStatement(sqlinsert,PreparedStatement.RETURN_GENERATED_KEYS);
    	ps.setString(1, t.loginName);
    	ps.setString(2, t.name);
    	ps.setString(3, t.pw);
    	ps.setString(4, t.sex);
    	ps.setString(5, t.birthday);
    	ps.setString(6, t.company);
    	ps.setString(7, t.dep);
    	ps.setString(8, t.cpZJ);
    	ps.setString(9, t.homeZJ);
    	ps.setString(10, t.cpTel);
    	ps.setString(11, t.tel);
    	ps.setString(12, t.cpEmail);
    	ps.setString(13, t.email);
    	ps.setString(14, t.describe);
    	ps.setString(15, t.validSityData);
    	ps.setInt(16, t.state);
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
	public int updateData(Tb_User t) {
		// TODO Auto-generated method stub
		 conn1 = unitMysql.getConnection();
         int i = 0;
         try {
        	 if(t.loginName==null||t.loginName.length()<=0)
        	 {
        		 return i;
        	 }  
             String sqlUpdate = "update t_User set Name=?,PW=?,sex=?,Birthday=?,Company=?,Dep=?,CpZJ=?,HomeZJ=?,CpTel=?,Tel=?,CpEmail=?,Email=?,Describe=?,ValidityData=?,State=?  where loginname=?";
            ps = conn1.prepareStatement(sqlUpdate);
          
             ps.setString(1, t.getname());
             ps.setString(2, t.getpw());
             ps.setString(3, t.getsex());
             ps.setString(4, t.getbirthday());
             ps.setString(5, t.getcompany());
             ps.setString(6, t.getdep());
             ps.setString(7, t.getcpZJ());
             ps.setString(8, t.gethomeZJ());
             ps.setString(9, t.getcpTel());
             ps.setString(10, t.gettel());
             ps.setString(11, t.getcpEmail());
             ps.setString(12, t.getemail());
             ps.setString(13, t.getdescribe());
             ps.setString(14, t.getvalidityData());
             ps.setInt(15, t.getstate());
             ps.setString(16, t.getloginName());
            i = ps.executeUpdate();
         } catch (SQLException e) {
           e.printStackTrace();
         } finally {
       	  unitMysql.Close(rs, ps, conn1);
         }
         return i;
	}
  //删除
	@Override
	public int deleteData(String loginName) {
		// TODO Auto-generated method stub
		 conn1 = unitMysql.getConnection();
         int i = 0;
        try 
        {
            String sqlDelete = "delete from t_User where loginName=?";
             ps = conn1.prepareStatement(sqlDelete);
             ps.setString(1, loginName);
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
   //查询一条
	@Override
	public Tb_User quertDataByid(String loginname) {
		// TODO Auto-generated method stub
		conn1 = unitMysql.getConnection();
        String sql = "select * from  t_User  where loginname=?";
        Tb_User user = null;
        if (loginname.length() > 0) {
            try {
           	 ps=conn1.prepareStatement(sql);
                ps.setString(1, loginname);
                rs = ps.executeQuery();
                if (rs.next()) {
                	user = new Tb_User();
                	user.setloginName(rs.getString("LoginName"));
       			 user.setname(rs.getString("Name"));
       			 user.setpw(rs.getString("PW"));
       			 user.setsex(rs.getString("sex"));
       			 user.setbirthday(rs.getString("birthday"));
       			 user.setcompany(rs.getString("company"));
       			 user.setdep(rs.getString("dep"));
       			 user.setcpZJ(rs.getString("cpZJ"));
       			 user.settel(rs.getString("tel"));
       			 user.setcpEmail(rs.getString("cpEmail"));
       			 user.setemail(rs.getString("email"));
       			 user.setdescribe(rs.getString("describe"));
       			 user.setvalidSityData(rs.getString("validSityData"));
       			 user.setstate(rs.getInt("state"));

                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
           	 unitMysql.Close(null, ps, conn1);
            }
        }
        return user;
	}

}
