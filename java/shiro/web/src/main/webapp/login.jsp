<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
</head>
<body>
    <div>${msg}</div>
    <form method="post" action="/login">
        username:<input type="text" name="username"><br>
        password:<input type="text" name="password"><br>
        <input type="submit" value="login">
    </form>
</body>
</html>