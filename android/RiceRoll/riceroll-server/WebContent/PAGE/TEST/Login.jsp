<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<dir align="center">
   <s:form name="loginForm" action="login" method="post" namespace="/user">
     <s:textfield label="用户名" name="user_name" title="请输入用户名！"/> 
     <s:textfield label="密码" name="user_password" title="请输入密码！"/> 
     <s:textfield label="用户类型" name="user_type" /> 
     <s:submit value="提交"/>
   </s:form>
</dir>
</body>
</html>