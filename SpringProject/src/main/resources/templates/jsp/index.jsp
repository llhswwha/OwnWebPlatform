<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<html>
<head>
<title>首页</title>
	<script type="text/javascript" src="../js/apiv2.0.min.js"></script>
<jsp:include page="head.jsp" flush="true" />
	<script type="text/javascript" src="../js/plugin/textIconOverlay_min.js"></script>
<script type="text/javascript" src="../js/plugin/markerClusterer_min.js"></script>
<!--点聚合-->
<link rel="stylesheet" type="text/css" href="../css/bmap.css"/>
			<style type="text/css">
			html,body  {
				height: 100%;
				overflow:hidden;
			}
body  {
	 position: relative;	
	box-sizing: border-box;
    padding-top: 100px;
			}
			
header{
	position: absolute;
    top: 0;
    z-index: 99;
    width: 100%;
			}
.contentbox{
    position: absolute;
    top: 100px;
    width:100%;
}

			.contentlist {
				height: 100%;
				display: none;

			}
			.contentlist.active{
				display: block;
			}
			.addbox{
				height: 100px;
				width: 200px;
				position: absolute;
				background: #ff0000;
				margin-top: -100px;
			}
		</style>
<link rel="stylesheet" href="../css/index.css">
</head>
<body>
<header>
    <div class="top">
    <span class="icon iconfont icon-caidan"></span>
    <span class="title_name">杭州莘科自有平台项目</span>
    <span class="right user_name">admin</span>
    <span  class="right icon iconfont icon-yonghu" ></span>
    <span  class="right" >22</span>
    <span  class="right" >在线人数：</span>
    <span  class="right icon iconfont icon-sousuo" ></span>
    <input class="right search"  type="text" placeholder="模糊匹配菜单引用">

    </div>
    <nav>
    <ul class="title_box">
    <li class="title_list"><a href="javascript:;" class="alist active" value="container">首页</a></li>
    <li class="title_list" ><a href="javascript:;" class="alist" value="Space">空间资源管理</a>


	</li>
    <li class="title_list" ><a href="javascript:;"  value="device_management" class="alist">设备管理</a>
		<div class="sec_nav">
			<div class="sec_navlist"><a href="javascript:;"  value="device_management">设备类型管理</a></div>
			<div class="sec_navlist"><a href="javascript:;"  value="device">设备资源管理</a></div>
			<div class="sec_navlist"><a href="javascript:;"  value="device_warning">告警管理</a></div>
		</div>
	</li>
    <li class="title_list"><a value="system_management" class="alist" href="javascript:;">系统管理</a></li>
    <li class="title_list"><a value="Centralized_monitoring" class="alist" href="javascript:;">集中监控展示管理</a></li>
      <li class="title_list"><a href="javascript:;"  value="device_warning">告警管理</a></li>
    </ul>
    </nav>
</header>
<div class="left_nav_box">
	<div class="left_main"></div>
	<ul class="leftnave">
		<li class="leftnavlist"><a href="javascript:;"  value="container">首页</a></li>
		<li class="leftnavlist"><a href="javascript:;"  value="Space">空间资源管理</a></li>
		<li class="leftnavlist"><a href="javascript:;"  value="device_management">设备管理 <i class="icon iconfont icon-youbian"></i></a>
			<div class="secleft_nav">
				<div class="secleft_navlist"><a href="javascript:;"  value="device_management">设备类型管理</a></div>
				<div class="secleft_navlist"><a href="javascript:;" value="device">设备资源管理</a></div>
				<div class="secleft_navlist"><a href="javascript:;"  value="device_warning">告警管理</a></div>
			</div>
		</li>
		<li class="leftnavlist"><a href="javascript:;"  value="system_management" >系统管理</a></li>
		<li class="leftnavlist"><a href="javascript:;" value="Centralized_monitoring">集中监控展示管理</a></li>
		 <li class="leftnavlist"><a href="javascript:;"  value="device_warning">告警管理</a></li>
	</ul>
</div>



    <div class="contentbox">
		<div class="contentlist active" id="container"></div>
    </div>
<script type="text/javascript">
	$(".top .icon-caidan,.left_main").click(function (e) {
		e.stopPropagation();
		$(".left_nav_box,.top .icon-caidan").toggleClass("show");
	})
	$(".left_nav_box").click(function (e) {
		e.stopPropagation();
	})
	$("body").click(function () {
		$(".left_nav_box,.top .icon-caidan").removeClass("show");
	})
	
	//加载菜单对应html
	$(".title_box .title_list a").click(function () {
		 if($(this).hasClass('active')){
			 return;
		 }
		var valuee =$(this).attr("value");
if(valuee=="0"){
	return;
}
		$(".title_box .title_list a,.contentlist").removeClass("active");

		$(this).addClass("active")

		if($("#"+valuee).length==0){
			$('.contentbox').append('<iframe id='+valuee+' class="contentlist active"  src="'+valuee+'.html" frameborder="0" scrolling="no" style="width: 100%; height: 100%;"></iframe>');
		}else {
			$("#"+valuee).addClass("active");
		}

	//	if(valuee=="0"&&text=="系统管理"){
//			$.ajax({
//				url:'system_management.jsp',
//				type:'get',
//				success:function(res){
					//<iframe id="mainPage" src="system_management.jsp" frameborder="0" scrolling="no" style="width: 100%; height: 100%;"></iframe>
				//	$('.contentbox').append('<iframe id="sys_manage" name="'+text+'" class="contentlist active" src="system_management.jsp" frameborder="0" scrolling="no" style="width: 100%; height: 100%;"></iframe>');
					//$(".title_box .title_list a.active").attr("value","1");
//				}
//			});
//		}else if(valuee=="1"&&text=="系统管理"){
//			$("#sys_manage").addClass("active");
//		}

	});
	$(".leftnave .leftnavlist a").click(function () {



		var valuee =$(this).attr("value");
		if(valuee=="0"){
			return;
		}
		if($("#"+valuee).length==0){
			$(".contentlist").removeClass("active");
			$('.contentbox').append('<iframe id='+valuee+' class="contentlist active" src="'+valuee+'.html" frameborder="0" scrolling="no" style="width: 100%; height: 100%;"></iframe>');
		}else {
			$(".contentlist").removeClass("active");
			$("#"+valuee).addClass("active");
		}
	});

	$(window).resize(function(){
		var contentboxheight = $(document.body).height();
		$(".contentbox").css("height",contentboxheight+"px");
	});
	var contentboxheight = $(document.body).height();
	$(".contentbox").css("height",contentboxheight+"px");
	// 百度地图API功能 点聚合 $(document.body).height()
	//var map = new BMap.Map("container",{minZoom:10,maxZoom:18,enableMapClick:false});
//	map.centerAndZoom(new BMap.Point(118.797239,32.06442), 13);
//	map.enableScrollWheelZoom();
//	map.addControl(new BMap.NavigationControl());			//缩放按钮
//	map.disableDoubleClickZoom();//禁止双击缩放

	var myIcon2 = new BMap.Icon("tb1_0.png", new BMap.Size(30, 40));
	var data = [{ "mapy": "32.06442", "mapx": "118.797239", "time": "12:30" },
		{ "mapy": "32.065452", "mapx": "118.787239", "time": "11:30" },
		{ "mapy": "32.07462", "mapx": "118.799239", "time": "10:30" },
		{ "mapy": "32.08472", "mapx": "118.779239", "time": "13:30" }
	];
	var markers = new Array();

	$.each(data, function(i, item) {
		var point = new BMap.Point(item.mapx, item.mapy);
		var marker = new BMap.Marker(point);
		var content = item.time;
		addClickHandler(content, marker); //添加点击事件

		markers.push(marker);

	});

	//添加聚合效果。
	var markerClusterer = new BMapLib.MarkerClusterer(map, {markers:markers});

	var opts = {
		width : 250, // 信息窗口宽度
		height: 80, // 信息窗口高度
		title : "信息窗口" , // 信息窗口标题
		enableMessage:true//设置允许信息窗发送短息
	};
	var textjd = "";
	var textwd="";
	function addClickHandler(content,marker){
		marker.addEventListener("click",function(e){
		var p = e.target;
					textjd = p.getPosition().lng;
					textwd = p.getPosition().lat;
		alert("marker的位置是" + p.getPosition().lng + "," + p.getPosition().lat);
			}
		);
	}
	$("body").on("click",".BMap_Marker",function (e) {

		$(".addbox").remove();
		var 	boxleft = $(this).css("left");
		var 	boxtop  = $(this).css("top");
		//	$(this).parent("div").append("<div class='addbox' style='left: "+boxleft+";top:"+boxtop+"'>"+textjd+","+textwd+"</div>")
		$(this).append("<div class='addbox' style=''>"+textjd+","+textwd+"</div>")

	})


	</script>
 
	
</body>
</html>