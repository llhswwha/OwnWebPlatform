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

function showEntityModal(option) {
    console.log('function showEntityModal()');
    //console.log(option);

    var title=option.title;
    var entity=option.entity;
    var entityName=option.entityName;
    var modalContainerId='#modalContainer';
    var modalId='#entityModal';
    var validateEntity=option.validateEntity;//验证输入的回调函数

    //console.log('showModal:'+title);
    var isModify=typeof(entity) != 'undefined';

    //console.log("showModal:"+isModify);
    //console.log(entity);
    //console.log(typeof(entity));
    var row={};
    if(isModify){
        row=entity;
    }
    //console.log(row);
    var htmlFile="entity/"+entityName+".html";
    //console.log('htmlFile:'+htmlFile);
    var $modalContainer=$(modalContainerId);
    //console.log($modalContainer);
    $modalContainer.load(htmlFile,function () {
        console.log('load');
        var $entityModal = $(modalId).modal({show: false});
        //console.log($entityModal);
        var inputControls=$entityModal.find('input[name]');
        if(isModify) { //设置属性到界面上
            inputControls.each(function () {
                var key = $(this).attr('name');
                var parts=key.split('.');
                var value = null;
                if(parts.length==1){
                    value = row[parts[0]];
                }else if(parts.length==2){
                    value = row[parts[0]][parts[1]];
                }else{
                    value = row[key];
                }
                $(this).val(value);//设置
                //console.log('set:' + key + ',' + value);
            })
        }

        //点击确定按钮
        $entityModal.find('.submit').click(function () {
            inputControls.each(function () { //从界面上获取属性值
                var key=$(this).attr('name');
                var value=$(this).val();
                row[key] = value;//获取
                //console.log('get:'+key+','+value);
            })
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
                    obj.loadData(obj.page, obj.size, name, order);
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
        EntityTable.prototype.loadData=function (page, size, sortName, sortOrder,map) {
            var obj=this;
            obj.table.bootstrapTable('showLoading');
            obj.page=page;
            obj.size=size;
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
        EntityTable.prototype.search=function(map) {
            var obj=this;
            obj.loadData(obj.page, obj.size, 'id', 'desc', map);
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
}