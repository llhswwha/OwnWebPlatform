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
});
function changeMargin(){
	var contentboxheight = $(document.body).height();
    $(".contentbox").css("height",contentboxheight+"px");
    var ResourcesRight = $(document.body).width();
    $(".contentbox").css("width",ResourcesRight+"px");
    $(".Resources-right").css("width",(ResourcesRight-250)+"px"); 
}