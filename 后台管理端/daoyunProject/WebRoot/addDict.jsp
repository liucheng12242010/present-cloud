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
<title>新增数据字典</title>
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
		                    url:  dictInsert,
		                    data: param,
		                    dataType: 'json',
		                    success: function (res) {    
		                        if (res.Success == true) {
		                        	layer.msg("新增成功！");
		                        } else {
		                        	layer.msg("字典数据已存在！");
		                        }
		                    },
		                    error: function () {
		                       
		                    }
		                });  
				    return false;
				  });			 
			});
</script>
<body class="layui-layout-body">
   <div class='layui-bg-gray'>
	<div class="layui-main">  
	    <fieldset class="layui-elem-field layui-field-title">
		  <legend>新增一个的字典数据</legend>
		</fieldset>
		<form action="dictInsert" class="layui-form" method="post">
		  <div class="layui-form-item">
		    <label class="layui-form-label">类别</label>
		    <div class="layui-input-inline">
		      <input type="text" name="dict.type"   lay-verify="required" placeholder="请输入类别" autocomplete="off" class="layui-input">
		    </div>
		    <label class="layui-form-label">名称</label>
		    <div class="layui-input-inline">
		     <input type="text" name="dict.name"  lay-verify="required" placeholder="请输入名称" autocomplete="off" class="layui-input">
		    </div>
		  </div>
		  <div class="layui-form-item">
		    <label class="layui-form-label">顺序</label>
		    <div class="layui-input-inline">
		      <input type="text" name="dict.order"   lay-verify="required" placeholder="输入顺序" autocomplete="off" class="layui-input">
		    </div>
		    <label class="layui-form-label">状态</label>
		    <div class="layui-input-inline">
		      <input type="text" name="dict.status"   lay-verify="required" placeholder="输入状态" autocomplete="off" class="layui-input">
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