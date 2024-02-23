package controller;

import java.sql.*;
import java.util.ArrayList;

import model.Usuario;

public class Usuario_Controller {
    
    private MySQLConnection connection;

    public Usuario_Controller(MySQLConnection connection) {
        this.connection = connection;
    }
    
    // devuelve una lista con el usuario cuyo dni ha sido pasado por parámetro
    public ArrayList<Usuario> getUser(String dniInput) throws SQLException {
        ArrayList<Usuario> list=new ArrayList<>();
        String query="SELECT * FROM usuarios WHERE dni = '"+dniInput+"'";
        ResultSet rset=connection.execSelect(query);
        
        while (rset.next()){
        	String dni=rset.getString("dni");
            String password=rset.getString("password");
            boolean admin=rset.getBoolean("admin");
            boolean desactivado=rset.getBoolean("desactivado");
            Usuario usuario=new Usuario(dni,password,admin,desactivado);
            list.add(usuario);
        }
        
        return list;
    }
    
    // inserta un nuevo usuario pasando por parámetro su dni, contraseña y si es admin
    public int insertNewUser(String dni, String password, boolean admin) throws SQLException {
        return connection.execInsDelUp("INSERT INTO usuarios (dni,password,admin) VALUES ('"+dni+"','"+password+"',"+admin+")");
    }
    
    // modifica la contraseña de un usuario pasado por parámetro por la contraseña también pasada por parámetro
    public int modifyPass(String dni, String password) throws SQLException {
        return connection.execInsDelUp("UPDATE usuarios SET password ='"+password+"' WHERE dni='"+dni+"'");
    }
    
    // modifica el campo desactivado de un usuario pasado por parámetro por el valor de desactivado también pasado por parámetro
    public int modifyDeactivated(String dni, boolean desactivado) throws SQLException {
        return connection.execInsDelUp("UPDATE usuarios SET desactivado ="+desactivado+" WHERE dni='"+dni+"'");
    }
    
}
