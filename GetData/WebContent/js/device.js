$(document).ready(function (){
	$("#head-crease").click(function(){
		$("#message-show").dialog({
	        title: '创建设备资源',
	        height: 700,
	        width: 644,
	        top: 50,
	        left:50,
	    });
		$("#message-show .detail-div").empty().append($("#detail-template").html());
		   $("#message-show .etui").show();
           $("#message-show").dialog("open");
	});
	
});
