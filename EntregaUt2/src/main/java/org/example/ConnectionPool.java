package org.example;

import org.apache.commons.dbcp.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool{
    private static BasicDataSource bds;
    private static ConnectionPool pool;
    private static final String url = "jdbc:postgresql://localhost:5432/ianmiguelsoler";
    private static final String user = "root";
    private static final String passwd = "root";

    //Constructor privado
     ConnectionPool(){
        bds = new BasicDataSource();
        bds.setUrl(url);
        bds.setUsername(user);
        bds.setPassword(passwd);
        bds.setMinIdle(5);
        bds.setMaxIdle(20);
    }

    //Método que haciendo uso del patrón Singleton devuelve una única instancia.
    public static ConnectionPool getInstance(){

        if(pool == null)
            pool = new ConnectionPool();
        return pool;
    }

    public Connection getConnection() throws SQLException{
        return bds.getConnection();
    }

    //Método que devuelve una conexion
    public void closeConnection(Connection conn) throws SQLException {
        conn.close();

    }
}
