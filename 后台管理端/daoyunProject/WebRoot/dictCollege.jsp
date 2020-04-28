<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>工作台</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="Contents/layuiadmin/layui/css/layui.css" media="all">
    <style>
        .layui-form-label {
            width: 115px;
        }
    </style>
</head>
<body style="margin-top: -15px;">
    <div class="layui-fluid">
        <div class="layui-table" style="margin-top:30px;">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend style="padding-top: 10px;">学院分类</legend>
            </fieldset>
          <div class="layui-form layui-card-header layuiadmin-card-header-auto">
         <div class="site-demo-button" id="layerDemo" style="margin-bottom: 0;">
			  <button class="layui-btn" data-method="setTop">新增字典数据</button>
		 </div>
        <table id="classTable" lay-filter="classTable"></table>
        <script id="toolbar" type="text/html">
                <div class="layui-btn-container">
                    <button class="layui-btn layui-btn-sm" lay-event="getCheckData">获取选中行数据</button>
                    <button class="layui-btn layui-btn-sm" lay-event="getCheckLength">获取选中数目</button>
                    <button class="layui-btn layui-btn-sm" lay-event="isAll">验证是否全选</button>
                </div>
            </script>
            <script id="bar" type="text/html">
                <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
                <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
            </script>
        </div>
       </div>
    </div>
    <script src="Contents/layuiadmin/layui/layui.js"></script>
    <script type="text/JavaScript">
        layui.config({
            base: 'Contents/layuiadmin/' //静态资源所在路径
        }).extend({
            index: 'lib/index' //主入口模块
            }).use(['index', 'forum', 'laydate','table','layer'], function () {
            var form = layui.form
                //laydate——控件
                , laydate = layui.laydate
                , $ = layui.$
                , table = layui.table;
            //年月日选择器
            laydate.render({
                elem: '#YMD'
                , value: new Date()
                , type: 'date'
                , format: 'yyyy-MM-dd'
            });

            //自定义验证规则
            form.verify({
                maxLen50: function (value) {
                    if (value.length > 50) {
                        return '不能超过50个字符';
                    }
                }
            });
            table.render({
                elem: '#classTable'
                , url : 'dictSelectCollege'
                , cols: [[ //标题栏
                	{type: 'checkbox', fixed: 'left'}
                    , { field: 'type', title: '类别', align: 'center', width: 200 }
                    , { field: 'name', title: '名称', align: 'center', edit: 'text',width: 200 }
                    , { field: 'order', title: '显示顺序', align: 'center', edit: 'text',width: 200 }
                    , { field: 'status', title: '状态', align: 'center', edit: 'text',width: 180 }
                    , {fixed: 'right', width: 165, align:'center', toolbar: '#bar'}
                ]]
                , id: 'classReload'
	            , page: true
	            , limit: 50
	            , cellMinWidth: 80
	            , toolbar: '#toolbar' //开启头部工具栏，并为其绑定左侧模板
	            , defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
	                title: '提示'
	                , layEvent: 'LAYTABLE_TIPS'
	                , icon: 'layui-icon-tips'
	            }]
            });
          //头工具栏事件
            table.on('toolbar(classTable)', function (obj) {
                var checkStatus = table.checkStatus(obj.config.id);
                switch (obj.event) {
                    case 'getCheckData':
                        var data = checkStatus.data;
                        layer.alert(JSON.stringify(data));
                        break;
                    case 'getCheckLength':
                        var data = checkStatus.data;
                        layer.msg('选中了：' + data.length + ' 个');
                        break;
                    case 'isAll':
                        layer.msg(checkStatus.isAll ? '全选' : '未全选');
                        break;

                    //自定义头工具栏右侧图标 - 提示
                    case 'LAYTABLE_TIPS':
                        layer.alert('这是工具栏右侧自定义的一个图标按钮');
                        break;
                };
            });

            //监听行工具事件
            table.on('tool(classTable)', function (obj) {
                var data = obj.data;
                //console.log(obj)
                if (obj.event === 'del') {
                    layer.confirm('真的删除行么', function (index) {
                        obj.del();
                        layer.close(index);
                    });
                } else if (obj.event === 'edit') {
                    layer.prompt({
                        formType: 2
                        , value: data.email
                    }, function (value, index) {
                        obj.update({
                            email: value
                        });
                        layer.close(index);
                    });
                }
            });
            
          //触发事件
            var active = {
              setTop: function(){
                var that = this; 
                //多窗口模式，层叠置顶
                layer.open({
                  type: 2 //此处以iframe举例
                  ,title: '当你选择该窗体时，即会在最顶端'
                  ,area: ['390px', '260px']
                  ,shade: 0
                  ,maxmin: true
                  ,offset: [ //为了演示，随机坐标
                    Math.random()*($(window).height()-300)
                    ,Math.random()*($(window).width()-390)
                  ] 
                  ,content: 'addDict.jsp'
                  ,yes: function(){
                    $(that).click(); 
                  }
                  ,btn2: function(){
                    layer.closeAll();
                  }
                  
                  ,zIndex: layer.zIndex //重点1
                  ,success: function(layero){
                    layer.setTop(layero); //重点2
                  }
                });
              }
             
            };
            
            $('#layerDemo .layui-btn').on('click', function(){
              var othis = $(this), method = othis.data('method');
              active[method] ? active[method].call(this, othis) : '';
            });
            
            
        });
    </script>
</body>
</html>