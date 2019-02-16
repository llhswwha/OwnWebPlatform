var menuList=[];
function getMenu(id){
    return menuList.find(function(i){
        return i.id==id;
    })
}
//动态初始化导航菜单
function initNavMenu(navulId,userId,menuClick){
    var menuDao=new MenuDao();
    //var userId=0;
    menuList=[];
    menuDao.getRoot(userId,function(rootMenu){
        //console.log('Menu-----------------------------------');
        //console.log(rootMenu);
        //console.log(rootMenu.items);
        //var $navbar=$('#navbarMenus');//导航栏的<ul
        var $ul=$(navulId);
        $ul.empty();
        for (var i in rootMenu.items){
            var menu=rootMenu.items[i];
            menuList.push(menu);
            //console.log(menu);
            if(menu.items){
                var id='menu_'+menu.code;
                $ul.append('<li class="nav-item dropdown" id='+id+'><a class="nav-link dropdown-toggle" href="#" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">'+menu.name+'</a><div class="dropdown-menu" aria-labelledby="navbarDropdown"></div></li>');
                var $li=$('#'+id);
                var $div=$li.find('.dropdown-menu');
                for(var j in menu.items){
                    var subMenu=menu.items[j];
                    subMenu.parent=menu;
                    menuList.push(subMenu);
                    $div.append('<a class="dropdown-item entityClass" menu-id="'+subMenu.id+'" href="#'+subMenu.code+'">'+subMenu.name+'</a>');
                }
            }
            else{
                if(i==0){
                    var li=$ul.append('<li class="nav-item active"><a class="nav-link entityClass" menu-id="'+menu.id+'" href="#'+menu.code+'">'+menu.name+'</a></li>');
                }
                else{
                    var li=$ul.append('<li class="nav-item"><a class="nav-link entityClass" menu-id="'+menu.id+'" href="#'+menu.code+'">'+menu.name+'</a></li>');
                }
            }
            /*
            <li class="nav-item"><a class="nav-link" href="#">监控管理</a></li>
             */
        }

        $('.entityClass').click(menuClick);//菜单点击事件
    });
}

var $confirmModal=null;
//显示提示框
function showConfirmModal(title,msg, callback) {
    if ($confirmModal == null) {
        console.log("showAlertModal 1");//第一次要从文件中加载弹窗
        var modalContainerId='confirmModalContainer';//容器id
        var pagePath='pages/confirmModal.html';//弹窗页面文件
        var modalId='#confirmModal';//弹窗id
        $('body').append('<div id="'+modalContainerId+'"></div>');//添加容器元素
        $('#'+modalContainerId).load(pagePath, function () {
            $confirmModal = $(modalId).modal({show: false})
            showConfirm(title,msg, callback);
        });
    } else {
        console.log("showAlertModal 2");//后续加载都是在已经页面上了的
        showConfirm(title,msg, callback);
    }
}

//显示提示内容
function showConfirm(title,msg, callback) {
    $confirmModal.find('.modal-title').text(title);
    $confirmModal.find('.modal-body').text(msg)
    $confirmModal.find('.btn-yes').click(function(){
        $confirmModal.modal('hide');
        if(callback!=null){
            callback();
        }
    })
    $confirmModal.modal('show');
}

var $alertModal=null;
//显示提示框
function showAlertModal(msg, type) {
    if ($alertModal == null) {
        console.log("showAlertModal 1");//第一次要从文件中加载弹窗
        var modalContainerId='alertModalContainer';//容器id
        var pagePath='pages/alertModal.html';//弹窗页面文件
        var modalId='#alertModal';//弹窗id
        $('body').append('<div id="'+modalContainerId+'"></div>');//添加容器元素
        $('#'+modalContainerId).load(pagePath, function () {
            $alertModal = $(modalId).modal({show: false})
            showAlert(msg, type);
        });
    } else {
        console.log("showAlertModal 2");//后续加载都是在已经页面上了的
        showAlert(msg, type);
    }
}

//显示提示内容
function showAlert(msg, type) {
    $alertModal.find('#title').attr('class', 'alert alert-' + type || 'success');
    $alertModal.find('.modal-body').text(msg)
    $alertModal.modal('show');
    setTimeout(function () {
        $alertModal.modal('hide');
    }, 3000)
}

//将属性设置到界面上
function setModelControls($entityModal,entity) {
    var $inputControls = $entityModal.find('input[name]');
    var $selectControls = $entityModal.find('select[name]');
    $inputControls.each(function () {
        var key = $(this).attr('name');
        var value = getEntityValue(entity, key);
        $(this).val(value);//设置
        console.log('set input1:' + key + ',' + value);
    })
    $selectControls.each(function () {
        var key = $(this).attr('name');
        var value = getEntityValue(entity, key);
        console.log('set select1:' + key + ',' + value);
        console.log(value);
        console.log($(this)[0]);
        if (value != null) {
            console.log('id:' + value.id);
            $(this).val(value.id);//设置
        } else {
            console.log('value == null');
        }
    })
}

function getEntityValue(entity,name){
    var parts=name.split('.');
    var value = null;
    if(parts.length==1){
        value = entity[parts[0]];
    }else if(parts.length==2){
        if(entity[parts[0]]!=null){ //.city.name时可能city是null
            value = entity[parts[0]][parts[1]];
        }
    }else{
        value = entity[key];
    }
    return value;
}

//从界面上获取数据
function getModelControls($entityModal,entity) {
    var $inputControls = $entityModal.find('input[name]');
    var $selectControls = $entityModal.find('select[name]');
    //从界面上获取属性值
    //1.input设置简单属性
    $inputControls.each(function () {
        var key=$(this).attr('name');
        var value=$(this).val();
        entity[key] = value;//设置属性值
        //console.log('get:'+key+','+value);
    })
    //2.select设置关联属性
    $selectControls.each(function(){
        var key=$(this).attr('name');
        var value=$(this).val();
        var text=$(this).find("option:selected").text();
        var obj={};//封装成对象
        obj.id=value;
        obj.name=text;
        entity[key] = obj;//设置属性值
    })
}

function showEntityModal(option) {
    console.log('function showEntityModal()');
    console.log(option);
    var title=option.title;
    var entity=option.entity;
    var entityName=option.entityName;
    var modalContainerId='#modalContainer';
    var modalId='#entityModal';
    var validateEntity=option.validateEntity;//验证输入的回调函数
    var isModify=typeof(entity) != 'undefined';
    var row={};
    if(isModify){
        row=entity;
    }
    var htmlFile="entity/"+entityName+".html";
    //console.log('htmlFile:'+htmlFile);
    var $modalContainer=$(modalContainerId);
    //console.log($modalContainer);
    $modalContainer.load(htmlFile,function () {
        console.log('load htmlFile:'+htmlFile);
        var $entityModal = $(modalId).modal({show: false});
        if(isModify) {
            //设置下拉框的值时需要事先加载下拉框的数据 loadSelectListData就是用于这个作用的 定义在具体的html子页面中 参考:user.html
            if(typeof(loadSelectListData) == 'function'){
                loadSelectListData(function () {
                    setModelControls($entityModal,row);//设置属性到界面上
                })
            }else{
                setModelControls($entityModal,row);//设置属性到界面上
            }
        }
        //点击确定按钮
        $entityModal.find('.submit').click(function () {
            getModelControls($entityModal,row);//从界面上获取数据
            console.log(row);
            //验证实体类属性是否填写正确，比如：判断是否为空，输入内容是否合法。
            if(validateEntity!=null){
                var validateResult=validateEntity(row);
                if(validateResult==false)return;//如果为false，这不做后续处理
            }
            var dao=entityTable.dao;
            dao.save(row,function (result) {
                if(result.state==0){
                    $entityModal.modal('hide')
                    entityTable.load();
                    showAlertModal(($entityModal.data('id') ? '修改' : '创建') + '成功!', 'success')
                }
                else{
                    $entityModal.modal('hide')
                    showAlertModal(($entityModal.data('id') ? '修改' : '创建') + '失败!', 'danger')
                }
            })
        })
        $entityModal.data('id', row.id);
        $entityModal.find('#modal-title').text(title);
        for (var name in row) {
            if (row[name]) {
                $entityModal.find('input[name="' + name + '"]').val(row[name]);
            }
        }
        $entityModal.modal('show');
    })
}

var entityColumns={};
function getEntityColumns(entityName,callback) {
    console.log('getEntityColumns:' + entityName);
    console.log(entityColumns[entityName]);
    if(entityColumns[entityName]==null){
        var jsonFile = "entity/" + entityName + ".json";
        $.getJSON(jsonFile, "", function (data) { //从文件读取
            entityColumns[entityName]=data;//存到内存中
            if (callback != null) {
                callback(data);
            }
        });
    }else{
        var columns=entityColumns[entityName]; //从内存读取
        if (callback != null) {
            callback(columns);
        }
    }
}

function EntityTable(tableId,entityName) {
    console.log("EntityTable.ctor:"+tableId+","+entityName);
    this.entityName=entityName;
    this.tableId=tableId;
    this.table=$(tableId);
    //this.dao=new EntityDao(entityName);
    this.dao=getDao(entityName);
    this.page=1;
    this.size=10;
    this.condition=new Condition();
    if(typeof this.init != 'function') {
        EntityTable.prototype.init = function (columns) {
            var obj = this;
            var option = {
                idField: 'id',
                sidePagination: 'server',
                pagination: true,
                dataField: 'content',
                totalField: 'totalElements',
                showColumns: true,
                sortStable: true,
                onPageChange: function (number, size) {
                    console.log('onPageChange:' + number + ',' + size);
                    obj.loadData(number, size);
                },
                customSort: function (sortName, sortOrder) {
                    console.log('customSort:' + sortName + ',' + sortOrder);
                },
                onAll: function (name, args) {
                    //console.log('onAll:'+name);
                    //console.log(args);
                },
                onSort: function (name, order) {
                    console.log('onSort:' + name + ',' + order);
                    obj.loadData(obj.page, obj.size, name, order,obj.map,obj.entity);
                },
                columns: columns
            };
            obj.table.bootstrapTable(option)
            //obj.table.bootstrapTable('refreshOptions', obj.option);//表格发生变化
            obj.option = option;
        }
    }
    if(typeof this.load != 'function'){
        EntityTable.prototype.load=function () {
            var obj=this;
            obj.loadData(obj.page,obj.size,'id','desc');
        }
    }
    if(typeof this.loadData != 'function'){
        EntityTable.prototype.loadData=function (page, size, sortName, sortOrder,map,entity) {
            var obj=this;
            obj.table.bootstrapTable('showLoading');
            obj.page=page;
            obj.size=size;
            obj.map=map;
            obj.entity=entity;
            if(typeof(sortName) == 'undefined'){
                sortName=obj.sortName;
            }else{
                obj.sortName=sortName;
            }
            if(typeof(sortOrder) == 'undefined'){
                sortOrder=obj.sortOrder;
            }else{
                obj.sortOrder=sortOrder;
            }
            var condition=new Condition();
            condition.setPage(page-1,size);
            condition.addSort(sortName,sortOrder);
            if(entity!=null){
                console.log("set condition entity !!!!!!!!!!!!");
                console.log(entity);
                condition.entity=entity;
            }

            if(map!=null){
                condition.map=map;
            }
            console.log(condition);
            obj.dao.queryPage(condition,function (result) {
                var data=getResultData(result);
                if(data!=null){
                    obj.table.bootstrapTable('load',data);
                }
                obj.table.bootstrapTable('hideLoading');
            });
        }
    }
    if(typeof this.search != 'function'){
        EntityTable.prototype.search=function(map, entity) {
            var obj=this;
            obj.loadData(obj.page, obj.size, 'id', 'desc', map,entity);
        }
    }
    if(typeof this.setOption!='function'){
        EntityTable.prototype.setOption=function (key, value) {
            console.log(' --> EntityTable.setOption:'+key+','+value);
            var obj=this;
            obj.option[key]=value;
            obj.table.bootstrapTable('refreshOptions',obj.option);//刷新一下
        }
    }
    if(typeof this.changeEntity!='function'){
        EntityTable.prototype.changeEntity=function (entityName, columns) {
            console.log(' --> EntityTable.changeEntity:'+entityName);
            var obj=this;
            obj.entityName=entityName;
            //obj.dao=new EntityDao(entityName);
            obj.dao=getDao(entityName);
            obj.setOption('columns',columns);//刷新一下
        }
    }
    if(typeof this.getSelections!='function'){
        EntityTable.prototype.getSelections=function () {
            console.log(' --> EntityTable.getSelections');
            var obj=this;
            var list= obj.table.bootstrapTable('getSelections');//刷新一下
            console.log(list);
            return list;
        }
    }
    if(typeof this.deleteSelections!='function'){
        EntityTable.prototype.deleteSelections=function () {
            console.log(' --> EntityTable.deleteSelections');
            var list=entityTable.getSelections();
            console.log(list);
            if(list.length==0){
                showAlertModal('请先选择一行','info');
            }
            else{
                console.log(list);
                var entity=list[0];
                var msg='是否确定删除？';
                showConfirmModal("确认",msg,function(){
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
                })
            }
        }
    }
}