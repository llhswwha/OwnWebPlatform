package com.GetData.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.GetData.dao.ITb_SpaceRes;
import com.GetData.domain.Tb_Compary;
import com.GetData.domain.Tb_SpaceRes;
import com.GetData.unit.unitMysql;

public class ITb_SpaceResImp implements ITb_SpaceRes{
	public Connection conn1 = null;
	public ResultSet rs = null;
    public PreparedStatement ps = null;
    //所有数据
	@Override
	public List<Tb_SpaceRes> queryAllData() {
		// TODO Auto-generated method stub
		conn1 =unitMysql.getConnection();// 链接数据库
    	List<Tb_SpaceRes> list=new ArrayList<Tb_SpaceRes>();
    	try
    	{
    		String sql="select a.*,b.`Name` as ComparyName from t_SpaceRes a left join t_Compary b on a.ComparyID=b.id";
    		 ps = conn1.prepareStatement(sql);
    		 rs = ps.executeQuery();
    		 Tb_SpaceRes SpaceRes=null;
    		 while(rs.next())
    		 {
    			 SpaceRes=new Tb_SpaceRes();
    	         SpaceRes.setid(rs.getString("id"));
    	         SpaceRes.setname(rs.getString("name"));
    	         SpaceRes.setarea(rs.getString("area"));
    	         SpaceRes.setcomparyID(rs.getString("comparyID"));
    	         SpaceRes.setcomparyName(rs.getString("comparyName"));
    	         SpaceRes.setcode(rs.getString("code"));
    	         SpaceRes.setaddress(rs.getString("address"));
    	         SpaceRes.setcharge(rs.getString("charge"));
    	         SpaceRes.settel(rs.getString("tel"));
    	         SpaceRes.setdevNum(rs.getInt("devNum"));
    			 list.add(SpaceRes);
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
	public int insertData(Tb_SpaceRes t) {
		// TODO Auto-generated method stub
		conn1 =unitMysql.getConnection();// 链接数据库
    	int i=0;
    	try
    	{
    	String sqlinsert="insert into t_SpaceRes (ID,name,area,ComparyID,Code,Address,Charge,Tel) values (?,?,?,?,?,?,?,?)";
    	ps=conn1.prepareStatement(sqlinsert,PreparedStatement.RETURN_GENERATED_KEYS);
    	
    	ps.setString(1, t.id);
    	ps.setString(2, t.name);
    	ps.setString(3, t.area);
    	ps.setString(4, t.comparyID);
    	ps.setString(5, t.code);
    	ps.setString(6, t.address);
    	ps.setString(7, t.charge);
    	ps.setString(8, t.tel);
    	
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
	public int updateData(Tb_SpaceRes t) {
		// TODO Auto-generated method stub
		 conn1 = unitMysql.getConnection();
         int i = 0;
         try {
             String sqlUpdate = "update t_SpaceRes set name=?,Area=?,ComparyID=?,code=?,Address=?,Charge=?,tel=? where id=?";
            ps = conn1.prepareStatement(sqlUpdate);
             ps.setString(1, t.getname());
             ps.setString(2, t.getarea());
             ps.setString(3, t.getcomparyID());
             ps.setString(4, t.getcode());
             ps.setString(5, t.getaddress());
             ps.setString(6, t.getcharge());
             ps.setString(7, t.gettel());
             ps.setString(8, t.getid());
            
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
            String sqlDelete = "delete from t_SpaceRes where id=?";
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
	//获取一条数据
	@Override
	public Tb_SpaceRes quertDataByid(String code) {
		// TODO Auto-generated method stub
		conn1 = unitMysql.getConnection();
        String sql = "select a.*,b.`Name` as ComparyName from t_SpaceRes a left join t_Compary b on a.ComparyID=b.id where a.id=?";
        Tb_SpaceRes SpaceRes = null;
        if (code.length() > 0) {
            try {
           	 ps=conn1.prepareStatement(sql);
                ps.setString(1, code);
                rs = ps.executeQuery();
                if (rs.next()) {
                	SpaceRes = new Tb_SpaceRes();
                	SpaceRes.setid(rs.getString("id"));
       	         SpaceRes.setname(rs.getString("name"));
       	         SpaceRes.setarea(rs.getString("area"));
       	         SpaceRes.setcomparyID(rs.getString("comparyID"));
       	         SpaceRes.setcomparyName(rs.getString("comparyName"));
       	         SpaceRes.setcode(rs.getString("code"));
       	         SpaceRes.setaddress(rs.getString("address"));
       	         SpaceRes.setcharge(rs.getString("charge"));
       	         SpaceRes.settel(rs.getString("tel"));
       	         SpaceRes.setdevNum(rs.getInt("devNum"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
           	 unitMysql.Close(null, ps, conn1);
            }
        }
        return SpaceRes;
	}
}
