package controller;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import model.Doctor;
import model.Especialidad;

public class Doctor_Controller {
    
    private MySQLConnection connection;

    public Doctor_Controller(MySQLConnection connection) {
        this.connection = connection;
    }
    
    // devuelve una lista con el doctor cuyo dni ha sido pasado por parámetro
    public ArrayList<Doctor> getDoctor(String dniInput) throws SQLException {
        ArrayList<Doctor> list=new ArrayList<>();
        String query="SELECT * FROM doctores WHERE dni = '"+dniInput+"'";
        ResultSet rset=connection.execSelect(query);
        
        while (rset.next()){
        	String dni=rset.getString("dni");
        	String nombre=rset.getString("nombre");
        	String apellidos=rset.getString("apellidos");
        	int telefono=rset.getInt("telefono");
        	Date fecha_nacimiento=rset.getDate("fecha_nacimiento");
        	String correo=rset.getString("correo");
        	boolean baja=rset.getBoolean("baja");
            Doctor doctor=new Doctor(dni,nombre,apellidos,telefono,fecha_nacimiento,correo,baja);
            list.add(doctor);
        }
        
        return list;
    }
    
    // devuelve una lista con todos los doctores ordenados por apellidos
    public ArrayList<Doctor> getDoctores() throws SQLException {
        ArrayList<Doctor> list=new ArrayList<>();
        String query="SELECT * FROM doctores ORDER BY apellidos";
        ResultSet rset=connection.execSelect(query);
        
        while (rset.next()){
        	String dni=rset.getString("dni");
        	String nombre=rset.getString("nombre");
        	String apellidos=rset.getString("apellidos");
        	int telefono=rset.getInt("telefono");
        	Date fecha_nacimiento=rset.getDate("fecha_nacimiento");
        	String correo=rset.getString("correo");
        	boolean baja=rset.getBoolean("baja");
            Doctor doctor=new Doctor(dni,nombre,apellidos,telefono,fecha_nacimiento,correo,baja);
            list.add(doctor);
        }
        
        return list;
    }
    
    // devuelve una lista con todos los doctores que tienen asignada la especialidad pasada por parámetro,
    // ordenados por apellidos
    public ArrayList<Doctor> getDoctoresBySpecialty(String specialtyId, boolean working) throws SQLException {
        ArrayList<Doctor> list=new ArrayList<>();
        String query="SELECT * FROM doctores d "
        		+ "INNER JOIN doctores_especialidades de "
        		+ "ON d.dni = de.dni_doctor "
        		+ "WHERE de.id_especialidad = "+specialtyId;
        
        // si se pasa el valor true como argumento para el parámetro working, sólo devolverá los doctores 
        // que no estén de baja
        if (working) {
        	query = query+" AND d.baja = false";
        }
        
        ResultSet rset=connection.execSelect(query);
        
        while (rset.next()){
        	String dni=rset.getString("dni");
        	String nombre=rset.getString("nombre");
        	String apellidos=rset.getString("apellidos");
        	int telefono=rset.getInt("telefono");
        	Date fecha_nacimiento=rset.getDate("fecha_nacimiento");
        	String correo=rset.getString("correo");
        	boolean baja=rset.getBoolean("baja");
            Doctor doctor=new Doctor(dni,nombre,apellidos,telefono,fecha_nacimiento,correo,baja);
            list.add(doctor);
        }
        
        return list;
    }
    
    // devuelve el modelo pasado por parámetro relleno con la lista pasada por parámetro o vacío en caso de que la lista esté vacía
    public DefaultTableModel fillTable(DefaultTableModel model, ArrayList<Doctor> lista) {
    	model.setRowCount(0);
    	
    	for (int i = 0; i < lista.size(); i++) {
			String[] row = new String[7];
			
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
			
			boolean baja = lista.get(i).isBaja();
			
			if (baja) {
				row[6] = "SI";
			} else {
				row[6] = "NO";
			}
			
			model.addRow(row);
		}
    	
    	return model;
    }
    
    // rellena el modelo pasado por parámetro con todos los doctores ordenados por apellidos
    public void listDoctors(DefaultTableModel model) {
    	try {
    		ArrayList<Doctor> doctores = getDoctores();
    		fillTable(model, doctores);
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    // rellena el modelo pasado por parámetro con el doctor cuyo dni ha sido pasado por parámetro
    public void findDoctor(DefaultTableModel model, String dniInput) {
    	try {
    		ArrayList<Doctor> doctor = getDoctor(dniInput);
    		fillTable(model, doctor);
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    // método para insertar un doctor con los campos obligatorios
    public int insertNewDoctor(String dni, String password, String nombre, String apellidos, int telefono) 
    		throws SQLException {
    	Usuario_Controller user_controller = new Usuario_Controller(connection);
    	int createUser = user_controller.insertNewUser(dni, password, false);
    	
    	if (createUser == 0) {
    		return createUser;
    	} else {
    		return connection.execInsDelUp("INSERT INTO doctores (dni,nombre,apellidos,telefono) VALUES ('"+dni+"','"+nombre+"','"+apellidos+"',"+telefono+")");
    	}
    }
    
    // método para insertar un doctor con los campos obligatorios y el correo electrónico
    public int insertNewDoctor(String dni, String password, String nombre, String apellidos, int telefono, 
    		String correo) throws SQLException {
    	Usuario_Controller user_controller = new Usuario_Controller(connection);
    	int createUser = user_controller.insertNewUser(dni, password, false);
    	
    	if (createUser == 0) {
    		return createUser;
    	} else {
    		return connection.execInsDelUp("INSERT INTO doctores (dni,nombre,apellidos,telefono,correo) VALUES ('"+dni+"','"+nombre+"','"+apellidos+"',"+telefono+",'"+correo+"')");
    	}
    }
    
    // método para insertar un doctor con los campos obligatorios y la fecha de nacimiento
    public int insertNewDoctor(String dni, String password, String nombre, String apellidos, 
    		String fecha_nacimiento, int telefono) throws SQLException {
    	Usuario_Controller user_controller = new Usuario_Controller(connection);
    	int createUser = user_controller.insertNewUser(dni, password, false);
    	
    	if (createUser == 0) {
    		return createUser;
    	} else {
    		return connection.execInsDelUp("INSERT INTO doctores (dni,nombre,apellidos,telefono,fecha_nacimiento) VALUES ('"+dni+"','"+nombre+"','"+apellidos+"',"+telefono+",'"+fecha_nacimiento+"')");
    	}
    }
    
    // método para insertar un doctor con todos los campos
    public int insertNewDoctor(String dni, String password, String nombre, String apellidos, int telefono, 
    		String fecha_nacimiento, String correo) throws SQLException {
    	Usuario_Controller user_controller = new Usuario_Controller(connection);
    	int createUser = user_controller.insertNewUser(dni, password, false);
    	
    	if (createUser == 0) {
    		return createUser;
    	} else {
    		return connection.execInsDelUp("INSERT INTO doctores (dni,nombre,apellidos,telefono,fecha_nacimiento,correo) VALUES ('"+dni+"','"+nombre+"','"+apellidos+"',"+telefono+",'"+fecha_nacimiento+"','"+correo+"')");
    	}
    }
    
    // método para asignar una especialidad al doctor
    public int assignSpecialty(String dni, String specName) throws SQLException {
    	Especialidad_Controller spec_controller = new Especialidad_Controller(connection);
    	ArrayList<Especialidad> specialty = spec_controller.getEspecialidad(specName);
    	
    	if (specialty.isEmpty()) {
    		return 0;
    	} else {
    		return connection.execInsDelUp("INSERT INTO doctores_especialidades VALUES ('"+dni+"',"+specialty.get(0).getId()+")");
    	}
    }
    
    // método para modificar el nombre de un doctor
    public int modifyName(String dni, String nombre) throws SQLException {
    	return connection.execInsDelUp("UPDATE doctores SET nombre ='"+nombre+"' WHERE dni='"+dni+"'");
    }
    
    // método para modificar los apellidos de un doctor
    public int modifySurname(String dni, String apellidos) throws SQLException {
    	return connection.execInsDelUp("UPDATE doctores SET apellidos ='"+apellidos+"' WHERE dni='"+dni+"'");
    }
    
    // método para modificar el teléfono de un doctor
    public int modifyPhone(String dni, int telefono) throws SQLException {
    	return connection.execInsDelUp("UPDATE doctores SET telefono ="+telefono+" WHERE dni='"+dni+"'");
    }
    
    // método para modificar la fecha de nacimiento de un doctor
    public int modifyDate(String dni, String fecha_nacimiento) throws SQLException {
    	return connection.execInsDelUp("UPDATE doctores SET fecha_nacimiento ='"+fecha_nacimiento+"' WHERE dni='"+dni+"'");
    }
    
    // método para modificar el correo electrónico de un doctor
    public int modifyMail(String dni, String correo) throws SQLException {
    	return connection.execInsDelUp("UPDATE doctores SET correo ='"+correo+"' WHERE dni='"+dni+"'");
    }
    
    // método para modificar la baja de un doctor
    public int modifyLeave(String dni, boolean baja) throws SQLException {
    	return connection.execInsDelUp("UPDATE doctores SET baja ="+baja+" WHERE dni='"+dni+"'");
    }
    
}
