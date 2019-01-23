console.log("testCommonCrud.js")
function getData(){
    console.log('getData');
    var data=new Object();
    data.name='用户'+new Date().getMilliseconds()+new Date().getSeconds();
    data.loginName='user'+new Date().getMilliseconds()+new Date().getSeconds();
    data.pw='1235';
    //data.id='1';
    data.sex='1';
    data.birthday='123';
    console.log(data);
    return data;
}
$(function () {
    $("#getEntityList").click(function () {
        console.log("getEntityList")
        var dao=new Ajax();
        dao.get("","");
    });
    $("#add").click(function () {
        var entityName='users';
        var data=getData();
        console.log('add');
        var dao=new EntityDao(entityName);
        var result=dao.add(data);
        console.log(result);
    });

    $("#getAll").click(function () {
        var entityName='users';
        var data=getData();
        console.log('add');
        var dao=new EntityDao(entityName);
        var result=dao.getAll();
        console.log(result);
    });

    $("#getAll").click(function () {
        var entityName='users';
        var data=getData();
        console.log('add');
        var dao=new EntityDao(entityName);
        var result=dao.getAll();
        console.log(result);
    });
})

