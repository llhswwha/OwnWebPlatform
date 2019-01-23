<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Insert title here</title>
     <jsp:include page="head.jsp" flush="true" />
     <script type="text/javascript" src="../js/device.js"></script>
     <link rel="stylesheet" href="../css/device.css">
</head>
<body>
   <div class="contentlist" id="device">
     <div class="deviece-top">
        <div class="top-head">
         	<a href="javascript: void(0)" class="btn-custom btn-blue" id="head-crease"  plain="true" style="height: 32px;">创建设备资源</a> 
            <a href="javascript: void(0)" class="btn-custom btn-white" id="head-edit"  plain="true">修改</a> 
            <a href="javascript: void(0)" class="btn-custom btn-white" id="head-delete" plain="true">删除</a> 
            <a href="javascript: void(0)" class="btn-custom btn-white" id="head-derive" plain="true" style="color: #333;margin-left:30px;margin-right:30px;">导出</a>
            <select class="device-select">
            	<option>武汉市</option>
            	<option>重庆市</option>
            </select>
            <select class="device-select" style="margin-left: 10px;">
            	<option>自然村/市辖区</option>
            	<option>重庆市</option>
            </select>
             <span class="icon iconfont icon-shuaxin"></span> 
        </div>
                  <!-- 表格内容 -->
        <div id="data_list">
            
        </div>
        <!--  设备资源弹窗 -->
        <div id="message-show" title="创建设备资源" buttons="#dlg-buttons" style="overflow: hidden;">
            	<form class="etui" style="height: 358px;">
                	<fieldset style="margin-left: 20px; border: none;" class="clearfix" id="modal-body" aria-labelledby="myModalLabel" aria-hidden="true">
                	<span style="padding-top: 10px;"><i>*</i>设备名称：</span>
                	<input type="text" class="textbox textbox_normal" placeholder="请输入设备名称" style="width:343px;"><br>
                	<span><i>*</i>设备类型：</span>
                	<select>
                  		<option>大设备</option>
                  		<option>小设备</option>
                	</select><br>
                	<span><i>*</i>所属地市：</span>
                	<select>
                  		<option>来丰县</option>
                  		<option>小城县</option>
                	</select><br>
                	<span><i>*</i>所属自然村/市辖区:</span>
                	<select>
                  		<option>来丰县</option>
                  		<option>小城县</option>
                	</select><br>
                	<span><i>*</i>详细地址：</span>
                	<input type="text" class="textbox textbox_normal" placeholder="请输入详细地址" style="width:343px;"><br>
                	<span><i>*</i>经纬度：</span>
                	<span style="width: 0px;text-align: left;margin-left: 0;">N</span><input type="text" class="textbox textbox_normal" style="width:133px;">
                	<span style="width: 0px;text-align: left;margin-left: 26px;">E</span><input type="text" class="textbox textbox_normal" style="width:133px;"><br>
                	<span><i>*</i>运维负责人：</span>
                	<input type="text" class="textbox textbox_normal" placeholder="请输入负责人姓名" style="width:343px;"><br>
                	<span><i>*</i>负责人联系方式：</span>
                	<input type="text" class="textbox textbox_normal" placeholder="请输入联系方式" style="width:343px;"><br>
                	<span>通信采集方式：</span>
                	<input type="text" class="textbox textbox_normal" placeholder="请输入通信采集方式" style="width:343px;"><br>
                	<span>采集方式：</span>
                	<input type="text" class="textbox textbox_normal" placeholder="请输入采集方式" style="width:343px;"><br>
                	<span>设备上电接口1：</span>
                	<input type="text" class="textbox textbox_normal" placeholder="请输入设备上电接口" style="width:343px;"><br>
                	<span>设备上电接口2：</span>
                	<input type="text" class="textbox textbox_normal" placeholder="请输入设备上电接口" style="width:343px;"><br>
                	<span>IP类型接口：</span>
                	<input type="text" class="textbox textbox_normal" placeholder="请输入IP类型 " style="width:343px;"><br>
                	</fieldset>
                	<div id="dlg-buttons">
                    	<a class="btn-custom btn-gray" href="javascript:void(0);" id="btn_sure" type="button">确定</a>
                    	<a class="btn-custom btn-white" href="javascript:void(0);" id="cancel" onclick="closeDialog('dialog-form') " type="button" iconCls="icon-cancel">取消</a>
                	</div>
            	</form>
            	</div>
          	 </div>
     </div>
 
</body>