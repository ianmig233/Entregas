package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Generar_datos {

    public static void main(String[] args) {
        String sql = "INSERT INTO series(cod,nombre,temporadas) VALUES (?, ?, ?)";

        try (Connection conexion = ConnectionPool.getInstance().getConnection();
        PreparedStatement sentencia = conexion.prepareStatement(sql)){


            sentencia.setInt(1, 1);
            sentencia.setString(2, "Rick and Morty");
            sentencia.setInt(3, 4);
            sentencia.executeUpdate();

            sentencia.setInt(1, 2);
            sentencia.setString(2, "The withcer");
            sentencia.setInt(3, 3);
            sentencia.executeUpdate();

            sentencia.setInt(1, 3);
            sentencia.setString(2, "Chainsaw man");
            sentencia.setInt(3, 1);
            sentencia.executeUpdate();

            sentencia.setInt(1, 4);
            sentencia.setString(2, "Karmaland");
            sentencia.setInt(3, 7);
            sentencia.executeUpdate();




        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
}
