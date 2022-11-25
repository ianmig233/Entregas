package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ej4_rollback {
    //realiza una transacción que no se complete al provocar un rollback.

    public static void main(String[] args) throws SQLException {
        Connection conexion = ConnectionPool.getInstance().getConnection();

        String sql = "insert into serie(cod,nombre,temporadas) values (4,Rick y Morty, 4)"+
                "insert into serie(cod,nombre,temporadas) values (4,Rick y Morty, 4)"
                +"insert into serie(cod,nombre,temporadas) values (4,Rick y Morty, 4)"
                +"insert into serie(cod,nombre,temporadas) values (4,Rick y Morty, 4)";

        try {

            conexion.setAutoCommit(false);
            PreparedStatement stmt = conexion.prepareStatement(sql);
            stmt = conexion.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE, PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.executeUpdate(sql);

            ResultSet rs = stmt.getGeneratedKeys();
            rs.next();
            stmt.close();
            rs.first();

            stmt = conexion.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE, PreparedStatement.RETURN_GENERATED_KEYS);

            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            rs.next();
            stmt.close();
            rs.first();
            conexion.commit();

        } catch (Exception e) {
            try {
                conexion.rollback();
                System.out.println("No esta bien manin");
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

        } finally {
            try {
                conexion.setAutoCommit(true);

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

    }
}
