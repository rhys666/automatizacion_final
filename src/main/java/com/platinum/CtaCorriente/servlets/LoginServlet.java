package com.platinum.CtaCorriente.servlets;

import com.platinum.CtaCorriente.utils.DatabaseUtils;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class LoginServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.sendRedirect("login.jsp"); // Cambia a tu página de inicio de sesión.
	}
	
	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try (Connection conn = DatabaseUtils.getConnection()) {
            String sql = "SELECT * FROM usuario WHERE nombreUsuario = ? AND password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String role = rs.getString("rol");

                // Guardar datos en sesión
                request.getSession().setAttribute("username", username);
                request.getSession().setAttribute("role", role);

                // Redirigir según el rol
                if ("ejecutivo".equalsIgnoreCase(role)) {
                    response.sendRedirect("menuEjecutivo.jsp");
                } else {
                    response.sendRedirect("menuUsuario.jsp");
                }
            } else {
                //response.getWriter().write("Credenciales inválidas.");
                request.setAttribute("errorMessage", "Credenciales inválidas.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("Error al procesar la solicitud.");
        }
    }
}