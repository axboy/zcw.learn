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
   <s:form name="regForm" action="/user/feedback" method="post">
    <s:textfield label="用户id" name="feedback_user_id" title=""/> 
     <s:textfield label="反馈内容" name="feedback_content" title=""/> 
     <s:textfield label="反馈关键词" name="feedback_keyword" title=""/> 
     <s:submit value="提交"/>
   </s:form>
</dir>
</body>
</html>