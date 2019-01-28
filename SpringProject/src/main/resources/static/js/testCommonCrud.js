console.log('testCommonCrud.js')
function getEntityData(entityName){
    console.log('getEntityData');
    var json=$('#txtResult').text();
    var data={};
    if(json==''){
        data.name='用户'+new Date().getMilliseconds()+new Date().getSeconds();
        data.loginName='user'+new Date().getMilliseconds()+new Date().getSeconds();
        data.password='1235';
        //data.id='1';
        data.gender='1';
        data.birthday='123';
        data.workEmail='work@work.com';
    }
    else{
        data=JSON.parse(json);
    }
    console.log(data);
    return data;
}
function getEntityName() {
    var entityName=$('#entityList option:selected').text();
    console.log('getEntityName:'+entityName);
    return entityName;
}
function getId() {
    return $('#txtId').val();
}
function setId(id) {
    return $('#txtId').val(id);
}
function getPage() {
    var page=$('#txtPage').val();
    //console.log('getPage1:'+page);
    page=page-1;
    //console.log('getPage2:'+page);
    return page;
}
function getSize() {
    return $('#txtSize').val();
}

function getCondition() {
    console.log('getCondition');
    var condition=null;
    var json=$('#txtCondition').val();
    console.log(json);
    condition=new Condition();
    if(json!='' && typeof(condition) != 'undefined'){
        condition.setPropertyByJson(json);//直接Json反序列化的话，不是Condition类型了，无法使用Condition类型的方法
    }

    console.log('type:'+typeof(condition));
    console.log(condition);
    var page=getPage();
    var size=getSize();
    condition.setPage(page,size);
    console.log(condition);
    return condition;
}

function showResult(result){
    console.log('showResult');
    console.log(result);
    console.log(typeof(result));
    /*console.log(!(result.state));
    console.log(!result.state);*/
    if(result==null){
        $('#txtResult').text('NULL');
        return;
    }

    /*var r1=isUndefined(null);
    var r2=isDefined(null);
    console.log('r1:'+r1);
    console.log('r2:'+r2);
    var r3=isUndefined(result.state);
    var r4=isDefined(result.state);
    console.log('r3:'+r3);
    console.log('r4:'+r4);*/

    var data=getResultData(result);
    if(data==null){
        data=result;
    }
    var json=JSON.stringify(data);
    $('#txtResult').text(json);

    if(isDefined(data.id)){//单个数据
        setId(data.id);

        var propertyList=[];
        for (var property in data){
            propertyList.push(property);
        }
        console.log('propertyList');
        console.log(propertyList);
        setComboBoxListData(["#propertyList","#sortProperty","#conditionProperty"],propertyList);
        //setComboBoxData("#propertyList",propertyList);
        selectComboValueByValue("#propertyList",'name');
        //setComboBoxData("#sortProperty",propertyList);
        selectComboValueByValue("#sortProperty",'name');
        //setComboBoxData("#conditionProperty",propertyList);
        selectComboValueByValue("#conditionProperty",'name');
    }else{

    }

    /*var columnsOut=[[]];
    //var columns=[{ field: 'checkbox', checkbox: 'true', width: 100 },{ field: 'Name', align: "center", title: "IP段名称", width: 120 }];
    columns=[];
    for (var property in result){
        columns.push({field: property, align: "center", title: property, width: 120})
    }
    columnsOut.push(columns);



    //var columns=[[{ field: 'checkbox', checkbox: 'true', width: 100 },{ field: 'Name', align: "center", title: "IP段名称", width: 120 }]];
    //var column=

    $("#dataGrid_entityList").datagrid({
        scrollbarSize: 0,
        rownumbers: true,
        fitColumns: true,
        striped: true,
        pagination: true,
        pageSize: 5,
        pageList: [5,10,15,20,25],
        fit: true,
        toolbar: "#tb",
        data:[],
        columns: columnsOut
    });*/
}
function clearResult(){
    $('#txtResult').text('');
}


$(function () {
    $('#btnTemplate').click(function () {
        console.log('test.btnTemplate')
        clearResult();
        var entityName=getEntityName();
        var dao=new EntityDao(entityName);
        dao.template(showResult);
    });
    $('#btnGetEntityList').click(function () {
        console.log('test.getEntityList')
        var dao=new Ajax();
        var result=dao.get('','');
        showResult(result);
    });
    $('#btnAdd').click(function () {
        var entityName=getEntityName();
        var data=getEntityData(entityName);
        console.log('test.add');
        var dao=new EntityDao(entityName);
        dao.add(data,showResult);
    });
    $('#btnUpdate').click(function () {
        var entityName=getEntityName();
        var data=getEntityData(entityName);
        console.log('test.update');
        var dao=new EntityDao(entityName);
        dao.update(data,showResult);
    });
    $('#btnPut').click(function () {
        var entityName=getEntityName();
        var data=getEntityData(entityName);
        console.log('test.put');
        var dao=new EntityDao(entityName);
        dao.put(data,showResult);
    });
    $('#btnPatch').click(function () {
        var entityName=getEntityName();
        var data=getEntityData(entityName);
        console.log('test.patch');
        var dao=new EntityDao(entityName);
        dao.patch(data,showResult);
    });

    $('#btnGet').click(function () {
        var id=getId();
        var entityName=getEntityName();
        console.log('test.get');
        var dao=new EntityDao(entityName);
        dao.get(id,showResult);
    });
    $('#btnDelete').click(function () {
        var id=getId();
        var entityName=getEntityName();
        console.log('test.delete');
        var dao=new EntityDao(entityName);
        dao.delete(id,showResult);
    });

    $('#btnGetAll').click(function () {
        var entityName=getEntityName();
        console.log('test.getAll');
        var dao=new EntityDao(entityName);
        dao.getAll(showResult);

        var userDao=new UserDao(entityName);
        userDao.login();
    });
    $('#btnGetPage').click(function () {
        var page=getPage();
        var size=getSize();
        var entityName=getEntityName();
        console.log('test.getPage');
        var dao=new EntityDao(entityName);
        dao.getPage(page,size,showResult);
    });

    $('#btnQueryAll').click(function () {
        var entityName=getEntityName();
        var condition=getCondition();
        console.log('test.queryAll');
        console.log(condition);
        var dao=new EntityDao(entityName);
        var result=dao.queryAll(condition,showResult);
    });

    $('#btnQueryPage').click(function () {
        var entityName=getEntityName();
        var condition=getCondition();
        console.log('queryPage');
        var dao=new EntityDao(entityName);
        dao.queryPage(condition,showResult);
    });

    $('#btnGetCount').click(function () {
        var entityName=getEntityName();
        console.log('getCount');
        var dao=new EntityDao(entityName);
        dao.getCount(showResult);
    });

    $('#btnAddMap').click(function(){
        var property=$('#conditionProperty').val();
        var op=$('#conditionOp').val();
        var value=$('#inputConditionValue').val();
        var condition=getCondition();
        condition.addMap(property,op,value);
        var json=JSON.stringify(condition);
        $('#txtCondition').text(json);
    });
    $('#btnAddSort').click(function(){
        var sort=$('#sortProperty').val();
        var sortType=$("input[name='sortType']:checked").val();
        var condition=getCondition();
        condition.addSort(sort,sortType);
        var json=JSON.stringify(condition);
        $('#txtCondition').text(json);
    });
    $('#btnClearCondition').click(function () {
        $('#txtCondition').text('');
    });
    $('#btnShowCondition').click(function () {
        var json=$('#txtCondition').text();
        alert(json);
    });
    $('#btnClearResult').click(function () {
        $('#txtResult').text('');
    })
})

