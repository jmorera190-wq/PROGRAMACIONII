package inventapro.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/inventapro";
    private static final String USER = "root";
    private static final String PASS = "admin"; // ESTA ES TU CONTRASEÑA REAL

    public static Connection conectar() {
        try {
            Connection cn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conectado a MySQL");
            return cn;
        } catch (Exception e) {
            System.out.println("Error al conectar: " + e.getMessage());
            return null;
        }
    }
}
