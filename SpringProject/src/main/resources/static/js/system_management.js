

$(document).ready(function () {

//tableView4  tb_tableView4
    $("#Post_management_add .adddepSubmit").click(function () {


    });

var data_list=data_rol_list();

            $("#tableView4").datagrid({
                scrollbarSize: 0,
                rownumbers: false,
                fitColumns: true,
                striped: true,
                pagination: true,
                pageSize: 15,   //表格中每页显示的行数
                pageList: [15],
                fit: true,//固定在底部
                toolbar: "#tb_tableView4",
                data: data_list.slice(0, 15),
                columns: [[
                    {field: 'id', checkbox: 'true', width: 30},
                    {field: 'name', align: "center", title: "角色名称", width: 170},
                    {field: 'represent', align: "center", title: "备注", width: 170}
                ]
                ]
            });
            var pager = $("#tableView4").datagrid("getPager");
            pager.pagination({
                total: data_list.length,
                layout: ['first', 'prev', 'links', 'next', 'last'],
                onSelectPage: function (pageNo, pageSize) {
                    var start = (pageNo - 1) * pageSize;
                    var end = start + pageSize;
                    $("#tableView4").datagrid("loadData", data_list.slice(start, end));
                    pager.pagination('refresh', {
                        total: data_list.length,
                        pageNumber: pageNo
                    });
                }
            });

});
function data_rol_list(){
        var productTypeTree = [];
        jqueryAjax("/ownwebplatform/roles/allRoles",
            {},
            function (obj) {
                var data = obj;

                        productTypeTree = data;

            });
        return productTypeTree;

}
function jqueryAjax(url, data, parseFunc) {
    // console.log("JqueryAjax");
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