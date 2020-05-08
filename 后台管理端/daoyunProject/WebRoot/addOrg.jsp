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
<title>新增组织</title>
<script src="Contents/layuiadmin/layui/layui.js"></script>
<script>
			layui.use(['form','upload','carousel','laydate'], function(){
				  var form = layui.form
				  ,carousel = layui.carousel
				  ,laydate = layui.laydate;
				  //监听提交
				  form.on('submit(addClassForm)', function(data){
					var param = data.field; 
					 $.ajax({
		                    type: 'post',
		                    url:  orgManageInsert,
		                    data: param,
		                    dataType: 'json',
		                    success: function (res) {    
		                        if (res.Success == true) {
		                        	layer.msg("新增成功！");
		                        } else {
		                        	layer.msg("组织Id已存在！");
		                        }
		                    },
		                    error: function () {
		                       
		                    }
		                });  
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
				  //日期选择器
				  laydate.render({
					    elem: '#classBeginDate'
					    ,type: 'datetime'
					  });
				
				  laydate.render({
					    elem: '#classEndDate'
					    ,type: 'datetime'
					  });
					  

			});
</script>
<body class="layui-layout-body">
   <div class='layui-bg-gray'>
	<div class="layui-main">  
	    <fieldset class="layui-elem-field layui-field-title">
		  <legend>新增一个新的组织</legend>
		</fieldset>
		<form action="orgManageInsert" class="layui-form" method="post">
		  <div class="layui-form-item">
		    <label class="layui-form-label">组织Id</label>
		    <div class="layui-input-inline">
		      <input type="text" name="organization.orgId" lay-verify="required" placeholder="请输入组织Id" autocomplete="off" class="layui-input">
		    </div>
		    <label class="layui-form-label">学校</label>
		    <div class="layui-input-inline">
		     <input type="text" name="organization.school" lay-verify="required" placeholder="请输入学校" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">学院</label>
		    <div class="layui-input-inline">
		      <input type="text" name="organization.college"   lay-verify="required" placeholder="请输入学院" autocomplete="off" class="layui-input">
		    </div>
		    <label class="layui-form-label">专业</label>
		    <div class="layui-input-inline">
		      <input type="text" name="organization.major"   lay-verify="required" placeholder="请输入专业" autocomplete="off" class="layui-input">
		    </div>
		    <label class="layui-form-label">年级</label>
		    <div class="layui-input-inline">
		      <input type="text" name="organization.grade"   lay-verify="required" placeholder="请输入年级" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" type="submit" lay-submit="" lay-filter="addClassForm">立即提交</button>
		      <button type="reset" class="layui-btn ">重置</button>
		    </div>
		  </div>
		</form>
	</div>
	</div>
</body>
</html>