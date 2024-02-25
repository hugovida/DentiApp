package controller;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import model.Diente;

public class Diente_Controller {
    
    private MySQLConnection connection;

    public Diente_Controller(MySQLConnection connection) {
        this.connection = connection;
    }
    
    // devuelve una lista con los dientes del paciente que presentan observaciones
    public ArrayList<Diente> getDientes(String dni_paciente) throws SQLException {
        ArrayList<Diente> list=new ArrayList<>();
        String query="SELECT DISTINCT d.num_diente "
        		+ "FROM dientes d "
        		+ "INNER JOIN citas c ON c.id = d.id_cita "
        		+ "WHERE c.dni_paciente = '"+dni_paciente+"'";
        ResultSet rset=connection.execSelect(query);
        
        while (rset.next()){
        	int num_diente=rset.getInt("d.num_diente");
            Diente diente=new Diente(num_diente);
            list.add(diente);
        }
        
        return list;
    }
    
    // devuelve una lista con todas las observaciones del diente de un paciente, ordenadas por fecha
    public ArrayList<Diente> getDientes(String dni_paciente, int num_diente) throws SQLException {
        ArrayList<Diente> list=new ArrayList<>();
        String query="SELECT die.notas, c.fecha, doc.nombre, doc.apellidos "
        		+ "FROM dientes die "
        		+ "INNER JOIN citas c ON c.id = die.id_cita "
        		+ "INNER JOIN doctores doc on doc.dni = c.dni_doctor "
        		+ "WHERE c.dni_paciente = '"+dni_paciente+"' AND die.num_diente = "+num_diente+" "
        		+ "ORDER BY c.fecha DESC";
        ResultSet rset=connection.execSelect(query);
        
        while (rset.next()){
        	String notas=rset.getString("die.notas");
        	Date fecha=rset.getDate("c.fecha");
        	String nom_doctor=rset.getString("doc.nombre");
        	String ape_doctor=rset.getString("doc.apellidos");
            Diente diente=new Diente(notas,fecha,nom_doctor,ape_doctor);
            list.add(diente);
        }
        
        return list;
    }
    
    // devuelve una lista que, de no estar vacía, indicará que ya existe una observación para
    // el diente indicado de la cita indicada
    public ArrayList<Diente> getDientes(int num_diente, String id_cita) throws SQLException {
        ArrayList<Diente> list=new ArrayList<>();
        String query="SELECT num_diente "
        		+ "FROM dientes "
        		+ "WHERE num_diente = "+num_diente+" AND id_cita = "+id_cita;
        ResultSet rset=connection.execSelect(query);
        
        while (rset.next()){
        	int n_diente=rset.getInt("num_diente");
            Diente diente=new Diente(n_diente);
            list.add(diente);
        }
        
        return list;
    }
    
    // método para insertar una nueva observación sobre un diente
    public int insertNewObservacion(String id_cita, int num_diente, String notas) 
    		throws SQLException {
    	return connection.execInsDelUp("INSERT INTO dientes VALUES ("+id_cita+","+num_diente+",'"+notas+"')");
    }
    
}
