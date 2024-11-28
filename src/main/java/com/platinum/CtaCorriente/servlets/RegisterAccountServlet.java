package com.platinum.CtaCorriente.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.platinum.CtaCorriente.utils.DatabaseUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterAccountServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtén los parámetros del formulario
        String rutCliente = request.getParameter("rutCliente");
        String ejecutivo = request.getParameter("ejecutivo");

        if (rutCliente == null || ejecutivo == null || rutCliente.isEmpty() || ejecutivo.isEmpty()) {
            response.getWriter().write("Todos los campos son obligatorios.");
            return;
        }

        try (Connection conn = DatabaseUtils.getConnection()) {
            // Inserta la cuenta corriente en la base de datos
            String sql = "INSERT INTO ctaCorriente (rutCliente, ejecutivo) VALUES (?, ?)";
            try (PreparedStatement ps = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
                ps.setString(1, rutCliente);
                ps.setString(2, ejecutivo);
                int rowsInserted = ps.executeUpdate();

                if (rowsInserted > 0) {
                    try (ResultSet rs = ps.getGeneratedKeys()) {
                        if (rs.next()) {
                            int idCuenta = rs.getInt(1); // Obtén el ID generado
                            response.getWriter().write("Cuenta registrada exitosamente. ID de la cuenta: " + idCuenta);
                        }
                    }
                } else {
                    response.getWriter().write("Error al registrar la cuenta.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error al procesar la solicitud: " + e.getMessage());
        }
    }
}