package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import model.Cita;
import model.Doctor;
import model.Especialidad;
import controller.Cita_Controller;
import controller.Doctor_Controller;
import controller.Especialidad_Controller;
import controller.Menu_Controller;
import controller.MySQLConnection;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.ImageIcon;

import resources.LeftMenuButton;
import resources.MainButton;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class Admin_InsCita extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MySQLConnection connection;
	private String userDni;
	private String[] rowData;
	private ArrayList<Especialidad> specialtyList;
	private ArrayList<Doctor> doctorList;
	private JTextField dniField;
	private JTextField nameField;
	private JTextField surnameField;
	private JComboBox specialtyCb;
	private JComboBox doctorCb;
	private JComboBox dayCb;
	private JComboBox monthCb;
	private JComboBox yearCb;
	private JComboBox hourCb;

	/**
	 * Create the frame.
	 */
	public Admin_InsCita(MySQLConnection connection, String userDni, String[] rowData) {
		// asignamos a las variables connection, userDni y rowData los argumentos pasados al crear la ventana
		this.connection = connection;
		this.userDni = userDni;
		this.rowData = rowData;
		
		// inicializamos las listas que contendrán todas las especialidades disponibles y los
		// doctores de la especialidad seleccionada
		specialtyList = new ArrayList<Especialidad>();
		doctorList = new ArrayList<Doctor>();
					
		setTitle("DentiApp");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1366, 768);
		contentPane = new JPanel();

		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		contentPane.setLayout(gbl_contentPane);
		
		JPanel leftMenuPanel = new JPanel();
		GridBagConstraints gbc_leftMenuPanel = new GridBagConstraints();
		gbc_leftMenuPanel.fill = GridBagConstraints.BOTH;
		gbc_leftMenuPanel.gridx = 0;
		gbc_leftMenuPanel.gridy = 0;
		gbc_leftMenuPanel.weightx = 0.005;
		gbc_leftMenuPanel.weighty = 1;
		contentPane.add(leftMenuPanel, gbc_leftMenuPanel);
		leftMenuPanel.setLayout(new GridLayout(5, 0, 0, 0));
		
		LeftMenuButton homeBtn = new LeftMenuButton("",false);
		homeBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu_Controller.openAdminMain(connection, userDni);
				dispose();
			}
		});
		homeBtn.setIcon(new ImageIcon("img/diente.png"));
		leftMenuPanel.add(homeBtn);
		
		LeftMenuButton userManageBtn = new LeftMenuButton("Gestión de usuarios",false);
		userManageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu_Controller.openUsers(connection, userDni);
				dispose();
			}
		});
		userManageBtn.setIcon(new ImageIcon("img/user.png"));
		leftMenuPanel.add(userManageBtn);
		
		LeftMenuButton stockManageBtn = new LeftMenuButton("Gestión de material",false);
		stockManageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu_Controller.openMaterial(connection, userDni);
				dispose();
			}
		});
		stockManageBtn.setIcon(new ImageIcon("img/box.png"));
		leftMenuPanel.add(stockManageBtn);
		
		LeftMenuButton medicalManageBtn = new LeftMenuButton("Gestión médica",true);
		medicalManageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu_Controller.openMedical(connection, userDni);
				dispose();
			}
		});
		medicalManageBtn.setIcon(new ImageIcon("img/folder.png"));
		leftMenuPanel.add(medicalManageBtn);
		
		LeftMenuButton economicManageBtn = new LeftMenuButton("Gestión económica",false);
		economicManageBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu_Controller.openEconomical(connection, userDni);
				dispose();
			}
		});
		economicManageBtn.setIcon(new ImageIcon("img/dollar.png"));
		leftMenuPanel.add(economicManageBtn);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(249, 249, 249));
		mainPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc_mainPanel = new GridBagConstraints();
		gbc_mainPanel.fill = GridBagConstraints.BOTH;
		gbc_mainPanel.gridx = 1;
		gbc_mainPanel.gridy = 0;
		gbc_mainPanel.weightx = 0.995;
		gbc_mainPanel.weighty = 1;
		contentPane.add(mainPanel, gbc_mainPanel);
		
		JPanel centeredPanel = new JPanel();
		centeredPanel.setBackground(new Color(249, 249, 249));
		centeredPanel.setPreferredSize(new Dimension(1173, 729));
		centeredPanel.setLayout(null);
		mainPanel.add(centeredPanel, new GridBagConstraints());
		
		JLabel lblTabName = new JLabel("Gestión médica");
		lblTabName.setForeground(new Color(0, 75, 72));
		lblTabName.setFont(new Font("Segoe UI Black", Font.PLAIN, 44));
		lblTabName.setBounds(24, 11, 776, 60);
		centeredPanel.add(lblTabName);
		
		JLabel lblHint = new JLabel("Añadir nueva cita");
		lblHint.setForeground(new Color(0, 75, 72));
		lblHint.setFont(new Font("Segoe UI", Font.PLAIN, 28));
		lblHint.setBounds(24, 62, 776, 60);
		centeredPanel.add(lblHint);
		
		JLabel dniLbl = new JLabel("DNI del paciente");
		dniLbl.setForeground(new Color(0, 75, 72));
		dniLbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
		dniLbl.setBounds(133, 170, 274, 32);
		centeredPanel.add(dniLbl);
		
		dniField = new JTextField();
		dniField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		dniField.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(0, 1, 1, 0)));
		dniField.setBounds(133, 205, 274, 44);
		centeredPanel.add(dniField);
		
		JLabel nameLbl = new JLabel("Nombre del paciente");
		nameLbl.setForeground(new Color(0, 75, 72));
		nameLbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
		nameLbl.setBounds(437, 170, 274, 32);
		centeredPanel.add(nameLbl);
		
		nameField = new JTextField();
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		nameField.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(0, 1, 1, 0)));
		nameField.setBounds(437, 205, 274, 44);
		centeredPanel.add(nameField);
		
		JLabel surnameLbl = new JLabel("Apellidos del paciente");
		surnameLbl.setForeground(new Color(0, 75, 72));
		surnameLbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
		surnameLbl.setBounds(745, 170, 274, 32);
		centeredPanel.add(surnameLbl);
		
		surnameField = new JTextField();
		surnameField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		surnameField.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(0, 1, 1, 0)));
		surnameField.setBounds(745, 205, 274, 44);
		centeredPanel.add(surnameField);
		
		JLabel specialtyLbl = new JLabel("Seleccione una especialidad");
		specialtyLbl.setForeground(new Color(0, 75, 72));
		specialtyLbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
		specialtyLbl.setBounds(320, 259, 426, 32);
		centeredPanel.add(specialtyLbl);
		
		specialtyCb = new JComboBox();
		specialtyCb.setFont(new Font("Tahoma", Font.PLAIN, 20));
		specialtyCb.setBounds(320, 294, 440, 44);
		centeredPanel.add(specialtyCb);
		// rellenamos el combobox de especialidades
		setSpecialtyCb();
		
		JLabel doctorLbl = new JLabel("Seleccione un doctor");
		doctorLbl.setForeground(new Color(0, 75, 72));
		doctorLbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
		doctorLbl.setBounds(320, 348, 426, 32);
		centeredPanel.add(doctorLbl);
		
		doctorCb = new JComboBox();
		doctorCb.setEnabled(false);
		doctorCb.setFont(new Font("Tahoma", Font.PLAIN, 20));
		doctorCb.setBounds(320, 383, 440, 44);
		centeredPanel.add(doctorCb);
		
		JLabel datetimeLbl = new JLabel("Seleccione fecha y hora para la cita");
		datetimeLbl.setForeground(new Color(0, 75, 72));
		datetimeLbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
		datetimeLbl.setBounds(335, 437, 440, 32);
		centeredPanel.add(datetimeLbl);
		
		yearCb = new JComboBox();
		yearCb.setEnabled(false);
		yearCb.setFont(new Font("Tahoma", Font.PLAIN, 20));
		yearCb.setBounds(357, 472, 78, 44);
		centeredPanel.add(yearCb);
		setYearCb(); // se rellena el combo de año
		
		monthCb = new JComboBox();
		monthCb.setEnabled(false);
		monthCb.setFont(new Font("Tahoma", Font.PLAIN, 20));
		monthCb.setBounds(445, 472, 50, 44);
		centeredPanel.add(monthCb);
		
		dayCb = new JComboBox();
		dayCb.setEnabled(false);
		dayCb.setFont(new Font("Tahoma", Font.PLAIN, 20));
		dayCb.setBounds(505, 472, 50, 44);
		centeredPanel.add(dayCb);
		
		hourCb = new JComboBox();
		hourCb.setEnabled(false);
		hourCb.setFont(new Font("Tahoma", Font.PLAIN, 20));
		hourCb.setBounds(584, 472, 132, 44);
		centeredPanel.add(hourCb);
		
		// al realizar una selección en el combobox de especialidades
		specialtyCb.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	if (doctorCb.getItemCount() != 0) { // si el combo de doctores contiene items, lo vacía
	        		doctorCb.removeAllItems();
	        	}
		    	
		    	if (specialtyCb.getSelectedItem().equals("")) { // si la selección ha sido el dummy, se desactiva el combo de doctores
		        	doctorCb.setEnabled(false);
		        } else { // si se ha hecho una selección real, se rellena el combo de doctores y se activa
		        	setDoctorCb();
		        	doctorCb.setEnabled(true);
		        }
		    }
		});
		
		// al realizar una selección en el combobox de doctores
		doctorCb.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	// si el actionPerformed se ha activado a través del setEnabled(false) o se ha seleccionado el dummy, se desactiva el combo de año
		    	if (doctorCb.getSelectedItem() == null || doctorCb.getSelectedItem().equals("")) {
		        	yearCb.setSelectedIndex(0);
		    		yearCb.setEnabled(false);
		        } else { // si se ha hecho una selección real, se activa el combo de año
		        	yearCb.setEnabled(true);
		        }
		    }
		});
		
		// al realizar una selección en el combobox de año
		yearCb.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	if (monthCb.getItemCount() != 0) { // si el combo de mes contiene items, lo vacía
	        		monthCb.removeAllItems();
	        	}
	        	
	        	if (dayCb.getItemCount() != 0) { // si el combo de día contiene items, lo vacía
	        		dayCb.removeAllItems();
	        	}
		    	
		    	if (yearCb.getSelectedItem().equals("")) { // si la selección ha sido el dummy, se desactiva el combo de mes (y el de día al activarse el actionPerformed del combo de mes con el setEnabled)
		        	monthCb.setEnabled(false);
		        } else { // si se ha hecho una selección real, se rellena el combo de mes y se activa
		        	Menu_Controller.setMonthCb(monthCb);
		        	
		        	LocalDate currentDate = LocalDate.now();
		    		int year = currentDate.getYear();
		    		
		    		if (yearCb.getSelectedItem().equals(year)) {
		    			int month = currentDate.getMonthValue();
		    			
		    			for (int i = month-1; i > 0; i--) {
							monthCb.removeItemAt(i);
						}
		    		}
		    		
		        	monthCb.setEnabled(true);
		        }
		    }
		});
		
		// al realizar una selección en el combobox de mes
		monthCb.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	if (dayCb.getItemCount() != 0) { // si el combo de día contiene items, lo vacía
	        		dayCb.removeAllItems();
	        	}
		    	
		    	// si el actionPerformed se ha activado a través del setEnabled(false) o se ha seleccionado el dummy, se desactiva el combo de día
		    	if (monthCb.getSelectedItem() == null || monthCb.getSelectedItem().equals("")) {
		        	dayCb.setEnabled(false);
		        } else { // si se ha hecho una selección real, se rellena el combo de día y se activa
		        	Menu_Controller.setDayCb(dayCb, monthCb, yearCb);
		        	
		        	LocalDate currentDate = LocalDate.now();
		    		int year = currentDate.getYear();
		    		int month = currentDate.getMonthValue();
		    		
		    		if (yearCb.getSelectedItem().equals(year) && monthCb.getSelectedItem().equals(month)) {
		    			int day = currentDate.getDayOfMonth();
		    			
		    			for (int i = day-1; i > 0; i--) {
							dayCb.removeItemAt(i);
						}
		    		}
		    		
		        	dayCb.setEnabled(true);
		        }
		    }
		});
		
		// al realizar una selección en el combobox de día
		dayCb.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) {
		    	if (hourCb.getItemCount() != 0) { // si el combo de horas contiene items, lo vacía
	        		hourCb.removeAllItems();
	        	}
		    	
		    	// si el actionPerformed se ha activado a través del setEnabled(false) o se ha seleccionado el dummy, se desactiva el combo de horas
		    	if (dayCb.getSelectedItem() == null || dayCb.getSelectedItem().equals("")) {
		        	hourCb.setEnabled(false);
		        } else { // si se ha hecho una selección real, se rellena el combo de horas y se activa
		        	setHourCb();
		        	hourCb.setEnabled(true);
		        }
		    }
		});
		
		MainButton insertBtn = new MainButton("Añadir cita");
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertAppointment();
				Menu_Controller.openConsPac(connection, userDni);
				dispose();
			}
		});
		insertBtn.setBounds(381, 530, 310, 44);
		centeredPanel.add(insertBtn);
		
		MainButton backBtn = new MainButton("Volver");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu_Controller.openConsPac(connection, userDni);
				dispose();
			}
		});
		backBtn.setBounds(963, 674, 200, 44);
		centeredPanel.add(backBtn);
		
		JLabel dienteBG = new JLabel("");
		dienteBG.setLocation(216, 90);
		dienteBG.setSize(584, 541);
		dienteBG.setIcon(new ImageIcon("img/diente_lowopacity.png"));
		centeredPanel.add(dienteBG);
		
		// rellenamos los campos correspondientes con los datos del paciente
		fillFields();
	}
	
	// comprueba que los campos son válidos antes de la inserción e informa al usuario en caso contrario
	private boolean checkFields() {
		if (specialtyCb.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(this, "Es necesario seleccionar una especialidad.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (doctorCb.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(this, "Es necesario seleccionar un doctor.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (yearCb.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(this, "Es necesario seleccionar un año.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (monthCb.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(this, "Es necesario seleccionar un mes.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (dayCb.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(this, "Es necesario seleccionar un día.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (hourCb.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(this, "Es necesario seleccionar una hora.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			return true;
		}
	}
	
	// efectúa la inserción de la cita
	private void insertAppointment() {
		if (checkFields()) {
			Cita_Controller controller = new Cita_Controller(connection);
			
			String dni_doctor = doctorList.get(doctorCb.getSelectedIndex()-1).getDni();
			String dni_paciente = dniField.getText();
			
			String dia = String.valueOf(dayCb.getSelectedItem());
			String mes = String.valueOf(monthCb.getSelectedItem());
			String year = String.valueOf(yearCb.getSelectedItem());
			
			if (dia.length() == 1) {
				dia = "0"+dia;
			}
			
			if (mes.length() == 1) {
				mes = "0"+mes;
			}
			
			String fecha = year+"-"+mes+"-"+dia;
			String hora = String.valueOf(hourCb.getSelectedItem());
			
			LocalDate inputDate = LocalDate.parse(fecha);
			String nombreDia = inputDate.getDayOfWeek().getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault());
			String nombreMes = inputDate.getMonth().getDisplayName(TextStyle.FULL_STANDALONE, Locale.getDefault());
			
			String mensaje = "Confirme la fecha y hora de la cita: "+nombreDia+" "+dia+" de "+nombreMes+" del "+year+" a las "+hora+".";
			
			int res = JOptionPane.showOptionDialog(this,mensaje,"Confirmar cita",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
						new Object[] { "Sí", "No" }, JOptionPane.YES_OPTION);
			if (res == JOptionPane.YES_OPTION) {
				int affectedRows = 0;
				
				try {
					affectedRows = controller.insertNewCita(dni_doctor, dni_paciente, fecha, hora);
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				
				if (affectedRows == 0) {
					JOptionPane.showMessageDialog(this, "No ha sido posible añadir la cita.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					JOptionPane.showMessageDialog(this, "La cita ha sido añadida con éxito.", "Éxito",
							JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
	}
	
	// rellena el combobox de año con los próximos cien años, incluyendo el actual
	private void setYearCb() {
		yearCb.addItem("");
		
		LocalDate currentDate = LocalDate.now();
		
		int year = currentDate.getYear();
		for (int i = 0; i < 100; i++) {
			yearCb.addItem(year);
			year++;
		}
	}
	
	// obtiene una lista con todas las especialidades disponibles y la utiliza para 
	// rellenar el combobox de especialidades
	private void setSpecialtyCb() {
		Especialidad_Controller controller = new Especialidad_Controller(connection);
		
		try {
			specialtyList = controller.getEspecialidades();
			
			specialtyCb.addItem("");
			for (int i = 0; i < specialtyList.size(); i++) {
				specialtyCb.addItem(specialtyList.get(i).getNombre());
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// permite rellenar el combobox de doctores en función de la especialidad escogida
	private void setDoctorCb() {
		Doctor_Controller controller = new Doctor_Controller(connection);
		String specialtyId = String.valueOf(specialtyList.get(specialtyCb.getSelectedIndex()-1).getId());
		
		try {
			doctorList = controller.getDoctoresBySpecialty(specialtyId,true);
			
			doctorCb.addItem("");
			for (int i = 0; i < doctorList.size(); i++) {
				doctorCb.addItem(doctorList.get(i).getNombre()+" "+doctorList.get(i).getApellidos());
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// permite rellenar el combobox de horas en función de la fecha y doctor escogidos
	private void setHourCb() {
		Cita_Controller controller = new Cita_Controller(connection);
		
		// obtenemos el dni del doctor seleccionado y la fecha de la cita
		String dni_doctor = doctorList.get(doctorCb.getSelectedIndex()-1).getDni();
		
		String mesSeleccionado = "";
		if (String.valueOf(monthCb.getSelectedItem()).length() == 1) {
			mesSeleccionado = "0"+monthCb.getSelectedItem();
		} else {
			mesSeleccionado = String.valueOf(monthCb.getSelectedItem());
		}
		
		String diaSeleccionado = "";
		if (String.valueOf(dayCb.getSelectedItem()).length() == 1) {
			diaSeleccionado = "0"+dayCb.getSelectedItem();
		} else {
			diaSeleccionado = String.valueOf(dayCb.getSelectedItem());
		}
		
		String fecha = String.valueOf(yearCb.getSelectedItem())
						+"-"+mesSeleccionado
						+"-"+diaSeleccionado;
		
		LocalDate fechaSeleccionada = LocalDate.parse(fecha);
		LocalDate fechaActual = LocalDate.now();
		
		try {
			String[] workHours = {"9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00"};
			ArrayList<String> availableHours = new ArrayList<String>();
			Collections.addAll(availableHours, workHours);
			ArrayList<Cita> citasList = controller.getDocCitasByDate(fecha, dni_doctor);
			
			LocalTime horaActual = LocalTime.now();
			
			// recorremos la lista con todas las horas disponibles y eliminamos las que ya tiene asignadas
			// el doctor
			Iterator<String> iterator = availableHours.iterator();
			while (iterator.hasNext()) {
			    Time iteratorHour = Time.valueOf(iterator.next()+":00");
			    
			    boolean found = false;
			    
			    if (fechaSeleccionada.compareTo(fechaActual) == 0 &&
			    		iteratorHour.toLocalTime().compareTo(horaActual) < 0) {
			    	iterator.remove();
			        found = true;
			    }
			    
			    for (int i = 0; i < citasList.size() && !found; i++) {
			    	if (citasList.get(i).getHora().compareTo(iteratorHour) == 0) {
				        iterator.remove();
				        found = true;
				    }
				}
			}
			
			hourCb.addItem("");
			for (int i = 0; i < availableHours.size(); i++) {
				hourCb.addItem(availableHours.get(i));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// permite rellenar los campos con los datos del paciente
	private void fillFields() {
		dniField.setText(rowData[0]);
		dniField.setEnabled(false);
		nameField.setText(rowData[1]);
		nameField.setEnabled(false);
		surnameField.setText(rowData[2]);
		surnameField.setEnabled(false);
	}
}
