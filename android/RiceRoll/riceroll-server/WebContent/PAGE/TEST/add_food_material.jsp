<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>食材</title>
</head>
<body>
<dir align="center">
   <s:form name="Form" action="addlink" method="post" namespace="/test">
     <s:textfield label="链接地址" name="link" title="请输入链接！"/> 
     <s:submit value="提交"/>
   </s:form>
</dir>
</body>
</html>