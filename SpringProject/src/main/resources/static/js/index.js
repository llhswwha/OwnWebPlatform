$(".top .icon-caidan,.left_main").click(function (e) {
	e.stopPropagation();
	$(".left_nav_box,.top .icon-caidan").toggleClass("show");
})
$(".left_nav_box").click(function (e) {
	e.stopPropagation();
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
	if(valuee=="container"){ //地图(container)把后面的iframe挤下去了 所以需要隐藏掉
		$("#container").show();
	}else{
		$("#container").hide();
	}
	$(this).addClass("active")

	if($("#"+valuee).length==0){
		$('.contentbox').append('<iframe id='+valuee+' class="contentlist active" src="'+valuee+'.html" frameborder="0" scrolling="no" style="width: 100%; height: 100%;"></iframe>');
	}else {
		$("#"+valuee).addClass("active");
	}

});
$(".leftnave .leftnavlist a").click(function () {



	var valuee =$(this).attr("value");
	if(valuee=="0"){
		return;
	}
	if(valuee=="container"){ //地图(container)把后面的iframe挤下去了 所以需要隐藏掉
		$("#container").show();
	}else{
		$("#container").hide();
	}
	if($("#"+valuee).length==0){
		$(".contentlist").removeClass("active");
	$('.contentbox').append(
			'<iframe id='
			+valuee+
			' class="contentlist active" src="'
			+valuee+
			'.html" frameborder="0" scrolling="no" style="width: 100%; height: 100%;"></iframe>'
		);
	}else {
		$(".contentlist").removeClass("active");
		$("#"+valuee).addClass("active");
	}
});

$(window).resize(function(){
	var contentboxheight = $(document.body).height()-100;
	$(".contentbox").css("height",contentboxheight+"px");
});
    var contentboxheight = $(document.body).height()-100;
    $(".contentbox").css("height",contentboxheight+"px");
// 百度地图API功能 点聚合 $(document.body).height()

$(document).ready(function () {
var map = new BMap.Map("container",{minZoom:10,maxZoom:17,enableMapClick:false});
	map.centerAndZoom(new BMap.Point(118.797239,32.06442), 12);
	map.enableScrollWheelZoom();
	//map.addControl(new BMap.NavigationControl());			//缩放按钮
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
		//	alert("marker的位置是" + p.getPosition().lng + "," + p.getPosition().lat);
		}
	);
}


//地图移动

	$("#container").on("click",".goLoction",function () {

		var longitude = $(".longitude").val();
		var latitude = $(".latitude").val();
		map.panTo(new BMap.Point(longitude,latitude));
	});
	;

	$("#container").append('<div class="mapnav"><input  class="mapSearch" type="text" placeholder="搜索资源、地点、设备" ><button class="icon iconfont icon-sousuo"></button></div><select class="mapCity"><option>南京市</option></select>'
		+ '<div class="mapNavRight"><div class="mapRightList"><i class="icon iconfont icon-dingwei"></i>定位</div><div class="mapRightList"><i class="icon iconfont icon-caozuo_quanpingfangda"></i>最大化</div><div class="mapRightList"><i class="icon iconfont icon-shaixuan"></i> 图层筛选</div></div>'
		+'<div class="mapLocation"><div class="left"><div style="height: 50%;box-sizing: border-box;padding-top: 10px;"><span>经度：</span><input class="longitude"/></div><div  style="height: 50%;box-sizing: border-box;padding-top: 10px;"><span>纬度：</span><input class="latitude"/></div></div><div class="goLoction">去定位</div></div>');
$("body").on("click",".BMap_Marker",function (e) {

	$(".addbox").remove();
	var 	boxleft = $(this).css("left");
	var 	boxtop  = $(this).css("top");
	//	$(this).parent("div").append("<div class='addbox' style='left: "+boxleft+";top:"+boxtop+"'>"+textjd+","+textwd+"</div>")
	$(this).append("<div class='addbox' style=''>"+textjd+","+textwd+"</div>")
	e.stopPropagation();
})
//#container  .mapRightList
	$("#container").on("click",".mapRightList",function () {

		if($(this).text()=="定位"){
			if($(this).hasClass('active')){

				$(this).removeClass("active");
				$("#container .mapLocation").hide();
			}else {
				$(this).addClass("active");
				$("#container .mapLocation").show();
			}

		}else {
			if($(this).hasClass('active')){

				return;
			}else {
				$("#container .mapRightList").removeClass("active");
				$(this).addClass("active");

			}
		}

	});
	$("body").on("click",".addbox",function (e) {
		e.stopPropagation();
	});

	$("body").click(function () {
		$(".left_nav_box,.top .icon-caidan").removeClass("show");
		$(".addbox").remove();

	})
})