$(document).ready(function (){
	changeMargin();
	$("#head-crease").click(function(){
		$("#message-show").dialog({
	        title: '创建空间资源',
	        height: 446,
	        width: 640,
	        top: 10,
	        left:40,
	    });
		$("#message-show input").val("");
		   $("#message-show .etui").show().find("i").hide();
           $("#message-show").dialog("open");
	});
	 window.onresize = function(){
         changeMargin();
     };
     $(".edit").click(function(){
    	 $("#message-city").dialog({
 	        title: '编辑地市',
 	        height: 450,
 	        width: 483,
 	        top: 10,
 	        left:40,
 	    });
    	 $("#message-city .etui").show();
    	 $("#message-city").dialog("open");
     });
     // 添加城市模态框
     $("#addCity").click(function () {
		 $(".city-list").append('<li><span contenteditable="true" class="count"></span></li>');
	 });
	$("#btn-sure").click(function () {
		addCity();
	})
});
// 界面自适应函数
function changeMargin(){
	var contentboxheight = $(document.body).height();
    $(".contentbox").css("height",contentboxheight+"px");
    var ResourcesRight = $(document.body).width();
    $(".contentbox").css("width",ResourcesRight+"px");
    $(".Resources-right").css("width",(ResourcesRight-250)+"px"); 
}
//添加城市名称
 function addCity(){
	 var list = [];
	 var cityName = $(".count");
	 cityName.each(function () {
		 list.push($(this).text());
	 })
	 console.log(list);
	 $.ajax({
		 type:"post",
		 url:url,
		 data:list,
		 success:function (result) {
			 alert(result);
		 }
	 })
}
