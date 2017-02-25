<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="refresh"
	content="3;url=http://localhost:8080/RiceRoll/PAGE/HTML/login.html">
<title>访问出错</title>
</head>
<body>
	<%
		String result = (String) request.getParameter("errorcode");
	if(result==null){
		%><p align="left" style="color: red">登录超时，请重新登录</p>
		<%
	}else if (result == "1" || result.equals("1")) {
	%><p align="left" style="color: red">登录超时，请重新登录</p>
	<%
		} else if (result == "2" || result.equals("2")) {
	%><p align="left" style="color: red">请先登录后访问</p>
	<%
		} else if (result == "6023" || result.equals("6023")) {
	%><p align="left" style="color: red">发生未知错误请重试...</p>
	<%
		} else if (result == "2100" || result.equals("2100")) {
	%><p align="left" style="color: red">非法登录...</p>
	<%
		}
	%>
	<b style=color:blue>稍后页面将自动返回登录页面...</b>
</body>
</html>