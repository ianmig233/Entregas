package org.example;

import java.sql.*;

public class ResultSet2 {

    public static void main(String[] args) {
        Connection conn = null;
        ResultSet rs = null;

        PreparedStatement sentencia = null;

        try {
            //Abrir la conexión con la Base de Datos
            System.out.println("Conectando con la base de datos....");
            String url = "jdbc:postgresql://localhost:5432/postgres";
            conn = DriverManager.getConnection(url, "root", "root");
            System.out.println("La concexión con la base de datos se ha establecido correctamente");

            String sql = "Select * from series";

            sentencia = conn.prepareStatement(sql);

            rs = sentencia.executeQuery(sql);

            System.out.println("Cursor antes de la primera fila = " + rs.isBeforeFirst());
            int cod, temporadas;
            String nombre;

            //Añado datos a la tabla de series 1 INSERCIÓN

            String sqlInsert = "INSERT INTO series VALUES (1,'Rick and Morty',6)";
            sentencia.executeUpdate(sqlInsert);
            System.out.println("Registro añadido!");

            //Eliminar series

            String sentenciaSqlBorrado = "DELETE series WHERE nombre = ?";
            sentencia.setString(1, "Rick and Morty");
            sentencia.executeUpdate(sentenciaSqlBorrado);


            while (rs.next()) {

                //Obtenemos la información por el nombre de la columna

                cod = rs.getInt("codigo");
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
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (sentencia != null)
                    sentencia.close();
                if (conn != null)
                    conn.close();

            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }
}