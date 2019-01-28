function EntityTable(tableId,entityName) {
    this.entityName=entityName;
    this.tableId=tableId;
    this.table=$(tableId);
    this.dao=new EntityDao(entityName);
    this.page=1;
    this.size=10;
    this.condition=new Condition();
    if(typeof this.init != 'function'){
        EntityTable.prototype.init=function(columns){
            var obj=this;
            var option={
                idField:'id',
                sidePagination:'server',
                pagination: true,
                dataField:'content',
                totalField:'totalElements',
                showColumns:true,
                sortStable:true,
                onPageChange:function(number,size){
                    console.log('onPageChange:'+number+','+size);
                    obj.loadData(number,size);
                },
                customSort:function(sortName,sortOrder){
                    console.log('customSort:'+sortName+','+sortOrder);
                },
                onAll:function(name,args){
                    //console.log('onAll:'+name);
                    //console.log(args);
                },
                onSort:function(name,order){
                    console.log('onSort:'+name+','+order);
                    obj.loadData(obj.page,obj.size,name,order);
                },
                columns: columns
            };
            obj.option=option;
            var fnInit=obj.table.bootstrapTable(option)
            obj.option=option;
        }
    }
    if(typeof this.load != 'function'){
        EntityTable.prototype.load=function () {
            var obj=this;
            obj.loadData(obj.page,obj.size);
        }
    }
    if(typeof this.loadData != 'function'){
        EntityTable.prototype.loadData=function (page, size, sortName, sortOrder,map) {
            var obj=this;
            obj.table.bootstrapTable('showLoading');
            obj.page=page;
            obj.size=size;
            var condition=new Condition();
            condition.setPage(page-1,size);
            condition.addSort(sortName,sortOrder);
            if(map!=null){
                condition.map=map;
            }
            console.log(condition);
            obj.dao.queryPage(condition,function (result) {
                var data=getResultData(result);
                obj.table.bootstrapTable('load',data);
                obj.table.bootstrapTable('hideLoading');
            });
        }
    }
    if(typeof this.search != 'function'){
        EntityTable.prototype.search=function(map) {
            var obj=this;
            obj.loadData(obj.page, obj.size, null, null, map);
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
}