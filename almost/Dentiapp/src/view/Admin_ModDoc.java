package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import controller.Doctor_Controller;
import controller.Menu_Controller;
import controller.MySQLConnection;
import controller.Usuario_Controller;
import model.Usuario;

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
import javax.swing.JCheckBox;

public class Admin_ModDoc extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MySQLConnection connection;
	private String userDni;
	private String[] rowData;
	private JTextField dniField;
	private JPasswordField passField;
	private JPasswordField repeatPassField;
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField phoneField;
	private JTextField mailField;
	private JComboBox dayCb;
	private JComboBox monthCb;
	private JComboBox yearCb;
	private JCheckBox leaveChckbx;
	private JCheckBox rejoinChckbx;

	/**
	 * Create the frame.
	 */
	public Admin_ModDoc(MySQLConnection connection, String userDni, String[] rowData) {
		// asignamos a las variables connection, userDni y rowData los argumentos pasados al crear la ventana
		this.connection = connection;
		this.userDni = userDni;
		this.rowData = rowData;
					
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
		
		JLabel lblHint = new JLabel("Modificar doctor");
		lblHint.setForeground(new Color(0, 75, 72));
		lblHint.setFont(new Font("Segoe UI", Font.PLAIN, 28));
		lblHint.setBounds(24, 62, 776, 60);
		centeredPanel.add(lblHint);
		
		JLabel dniLbl = new JLabel("DNI");
		dniLbl.setForeground(new Color(0, 75, 72));
		dniLbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
		dniLbl.setBounds(133, 170, 274, 32);
		centeredPanel.add(dniLbl);
		
		dniField = new JTextField();
		dniField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		dniField.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(0, 1, 1, 0)));
		dniField.setBounds(133, 205, 274, 44);
		centeredPanel.add(dniField);
		
		JLabel passLbl = new JLabel("Contraseña");
		passLbl.setForeground(new Color(0, 75, 72));
		passLbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
		passLbl.setBounds(437, 170, 274, 32);
		centeredPanel.add(passLbl);
		
		passField = new JPasswordField();
		passField.setTransferHandler(null); // deshabilita copiar-pegar en el campo
		passField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		passField.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(0, 1, 1, 0)));
		passField.setBounds(437, 205, 274, 44);
		centeredPanel.add(passField);
		
		JLabel repeatPassLbl = new JLabel("Repita la contraseña");
		repeatPassLbl.setForeground(new Color(0, 75, 72));
		repeatPassLbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
		repeatPassLbl.setBounds(745, 170, 274, 32);
		centeredPanel.add(repeatPassLbl);
		
		repeatPassField = new JPasswordField();
		repeatPassField.setTransferHandler(null); // deshabilita copiar-pegar en el campo
		repeatPassField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		repeatPassField.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(0, 1, 1, 0)));
		repeatPassField.setBounds(745, 205, 274, 44);
		centeredPanel.add(repeatPassField);
		
		JLabel nameLbl = new JLabel("Nombre");
		nameLbl.setForeground(new Color(0, 75, 72));
		nameLbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
		nameLbl.setBounds(133, 258, 274, 32);
		centeredPanel.add(nameLbl);
		
		nameField = new JTextField();
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		nameField.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(0, 1, 1, 0)));
		nameField.setBounds(133, 293, 274, 44);
		centeredPanel.add(nameField);
		
		JLabel surnameLbl = new JLabel("Apellidos");
		surnameLbl.setForeground(new Color(0, 75, 72));
		surnameLbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
		surnameLbl.setBounds(437, 259, 274, 32);
		centeredPanel.add(surnameLbl);
		
		surnameField = new JTextField();
		surnameField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		surnameField.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(0, 1, 1, 0)));
		surnameField.setBounds(437, 294, 274, 44);
		centeredPanel.add(surnameField);
		
		JLabel phoneLbl = new JLabel("Teléfono");
		phoneLbl.setForeground(new Color(0, 75, 72));
		phoneLbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
		phoneLbl.setBounds(745, 258, 274, 32);
		centeredPanel.add(phoneLbl);
		
		phoneField = new JTextField();
		phoneField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		phoneField.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(0, 1, 1, 0)));
		phoneField.setBounds(745, 293, 274, 44);
		centeredPanel.add(phoneField);
		
		JLabel mailLbl = new JLabel("Correo electrónico");
		mailLbl.setForeground(new Color(0, 75, 72));
		mailLbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
		mailLbl.setBounds(156, 347, 274, 32);
		centeredPanel.add(mailLbl);
		
		mailField = new JTextField();
		mailField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		mailField.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(0, 1, 1, 0)));
		mailField.setBounds(156, 382, 366, 44);
		centeredPanel.add(mailField);
		
		JLabel bornLbl = new JLabel("Fecha de nacimiento (AAAA-MM-DD)");
		bornLbl.setForeground(new Color(0, 75, 72));
		bornLbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
		bornLbl.setBounds(581, 347, 540, 32);
		centeredPanel.add(bornLbl);
		
		yearCb = new JComboBox();
		yearCb.setFont(new Font("Tahoma", Font.PLAIN, 20));
		yearCb.setBounds(687, 382, 78, 44);
		centeredPanel.add(yearCb);
		Menu_Controller.setYearCb(yearCb); // se rellena el combo de año
		
		monthCb = new JComboBox();
		monthCb.setEnabled(false);
		monthCb.setFont(new Font("Tahoma", Font.PLAIN, 20));
		monthCb.setBounds(775, 382, 50, 44);
		centeredPanel.add(monthCb);
		
		dayCb = new JComboBox();
		dayCb.setEnabled(false);
		dayCb.setFont(new Font("Tahoma", Font.PLAIN, 20));
		dayCb.setBounds(835, 382, 50, 44);
		centeredPanel.add(dayCb);
		
		// al realizar una selección en el combobox de año
		yearCb.addActionListener (new ActionListener () {
		    public void actionPerformed(ActionEvent e) { // si el combo de mes contiene items, lo vacía
		    	if (monthCb.getItemCount() != 0) {
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
		
		leaveChckbx = new JCheckBox("Dar de baja");
		leaveChckbx.setOpaque(false);
		leaveChckbx.setFocusPainted(false);
		leaveChckbx.setForeground(new Color(0, 75, 72));
		leaveChckbx.setFont(new Font("Segoe UI", Font.BOLD, 24));
		leaveChckbx.setBounds(396, 432, 160, 44);
		centeredPanel.add(leaveChckbx);
		
		rejoinChckbx = new JCheckBox("Dar de alta");
		rejoinChckbx.setOpaque(false);
		rejoinChckbx.setFocusPainted(false);
		rejoinChckbx.setForeground(new Color(0, 75, 72));
		rejoinChckbx.setFont(new Font("Segoe UI", Font.BOLD, 24));
		rejoinChckbx.setBounds(562, 432, 160, 44);
		centeredPanel.add(rejoinChckbx);
		
		// rellenamos los campos con los datos del doctor
		fillFields();
		
		MainButton modifyBtn = new MainButton("Modificar doctor");
		modifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyDoctor();
			}
		});
		modifyBtn.setBounds(401, 482, 310, 44);
		centeredPanel.add(modifyBtn);
		
		MainButton backBtn = new MainButton("Volver");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu_Controller.openConsDoc(connection, userDni);
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
	
	// comprueba que ciertos campos son válidos antes de la modificación e informa al usuario en caso contrario
	private boolean checkFields() {
		if (!String.valueOf(passField.getPassword()).isEmpty() && 
				(String.valueOf(passField.getPassword()).length()<8 || String.valueOf(passField.getPassword()).length()>16)) {
			JOptionPane.showMessageDialog(this, "La contraseña debe tener una longitud mayor a 8 y menor a 16.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (!String.valueOf(passField.getPassword()).isEmpty() && String.valueOf(repeatPassField.getPassword()).isEmpty()) {
			JOptionPane.showMessageDialog(this, "El campo repita la contraseña está vacío.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (!String.valueOf(passField.getPassword()).equals(String.valueOf(repeatPassField.getPassword()))) {
			JOptionPane.showMessageDialog(this, "La nueva contraseña no coincide.", "Error",
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
	
	// ejecuta las modificaciones solicitadas
	private void modifyDoctor() {
		if (checkFields()) {
			Usuario_Controller user_controller = new Usuario_Controller(connection);
			Doctor_Controller doc_controller = new Doctor_Controller(connection);
			
			String dni = dniField.getText();
						
			try {
				if (!String.valueOf(passField.getPassword()).isEmpty()) {
					String newPass = String.valueOf(passField.getPassword());
					user_controller.modifyPass(dni,newPass);
				}
				
				if (!nameField.getText().isEmpty()) {
					String nombre = nameField.getText();
					doc_controller.modifyName(dni,nombre);
				}
				
				if (!surnameField.getText().isEmpty()) {
					String apellidos = surnameField.getText();
					doc_controller.modifySurname(dni,apellidos);
				}
				
				if (!phoneField.getText().isEmpty()) {
					int telefono = Integer.parseInt(phoneField.getText());
					doc_controller.modifyPhone(dni,telefono);
				}
				
				if (dayCb.isEnabled() && !dayCb.getSelectedItem().equals("")) {
					String fecha_nacimiento = String.valueOf(yearCb.getSelectedItem())
							+"-"+String.valueOf(monthCb.getSelectedItem())
							+"-"+String.valueOf(dayCb.getSelectedItem());
					doc_controller.modifyDate(dni,fecha_nacimiento);
				}
				
				if (!mailField.getText().isEmpty()) {
					String correo = mailField.getText();
					doc_controller.modifyMail(dni,correo);
				}
				
				// dar de baja a un doctor también desactiva su cuenta de usuario
				if (leaveChckbx.isSelected()) {
					int modifiedRows = user_controller.modifyDeactivated(dni,true);

					if (modifiedRows != 0) {
						doc_controller.modifyLeave(dni,true);
					}
				}

				// dar de alta a un doctor también activa su cuenta de usuario
				if (rejoinChckbx.isSelected()) {
					int modifiedRows = user_controller.modifyDeactivated(dni,false);

					if (modifiedRows != 0) {
						doc_controller.modifyLeave(dni,false);
					}
				}
				
				JOptionPane.showMessageDialog(this, "El doctor ha sido modificado con éxito.", "Éxito",
						JOptionPane.INFORMATION_MESSAGE);
				Menu_Controller.openConsDoc(connection, userDni);
				dispose();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, "No ha sido posible modificar el doctor.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	// permite rellenar los campos con los datos del doctor
	private void fillFields() {
		dniField.setText(rowData[0]);
		dniField.setEnabled(false);
		nameField.setText(rowData[1]);
		surnameField.setText(rowData[2]);
		phoneField.setText(rowData[3]);
		
		if (!rowData[4].isEmpty()) {
			String[] date = rowData[4].split("-");
			yearCb.setSelectedItem(Integer.parseInt(date[0]));
			monthCb.setSelectedItem(Integer.parseInt(date[1]));
			dayCb.setSelectedItem(Integer.parseInt(date[2]));
		}
		
		mailField.setText(rowData[5]);
		
		if (rowData[6].equalsIgnoreCase("SI")) {
			leaveChckbx.setEnabled(false);
		} else {
			rejoinChckbx.setEnabled(false);
		}
	}
}
