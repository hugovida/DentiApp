package controller;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import model.Especialidad;

public class Especialidad_Controller {
    
    private MySQLConnection connection;

    public Especialidad_Controller(MySQLConnection connection) {
        this.connection = connection;
    }
    
    // devuelve una lista con las especialidades cuyo nombre coincide con el pasado por parámetro
    public ArrayList<Especialidad> getEspecialidad(String nombreEsp) throws SQLException {
        ArrayList<Especialidad> list=new ArrayList<>();
        String query="SELECT * FROM especialidades WHERE nombre LIKE '"+nombreEsp+"%"+"'";
        ResultSet rset=connection.execSelect(query);
        
        while (rset.next()){
        	int id=rset.getInt("id");
        	String nombre=rset.getString("nombre");
            Especialidad especialidad=new Especialidad(id,nombre);
            list.add(especialidad);
        }
        
        return list;
    }
    
    // devuelve una lista con todas las especialidades ordenadas por nombre
    public ArrayList<Especialidad> getEspecialidades() throws SQLException {
        ArrayList<Especialidad> list=new ArrayList<>();
        String query="SELECT * FROM especialidades ORDER BY nombre";
        ResultSet rset=connection.execSelect(query);
        
        while (rset.next()){
        	int id=rset.getInt("id");
        	String nombre=rset.getString("nombre");
            Especialidad especialidad=new Especialidad(id,nombre);
            list.add(especialidad);
        }
        
        return list;
    }
    
    // devuelve una lista con todas las especialidades asignadas a un doctor, ordenadas por nombre
    public ArrayList<Especialidad> getEspecialidades(String dni) throws SQLException {
        ArrayList<Especialidad> list=new ArrayList<>();
        String query="SELECT * FROM especialidades e "
        		+ "INNER JOIN doctores_especialidades de "
        		+ "ON e.id = de.id_especialidad "
        		+ "WHERE de.dni_doctor = '"+dni+"'";
        ResultSet rset=connection.execSelect(query);
        
        while (rset.next()){
        	int id=rset.getInt("id");
        	String nombre=rset.getString("nombre");
            Especialidad especialidad=new Especialidad(id,nombre);
            list.add(especialidad);
        }
        
        return list;
    }
    
    // devuelve el modelo pasado por parámetro relleno con la lista pasada por parámetro o vacío en caso de que la lista esté vacía
    public DefaultTableModel fillTable(DefaultTableModel model, ArrayList<Especialidad> lista) {
    	model.setRowCount(0);
    	
    	for (int i = 0; i < lista.size(); i++) {
			String[] row = new String[1];
			
			row[0] = lista.get(i).getNombre();
			
			model.addRow(row);
		}
    	
    	return model;
    }
    
    // rellena el modelo pasado por parámetro con todas las especialidades ordenadas por nombre
    public void listSpecialties(DefaultTableModel model) {
    	try {
    		ArrayList<Especialidad> especialidades = getEspecialidades();
    		fillTable(model, especialidades);
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    // rellena el modelo pasado por parámetro con todas las especialidades de un doctor, ordenadas por nombre
    public void listSpecialties(DefaultTableModel model, String dni) {
    	try {
    		ArrayList<Especialidad> especialidades = getEspecialidades(dni);
    		fillTable(model, especialidades);
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    // rellena el modelo pasado por parámetro con la especialidad cuyo nombre ha sido pasado por parámetro
    public void findSpecialty(DefaultTableModel model, String nombreEsp) {
    	try {
    		ArrayList<Especialidad> especialidad = getEspecialidad(nombreEsp);
    		fillTable(model, especialidad);
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    // método para insertar una especialidad
    public int insertNewSpecialty(String nombre) throws SQLException {
    	return connection.execInsDelUp("INSERT INTO especialidades (nombre) VALUES ('"+nombre+"')");
    }
    
    // método para modificar el nombre de una especialidad
    public int modifyName(String oldNombre, String newNombre) throws SQLException {
    	return connection.execInsDelUp("UPDATE especialidades SET nombre ='"+newNombre+"' WHERE nombre='"+oldNombre+"'");
    }
    
}
