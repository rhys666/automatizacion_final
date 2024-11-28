<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="transfer" method="post">
	    <input type="text" name="rutCliente" placeholder="RUT Cliente" required>
	    <input type="text" name="rutDueno" placeholder="RUT Dueño de la cuenta" required>
	    <input type="text" name="cuentaTransferencia" placeholder="Número de cuenta" required>
	    <input type="number" step="0.01" name="monto" placeholder="Monto" required>
	    <button type="submit">Transferir</button>
	</form>
</body>
</html>