package controller;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import model.Doctor;
import view.Admin_Citas;
import view.Admin_ConsDoc;
import view.Admin_ConsPac;
import view.Admin_ConsProd;
import view.Admin_ConsProv;
import view.Admin_EconomicaMain;
import view.Admin_Especialidades;
import view.Admin_InsCita;
import view.Admin_InsDoc;
import view.Admin_InsPac;
import view.Admin_InsProd;
import view.Admin_InsProv;
import view.Admin_Main;
import view.Admin_MaterialMain;
import view.Admin_MedicaMain;
import view.Admin_ModDoc;
import view.Admin_ModPac;
import view.Admin_ModProd;
import view.Admin_ModProv;
import view.Admin_UsuariosMain;
import view.Doctor_Main;
import view.EasterEgg;
import view.Login_Main;
import view.Login_PassChange;

public class Menu_Controller {
	
	// abre la ventana de cambio de contraseña
	public static void openChangePass(MySQLConnection connection, Login_Main lm) {
		Login_PassChange passChange = new Login_PassChange(connection, lm);
		passChange.setLocationRelativeTo(null);
		passChange.setVisible(true);
	}
	
	// abre el frame de login, cerrando la conexión
	public static void openLogin(MySQLConnection connection) {
		try {
			connection.disconnect();
			Login_Main loginFrame = new Login_Main();
			loginFrame.setLocationRelativeTo(null);
			loginFrame.setVisible(true);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "No se ha podido conectar a la base de datos, vuelva a iniciar la aplicación.", "DentiApp",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	// abre el frame principal de admin
	public static void openAdminMain(MySQLConnection connection, String userDni) {
		Admin_Main adminFrame = new Admin_Main(connection, userDni);
		adminFrame.setLocationRelativeTo(null);
		adminFrame.setVisible(true);
	}
	
	// abre el dialog de easter egg
	public static void openEasterEgg(Login_Main frame) {
		EasterEgg egg = new EasterEgg(frame, true);
		egg.setLocationRelativeTo(null);
		egg.setVisible(true);
	}
	
	// abre el frame de gestión de usuarios
	public static void openUsers(MySQLConnection connection, String userDni) {
		Admin_UsuariosMain adminUsuariosFrame = new Admin_UsuariosMain(connection,userDni);
		adminUsuariosFrame.setLocationRelativeTo(null);
		adminUsuariosFrame.setVisible(true);
	}
	
	// abre el frame de consultar doctor
	public static void openConsDoc(MySQLConnection connection, String userDni) {
		Admin_ConsDoc consDocFrame = new Admin_ConsDoc(connection,userDni);
		consDocFrame.setLocationRelativeTo(null);
		consDocFrame.setVisible(true);
	}
	
	// abre el frame de añadir doctor
	public static void openInsDoc(MySQLConnection connection, String userDni) {
		Admin_InsDoc insDocFrame = new Admin_InsDoc(connection,userDni);
		insDocFrame.setLocationRelativeTo(null);
		insDocFrame.setVisible(true);
	}
	
	// abre el frame de modificar doctor
	public static void openModDoc(MySQLConnection connection, String userDni, String[] rowData) {
		Admin_ModDoc modDocFrame = new Admin_ModDoc(connection,userDni,rowData);
		modDocFrame.setLocationRelativeTo(null);
		modDocFrame.setVisible(true);
	}
	
	// abre el frame de consultar paciente
	public static void openConsPac(MySQLConnection connection, String userDni) {
		Admin_ConsPac consPacFrame = new Admin_ConsPac(connection,userDni);
		consPacFrame.setLocationRelativeTo(null);
		consPacFrame.setVisible(true);
	}
	
	// abre el frame de añadir paciente
	public static void openInsPac(MySQLConnection connection, String userDni) {
		Admin_InsPac insPacFrame = new Admin_InsPac(connection,userDni);
		insPacFrame.setLocationRelativeTo(null);
		insPacFrame.setVisible(true);
	}
	
	// abre el frame de modificar paciente
	public static void openModPac(MySQLConnection connection, String userDni, String[] rowData) {
		Admin_ModPac modPacFrame = new Admin_ModPac(connection,userDni,rowData);
		modPacFrame.setLocationRelativeTo(null);
		modPacFrame.setVisible(true);
	}
	
	// abre el frame de gestión de material
	public static void openMaterial(MySQLConnection connection, String userDni) {
		Admin_MaterialMain adminMaterialFrame = new Admin_MaterialMain(connection,userDni);
		adminMaterialFrame.setLocationRelativeTo(null);
		adminMaterialFrame.setVisible(true);
	}
	
	// abre el frame de gestión de productos
	public static void openProducts(MySQLConnection connection, String userDni) {
		Admin_ConsProd adminProdFrame = new Admin_ConsProd(connection,userDni);
		adminProdFrame.setLocationRelativeTo(null);
		adminProdFrame.setVisible(true);
	}
	
	// abre el frame de gestión de productos mostrando aquellos cuyo proveedor coincida con el pasado
	// por parámetro
	public static void openProducts(MySQLConnection connection, String userDni, String nif_proveedor) {
		Admin_ConsProd adminProdFrame = new Admin_ConsProd(connection,userDni,nif_proveedor);
		adminProdFrame.setLocationRelativeTo(null);
		adminProdFrame.setVisible(true);
	}
	
	// abre el frame de añadir producto
	public static void openInsProd(MySQLConnection connection, String userDni) {
		Admin_InsProd insProdFrame = new Admin_InsProd(connection,userDni);
		insProdFrame.setLocationRelativeTo(null);
		insProdFrame.setVisible(true);
	}
	
	// abre el frame de modificar producto
	public static void openModProd(MySQLConnection connection, String userDni, String[] rowData) {
		Admin_ModProd modProdFrame = new Admin_ModProd(connection,userDni,rowData);
		modProdFrame.setLocationRelativeTo(null);
		modProdFrame.setVisible(true);
	}
	
	// abre el frame de gestión de proveedores
	public static void openProviders(MySQLConnection connection, String userDni) {
		Admin_ConsProv adminProvFrame = new Admin_ConsProv(connection,userDni);
		adminProvFrame.setLocationRelativeTo(null);
		adminProvFrame.setVisible(true);
	}
	
	// abre el frame de añadir proveedor
	public static void openInsProv(MySQLConnection connection, String userDni) {
		Admin_InsProv insProvFrame = new Admin_InsProv(connection,userDni);
		insProvFrame.setLocationRelativeTo(null);
		insProvFrame.setVisible(true);
	}
	
	// abre el frame de modificar proveedor
	public static void openModProv(MySQLConnection connection, String userDni, String[] rowData) {
		Admin_ModProv modProvFrame = new Admin_ModProv(connection,userDni,rowData);
		modProvFrame.setLocationRelativeTo(null);
		modProvFrame.setVisible(true);
	}
	
	// abre el frame de gestión médica
	public static void openMedical(MySQLConnection connection, String userDni) {
		Admin_MedicaMain adminMedicaFrame = new Admin_MedicaMain(connection,userDni);
		adminMedicaFrame.setLocationRelativeTo(null);
		adminMedicaFrame.setVisible(true);
	}
	
	// abre el frame de gestión de especialidades
	public static void openSpecialties(MySQLConnection connection, String userDni) {
		Admin_Especialidades adminEspecFrame = new Admin_Especialidades(connection,userDni);
		adminEspecFrame.setLocationRelativeTo(null);
		adminEspecFrame.setVisible(true);
	}
	
	// abre el frame de gestión de especialidades mostrando las especialidades del doctor
	// cuyos datos han sido pasados por parámetro
	public static void openSpecialties(MySQLConnection connection, String userDni, String[] rowData) {
		Admin_Especialidades adminEspecFrame = new Admin_Especialidades(connection,userDni,rowData);
		adminEspecFrame.setLocationRelativeTo(null);
		adminEspecFrame.setVisible(true);
	}
	
	// abre el frame de gestión de citas
	public static void openAppointments(MySQLConnection connection, String userDni) {
		Admin_Citas adminCitasFrame = new Admin_Citas(connection,userDni);
		adminCitasFrame.setLocationRelativeTo(null);
		adminCitasFrame.setVisible(true);
	}
	
	// abre el frame de gestión de citas mostrando las citas que coincidan con los datos del doctor 
	// y fecha pasados por parámetro
	public static void openDocAppointments(MySQLConnection connection, String userDni,
			String[] rowData, String date) {
		Admin_Citas adminCitasFrame = new Admin_Citas(connection,userDni,rowData,date);
		adminCitasFrame.setLocationRelativeTo(null);
		adminCitasFrame.setVisible(true);
	}
	
	// abre el frame de gestión de citas mostrando las citas que coincidan con los datos del paciente 
	// y fecha pasados por parámetro
	public static void openPacAppointments(MySQLConnection connection, String userDni,
			String[] rowData, String date) {
		Admin_Citas adminCitasFrame = new Admin_Citas(connection,userDni,date,rowData);
		adminCitasFrame.setLocationRelativeTo(null);
		adminCitasFrame.setVisible(true);
	}
	
	// abre el frame de añadir cita
	public static void openInsApp(MySQLConnection connection, String userDni, String[] rowData) {
		Admin_InsCita adminInsCitaFrame = new Admin_InsCita(connection,userDni, rowData);
		adminInsCitaFrame.setLocationRelativeTo(null);
		adminInsCitaFrame.setVisible(true);
	}
	
	// abre el frame de gestión económica
	public static void openEconomical(MySQLConnection connection, String userDni) {
		Admin_EconomicaMain adminEconomicaFrame = new Admin_EconomicaMain(connection,userDni);
		adminEconomicaFrame.setLocationRelativeTo(null);
		adminEconomicaFrame.setVisible(true);
	}
	
	// abre el frame principal de doctor
	public static void openDoctorMain(MySQLConnection connection, String userDni) {
		Doctor_Main doctorFrame = new Doctor_Main(connection, userDni);
		doctorFrame.setLocationRelativeTo(null);
		doctorFrame.setVisible(true);
	}
	
	// establece la fecha actual, en español, en el JLabel pasado por parámetro
	public static void setDate(JLabel lblDate) {
		/* String date = java.time.LocalDate.now().toString();
		
		LocalDate currentDate = LocalDate.parse(date); */
		
		LocalDate currentDate = LocalDate.now();
		
		int day = currentDate.getDayOfMonth();
		Month month = currentDate.getMonth();
		int year = currentDate.getYear();
		
		String spanishMonth = month.getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault());
		
		lblDate.setText("Hoy es "+day+" de "+spanishMonth+" de "+year+".");
	}
	
	// establece una bienvenida con el nombre del doctor cuyo DNI ha sido pasado por parámetro en el JLabel también pasado por parámetro
	public static void setDoctorName(MySQLConnection connection, String userDni, JLabel lblWelcome) throws SQLException {
		Doctor_Controller controller = new Doctor_Controller(connection);
		ArrayList<Doctor> doctor = controller.getDoctor(userDni);
		
		if (!doctor.isEmpty()) {
			lblWelcome.setText("Bienvenid@ "+doctor.get(0).getNombre());
		}
	}
	
	// rellena el combobox pasado por parámetro con los últimos cien años, incluyendo el actual
	public static void setYearCb(JComboBox yearCb) {
		yearCb.addItem("");
		
		LocalDate currentDate = LocalDate.now();
		
		int year = currentDate.getYear();
		for (int i = 0; i < 100; i++) {
			yearCb.addItem(year);
			year--;
		}
	}
	
	// rellena el combobox pasado por parámetro con los números del uno al doce inclusive, representando los meses del año
	public static void setMonthCb(JComboBox monthCb) {
		monthCb.addItem("");
		
		for (int i = 1; i <= 12; i++) {
			monthCb.addItem(i);
		}
	}
	
	// rellena el combobox pasado por parámetro con los días del mes seleccionado, teniendo también en cuenta si el año es bisiesto
	public static void setDayCb(JComboBox dayCb, JComboBox monthCb, JComboBox yearCb) {
		dayCb.addItem("");
		
		int selectedYear = (int)yearCb.getSelectedItem();
		int selectedMonth = (int)monthCb.getSelectedItem();
		boolean leapYear = false;
		
		if (selectedYear%4==0) {
			leapYear=true;
		}
		
		if (selectedMonth == 2 && leapYear) { // si el mes es febrero y el año es bisiesto
			for (int i = 1; i <= 29; i++) {
				dayCb.addItem(i);
			}
		} else if (selectedMonth == 2 && !leapYear) { // si el mes es febrero y el año no es bisiesto
			for (int i = 1; i <= 28; i++) {
				dayCb.addItem(i);
			}
		// si el mes tiene 31 días	
		} else if ((selectedMonth <= 7 && selectedMonth%2!=0) || (selectedMonth > 7 && selectedMonth%2==0)) {
			for (int i = 1; i <= 31; i++) {
				dayCb.addItem(i);
			}
		// si el mes tiene 30 días
		} else {
			for (int i = 1; i <= 30; i++) {
				dayCb.addItem(i);
			}
		}
	}
	
	// obtiene los datos de la fila de la tabla seleccionada
	public static String[] getSelectedTableRowData(JTable table) {
		// creamos un array de String con un tamaño igual a las columnas de la tabla
		String[] rowData = new String[table.getColumnCount()];
		// obtenemos la fila seleccionada
		int selectedRow = table.getSelectedRow();
		// recorremos la fila guardando los datos en el array
		for (int i = 0; i < table.getColumnCount(); i++) {
		   rowData[i] = (String)table.getValueAt(selectedRow, i);
		}
		
		return rowData;
	}
	
	// comprueba si el String pasado por parámetro únicamente contiene números
	public static boolean checkNumber(String value) {
		boolean validNumber = false;
		
		try {
			Integer.parseInt(value);
			validNumber = true;
		} catch (NumberFormatException e) {
			System.out.println(e.getMessage());
		}
		
		return validNumber;
	}
	
	// comprueba si el dni pasado por parámetro es válido (está compuesto por números en sus ocho primeros caracteres y por una letra mayúscula en el noveno)
	public static boolean checkDni(String dni) {
		boolean validDni = false;
		
		if (dni.length() == 9) {
			String digits = dni.substring(0, 8);
			char letter = dni.charAt(8);
			
			try {
				Integer.parseInt(digits);
				validDni = true;
			} catch (NumberFormatException e) {
				System.out.println(e.getMessage());
			}
			
			if(!(letter>='A' && letter<='Z')) {
	            validDni = false;
	        }
		}
		
		return validDni;
	}
	
}
