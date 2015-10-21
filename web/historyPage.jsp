<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<head>
  <meta charset="utf-8">
  <title>历史轨迹</title>
  <link rel="stylesheet" type="text/css" href="css/jquery-ui.css" />
  <style type="text/css">
    body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
    a{color:#007bc4/*#424242*/; text-decoration:none;}
    a:hover{text-decoration:underline}
    ol,ul{list-style:none}
    body{height:100%; font:12px/18px Tahoma, Helvetica, Arial, Verdana, "\5b8b\4f53", sans-serif; color:#51555C;}
    img{border:none}
    .demo{width:500px; margin:20px auto}
    .demo h4{height:32px; line-height:32px; font-size:14px}
    .demo h4 span{font-weight:500; font-size:12px}
    .demo p{line-height:28px;}
    input{width:200px; height:20px; line-height:20px; padding:2px; border:1px solid #d3d3d3}
    pre{padding:6px 0 0 0; color:#666; line-height:20px; background:#f7f7f7}

    .ui-timepicker-div .ui-widget-header { margin-bottom: 8px;}
    .ui-timepicker-div dl { text-align: left; }
    .ui-timepicker-div dl dt { height: 25px; margin-bottom: -25px; }
    .ui-timepicker-div dl dd { margin: 0 10px 10px 65px; }
    .ui-timepicker-div td { font-size: 90%; }
    .ui-tpicker-grid-label { background: none; border: none; margin: 0; padding: 0; }
    .ui_tpicker_hour_label,.ui_tpicker_minute_label,.ui_tpicker_second_label,.ui_tpicker_millisec_label,.ui_tpicker_time_label{padding-left:20px}
  </style>

  <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=c3uCyaCcLuGS5wmOSkHWkpPS"></script>
  <script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
  <script type="text/javascript" src="js/jquery-ui.js"></script>
  <script type="text/javascript" src="js/jquery-ui-slide.min.js"></script>
  <script type="text/javascript" src="js/jquery-ui-timepicker-addon.js"></script>
  <script type="text/javascript">
    $(function() {
      $('#example_1').datetimepicker({
        showSecond: true,
        showMillisec: true,
        timeFormat: 'hh:mm:ss:l'
      });
      $('#example_2').datetimepicker({
        showSecond: true,
        showMillisec: true,
        timeFormat: 'hh:mm:ss:l'
      });
    })
  </script>
</head>

<body>
<div style="width: 100%; heigth: 100%; margin-top: 10px;">
  <div style="text-align: center;">
    <form name="input" action="historyRoute.do" method="post">
      请输入车辆对应的电话号码：<input type="text" name="drivertel">
      请选择开始时间:<input type="text" id="example_1" name="startTime" />
      请选择结束时间<input type="text" id="example_2" name="lastTime" />
      <input type="submit" value="确认"> <a href="queryAllUserCar.do">返回到查询页面</a>
    </form>
  </div>

  <div id="allmap" style="height:700px;"></div>
</div>
</body>
</html>
<script type="text/javascript">
  // 百度地图API功能
  var map = new BMap.Map("allmap");    // 创建Map实例
  map.centerAndZoom(new BMap.Point(117.228804,34.271097), 11);  // 初始化地图,设置中心点坐标和地图级别
  map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
  map.setCurrentCity("徐州");          // 设置地图显示的城市 此项是必须设置的
  map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放

  </script>