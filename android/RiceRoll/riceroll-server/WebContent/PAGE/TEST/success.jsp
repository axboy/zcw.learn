<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html><body>
  <form action="" method="post">
    ����<input type="text" name="user.userName">
    ����<input type="password" name="user.passWord">
 ��֤��<input type="text" name="saveCode">
 <img id="saveCode" src="servlet.saveCode">
    <input type="submit"  value="�ύ">
  </form>
  <br>
  <s:property value="hello"/>
</body></html>
