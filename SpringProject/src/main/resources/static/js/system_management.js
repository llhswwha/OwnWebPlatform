// $(".systitlebox .titlelist").click(function () {
// if($(this).hasClass('active')){
//
// }else {
//
//     var index = $(".systitlebox .titlelist").index(this);
//     $(".systitlebox .titlelist").removeClass("active").eq(index).addClass("active");
//     $(".syscontent .sysmain").removeClass("active").eq(index).addClass("active");
// }
//
// })


    $("#add_userlist").click(function () {
        var top = document.body.scrollTop + document.documentElement.scrollTop;
        $('#User_management_add').window({
            collapsible: false,
            minimizable: false,
            maximizable: false
        }).window("open").window('resize', { top: top + 40, left: 40 });
    });
$("#post_open_add,#add_rol_open,#Post_editdata,#rol_edit_list").click(function () {
    // 岗位，角色增加修改

    $("#Post_management_add .textbox ,#Post_management_add .richtextbox").val("");
    var doname = $(this).attr("value");
    var textname=$(this).text();
    $('#Post_management_add').window('open').window('resize', { top: 40, left: 40 });
    $("#Post_management_add").siblings(".panel-header").children(".panel-title").text( textname+doname+"信息");
    $("#Post_management_add .postname").text(doname);
    $("#Post_management_add .post_head").text(doname+"信息：");


});
$(".close_winde").click(function () {
var win = $(this).attr("value");
    $(win).window("close");
});