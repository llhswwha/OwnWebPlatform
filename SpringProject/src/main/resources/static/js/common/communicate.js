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
var man =new Person ("凯撒", "男");
man.say();//凯撒

function EntityDao(name) {
    this.name=name;
    if (typeof this.add != "function") {
        Person.prototype.add = function (entity) {
            alert(this.name);
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