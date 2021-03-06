<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
   <jsp:include page="head.jsp" flush="true" />
    <title>系统管理</title>
    <link rel="stylesheet" href="../css/system_management.css">

</head>
<body>
<!--<div>-->
    <!--<div class="systitlebox">-->
        <!--<div class="titlelist active">人员</div>-->
        <!--<div class="titlelist">岗位</div>-->
        <!--<div class="titlelist">角色</div>-->
    <!--</div>-->
    <!--<div class="syscontent">-->
        <!--<div class="sysmain active">人员人员人员人员</div>-->
        <!--<div class="sysmain">岗位岗位岗位</div>-->
        <!--<div class="sysmain">角色角色角色</div>-->
    <!--</div>-->

<!--</div>-->

<div class="easyui-tabs" fit="true" fitColumns="true">
    <div title="用户管理">
        <!--<!--表格一内容 用户管理-->
        <div class="tab-pane fade in active" id="User_management">
            <div class="search_del" style="height: 33px;float: left">
                <input id="search_people" type="text" name="" value="" class="textbox textbox_normal" placeholder="输入关键字搜索">
                <button id="search_peo_btn" type="button" class="btn-custom btn-blue btn btn-success">查询</button>
                <button type="button" class="btn-custom btn-blue btn btn-success" id="btn_clearinput">清空</button>
                <lable><input name="" type="checkbox" onclick='toggsubcomchang()' value="" />是否显示子部门人员</lable>
            </div>
            <div class="but_list" style="float: right; width:300px; height: 40px; line-height: 40px;">
                <a href="javascript: void(0)" class="btn-custom btn-blue" id="add_userlist" plain="true">添加</a>
                <a href="javascript: void(0)" class="btn-custom btn-blue" id="user_edit" plain="true">修改</a>
                <a href="javascript: void(0)" class="btn-custom btn-blue" id="user_delete" plain="true">删除</a>
                <!--暂时不做-->
                <!--<span class="shugan"></span><span class="details_btn"><img src="../../img/xiugai_img.png">详细</span>-->
            </div>
        </div>

        <!--用户数据加载-->
        <div id="data_list"></div>
    </div>

    <div title="岗位管理">
        <div class="but_list" id="header_post">
            <span class="btn-custom btn-blue add_list" id="post_open_add" value="岗位">添加</span>
            <a href="javascript: void(0)" class="btn-custom btn-blue" id="Post_editdata" plain="true"  value="岗位">修改</a>
            <a href="javascript: void(0)" class="btn-custom btn-blue" id="Post_dele_lidata" plain="true"  value="岗位">删除</a>
            <a href="javascript: void(0)" class="btn-custom btn-blue" id="Post_li_users" plain="true"  value="岗位">所属用户</a>
        </div>
        <div id="ganwei_datalist" style="height: 700px;"></div>
    </div>
    <div title="角色管理">
        <div class="but_list" id="tb_tableView4">
            <span class="btn-custom btn-blue add_list" id="add_rol_open"  value="角色">添加</span>
            <a href="javascript: void(0)" class="btn-custom btn-blue" id="rol_del_list" plain="true" value="角色">删除</a>
            <a href="javascript: void(0)" class="btn-custom btn-blue" id="rol_edit_list" plain="true" value="角色">编辑</a>
            <a href="javascript: void(0)" class="btn-custom btn-blue" id="rol_power_list" plain="true" value="角色">操作权限</a>
            <a href="javascript: void(0)" class="btn-custom btn-blue" id="rol_user_list" plain="true"  value="角色">关联用户</a>
        </div>
        <div id="tableView4"></div>
    </div>

</div>

<!--用户管理添加弹窗-->
<div id="User_management_add" class="easyui-window" title="添加用户信息" data-options="modal:true,closed:true,iconCls:'icon-save'" style="background: #ffffff; width: 800px; height: 480px; position: relative;">
    <div style="padding: 0 0 0 60px">
        <form id="organ_amend_content" method="post" style="float: left;">
            <table cellpadding="5">
                <tr>
                    <td>登录账号：</td>
                    <td><input class="textbox textbox_normal newLoginName" type="text" name="name" data-options="required:true" style="margin-top: 5px; margin-right: 20px;">
                        <div class="inputxin">*</div></td>
                    <td>用户姓名：</td>
                    <td><input class="textbox textbox_normal name" type="text" name="name" data-options="required:true" style="margin-top: 5px; margin-right: 20px;">
                        <div class="inputxin">*</div></td>
                </tr>
                <tr>

                </tr>
                <tr>
                    <td>用户性别：</td>
                    <td>
                        <select class="sex" >
                            <option value="男">男</option>
                            <option value="女">女</option>
                        </select>
                        <div class="inputxin">*</div>
                    </td>
                    <td>出生年月：</td>
                    <td id="time_box">
                        <input class="birthday" id="start_time11" style="width: 178px; height: 30px; border: 1px solid #cfd5e5; margin-top: 5px;" type="text">
                        <div class="inputxin">*</div>
                        <!--<input class="easyui-datebox birthday" style="width:170px"><div class="inputxin">*</div>-->
                    </td>
                </tr>
                <tr>
                    <td>所属单位:</td>
                    <td>
                        <div id="input_orgIDbox" style="background: #fff; filter: alpha(opacity=0); opacity: 0;"></div>
                        <select class="easyui-textbox orgID" id="input_orgID" type="text" name="message" data-options="multiline:true"></select>
                        <div class="inputxin">*</div>
                        <div id="Sel_CompanyName">
                            <ul id="treeDemo" class="ztree" style="height: 138px; width: 162px; display: block; overflow-x: hidden; overflow-y: scroll;"></ul> <!-- 树形图-->
                        </div>
                    </td>
                    <td>岗位:</td>
                    <td >
                        <div id="GetPostListboxk" style="background: #fff; filter: alpha(opacity=0); opacity: 0;"></div>
                        <select class="easyui-textbox postIDList" id="GetPostList" type="text" name="message" data-options="multiline:true"></select>
                        <div class="inputxin">*</div>
                        <div id="GetPostListbox"></div>
                    </td>
                </tr>
                <tr>
                    <td>用户角色:</td>
                    <td>
                        <div id="GetRoletListboxk" style="background: #fff; filter: alpha(opacity=0); opacity: 0;"></div>
                        <select id="GetRoletList" class="easyui-textbox roleIDList" name="message" data-options="multiline:true"></select>
                        <div class="inputxin">*</div>
                        <div id="GetRoletListbox"></div>
                    </td>
                    <td>职位:</td>
                    <td>
                        <div id="GetDutiesListboxk" style="background: #fff; filter: alpha(opacity=0); opacity: 0;"></div>
                        <select class="easyui-textbox dutiesIDList" id="GetDutiesList" name="message" data-options="multiline:true"></select>
                        <div class="inputxin">*</div>
                        <div id="GetDutiesListbox"></div>
                    </td>
                </tr>
                <tr>
                    <td>所属区域:</td>
                    <td><select id="GetDepList" class="easyui-textbox depID" name="message" data-options="required:true"></select>
                        <div class="inputxin">*</div></td>
                    <td>所属数据中心:</td>
                    <td><!--<select id="Sel_DataCenterName" class="easyui-textbox dataCenterID" name="message" data-options="multiline:true"></select>--><input id="Eysel" name="dept" class="easyui-combobox" style="width: 178px; height: 30px; border: 1px solid #cfd5e5; margin-top: 5px;">
                        <div class="inputxin">*</div></td>
                </tr>
                <tr>
                    <td>公司座机:</td>
                    <td><input class="textbox textbox_normal cpZJ" name="message" data-options="multiline:true" style="margin-top: 5px; margin-right: 20px;"></input></td>
                    <td>家庭座机:</td>
                    <td><input class="textbox textbox_normal homeZJ" name="message" data-options="multiline:true" style="margin-top: 5px; margin-right: 20px;"></input></td>
                </tr>
                <tr>
                    <td>公司手机:</td>
                    <td><input class="textbox textbox_normal cpTel" name="message" data-options="prompt:'公司手机电话',required:true" style="margin-top: 5px; margin-right: 20px;"></input></td>
                    <td>私人手机:</td>
                    <td><input class="textbox textbox_normal tel" name="message" data-options="multiline:true" style="margin-top: 5px; margin-right: 20px;"></input>
                        <div class="inputxin">*</div></td>
                </tr>
                <tr>
                    <td>公司邮箱:</td>
                    <td><input class="textbox textbox_normal cpEmail" name="cpEmail" data-options="required:true,validType:'email'" style="margin-top: 5px; margin-right: 20px;"></input></td>
                    <td>私人邮箱:</td>
                    <td><input class="textbox textbox_normal email" name="email" data-options="required:true,validType:'email'" style="margin-top: 5px; margin-right: 20px;"></input>
                        <div class="inputxin">*</div></td>
                </tr>
                <tr>
                    <td>用户描述：</td>
                    <td colspan="3"><textarea class="richtextbox richtextbox_normal describe" style="width: 530px;"> </textarea> </td>
                </tr>
            </table>
        </form>
    </div>
    <div class="butn_list">
        <a href="javascript: void(0)" class="btn-custom btn-blue" plain="true" onclick="submitForm()">确认</a>
        <a href="javascript: void(0)" class="btn-custom btn-white" plain="true" onclick="clearForm()">重置</a>
    </div>
</div>
<!--岗位管理添加弹窗-->
<div id="Post_management_add" class="easyui-window moreaddbox" title="Modal Window" data-options="modal:true,closed:true,iconCls:'icon-save'" style="background: #ffffff; width: 500px; height: 300px;">
    <p class="top_gaine">
        <span class="post_head">岗位信息 : </span>
        <i style="width: 342px;"></i>
    </p>
    <div style="width: 300px; height: 150px;margin-left: 90px;">
        <lable class="dep_nameinp"><i class="symbol_i">*</i><span class="postname">岗位</span>名称:<input id="addPostList" class="textbox textbox_normal depID" name="" data-options="multiline:true" style="width: 203px;margin-top: 5px;"></lable>
        <div style="clear:both"></div>
        <lable><span style="float: left;margin-left:10px; margin-top: 5px;"><span class="postname">岗位</span>描述:</span><textarea class="richtextbox richtextbox_normal describe" style="width: 198px;"></textarea></lable>
    </div>
    <div class="butn_list">
        <div class="btn-custom btn-white close_winde" value="#Post_management_add">取消</div>
        <div class="btn-custom btn-blue adddepSubmit">确认</div>
    </div>
</div>
<script type="text/javascript" src="../js/system_management.js"></script>
</body>
</html>