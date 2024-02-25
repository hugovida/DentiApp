package controller;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import model.Citas;
import model.Dientes;
import model.Doctores;
import model.Especialidades;
import model.Pacientes;
import model.Pagos;
import model.Tratamientos;
import model.Usuarios;
import view.Query;

/**
 * Clase que corresponde al controlador de la ventana de realización de consultas.
 */
public class Query_Controller {
	
	/**
     * Método que permite obtener y mostrar en una tabla un objeto de la clase deseada mediante su identificador.
     * @param q instancia de la clase Query de la que dependen las ventanas de diálogo
     * @param connection instancia de la clase HibernateConnection que permite al método comunicarse con la base de datos
     * @param currentModel modelo de la tabla en la que se mostrarán los resultados
     */
	public static void find(Query q, HibernateConnection connection, DefaultTableModel currentModel) {
		String[] choices = { "citas", "dientes", "doctores", "especialidades", "pacientes", 
				"pagos", "tratamientos", "usuarios" };
	    String selection = (String) JOptionPane.showInputDialog(q, "Seleccione qué tipo de objeto desea buscar:",
	        "Buscar en...", JOptionPane.QUESTION_MESSAGE, null,
	        choices,
	        choices[0]);
	    
	    if (selection!=null) {
	    	String input = JOptionPane.showInputDialog(
					q,
					"Introduzca el identificador del objeto a buscar en la tabla "+selection+":",
					"Buscar en "+selection,
					JOptionPane.INFORMATION_MESSAGE);
	    	
	    	if (input != null && !input.isEmpty()) {
	    		try {
			    	switch (selection) {
			    	case "citas":
			    		showCita(input,connection,currentModel);
			    		break;
			    	case "dientes":
			    		showDiente(input,connection,currentModel);
			    		break;
			    	case "doctores":
			    		showDoctor(input,connection,currentModel);
			    		break;
			    	case "especialidades":
			    		showEspecialidad(input,connection,currentModel);
			    		break;
			    	case "pacientes":
			    		showPaciente(input,connection,currentModel);
			    		break;
			    	case "pagos":
			    		showPago(input,connection,currentModel);
			    		break;
			    	case "tratamientos":
			    		showTratamiento(input,connection,currentModel);
			    		break;
			    	case "usuarios":
			    		showUsuario(input,connection,currentModel);
			    		break;
			    	}
			    } catch (Exception e) {
			    	e.printStackTrace();
					JOptionPane.showMessageDialog(q, "No se ha encontrado ningún objeto con ese identificador.",
						      "Error", JOptionPane.ERROR_MESSAGE);
				}
	    	}
	    }
	}

	/**
     * Método que permite obtener un objeto de la clase Citas mediante su identificador y mostrarlo en la tabla.
     * @param input identificador del objeto
     * @param connection instancia de la clase HibernateConnection que permite al método comunicarse con la base de datos
     * @param currentModel modelo de la tabla en la que se mostrarán los resultados
     */
	private static void showCita(String input, HibernateConnection connection, DefaultTableModel currentModel) {
		int id = Integer.parseInt(input);
		Citas cita = Operations.getCita(connection.getSession(), id);
		String[] colNames = {"id", "id_doctor", "dni_paciente", "fecha", "notas"};
		
		String[] row = new String[colNames.length];
		
		row[0] = String.valueOf(cita.getId());
		row[1] = String.valueOf(cita.getDoctor().getId());
		row[2] = cita.getPaciente().getDni();
		row[3] = cita.getFecha().toString();
		
		if (cita.getNotas()!=null) {
			row[4] = cita.getNotas();
		} else {
			row[4] = "";
		}
		
		currentModel.setRowCount(0);
		currentModel.setColumnIdentifiers(colNames);
		currentModel.addRow(row);
	}
	
	/**
     * Método que permite obtener un objeto de la clase Dientes mediante su identificador y mostrarlo en la tabla.
     * @param input identificador del objeto
     * @param connection instancia de la clase HibernateConnection que permite al método comunicarse con la base de datos
     * @param currentModel modelo de la tabla en la que se mostrarán los resultados
     */
	private static void showDiente(String input, HibernateConnection connection, DefaultTableModel currentModel) {
		int id = Integer.parseInt(input);
		Dientes diente = Operations.getDiente(connection.getSession(), id);
		String[] colNames = {"id", "dni_paciente", "num_diente", "notas"};
		
		String[] row = new String[colNames.length];
		
		row[0] = String.valueOf(diente.getId());
		row[1] = diente.getPaciente().getDni();
		row[2] = String.valueOf(diente.getNum_diente());
		row[3] = diente.getNotas();

		currentModel.setRowCount(0);
		currentModel.setColumnIdentifiers(colNames);
		currentModel.addRow(row);
	}
	
	/**
     * Método que permite obtener un objeto de la clase Doctores mediante su identificador y mostrarlo en la tabla.
     * @param input identificador del objeto
     * @param connection instancia de la clase HibernateConnection que permite al método comunicarse con la base de datos
     * @param currentModel modelo de la tabla en la que se mostrarán los resultados
     */
	private static void showDoctor(String input, HibernateConnection connection, DefaultTableModel currentModel) {
		int id = Integer.parseInt(input);
		Doctores doctor = Operations.getDoctor(connection.getSession(), id);
		String[] colNames = {"id", "dni", "nombre", "apellidos", "telefono", "fecha_nacimiento", "correo", "baja"};
		
		String[] row = new String[colNames.length];
		
		row[0] = String.valueOf(doctor.getId());
		row[1] = doctor.getUsuario().getDni();
		row[2] = doctor.getNombre();
		row[3] = doctor.getApellidos();
		row[4] = String.valueOf(doctor.getTelefono());
		
		if (doctor.getFecha_nacimiento()!=null) {
			row[5] = doctor.getFecha_nacimiento().toString();
		} else {
			row[5] = "";
		}
		
		if (doctor.getCorreo()!=null) {
			row[6] = doctor.getCorreo();
		} else {
			row[6] = "";
		}

		row[7] = String.valueOf(doctor.getBaja());

		currentModel.setRowCount(0);
		currentModel.setColumnIdentifiers(colNames);
		currentModel.addRow(row);
	}
	
	/**
     * Método que permite obtener un objeto de la clase Especialidades mediante su identificador y mostrarlo en la tabla.
     * @param input identificador del objeto
     * @param connection instancia de la clase HibernateConnection que permite al método comunicarse con la base de datos
     * @param currentModel modelo de la tabla en la que se mostrarán los resultados
     */
	private static void showEspecialidad(String input, HibernateConnection connection, DefaultTableModel currentModel) {
		int id = Integer.parseInt(input);
		Especialidades especialidad = Operations.getEspecialidad(connection.getSession(), id);
		String[] colNames = {"id", "nombre"};
		
		String[] row = new String[colNames.length];
		
		row[0] = String.valueOf(especialidad.getId());
		row[1] = especialidad.getNombre();

		currentModel.setRowCount(0);
		currentModel.setColumnIdentifiers(colNames);
		currentModel.addRow(row);
	}
	
	/**
     * Método que permite obtener un objeto de la clase Pacientes mediante su identificador y mostrarlo en la tabla.
     * @param input identificador del objeto
     * @param connection instancia de la clase HibernateConnection que permite al método comunicarse con la base de datos
     * @param currentModel modelo de la tabla en la que se mostrarán los resultados
     */
	private static void showPaciente(String input, HibernateConnection connection, DefaultTableModel currentModel) {
		Pacientes paciente = Operations.getPaciente(connection.getSession(), input);
		String[] colNames = {"dni", "nombre", "apellidos", "telefono", "fecha_nacimiento", "correo"};
		
		String[] row = new String[colNames.length];
		
		row[0] = paciente.getDni();
		row[1] = paciente.getNombre();
		row[2] = paciente.getApellidos();
		row[3] = paciente.getTelefono();
		
		if (paciente.getFecha_nacimiento()!=null) {
			row[4] = paciente.getFecha_nacimiento().toString();
		} else {
			row[4] = "";
		}
		
		if (paciente.getCorreo()!=null) {
			row[5] = paciente.getCorreo();
		} else {
			row[5] = "";
		}

		currentModel.setRowCount(0);
		currentModel.setColumnIdentifiers(colNames);
		currentModel.addRow(row);
	}
	
	/**
     * Método que permite obtener un objeto de la clase Pagos mediante su identificador y mostrarlo en la tabla.
     * @param input identificador del objeto
     * @param connection instancia de la clase HibernateConnection que permite al método comunicarse con la base de datos
     * @param currentModel modelo de la tabla en la que se mostrarán los resultados
     */
	private static void showPago(String input, HibernateConnection connection, DefaultTableModel currentModel) {
		int id = Integer.parseInt(input);
		Pagos pago = Operations.getPago(connection.getSession(), id);
		String[] colNames = {"id", "dni_paciente", "mensualidad", "tipo", "importe", "fecha_registro", "fecha_pago"};
		
		String[] row = new String[colNames.length];
		
		row[0] = String.valueOf(pago.getId());
		row[1] = pago.getPaciente().getDni();
		
		if (pago.getMensualidad()!=null) {
			row[2] = String.valueOf(pago.getMensualidad());
		} else {
			row[2] = "";
		}
		
		if (pago.getTipo()!=null) {
			row[3] = String.valueOf(pago.getTipo());
		} else {
			row[3] = "";
		}

		row[4] = String.valueOf(pago.getImporte());
		row[5] = pago.getFecha_registro().toString();
		
		if (pago.getFecha_pago().toString()!=null) {
			row[6] = pago.getFecha_pago().toString();
		} else {
			row[6] = "";
		}

		currentModel.setRowCount(0);
		currentModel.setColumnIdentifiers(colNames);
		currentModel.addRow(row);
	}
	
	/**
     * Método que permite obtener un objeto de la clase Tratamientos mediante su identificador y mostrarlo en la tabla.
     * @param input identificador del objeto
     * @param connection instancia de la clase HibernateConnection que permite al método comunicarse con la base de datos
     * @param currentModel modelo de la tabla en la que se mostrarán los resultados
     */
	private static void showTratamiento(String input, HibernateConnection connection, DefaultTableModel currentModel) {
		int id = Integer.parseInt(input);
		Tratamientos tratamiento = Operations.getTratamiento(connection.getSession(), id);
		String[] colNames = {"id", "nombre", "precio", "id_especialidad"};
		
		String[] row = new String[colNames.length];
		
		row[0] = String.valueOf(tratamiento.getId());
		row[1] = tratamiento.getNombre();
		row[2] = String.valueOf(tratamiento.getPrecio());
		
		if (tratamiento.getEspecialidad()!=null) {
			row[3] = String.valueOf(tratamiento.getEspecialidad().getId());
		} else {
			row[3] = "";
		}

		currentModel.setRowCount(0);
		currentModel.setColumnIdentifiers(colNames);
		currentModel.addRow(row);
	}
	
	/**
     * Método que permite obtener un objeto de la clase Usuarios mediante su identificador y mostrarlo en la tabla.
     * @param input identificador del objeto
     * @param connection instancia de la clase HibernateConnection que permite al método comunicarse con la base de datos
     * @param currentModel modelo de la tabla en la que se mostrarán los resultados
     */
	private static void showUsuario(String input, HibernateConnection connection, DefaultTableModel currentModel) {
		Usuarios usuario = Operations.getUsuario(connection.getSession(), input);
		String[] colNames = {"dni", "password", "admin", "desactivado"};
		
		String[] row = new String[colNames.length];
		
		row[0] = usuario.getDni();
		row[1] = usuario.getPassword();
		row[2] = String.valueOf(usuario.getAdmin());
		row[3] = String.valueOf(usuario.getDesactivado());

		currentModel.setRowCount(0);
		currentModel.setColumnIdentifiers(colNames);
		currentModel.addRow(row);
	}

}
