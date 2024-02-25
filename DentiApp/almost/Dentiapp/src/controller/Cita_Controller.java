package controller;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import model.Cita;

public class Cita_Controller {
    
    private MySQLConnection connection;

    public Cita_Controller(MySQLConnection connection) {
        this.connection = connection;
    }
    
    // devuelve una lista con la cita cuyo ID ha sido pasado por parámetro
    public ArrayList<Cita> getCitaByID(String idInput) throws SQLException {
        ArrayList<Cita> list=new ArrayList<>();
        String query="SELECT c.*, d.nombre, d.apellidos, p.nombre, p.apellidos "
        		+ "FROM citas c "
        		+ "INNER JOIN doctores d ON c.dni_doctor=d.dni "
        		+ "INNER JOIN pacientes p ON c.dni_paciente=p.dni "
        		+ "WHERE c.id = "+idInput;
        ResultSet rset=connection.execSelect(query);
        
        while (rset.next()){
        	int id=rset.getInt("id");
        	String dni_doctor=rset.getString("dni_doctor");
        	String dni_paciente=rset.getString("dni_paciente");
        	int id_ptrat=rset.getInt("id_ptrat");
        	Date fecha=rset.getDate("fecha");
        	Time hora=rset.getTime("hora");
        	String notas=rset.getString("notas");
        	String nom_doctor=rset.getString("d.nombre");
        	String ape_doctor=rset.getString("d.apellidos");
        	String nom_paciente=rset.getString("p.nombre");
        	String ape_paciente=rset.getString("p.apellidos");
            Cita cita=new Cita(id,dni_doctor,nom_doctor,ape_doctor,dni_paciente,
            		nom_paciente,ape_paciente,id_ptrat,fecha,hora,notas);
            list.add(cita);
        }
        
        return list;
    }
    
    // devuelve una lista con las citas cuya fecha ha sido pasada por parámetro, ordenadas por hora
    public ArrayList<Cita> getCitasByDate(String dateInput) throws SQLException {
        ArrayList<Cita> list=new ArrayList<>();
        String query="SELECT c.*, d.nombre, d.apellidos, p.nombre, p.apellidos "
        		+ "FROM citas c "
        		+ "INNER JOIN doctores d ON c.dni_doctor=d.dni "
        		+ "INNER JOIN pacientes p ON c.dni_paciente=p.dni "
        		+ "WHERE c.fecha = '"+dateInput+"' ORDER BY c.hora";
        ResultSet rset=connection.execSelect(query);
        
        while (rset.next()){
        	int id=rset.getInt("id");
        	String dni_doctor=rset.getString("dni_doctor");
        	String dni_paciente=rset.getString("dni_paciente");
        	int id_ptrat=rset.getInt("id_ptrat");
        	Date fecha=rset.getDate("fecha");
        	Time hora=rset.getTime("hora");
        	String notas=rset.getString("notas");
        	String nom_doctor=rset.getString("d.nombre");
        	String ape_doctor=rset.getString("d.apellidos");
        	String nom_paciente=rset.getString("p.nombre");
        	String ape_paciente=rset.getString("p.apellidos");
            Cita cita=new Cita(id,dni_doctor,nom_doctor,ape_doctor,dni_paciente,
            		nom_paciente,ape_paciente,id_ptrat,fecha,hora,notas);
            list.add(cita);
        }
        
        return list;
    }
    
    // devuelve una lista con todas las citas de un doctor, ordenadas por fecha y hora
    public ArrayList<Cita> getDocCitas(String doctorDni) throws SQLException {
        ArrayList<Cita> list=new ArrayList<>();
        String query="SELECT c.*, d.nombre, d.apellidos, p.nombre, p.apellidos "
        		+ "FROM citas c "
        		+ "INNER JOIN doctores d ON c.dni_doctor=d.dni "
        		+ "INNER JOIN pacientes p ON c.dni_paciente=p.dni "
        		+ "WHERE c.dni_doctor = '"+doctorDni+"' ORDER BY c.fecha, c.hora";
        ResultSet rset=connection.execSelect(query);
        
        while (rset.next()){
        	int id=rset.getInt("id");
        	String dni_doctor=rset.getString("dni_doctor");
        	String dni_paciente=rset.getString("dni_paciente");
        	int id_ptrat=rset.getInt("id_ptrat");
        	Date fecha=rset.getDate("fecha");
        	Time hora=rset.getTime("hora");
        	String notas=rset.getString("notas");
        	String nom_doctor=rset.getString("d.nombre");
        	String ape_doctor=rset.getString("d.apellidos");
        	String nom_paciente=rset.getString("p.nombre");
        	String ape_paciente=rset.getString("p.apellidos");
            Cita cita=new Cita(id,dni_doctor,nom_doctor,ape_doctor,dni_paciente,
            		nom_paciente,ape_paciente,id_ptrat,fecha,hora,notas);
            list.add(cita);
        }
        
        return list;
    }
    
    // devuelve una lista con las citas de un doctor en una determinada fecha, ordenadas por hora
    public ArrayList<Cita> getDocCitasByDate(String dateInput, String doctorDni) throws SQLException {
        ArrayList<Cita> list=new ArrayList<>();
        String query="SELECT c.*, d.nombre, d.apellidos, p.nombre, p.apellidos "
        		+ "FROM citas c "
        		+ "INNER JOIN doctores d ON c.dni_doctor=d.dni "
        		+ "INNER JOIN pacientes p ON c.dni_paciente=p.dni "
        		+ "WHERE c.fecha = '"+dateInput+"' AND c.dni_doctor = '"+doctorDni+"' ORDER BY c.hora";
        ResultSet rset=connection.execSelect(query);
        
        while (rset.next()){
        	int id=rset.getInt("id");
        	String dni_doctor=rset.getString("dni_doctor");
        	String dni_paciente=rset.getString("dni_paciente");
        	int id_ptrat=rset.getInt("id_ptrat");
        	Date fecha=rset.getDate("fecha");
        	Time hora=rset.getTime("hora");
        	String notas=rset.getString("notas");
        	String nom_doctor=rset.getString("d.nombre");
        	String ape_doctor=rset.getString("d.apellidos");
        	String nom_paciente=rset.getString("p.nombre");
        	String ape_paciente=rset.getString("p.apellidos");
            Cita cita=new Cita(id,dni_doctor,nom_doctor,ape_doctor,dni_paciente,
            		nom_paciente,ape_paciente,id_ptrat,fecha,hora,notas);
            list.add(cita);
        }
        
        return list;
    }
    
    // devuelve una lista con todas las citas de un paciente, ordenadas por fecha y hora
    public ArrayList<Cita> getPacCitas(String pacienteDni) throws SQLException {
        ArrayList<Cita> list=new ArrayList<>();
        String query="SELECT c.*, d.nombre, d.apellidos, p.nombre, p.apellidos "
        		+ "FROM citas c "
        		+ "INNER JOIN doctores d ON c.dni_doctor=d.dni "
        		+ "INNER JOIN pacientes p ON c.dni_paciente=p.dni "
        		+ "WHERE c.dni_paciente = '"+pacienteDni+"' ORDER BY c.fecha, c.hora";
        ResultSet rset=connection.execSelect(query);
        
        while (rset.next()){
        	int id=rset.getInt("id");
        	String dni_doctor=rset.getString("dni_doctor");
        	String dni_paciente=rset.getString("dni_paciente");
        	int id_ptrat=rset.getInt("id_ptrat");
        	Date fecha=rset.getDate("fecha");
        	Time hora=rset.getTime("hora");
        	String notas=rset.getString("notas");
        	String nom_doctor=rset.getString("d.nombre");
        	String ape_doctor=rset.getString("d.apellidos");
        	String nom_paciente=rset.getString("p.nombre");
        	String ape_paciente=rset.getString("p.apellidos");
            Cita cita=new Cita(id,dni_doctor,nom_doctor,ape_doctor,dni_paciente,
            		nom_paciente,ape_paciente,id_ptrat,fecha,hora,notas);
            list.add(cita);
        }
        
        return list;
    }
    
    // devuelve una lista con las citas de un paciente en una determinada fecha, ordenadas por hora
    public ArrayList<Cita> getPacCitasByDate(String dateInput, String pacienteDni) throws SQLException {
        ArrayList<Cita> list=new ArrayList<>();
        String query="SELECT c.*, d.nombre, d.apellidos, p.nombre, p.apellidos "
        		+ "FROM citas c "
        		+ "INNER JOIN doctores d ON c.dni_doctor=d.dni "
        		+ "INNER JOIN pacientes p ON c.dni_paciente=p.dni "
        		+ "WHERE c.fecha = '"+dateInput+"' AND c.dni_paciente = '"+pacienteDni+"' ORDER BY c.hora";
        ResultSet rset=connection.execSelect(query);
        
        while (rset.next()){
        	int id=rset.getInt("id");
        	String dni_doctor=rset.getString("dni_doctor");
        	String dni_paciente=rset.getString("dni_paciente");
        	int id_ptrat=rset.getInt("id_ptrat");
        	Date fecha=rset.getDate("fecha");
        	Time hora=rset.getTime("hora");
        	String notas=rset.getString("notas");
        	String nom_doctor=rset.getString("d.nombre");
        	String ape_doctor=rset.getString("d.apellidos");
        	String nom_paciente=rset.getString("p.nombre");
        	String ape_paciente=rset.getString("p.apellidos");
            Cita cita=new Cita(id,dni_doctor,nom_doctor,ape_doctor,dni_paciente,
            		nom_paciente,ape_paciente,id_ptrat,fecha,hora,notas);
            list.add(cita);
        }
        
        return list;
    }
    
    // devuelve el modelo pasado por parámetro relleno con la lista pasada por parámetro o vacío en caso de que la lista esté vacía
    public DefaultTableModel fillTable(DefaultTableModel model, ArrayList<Cita> lista) {
    	model.setRowCount(0);
    	
    	for (int i = 0; i < lista.size(); i++) {
			String[] row = new String[6];
			
			row[0] = String.valueOf(lista.get(i).getId());
			row[1] = lista.get(i).getNom_doctor()+" "+lista.get(i).getApe_doctor();
			row[2] = lista.get(i).getNom_paciente()+" "+lista.get(i).getApe_paciente();
			row[3] = lista.get(i).getFecha().toString();
			
			String hora = lista.get(i).getHora().toString();
			row[4] = hora.substring(0, 5);
			
			if (lista.get(i).getNotas() == null) {
				row[5] = "NO";
			} else {
				row[5] = "SI";
			}
			
			model.addRow(row);
		}
    	
    	return model;
    }
    
    // rellena el modelo pasado por parámetro con la cita cuyo ID ha sido pasado por parámetro
    public void listCita(DefaultTableModel model, String id) {
    	try {
    		ArrayList<Cita> citas = getCitaByID(id);
    		fillTable(model, citas);
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    // rellena el modelo pasado por parámetro con todas las citas cuya fecha ha sido pasada por parámetro
    // ordenadas por hora
    public void findCitasByDate(DefaultTableModel model, String dateInput) {
    	try {
    		ArrayList<Cita> citas = getCitasByDate(dateInput);
    		fillTable(model, citas);
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    // rellena el modelo pasado por parámetro con todas las citas de un doctor, ordenadas por fecha y hora
    public void findDocCitas(DefaultTableModel model, String doctorDni) {
    	try {
    		ArrayList<Cita> citas = getDocCitas(doctorDni);
    		fillTable(model, citas);
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    // rellena el modelo pasado por parámetro con todas las citas de un doctor en una determinada fecha, 
    // ordenadas por hora
    public void findDocCitasByDate(DefaultTableModel model, String dateInput, String doctorDni) {
    	try {
    		ArrayList<Cita> citas = getDocCitasByDate(dateInput, doctorDni);
    		fillTable(model, citas);
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    // rellena el modelo pasado por parámetro con todas las citas de un paciente, ordenadas por fecha y hora
    public void findPacCitas(DefaultTableModel model, String pacienteDni) {
    	try {
    		ArrayList<Cita> citas = getPacCitas(pacienteDni);
    		fillTable(model, citas);
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    // rellena el modelo pasado por parámetro con todas las citas de un paciente en una determinada fecha, 
    // ordenadas por hora
    public void findPacCitasByDate(DefaultTableModel model, String dateInput, String pacienteDni) {
    	try {
    		ArrayList<Cita> citas = getPacCitasByDate(dateInput, pacienteDni);
    		fillTable(model, citas);
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    // método para insertar una cita
    public int insertNewCita(String dni_doctor, String dni_paciente, String fecha,
    		String hora) throws SQLException {
    	return connection.execInsDelUp("INSERT INTO citas (dni_doctor,dni_paciente,fecha,hora) VALUES ('"+dni_doctor+"','"+dni_paciente+"','"+fecha+"','"+hora+"')");
    }
    
    // método para modificar la fecha y hora de una cita
    public int modifyCita(String id, String fecha, String hora) throws SQLException {
    	return connection.execInsDelUp("UPDATE citas SET fecha ='"+fecha+"', hora ='"+hora+"' WHERE id="+id+"");
    }
    
    // método para eliminar una cita
    public int deleteCita(String id) throws SQLException {
    	return connection.execInsDelUp("DELETE FROM citas WHERE id="+id+"");
    }
    
}
