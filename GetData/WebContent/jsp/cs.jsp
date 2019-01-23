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
    <script type="text/javascript" src="/GetData/js/jquery-1.11.3.js"></script>

</head>
<body>
<div align="center"
         style="width: 400px; position: relative;left:450px">
         <form method="post" >
             <h4>新增用户</h4>
            账号：  <input id="addcodename" type="text" name="code"><br />
             姓名: <input id="addname" type="text" name="name"><br />
             
             <input type="button" id="addsub" value="新增"/>
            
             <hr />
         </form>
     </div> 
     
    
<script type="text/javascript">
    $("#addsub").click(function () {
    	
    	var form = new FormData();
    	  form.append("code","zxj");
    	  form.append("name",123456);
    	 $.ajax({
    	                url:"${url}/text/Servlet_TbCustomer",
    	                type:"post",
    	                data:{
    	                	who:"Insert",
    	                	code:$("#addcodename").val(),
    	                	name:$("#addname").val()
    	                },
    	               // processData:false,
    	               // contentType:false,
    	                success:function(data){
    	                	  //$("body").append(data)
    	                    console.log("over..");
    	                }
    	});
    	
    	
    	
      
     // var queryAll = "queryAll";   text/Servlet_TbCustomer?who=Insert
    	  
     //   var data={"who":queryAll};
       // $.ajax({
         //   type: "post",
        //    url:"${url}/text/Servlet_TbCustomer",
        //  data:data,
           // dataType: "json",
          //  async: false,
           // contentType: 'application/json',
          //  success: function (result) {
            //    console.log(result)
          //  },error:function () {
            //    console.log("qqq")
           // }})
    });

</script>
 </body>
</body>
</html>