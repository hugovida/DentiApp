package controller;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import model.Proveedor;

public class Proveedor_Controller {
    
    private MySQLConnection connection;

    public Proveedor_Controller(MySQLConnection connection) {
        this.connection = connection;
    }
    
    // devuelve una lista con los proveedores cuyo nombre coincide con el pasado por parámetro
    public ArrayList<Proveedor> getProveedor(String nombreProv) throws SQLException {
        ArrayList<Proveedor> list=new ArrayList<>();
        String query="SELECT * FROM proveedores WHERE nombre LIKE '"+nombreProv+"%"+"'";
        ResultSet rset=connection.execSelect(query);
        
        while (rset.next()){
        	String nif=rset.getString("nif");
        	String nombre=rset.getString("nombre");
        	int telefono=rset.getInt("telefono");
        	String correo=rset.getString("correo");
        	boolean baja=rset.getBoolean("baja");
            Proveedor proveedor=new Proveedor(nif,nombre,telefono,correo,baja);
            list.add(proveedor);
        }
        
        return list;
    }
    
    // devuelve una lista con todos los proveedores ordenados por estado de baja y nombre
    public ArrayList<Proveedor> getProveedores(boolean active) throws SQLException {
        ArrayList<Proveedor> list=new ArrayList<>();
        String query="SELECT * FROM proveedores";
        
        // si se pasa el valor true como argumento para el parámetro active, sólo devolverá los proveedores 
        // que no hayan sido dados de baja
        if (active) {
        	query = query+" WHERE baja = false";
        }
        
        query = query+" ORDER BY baja, nombre";
        
        ResultSet rset=connection.execSelect(query);
        
        while (rset.next()){
        	String nif=rset.getString("nif");
        	String nombre=rset.getString("nombre");
        	int telefono=rset.getInt("telefono");
        	String correo=rset.getString("correo");
        	boolean baja=rset.getBoolean("baja");
            Proveedor proveedor=new Proveedor(nif,nombre,telefono,correo,baja);
            list.add(proveedor);
        }
        
        return list;
    }
    
    // devuelve el modelo pasado por parámetro relleno con la lista pasada por parámetro o vacío en caso de que la lista esté vacía
    public DefaultTableModel fillTable(DefaultTableModel model, ArrayList<Proveedor> lista) {
    	model.setRowCount(0);
    	
    	for (int i = 0; i < lista.size(); i++) {
			String[] row = new String[5];
			
			row[0] = lista.get(i).getNif();
			row[1] = lista.get(i).getNombre();
			row[2] = String.valueOf(lista.get(i).getTelefono());
			row[3] = lista.get(i).getCorreo();
			
			boolean baja = lista.get(i).isBaja();
			
			if (baja) {
				row[4] = "SI";
			} else {
				row[4] = "NO";
			}
			
			model.addRow(row);
		}
    	
    	return model;
    }
    
    // rellena el modelo pasado por parámetro con todos los proveedores ordenados por estado de baja y nombre
    public void listProviders(DefaultTableModel model) {
    	try {
    		ArrayList<Proveedor> proveedores = getProveedores(false);
    		fillTable(model, proveedores);
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    // rellena el modelo pasado por parámetro con los proveedores cuyos nombres coincidan con el pasado por parámetro
    public void findProvider(DefaultTableModel model, String nombreProv) {
    	try {
    		ArrayList<Proveedor> proveedor = getProveedor(nombreProv);
    		fillTable(model, proveedor);
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    // método para insertar un proveedor con los campos obligatorios
    public int insertNewProveedor(String nif, String nombre, int telefono) 
    		throws SQLException {
    	return connection.execInsDelUp("INSERT INTO proveedores (nif,nombre,telefono) VALUES ('"+nif+"','"+nombre+"',"+telefono+")");
    }
    
    // método para insertar un proveedor con los campos obligatorios y el correo electrónico
    public int insertNewProveedor(String nif, String nombre, int telefono, 
    		String correo) throws SQLException {
    	return connection.execInsDelUp("INSERT INTO proveedores (nif,nombre,telefono,correo) VALUES ('"+nif+"','"+nombre+"',"+telefono+",'"+correo+"')");
    }
    
    // método para modificar el nombre de un proveedor
    public int modifyName(String nif, String nombre) throws SQLException {
    	return connection.execInsDelUp("UPDATE proveedores SET nombre ='"+nombre+"' WHERE nif='"+nif+"'");
    }
    
    // método para modificar el teléfono de un proveedor
    public int modifyPhone(String nif, int telefono) throws SQLException {
    	return connection.execInsDelUp("UPDATE proveedores SET telefono ="+telefono+" WHERE nif='"+nif+"'");
    }
    
    // método para modificar el correo electrónico de un proveedor
    public int modifyMail(String nif, String correo) throws SQLException {
    	return connection.execInsDelUp("UPDATE proveedores SET correo ='"+correo+"' WHERE nif='"+nif+"'");
    }
    
    // método para modificar la baja de un proveedor
    public int modifyLeave(String nif, boolean baja) throws SQLException {
    	return connection.execInsDelUp("UPDATE proveedores SET baja ="+baja+" WHERE nif='"+nif+"'");
    }
    
}
