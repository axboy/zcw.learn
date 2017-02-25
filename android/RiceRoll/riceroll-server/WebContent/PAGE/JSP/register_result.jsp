<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="refresh"
	content="3;url=http://localhost:8080/RiceRoll/PAGE/HTML/register.html">
<title>注册失败</title>
</head>
<body>
	<%
		String result = request.getParameter("result");
		if (result == "6012" || result.equals("6012")) {
	%><p align="left" style="color: red">用户名已存在...</p>
	<%
		} else if (result == "6011" || result.equals("6011")) {
	%><p align="left" style="color: red">注册时出现未知错误请重试...</p>
	<%
		}
	%>
	<b style="color: blue">稍后页面将自动返回注册页面...</b>
</body>
</html>