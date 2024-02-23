package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import controller.Paciente_Controller;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class Admin_InsPac extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MySQLConnection connection;
	private String userDni;
	private JTextField dniField;
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField phoneField;
	private JTextField mailField;
	private JComboBox dayCb;
	private JComboBox monthCb;
	private JComboBox yearCb;

	/**
	 * Create the frame.
	 */
	public Admin_InsPac(MySQLConnection connection, String userDni) {
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
		
		LeftMenuButton userManageBtn = new LeftMenuButton("Gestión de usuarios",true);
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
		
		LeftMenuButton medicalManageBtn = new LeftMenuButton("Gestión médica",false);
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
		
		JLabel lblTabName = new JLabel("Gestión de usuarios");
		lblTabName.setForeground(new Color(0, 75, 72));
		lblTabName.setFont(new Font("Segoe UI Black", Font.PLAIN, 44));
		lblTabName.setBounds(24, 11, 776, 60);
		centeredPanel.add(lblTabName);
		
		JLabel lblHint = new JLabel("Añadir nuevo paciente");
		lblHint.setForeground(new Color(0, 75, 72));
		lblHint.setFont(new Font("Segoe UI", Font.PLAIN, 28));
		lblHint.setBounds(24, 62, 776, 60);
		centeredPanel.add(lblHint);
		
		JLabel dniLbl = new JLabel("DNI *");
		dniLbl.setForeground(new Color(0, 75, 72));
		dniLbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
		dniLbl.setBounds(133, 170, 274, 32);
		centeredPanel.add(dniLbl);
		
		dniField = new JTextField();
		dniField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		dniField.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(0, 1, 1, 0)));
		dniField.setBounds(133, 205, 274, 44);
		centeredPanel.add(dniField);
		
		JLabel nameLbl = new JLabel("Nombre *");
		nameLbl.setForeground(new Color(0, 75, 72));
		nameLbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
		nameLbl.setBounds(437, 170, 274, 32);
		centeredPanel.add(nameLbl);
		
		nameField = new JTextField();
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		nameField.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(0, 1, 1, 0)));
		nameField.setBounds(437, 205, 274, 44);
		centeredPanel.add(nameField);
		
		JLabel surnameLbl = new JLabel("Apellidos *");
		surnameLbl.setForeground(new Color(0, 75, 72));
		surnameLbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
		surnameLbl.setBounds(745, 170, 274, 32);
		centeredPanel.add(surnameLbl);
		
		surnameField = new JTextField();
		surnameField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		surnameField.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(0, 1, 1, 0)));
		surnameField.setBounds(745, 205, 274, 44);
		centeredPanel.add(surnameField);
		
		JLabel phoneLbl = new JLabel("Teléfono *");
		phoneLbl.setForeground(new Color(0, 75, 72));
		phoneLbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
		phoneLbl.setBounds(640, 259, 274, 32);
		centeredPanel.add(phoneLbl);
		
		phoneField = new JTextField();
		phoneField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		phoneField.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(0, 1, 1, 0)));
		phoneField.setBounds(640, 294, 274, 44);
		centeredPanel.add(phoneField);
		
		JLabel mailLbl = new JLabel("Correo electrónico");
		mailLbl.setForeground(new Color(0, 75, 72));
		mailLbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
		mailLbl.setBounds(216, 259, 274, 32);
		centeredPanel.add(mailLbl);
		
		mailField = new JTextField();
		mailField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		mailField.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(0, 1, 1, 0)));
		mailField.setBounds(216, 294, 366, 44);
		centeredPanel.add(mailField);
		
		JLabel bornLbl = new JLabel("Fecha de nacimiento (AAAA-MM-DD)");
		bornLbl.setForeground(new Color(0, 75, 72));
		bornLbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
		bornLbl.setBounds(347, 348, 567, 32);
		centeredPanel.add(bornLbl);
		
		yearCb = new JComboBox();
		yearCb.setFont(new Font("Tahoma", Font.PLAIN, 20));
		yearCb.setBounds(453, 383, 78, 44);
		centeredPanel.add(yearCb);
		Menu_Controller.setYearCb(yearCb); // se rellena el combo de año
		
		monthCb = new JComboBox();
		monthCb.setEnabled(false);
		monthCb.setFont(new Font("Tahoma", Font.PLAIN, 20));
		monthCb.setBounds(541, 383, 50, 44);
		centeredPanel.add(monthCb);
		
		dayCb = new JComboBox();
		dayCb.setEnabled(false);
		dayCb.setFont(new Font("Tahoma", Font.PLAIN, 20));
		dayCb.setBounds(601, 383, 50, 44);
		centeredPanel.add(dayCb);
		
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
		        	dayCb.setEnabled(true);
		        }
		    }
		});
		
		MainButton insertBtn = new MainButton("Añadir paciente");
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertPatient();
			}
		});
		insertBtn.setBounds(401, 447, 310, 44);
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
	}
	
	// comprueba que los campos son válidos antes de la inserción e informa al usuario en caso contrario
	private boolean checkFields() {
		if (dniField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "El campo DNI está vacío.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (!Menu_Controller.checkDni(dniField.getText())) {
			JOptionPane.showMessageDialog(this, "El DNI introducido no es válido.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (nameField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "El campo nombre está vacío.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (surnameField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "El campo apellidos está vacío.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (phoneField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "El campo teléfono está vacío.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (!phoneField.getText().isEmpty() && !Menu_Controller.checkNumber(phoneField.getText())) {
			JOptionPane.showMessageDialog(this, "El campo teléfono solo puede contener números.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			return true;
		}
	}
	
	// efectúa la inserción del paciente en función de los campos rellenos
	private void insertPatient() {
		if (checkFields()) {
			Paciente_Controller controller = new Paciente_Controller(connection);
			
			String dni = dniField.getText();
			String nombre = nameField.getText();
			String apellidos = surnameField.getText();
			int telefono = Integer.parseInt(phoneField.getText());
			
			int affectedRows = 0;
			
			try {
				if (mailField.getText().isEmpty() && 
						((dayCb.isEnabled() && dayCb.getSelectedItem().equals("")) ||
								!dayCb.isEnabled())) {
					affectedRows = controller.insertNewPaciente(dni, nombre, apellidos, telefono);
				} else if ((dayCb.isEnabled() && dayCb.getSelectedItem().equals("")) ||
								!dayCb.isEnabled()) {
					String correo = mailField.getText();
					affectedRows = controller.insertNewPaciente(dni, nombre, apellidos, telefono, correo);
				} else if (mailField.getText().isEmpty()) {
					String fecha_nacimiento = String.valueOf(yearCb.getSelectedItem())
							+"-"+String.valueOf(monthCb.getSelectedItem())
							+"-"+String.valueOf(dayCb.getSelectedItem());
					affectedRows = controller.insertNewPaciente(dni, nombre, apellidos, fecha_nacimiento, telefono);
				} else {
					String correo = mailField.getText();
					String fecha_nacimiento = String.valueOf(yearCb.getSelectedItem())
							+"-"+String.valueOf(monthCb.getSelectedItem())
							+"-"+String.valueOf(dayCb.getSelectedItem());
					affectedRows = controller.insertNewPaciente(dni, nombre, apellidos, telefono, fecha_nacimiento, correo);
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			
			if (affectedRows == 0) {
				JOptionPane.showMessageDialog(this, "No se ha podido añadir al paciente. Es posible que el DNI ya esté en uso.", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "El paciente ha sido incorporado con éxito.", "Éxito",
						JOptionPane.INFORMATION_MESSAGE);
				emptyFields();
			}
		}
	}
	
	// permite vaciar los campos tras la inserción
	private void emptyFields() {
		dniField.setText("");
		nameField.setText("");
		surnameField.setText("");
		phoneField.setText("");
		mailField.setText("");
		yearCb.setSelectedIndex(0);
	}
}
