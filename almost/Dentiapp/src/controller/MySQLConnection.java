package controller;

import java.sql.*;

public class MySQLConnection {

	private static final String URL = "jdbc:mysql://localhost:3306/dentiapp?useSSL=false";
	private static final String USER = "root";
	private static final String PASS = "1234";
    private Connection connection;

    public MySQLConnection() {
        connection = null;
    }

    // conectar con la BBDD
    public void connect() throws SQLException {
        if (connection == null || connection.isClosed()) {
        	connection = DriverManager.getConnection(URL, USER, PASS);
        }
    }

    // cerrar la conexión con la BBDD
    public void disconnect() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    // consulta con SELECT
    public ResultSet execSelect(String query) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rset = stmt.executeQuery(query);
        return rset;
    }

    // ejecutar INSERT, DELETE o UPDATE
    public int execInsDelUp(String query) throws SQLException {
        Statement stmt = connection.createStatement();
        int row=stmt.executeUpdate(query);
        return row;
    }

}
