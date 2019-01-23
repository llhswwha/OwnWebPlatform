<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
  <%
      String path = request.getContextPath();
      String basePath = request.getScheme() + "://"
              + request.getServerName() + ":" + request.getServerPort()
              + path + "/";
 %>
   <!--    c标签要使用,那么就必须要有它 -->
  <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
  <c:set scope="page" var="url"  value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <base href="<%=basePath%>">
<title>新增用户</title> 
 <meta http-equiv="pragma" content="no-cache">
 <meta http-equiv="cache-control" content="no-cache">
 <meta http-equiv="expires" content="0">
 <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
 <meta http-equiv="description" content="This is my page">   
</head>
<body>

<div align="center"
         style="width: 400px; position: relative;left:450px">
         <form action="${url}/text/Servlet_TbCustomer?who=Insert" method="post">
             <h4>新增用户</h4>
            账号：  <input type="text" name="code"><br />
             姓名: <input type="text" name="name"><br />
             
             <input type="submit" value="新增"/>
             <hr />
              <input type="text" name="state" value="${msg.state}"/>
              <input type="text" name="describe" value="${msg.describe}"/>
            <hr/>
         </form>
     </div> 
     <div align="center"style="width: 400px; position: relative;left:450px;">
         <form action="${url}/text/Servlet_TbCustomer?who=queryAll" method="post"> 
              <input type="submit" value="查询所有的数据"/> <br/>
             <table border="1"  cellspacing="0"> 
                  <thead>
                   <tr><td>账号</td><td>姓名</td><td>操作</td></tr>
                  </thead>
                  <tbody>
          <c:forEach items="${list}" var="item">
          <tr>
                 <td>${item.code}</td>
                 <td>${item.name}</td>
                  <td><a  href= "${url}/text/Servlet_TbCustomer?who=queryById&code=${item.code}"     style='text-decoration:none'    onclick='update(this)'   >修改&nbsp;</a> 
                     <a   href= "${url}/text/Servlet_TbCustomer?who=delete&code=${item.code}"   style='text-decoration:none'>删除</a>  </td> 
          </tr>
               </c:forEach>
                  </tbody>
             </table>
             <hr />
         </form>
     </div> 
     <div align="center"
         style="width: 400px; position: relative;left:450px">
         <form action="${url}/text/Servlet_TbCustomer?who=update" method="post">
             <h4>修改用户</h4>
        账号  ： <input type="text"name="code"  value="${customer.code}"/><br />
            姓名: <input type="text" name="name" value="${customer.name}"><br />
           
             <input type="submit" value="保存修改"/>
             <hr />
         </form>
     </div>
 </body>
</body>
</html>