package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import controller.Cita_Controller;
import controller.Doctor_Controller;
import controller.Menu_Controller;
import controller.MySQLConnection;
import model.Cita;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Point;

import javax.swing.ImageIcon;

import resources.LeftMenuButton;
import resources.MainButton;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.awt.event.ActionEvent;

public class Admin_Citas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MySQLConnection connection;
	private String userDni;
	private JTable table;
	private MainButton backBtn;

	/**
	 * Create the frame.
	 */
	public Admin_Citas(MySQLConnection connection, String userDni) {
		// asignamos a las variables connection y userDni los argumentos pasados al crear la ventana
		this.connection = connection;
		this.userDni = userDni;
					
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
		
		JLabel lblHint = new JLabel("Panel de citas");
		lblHint.setForeground(new Color(0, 75, 72));
		lblHint.setFont(new Font("Segoe UI", Font.PLAIN, 28));
		lblHint.setBounds(24, 62, 776, 60);
		centeredPanel.add(lblHint);
		
		table = new JTable();
		table.setRowHeight(20);
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JPanel tablePanel = new JPanel();
		tablePanel.setLocation(56, 146);
		tablePanel.setSize(1061, 402);
		tablePanel.setLayout(new BorderLayout(0, 0));
		tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);
		centeredPanel.add(tablePanel);
		
		// array que contiene las columnas de la tabla
		String[] colNames = {"ID", "Doctor", "Paciente", "Fecha", "Hora", "Asistencia"};
		// modelo de la tabla con las columnas
		DefaultTableModel model = new DefaultTableModel(null, colNames) {

	        @Override
	        public boolean isCellEditable(int row, int column) {
	           return false; // impide que las tablas puedan ser editadas
	        }
	    };
	    table.setModel(model);
	    
	    // creamos un popup menu
	    JPopupMenu popupMenu = new JPopupMenu();
	    JMenuItem menuItemModify = new JMenuItem("Modificar cita");
	    menuItemModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] rowData = Menu_Controller.getSelectedTableRowData(table);
				modifyAppointment(rowData);
			}
		});
	    JMenuItem menuItemDelete = new JMenuItem("Eliminar cita");
	    menuItemDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] rowData = Menu_Controller.getSelectedTableRowData(table);
				deleteAppointment(rowData);
			}
		});
	    
	    popupMenu.add(menuItemModify);
	    popupMenu.add(menuItemDelete);
	    
	    // establecemos el popup menu para la tabla
	    table.setComponentPopupMenu(popupMenu);
	    
	    // añadimos a la tabla el escuchador que permitirá seleccionar filas con click derecho
	    table.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// si el botón del ratón pulsado es el derecho
				if (SwingUtilities.isRightMouseButton(e)) {
					// obtenemos la posición del puntero
					Point point = e.getPoint();
					// obtenemos la fila sobre la que está el puntero
			        int currentRow = table.rowAtPoint(point);
			        // establecemos que la fila seleccionada es sobre la que está el puntero
			        table.setRowSelectionInterval(currentRow, currentRow);
				}
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
	    	
	    });
	    
	    // mostramos todas las citas de hoy en la tabla
	    listTodayAppointments();
		
		MainButton listBtn = new MainButton("Mostrar citas de hoy");
		listBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listTodayAppointments();
			}
		});
		listBtn.setBounds(231, 558, 310, 44);
		centeredPanel.add(listBtn);
		
		MainButton findBtn = new MainButton("Buscar por fecha");
		findBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findAppointmentByDate();
			}
		});
		findBtn.setBounds(563, 558, 310, 44);
		centeredPanel.add(findBtn);
				
		backBtn = new MainButton("Volver");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu_Controller.openMedical(connection, userDni);
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
	}
	
	// constructor para mostrar las citas de un doctor en concreto
	public Admin_Citas(MySQLConnection connection, String userDni, String[] rowData, String date) {
		this(connection, userDni);
		
		// obtenemos y eliminamos el ActionListener aplicado al botón de volver por el otro constructor
		ActionListener[] listeners = backBtn.getActionListeners();
		for (ActionListener listener : listeners) {
			backBtn.removeActionListener(listener);
        }
		
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu_Controller.openConsDoc(connection, userDni);
				dispose();
			}
		});
		
		// mostramos las citas del doctor solicitado
		listDocAppointments(rowData, date);
	}
	
	// constructor para mostrar las citas de un paciente en concreto
	public Admin_Citas(MySQLConnection connection, String userDni, String date, String[] rowData) {
		this(connection, userDni);
		
		// obtenemos y eliminamos el ActionListener aplicado al botón de volver por el otro constructor
		ActionListener[] listeners = backBtn.getActionListeners();
		for (ActionListener listener : listeners) {
			backBtn.removeActionListener(listener);
        }
		
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu_Controller.openConsPac(connection, userDni);
				dispose();
			}
		});
		
		// mostramos las citas del doctor solicitado
		listPacAppointments(rowData, date);
	}
	
	// obtiene todas las citas de hoy y las añade a la tabla
	private void listTodayAppointments() {
		Cita_Controller controller = new Cita_Controller(connection);
		DefaultTableModel currentModel = (DefaultTableModel) table.getModel();
		String todayDate = LocalDate.now().toString();
		controller.findCitasByDate(currentModel, todayDate);
	}
	
	// obtiene las citas de la fecha indicada y las añade a la tabla vaciándola previamente
	private void findAppointmentByDate() {
		String input = JOptionPane.showInputDialog(
				   this,
				   "Introduzca la fecha en formato AAAA-MM-DD:",
				   "Buscar por fecha",
				   JOptionPane.INFORMATION_MESSAGE);
		
		// si el usuario introduce algo, procede a buscarla
		if (input != null && !input.isEmpty()) {
			Cita_Controller controller = new Cita_Controller(connection);
			DefaultTableModel currentModel = (DefaultTableModel) table.getModel();
			controller.findCitasByDate(currentModel, input);
			
			if (currentModel.getRowCount() == 0) {
				JOptionPane.showMessageDialog(this, "No se ha encontrado ninguna cita en esta fecha. Compruebe que está en el formato solicitado.", "Sin resultados",
						JOptionPane.WARNING_MESSAGE);
			}
		} else if (input != null){
			JOptionPane.showMessageDialog(this, "La fecha introducida no es válida.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	// obtiene todas las citas del doctor cuyos datos han sido pasados por parámetro
	// y las añade a la tabla, pudiendo también filtrar por fecha si esta es incluida
	private void listDocAppointments(String[] rowData, String date) {
		Cita_Controller controller = new Cita_Controller(connection);
		DefaultTableModel currentModel = (DefaultTableModel) table.getModel();
		
		if (date == null) {
			controller.findDocCitas(currentModel, rowData[0]);
		} else {
			controller.findDocCitasByDate(currentModel, date, rowData[0]);
		}
		
		if (currentModel.getRowCount() == 0) {
			JOptionPane.showMessageDialog(this, "No se ha encontrado ninguna cita.", "Sin resultados",
					JOptionPane.WARNING_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "Se han encontrado "+currentModel.getRowCount()+" citas.", "Consultar citas",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	// obtiene todas las citas del paciente cuyos datos han sido pasados por parámetro
	// y las añade a la tabla, pudiendo también filtrar por fecha si esta es incluida
	private void listPacAppointments(String[] rowData, String date) {
		Cita_Controller controller = new Cita_Controller(connection);
		DefaultTableModel currentModel = (DefaultTableModel) table.getModel();
		
		if (date == null) {
			controller.findPacCitas(currentModel, rowData[0]);
		} else {
			controller.findPacCitasByDate(currentModel, date, rowData[0]);
		}
		
		if (currentModel.getRowCount() == 0) {
			JOptionPane.showMessageDialog(this, "No se ha encontrado ninguna cita.", "Sin resultados",
					JOptionPane.WARNING_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "Se han encontrado "+currentModel.getRowCount()+" citas.", "Consultar citas",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	// permite modificar la fecha y hora de una cita
	private void modifyAppointment(String[] rowData) {
		Object input = JOptionPane.showInputDialog(
				   this,
				   "Introduzca la nueva fecha en formato AAAA-MM-DD:",
				   "Modificar cita",
				   JOptionPane.INFORMATION_MESSAGE,
				   null,
				   null,
				   rowData[3]);
		
		try {
			if (input != null) {
				LocalDate fechaSeleccionada = LocalDate.parse(input.toString());
				LocalDate fechaActual = LocalDate.now();
				
				if (fechaSeleccionada.compareTo(fechaActual) >= 0) {
					Cita_Controller controller = new Cita_Controller(connection);
					DefaultTableModel currentModel = (DefaultTableModel) table.getModel();
					ArrayList<Cita> citaActual = new ArrayList<Cita>();
					
					try {
						citaActual = controller.getCitaByID(rowData[0]);
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
					
					String dni_doctor = citaActual.get(0).getDni_doctor();
					String id_citaActual = String.valueOf(citaActual.get(0).getId());

					String[] workHours = {"9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30", "13:00", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00"};
					ArrayList<String> availableHours = new ArrayList<String>();
					Collections.addAll(availableHours, workHours);
					ArrayList<Cita> citasList = new ArrayList<Cita>();
					
					try {
						citasList = controller.getDocCitasByDate(fechaSeleccionada.toString(), dni_doctor);
					} catch (SQLException e) {
						System.out.println(e.getMessage());
					}
					
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
					
					if (!availableHours.isEmpty()) {
						String[] choices = new String[availableHours.size()];
						
						for (int i = 0; i < availableHours.size(); i++) {
							choices[i] = availableHours.get(i);
						}

					    String newHour = (String) JOptionPane.showInputDialog(this, "Seleccione una hora para la cita:",
					        "Modificar cita", JOptionPane.QUESTION_MESSAGE, null,
					        choices,
					        choices[0]);
					    
					    if (newHour!=null) {
					    	int affectedRows = 0;
							
							try {
								affectedRows = controller.modifyCita(id_citaActual, fechaSeleccionada.toString(), newHour);
							} catch (SQLException e) {
								System.out.println(e.getMessage());
							}
							
							if (affectedRows == 0) {
								JOptionPane.showMessageDialog(this, "No se ha podido modificar la cita.", "Error",
										JOptionPane.ERROR_MESSAGE);
							} else {
								JOptionPane.showMessageDialog(this, "La cita ha sido modificada con éxito.", "Éxito",
										JOptionPane.INFORMATION_MESSAGE);
								controller.listCita(currentModel, id_citaActual);
							}
					    }
					} else {
						JOptionPane.showMessageDialog(this, "No hay horas disponibles para esta fecha.", "Hueco no disponible",
								JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(this, "La nueva fecha no puede ser anterior a la fecha actual.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (DateTimeParseException e) {
			JOptionPane.showMessageDialog(this, "La fecha introducida no es válida.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	// permite eliminar una cita si la fecha de hoy es previa a la de la cita
	private void deleteAppointment(String[] rowData) {
		Cita_Controller controller = new Cita_Controller(connection);
		DefaultTableModel currentModel = (DefaultTableModel) table.getModel();
		ArrayList<Cita> cita = new ArrayList<Cita>();
		
		try {
			cita = controller.getCitaByID(rowData[0]);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		int idCita = cita.get(0).getId();
		Date fechaCita = cita.get(0).getFecha();
		Date fechaActual = Date.valueOf(LocalDate.now());
		
		if (fechaActual.compareTo(fechaCita) < 0) {
			int affectedRows = 0;
			
			try {
				affectedRows = controller.deleteCita(String.valueOf(idCita));
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			
			if (affectedRows == 0) {
				JOptionPane.showMessageDialog(this, "No ha sido posible eliminar la cita.", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "La cita ha sido eliminada con éxito.", "Éxito",
						JOptionPane.INFORMATION_MESSAGE);
				listTodayAppointments();
			}
		} else {
			JOptionPane.showMessageDialog(this, "No ha sido posible eliminar la cita. La fecha de la cita es previa o igual a la de hoy.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
	}
		
}
