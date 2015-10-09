 
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>农机定位调度平台</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<link rel="stylesheet" type="text/css" href="base.css" />
    <link rel="stylesheet" type="text/css" href="admin-all.css" />
    <link rel="stylesheet" type="text/css" href="bootstrap.min.css" />
    <script type="text/javascript" src="jquery.js"></script> 
    <script type="text/javascript" src="jquery.spritely-0.6.js"></script>
    <script type="text/javascript" src="chur.min.js"></script>
    <link rel="stylesheet" type="text/css" href="login.css" />
    <!-- <script src="http://siteapp.baidu.com/static/webappservice/uaredirect.js" type="text/javascript">
    </script><script type="text/javascript">uaredirect("index.jsp");</script> -->
<script type="text/javascript" language="javascript">
function loginPwd(){
	$.ajax({
        type:"post",
        dataType:"text",
        url:"loginInfo.do",
        data:{"phone":$("#phone").val(),"pwd":$("#pwd").val()},
        success:function(msg){
        	if(msg>0){
        		alert("登陆成功!!!");
        		location.href="queryAllUserCar.do";
        	}else{
        		
        		alert("登陆失败!!!");
        	}
        }
     });
}

function zhuce(){
	
	location.href="queryTypeInfo.do"
}
function shouji(){
	
	location.href="NongJi.apk"
}
function bangzhu(){
	
	location.href="help20140929.docx"
}
</script>

<!-- <script type="text/javascript">
	function change(){
		var img=document.getElementById("ma");
		img.src=img.src+"?";
	}
</script> -->
</head>
  
<body>
	<form action="" method="post" id="fm">
	    <div id="clouds" class="stage"></div>
	    <div class="loginmain">
	    </div>
    <div class="row-fluid">
        <h1>农机定位调度平台</h1>
        <p>
            <label>帐&nbsp;&nbsp;&nbsp;号：<input type="text"   id="phone"  name="username" style="height:30px" /></label>
        </p>
        <p>
            <label>密&nbsp;&nbsp;&nbsp;码：<input type="password"  id="pwd" name="password" style="height:30px" /></label>
        </p>
        <br>
          <p><span style="font-size:25px;font-weight:bold;color:blue;cursor:pointer;" onclick="shouji()">下载手机端</span>
        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
          <span style="font-size:25px;font-weight:bold;color:blue;cursor:pointer;" onclick="bangzhu()">下载帮助文档</span> </p>
          <br>
          <!--  <type type="text" value="hahaha" style="hidden:none;" /> -->
        <input type="button" value=" 登 录 " class="btn btn-primary btn-large login" onclick="loginPwd()" />
         <input type="button" value=" 注 册 " class="btn btn-primary btn-large login" onclick="zhuce()" />
    </div>  
    </form>
</body>
</html>




