<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<head>

  <style type="text/css">
    body, html,#allmap {width: 100%;height: 100%;overflow: hidden;margin:0;font-family:"微软雅黑";}
  </style>
  <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=c3uCyaCcLuGS5wmOSkHWkpPS"></script>
  <title>历史轨迹</title>
</head>
<body>
<div style="width: 100%; heigth: 100%; margin-top: 10px;">
  <div style="text-align: center;">
    <form name="input" action="historyRoute.do" method="post">
      请输入车辆对应的电话号码：<input type="text" name="drivertel">
      <input type="submit" value="确认"> <a href="queryAllUserCar.do">返回到查询页面</a>
    </form>

  </div>
  <div id="allmap" style="height:700px;"></div>
</div>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript">


</script>


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