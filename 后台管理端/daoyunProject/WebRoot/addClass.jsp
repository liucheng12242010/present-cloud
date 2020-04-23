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
			layui.use(['form','upload','carousel','laydate'], function(){
				  var form = layui.form
				  ,carousel = layui.carousel
				  ,laydate = layui.laydate;
				  //监听提交
				  form.on('submit(addClassForm)', function(data){
					var param = data.field; 
					 $.ajax({
		                    type: 'post',
		                    url:  classManageInsert,
		                    data: param,
		                    dataType: 'json',
		                    success: function (res) {    
		                        if (res.Success == true) {
		                        	layer.msg("新增成功！");
		                        } else {
		                        	layer.msg("课程Id已存在！");
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
		  <legend>新增一个新的课程</legend>
		</fieldset>
		<form action="classManageInsert" class="layui-form" method="post">
		  <div class="layui-form-item">
		    <label class="layui-form-label">课程Id</label>
		    <div class="layui-input-inline">
		      <input type="text" name="classes.classId"   lay-verify="required" placeholder="请输入课程Id" autocomplete="off" class="layui-input">
		    </div>
		    <label class="layui-form-label">课程名称</label>
		    <div class="layui-input-inline">
		     <input type="text" name="classes.className"  lay-verify="required" placeholder="请输入课程名称" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">任课教师</label>
		    <div class="layui-input-inline">
		      <input type="text" name="classes.teacher"   lay-verify="required" placeholder="请输入任课教师" autocomplete="off" class="layui-input">
		    </div>
		    <label class="layui-form-label">开课时间</label>
		    <div class="layui-input-inline">
		        <input class="layui-input" id="classBeginDate" name="classes.classBeginDate" type="text" placeholder="yyyy-MM-dd">
		      </div>
		     <label class="layui-form-label">结课时间</label>
		    <div class="layui-input-inline">
		        <input class="layui-input" id="classEndDate" name="classes.classEndDate" type="text" placeholder="yyyy-MM-dd">
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