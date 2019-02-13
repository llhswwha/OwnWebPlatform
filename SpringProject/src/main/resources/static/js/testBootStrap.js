function init(name){
    console.log('init:'+name);
    getEntityColumns(name,function (columns) {
        entityTable=initTable(name,columns);
    })
}

function change(name){
    console.log('change:'+name);
    getEntityColumns(name,function (columns) {
        entityTable.changeEntity(name,columns);
        entityTable.load();
    })
}
function initTable(name,columns){
    console.log('initTable:'+name);
    if(name=='')return null;
    var table=new EntityTable('#table',name);
    var columns=columns;
    console.log(columns);
    table.init(columns);
    table.load();
    return table;
}

var entityName='user';
var $btnSearch = $('#btnSearch');
var $txtLoginName = $('#txtLoginName');
var $txtName = $('#txtName');
var $btnClearSearch=$('#btnClearSearch');

//var $entityModal = $('#entityModal').modal({show: false})

var entityTable=new EntityTable('','');
$(function(){
    init(entityName);
    $btnSearch.click(function () {
        var map={};
        map['name']=$txtName.val();
        map['loginName']=$txtLoginName.val();
        entityTable.search(map);
    })
    $btnClearSearch.click(function () {
        entityTable.search(null);
    })

    $('.entityClass').click(function(){
        console.log('click');
        var entityText=$(this).text();
        entityName=$(this).attr('data-name');
        $('#dropdownEntityList').text(entityText);
        $('#h2EntityText').text(entityText);
        $('.entityClass').removeClass('active');
        $(this).addClass('active');
        change(entityName);
    });

    $('#btnCreate').click(function () {
        //showModal($(this).text());
        showEntityModal({
            title:$(this).text(),
            entityName:entityName
        });
    })
    $('#btnModify').click(function () {
        var list=entityTable.getSelections();
        if(list.length==0){
            showAlertModal('请先选择一行','info');
        }
        else{
            var entity=list[0];
            showEntityModal({
                title:$(this).text(),
                entityName:entityName,
                entity:entity,
            });
        }
    })
    $('#btnDelete').click(function () {
        var list=entityTable.getSelections();
        console.log(list);
        if(list.length==0){
            showAlertModal('请先选择一行','info');
        }
        else{
            console.log(list);
            var entity=list[0];
            var id=entity.id;
            var dao=entityTable.dao;
            dao.delete(id,function (result) {
                if(result.state==0){
                    entityTable.load();
                    showAlertModal('删除成功!', 'success');
                }
                else{
                    showAlertModal('删除失败!', 'success');
                }
            })
        }
    })
});