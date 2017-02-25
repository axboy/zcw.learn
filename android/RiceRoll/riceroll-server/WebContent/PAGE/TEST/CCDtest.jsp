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
   <s:form name="loginForm" action="/user/userCCD" method="post">
     <s:textfield label="菜谱id" name="cookbook_id" title="请输入密码！"/> 
     <s:textfield label="操作类型" name="type" /> 
     <s:submit value="提交"/>
   </s:form>
</dir>
</body>
</html>