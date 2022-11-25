package org.example;
//Ejercicio 1
import java.sql.*;

public class resultset {

    public static void main(String[] args) {

        String sql = "Select * from series";

        try(Connection conexion = ConnectionPool.getInstance().getConnection(); PreparedStatement ps = conexion.prepareStatement(sql);){

        ResultSet rs = ps.executeQuery();

            rs.next();
            sentencia.getString(1, "Rick and Morty");
            sentencia.executeUpdate(sentenciaSqlBorrado);

            rs.last();
            sentencia.getString(1, "Rick and Morty");
            sentencia.executeUpdate(sentenciaSqlBorrado);

            rs.first();
            sentencia.getString(1, "Rick and Morty");
            sentencia.executeUpdate(sentenciaSqlBorrado);

            rs.absolute(3);
            sentencia.getString(1, "Rick and Morty");
            sentencia.executeUpdate(sentenciaSqlBorrado);

            rs.insertRow();
            sentencia.getString(1, "Rick and Morty");
            sentencia.executeUpdate(sentenciaSqlBorrado);




        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


}
