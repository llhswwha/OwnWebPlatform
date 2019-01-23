$(document).ready(function (){
	changeMargin();
	window.onresize = function(){
        changeMargin();
    };
    
});
function changeMargin(){
	var contentboxheight = $(document.body).height();
    $(".contentbox").css("height",contentboxheight+"px");
    var ResourcesRight = $(document.body).width();
    $(".contentbox").css("width",ResourcesRight+"px");
    $(".Resources-right").css("width",(ResourcesRight-250)+"px"); 
}