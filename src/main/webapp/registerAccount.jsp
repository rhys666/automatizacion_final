<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Registrar Cuenta Corriente</title>
</head>
<body>
    <h2>Registrar Cuenta Corriente</h2>
    <form action="RegisterAccountServlet" method="post">
        <label for="rutCliente">RUT Cliente:</label>
        <input type="text" id="rutCliente" name="rutCliente" required><br>

        <label for="ejecutivo">Nombre Ejecutivo:</label>
        <input type="text" id="ejecutivo" name="ejecutivo" required><br>

        <button type="submit">Registrar Cuenta</button>
    </form>
</body>
</html>	