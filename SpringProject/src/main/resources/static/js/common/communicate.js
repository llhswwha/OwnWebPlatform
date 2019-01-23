console.log("communicate.js")
//动态原型方式和原型/构造混合方式的原理相似，唯一的区别就是赋予对象方法的位置
function Person(name, sex) {
    this.name = name;
    this.sex = sex;
    if (typeof this.say != "function") {
        Person.prototype.say = function () {
            alert(this.name);
        }
    }
}

//var man =new Person ("凯撒", "男");
//man.say();//凯撒

function EntityDao(name) {
    this.name=name;
    if (typeof this.add != "function") {
        EntityDao.prototype.add = function (entity) {
            //alert(this.name);
            console.log("add");
            var url=name+"/add";
            var ajax=new Ajax();
            var result=ajax.post(url,entity);
            return result;
        }
    }
    if (typeof this.update != "function") {
        EntityDao.prototype.update = function (entity) {
            //alert(this.name);
            console.log("update");
            var url=name+"/update";
            var ajax=new Ajax();
            var result=ajax.post(url,entity);
            return result;
        }
    }
    if (typeof this.put != "function") {
        EntityDao.prototype.put = function (entity) {
            //alert(this.name);
            console.log("put");
            var url=name+"/put";
            var ajax=new Ajax();
            var result=ajax.put(url,entity);
            return result;
        }
    }
    if (typeof this.patch != "function") {
        EntityDao.prototype.patch = function (entity) {
            //alert(this.name);
            console.log("patch");
            var url=name+"/patch";
            var ajax=new Ajax();
            var result=ajax.patch(url,entity);
            return result;
        }
    }
    if (typeof this.getOne != "function") {
        EntityDao.prototype.getOne = function (id) {
            //alert(this.name);
            console.log("getOne");
            var url=name+"/"+id;
            var ajax=new Ajax();
            var result=ajax.put(url,id);
            return result;
        }
    }
    if (typeof this.getOne != "function") {
        EntityDao.prototype.getOne = function (id) {
            //alert(this.name);
            console.log("getOne");
            var url=name+"/"+id;
            var ajax=new Ajax();
            var result=ajax.put(url,id);
            return result;
        }
    }
    if (typeof this.getAll != "function") {
        EntityDao.prototype.getAll = function () {
            //alert(this.name);
            console.log("getAll");
            var url=name+"/";
            var ajax=new Ajax();
            var result=ajax.get(url);
            return result;
        }
    }
    if (typeof this.getPage != "function") {
        EntityDao.prototype.getPage = function () {
            //alert(this.name);
            console.log("getAll");
            var url=name+"/";
            var ajax=new Ajax();
            var result=ajax.get(url);
            return result;
        }
    }
    if (typeof this.queryAll != "function") {
        EntityDao.prototype.queryAll = function () {
            //alert(this.name);
            console.log("getAll");
            var url=name+"/";
            var ajax=new Ajax();
            var result=ajax.get(url);
            return result;
        }
    }
}

//document.write(”<script language=javascript src=’/js/import.js’></script>”);

function Ajax() {
    if (typeof this.send != "function") {
        Ajax.prototype.send = function (type,url,data,sucess,error) {
            $.ajax({
                url:url,
                type:type,
                data:data,
                dataType:'json',
                success:sucess,
                error:error,
            })
        }
    }
    if (typeof this.get != "function") {
        Ajax.prototype.get = function (url,data) {
            $.ajax({
                url:url,
                type:'GET',
                data:data,
                dataType:'json',
                success:function (result) {
                    console.log('创建成功')
                    console.log(result);
                },
                error:function () {
                    console.log('创建失败')
                },
            })
        }
    }
    if (typeof this.post != "function") {
        Ajax.prototype.post = function (url,data) {
            $.ajax({
                url:url,
                type:'POST',
                data:data,
                dataType:'json',
                success:function (result) {
                    console.log('创建成功')
                    console.log(result);
                },
                error:function () {
                    console.log('创建失败')
                },
            })
        }
    }
    if (typeof this.delete != "function") {
        Ajax.prototype.delete = function (url,data) {
            $.ajax({
                url:url,
                type:'DELETE',
                data:data,
                dataType:'json',
                success:function (result) {
                    console.log('创建成功')
                    console.log(result);
                },
                error:function () {
                    console.log('创建失败')
                },
            })
        }
    }
    if (typeof this.put != "function") {
        Ajax.prototype.put = function (url,data) {
            $.ajax({
                url:url,
                type:'PUT',
                data:data,
                dataType:'json',
                success:function (result) {
                    console.log('创建成功')
                    console.log(result);
                },
                error:function () {
                    console.log('创建失败')
                },
            })
        }
    }
    if (typeof this.patch != "function") {
        Ajax.prototype.patch = function (url,data) {
            $.ajax({
                url:url,
                type:'PATCH',
                data:data,
                dataType:'json',
                success:function (result) {
                    console.log('创建成功')
                    console.log(result);
                },
                error:function () {
                    console.log('创建失败')
                },
            })
        }
    }
}