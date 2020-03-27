<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	    <link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css">
		<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
		<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript">
<script type="text/javascript">

$(function(){
$('#index_regForm').form({
	url:'register.jsp',
	success:function(data){
		
	}
	});

});

</script>

<title>注册页面</title>

</head>

<body
class="easyui-layout">

<div data-options="region:'north'"style="height:60px;"></div>

<div data-options="region:'south'"style="height:20px;"></div>

<div data-options="region:'east',split:true"style="width:200px;">

<div
class="easyui-panel"data-options="title:'east',border:false,fit:true"></div></div>

<div data-options="region:'west',title:'west',split:true"style="width: 200px"></div>

<div
data-options="region:'center',title:'centertitle'"
style="paddiing:5px;background:#eee;"></div>

 

<div
class="easyui-dialog"  
data-options="title:'登录',buttons:[{

text:'注册',

iconCls:'icon-edit',

handler:function(){

$('#index_regDialog').dialog('open');

}

},{

text:'登录',

iconCls:'icon-help',

handler:function(){alert('登录')}}],modal:true,closable:false">

<table>

<tr>

<th>登录名</th>

<td><input/></td>

</tr>

<tr>

<th>密码</th>

<td><input/></td>

</tr>

</table>

</div>

 

 

 

<div id="index_regDialog"  style="width:250px" class="easyui-dialog" data-options="title:'注册',closed:true,buttons:[{text:'注册',iconCls:'icon-edit',

handler:function(){

$('#index_regForm').submit();

}

}]">

 

<form
id="index_regForm"method="post">

<table>

<tr>

<th>登录名</th>

<td><input
name="name"class="easyui-validatebox"
data-options="required:true"/></td>

</tr>

<tr>

<th>密码</th>

<td><input
name="pwd"type="password"
class="easyui-validatebox"data-options="required:true"/></td>

</tr>

<tr>

<th>重复密码</th>

<td><input
name="rePwd"type="password"
class="easyui-validatebox"data-options="required:true,validType:'eqPwd[\'#index_regForminput[name=pwd]\']'"/></td>

</tr>

</table>

</form>

</div>

</body>

</html>