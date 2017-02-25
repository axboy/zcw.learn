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
<dir >
   <s:form name="loginForm" action="updateuserinfo"  enctype="multipart/form-data" method="post" namespace="/user">
     <s:textfield label="新用户名" name="new_user_name" title="请输入新用户名！"/> 
     <s:textfield label="密码" name="user_password" title="请输入新密码！"/> 
     <s:textfield label="手机号" name="user_phonenumber" title="请输入新手机号！"/> 
     <s:textfield label="地址" name="user_address" title="请输入新地址！"/> 
     <s:file label="头像" name="user_headimage" /> 
     <s:submit value="提交"/>
   </s:form>
</dir>
</body>
</html>