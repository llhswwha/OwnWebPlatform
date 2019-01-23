console.log("testCommonCrud.js")
$("#getEntityList").click(function () {
    console.log("getEntityList")
    var dao=new Ajax();
    dao.get("","");
});
$("#add").click(function () {
    console.log("add")
    var dao=new EntityDao("user");
});