package org.example;

import java.sql.*;

public class PreparedStatementBueno {

    //haz la variante que corrija el inconveniente anterior (haciendo uso de PreparedStatement)

    //Crear conexion
    static Connection conn;

    public static void main(String[] args) {

            try (Connection conexion = ConnectionPool.getInstance().getConnection()){

                //Preparar consulta
                PreparedStatement stmt = conn.prepareStatement("Select nombre, temporadas from series where nombre=? and temporadas=?");

                //3. Establecemos parametros de consulta
                stmt.setString(1, "Rick and Morty");
                stmt.setInt(2, 6);
                stmt.setString(1, "The witcher");
                stmt.setInt(2, 3);

                //4. Ejecutamos y recorremos la consulta
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    int cod, temporadas;
                    String nombre;
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
                rs.close();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


    }
}
