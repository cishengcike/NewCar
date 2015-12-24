<%--
  Created by IntelliJ IDEA.
  User: SteinbockA
  Date: 2015/10/20 0020
  Time: 18:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

</body>
</html>
<script>
  var code=${errorCode};
  if(code==1)
  { alert("请返回并输入正确的sim卡号");
    location.href='historyPage.do';
  }
  else {
    alert("在该时间内车辆没有上传经纬度");
    location.href='historyPage.do';
  }

</script>