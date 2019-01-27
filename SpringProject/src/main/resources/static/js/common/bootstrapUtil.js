function EntityTable(entityName,tableId) {
    this.entityName=entityName;
    this.tableId=tableId;
    this.table=$(tableId);
    this.dao=new EntityDao(entityName);
    this.page=1;
    this.size=10;
    if(typeof this.init != 'function'){
        EntityTable.prototype.init=function(columns){
            var obj=this;
            obj.table.bootstrapTable({
                idField:'id',
                sidePagination:'server',
                pagination: true,
                dataField:'content',
                totalField:'totalElements',
                showColumns:true,
                height:460,
                sortStable:true,
                onPageChange:function(number,size){
                    console.log('onPageChange:'+number+','+size);
                    obj.loadPage(number,size);
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
                    obj.loadPage(obj.page,obj.size,name,order);
                },
                columns: columns
            })
        }
    }
    if(typeof this.load != 'function'){
        EntityTable.prototype.load=function () {
            var obj=this;
            obj.loadPage(obj.page,obj.size);
        }
    }
    
    if(typeof this.loadPage != 'function'){
        EntityTable.prototype.loadPage=function (page,size,sortName,sortOrder) {
            var obj=this;
            obj.page=page;
            obj.size=size;
            var condition=new Condition();
            condition.setPage(page-1,size);
            condition.addSort(sortName,sortOrder);
            console.log(condition);
            obj.dao.queryPage(condition,function (result) {
                var data=getResultData(result);
                obj.table.bootstrapTable('load',data);
            });
        }
    }
    //if(typeof this.)
}