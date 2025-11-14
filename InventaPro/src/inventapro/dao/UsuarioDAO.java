package inventapro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UsuarioDAO {

    public boolean login(String user, String pass) {
        String sql = "SELECT * FROM usuarios WHERE username=? AND clave=?";

        try (Connection cn = Conexion.conectar();
             PreparedStatement pst = cn.prepareStatement(sql)
        ) {
            pst.setString(1, user);
            pst.setString(2, pass);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                System.out.println("Login correcto para: " + user);
                return true;
            } else {
                System.out.println("Login fallido para: " + user + " / " + pass);
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error en login: " + e.getMessage());
            return false;
        }
    }
}
