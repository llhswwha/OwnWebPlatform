function init(name) {
    getEntityColumns(name);
    return initTable(name);
}
function getEntityColumns(name){
    console.log('getEntityColumns:'+name);
    // jQuery.getJSON( url [, data ] [, success ] )
    var jsonFile="entity/"+name+".json";
    console.log('getJSON1');
    $.getJSON(jsonFile, "", function(data) {
        console.log('getJSON2');//这里进不来 怎么回事？
        console.log(data);
    });
}
function initTable(name){
    console.log('initTable:'+name);
    if(name=='')return null;
    var table=new EntityTable('#table',name);
    var columns=[{
        field: 'id',
        title: 'ID',
        sortable:true
    }, {
        field: 'name',
        title: '昵称',
        sortable:true
    }, {
        field: 'loginName',
        title: '登录名',
        sortable:true
    }, {
        field: 'password',
        title: '密码',
        sortable:true
    }];
    table.init(columns);
    table.load();
    return table;
}

var entityName='user';
var $btnSearch = $('#btnSearch');
var $txtLoginName = $('#txtLoginName');
var $txtName = $('#txtName');
var $btnClearSearch=$('#btnClearSearch');
var $modal = $('#modal').modal({show: false})
var entityTable;
$(function(){
    entityTable=init(entityName);
    $btnSearch.click(function () {
        var map={};
        map['name']=$txtName.val();
        map['loginName']=$txtLoginName.val();
        table.search(map);
    })
    $btnClearSearch.click(function () {
        table.search(null);
    })

    $('.entityClass').click(function(){
        console.log('click');
        var entityText=$(this).text();
        entityName=$(this).attr('data-name');
        $('#dropdownEntityList').text(entityText);
        $('#h2EntityText').text(entityText);
        $('.entityClass').removeClass('active');
        $(this).addClass('active');

        entityTable=initTable(entityName);
    });

    $('#btnCreate').click(function () {
        showModal($(this).text())
    })

    $modal.find('.submit').click(function () {
        var row = {}

        $modal.find('input[name]').each(function () {
            row[$(this).attr('name')] = $(this).val()
        })

        var dao=new EntityDao(entityName);
        dao.add(row,function (result) {
            if(result.state==0){
                $modal.modal('hide')
                //$table.bootstrapTable('refresh')
                showAlert(($modal.data('id') ? 'Update' : 'Create') + ' item successful!', 'success')
            }
            else{
                $modal.modal('hide')
                showAlert(($modal.data('id') ? 'Update' : 'Create') + ' item error!', 'danger')
            }
        })
    })
});

function showModal(title, row_) {
    var row = row_ || {
        id: '',
        name: '',
        stargazers_count: 0,
        forks_count: 0,
        description: ''
    } // default row value
    $modal.find('.modal-content').load("entity/user.html");
    $modal.data('id', row.id);
    $modal.find('.modal-title').text(title);
    for (var name in row) {
        if (row[name]) {
            $modal.find('input[name="' + name + '"]').val(row[name]);
        }
    }
    $modal.modal('show');
}

function showAlert(title, type) {
    $alert.attr('class', 'alert alert-' + type || 'success')
        .html('<i class="glyphicon glyphicon-check"></i> ' + title).show()
    setTimeout(function () {
        $alert.hide()
    }, 3000)
}