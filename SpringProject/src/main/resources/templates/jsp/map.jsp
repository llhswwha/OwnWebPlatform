<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />
	<script type="text/javascript" src="js/apiv2.0.min.js"></script>
<jsp:include page="head.jsp" flush="true" />
<title>map</title>
<script type="text/javascript" src="js/plugin/textIconOverlay_min.js"></script>
		<script type="text/javascript" src="js/plugin/markerClusterer_min.js"></script>
		<!--点聚合-->
		<link rel="stylesheet" type="text/css" href="css/bmap.css"/>
			<style type="text/css">
			html {
				height: 100%;
			}

			body {
				height: 100%;
				margin: 0px;
				padding: 0px;
			}
			

			#container {
				height: 100%;
			}
			.addbox{
				height: 100px;
				width: 200px;
				position: absolute;
				background: red;
				margin-top: -100px;
			}
		</style>
</head>
<body>
	<div id="container"></div>
	<script type="text/javascript">
	// 百度地图API功能 点聚合
	var map = new BMap.Map("container",{minZoom:10,maxZoom:18,enableMapClick:false});
	map.centerAndZoom(new BMap.Point(118.797239,32.06442), 13);
	map.enableScrollWheelZoom();
	map.addControl(new BMap.NavigationControl());			//缩放按钮
	map.disableDoubleClickZoom();//禁止双击缩放

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