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

function clickMenu(){
    console.log('click');
    var entityText=$(this).text();
    var menuId=$(this).attr('menu-id');
    var menu=getMenu(menuId);
    var entityName=menu.code;
    //$('#dropdownEntityList').text(entityText);
    //$('#h2EntityText').text(entityText);
    $('.entityClass').removeClass('active');
    $(this).addClass('active');
    change(entityName);
    $('.breadcrumb').empty();
    //<li class="breadcrumb-item"><a href="#">Home</a></li>
    $('.breadcrumb').append('<li class="breadcrumb-item"><a href="#">首页</a></li>');
    if(menu.parent!=null && typeof(menu.parent)!='undefined'){
        $('.breadcrumb').append('<li class="breadcrumb-item"><a class="entityClass" menu-id="'+menu.parent.id+'" href="#'+menu.parent.code+'">'+menu.parent.name+'</a></li>');
    }
    $('.breadcrumb').append('<li class="breadcrumb-item"><a class="entityClass" menu-id="'+menu.id+'" href="#'+menu.code+'">'+menu.name+'</a></li>');
    $('.breadcrumb-item .entityClass').click(clickMenu);//菜单点击事件
}

$(function(){
    initNavMenu('#navbarMenus',clickMenu);//动态加载菜单
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

    //$('.entityClass').click(clickMenu);

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
        /*var list=entityTable.getSelections();
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
        }*/
        entityTable.deleteSelections();
    })
    $('#btnTestAlert').click(function () {
        showAlertModal('测试提示框','info');
    })
    $('#btnTestConfirm').click(function () {
        showConfirmModal('确认','是否删除',function () {
            alert('yes');
        });
    })

});