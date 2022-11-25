package org.example;

import java.sql.*;
//Ejercicio 1
public class Crear_Base_de_datos {
    public static void main(String[] args) {

        String jdbcUrl = "jdbc:postgresql://localhost:5432/postgres";


        try (Connection conexion = DriverManager.getConnection(jdbcUrl, "root", "root");
        Statement stmt = conexion.createStatement()){
            Class.forName("org.postgresql.Driver");
            String sql = "CREATE DATABASE ianmiguelsoler";
            stmt.executeUpdate(sql);


        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        //creamos una tabla
        CrearTabla();

    }

    private static void CrearTabla() {

        String url = "jdbc:postgresql://localhost:5432/ianmiguelsoler";

        String sql = ("CREATE TABLE Series (cod INTEGER primary key NOT NULL, nombre VARCHAR, temporadas INTEGER)");

        try (Connection conexion = DriverManager.getConnection(url, "root", "root");
             Statement stmt = conexion.createStatement()){

            stmt.executeUpdate(sql);
            System.out.println("Se ha creado la tabla");
        }catch (SQLException e){
            System.err.println(e.getMessage());
        }



    }
}
