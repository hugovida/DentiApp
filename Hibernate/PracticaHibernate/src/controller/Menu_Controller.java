package controller;

import java.sql.Date;

import javax.swing.JOptionPane;

import model.Citas;
import model.Dientes;
import model.Doctores;
import model.Especialidades;
import model.Pacientes;
import model.Pagos;
import model.Tratamientos;
import model.Usuarios;
import view.MainMenu;
import view.Query;

/**
 * Clase que corresponde al controlador del menú principal de la aplicación.
 */
public class Menu_Controller {
	
	/**
     * Método que permite instanciar el JDialog de realizar consulta y hacerlo visible en el centro de la pantalla.
     *
     * @param m instancia de la clase MainMenu de la que dependerá la ventana de diálogo
     * @param connection instancia de la clase HibernateConnection que permitirá a la ventana de diálogo comunicarse con la base de datos
     */
	public static void openQuery(MainMenu m, HibernateConnection connection) {
		Query query = new Query(m,connection);
		query.setLocationRelativeTo(null);
		query.setVisible(true);
	}
	
	/**
     * Método que permite abrir el menú de inserción.
     *
     * @param m instancia de la clase MainMenu de la que dependerá la ventana de diálogo
     * @param connection instancia de la clase HibernateConnection que permitirá a la ventana de diálogo comunicarse con la base de datos
     */
	public static void openSave(MainMenu m, HibernateConnection connection) {
		String[] choices = { "citas", "dientes", "doctores", "especialidades", "pacientes", 
				"pagos", "tratamientos", "usuarios" };
	    String input = (String) JOptionPane.showInputDialog(m, "Seleccione qué tipo de objeto desea insertar:",
	        "Inserción de objetos", JOptionPane.QUESTION_MESSAGE, null,
	        choices,
	        choices[0]);
	    
	    if (input!=null) {
	    	try {
		    	switch (input) {
		    	case "citas":
		    		insertCita(m, connection);
		    		break;
		    	case "dientes":
		    		insertDiente(m, connection);
		    		break;
		    	case "doctores":
		    		insertDoctor(m, connection);
		    		break;
		    	case "especialidades":
		    		insertEspecialidad(m, connection);
		    		break;
		    	case "pacientes":
		    		insertPaciente(m, connection);
		    		break;
		    	case "pagos":
		    		insertPago(m, connection);
		    		break;
		    	case "tratamientos":
		    		insertTratamiento(m, connection);
		    		break;
		    	case "usuarios":
		    		insertUsuario(m, connection);
		    		break;
		    	}
		    	JOptionPane.showMessageDialog(m, "El objeto ha sido insertado con éxito.",
					      "Inserción completada", JOptionPane.INFORMATION_MESSAGE);
		    } catch (Exception e) {
				JOptionPane.showMessageDialog(m, "No ha sido posible insertar el objeto.",
					      "Error", JOptionPane.ERROR_MESSAGE);
			}
	    }
	}
	
	/**
     * Método que permite abrir el menú de modificación.
     *
     * @param m instancia de la clase MainMenu de la que dependerá la ventana de diálogo
     * @param connection instancia de la clase HibernateConnection que permitirá a la ventana de diálogo comunicarse con la base de datos
     */
	public static void openUpdate(MainMenu m, HibernateConnection connection) {
		String[] choices = { "citas", "dientes", "doctores", "especialidades", "pacientes", 
				"pagos", "tratamientos", "usuarios" };
	    String input = (String) JOptionPane.showInputDialog(m, "Seleccione qué tipo de objeto desea modificar:",
	        "Modificación de objetos", JOptionPane.QUESTION_MESSAGE, null,
	        choices,
	        choices[0]);
	    
	    if (input!=null) {
	    	String idInput = JOptionPane.showInputDialog(
    				m,
    				"Introduzca el identificador del objeto a modificar en la tabla "+input+":",
    				"Modificación de objetos",
    				JOptionPane.INFORMATION_MESSAGE);
	    	
	    	if (idInput != null && !idInput.isEmpty()) {
	    		try {
			    	switch (input) {
			    	case "citas":
			    		modifyCita(m, connection, idInput);
			    		break;
			    	case "dientes":
			    		modifyDiente(m, connection, idInput);
			    		break;
			    	case "doctores":
			    		modifyDoctor(m, connection, idInput);
			    		break;
			    	case "especialidades":
			    		modifyEspecialidad(m, connection, idInput);
			    		break;
			    	case "pacientes":
			    		modifyPaciente(m, connection, idInput);
			    		break;
			    	case "pagos":
			    		modifyPago(m, connection, idInput);
			    		break;
			    	case "tratamientos":
			    		modifyTratamiento(m, connection, idInput);
			    		break;
			    	case "usuarios":
			    		modifyUsuario(m, connection, idInput);
			    		break;
			    	}
			    	JOptionPane.showMessageDialog(m, "El objeto ha sido modificado con éxito.",
						      "Modificación completada", JOptionPane.INFORMATION_MESSAGE);
			    } catch (Exception e) {
					JOptionPane.showMessageDialog(m, "No ha sido posible modificar el objeto.",
						      "Error", JOptionPane.ERROR_MESSAGE);
				}
	    	}
	    }
	}
	
	/**
     * Método que permite abrir el menú de eliminación.
     *
     * @param m instancia de la clase MainMenu de la que dependerá la ventana de diálogo
     * @param connection instancia de la clase HibernateConnection que permitirá a la ventana de diálogo comunicarse con la base de datos
     */
	public static void openDelete(MainMenu m, HibernateConnection connection) {
		String[] choices = { "citas", "dientes", "doctores", "especialidades", "pacientes", 
				"pagos", "tratamientos", "usuarios" };
	    String input = (String) JOptionPane.showInputDialog(m, "Seleccione qué tipo de objeto desea eliminar:",
	        "Eliminación de objetos", JOptionPane.QUESTION_MESSAGE, null,
	        choices,
	        choices[0]);
	    
	    if (input!=null) {
	    	String idInput = JOptionPane.showInputDialog(
    				m,
    				"Introduzca el identificador del objeto a eliminar en la tabla "+input+":",
    				"Eliminación de objetos",
    				JOptionPane.INFORMATION_MESSAGE);
	    	
	    	if (idInput != null && !idInput.isEmpty()) {
	    		boolean eliminacion = false;
	    		
	    		switch (input) {
		    	case "citas":
		    		int idCita = Integer.parseInt(idInput);
	    			eliminacion = Operations.deleteCita(connection.getSession(), idCita);
		    		break;
		    	case "dientes":
		    		int idDiente = Integer.parseInt(idInput);
		    		eliminacion = Operations.deleteDiente(connection.getSession(), idDiente);
		    		break;
		    	case "doctores":
		    		int idDoctor = Integer.parseInt(idInput);
		    		eliminacion = Operations.deleteDoctor(connection.getSession(), idDoctor);
		    		break;
		    	case "especialidades":
		    		int idEspecialidad = Integer.parseInt(idInput);
		    		eliminacion = Operations.deleteDiente(connection.getSession(), idEspecialidad);
		    		break;
		    	case "pacientes":
		    		eliminacion = Operations.deletePaciente(connection.getSession(), idInput);
		    		break;
		    	case "pagos":
		    		int idPago = Integer.parseInt(idInput);
		    		eliminacion = Operations.deletePago(connection.getSession(), idPago);
		    		break;
		    	case "tratamientos":
		    		int idTratamiento = Integer.parseInt(idInput);
		    		eliminacion = Operations.deleteTratamiento(connection.getSession(), idTratamiento);
		    		break;
		    	case "usuarios":
		    		eliminacion = Operations.deleteUsuario(connection.getSession(), idInput);
		    		break;
		    	}
	    		
	    		if (eliminacion) {
	    			JOptionPane.showMessageDialog(m, "El objeto ha sido eliminado con éxito.",
						      "Eliminación completada", JOptionPane.INFORMATION_MESSAGE);
	    		} else {
	    			JOptionPane.showMessageDialog(m, "No ha sido posible eliminar el objeto.",
						      "Error", JOptionPane.ERROR_MESSAGE);
	    		}
	    	}
	    }
	}
	
	/**
     * Método que permite generar un objeto de tipo Citas en base a los parámetros introducidos por el usuario.
     *
     * @param m instancia de la clase MainMenu de la que dependerá la ventana de diálogo
     * @return objeto de tipo Citas
     */
	private static Citas newCita(MainMenu m) {
		String[] parametrosNombre = {"fecha","notas"};
		String[] parametrosValor = new String[parametrosNombre.length];
		
		for (int i = 0; i < parametrosValor.length; i++) {
			parametrosValor[i] = JOptionPane.showInputDialog(null, "Introduzca un valor para el campo "+parametrosNombre[i], 
	                "Crear nueva cita", JOptionPane.INFORMATION_MESSAGE);
		}
		
		Date fecha = Date.valueOf(parametrosValor[0]);
		Citas cita = new Citas(fecha, parametrosValor[1]);
		
		return cita;
	}
	
	/**
     * Método que permite al usuario generar e insertar un objeto de tipo Citas.
     *
     * @param m instancia de la clase MainMenu de la que dependerá la ventana de diálogo
     * @param connection instancia de la clase HibernateConnection que permitirá a la ventana de diálogo comunicarse con la base de datos
     */
	private static void insertCita(MainMenu m, HibernateConnection connection) {
		Citas cita = newCita(m);
		
		String id_doctor = JOptionPane.showInputDialog(null, "Introduzca un valor para el campo id_doctor", 
                "Crear nueva cita", JOptionPane.INFORMATION_MESSAGE);
		int id = Integer.parseInt(id_doctor);
		Doctores doctor = connection.getSession().get(Doctores.class, id);
		String dni_paciente = JOptionPane.showInputDialog(null, "Introduzca un valor para el campo dni_paciente", 
                "Crear nueva cita", JOptionPane.INFORMATION_MESSAGE);
		Pacientes paciente = connection.getSession().get(Pacientes.class, dni_paciente);
		
		Operations.saveCita(connection.getSession(), cita, doctor, paciente);
	}
	
	/**
     * Método que permite al usuario modificar un campo de un objeto de tipo Citas y aplicar dicha modificación en la base de datos.
     *
     * @param m instancia de la clase MainMenu de la que dependerá la ventana de diálogo
     * @param connection instancia de la clase HibernateConnection que permitirá a la ventana de diálogo comunicarse con la base de datos
     * @param idInput identificador de la cita a modificar
     */
	private static void modifyCita(MainMenu m, HibernateConnection connection, String idInput) {
		if (idInput != null && !idInput.isEmpty()) {
			int id = Integer.parseInt(idInput);
			Citas cita = connection.getSession().get(Citas.class, id);
			String[] choices = {"id_doctor", "dni_paciente", "fecha", "notas"};
		    String selection = (String) JOptionPane.showInputDialog(m, "Seleccione qué campo del objeto desea modificar:",
		        "Modificación de objetos", JOptionPane.QUESTION_MESSAGE, null,
		        choices,
		        choices[0]);
		    
		    if (selection!=null) {
		    	String value = JOptionPane.showInputDialog(
	    				m,
	    				"Introduzca el nuevo valor para el campo "+selection+":",
	    				"Modificación de objetos",
	    				JOptionPane.INFORMATION_MESSAGE);
		    	
		    	switch(selection) {
		    	case "id_doctor":
		    		int citaIdDoctor = Integer.parseInt(value);
		    		Doctores doctor = connection.getSession().get(Doctores.class, citaIdDoctor);
		    		cita.setDoctor(doctor);
		    		break;
		    	case "fecha":
		    		Date citaFecha = Date.valueOf(value);
		    		cita.setFecha(citaFecha);
		    		break;
		    	case "notas":
		    		cita.setNotas(value);
		    		break;
		    	}
		    }
			Operations.updateCita(connection.getSession(), cita);
		}
	}
	
	/**
     * Método que permite generar un objeto de tipo Dientes en base a los parámetros introducidos por el usuario.
     *
     * @param m instancia de la clase MainMenu de la que dependerá la ventana de diálogo
     * @return objeto de tipo Dientes
     */
	private static Dientes newDiente(MainMenu m) {
		String[] parametrosNombre = {"num_diente","notas"};
		String[] parametrosValor = new String[parametrosNombre.length];
		
		for (int i = 0; i < parametrosValor.length; i++) {
			parametrosValor[i] = JOptionPane.showInputDialog(null, "Introduzca un valor para el campo "+parametrosNombre[i], 
	                "Crear nuevo diente", JOptionPane.INFORMATION_MESSAGE);
		}
		
		int num_diente = Integer.parseInt(parametrosValor[0]);
		Dientes diente = new Dientes(num_diente, parametrosValor[1]);
		
		return diente;
	}
	
	/**
     * Método que permite al usuario generar e insertar un objeto de tipo Dientes.
     *
     * @param m instancia de la clase MainMenu de la que dependerá la ventana de diálogo
     * @param connection instancia de la clase HibernateConnection que permitirá a la ventana de diálogo comunicarse con la base de datos
     */
	private static void insertDiente(MainMenu m, HibernateConnection connection) {
		Dientes diente = newDiente(m);
		
		String dni_paciente = JOptionPane.showInputDialog(null, "Introduzca un valor para el campo dni_paciente", 
                "Crear nuevo diente", JOptionPane.INFORMATION_MESSAGE);
		Pacientes paciente = connection.getSession().get(Pacientes.class, dni_paciente);
		
		Operations.saveDiente(connection.getSession(), diente, paciente);
	}
	
	/**
     * Método que permite al usuario modificar un campo de un objeto de tipo Dientes y aplicar dicha modificación en la base de datos.
     *
     * @param m instancia de la clase MainMenu de la que dependerá la ventana de diálogo
     * @param connection instancia de la clase HibernateConnection que permitirá a la ventana de diálogo comunicarse con la base de datos
     * @param idInput identificador del diente a modificar
     */
	private static void modifyDiente(MainMenu m, HibernateConnection connection, String idInput) {
		if (idInput != null && !idInput.isEmpty()) {
			int id = Integer.parseInt(idInput);
			Dientes diente = connection.getSession().get(Dientes.class, id);
			String[] choices = {"dni_paciente", "num_diente", "notas"};
		    String selection = (String) JOptionPane.showInputDialog(m, "Seleccione qué campo del objeto desea modificar:",
		        "Modificación de objetos", JOptionPane.QUESTION_MESSAGE, null,
		        choices,
		        choices[0]);
		    
		    if (selection!=null) {
		    	String value = JOptionPane.showInputDialog(
	    				m,
	    				"Introduzca el nuevo valor para el campo "+selection+":",
	    				"Modificación de objetos",
	    				JOptionPane.INFORMATION_MESSAGE);
		    	
		    	switch(selection) {
		    	case "dni_paciente":
		    		Pacientes paciente = connection.getSession().get(Pacientes.class, value);
		    		diente.setPaciente(paciente);
		    		break;
		    	case "num_diente":
		    		int dienteNum = Integer.parseInt(value);
		    		diente.setNum_diente(dienteNum);
		    		break;
		    	case "notas":
		    		diente.setNotas(value);
		    		break;
		    	}
		    }
			Operations.updateDiente(connection.getSession(), diente);
		}
	}
	
	/**
     * Método que permite generar un objeto de tipo Doctores en base a los parámetros introducidos por el usuario.
     *
     * @param m instancia de la clase MainMenu de la que dependerá la ventana de diálogo
     * @return objeto de tipo Doctores
     */
	private static Doctores newDoctores(MainMenu m) {
		String[] parametrosNombre = {"nombre","apellidos","telefono","fecha_nacimiento","correo","baja"};
		String[] parametrosValor = new String[parametrosNombre.length];
		
		for (int i = 0; i < parametrosValor.length; i++) {
			parametrosValor[i] = JOptionPane.showInputDialog(null, "Introduzca un valor para el campo "+parametrosNombre[i], 
	                "Crear nuevo doctor", JOptionPane.INFORMATION_MESSAGE);
		}
		
		Date fecha_nacimiento = null;
		if (parametrosValor[3]!=null && !parametrosValor[3].isEmpty()) {
			fecha_nacimiento = Date.valueOf(parametrosValor[3]);
		}
		
		boolean baja = false;
		if (parametrosValor[5].equalsIgnoreCase("true")) {
			baja = true;
		}
		
		Doctores doctor = new Doctores(parametrosValor[0],parametrosValor[1],parametrosValor[2],
				fecha_nacimiento, parametrosValor[4],baja);
		
		return doctor;
	}
	
	/**
     * Método que permite al usuario generar e insertar un objeto de tipo Doctores.
     *
     * @param m instancia de la clase MainMenu de la que dependerá la ventana de diálogo
     * @param connection instancia de la clase HibernateConnection que permitirá a la ventana de diálogo comunicarse con la base de datos
     */
	private static void insertDoctor(MainMenu m, HibernateConnection connection) {
		Doctores doctor = newDoctores(m);
		
		String dni = JOptionPane.showInputDialog(null, "Introduzca un valor para el campo dni", 
                "Crear nuevo doctor", JOptionPane.INFORMATION_MESSAGE);
		Usuarios usuario = connection.getSession().get(Usuarios.class, dni);
		
		Operations.saveDoctor(connection.getSession(), doctor, usuario);
	}
	
	/**
     * Método que permite al usuario modificar un campo de un objeto de tipo Doctores y aplicar dicha modificación en la base de datos.
     *
     * @param m instancia de la clase MainMenu de la que dependerá la ventana de diálogo
     * @param connection instancia de la clase HibernateConnection que permitirá a la ventana de diálogo comunicarse con la base de datos
     * @param idInput identificador del doctor a modificar
     */
	private static void modifyDoctor(MainMenu m, HibernateConnection connection, String idInput) {
		if (idInput != null && !idInput.isEmpty()) {
			int id = Integer.parseInt(idInput);
			Doctores doctor = connection.getSession().get(Doctores.class, id);
			String[] choices = {"dni", "nombre", "apellidos", "telefono", "fecha_nacimiento", "correo", "baja"};
		    String selection = (String) JOptionPane.showInputDialog(m, "Seleccione qué campo del objeto desea modificar:",
		        "Modificación de objetos", JOptionPane.QUESTION_MESSAGE, null,
		        choices,
		        choices[0]);
		    
		    if (selection!=null) {
		    	String value = JOptionPane.showInputDialog(
	    				m,
	    				"Introduzca el nuevo valor para el campo "+selection+":",
	    				"Modificación de objetos",
	    				JOptionPane.INFORMATION_MESSAGE);
		    	
		    	switch(selection) {
		    	case "dni":
		    		Usuarios usuario = connection.getSession().get(Usuarios.class, value);
		    		doctor.setUsuario(usuario);
		    		break;
		    	case "nombre":
		    		doctor.setNombre(value);
		    		break;
		    	case "apellidos":
		    		doctor.setApellidos(value);
		    		break;
		    	case "telefono":
		    		doctor.setTelefono(value);
		    		break;
		    	case "fecha_nacimiento":
		    		Date doctorFecha = Date.valueOf(value);
		    		doctor.setFecha_nacimiento(doctorFecha);
		    		break;
		    	case "correo":
		    		doctor.setCorreo(value);
		    		break;
		    	case "baja":
		    		boolean baja = Boolean.parseBoolean(value);
		    		doctor.setBaja(baja);
		    		break;
		    	}
		    }
			Operations.updateDoctor(connection.getSession(), doctor);
		}
	}
	
	/**
     * Método que permite generar un objeto de tipo Especialidades en base a los parámetros introducidos por el usuario.
     *
     * @param m instancia de la clase MainMenu de la que dependerá la ventana de diálogo
     * @return objeto de tipo Especialidades
     */
	private static Especialidades newEspecialidad(MainMenu m) {
		String[] parametrosNombre = {"nombre"};
		String[] parametrosValor = new String[parametrosNombre.length];
		
		for (int i = 0; i < parametrosValor.length; i++) {
			parametrosValor[i] = JOptionPane.showInputDialog(null, "Introduzca un valor para el campo "+parametrosNombre[i], 
	                "Crear nueva especialidad", JOptionPane.INFORMATION_MESSAGE);
		}
		
		Especialidades especialidad = new Especialidades(parametrosValor[0]);
		
		return especialidad;
	}
	
	/**
     * Método que permite al usuario generar e insertar un objeto de tipo Especialidades.
     *
     * @param m instancia de la clase MainMenu de la que dependerá la ventana de diálogo
     * @param connection instancia de la clase HibernateConnection que permitirá a la ventana de diálogo comunicarse con la base de datos
     */
	private static void insertEspecialidad(MainMenu m, HibernateConnection connection) {
		Especialidades especialidad = newEspecialidad(m);
		
		Operations.saveEspecialidad(connection.getSession(), especialidad);
	}
	
	/**
     * Método que permite al usuario modificar un campo de un objeto de tipo Especialidades y aplicar dicha modificación en la base de datos.
     *
     * @param m instancia de la clase MainMenu de la que dependerá la ventana de diálogo
     * @param connection instancia de la clase HibernateConnection que permitirá a la ventana de diálogo comunicarse con la base de datos
     * @param idInput identificador de la especialidad a modificar
     */
	private static void modifyEspecialidad(MainMenu m, HibernateConnection connection, String idInput) {
		if (idInput != null && !idInput.isEmpty()) {
			int id = Integer.parseInt(idInput);
			Especialidades especialidad = connection.getSession().get(Especialidades.class, id);
			String[] choices = {"nombre"};
		    String selection = (String) JOptionPane.showInputDialog(m, "Seleccione qué campo del objeto desea modificar:",
		        "Modificación de objetos", JOptionPane.QUESTION_MESSAGE, null,
		        choices,
		        choices[0]);
		    
		    if (selection!=null) {
		    	String value = JOptionPane.showInputDialog(
	    				m,
	    				"Introduzca el nuevo valor para el campo "+selection+":",
	    				"Modificación de objetos",
	    				JOptionPane.INFORMATION_MESSAGE);
		    	
		    	especialidad.setNombre(value);
		    }
			Operations.updateEspecialidad(connection.getSession(), especialidad);
		}
	}
	
	/**
     * Método que permite generar un objeto de tipo Pacientes en base a los parámetros introducidos por el usuario.
     *
     * @param m instancia de la clase MainMenu de la que dependerá la ventana de diálogo
     * @return objeto de tipo Pacientes
     */
	private static Pacientes newPacientes(MainMenu m) {
		String[] parametrosNombre = {"dni","nombre","apellidos","telefono","fecha_nacimiento","correo"};
		String[] parametrosValor = new String[parametrosNombre.length];
		
		for (int i = 0; i < parametrosValor.length; i++) {
			parametrosValor[i] = JOptionPane.showInputDialog(null, "Introduzca un valor para el campo "+parametrosNombre[i], 
	                "Crear nuevo paciente", JOptionPane.INFORMATION_MESSAGE);
		}
		
		Date fecha_nacimiento = null;
		if (parametrosValor[4]!=null && !parametrosValor[4].isEmpty()) {
			fecha_nacimiento = Date.valueOf(parametrosValor[4]);
		}
		
		Pacientes paciente = new Pacientes(parametrosValor[0],parametrosValor[1],parametrosValor[2],
				parametrosValor[3], fecha_nacimiento, parametrosValor[5]);
		
		return paciente;
	}
	
	/**
     * Método que permite al usuario generar e insertar un objeto de tipo Pacientes.
     *
     * @param m instancia de la clase MainMenu de la que dependerá la ventana de diálogo
     * @param connection instancia de la clase HibernateConnection que permitirá a la ventana de diálogo comunicarse con la base de datos
     */
	private static void insertPaciente(MainMenu m, HibernateConnection connection) {
		Pacientes paciente = newPacientes(m);
		
		Operations.savePaciente(connection.getSession(), paciente);
	}
	
	/**
     * Método que permite al usuario modificar un campo de un objeto de tipo Pacientes y aplicar dicha modificación en la base de datos.
     *
     * @param m instancia de la clase MainMenu de la que dependerá la ventana de diálogo
     * @param connection instancia de la clase HibernateConnection que permitirá a la ventana de diálogo comunicarse con la base de datos
     * @param idInput identificador del paciente a modificar
     */
	private static void modifyPaciente(MainMenu m, HibernateConnection connection, String idInput) {
		if (idInput != null && !idInput.isEmpty()) {
			Pacientes paciente = connection.getSession().get(Pacientes.class, idInput);
			String[] choices = {"nombre", "apellidos", "telefono", "fecha_nacimiento", "correo"};
		    String selection = (String) JOptionPane.showInputDialog(m, "Seleccione qué campo del objeto desea modificar:",
		        "Modificación de objetos", JOptionPane.QUESTION_MESSAGE, null,
		        choices,
		        choices[0]);
		    
		    if (selection!=null) {
		    	String value = JOptionPane.showInputDialog(
	    				m,
	    				"Introduzca el nuevo valor para el campo "+selection+":",
	    				"Modificación de objetos",
	    				JOptionPane.INFORMATION_MESSAGE);
		    	
		    	switch(selection) {
		    	case "nombre":
		    		paciente.setNombre(value);
		    		break;
		    	case "apellidos":
		    		paciente.setApellidos(value);
		    		break;
		    	case "telefono":
		    		paciente.setTelefono(value);
		    		break;
		    	case "fecha_nacimiento":
		    		Date doctorFecha = Date.valueOf(value);
		    		paciente.setFecha_nacimiento(doctorFecha);
		    		break;
		    	case "correo":
		    		paciente.setCorreo(value);
		    		break;
		    	}
		    }
			Operations.updatePaciente(connection.getSession(), paciente);
		}
	}
	
	/**
     * Método que permite generar un objeto de tipo Pagos en base a los parámetros introducidos por el usuario.
     *
     * @param m instancia de la clase MainMenu de la que dependerá la ventana de diálogo
     * @return objeto de tipo Pagos
     */
	private static Pagos newPago(MainMenu m) {
		String[] parametrosNombre = {"mensualidad","tipo","importe","fecha_registro","fecha_pago"};
		String[] parametrosValor = new String[parametrosNombre.length];
		
		for (int i = 0; i < parametrosValor.length; i++) {
			parametrosValor[i] = JOptionPane.showInputDialog(null, "Introduzca un valor para el campo "+parametrosNombre[i], 
	                "Crear nuevo pago", JOptionPane.INFORMATION_MESSAGE);
		}
		
		Integer mensualidad = null;
		if (parametrosValor[0]!=null && !parametrosValor[0].isEmpty()) {
			mensualidad = Integer.parseInt(parametrosValor[0]);
		}
		
		Integer tipo = null;
		if (parametrosValor[1]!=null && !parametrosValor[1].isEmpty()) {
			tipo = Integer.parseInt(parametrosValor[1]);
		}
		
		Double importe = Double.parseDouble(parametrosValor[2]);
		
		Date fecha_registro = Date.valueOf(parametrosValor[3]);
		
		Date fecha_pago = null;
		if (parametrosValor[4]!=null && !parametrosValor[4].isEmpty()) {
			fecha_pago = Date.valueOf(parametrosValor[4]);
		}
		
		Pagos pago = new Pagos(mensualidad, tipo, importe, fecha_registro, fecha_pago);
		
		return pago;
	}
	
	/**
     * Método que permite al usuario generar e insertar un objeto de tipo Pagos.
     *
     * @param m instancia de la clase MainMenu de la que dependerá la ventana de diálogo
     * @param connection instancia de la clase HibernateConnection que permitirá a la ventana de diálogo comunicarse con la base de datos
     */
	private static void insertPago(MainMenu m, HibernateConnection connection) {
		Pagos pago = newPago(m);
		
		String dni_paciente = JOptionPane.showInputDialog(null, "Introduzca un valor para el campo dni_paciente", 
                "Crear nuevo pago", JOptionPane.INFORMATION_MESSAGE);
		Pacientes paciente = connection.getSession().get(Pacientes.class, dni_paciente);
		
		Operations.savePagos(connection.getSession(), pago, paciente);
	}
	
	/**
     * Método que permite al usuario modificar un campo de un objeto de tipo Pagos y aplicar dicha modificación en la base de datos.
     *
     * @param m instancia de la clase MainMenu de la que dependerá la ventana de diálogo
     * @param connection instancia de la clase HibernateConnection que permitirá a la ventana de diálogo comunicarse con la base de datos
     * @param idInput identificador del pago a modificar
     */
	private static void modifyPago(MainMenu m, HibernateConnection connection, String idInput) {
		if (idInput != null && !idInput.isEmpty()) {
			int id = Integer.parseInt(idInput);
			Pagos pago = connection.getSession().get(Pagos.class, id);
			String[] choices = {"dni_paciente", "mensualidad", "tipo", "importe", "fecha_registro", "fecha_pago"};
		    String selection = (String) JOptionPane.showInputDialog(m, "Seleccione qué campo del objeto desea modificar:",
		        "Modificación de objetos", JOptionPane.QUESTION_MESSAGE, null,
		        choices,
		        choices[0]);
		    
		    if (selection!=null) {
		    	String value = JOptionPane.showInputDialog(
	    				m,
	    				"Introduzca el nuevo valor para el campo "+selection+":",
	    				"Modificación de objetos",
	    				JOptionPane.INFORMATION_MESSAGE);
		    	
		    	switch(selection) {
		    	case "dni_paciente":
		    		Pacientes paciente = connection.getSession().get(Pacientes.class, value);
		    		pago.setPaciente(paciente);
		    		break;
		    	case "mensualidad":
		    		int pagoMens = Integer.parseInt(value);
		    		pago.setMensualidad(pagoMens);
		    		break;
		    	case "tipo":
		    		int pagoTipo = Integer.parseInt(value);
		    		pago.setTipo(pagoTipo);
		    		break;
		    	case "importe":
		    		double pagoImporte = Double.parseDouble(value);
		    		pago.setImporte(pagoImporte);
		    		break;
		    	case "fecha_registro":
		    		Date pagoFechaReg = Date.valueOf(value);
		    		pago.setFecha_registro(pagoFechaReg);
		    		break;
		    	case "fecha_pago":
		    		Date pagoFechaPago = Date.valueOf(value);
		    		pago.setFecha_pago(pagoFechaPago);
		    		break;
		    	}
		    }
			Operations.updatePago(connection.getSession(), pago);
		}
	}
	
	/**
     * Método que permite generar un objeto de tipo Tratamientos en base a los parámetros introducidos por el usuario.
     *
     * @param m instancia de la clase MainMenu de la que dependerá la ventana de diálogo
     * @return objeto de tipo Tratamientos
     */
	private static Tratamientos newTratamiento(MainMenu m) {
		String[] parametrosNombre = {"nombre","precio"};
		String[] parametrosValor = new String[parametrosNombre.length];
		
		for (int i = 0; i < parametrosValor.length; i++) {
			parametrosValor[i] = JOptionPane.showInputDialog(null, "Introduzca un valor para el campo "+parametrosNombre[i], 
	                "Crear nuevo tratamiento", JOptionPane.INFORMATION_MESSAGE);
		}
		
		Double precio = Double.parseDouble(parametrosValor[1]);
		
		Tratamientos tratamiento = new Tratamientos(parametrosValor[0],precio);
		
		return tratamiento;
	}
	
	/**
     * Método que permite al usuario generar e insertar un objeto de tipo Tratamientos.
     *
     * @param m instancia de la clase MainMenu de la que dependerá la ventana de diálogo
     * @param connection instancia de la clase HibernateConnection que permitirá a la ventana de diálogo comunicarse con la base de datos
     */
	private static void insertTratamiento(MainMenu m, HibernateConnection connection) {
		Tratamientos tratamiento = newTratamiento(m);
		
		String id_especialidad = JOptionPane.showInputDialog(null, "Introduzca un valor para el campo id_especialidad", 
                "Crear nuevo tratamiento", JOptionPane.INFORMATION_MESSAGE);
		int id = Integer.parseInt(id_especialidad);
		Especialidades especialidad = connection.getSession().get(Especialidades.class, id);
		
		Operations.saveTratamiento(connection.getSession(), tratamiento, especialidad);
	}
	
	/**
     * Método que permite al usuario modificar un campo de un objeto de tipo Tratamientos y aplicar dicha modificación en la base de datos.
     *
     * @param m instancia de la clase MainMenu de la que dependerá la ventana de diálogo
     * @param connection instancia de la clase HibernateConnection que permitirá a la ventana de diálogo comunicarse con la base de datos
     * @param idInput identificador del tratamiento a modificar
     */
	private static void modifyTratamiento(MainMenu m, HibernateConnection connection, String idInput) {
		if (idInput != null && !idInput.isEmpty()) {
			int id = Integer.parseInt(idInput);
			Tratamientos tratamiento = connection.getSession().get(Tratamientos.class, id);
			String[] choices = {"nombre", "precio", "id_especialidad"};
		    String selection = (String) JOptionPane.showInputDialog(m, "Seleccione qué campo del objeto desea modificar:",
		        "Modificación de objetos", JOptionPane.QUESTION_MESSAGE, null,
		        choices,
		        choices[0]);
		    
		    if (selection!=null) {
		    	String value = JOptionPane.showInputDialog(
	    				m,
	    				"Introduzca el nuevo valor para el campo "+selection+":",
	    				"Modificación de objetos",
	    				JOptionPane.INFORMATION_MESSAGE);
		    	
		    	switch(selection) {
		    	case "nombre":
		    		tratamiento.setNombre(value);
		    		break;
		    	case "precio":
		    		double precio = Double.parseDouble(value);
		    		tratamiento.setPrecio(precio);
		    		break;
		    	case "id_especialidad":
		    		int especialidadId = Integer.parseInt(value);
		    		Especialidades especialidad = connection.getSession().get(Especialidades.class, especialidadId);
		    		tratamiento.setEspecialidad(especialidad);
		    		break;
		    	}
		    }
			Operations.updateTratamiento(connection.getSession(), tratamiento);
		}
	}
	
	/**
     * Método que permite generar un objeto de tipo Usuarios en base a los parámetros introducidos por el usuario.
     *
     * @param m instancia de la clase MainMenu de la que dependerá la ventana de diálogo
     * @return objeto de tipo Usuarios
     */
	private static Usuarios newUsuarios(MainMenu m) {
		String[] parametrosNombre = {"dni","password","admin","desactivado"};
		String[] parametrosValor = new String[parametrosNombre.length];
		
		for (int i = 0; i < parametrosValor.length; i++) {
			parametrosValor[i] = JOptionPane.showInputDialog(null, "Introduzca un valor para el campo "+parametrosNombre[i], 
	                "Crear nuevo usuario", JOptionPane.INFORMATION_MESSAGE);
		}
		
		boolean admin = false;
		if (parametrosValor[2].equalsIgnoreCase("true")) {
			admin = true;
		}
		
		boolean desactivado = false;
		if (parametrosValor[3].equalsIgnoreCase("true")) {
			desactivado = true;
		}
		
		Usuarios usuario = new Usuarios(parametrosValor[0],parametrosValor[1],admin,desactivado);
		
		return usuario;
	}
	
	/**
     * Método que permite al usuario generar e insertar un objeto de tipo Usuarios.
     *
     * @param m instancia de la clase MainMenu de la que dependerá la ventana de diálogo
     * @param connection instancia de la clase HibernateConnection que permitirá a la ventana de diálogo comunicarse con la base de datos
     */
	private static void insertUsuario(MainMenu m, HibernateConnection connection) {
		Usuarios usuario = newUsuarios(m);
		
		Operations.saveUsuario(connection.getSession(), usuario);
	}
	
	/**
     * Método que permite al usuario modificar un campo de un objeto de tipo Usuarios y aplicar dicha modificación en la base de datos.
     *
     * @param m instancia de la clase MainMenu de la que dependerá la ventana de diálogo
     * @param connection instancia de la clase HibernateConnection que permitirá a la ventana de diálogo comunicarse con la base de datos
     * @param idInput identificador del usuario a modificar
     */
	private static void modifyUsuario(MainMenu m, HibernateConnection connection, String idInput) {
		if (idInput != null && !idInput.isEmpty()) {
			Usuarios usuario = connection.getSession().get(Usuarios.class, idInput);
			String[] choices = {"password", "admin", "desactivado"};
		    String selection = (String) JOptionPane.showInputDialog(m, "Seleccione qué campo del objeto desea modificar:",
		        "Modificación de objetos", JOptionPane.QUESTION_MESSAGE, null,
		        choices,
		        choices[0]);
		    
		    if (selection!=null) {
		    	String value = JOptionPane.showInputDialog(
	    				m,
	    				"Introduzca el nuevo valor para el campo "+selection+":",
	    				"Modificación de objetos",
	    				JOptionPane.INFORMATION_MESSAGE);
		    	
		    	switch(selection) {
		    	case "password":
		    		usuario.setPassword(value);
		    		break;
		    	case "admin":
		    		boolean admin = Boolean.parseBoolean(value);
		    		usuario.setAdmin(admin);
		    		break;
		    	case "desactivado":
		    		boolean desactivado = Boolean.parseBoolean(value);
		    		usuario.setDesactivado(desactivado);
		    		break;
		    	}
		    }
			Operations.updateUsuario(connection.getSession(), usuario);
		}
	}

}
