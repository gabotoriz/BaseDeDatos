package utc.mx.DB;

import java.sql.*;

public class Conexion {
    static Settings settings = new Settings();
    private static Connection connection;
    private static Conexion instancia;//es el nombre de esta clase

    private Conexion() {
    }

    public Connection getConnection() {
        try {
            Class.forName(settings.getDriver());
            connection = DriverManager.getConnection(settings.getUrl() + settings.getDataBase(), settings.getUser(), settings.getPssword());
            return connection;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e);
        }
        return connection;
    }

    public void dropConnection() throws SQLException {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e);
            connection.close();
        } finally {
            connection.close();
        }
    }

    //SINGLETON


    public static Conexion getInstance() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }
}
