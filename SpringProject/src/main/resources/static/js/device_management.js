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
var entityName = 'deviceType';
var  entityTable=null;
$(document).ready(function (){
    TableExport.init();
    deviceTypeList();
});
function deviceTypeList(){
    getEntityColumns(entityName,function (columns) {
        entityTable=new EntityTable('#deviceType-list',entityName);
        entityTable.init(columns);
        entityTable.load();
    })
};

$(".close_winde").click(function () {
var win = $(this).attr("value");
    $(win).window("close");
});
$("#head-crease").click(function () {
    //Device_attribute  840 480
    var top = document.body.scrollTop + document.documentElement.scrollTop;
    $('#Device_attribute').window({
        collapsible: false,
        minimizable: false,
        maximizable: false
    }).window("open").window('resize', { top: top + 40, left: 40 });
})