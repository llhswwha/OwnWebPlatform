<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%
      String path = request.getContextPath();
      String basePath = request.getScheme() + "://"
              + request.getServerName() + ":" + request.getServerPort()
              + path + "/";
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link href="<%=basePath%>css/Public/reset.css" rel="stylesheet" />
   <link href="<%=basePath%>css/Login/Login.css" rel="stylesheet" />
   <script type="text/javascript" src="<%=basePath%>js/jquery-1.11.3.js"></script>
<title>登录</title>
</head>      
<body style="overflow: hidden">
<script type="text/javascript">
</script>
<div class="All">
<%--     <div class="login-top">
        <span class="login-tophei"></span>
        <img alt="" src="<%=basePath%>images/Login.png" class="login-topicon"/>
        <span class="login-toptip">公安部门设备管理系统</span>
    </div>  --%>
    <div class="login-content" style="background-image:url(<%=basePath%>images/bg.png)">
        <ul class="login_bg">
            <li class="login_fields_user" style="z-index:1000">
                <form id="form" action="${pageContext.request.contextPath}/checkCode" method="post">
                    <img alt="" src="<%=basePath%>images/Login.png" class="login-topicon"/>
                    <span class="login-toptip">公安部门设备管理系统</span>
                    <div style="height:40px;width:300px;margin-top: 28px;">
                    <input type="text" value="" placeholder="用户名/编号" style="margin-left:17px" class="form-control" id="loginName"/>
                    </div>
                    <div style="height:40px;width:300px;padding-top:20px;">
                        <input type="text" value="" placeholder="请输入登录密码" style="margin-left:17px" class="form-control" id="passWord" />
                    </div>              
                    <!--验证码-->
                    <div id="auth-code-box" style="padding-top:20px;">
                        <input type="text" id="inputCode" autocomplete="off" placeholder="验证码"  />
                        <img id="imgObj" alt="验证码"src="${pageContext.request.contextPath}/getCode">
                        <span style="font-size:14px;color:#41a0f1;margin-left:20px;cursor: pointer;" onclick="changeImg()">换一组</span>
                    </div>
                </form>
                <!-- 登录按钮 重置按钮 -->
                <div class="login_fields_submit">
                    <button class="btn_dl" id="login"  type="submit">登录</button>
                </div>
            </li>
        </ul>
    </div>
    <div class="login-bottom">
        <p>杭州莘科信息技术有限公司大平台项目</p>
    </div>
</div>
    <!-- 底部 -->
<script type="text/javascript">

    function changeImg() {
        var imgSrc = $("#imgObj");
        var src = imgSrc.attr("src");
        imgSrc.attr("src", chgUrl(src));
    }

    // 时间戳
    // 为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳
    function chgUrl(url) {
        var timestamp = (new Date()).valueOf();
        url = url.substring(0, 20);
        if ((url.indexOf("&") >= 0)) {
            url = url + "×tamp=" + timestamp;
        } else {
            url = url + "?timestamp=" + timestamp;
        }
        return url;
    }
    $("#login").click(function(){
    	$.ajax({
    		type: "post",
            url: "${url}/GetData/text/checkCode",
            data: {
            	code:$("#inputCode").val()
            },
            async: false,
            success: function (data) {
                var result = eval('('+data+')');
                alert(data);
                var list = result.success;
                alert(list);
                if(list == "true")
                {
                	alert(list+"1");
                	$.ajax({
                		type: "post",
                        url: "${url}/GetData/text/Servlet_TbUser?who=DoLogin",
                        data: {
                        	loginName:$("#loginName").val(),
                            pw:$("#passWord").val()
                        },
                        async: false,
                        success:function(e){ 
                        	var result = eval('('+e+')');
                        	console.log(result);
                            if(result.success == "true"){
                            	alert("登录成功");          
                            }
                            else{
                            	alert("输入的用户名或密码有误");
                            }                   	    
                        },
                        error:function(){
                        }
                	});
                }
                else{
                	alert("验证码输入错误");
                }
                
            },
            error:function(){
            }
    	});
    });
    
    
</script>

</body>
</html>