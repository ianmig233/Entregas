package org.example;

import org.postgresql.util.PSQLException;

import java.sql.*;

public class ej3_NoInyection {

    public static void main(String[] args) {


        String sqlInyeccion = "Select * from series where cod = 1";
        try(Connection conexion = ConnectionPool.getInstance().getConnection(); PreparedStatement ps = conexion.prepareStatement(sqlInyeccion);){

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

        }catch (PSQLException e){
            System.out.println("No se pueden hacer inyecciones");
        }
        catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}
