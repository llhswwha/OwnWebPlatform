

$(document).ready(function () {
    $('.form_datetime').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
//获取角色列表

   var table=userDataList();
   var roltable= dataRoleList();
    $('#myTabContent li:eq(0) a').tab('show');
    $("#Post_management_add .adddepSubmit").click(function () {
        if($("#addPostList").val()==""){
            alert("角色名不能为空！");
            return
        }
      //    var data={"id":null,"active":true,"createTime":null,"modifyTime":null,"deleteTime":null,"name":"nnnnn","represent":null,"menuSet":null,"userSet":null,"userIdeList":null,"menuIdList":null};
    var data={"id":null,"active":true,"createTime":null,"modifyTime":null,"deleteTime":null,"name":$("#addPostList").val(),"represent":$("#Post_management_add .describe").val(),"menuSet":null,"userSet":null,"userIdeList":null,"menuIdList":null};
var dao=new EntityDao('role');
var ajaxRolType =$("#Post_management_add").siblings(".panel-header").children(".panel-title").text();
if(ajaxRolType=="添加角色信息"){
    dao.add(data,function(result){
        if(result.state=="0"){
            alert("角色添加成功！")
        }
    });
}else {
    //修改角色
    dao.update(data,function(result){
        if(result.state=="0"){
            alert("角色修改成功！")
        }
    });
}

    });
//角色下拉


});
//新增用户提交

function submitForm() {
    var loginName = $("#organ_amend_content .loginName").val();
    var userName = $("#organ_amend_content .userName").val();
    var gender=$("#organ_amend_content .gender").val();
    //find('option:selected').text();
    // var serverTypeID = $('#ServerType').find('option:selected').attr('value');
    var birthday=$("#organ_amend_content .birthday").val();
    var companyId=$("#organ_amend_content .companyId").val();
    var company=$("#organ_amend_content .company").val();
    var dep=$("#organ_amend_content .dep").val();
    var companyPhone=$("#organ_amend_content .companyPhone").val();
    var homePhone=$("#organ_amend_content .homePhone").val();
    var workPhone=$("#organ_amend_content .workPhone").val();
    var privatePhone=$("#organ_amend_content .privatePhone").val();
    var workEmail=$("#organ_amend_content .workEmail").val();
    var privateEmail=$("#organ_amend_content .privateEmail").val();
    var represent=$("#organ_amend_content .represent").val();
    var validSityData=$("#organ_amend_content .validSityData").val();
    var state=$("#organ_amend_content .state").val();
    var roleSet=$("#organ_amend_content .roleSet").val();
    var roleIdList=$("#organ_amend_content .roleIdList").val();
    var describe=$("#organ_amend_content .describe").val();
    var cpZJ=$("#organ_amend_content .cpZJ").val();
    var password=$("#organ_amend_content .password").val();

    var data= {"id":null,"active":true,"createTime":null,"modifyTime":null,"deleteTime":null,"loginName":loginName,"name":userName,"password":password,"gender":gender,"birthday":null,"cityId":null,"city":null,"dep":dep,"companyPhone":companyPhone,"homePhone":homePhone,"workPhone":workPhone,"privatePhone":privatePhone,"workEmail":workEmail,"privateEmail":privateEmail,"represent":represent,"validSityData":null,"state":0,"roleSet":null,"roleIdList":null,"describe":describe,"cpZJ":cpZJ}
  //  var data={"id":null,"active":true,"createTime":null,"modifyTime":null,"deleteTime":null,"name":$("#addPostList").val(),"represent":$("#Post_management_add .describe").val(),"menuSet":null,"userSet":null,"userIdeList":null,"menuIdList":null};
    var dao=new EntityDao('user');
    var ajaxRolType =$("#Post_management_add").siblings(".panel-header").children(".panel-title").text();
   // if(ajaxRolType=="添加角色信息"){
        dao.add(data,function(result){
            if(result.state=="0"){
                alert("用户添加成功！")
            }
        });
    // }else {
    //     //修改角色
    //     dao.update(data,function(result){
    //         if(result.state=="0"){
    //             alert("角色修改成功！")
    //         }
    //     });
    // }

}
//获取角色列表
function dataRoleList(){
    var table=new EntityTable('#tableView4','role');


        var columns=  [[
            {field: 'id', checkbox: 'true'},
            {field: 'name', align: "center", title: "角色名称"},
            {field: 'represent', align: "center", title: "备注"}
        ]
        ];
    table.init(columns);
    table.load();
    return table;


}
//用户列表
function userDataList(){

    var table=new EntityTable('#data_list','user');
    var columns=[{
        field: 'id',
        title: 'ID',
        sortable:true
    }, {
        field: 'name',
        title: '昵称',
        sortable:true
    }, {
        field: 'loginName',
        title: '登录名',
        sortable:true
    }, {
        field: 'password',
        title: '密码',
        sortable:true
    }];
    table.init(columns);
    table.load();
    return table;
}


$(function () {

    $("#add_userlist").click(function () {

        $('#User_management_add').modal({backdrop:'static',keyboard:false});
    });
    $("#add_rol_open,#rol_edit_list").click(function () {
        $('#Post_management_add').modal({backdrop:'static',keyboard:false});

    });
    $(".close_winde").click(function () {
        var win = $(this).attr("value");
        $(win).window("close");
    });

});


