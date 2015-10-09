<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>register page</title>
    <link href="main.css" rel="stylesheet">
    <link href="style.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/jquery.min.js"></script>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>

	<script type="text/javascript">
		function addUser(){
			if($("#userName").val().trim().length<=0){
				alert("用户名不能为空!!!");
				return ;
			}

			if($("#phone").val().trim().length<=0){
				alert("电话号码不能为空!!!");
				return ;
			}
			
			if($("#userPwd").val().trim().length<=0 ){
				alert("密码不能为空!!!");
				return ;
			}
			if($("#userPwd").val().trim().length<6 ){
				alert("密码长度不能低于6位!!!");
				return ;
			}
			if($("#userPwdTwo").val().trim()!=$("#userPwd").val().trim()){
				alert("两次密码输入不一样!!!");
				return ;
			}
			if($("#sussessss").attr("checked")!="checked"){
				alert("阅读并同意以下条款");
				return ;
			}
				var str ="";
				if($("#type").val()=="1"){//为农机手类型时，保存农机类型到str
					str=$("#carTypeId").val();
					alert(str+"htt htt HTT");
				}

			$.ajax({
		        type:"post",
		        dataType:"text",
		        url:"addUser.do",
		        data:{"phone":$("#phone").val(),"pwd":$("#userPwd").val(),
		        		"userName":$("#userName").val(),"userType":$("#type").val(),"carType":str},
		        success:function(msg){
		        	 alert(msg);
					location.href=encodeURI("returnIndex.do");
		        }
		     });
			}
		 
			function updateTr(){
				if($("#type").val()=="0"){/*选择农户注册*/
					document.getElementById("nongjitype").style.display="none";
				}else if($("#type").val()=="1"){/*选择农机手*/
					document.getElementById("nongjitype").style.display="";
				}
			}
			
		 
	</script>

	<div class="container">
	    <div class="loginbox">
	      <div class="loginsignTitle">新用户注册</div>
	      <div class="logintop"></div>
	      <div class="logincontent">
	       <table width="560" border="0" style="margin:0 auto;">
	          <tbody>
	            <tr>
	              <td width="80">用户名：</td>
	              <td width="300"><input type="text"   id="userName" maxlength="20" placeholder="请填写您的真实姓名"></td>
	              <td width="220">&nbsp;</td>
	            </tr>
	          
	            <tr>
	              <td>手机号：</td>
	              <td><input type="text"   id="phone"  onkeyup="value=value.replace(/[^\d]/g,'')"
	              						maxlength="11" placeholder="请输入11位手机号码"></td>
	              <td>&nbsp;</td>
	            </tr>
	            <tr>
	              <td>登录密码：</td>
	              <td><input type="password" value=""  id="userPwd" placeholder="请输入6~20位字母或数字" maxlength="20"></td>
	              <td>&nbsp;</td>
	            </tr>
	            <tr>
	              <td>确认密码：</td>
	              <td><input type="password" value="" id="userPwdTwo"   placeholder="请再次输入"></td>
	              <td>&nbsp;</td>
	            </tr>
	             <tr>
	              <td>类型：</td>
	              <td><select id="type" style="width:130px;"   onchange="updateTr()" >
								<option value="0" selected="selected">农户</option>
								<option value="1">农机手</option>
						</select></td>
	              <td>&nbsp;</td>
	            </tr>
	            
	             <tr id="nongjitype" style="display: none">
		              <td>农机类型：</td>
		              <td>
		              		<select  style="width:130px;" id="carTypeId">
		               			<c:forEach items="${CARTYPE}" varStatus="i" var="temp">
									<option value="${temp.TYPENAME}" >${temp.TYPENAME }</option>
								</c:forEach>
							</select>
					   </td>
		               <td>&nbsp;</td>
	            </tr>
	             
	            <tr>
	              <td></td>
	              <td ><input type="checkbox" id="sussessss">&nbsp;&nbsp;我以阅读并同意接受《查看条款》</td>
	            </tr>
	            <tr>
	              <td>&nbsp;</td>
	              <td>
	               <a class="start btn btn-primary btn-large" style="width:300px;" href="javascript:addUser()"><span >免费注册</span> </a>
	               </td>
	               <td>&nbsp;</td>
	            </tr>
	          </tbody>
	        </table>
	      </div>
	      <div class="loginbot"></div>
	    </div>
	</div>

</body>
</html>
