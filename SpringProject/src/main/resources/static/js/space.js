var cityID = -1 ;  //设置城市删除初始ID为-1
var spaceCityId = -1;
var cityName = "";
$(document).ready(function (){
    TableExport.init();
	 getCityList();
	 dataSpaceList(spaceCityId);
});

var entityName='spaceRes';//页面范围内的实体类名称
var entityTable=null;
//获取空间资源列表
function dataSpaceList(spaceCityId){
	console.log(spaceCityId);
    if(entityTable==null){ //初始化部分(getEntityColumns,init)只要执行一次就行
        entityTable=new EntityTable('#data_list',entityName);
        getEntityColumns(entityName,function (columns) {
            entityTable.init(columns);
            var entity={city:{id:spaceCityId}};//这里相当于把city信息传给后端
            entityTable.search(null,entity);
        })
    }
    else{
        var entity={city:{id:spaceCityId}};//这里相当于把city信息传给后端
        entityTable.search(null,entity);
    }

}
//新增空间资源信息
function validateSpaceProperty(data){
	if ((data.name&&data.area&&data.address&&data.code&&data.charge&&data.tel)!==""){
		return data;
	}else {
		alert("请输入完整信息");
		return false;
	}
}

// 界面自适应函数
// function changeMargin(){
// 	var contentboxheight = $(document.body).height();
// 	$(".contentbox").css("height",contentboxheight+"px");
// 	var ResourcesRight = $(document.body).width();
// 	$(".contentbox").css("width",ResourcesRight+"px");
// 	$(".Resources-right").css("width",(ResourcesRight-250)+"px");
// }
//添加城市名称
function addCity(){
	var list = [];
	var cityName = $(".count");
	cityName.each(function () {
		var cityName=$(this).text();
		var city={name:cityName,pid:0};
		list.push(city);
	})
	var dao = new EntityDao("city");
	dao.addList(list,function (result) {

	})
}
//模态框中城市删除
function delCityName(cityId) {
	var dao = new EntityDao('city');
	dao.delete(cityId,function (result) {
		console.log(result);
	});
}
//获取左边城市列表
function getCityList() {
	var dao = new EntityDao("city");
	dao.getAll(function (result) {
		for (var i=0;i<result.length;i++) {
			if (result[i].name != "") {
				$("#cityList").append("<li class=\"city-item\" data-id="+ result[i].id +" value="+result[i].name+">" + result[i].name + "<span></span></li>");
			}
		}
		$(".left-inside ul li").first().addClass("nav");
	});
}
$(function () {
	//左边城市导航栏按钮事件
	$(".left-inside ul").on("click","li",function(){
		$(this).removeClass("city-item");
		$(this).addClass("nav").siblings().removeClass("nav").addClass("city-item");
		var id = $(this).attr("data-id");
		spaceCityId =id;
		dataSpaceList(spaceCityId);
	});
	// 添加城市输入框
	$("#addCity").click(function () {
		$(".city-list").append('<li><span contenteditable="true" class="count"></span></li>');
	});
	//点击编辑获取城市列表
	$("#revise").click(function () {
		$("#cityModal").modal("show");
		$(".city-list").empty();
		var dao = new EntityDao("city");
		dao.getAll(function (result) {
			for (var i=0;i<result.length;i++){
				if (result[i].name != ""){
					$(".city-list").append("<li data-id="+result[i].id+"><span>"+result[i].name+"</span><i class=\"input-close\"></i></li>");
				}
			}
		});
	})
	//城市删除功能
	$(".city-list").on( "click","li",function () {
		$(this).addClass("add").siblings().removeClass("add");
		$(this).siblings().find("span").next().css("display","none");
		$(".city-list").each(function () {
			$(this).find("li").each(function () {
				if ($(this).attr("class")== "add"){
					$(this).find("span").next().css("display","inline-block");
				}
			})
		});
	})
	$(".city-list").on("click","i",function () {
		$(this).parent().css("display","none");
		var cityId = $(this).parent().attr("data-id");
		cityID = cityId;
	});
	$("#btn-sure").click(function () {
		addCity();
		if (cityID != -1){
			delCityName(cityID);
		}
	});
	//创建空间资源
	$("#head-crease").click(function(){
		var id = $("#cityList").find($("li[class='nav']")).attr("data-id");
		var name = $("#cityList").find($("li[class='nav']")).attr("value");
		console.log(id);
		console.log(name);

		//显示模态框界面
		var city={};
		city.name=name;
		city.id=id;
		var entity={};
		entity.city=city;

		showEntityModal({
			title:$(this).text(),
			entityName:entityName,
			validateEntity:validateSpaceProperty,
			entity:entity
		});
		$("#spaceModel input").val("");
	});
	//空间资源删除功能
	$('#head-delete').click(function () {
		// var list=entityTable.getSelections();
		// if(list.length==0){
		// 	showAlertModal('请先选择一行','info');
		// }
		// else{
		// 	var entity=list[0];
		// 	console.log(entity);
		// 	var id=entity.id;
		// 	var dao=entityTable.dao;
		// 	dao.delete(id,function (result) {
		// 		if(result.state==0){
		// 			entityTable.load();
		// 			showAlertModal('删除成功!', 'success');
		// 		}
		// 		else{
		// 			showAlertModal('删除失败!', 'success');
		// 		}
		// 	})
		// }
		entityTable.deleteSelections();
	})
	//空间资源修改功能
    $('#head-edit').click(function () {
    var list=entityTable.getSelections();
	if(list.length==0){
 		showAlertModal('请先选择一行','info');
 	}
 	else{
 		var entity=list[0];
 		console.log(entity);
 		showEntityModal({
			title:$(this).text(),
 			entityName:entityName,
            entity:entity
 		});
 	}
 })
	//搜索功能
	$("#search").click(function () {
	    var map ={};
		map['name'] = $("#search-input").val();
        entityTable.search(map);
	})
 });

/*
function showAlertModal(title, type) {
	$alertModal.find('#title').attr('class','alert alert-' + type || 'success');
	$alertModal.find('.modal-body').text(title)
	$alertModal.modal('show');
	/!*$alert.attr('class', 'alert alert-' + type || 'success')
        .html('<i class="glyphicon glyphicon-check"></i> ' + title).show()
    setTimeout(function () {
        $alert.hide()
    }, 3000)*!/
	setTimeout(function () {
		$alertModal.modal('hide');
	},3000)
}
*/
