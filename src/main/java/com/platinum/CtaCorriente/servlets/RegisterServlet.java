package com.platinum.CtaCorriente.servlets;

import com.platinum.CtaCorriente.utils.DatabaseUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class RegisterServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rut = request.getParameter("rut");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String direccion = request.getParameter("direccion");
        String correo = request.getParameter("correo");
        String telefono = request.getParameter("telefono");
        String mascota = request.getParameter("mascota");

        try (Connection conn = DatabaseUtils.getConnection()) {
            String sqlPersona = "INSERT INTO persona (rut, nombre, apellido, direccion, correo, telefono, nombreMascota) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement psPersona = conn.prepareStatement(sqlPersona);
            psPersona.setString(1, rut);
            psPersona.setString(2, nombre);
            psPersona.setString(3, apellido);
            psPersona.setString(4, direccion);
            psPersona.setString(5, correo);
            psPersona.setString(6, telefono);
            psPersona.setString(7, mascota);
            psPersona.executeUpdate();

            response.getWriter().write("Registro exitoso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}