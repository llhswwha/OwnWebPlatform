console.log('communicate.js')

function isDefined(obj) {
    console.log('isDefined');
    console.log(typeof (obj));
    //if (obj == null) return true;
    if (typeof (obj) == "undefined") {
        return false;
    } else {
        return true;
    }
}

function ClassObjectParse() {

}

String.prototype.startWith=function(str){
    var reg=new RegExp('^'+str);
    return reg.test(this);
}

String.prototype.endWith=function(str){
    var reg=new RegExp(str+'$');
    return reg.test(this);
}

function getResultData(result) {
    var data=result;
    if(isDefined(result.state)){
        if(result.state==0){
            alert('成功!');
        }
        else{
            alert(result.describe);
        }
        console.log('data');
        console.log(result.data);
        if(isDefined(result.data)) {//data is undefined User属性中有个state并且等于0
            data=result.data;
        }
        console.log(data);
    }
    return data;
}

function Condition(page,size) {
    if(page==null){
        this.page=0;
    }else{
        this.page=page;
    }
    if(size==null){
        this.size=10;
    }else{
        this.size=size;
    }
    this.map=new Object();
    this.sort=new Object();
    if (typeof this.setPropertyByJson != 'function') {
        Condition.prototype.setPropertyByJson = function (json) {
            console.log('condition.setPropertyByJson');
            if (typeof (json) == 'undefined') return;
            var obj = JSON.parse(json);
            this.page = obj.page;
            this.size = obj.size;
            this.map = obj.map;
            this.sort = obj.sort;

        }
    }
    if (typeof this.setPage != 'function') {
        Condition.prototype.setPage = function (page,size) {
            console.log('condition.setPage');
            if (typeof (page) == 'undefined') return;
            this.page=page;
            this.size=size;
        }
    }
    if (typeof this.addMap != 'function') {
        Condition.prototype.addMap = function (column,op,value) {
            console.log('condition.addMap');
            if (typeof (column) == 'undefined') return;
            var key=column+'-'+op;
            this.map[key]=value;
        }
    }
    if (typeof this.addSort != 'function') {
        Condition.prototype.addSort = function (column,sortType) {
            console.log('condition.addSort');
            if (typeof (column) == 'undefined') return;
            this.sort[column]=sortType;
        }
    }
    if (typeof this.asc != 'function') {
        Condition.prototype.asc = function (column) {
            console.log('condition.asc');
            if (typeof (column) == 'undefined') return;
            this.sort[column]='asc';
        }
    }
    if (typeof this.desc != 'function') {
        Condition.prototype.desc = function (column) {
            console.log('condition.desc');
            if (typeof (column) == 'undefined') return;
            this.sort[column]='desc';
        }
    }
}

function EntityDao(name) {
    this.ajax=new Ajax();
    this.name=name;

    //console.log('EntityDao:'+name);
    if (typeof this.template != 'function') {
        EntityDao.prototype.template = function (callBack) {
            console.log('dao.template');
            var url=this.name+'/template';//这里必须是this.name不能用name
            this.ajax.get(url,null,callBack);
        }
    }
    if (typeof this.add != 'function') {
        EntityDao.prototype.add = function (entity,callBack) {
            console.log('dao.add');
            var url=this.name+'/add';
            this.ajax.post(url,entity,callBack);
        }
    }



    if (typeof this.update != 'function') {
        EntityDao.prototype.update = function (entity,callBack) {
            console.log('dao.update');
            console.log(entity);
            var url=this.name+'/update';
            this.ajax.post(url,entity,callBack);
        }
    }
    if (typeof this.put != 'function') {
        EntityDao.prototype.put = function (entity,callBack) {
            console.log('dao.put');
            var url=this.name+'/put';
            this.ajax.put(url,entity,callBack);
        }
    }
    if (typeof this.patch != 'function') {
        EntityDao.prototype.patch = function (entity,callBack) {
            console.log('dao.patch');
            var url=this.name+'/patch';
            this.ajax.patch(url,entity,callBack);
        }
    }
    if (typeof this.get != 'function') {
        EntityDao.prototype.get = function (id,callBack) {
            console.log('dao.get:'+id);
            var url=this.name+'/'+id;
            this.ajax.get(url,null,callBack);
        }
    }
    if (typeof this.delete != 'function') {
        EntityDao.prototype.delete = function (id,callBack) {
            console.log('dao.delete:'+id);
            var url=this.name+'/'+id;
            this.ajax.delete(url,null,callBack);
        }
    }
    if (typeof this.getAll != 'function') {
        EntityDao.prototype.getAll = function (callBack) {
            console.log('dao.getAll');
            //console.log(callBack);
            var url=this.name+'/list';
            this.ajax.get(url,null,callBack);
        }
    }
    if (typeof this.getPage != 'function') {
        EntityDao.prototype.getPage = function (page,size,callBack) {
            console.log('dao.getPage');
            var url=this.name+'/page';
            var condition=new Object();
            condition.page=page;
            condition.size=size;
            this.ajax.get(url,condition,callBack);
        }
    }
    if (typeof this.queryAll != 'function') {
        EntityDao.prototype.queryAll = function (condition,callBack) {
            console.log('dao.queryAll');
            var url=this.name+'/queryAll';
            condition=JSON.parse(JSON.stringify(condition));//由于Condition类有方法,而jquery的ajax序列化时会把方法一起序列化，导致出问题，这里转换成一般对象
            this.ajax.post(url,condition,callBack);
        }
    }
    if (typeof this.queryPage != 'function') {
        EntityDao.prototype.queryPage = function (condition,callBack) {
            console.log('dao.queryPage');
            var url=this.name+'/queryPage';

            condition=JSON.parse(JSON.stringify(condition));//由于Condition类有方法,而jquery的ajax序列化时会把方法一起序列化，导致出问题，这里转换成一般对象
            this.ajax.post(url,condition,callBack);
        }
    }
    if (typeof this.getCount != 'function') {
        EntityDao.prototype.getCount = function (callBack) {
            console.log('dao.getCount');
            var url=this.name+'/count';
            this.ajax.get(url,null,callBack);
        }
    }
}

function UserDao() {
    if (typeof this.login != 'function') {
        UserDao.prototype.login = function (user,pass,callBack) {
            console.log('userDao.login');
            //var url=this.name+'/';
            //this.ajax.post(url,condition,callBack);
        }
    }

}
UserDao.prototype = new EntityDao();


//document.write(”<script language=javascript src=’/js/import.js’></script>”);

function Ajax() {
    if (typeof this.send != 'function') {
        Ajax.prototype.send = function (type,url,data,sucess,error) {
            $.ajax({
                url:url,
                type:type,
                data:data,
                dataType:'json',
                async:false,
                success:sucess,
                error:error,
            })
        }
    }
    if (typeof this.get != 'function') {
        Ajax.prototype.get = function (url,data,callBack) {
            //console.log('ajax.get:'+url);
            //console.log(data);
            //console.log(callBack);
            $.ajax({
                url:url,
                type:'GET',
                data:data,
                dataType:'json',
                success:function (result) {
                    console.log('成功')
                    console.log(result);
                    console.log(callBack);
                    if(callBack!=null)
                        callBack(result);
                },
                error:function () {
                    console.log('失败')
                    if(callBack!=null)
                        callBack(null);
                }
            })
        }
    }
    if (typeof this.post != 'function') {
        Ajax.prototype.post = function (url,data,callBack) {
            console.log('ajax.post:'+url);
            console.log(data);
            $.ajax({
                url:url,
                type:'POST',
                data:data,
                dataType:'json',
                success:function (result) {
                    console.log('成功')
                    console.log(result);
                    if(callBack!=null)
                        callBack(result);
                },
                error:function () {
                    console.log('失败')
                    if(callBack!=null)
                        callBack(null);
                }
            })
        }
    }

    if (typeof this.post2 != 'function') {
        Ajax.prototype.post2 = function (url,data,callBack) {
            console.log('ajax.post:'+url);
            console.log(data);
            $.ajax({
                url:url,
                type:'POST',
                dataType:'json',
                contentType: 'application/json',//这种方式后端无法用实体类直接收到，还要再研究一下
                data:JSON.stringify(data),
                success:function (result) {
                    console.log('成功')
                    console.log(result);
                    if(callBack!=null)
                        callBack(result);
                },
                error:function () {
                    console.log('失败')
                    if(callBack!=null)
                        callBack(null);
                }
            })
        }
    }

    if (typeof this.delete != 'function') {
        Ajax.prototype.delete = function (url,data,callBack) {
            $.ajax({
                url:url,
                type:'DELETE',
                data:data,
                dataType:'json',
                success:function (result) {
                    console.log('成功')
                    console.log(result);
                    if(callBack!=null)
                        callBack(result);
                },
                error:function () {
                    console.log('失败')
                    if(callBack!=null)
                        callBack(null);
                }
            })
        }
    }
    if (typeof this.put != 'function') {
        Ajax.prototype.put = function (url,data,callBack) {
            $.ajax({
                url:url,
                type:'PUT',
                data:data,
                dataType:'json',
                success:function (result) {
                    console.log('成功')
                    console.log(result);
                    if(callBack!=null)
                        callBack(result);
                },
                error:function () {
                    console.log('失败')
                    if(callBack!=null)
                        callBack(null);
                }
            })
        }
    }
    if (typeof this.patch != 'function') {
        Ajax.prototype.patch = function (url,data,callBack) {
            $.ajax({
                url:url,
                type:'PATCH',
                data:data,
                dataType:'json',
                success:function (result) {
                    console.log('成功')
                    console.log(result);
                    if(callBack!=null)
                        callBack(result);
                },
                error:function () {
                    console.log('失败')
                    if(callBack!=null)
                        callBack(null);
                }
            })
        }
    }
}