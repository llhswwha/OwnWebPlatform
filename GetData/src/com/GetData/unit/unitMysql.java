package com.GetData.unit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class unitMysql {
	public static final String URL="jdbc:mysql://192.168.1.32:3306/java_cs??useUnicode=true&characterEncoding=utf8";
	public static final String name="root";
	public static final String password="123456";
	public static final String DREIVER="com.mysql.jdbc.Driver";
	
	static 
	{
		try
		{
			Class.forName(DREIVER);
			
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection()
	{
		try
		{
		return DriverManager.getConnection(URL, name, password);//创建与数据库的链接	
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}
	
    public static void Close(ResultSet rs, PreparedStatement ps, Connection conn) {
             if (rs != null) {
                 try {
                     rs.close();
                 } catch (SQLException e) {
                     e.printStackTrace();
                 }
             }
 
             if (ps != null) {
                 try {
                     ps.close();
                 } catch (SQLException e) {
                     e.printStackTrace();
                 }
             }
 
             if (conn != null) {
                 try {
                     conn.close();
                       } catch (SQLException e) {
                     e.printStackTrace();
                 }
             }
         }
	
	
    
}
