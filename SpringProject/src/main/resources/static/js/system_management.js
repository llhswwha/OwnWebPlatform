

$(document).ready(function () {
    $("#start_time11").datebox({
        required: "true",
        missingMessage: "必填项",
        formatter: function (date) {
            var y = date.getFullYear();
            var m = date.getMonth() + 1;
            var d = date.getDate();
            return y + "/" + (m < 10 ? ("0" + m) : m) + "/" + (d < 10 ? ("0" + d) : d);
        }
    });
    //start_time11  出生年月

    $("#Post_management_add .adddepSubmit").click(function () {


    });

var data_list=dataRoleList();
var columns=  [[
    {field: 'id', checkbox: 'true', width: 30},
    {field: 'name', align: "center", title: "角色名称", width: 170},
    {field: 'represent', align: "center", title: "备注", width: 170}
]
];
    dataForTable($("#tableView4"),"#tb_tableView4",data_list,columns);


    dataForTable($("#data_list"),"#User_management",data_list,columns);
            var userData= userDataList();
            console.log(userData);

});
//新增用户提交

function submitForm() {
    var loginName = $("#organ_amend_content .loginName").val();
    var userName = $("#organ_amend_content .userName").val();
    var gender=$("#organ_amend_content .gender").val();
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
    $.ajax({
        type: "post",
        url: "/ownwebplatform/user/addUsers",
        data:{loginName : loginName ,name:userName ,password :"123",gender :gender ,birthday :birthday ,companyId :companyId ,company :company ,dep :dep ,companyPhone :companyPhone ,homePhone :homePhone ,workPhone :workPhone ,privatePhone :privatePhone ,workEmail :workEmail ,privateEmail :privateEmail ,represent :represent ,validSityData :validSityData ,state :state ,roleSet :roleSet ,roleIdList :roleIdList},

        success: function (data) {
            login_text=data;
            console.log(data);
        },error:function () {
            console.log("ssss")
        }

    });



}

//  统一制作easyUI 制作表格
function dataForTable(tableBox,toolbar,tableData,tableList) {

    tableBox.datagrid({
        scrollbarSize: 0,
        rownumbers: false,
        fitColumns: true,
        striped: true,
        pagination: true,
        pageSize: 15,   //表格中每页显示的行数
        pageList: [15],
        fit: true,//固定在底部
        toolbar: toolbar,
        data: tableData.slice(0, 15),
        columns:tableList
    });
    var pager = tableBox.datagrid("getPager");
    pager.pagination({
        total: tableData.length,
        layout: ['first', 'prev', 'links', 'next', 'last'],
        onSelectPage: function (pageNo, pageSize) {
            var start = (pageNo - 1) * pageSize;
            var end = start + pageSize;
            $("#tableView4").datagrid("loadData", tableData.slice(start, end));
            pager.pagination('refresh', {
                total: tableData.length,
                pageNumber: pageNo
            });
        }
    });


}
//获取用户列表
function userDataList(){
    var productTypeTree = [];
    jqueryAjax("/ownwebplatform/user/findUserList",
        {},
        function (obj) {
            var data = obj;
            productTypeTree = data;
            console.log(productTypeTree);
        });
    return productTypeTree;
}
//获取角色列表
function dataRoleList(){
        var productTypeTree = [];
        jqueryAjax("/ownwebplatform/role/allRoles",
            {},
            function (obj) {
                var data = obj;
                productTypeTree = data;
            });
        return productTypeTree;
}
function jqueryAjax(url, data, parseFunc) {
    $.ajax({
        type: "get",
        url: url,
        data: data,
        dataType: "json",
        async: false,
        contentType: 'application/json',
        success: function (result) {
            var obj =  result;
            parseFunc(obj);
        }
    });
}
$(function () {
    $("#add_userlist").click(function () {
        var top = document.body.scrollTop + document.documentElement.scrollTop;
        $('#User_management_add').window({
            collapsible: false,
            minimizable: false,
            maximizable: false
        }).window("open").window('resize', {top: top + 40, left: 40});
    });
    $("#post_open_add,#add_rol_open,#Post_editdata,#rol_edit_list").click(function () {
        // 岗位，角色增加修改

        $("#Post_management_add .textbox ,#Post_management_add .richtextbox").val("");
        var doname = $(this).attr("value");
        var textname = $(this).text();
        $('#Post_management_add').window('open').window('resize', {top: 40, left: 40});
        $("#Post_management_add").siblings(".panel-header").children(".panel-title").text(textname + doname + "信息");
        $("#Post_management_add .postname").text(doname);
        $("#Post_management_add .post_head").text(doname + "信息：");


    });
    $(".close_winde").click(function () {
        var win = $(this).attr("value");
        $(win).window("close");
    });

});