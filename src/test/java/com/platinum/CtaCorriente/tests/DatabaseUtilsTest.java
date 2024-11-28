package com.platinum.CtaCorriente.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.platinum.CtaCorriente.utils.DatabaseUtils;

import java.sql.Connection;

public class DatabaseUtilsTest {

    @Test
    public void testDatabaseConnection() {
        try (Connection connection = DatabaseUtils.getConnection()) {
            assertNotNull(connection, "La conexión debería ser exitosa y no nula.");
            assertFalse(connection.isClosed(), "La conexión debería estar abierta.");
        } catch (Exception e) {
            fail("No se pudo establecer la conexión a la base de datos: " + e.getMessage());
        }
    }
    
/*
    @Test
    public void testInsertAccountWithInvalidExecutive() {
        try (Connection conn = DatabaseUtils.getConnection()) {
            String sql = "INSERT INTO ctaCorriente (rutCliente, ejecutivo) VALUES (?, ?)";
            try (var ps = conn.prepareStatement(sql)) {
                ps.setString(1, "22626647k"); // RUT válido en persona
                //ps.setDouble(2, 0.0);
                ps.setString(3, "NOMBRE_MALO"); // Ejecutivo inexistente 

                ps.executeUpdate();
                fail("La inserción debería haber fallado debido a ejecutivo inexistente.");
            }
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("foreign key constraint"), 
                       "El mensaje de error debería indicar ejecutivo inexistente.");
        }
    }
    
    @Test
    public void testInsertAccount() {
        try (Connection conn = DatabaseUtils.getConnection()) {
            String sql = "INSERT INTO ctaCorriente (rutCliente, ejecutivo) VALUES (?, ?)";
            try (var ps = conn.prepareStatement(sql)) {
                ps.setString(1, "22626647k"); // Este RUT existe en la tabla persona
                //ps.setDouble(2, 0.0);
                ps.setString(3, "andres"); // Nombre de ejecutivo existe en la tabla ejecutivo

                int rowsAffected = ps.executeUpdate();
                assertEquals(1, rowsAffected, "Debería haberse insertado una fila.");
            }
        } catch (Exception e) {
            fail("Error al insertar cuenta corriente: " + e.getMessage());
        }
    }*/
    
}