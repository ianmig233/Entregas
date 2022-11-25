package org.example;

import java.sql.*;

public class ej2_Inyeccion {

    public static void main(String[] args) {


    String sqlInyeccion = "Select * from series where cod = 1";
    try(Connection conexion = ConnectionPool.getInstance().getConnection();Statement ps = conexion.createStatement();){

        ResultSet rs = ps.executeQuery(sqlInyeccion);

        while (rs.next()) {
            int cod, temporadas;
            String nombre;
            //Obtenemos la información por el nombre de la columna

            cod = rs.getInt("cod");
            nombre = rs.getString("nombre");
            temporadas = rs.getInt("temporadas");

            //Mostramos la información
            System.out.print("Numero de Fila=" + rs.getRow());
            System.out.print(", Código: " + cod);
            System.out.print(", Nombre: " + nombre);
            System.out.print(", Número de Temporadas: " + temporadas);
        }

    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

    }
}

