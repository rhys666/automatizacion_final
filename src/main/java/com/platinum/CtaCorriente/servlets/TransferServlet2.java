package com.platinum.CtaCorriente.servlets;

import com.platinum.CtaCorriente.utils.DatabaseUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;


public class TransferServlet2 extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rutCliente = request.getParameter("rutCliente");
        String rutDueno = request.getParameter("rutDueno");
        String cuentaTransferencia = request.getParameter("cuentaTransferencia");
        double monto = Double.parseDouble(request.getParameter("monto"));

        try (Connection conn = DatabaseUtils.getConnection()) {
            String sql = "INSERT INTO transaccion (rutCliente, rutDueño, cuentaTransferencia, montoTransferencia) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, rutCliente);
            ps.setString(2, rutDueno);
            ps.setString(3, cuentaTransferencia);
            ps.setDouble(4, monto);
            ps.executeUpdate();

            response.getWriter().write("Transferencia registrada!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}