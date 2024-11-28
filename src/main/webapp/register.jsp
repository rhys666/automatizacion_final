<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="register" method="post">
	    <input type="text" name="rut" placeholder="RUT" required>
	    <input type="text" name="nombre" placeholder="Nombre" required>
	    <input type="text" name="apellido" placeholder="Apellido" required>
	    <input type="text" name="direccion" placeholder="Dirección" required>
	    <input type="email" name="correo" placeholder="Correo electrónico" required>
	    <input type="text" name="telefono" placeholder="Teléfono" required>
	    <input type="text" name="mascota" placeholder="Nombre de tu mascota" required>
	    <button type="submit">Registrar</button>
	</form>
</body>
</html>