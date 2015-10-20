<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<head>

    <style type="text/css">
        body, html, #allmap {
            width: 100%;
            height: 100%;
            overflow: hidden;
            margin: 0;
            font-family: "微软雅黑";
        }
    </style>
    <script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=c3uCyaCcLuGS5wmOSkHWkpPS"></script>
    <title>历史轨迹</title>
</head>
<body>
<div style="width: 100%; heigth: 100%; margin-top: 10px;">
    <%--<div style="text-align: center;">--%>
    <%--请输入车辆编号:<input id="carID" type="text"/>--%>
    <%--<input type="button" value="确定" onclick="historyLoLa()">--%>
</div>
<div id="allmap" style="height:700px;"></div>
</div>
<script type="text/javascript" src="jquery.js"></script>
<script type="text/javascript">

    function drawRoute(slo, sla, flo, fla) {
        var driving = new BMap.DrivingRoute(map);    //驾车实例
        var myP1 = new BMap.Point(slo, sla);
        var myP2 = new BMap.Point(flo, fla);
        driving.search(myP1, myP2);    //显示一条公交线路
        driving.setSearchCompleteCallback(function(){
            var pts = driving.getResults().getPlan(0).getRoute(0).getPath();    //通过驾车实例，获得一系列点的数组
            var polyline = new BMap.Polyline(pts);
            map.addOverlay(polyline);

        });
    }
</script>


</body>
</html>
<script type="text/javascript">
    // 百度地图API功能
    var map = new BMap.Map("allmap");    // 创建Map实例
    var hisLoLa =${historyLoLa};
    var top_left_control = new BMap.ScaleControl({anchor: BMAP_ANCHOR_TOP_LEFT});


    map.centerAndZoom(new BMap.Point(hisLoLa[0].lo, hisLoLa[0].la), 11);  // 初始化地图,设置中心点坐标和地图级别
    map.addControl(top_left_control);
    map.addControl(new BMap.MapTypeControl());   //添加地图类型控件
    map.setCurrentCity("徐州");          // 设置地图显示的城市 此项是必须设置的
    map.enableScrollWheelZoom(true);     //开启鼠标滚轮缩放



    var FIRST_LENGTH =${FIRST_LENGTH};

    var FINAL_LENGTH =${FINAL_LENGTH};

    if (FIRST_LENGTH == 1) alert("车辆没有移动");
    else if (FINAL_LENGTH == 1) {
        //alert("车辆始终在1000米范围内移动，仅绘制起点和终点");
        var myP1 = new BMap.Point(hisLoLa[0].lo,hisLoLa[0].la);
        var myP2 = new BMap.Point(${flo},${fla});
        var driving = new BMap.DrivingRoute(map, {renderOptions:{map: map, autoViewport: true}});
        driving.search(myP1,myP2);
    }
    else {
        for (var i = 0; i < FINAL_LENGTH - 1; i++) {

            var START_LO = hisLoLa[i].lo;
            var START_LA = hisLoLa[i].la;
            var FINISH_LO = hisLoLa[i + 1].lo;
            var FINISH_LA = hisLoLa[i + 1].la;

//      var pt = new BMap.Point(START_LO, START_LA);
//      var myIcon = new BMap.Icon("icon_xuzhuang.png", new BMap.Size(50, 57), {
//        //anchor: new BMap.Size(10, 25)
//        imageOffset: new BMap.Size(0, 0)
//      });
//      var marker = new BMap.Marker(pt, {icon: myIcon});  // 创建标注
//      map.addOverlay(marker);              // 将标注添加到地图中
            drawRoute(START_LO, START_LA, FINISH_LO, FINISH_LA);




        }

//        setTimeout(function () {
//            map.clearOverlays;
//        }, "8000");

        var PT1=new BMap.Point(hisLoLa[0].lo,hisLoLa[0].la);
        var PT2=new BMap.Point(hisLoLa[FINAL_LENGTH-1].lo,hisLoLa[FINAL_LENGTH-1].la);
        var mark1=new BMap.Marker(PT1);
        var mark2=new BMap.Marker(PT2);
        alert("test");
        map.addOverlay(mark1);
        alert("mark1");
        map.addOverlay(mark2);


    }
</script>
