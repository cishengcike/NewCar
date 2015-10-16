<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>农机定位调度平台</title>
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
    <%--<script type="text/javascript" src="chur.min.js"></script>--%>
    <link rel="stylesheet" type="text/css" href="login.css" />
    <script src="http://siteapp.baidu.com/static/webappservice/uaredirect.js" type="text/javascript">
    </script><script type="text/javascript">uaredirect("indexPhone.jsp");</script>
    <script type="text/javascript" language="javascript">
	function loginPwd(){
		$.ajax({
	        type:"post",
	        dataType:"text",
	        url:"loginInfo.do",
	        data:{"phone":$("#phone").val(),"pwd":$("#pwd").val()},
	        success:function(msg){
	        	if(msg==-5){
	        		alert("用户名不存在");
	        		return;
	        	}
	        	if(msg>0){
	        		alert("登陆成功!!!");
	        		location.href="queryAllUserCar.do";
	        	}else{
	        		
	        		alert("用户名或密码错误!!!");
	        	}
	        }
	     });
	}
	
	function zhuce(){
		
		location.href="queryCarTypeInfo.do";
	}
	function shouji(){
		
		location.href="NongJi.apk";
	}
	function bangzhu(){
		
		location.href="help20140929.docx";
	}
	</script>
    
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
          
        <input type="button" value=" 登 录 " class="btn btn-primary btn-large login" onclick="loginPwd()" />
         <input type="button" value=" 注 册 " class="btn btn-primary btn-large login" onclick="zhuce()" />
    </div>  
    </form>
  </body>
</html>
