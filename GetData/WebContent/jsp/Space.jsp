<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>空间资源管理</title>
     <jsp:include page="head.jsp" flush="true" />
     <script type="text/javascript" src="../js/Space.js"></script>
     <link rel="stylesheet" href="../css/Space.css">
</head>
<style>
      .nav{
      background-color: #0072ff;
      color: #fff;
      }
</style>
<body>
		<div class="contentlist" id="Space_Resources" style="background-color: #eff3f9">
           <div class="Resources-left">
           	 <div class="left-inside" style="margin:20px;border: 1px solid #ddd;height:calc(100% - 40px);">           
           		<div style="padding:10px 0 0 20px;">
              	<span style="font-size:14px;">选择地市</span>
              	<a class="edit">编辑</a>
                </div>
             	<ul>
               		<li class="city-item">武汉市<span></span></li>
               		<li class="city-item">江苏市<span></span></li>
              		<li class="city-item">南昌市<span></span></li>
               		<li class="city-item">南京市<span></span></li>
               		<li class="city-item">杭州市<span></span></li>
             	</ul>
              </div>
           </div>
          <div class="Resources-right">
            <div class="right-head" style="display: flex;">
                <a href="javascript: void(0)" class="btn-custom btn-blue" id="head-crease"  plain="true" style="height: 32px;">创建空间资源</a> 
                <a href="javascript: void(0)" class="btn-custom btn-white" id="head-edit"  plain="true">修改</a> 
                <a href="javascript: void(0)" class="btn-custom btn-white" id="head-delete" plain="true">删除</a> 
                <a href="javascript: void(0)" class="btn-custom btn-white" id="head-derive" plain="true" style="color: #333;margin-left:30px;margin-right:30px;">导出</a>
                <div style="width:335px; display: inline-block;">
                   <input type="text" class="textbox textbox_normal" placeholder="根据区域或设备名称搜索自然村、市辖区" style="width:270px;border-radius:0;padding: 0px 10px;">
                   <div style="display: inline-block;width: 40px;height: 30px;border: 1px solid #ddd;vertical-align: bottom;margin-left:-5px;">
                       <span class="icon iconfont icon-sousuo" style="margin-left: 11px;font-size: 17px;cursor: pointer;"></span>
                   </div>
                </div>
                <div class="flush">
                <span class="icon iconfont icon-shuaxin" style="font-size: 16px;cursor: pointer;margin-left: 8px;"></span> 
                </div> 
            </div>
            <div id="data_list">
            
            </div>
           <!--  创建空间管理弹窗 -->
            <div id="message-show" title="空间管理展示页面" buttons="#dlg-buttons" >
            	<form class="etui" style="height: 348px;">
                	<fieldset style="margin-left: 20px; border: none;" class="clearfix" id="modal-body" aria-labelledby="myModalLabel" aria-hidden="true">
                		<span style="padding-top: 10px;">所属地市：</span>
                		<input type="text" class="textbox textbox_normal" placeholder="恩施土家族苗族自治区"><br>
                		<span><i>*</i>自然村、市辖区名称：</span>
                		<select >
                  			<option>来丰县</option>
                		</select>
                		<span style="color:#999;font-size:12px;margin-left:-20px;">请先选择所属地市</span><br>
                		<span>面积：</span>
                		<input type="text" class="textbox textbox_normal" placeholder="请输入占地面积">
                		<span style="color:#999;font-size:12px;margin-left:-15px;">（单位：平方公里）</span><br>
                		<span>行政区域代码：</span>
                		<input type="text" class="textbox textbox_normal" placeholder="请输入行政区域代码"><br>
                		<span>政府驻地：</span>
                		<input type="text" class="textbox textbox_normal" placeholder="请输入政府驻地" style="width:343px;"><br>
                		<span><i>*</i>负责人姓名：</span>
                		<input type="text" class="textbox textbox_normal" placeholder="请输入姓名" style="width:343px;"><br>
                		<span><i>*</i>负责人联系方式：</span>
                		<input type="text" class="textbox textbox_normal" placeholder="请输入联系方式" style="width:343px;"><br>
                	</fieldset>
                	<div id="dlg-buttons">
                    	<a class="btn-custom btn-gray" href="javascript:void(0);" id="btn_sure" type="button">确定</a>
                    	<a class="btn-custom btn-white" href="javascript:void(0);" id="cancel" type="button" iconCls="icon-cancel">取消</a>
                	</div>
            	</form>
             </div>
            </div>
       	</div> 
       	 <!-- 编辑地市弹窗 -->
     <div id="message-city" title="编辑地市" buttons="#btn-city">
         <form class="etui" style="height: 348px;">
         	<fieldset style="padding:20px; border: none;" class="clearfix" id="modal-body" aria-labelledby="myModalLabel" aria-hidden="true">
         		<ul class="city-list">
         			<li><span>武汉市</span></li>
         			<li><span>重庆市</span></li>
         			<li><span>南昌市</span></li>
         			<li><span>上饶市</span></li>
         			<li><span>杭州市</span></li>
         		</ul>
                <div style="float:left;width: 40px;height: 40px;border: 1px dashed #cee3fd;vertical-align: bottom;margin-left:10px;">
                     <span class="icon iconfont icon-jiahao" style="margin-left: 10px;font-size: 19px;cursor: pointer;"></span>
                </div>
         	</fieldset>
         	<div id="btn-city">
              <a class="btn-custom btn-blue" href="javascript:void(0);" id="btn_sure" type="button">保存并关闭</a>
              <a class="btn-custom btn-white" href="javascript:void(0);" id="cancel" type="button" iconCls="icon-cancel">取消</a>
         	</div>	
         </form>	
    </div>
</body>
<script type="text/javascript">
    $(".left-inside ul li").first().addClass("nav");
 	$(".left-inside ul li").click(function(){
 		$(this).removeClass("city-item");
 		$(this).addClass("nav").siblings().removeClass("nav").addClass("city-item");
 	});
</script>
</html>