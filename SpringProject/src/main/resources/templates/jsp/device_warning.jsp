<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>设备告警</title>
     <jsp:include page="head.jsp" flush="true" />
     <script type="text/javascript" src="../js/device_warning.js"></script>
     <link rel="stylesheet" href="../css/device_warning.css">
</head>
<style>
	.nav{
      	background-color: #0072ff;
      	color: #fff;
      }
</style>
<body>
	<div class="contentlist" id="device-warning" style="background-color: #eff3f9">
		<div class="Resources-left">
			<div style="margin:20px;border: 1px solid #ddd;height: calc(100% - 40px);;">
				<ul>
					<li id="curve">当前告警</li>
					<li id="history">历史告警</li>
				</ul>
			</div>
		</div>
		<div class="Resources-right">
			<ul class="right-head first" >
				<li>
					<span>告警区域:</span>
					<select>
						<option>来峰县</option>
						<option>武汉县</option>
					</select>
				</li>
				<li>
					<span>告警设备类型:</span>
					<select>
						<option>服务器</option>
						<option>交换机</option>
					</select>
				</li>
				<li>
					<span>告警类型:</span>
					<select>
						<option>交换机告警</option>
						<option>发动机告警</option>
					</select>
				</li>
				<li>
					<span>告警级别:</span>
					<select>
						<option>一级告警</option>
						<option>二级告警</option>
					</select>
				</li>
				<li>
					<div class="flush">
                	<span class="icon iconfont icon-shuaxin" style="font-size: 16px;cursor: pointer;margin-left: 8px;"></span> 
                	</div>
				</li>		 
			</ul>
			<ul class="right-head two" style="display: flex;display:none;">
				<li><input type="text"  value="2018-11-28" class="textbox textbox_normal"></li>
				<li><input type="text"  value="2018-11-28" class="textbox textbox_normal" style="margin: 0 20px;"></li>
				<li>
					<span>告警区域:</span>
					<select>
						<option>来峰县</option>
						<option>武汉县</option>
					</select>
				</li>
				<li>
					<span>告警设备类型:</span>
					<select>
						<option>服务器</option>
						<option>交换机</option>
					</select>
				</li>
				<li>
					<span>告警类型:</span>
					<select>
						<option>交换机告警</option>
						<option>发动机告警</option>
					</select>
				</li>
				<li>
					<div class="flush">
                	<span class="icon iconfont icon-shuaxin" style="font-size: 16px;cursor: pointer;margin-left: 8px;"></span> 
                	</div>
				</li>
				<li>
					<span>告警级别:</span>
					<select>
						<option>一级告警</option>
						<option>二级告警</option>
					</select>
				</li>
				<li>
					<div style="width:300px; display: inline-block;">
                   		<input type="text" class="textbox textbox_normal" placeholder="输入设备类型名称进行搜索" style="width:228px;border-radius:0;padding: 0px 10px;">
                   		<div style="display: inline-block;width: 40px;height: 30px;border: 1px solid #ddd;vertical-align: bottom;margin-left:-5px;">
                       	<span class="icon iconfont icon-sousuo" style="margin-left: 11px;font-size: 17px;cursor: pointer;"></span>
                   		</div>
                	</div>
				</li>
			</ul>			
		</div>
	</div>
</body>
<script type="text/javascript">
    $(".Resources-left ul li").first().addClass("nav");
 	$(".Resources-left ul li").click(function(){
 		$(this).addClass("nav").siblings().removeClass("nav").addClass("city-item");
 	});
 	$("#curve").click(function(){
 		$(".first").css("display","inherit");
 		$(".two").css("display","none");
 	});
 	$("#history").click(function(){
 		$(".first").css("display","none");
 		$(".two").css("display","inherit");
 	});
 	
</script>
</html>