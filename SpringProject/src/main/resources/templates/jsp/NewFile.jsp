<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
 
<html>
<head>
<title>SELECT 操作</title>

<jsp:include page="head.jsp" flush="true" />
<style type="text/css">
.border_table td,.border_table th{
border:1px solid #000;}
</style>
</head>
<body>
<div id="data"></div>
<jsp:include page="Data.jsp" flush="true" />
<!--
JDBC 驱动名及数据库 URL 
数据库的用户名与密码，需要根据自己的设置
useUnicode=true&characterEncoding=utf-8 防止中文乱码
 -->
<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
     url="jdbc:mysql://localhost:3306/RUNOOB?useUnicode=true&characterEncoding=utf-8"
     user="root"  password="123456"/>
 
<sql:query dataSource="${snapshot}" var="result">
SELECT * from websites;
</sql:query>

<a href="map.jsp">map</a>
<h1>JSP 数据库实例 - 菜鸟教程</h1>
<table class="border_table">
<tr>
   <th>ID</th>
   <th>站点名</th>
   <th>站点地址</th>
</tr>
<c:forEach var="row" items="${result.rows}">
<tr>
   <td><c:out value="${row.id}"/></td>
   <td><c:out value="${row.name}"/></td>
   <td><c:out value="${row.url}"/></td>
</tr>
</c:forEach>
</table>
 <%
    String test = "I am testing !"; // JSP片段中定义变量
%>
<SCRIPT type="text/javascript"  >
    var tmp = "<%=test %>"; 
    $.ajax({
        url:'./data.html',
        type:'get',
        success:function(res){
            $('#data').html($(res));
        }
    });
    console.log(tmp);
</SCRIPT >
</body>
</html>