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
    entityName=$(this).attr('data-name');
    //$('#dropdownEntityList').text(entityText);
    $('#h2EntityText').text(entityText);
    $('.entityClass').removeClass('active');
    $(this).addClass('active');
    change(entityName);
}

//动态初始化导航菜单
function initMenu(){
    var menuDao=new MenuDao();
    var userId=0;
    menuDao.getRoot(userId,function(rootMenu){
        //console.log('Menu-----------------------------------');
        //console.log(rootMenu);
        //console.log(rootMenu.items);
        var $navbar=$('#navbarMenus');//导航栏的<ul
        for (var i in rootMenu.items){
            var menu=rootMenu.items[i];
            //console.log(menu);
            if(menu.items){
                var id='menu_'+menu.code;
                $navbar.append('<li class="nav-item dropdown" id='+id+'><a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">'+menu.name+'</a><div class="dropdown-menu" aria-labelledby="navbarDropdown"></div></li>');
                var $li=$('#'+id);
                var $div=$li.find('.dropdown-menu');
                for(var j in menu.items){
                    var subMenu=menu.items[j];
                    $div.append('<a class="dropdown-item entityClass" data-name="'+subMenu.code+'" href="#'+subMenu.code+'">'+subMenu.name+'</a>');
                }
            }
            else{
                var li=$navbar.append('<li class="nav-item"><a class="nav-link entityClass" data-name="'+menu.code+'" href="#'+menu.code+'">'+menu.name+'</a></li>');
            }
            /*
            <li class="nav-item"><a class="nav-link" href="#">监控管理</a></li>
             */
        }

        $('.entityClass').click(clickMenu);
    });

}

$(function(){
    initMenu();
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
    $('#btnTestAlert').click(function () {
        showAlertModal('测试提示框','info');
    })
    $('#btnTestConfirm').click(function () {
        showAlertModal('测试提示框','success');
    })

});