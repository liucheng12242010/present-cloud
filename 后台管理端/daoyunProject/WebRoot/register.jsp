<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="Contents/layuiadmin/layui/css/layui.css" media="all">
<style>
        .layui-form-label {
            width: 115px;
        }
    </style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>到云资源管理系统注册</title>
<script src="Contents/layuiadmin/layui/layui.js"></script>
<script>
			layui.use(['form','upload'], function(){
				  var form = layui.form
				  ,upload = layui.upload;
				  //监听提交
				  form.on('submit(registerForm)', function(data){
					var param = data.field; 
					 $.ajax({
		                    type: 'post',
		                    url:  register,
		                    data: param,
		                    dataType: 'json',
		                    success: function (res) {    
		                        if (res.Success == true) {
		                        
		                        } else {
		                            
		                        }
		                    },
		                    error: function () {
		                       
		                    }
		                });
				    layer.msg("注册成功！");
				    //window.location = "index.jsp";
				    return false;
				  });
				//自定义验证规则
				  form.verify({
					    title: function(value){
					      if(value.length < 5){
					        return '标题至少得5个字符啊';
					      }
					    }
					    ,pass: [
					      /^[\S]{6,12}$/
					      ,'密码必须6到12位，且不能出现空格'
					    ]
					    ,passRe: function(value){
					    	var ps=document.getElementById("userPassword").value;
					    	if(value != ps)
					    		return '两次密码不一致!'
					    }
					    ,content: function(value){
					      layedit.sync(editIndex);
					    }
					  });
			});
</script>
<body class="layui-layout-body">
	<div class="layui-btn-fluid">  
	    <fieldset class="layui-elem-field layui-field-title">
		  <legend>到云资源管理系统注册</legend>
		  <div class="layui-field-box">
		    注册信息：
		  </div>
		</fieldset>
		<form action="register" class="layui-form" method="post">
		  <div class="layui-form-item">
		    <label class="layui-form-label">用户账号</label>
		    <div class="layui-input-inline">
		      <input type="text" name="user.userId"   lay-verify="required" placeholder="请输入用户账号" autocomplete="off" class="layui-input">
		    </div>
		    <label class="layui-form-label">用户姓名</label>
		    <div class="layui-input-inline">
		     <input type="text" name="user.userName"  lay-verify="required" placeholder="请输入用户姓名" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">用户密码</label>
		    <div class="layui-input-inline">
		      <input type="password" name="user.password" id='userPassword' lay-verify="required|pass" placeholder="请输入密码" autocomplete="off" class="layui-input">
		    </div>
		    <label class="layui-form-label">密码确认</label>
		    <div class="layui-input-inline">
		      <input type="password" name="passwordRe"  lay-verify="required|pass|passRe" placeholder="请输入密码" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">用户角色</label>
		    <div class="layui-input-block">
		      <select name="user.role" lay-verify="required">
		        <option value=""></option>
		        <option value="0">学生</option>
		        <option value="1">老师</option>
		        <option value="2">助教</option>
		      </select>
		    </div>
		    <label class="layui-form-label">所在学校</label>
		    <div class="layui-input-block">
		      <select name="user.school" lay-verify="required">
		        <option value=""></option>
		        <option value="福州大学">福州大学</option>
		        <option value="福州中学">福州中学</option>
		        <option value="福州小学">福州小学</option>
		      </select>
		    </div>
		    <label class="layui-form-label">所在学院</label>
		    <div class="layui-input-block">
		      <select name="user.college" lay-verify="required">
		        <option value=""></option>
		        <option value="数计学院">数计学院</option>
		        <option value="物信学院">物信学院</option>
		        <option value="艺术学院">艺术学院</option>
		      </select>
		    </div>
		    <label class="layui-form-label">用户性别</label>
		    <div class="layui-input-block">
		      <select name="user.gender" lay-verify="required">
		        <option value=""></option>
		        <option value="男">男</option>
		        <option value="女">女</option>
		      </select>
		    </div>
		  </div>
		  <div class="layui-form-item layui-form-text">
		    <label class="layui-form-label">详细介绍</label>
		    <div class="layui-input-block">
		      <textarea name="user.remarks" placeholder="请输入内容" class="layui-textarea"></textarea>
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" type="submit" lay-submit="" lay-filter="registerForm">立即提交</button>
		      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		  </div>
		</form>
	</div>
</body>
</html>