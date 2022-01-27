<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
<form action="login" method="post">
<pre>
${msg} 
<h2>Login</h2>
User Name: <input type="text" name="email"/> <br/>
Password: <input type="password" name="password"/> <br/>
<input type="submit" value="Login" />


</pre>

</form>
</body>
</html>