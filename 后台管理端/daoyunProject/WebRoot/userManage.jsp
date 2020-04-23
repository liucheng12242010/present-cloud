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
        <div class="layui-table" id="edit" style="margin-top:30px;">
            <fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
                <legend style="padding-top: 10px;">课程查询</legend>
            </fieldset>
          <div class="layui-form layui-card-header layuiadmin-card-header-auto">
            <div class="layui-form-item">
              <div class="layui-inline">
               <label class="layui-form-label">ID</label>
               <div class="layui-input-block">
               <input type="text" name="id" placeholder="请输入" autocomplete="off" class="layui-input">
              </div>
            </div>
            <div class="layui-inline">
             <label class="layui-form-label">用户名</label>
             <div class="layui-input-block">
              <input type="text" name="username" placeholder="请输入" autocomplete="off" class="layui-input">
             </div>
           </div>
          <div class="layui-inline">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-block">
              <input type="text" name="email" placeholder="请输入" autocomplete="off" class="layui-input">
            </div>
          </div>
          <div class="layui-inline">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
              <select name="sex">
                <option value="0">不限</option>
                <option value="1">男</option>
                <option value="2">女</option>
              </select>
            </div>
          </div>
          <div class="layui-inline">
            <button class="layui-btn layuiadmin-btn-useradmin" lay-submit lay-filter="LAY-user-front-search">
              <i class="layui-icon layui-icon-search layuiadmin-button-btn"></i>
            </button>
          </div>
        </div>
        <table id="FISTable" lay-filter="FISTable"></table>
        </div>
       </div>
    </div>
    <script src="Contents/layuiadmin/layui/layui.js"></script>
    <script type="text/JavaScript">
        layui.config({
            base: 'Contents/layuiadmin/' //静态资源所在路径
        }).extend({
            index: 'lib/index' //主入口模块
            }).use(['index', 'forum', 'laydate','table'], function () {
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
            form.on('submit(submitBtn)', function (data) {
                document.getElementById("submitBtn").disabled = true;
                var param = data.field;
                $.ajax({
                    type: 'post',
                    url: '/FIS/FuelPredictRouteHis',
                    data: param,
                    dataType: 'json',
                    success: function (res) {
                        document.getElementById("submitBtn").disabled = false;
                        if (res.Success == true) {
                            table.render({
                                elem: '#FISTable'
                                , cols: [[ //标题栏
                                    { field: 'MONTH', title: '月份', align: 'center', width: 100 }
                                    , { field: 'DAY', title: '日', align: 'center', width: 100 }
                                    , { field: 'HOUR', title: '小时', align: 'center', width: 100 }
                                    , { field: 'PLANE_CONFIG', title: '机型', align: 'center', width: 100 }
                                    , { field: 'DEPT', title: '始发机场', align: 'center', width: 100 }
                                    , { field: 'ARRV', title: '到达机场', align: 'center', width: 100 }
                                    , { field: 'PAYLOAD', title: '业载', align: 'center', width: 100 }
                                    , { field: 'PEOPLE', title: '人数', align: 'center', width: 100 }
                                    , { field: 'CARGO_TOT', title: '货载', align: 'center', width: 100 }
                                    , { field: 'DSTA_PLN', title: '计划航距', align: 'center', width: 100 }
                                    , { field: 'DSTA_ACT', title: '实际航距', align: 'center', width: 100 }
                                    , { field: 'FUEL_AIR_PLN', title: '计划油', align: 'center', width: 100 }
                                    , { field: 'FUEL_AIR_ACT', title: '实际油', align: 'center', width: 100 }
                                ]]
                                , data: res.predictModelRoute
                                , limit: 1
                            });
                            $("#FISResult").html("计划油耗：" + res.fuelPlan
                                + "<br>推理结果1：" + res.data1
                                + "<br>推理结果2：" + res.data2
                                + "<br>推理结果3：" + res.data3
                                + "<br>实际油耗：" + res.fuelAct);
                        } else {
                            layer.alert(res.Message, { icon: 5 });
                            $("#FISResult").html("推理失败，请重试或联系管理员！");
                        }
                    },
                    error: function () {
                        layer.alert('推理机连接失败', { icon: 5 });
                        document.getElementById("submitBtn").disabled = false;
                        $("#FISResult").html("推理失败，请重试或联系管理员！");
                    }
                });
            });
            form.on('select(DEPT)', function (data) {
                var arrv = document.getElementById("ARRV");
                var ymd = document.getElementById("YMD");
                if (data.value != "" && arrv.value != "") {
                    $.ajax({
                        type: 'post',
                        url: '/FIS/getPredictRouteHisFltnoData',
                        data: { DEPT: data.value, ARRV: arrv.value, YMD: ymd.value},
                        dataType: 'json',
                        success: function (res) {
                            if (res.Success == true) {
                                if (res.returnData.length > 0) {
                                    $("#FLT_NO").empty();
                                    for (var i = 0; i < res.returnData.length; i++) {
                                        $("#FLT_NO").append(new Option(res.returnData[i], res.returnData[i]));
                                    }
                                    layui.form.render("select");
                                }
                            } else {
                                layer.alert(res.Message, { icon: 5 });
                            }
                        },
                        error: function () {
                            layer.alert('获取航路路线失败', { icon: 5 });
                        }
                    });
                }
            });
            form.on('select(ARRV)', function (data) {
                var dept = document.getElementById("DEPT");
                var ymd = document.getElementById("YMD");
                if (data.value != "" && dept.value != "") {
                    $.ajax({
                        type: 'post',
                        url: '/FIS/getPredictRouteHisFltnoData',
                        data: { DEPT: dept.value, ARRV: data.value, YMD: ymd.value},
                        dataType: 'json',
                        success: function (res) {
                            if (res.Success == true) {
                                if (res.returnData.length > 0) {
                                    $("#FLT_NO").empty();
                                    for (var i = 0; i < res.returnData.length; i++) {
                                        $("#FLT_NO").append(new Option(res.returnData[i], res.returnData[i]));
                                    }
                                    layui.form.render("select");
                                }
                            } else {
                                layer.alert(res.Message, { icon: 5 });
                            }
                        },
                        error: function () {
                            layer.alert('获取航路路线失败', { icon: 5 });
                        }
                    });
                }
            });
        });
    </script>
</body>
</html>