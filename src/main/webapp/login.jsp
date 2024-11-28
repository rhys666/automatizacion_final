<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="login" method="post">
    <input type="text" id="username" name="username" placeholder="Nombre de usuario" required>
    <input type="password" id="password" name="password" placeholder="Contraseña" required>
    <button type="submit" id="loginButton">Ingresar</button>
</form>
<!-- Mostrar el mensaje de error si existe -->
<div id="errorMessage" style="color:red;">
   <%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "" %>
</div>
</body>
</html>