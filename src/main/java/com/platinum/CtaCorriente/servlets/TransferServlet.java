package com.platinum.CtaCorriente.servlets;

import com.platinum.CtaCorriente.utils.DatabaseUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class TransferServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rutCliente = request.getParameter("rutCliente");
        String rutDueno = request.getParameter("rutDueno");
        String cuentaTransferencia = request.getParameter("cuentaTransferencia");
        double monto = Double.parseDouble(request.getParameter("monto"));

        try (Connection conn = DatabaseUtils.getConnection()) {
            // Verifica si rutCliente existe en la tabla persona
            String checkClienteSQL = "SELECT COUNT(*) FROM persona WHERE rut = ?";
            try (PreparedStatement checkClienteStmt = conn.prepareStatement(checkClienteSQL)) {
                checkClienteStmt.setString(1, rutCliente);
                ResultSet rs = checkClienteStmt.executeQuery();
                if (rs.next() && rs.getInt(1) == 0) {
                    displayError(response, "El rutCliente no existe en la base de datos.");
                    return;
                }
            }

            // Verifica si rutDueno existe en la tabla persona
            String checkDuenoSQL = "SELECT COUNT(*) FROM persona WHERE rut = ?";
            try (PreparedStatement checkDuenoStmt = conn.prepareStatement(checkDuenoSQL)) {
                checkDuenoStmt.setString(1, rutDueno);
                ResultSet rs = checkDuenoStmt.executeQuery();
                if (rs.next() && rs.getInt(1) == 0) {
                    displayError(response, "El rutDueno no existe en la base de datos.");
                    return;
                }
            }

            // Verifica si cuentaTransferencia existe en la tabla ctaCorriente
            String checkCuentaSQL = "SELECT COUNT(*) FROM ctaCorriente WHERE idCuenta = ?";
            try (PreparedStatement checkCuentaStmt = conn.prepareStatement(checkCuentaSQL)) {
                checkCuentaStmt.setString(1, cuentaTransferencia);
                ResultSet rs = checkCuentaStmt.executeQuery();
                if (rs.next() && rs.getInt(1) == 0) {
                    displayError(response, "La cuentaTransferencia no existe en la base de datos.");
                    return;
                }
            }

            // Inserta la transacción en la tabla transaccion
            String insertSQL = "INSERT INTO transaccion (rutCliente, rutDueño, cuentaTransferencia, montoTransferencia) VALUES (?, ?, ?, ?)";
            try (PreparedStatement insertStmt = conn.prepareStatement(insertSQL)) {
                insertStmt.setString(1, rutCliente);
                insertStmt.setString(2, rutDueno);
                insertStmt.setString(3, cuentaTransferencia);
                insertStmt.setDouble(4, monto);
                insertStmt.executeUpdate();
            }

            response.getWriter().write("Transferencia registrada exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            displayError(response, "Error al procesar la transferencia: " + e.getMessage());
        }
    }

    private void displayError(HttpServletResponse response, String errorMessage) throws IOException {
        response.setContentType("text/html");
        response.getWriter().write("<html><body>");
        response.getWriter().write("<h3>Error: " + errorMessage + "</h3>");
        response.getWriter().write("<button onclick=\"window.location.href='/CtaCorriente/transfer.jsp';\">Volver al menú</button>");
        response.getWriter().write("</body></html>");
    }
}