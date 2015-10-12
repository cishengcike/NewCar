<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<!--地图-->
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="viewport" content="initial-scale=1.0, user-scalable=no" />

		<style type="text/css">
			body,html,#allmap {
				width: 100%;
				height: 100%;
				overflow: hidden;
				margin: 0;
				font-family: "微软雅黑";
			}
		</style>

		<title>根据城市名设置地图中心点</title>

		<!--引入Jquery生产版（最小化和压缩版）-->
		<script type="text/javascript" src="js/jquery.min.js"></script>

		<!--Jquery esayui  -->
		<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.min.js"></script>
		<script type="text/javascript" src="jquery-easyui-1.3.3/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="jquery-easyui-1.3.3/locale/easyui-lang-zh_CN.js"></script>
		<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/bootstrap/easyui.css" />
		<link rel="stylesheet" type="text/css" href="jquery-easyui-1.3.3/themes/icon.css" />

		<%--地图--%>
		<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=IjWZq77w7xLj9ginwg2tA8RM"></script>
		<script type="text/javascript" src="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.js"></script>
		<link rel="stylesheet" href="http://api.map.baidu.com/library/SearchInfoWindow/1.5/src/SearchInfoWindow_min.css" />


	</head>
	<body>
		<style type="text/css">
			table.gridtable {
				font-family: verdana, arial, sans-serif;
				font-size: 11px;
				color: #333333;
				border-width: 1px;
				border-color: #666666;
				border-collapse: collapse;
				overflow: scroll;
				height: 200px;
			}

			table.gridtable th {
				border-width: 1px;
				border-style: solid;
				border-color: #666666;
				background-color: #dedede;
				text-align: center;
				width: 100px;
				height: 30px;
			}

			table.gridtable td {
				border-width: 1px;
				padding: 8px;
				border-style: solid;
				border-color: #666666;
				background-color: #ffffff;
			}
		</style>

		<!-- 打开修改密码对话框 -->
		<script type="text/javascript">
			function openUpdatePwd() {
				$('#dlg').dialog('open');

			}
			<!-- 修改密码 -->
			function updatePwd(id){
				if($("#usernameUpdatePwd").val().trim().length<=0){
					alert("用户名称不能为空!!!");
					return ;
				}
				if($("#oldPwd").val()!="${user['PASSWORD']}"){
					alert("旧密码输入错误!!!");
					$("#oldPwd").val("");
					$("#oldPwd").focu();
					return;
				}
				if($("#pwd").val().trim().length<=0){
					alert("新密码不能为空!!!");
					return ;
				}
				if($("#pwd").val().trim().length<=6){
					alert("新密码至少六位以上!!!");
					return ;
				}

				if($("#pwd").val()!=$("#newPwd").val()){
					alert("两次密码输入不一样，请重新输入!!!");
					$("#pwd").val("");
					$("#newPwd").val("");
					return ;
				}
				$.ajax({
					type:"post",
					dataType:"text",
					url:"updateUserPwd.do",
					data:{"userID":id,"new_password":$("#pwd").val()},
					success:function(msg){
						if(msg>0){
							alert("修改成功");
							$('#dlg').dialog('close');
						}else{
							alert("修改失败");
						}
					}
				});
			}
			function  queryMapUser(){//aType,aPhone,aDistance
				var aType=document.getElementById("queryMapUserType").value;
				var aPhone=document.getElementById("queryMapUserPhone").value;
				var aDistance=document.getElementById("queryMapUserDistance").value;
				location.href=encodeURI("queryMapNoddessUser.do?" +
								"userID=" + ${user['USERID']}+
								"&userTypeQuery=" + aType+
								"&kmNumber=" + aDistance+
								"&tel="      + aPhone +
								"&lo="       + ${user['LO']} +
								"&la="       + ${user['LA']});
			}

		</script>


		<div id="dlg" class="easyui-dialog" title="修改密码"
			 data-options="iconCls:'icon-ok',resizable:false,closed:true,modal:true,draggable:false"
			 style="width: 430px; height: 220px; padding: 10px;">
			<table align="center">
				<tbody>
				<tr>
					<th>用户名:</th>
					<td><input class="text" maxlength="20"
							   value="${user['USERNAME']}" id="usernameUpdatePwd" /></td>
				</tr>
				<tr>
					<th>旧密码:</th>
					<td><input class="text" maxlength="20" id="oldPwd" /></td>
				</tr>
				<tr>
					<th>新密码:</th>
					<td><input class="text" maxlength="20" id="pwd" /></td>
				</tr>
				<tr>
					<th>确认密码:</th>
					<td><input class="text" maxlength="20" id="newPwd" /></td>
				</tr>
				<tr>
					<td colspan="2" style="text-align: center;"><input
							type="button" value="修改" onclick="updatePwd(${user['USERID']})" /></td>
				</tr>
				</tbody>
			</table>
		</div>

		<div style="width: 100%; heigth: 100%; margin-top: 10px;">
			<div style="text-align:center;">
				欢迎 <span style="font-weight: bold; color: red">${user['USERNAME']}</span>登陆BDS/GPS后台系统!!!
				<a href="javascript:openUpdatePwd()">修改密码</a>
				<a href="deleteUserByTel.do?tel=${user['TEL']}">注销用户</a>
				<c:if test="${user['TYPE']=='3'}">
					<a href="historyPage.do">查询历史轨迹</a>
				</c:if>

			</div>
			<div style="height: 40px; text-align: center;">
				用户类型:<select  style="width:130px;" id="queryMapUserType">
							<option value="0"
									<c:if test="${MAPUSERTYPEQUERY==1}">selected='selected'</c:if>>农机手</option>
							<option value="1"
									<c:if test="${MAPUSERTYPEQUERY==0}">selected='selected'</c:if>>农户</option>
							<option value="2"
									<c:if test="${MAPUSERTYPEQUERY==-1}">selected='selected'</c:if>>全部</option>
						</select>
				手机号码:<input type="text" id="queryMapUserPhone"/>
				距离范围:<input type="text" value="${KMNUMBER}" id="queryMapUserDistance" />公里
				<input type="button" value="查询" onclick="queryMapUser()" />
			</div>

			<div id="allmap" style="height: 700px;"></div>
		</div>

		<script type="text/javascript">
			var temp="";
			$(function (){
				//自定义标注
				function addMarker(point,conten,opts){  // 创建图标对象
					var myIcon = new BMap.Icon("user.png", new BMap.Size(50,50), {
						offset: new BMap.Size(10, 25),
						imageOffset: new BMap.Size(0,0)   // 设置图片偏移
					});
						// 创建标注对象并添加到地图
					var marker = new BMap.Marker(point, {icon: myIcon});
					map.addOverlay(marker);
					var infoWindow=new BMap.InfoWindow(conten,opts);//创建信息窗口对象
					marker.addEventListener("click",function(){//创建标注的监听
						map.openInfoWindow(infoWindow,point);//开启信息窗口
					});
				}

				var loMap="${user['LO']}";
				var laMap="${user['LA']}";
				if((loMap===temp)||(laMap===temp))
				{
					loMap="121.0";
					laMap="31.0";
				}
				var map = new BMap.Map("allmap");          // 创建地图实例
				var pointNow = new BMap.Point(loMap,laMap);  // 创建点坐标
				map.centerAndZoom(pointNow, 15);                 // 初始化地图，设置中心点坐标和地图级别
				var opts={
					width:200,
					height:200
				}
				var contenStart="<div style='height:100px;'>"+
						"<table	class='gridtable'> "+
						"<thead>"+
						"<tr>"+
							"<th>用户名</th>"+
							"<th>电话号码</th>"+
						"</tr>"+
						"</thead>"+
						"<tbody>";
				var conten=contenStart+
						"<tr>" +
							"<td>"+"${user['USERNAME']}"+"</td>"+
							"<td>"+"${user['TEL']}"	+"</td>"+
						"</tr>";

				contenEnd="</tbody></table></div>";
				conten+=contenEnd;
				addMarker(pointNow,conten,opts);//添加自定义标注

				//获取查询到的用户信息
				var farmerMapTemp='${farmer}';
				var farmerSizeMap="${farmerSize}";
				var driverMapTemp='${driver}';
				var driverSizeMap="${driverSize}";
				var userByPhoneMap='${userByPhoneWeb}';

				var pointQuery=new Array();
				var markerQuery=new Array();
				var contentQuery=new Array();
				var infoWin=new Array();
				var i;


				if(!(userByPhoneMap===temp)) {
					var userByPhone=eval(userByPhoneMap);
					document.getElementById("queryMapUserPhone").value =userByPhone[0]['PHONE'];
					pointQuery[0]=new window.BMap.Point(userByPhone[0]['LO'],userByPhone[0]['LA']);
					contentQuery[0] = contenStart +
							"<tr>" +
							"<td>" + userByPhone[0]['USERNAME'] + "</td>" +
							"<td>" + userByPhone[0]['PHONE'] + "</td>" +
							"</tr>";
					contentQuery[0] += contenEnd;
					markerQuery[0]=new BMap.Marker(pointQuery[0]);
					map.addOverlay(markerQuery[0]);
					infoWin[0]=new BMap.InfoWindow(contentQuery[0],opts);
					markerQuery[0].addEventListener("click", function () {
						map.openInfoWindow(infoWin[0],pointQuery[0]);
					});
				}
				else {
					if (!(farmerMapTemp === temp)) {

						var farmerMap = eval(farmerMapTemp);
						for (i = 0; i < farmerSizeMap; i++) {
							pointQuery[i] = new window.BMap.Point(farmerMap[i]['LO'], farmerMap[i]['LA']);
							contentQuery[i] = contenStart +
									"<tr>" +
									"<td>" + farmerMap[i]['USERNAME'] + "</td>" +
									"<td>" + farmerMap[i]['PHONE'] + "</td>" +
									"</tr>";
							contentQuery[i] += contenEnd;
							var username=farmerMap[i]['USERNAME'];
							markerQuery[i] = new BMap.Marker(pointQuery[i]);
							map.addOverlay(markerQuery[i]);
							var label = new BMap.Label(username,{offset:new BMap.Size(20,-10)});
							markerQuery[i].setLabel(label);

							infoWin[i] = new BMap.InfoWindow(contentQuery[i], opts);
							markerQuery[i].addEventListener("click", (function (k) {
								return function () {
									map.openInfoWindow(infoWin[k], pointQuery[k]);
								}

							})(i));
						}
					}
					if (!(driverMapTemp === temp)) {
						var driverMap = eval(driverMapTemp);
						for (i = 0; i < driverSizeMap; i++) {
							pointQuery[i] = new window.BMap.Point(driverMap[i]['LO'], driverMap[i]['LA']);
							contentQuery[i] = contenStart +
									"<tr>" +
									"<td>" + driverMap[i]['USERNAME'] + "</td>" +
									"<td>" + driverMap[i]['PHONE'] + "</td>" +
									"</tr>";
							contentQuery[i] += contenEnd;
							var username = driverMap[i]['USERNAME'];
							var teamID=driverMap[i]['TEAMID'];
							if(teamID==1)
								var myIcon = new BMap.Icon("icon_xuzhuang.png",new BMap.Size(50,50));
							else
								var myIcon = new BMap.Icon("car.png", new BMap.Size(50,50));
							markerQuery[i] = new BMap.Marker(pointQuery[i],{icon:myIcon});
							map.addOverlay(markerQuery[i]);
							var label = new BMap.Label(username,{offset:new BMap.Size(20,-10)});
							markerQuery[i].setLabel(label);
							infoWin[i] = new BMap.InfoWindow(contentQuery[i], opts);
							markerQuery[i].addEventListener("click", (function (k) {
								return function () {
									map.openInfoWindow(infoWin[k], pointQuery[k]);
								}

							})(i));
						}
					}
				}

			});

		</script>
	</body>
</html>