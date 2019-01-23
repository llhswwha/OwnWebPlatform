<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
   <jsp:include page="head.jsp" flush="true" />
    <title>设备管理</title>
    <link rel="stylesheet" href="../css/device_management.css">

</head>
<body>
<div class="outbox">
    <div class="contentbox">
        <div class="action_bar">
            <a href="javascript:;" class="btn-custom btn-blue" id="head-crease" plain="true">创建设备类型</a>
            <a href="javascript:;" class="btn-custom btn-white" plain="true">修改</a>
            <a href="javascript:;" class="btn-custom btn-white" plain="true">删除</a>
            <a href="javascript:;" class="btn-custom btn-white out_write" plain="true">导出</a>

            <input type="text" class="page_search" placeholder="输入设备类型名称进行搜索">
            <span class="icon iconfont icon-sousuo"></span>
            <span class="icon iconfont icon-shuaxin"></span>




        </div>

    </div>

</div>
<div id="Device_attribute"  class="easyui-window" title="设备监控项属性" data-options="modal:true,closed:true,iconCls:'icon-save'" style="background: #ffffff; width: 840px; height: 480px; position: relative;">
<div class="box">
    <div class="left content_div"></div>
    <div class="Middle content_div">


    </div>
    <div class="right content_div">
<table>
    <caption>基本属性</caption>
    <tr>
        <td>对象ID</td>
        <td></td>
    </tr>
    <tr>
        <td>对象名称</td>
        <td></td>
    </tr>
    <tr>
        <td>测点编号</td>
        <td></td>
    </tr>
    <tr>
        <td>测点类型</td>
        <td></td>
    </tr>
</table>
<table>
    <caption>扩展属性</caption>
    <tr>
        <td>组集合</td>
        <td></td>
    </tr>
    <tr>
        <td>量程上限</td>
        <td></td>
    </tr>
    <tr>
        <td>量程下限</td>
        <td></td>
    </tr>
    <tr>
        <td>一般告警上限</td>
        <td></td>
    </tr>
    <tr>
        <td>一般告警下限</td>
        <td></td>
    </tr>
    <tr>
        <td>重要告警上限</td>
        <td></td>
    </tr>
    <tr>
        <td>重要告警下限</td>
        <td></td>
    </tr>
    <tr>
        <td>紧急告警上限</td>
        <td></td>
    </tr>
    <tr>
        <td>紧急告警下限</td>
        <td></td>
    </tr>
    <tr>
        <td>设备分类</td>
        <td></td>
    </tr>
    <tr>
        <td>精度</td>
        <td></td>
    </tr>
    <tr>
        <td>单位</td>
        <td></td>
    </tr>
    <tr>
        <td>标准值</td>
        <td></td>
    </tr>
    <tr>
        <td>入库周期</td>
        <td></td>
    </tr>
    <tr>
        <td>告警延迟</td>
        <td></td>
    </tr>
    <tr>
        <td>告警维持时间</td>
        <td></td>
    </tr>
    <tr>
        <td>告警升级时间</td>
        <td></td>
    </tr>
    <tr>
        <td>允许告警</td>
        <td></td>
    </tr>
    <tr>
        <td>允许通道运算</td>
        <td></td>
    </tr>
    <tr>
        <td>值上送周期</td>
        <td></td>
    </tr>
    <tr>
        <td>最小变化值</td>
        <td></td>
    </tr>
</table>

    </div>
</div>

</div>

<script type="text/javascript" src="../js/device_management.js"></script>
</body>
</html>