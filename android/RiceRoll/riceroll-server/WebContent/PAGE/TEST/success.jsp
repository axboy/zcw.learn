<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html><body>
  <form action="" method="post">
    姓名<input type="text" name="user.userName">
    密码<input type="password" name="user.passWord">
 验证码<input type="text" name="saveCode">
 <img id="saveCode" src="servlet.saveCode">
    <input type="submit"  value="提交">
  </form>
  <br>
  <s:property value="hello"/>
</body></html>
