/**
 * commmonUI.js
 *
 * 公用的JS方法，一些界面显示（主要是easyui)的方法。
 *
 * @author  蔡万伟
 *
 */

/**
 * 居中显示窗口
 * 正则表达式内容替换 \$\((.*)\)\.dialog\("open"\) -> openDialogCenter($1) vs2015
 *
 * @param    {dlgId}  text   窗口Id，带#
 * @date     2018-06-24
 * @author   蔡万伟
 */
function openDialogCenter(dlgId) {
    $(dlgId).dialog("open");
    $(dlgId).window('center');//窗口居中显示
}

/**
 * 用a封装一下字符串，用于达到tooltip显示的效果
 *
 * @param    {String}  text   内容
 * @date     2018-06-23
 * @author   蔡万伟
 */
function getToolTipAddress(text) {
    return '<a href="javascript:;"  title="' + text + '" class="easyui-tooltip" style="text-decoration:none;color:#000;">' + text + '</a>';
}

/**
 * 获取鼠标坐标
 *
 * @param    {Event}  ev   鼠标事件参数
 *
 * @author   蔡万伟
 */
function getMousePosition(ev) {
    ev = ev || window.event;
    if (ev.pageX || ev.pageY) {
        return { x: ev.pageX, y: ev.pageY };
    }
    return {
        x: ev.clientX + document.body.scrollLeft - document.body.clientLeft,
        y: ev.clientY + document.body.scrollTop - document.body.clientTop
    };
}

/**
 * 将（前端分页的）表格的所有列设置为可排序的（通过添加sortable: true）
 *
 * @param    {String}  gridId   表格Id
 * @param    {String}  rows   数据行
 * @param    {String}  initPageSize   表格初始时的pageSize，注意！现在这个参数没用了！
 *
 * @date     2018-06-04
 * @author   蔡万伟
 */
function setDataGridSortable(gridId, rows, initPageSize) {
    setDataGridSortable$($(gridId), rows, initPageSize);
}

/**
 * 将（前端分页的）表格的所有列设置为可排序的（通过添加sortable: true）
 *
 * @param    {String}  gridId   表格Id
 * @param    {String}  rows   数据行
 * @param    {String}  initPageSize   表格初始时的pageSize，注意！现在这个参数没用了！
 *
 * @date     2018-06-04
 * @author   蔡万伟
 */
function setDataGridSortable$($grid, rows, initPageSize) {
    //console.log("setDataGridSortable");
    //1.获取所有列
    //2.给列添加sortable: true
    //3.给grid添加onSortColumn响应方法
    var options = $grid.datagrid('options');
    //console.log(options);

    var isPagination = true;
    if (typeof (options.pagination) == 'undefined' || options.pagination == false) {
        isPagination = false;
    }
    var columns = options.columns;
    //console.log(columns);
    for (var i in columns) {
        var innerColumns = columns[i];
        for (var j in innerColumns) {
            var column = innerColumns[j];
            column.sortable = true;
            //console.log(column);
        }
    }
    options.onSortColumn = function (sort, order) {
        sortDataGridByColumn(rows, sort, order);
        if (isPagination) {

            var pager = $grid.datagrid("getPager");
            var pageOptions = pager.pagination('options');
            var pageSize = pageOptions.pageSize;
            $grid.datagrid("loadData", rows.slice(0, pageSize));
            pager.pagination('refresh', {
                total: rows.length,
                pageNumber: 1
            });
        } else {
            $grid.datagrid("loadData", rows.slice(0, 10000));
        }
    }
}

/**
 * 判断某字符串是否为数字（使用正则表达式）
 *
 * @param    {String}  value   值
 *
 * @date     2018-06-04
 * @author   蔡万伟
 */
function checkNumber(value) {
    var reg = /^[0-9]+.?[0-9]*$/;
    if (reg.test(value)) {
        return true;
    }
    return false;
}

/**
 * 判断列表中是否都为数字（使用正则表达式）
 *
 * @param    {Array}  rows      值
 * @param    {String}  property   值
 *
 * @date     2018-06-04
 * @author   蔡万伟
 */
function checkNumberForRows(list,property) {
    for (var i in list) {
        var row = list[i];
        var value = row[property];
        if (!checkNumber(value)) {
            return false;
        }
    }
    return true;
}

/**
 * 对两个值进行排序，是数字按数字排序，不是则按字符串排序
 *
 * @param    {String}  a1   值1
 * @param    {String}  b1   值2
 *
 * @date     2018-06-04
 * @author   蔡万伟
 */
function sortFunc(a1, b1, isNumber) {

    if (isNumber) {
        return a1 - b1;
    }
    if (a1 == b1) {
        return 0;
    }
    else if (a1 > b1) {
        return 1;
    }
    else {
        return -1;
    }
}

/**
 * 对列表数据按某一列的属性排序
 *
 * @param    {Array}  localData   列表数据
 * @param    {String}  sort        排序属性
 * @param    {String}  order       排序规则 asc/desc
 *
 * @date     2018-06-04
 * @author   蔡万伟
 */
function sortDataGridByColumn(list, sort, order) {
    //alert("sort:" + sort + ",order：" + order + "");
    var isNumber = checkNumberForRows(list, sort);

    if (order == 'asc') {
        list.sort(function (a, b) {
            var a1 = a[sort];
            var b1 = b[sort];
            return sortFunc(a1, b1, isNumber);
        });
    } else { //"desc"
        list.sort(function (a, b) {
            var a1 = a[sort];
            var b1 = b[sort];
            return -sortFunc(a1, b1, isNumber);
        });
    }
}


/**
 * 弹窗样式
 *
 * @param    {Object}  value        对象
 *
 * @date     2018-05-29
 * @author   张迪
 */
function openWindowAndResize(wndId, resizeInfo, title) {
    if (title == null || title == 'undefined') {
        $(wndId).window({
            collapsible: false,
            minimizable: false,
            maximizable: false
        }).window("open").window('resize', resizeInfo);
    } else {
        $(wndId).window({
            title: title,
            collapsible: false,
            minimizable: false,
            maximizable: false
        }).window("open").window('resize', resizeInfo);
    }
}

function openWindow(wndId) {
    $(wndId).window({
        collapsible: false,
        minimizable: false,
        maximizable: false
    }).window("open");
}

/**
 * CommmonLib.js
 *
 * 公用的JS方法，一些通用的工具方法。
 *
 * @author  蔡万伟
 *
 */

/**
 * 判断是否为空。
 *
 * @param    {Object}  value        对象
 *
 * @date     2018-05-25
 * @author   蔡万伟
 */
function isNullOrEmpty(value) {
    if (value == '' || value == null) {
        return true;
    }
    return false;
}

/**
 * 将一个界面区域的属性设置到对象中，需要界面元素中的data-name属性值和对象的一致。
 * 表格中的数据必须有ID和Name，根据ID删除数据。
 *
 * @param    {Object}  data        数据对象
 * @param    {String}  panelId     界面区域Id
 *
 * @date     2018-05-24
 * @author   蔡万伟
 */
function setDataValueToControl(data, panelId) {
    var $panel = $(panelId);
    var $eles = $panel.find("option:selected,input,textarea,input[type='hidden']");
    var $eles2 = $panel.find("select,input,textarea");
    $eles.each(function (i, obj) {
        var ctrName = $(obj).attr("data-name");
        for (var p in data) {
            if (p === ctrName) {
                var value = data[p];
                $(obj).val(value);

            }
        }
    });
}

/**
 * 将一个界面区域的属性设置到对象中，需要界面元素中的data-name属性值和对象的一致。
 * 表格中的数据必须有ID和Name，根据ID删除数据。
 *
 * @param    {Object}  data        数据对象
 * @param    {String}  panelId     界面区域Id
 *
 * @date     2018-05-24
 * @author   蔡万伟
 */
function setControlValueToData(panelId,data) {
    var controls = $(panelId).find("select,input,textarea");
    controls.each(function (i, ctr) {
        var value = "";
        if ($(ctr).is("select")) {
            value = $(this).find("option:selected").attr("value")
        } else if ($(ctr).is("textarea")) {
            value = $(this).val();
        } else if ($(ctr).is("input")) {
            value = $(this).val();
        }
        var ctrName = $(ctr).attr("data-name");
        //console.log("data-name:" + ctrName + ",value:" + value);
        if (ctrName) {
            for (var property in data) { //遍历对象的属性
                if (ctrName === property) { //当属性名称和控件名称相同时设置属性值
                    data[property] = value;
                    //console.log("data-name:" + ctrName + ",property:" + property + ",value:" + value);
                }
            }
        }
    });
}

window.console = window.console || (function () {
    var c = {}; c.log = c.warn = c.debug = c.info = c.error = c.time = c.dir = c.profile
        = c.clear = c.exception = c.trace = c.assert = function () { };
    return c;
})();

/**
 * Array的find扩展方法
 *
 * @param    {function}  compareFunc        比较函数
 * @return   {Object}  item or null 查找结果
 *
 * @date     2018-05-27
 * @author   蔡万伟
 */
function arrayFindExtension(compareFunc) {
    //console.log("myFind");
    //console.log(compareFunc);
    //console.log(this);
    //console.log(this.length);
    if (compareFunc == null)return null;
    for (var i = 0; i < this.length; i++) {
        var item = this[i];
        var compareResult = compareFunc(item);
        if (compareResult == true) {
            //console.log("finded");
            //console.log(item);
            return item;
        }
    }
    //console.log("return null");
    return null;
}

////添加数组find方法
//if (!Array.prototype.find) { //IE8下数组没有find方法，自己写一个给他
//    Array.prototype.find = arrayFindExtension;
//}
//

/**
 * 前面的代码给Array加上find扩展方法后，遍历数组会把这个方法也作为一个子元素也遍历出来，不能用。
 * 用调用函数取代使用扩展函数。
 *
 * @param    {function}  compareFunc        比较函数
 * @return   {Object}  item or null 查找结果
 *
 * @date     2018-05-29
 * @author   蔡万伟
 */
function findItemInArray(array, compareFunc) {
    if (compareFunc == null) return null;
    for (var i = 0; i < array.length; i++) {
        var item = array[i];
        var compareResult = compareFunc(item);
        if (compareResult == true) {
            return item;
        }
    }
    return null;
}


//判断是否是邮箱地址格式
function isMailFormat(mail) {
    var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
    var bChk = reg.test(mail);
    return bChk;
}
function isPhoneNo(phone) {
    var pattern = /^1[34578]\d{9}$/;
    return pattern.test(phone);
}

/**
 * 删除表格中的一行，提交指令，显示返回结果，刷新表格。
 * 表格中的数据必须有ID和Name，根据ID删除数据。
 *
 * @param    {String}  grid     表格
 * @param    {String}  text     表格
 * @param    {Function}  text     删除函数
 * @param    {Function}  text     刷新函数
 *
 * @date     2018-05-21
 * @author   蔡万伟
 */
function submitDeleteDataGridRowByID(grid, text, deleteFunc, loadDataFunc) {
    submitDeleteDataGridRowByField(grid, text, 'ID', 'Name', deleteFunc, loadDataFunc);
}

/**
 * 删除表格中的一行，提交指令，显示返回结果，刷新表格。
 * 表格中的数据必须有ID和Name，根据ID删除数据。
 *
 * @param    {String}  grid     表格
 * @param    {String}  text     表格
 * @param    {Function}  text     删除函数
 * @param    {Function}  text     刷新函数
 *
 * @date     2018-05-21
 * @author   蔡万伟
 */
function submitDeleteDataGridRowByField(grid, text, idField,nameField,deleteFunc, loadDataFunc) {
    var selected = $(grid).datagrid('getSelected');
    if (selected != null) {
        //console.log(selected);
        var id = selected[idField];
        var name = selected[nameField];
        if (!confirm('是否要删除' + text + ': ' + name)) {
            return;
        }
        var obj = deleteFunc(id);
        if (obj == null) {
            alert(text + "删除失败! 返回结果为空，检查参数是否正确。");
        }
        else if (obj.State == 0) {
            alert(text + "删除成功!");
            if (loadDataFunc != null) {
                loadDataFunc();
            }
        } else {
            alert(text + "删除失败!错误信息：" + obj.Describe);
        }
    } else {
        alert("请先选中要修改的行!");
    }
}

function getLoginName() {
    return window.parent.global_username;
}

function showWindowInScroll(wndId, text, topOff, leftOff) {
    var top = document.body.scrollTop + document.documentElement.scrollTop;
    //$(wndId).window("open").window('resize', { top: top + topOff, left: leftOff });
    $(wndId).window("open").window('center');
    $(wndId).siblings(".panel-header").children(".panel-title").text(text);
}

/**
 * 设置下拉框的值，同时设置为只读，仅仅是看名称用的，没有id信息。
 *
 * @param    {String}  comboId     表格
 * @param    {String}  value     表格
 *
 * @date     2018-05-21
 * @author   蔡万伟
 */
function setComboBoxReadonlyValue(comboId, value) {
    $("#" + comboId).empty();
    $("#" + comboId).append("<option value='" + value + "'>" + value + "</option>");
    disableElementById(comboId);
}

function disableElementsById(ids) {
    for (var i in ids) {
        disableElementById(ids[i]);
    }
}

function disableElementById(id) {
    var ele = document.getElementById(id);
    if (ele!=null)
        ele.disabled = true;
}

function hideElementsById(ids) {
    for (var i in ids) {
        hideElementById(ids[i]);
    }
}

function hideElementById(id) {
    var ele = document.getElementById(id);
    if (ele != null && ele.style!=null)
        ele.style.display = "none";
}

function showElementById(id) {
    var ele = document.getElementById(id);
    if (ele != null && ele.style != null)
        ele.style.display = "";
}

/**
 * 合并表格中的单元格
 *
 * @param    {String}  grid     表格
 * @returns  {Array}  filedName   要合并的列
 *
 * @date     2018-05-21
 * @author   蔡万伟
 */
function mergeGridCells(grid, filedName) {
    var rows = $(grid).datagrid('getRows');
    for (var i = 0; i < rows.length; i++) {
        var indexStart = i;
        var item = rows[i];
        if (i + 1 >= rows.length) break;
        var item2 = rows[i + 1];
        if (item[filedName] == null) return;
        while (item[filedName] == item2[filedName]) {
            i++;
            if (i + 1 >= rows.length) break;
            item2 = rows[i + 1];
        }
        var indexEnd = i;

        $(grid).datagrid('mergeCells', {
            index: indexStart,
            field: filedName,
            rowspan: indexEnd - indexStart + 1
        });
    };
}

/**
 * 根据树列表中的父子关系创建列表，添加子节点到列表中
 *
 * @param    {string}  listRec     列表
 * @returns  {Array}  treeNodes   树
 *
 * @date     2018-05-21
 * @author   蔡万伟
 */
function appendSubTreeNode(id, children, list) {
    for (var i = 0; i < children.length; i++) {
        var child = children[i];
        var strid = child.id;
        var strname = child.name;
        list.push({
            id: strid,
            pId: id,
            name: strname,
            tag:child
        });
        appendSubTreeNode(child.id, child.ListRec, list);//递归
    }
}

/**
 * 根据树列表中的父子关系创建列表。
 * 既然需要的列表的节点只有 id,pId,name,可以直接让后台传这种数据的。
 *
 * @param    {Array}  listRec     树
 * @returns  {Array}  treeNodes   列表
 *
 * @date     2018-05-21
 * @author   蔡万伟
 */
function createListByTree(listTree) {
    var list = [];
    if (listTree == null) {
        return list;
    }
    for (var i = 0; i < listTree.length; i++) {
        var node = listTree[i];
        var strid = node.id;
        var strname = node.name;
        list.push({
            id: strid,
            pId: 0,
            name: strname,
            tag:node
        });//一级节点
        appendSubTreeNode(strid, node.ListRec, list);//子节点
    }
    return list;
}

function jqueryAjax(url, data, parseFunc) {
    // console.log("JqueryAjax");
    $.ajax({
        type: "post",
        url: url,
        data: JSON.stringify(data),
        dataType: "json",
        async: false,
        contentType: 'application/json',
        success: function (result) {
            var obj = eval('(' + result.d + ')');
            parseFunc(obj);
        }
    });
}

function jqueryAjaxText(url, dataText, parseFunc) {
    // console.log("JqueryAjax");
    $.ajax({
        type: "post",
        url: url,
        data: dataText,
        dataType: "json",
        async: false,
        contentType: 'application/json',
        success: function (result) {
            var obj = eval('(' + result.d + ')');
            parseFunc(obj);
        }
    });
}

function cloneObject(obj) {
    // Handle the 3 simple types, and null or undefined
    if (null == obj || "object" != typeof obj) return obj;

    // Handle Date
    if (obj instanceof Date) {
        var copy = new Date();
        copy.setTime(obj.getTime());
        return copy;
    }

    // Handle Array
    if (obj instanceof Array) {
        var copy = [];
        for (var i = 0, len = obj.length; i < len; ++i) {
            copy[i] = cloneObject(obj[i]);
        }
        return copy;
    }

    // Handle Object
    if (obj instanceof Object) {
        var copy = {};
        for (var attr in obj) {
            if (obj.hasOwnProperty(attr)) copy[attr] = cloneObject(obj[attr]);
        }
        return copy;
    }

    throw new Error("Unable to copy obj! Its type isn't supported.");
}

//获取当前显示的frame
function getCurrentFrame() {
    var frames = $("iframe");
    //  writeLog(frames);
    var frame = frames[frames.length - 1];//最后一个是当前显示的那一个
    if (frame.name == "nanana") {   //导出用子界面
        frame = frames[frames.length - 2];
    }
    //   writeLog(frame);
    var frameWnd = frame.contentWindow;
    return frameWnd;
}

function getTagFrameByName(name) {
    var frames = $("iframe");
    //   writeLog(frames);
    var frame = frames[name];//最后一个是当前显示的那一个
    //   writeLog(frame);
    if (frame == null) {
        return null;
    }
    var frameWnd = frame.contentWindow;
    return frameWnd;
}

//关闭自身窗口 未实现
function closeSelfDialog(btn) {
    var wnd = $(btn).parents("form");
    $(wnd).dialog("close");
    //$("#" + id).dialog("close");
}

//根据Id显示对话框
function showDialogById(dialogId, titleText, left, top, width, height) {
    var $dg = $(dialogId);
    showDialog$($dg, titleText, left, top, width, height);
}

function showDialog$($dg, titleText, left, top, width, height) {
    $dg.dialog({
        title: titleText,
        left: left,
        top: top,
        width: width,
        height: height
    });
    var froms = $dg.find("form");
    froms.show();
    if (froms.length>0)
        froms[0].reset();
    $dg.dialog("open");
    return $dg;
}

/**
 * 设置下拉框的值，类对象的必须有ID和Name属性，默认value是ID，内容是Name。另外默认最顶部有个空的内容为选中状态。
 *
 * @param    {Object}  cb         下拉框Id
 * @param    {Array}  listData     数据列表
 * @param    {Boolean}  noEmpty    是否添加空数据，默认添加
 * @date
 * @author   蔡万伟
 */
function setComboBoxData(cb, listData, noEmpty) {
    setComboBoxData$($(cb), listData, noEmpty);
}

/**
 * 设置下拉框的值，类对象的必须有ID和Name属性，默认value是ID，内容是Name。另外默认最顶部有个空的内容为选中状态。
 *
 * @param    {Object}  $cb         下拉框
 * @param    {Array}  listData     数据列表
 * @param    {Boolean}  noEmpty    是否添加空数据，默认添加
 * @date
 * @author   蔡万伟
 */
function setComboBoxData$($cb, listData, noEmpty) {
    $cb.empty();
    if (listData == null) {
        return;
    }
    if (!noEmpty) {
        $cb.append('<option value="" selected = "selected"></option>');
    }
    if (listData && listData.length) {
        for (var i = 0; i < listData.length; i++) {
            var item = listData[i];
            if(!item.id){ // id is undefined 没有id属性
                $cb.append('<option value="' + item + '">' + item + '</option>');
            }
            else{
                $cb.append('<option value="' + item.id + '">' + item.name + '</option>');
            }

        }
    }
}

//setComboBoxData的value也是是Name的版本
function setComboBoxDataByName(cb, listData) {
    setComboBoxDataByName$($(cb), listData);
}

function setComboBoxDataByName$($cb, listData) {
    $cb.empty();
    if (listData == null) {
        return;
    }
    $cb.append('<option value="" selected = "selected"></option>');
    if (listData && listData.length) {
        for (var i = 0; i < listData.length; i++) {
            var item = listData[i];
            $cb.append('<option value="' + item.name + '">' + item.name + '</option>');
        }
    }
}

//setComboBoxData的设置多个下拉框的版本
function setComboBoxListData(comboBoxs, listRec) {
    for (var j in comboBoxs) {
        var cb = comboBoxs[j];
        setComboBoxData(cb, listRec);
    }
}

function setComboBoxListDataCore($comboBoxs, listRec) {
    for (var j in $comboBoxs) {
        var $cb = $comboBoxs[j];
        setComboBoxData$($cb, listRec);
    }
}

//初始化DataGrid的分页数据，能够进行分页导航
function initDataGridPager(datagrid, listData) {
    return initDataGridPager$($(datagrid), listData);
}

function initDataGridPager$($datagrid, listData) {
    var pager = $datagrid.datagrid("getPager");
    pager.pagination({
        total: listData.length,
        layout: ['first', 'prev', 'links', 'next', 'last'],
        onSelectPage: function (pageNo, pageSize) {
            var start = (pageNo - 1) * pageSize;
            var end = start + pageSize;
            $datagrid.datagrid("loadData", listData.slice(start, end));
            pager.pagination('refresh', {
                total: listData.length,
                pageNumber: pageNo
            });
        }
    });
    return pager;
}

//获取值 代替.val()，如 $("#name").val() 改为 getElementValue("#name");
function getElementValue(controlId) {
    return getElementValue$($(controlId));
}

function getElementValue$($control) {
    var elem = $control[0]; //网页元素
    if (elem == null) {//元素未找到
        return "";
    }
    var elemName = elem.nodeName.toLowerCase();
    //var value = $(cb).val();//IE8下返回值是一个对象，对象内的一个属性才是值
    var value = "";
    if (elemName == "select") {
        value = $control.find('option:selected').attr('value');
    } else if (elemName == "input") {
        value = $control.attr('value');
    } else {
        value = $control.val();
    }
    if (value == null) {
        value = "";
    }
    return value;
}

//获取值 代替.val()，如 $("#name").val() 改为 getElementValue("#name");
function getElementText(controlId) {
    return getElementText$($(controlId));
}

function getElementText$($control) {
    var elem = $control[0]; //网页元素
    if (elem == null) {//元素未找到
        return "";
    }
    var elemName = elem.nodeName.toLowerCase();
    //var value = $(cb).val();//IE8下返回值是一个对象，对象内的一个属性才是值
    var value = "";
    if (elemName == "select") {
        value = $control.find('option:selected').attr('text');
    } else if (elemName == "input") {
        value = $control.attr('text');
    } else {
        value = $control.attr('text');
    }
    if (value == null) {
        value = "";
    }
    return value;
}

function getSelectedComboAttr(controlId, attrName) {
    return getSelectedComboAttr$($(controlId), attrName);
}

function getSelectedComboAttr$($control, attrName) {
    var selectedItem = $control.find('option:selected');
    var value = selectedItem.attr(attrName);
    if (!value) {//ie11下没有选中一个的情况下会返回空的
        var items = $control.find('option');
        var first = items[0];//取第一个值
        value = $(first).attr(attrName);
    }
    return value;
}

//设置值 代替.val(value)，如 $("#name").val("123") 改为 setElementValue("#value","123");
function setElementValue(controlId, value) {
    $(controlId).val(value);
}

//getElementValue的简写
function getEleValue(controlId) {
    getElementValue(controlId);
}

//setEleValue的简写
function setEleValue(controlId) {
    setElementValue(controlId);
}

//获取select的选中值
function getComboValue(cb) {
    //var value = $(cb).val();
    var value = $(cb).find('option:selected').attr('value');
    if (value == null) {
        value = "";
    }
    return value;
}

//获取select的选中值
function getComboText(cb) {
    //var value = $(cb).val();
    var value = $(cb).find('option:selected').attr('text');
    if (value == null) {
        value = "";
    }
    return value;
}

function selectComboValueByText(cb, text) {
    setComboValueByText(cb, text);
    $(cb).change();
}

function selectComboValueByValue(cb, value) {
    setComboValueByValue(cb, value);
    $(cb).change();
}


/**
 * 根据text的内容设置下拉框的选中项，并改成不能选择。
 * 不存在该text时，改为可以选择，以避免无法进行后续操作。
 *
 * @param    {String}  cb   下拉框Id 带#
 * @param    {String}  text  文本内容
 *
 * @date     2018-06-14
 * @author   蔡万伟
 */
function setComboValueByTextAndDisable(cb, text) {
    $(cb).attr('disabled', true);
    setComboValueByText(cb, text);
    var cbValue = getComboValue(cb);
    if (cbValue == "nullid" || cbValue == "") {
        $(cb).attr('disabled', false);
    }
}

function setComboValueByText(cb,text) {
    $(cb).find('option').filter(function () { return $(this).attr('text') == text; }).attr("selected", true);
}

function setComboValueByValue(cb,value) {
    $(cb).find('option').filter(function () { return $(this).attr('value') == value; }).attr("selected", true);
}

//获取input的输入值
function getInputValue(tb) {
    var value = $(tb).attr('value');
    if (value == null) {
        value = "";
    }
    return value;
}

function writeLog(txt) {
    // console.log(txt);
}

function writeInfo(tag,txt) {
    //  console.log("["+tag+"] "+txt);
}
