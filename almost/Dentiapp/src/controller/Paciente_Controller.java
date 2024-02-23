package controller;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import model.Paciente;

public class Paciente_Controller {
    
    private MySQLConnection connection;

    public Paciente_Controller(MySQLConnection connection) {
        this.connection = connection;
    }
    
    // devuelve una lista con el paciente cuyo dni ha sido pasado por parámetro
    public ArrayList<Paciente> getPaciente(String dniInput) throws SQLException {
        ArrayList<Paciente> list=new ArrayList<>();
        String query="SELECT * FROM pacientes WHERE dni = '"+dniInput+"'";
        ResultSet rset=connection.execSelect(query);
        
        while (rset.next()){
        	String dni=rset.getString("dni");
        	String nombre=rset.getString("nombre");
        	String apellidos=rset.getString("apellidos");
        	int telefono=rset.getInt("telefono");
        	Date fecha_nacimiento=rset.getDate("fecha_nacimiento");
        	String correo=rset.getString("correo");
            Paciente paciente=new Paciente(dni,nombre,apellidos,telefono,fecha_nacimiento,correo);
            list.add(paciente);
        }
        
        return list;
    }
    
    // devuelve una lista con todos los pacientes ordenados por apellidos
    public ArrayList<Paciente> getPacientes() throws SQLException {
        ArrayList<Paciente> list=new ArrayList<>();
        String query="SELECT * FROM pacientes ORDER BY apellidos";
        ResultSet rset=connection.execSelect(query);
        
        while (rset.next()){
        	String dni=rset.getString("dni");
        	String nombre=rset.getString("nombre");
        	String apellidos=rset.getString("apellidos");
        	int telefono=rset.getInt("telefono");
        	Date fecha_nacimiento=rset.getDate("fecha_nacimiento");
        	String correo=rset.getString("correo");
        	Paciente paciente=new Paciente(dni,nombre,apellidos,telefono,fecha_nacimiento,correo);
            list.add(paciente);
        }
        
        return list;
    }
    
    // devuelve el modelo pasado por parámetro relleno con la lista pasada por parámetro o vacío en caso de que la lista esté vacía
    public DefaultTableModel fillTable(DefaultTableModel model, ArrayList<Paciente> lista) {
    	model.setRowCount(0);
    	
    	for (int i = 0; i < lista.size(); i++) {
			String[] row = new String[6];
			
			row[0] = lista.get(i).getDni();
			row[1] = lista.get(i).getNombre();
			row[2] = lista.get(i).getApellidos();
			row[3] = String.valueOf(lista.get(i).getTelefono());
			
			Date fecha_nacimiento = lista.get(i).getFecha_nacimiento();
			
			if (fecha_nacimiento == null) {
				row[4] = "";
			} else {
				row[4] = fecha_nacimiento.toString();
			}
			
			row[5] = lista.get(i).getCorreo();
			
			model.addRow(row);
		}
    	
    	return model;
    }
    
    // rellena el modelo pasado por parámetro con todos los pacientes ordenados por apellidos
    public void listPatients(DefaultTableModel model) {
    	try {
    		ArrayList<Paciente> pacientes = getPacientes();
    		fillTable(model, pacientes);
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    // rellena el modelo pasado por parámetro con el paciente cuyo dni ha sido pasado por parámetro
    public void findPatient(DefaultTableModel model, String dniInput) {
    	try {
    		ArrayList<Paciente> paciente = getPaciente(dniInput);
    		fillTable(model, paciente);
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    // método para insertar un paciente con los campos obligatorios
    public int insertNewPaciente(String dni, String nombre, String apellidos, int telefono) 
    		throws SQLException {
    	return connection.execInsDelUp("INSERT INTO pacientes (dni,nombre,apellidos,telefono) VALUES ('"+dni+"','"+nombre+"','"+apellidos+"',"+telefono+")");
    }
    
    // método para insertar un paciente con los campos obligatorios y el correo electrónico
    public int insertNewPaciente(String dni, String nombre, String apellidos, int telefono, 
    		String correo) throws SQLException {
    	return connection.execInsDelUp("INSERT INTO pacientes (dni,nombre,apellidos,telefono,correo) VALUES ('"+dni+"','"+nombre+"','"+apellidos+"',"+telefono+",'"+correo+"')");
    }
    
    // método para insertar un paciente con los campos obligatorios y la fecha de nacimiento
    public int insertNewPaciente(String dni, String nombre, String apellidos, 
    		String fecha_nacimiento, int telefono) throws SQLException {
    	return connection.execInsDelUp("INSERT INTO pacientes (dni,nombre,apellidos,telefono,fecha_nacimiento) VALUES ('"+dni+"','"+nombre+"','"+apellidos+"',"+telefono+",'"+fecha_nacimiento+"')");
    }
    
    // método para insertar un paciente con todos los campos
    public int insertNewPaciente(String dni, String nombre, String apellidos, int telefono, 
    		String fecha_nacimiento, String correo) throws SQLException {
    	return connection.execInsDelUp("INSERT INTO pacientes (dni,nombre,apellidos,telefono,fecha_nacimiento,correo) VALUES ('"+dni+"','"+nombre+"','"+apellidos+"',"+telefono+",'"+fecha_nacimiento+"','"+correo+"')");
    }
    
    // método para modificar el nombre de un paciente
    public int modifyName(String dni, String nombre) throws SQLException {
    	return connection.execInsDelUp("UPDATE pacientes SET nombre ='"+nombre+"' WHERE dni='"+dni+"'");
    }
    
    // método para modificar los apellidos de un paciente
    public int modifySurname(String dni, String apellidos) throws SQLException {
    	return connection.execInsDelUp("UPDATE pacientes SET apellidos ='"+apellidos+"' WHERE dni='"+dni+"'");
    }
    
    // método para modificar el teléfono de un paciente
    public int modifyPhone(String dni, int telefono) throws SQLException {
    	return connection.execInsDelUp("UPDATE pacientes SET telefono ="+telefono+" WHERE dni='"+dni+"'");
    }
    
    // método para modificar la fecha de nacimiento de un paciente
    public int modifyDate(String dni, String fecha_nacimiento) throws SQLException {
    	return connection.execInsDelUp("UPDATE pacientes SET fecha_nacimiento ='"+fecha_nacimiento+"' WHERE dni='"+dni+"'");
    }
    
    // método para modificar el correo electrónico de un paciente
    public int modifyMail(String dni, String correo) throws SQLException {
    	return connection.execInsDelUp("UPDATE pacientes SET correo ='"+correo+"' WHERE dni='"+dni+"'");
    }
    
}
