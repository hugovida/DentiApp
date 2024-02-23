package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import controller.Proveedor_Controller;
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
import java.awt.event.ActionEvent;

public class Admin_InsProv extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MySQLConnection connection;
	private String userDni;
	private JTextField nifField;
	private JTextField nameField;
	private JTextField phoneField;
	private JTextField mailField;

	/**
	 * Create the frame.
	 */
	public Admin_InsProv(MySQLConnection connection, String userDni) {
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
		
		LeftMenuButton stockManageBtn = new LeftMenuButton("Gestión de material",true);
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
		
		JLabel lblTabName = new JLabel("Gestión de material");
		lblTabName.setForeground(new Color(0, 75, 72));
		lblTabName.setFont(new Font("Segoe UI Black", Font.PLAIN, 44));
		lblTabName.setBounds(24, 11, 776, 60);
		centeredPanel.add(lblTabName);
		
		JLabel lblHint = new JLabel("Añadir nuevo proveedor");
		lblHint.setForeground(new Color(0, 75, 72));
		lblHint.setFont(new Font("Segoe UI", Font.PLAIN, 28));
		lblHint.setBounds(24, 62, 776, 60);
		centeredPanel.add(lblHint);
		
		JLabel nifLbl = new JLabel("NIF *");
		nifLbl.setForeground(new Color(0, 75, 72));
		nifLbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
		nifLbl.setBounds(216, 254, 274, 32);
		centeredPanel.add(nifLbl);
		
		nifField = new JTextField();
		nifField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		nifField.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(0, 1, 1, 0)));
		nifField.setBounds(216, 289, 274, 44);
		centeredPanel.add(nifField);
		
		JLabel nameLbl = new JLabel("Nombre *");
		nameLbl.setForeground(new Color(0, 75, 72));
		nameLbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
		nameLbl.setBounds(548, 254, 366, 32);
		centeredPanel.add(nameLbl);
		
		nameField = new JTextField();
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		nameField.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(0, 1, 1, 0)));
		nameField.setBounds(548, 289, 366, 44);
		centeredPanel.add(nameField);
		
		JLabel phoneLbl = new JLabel("Teléfono *");
		phoneLbl.setForeground(new Color(0, 75, 72));
		phoneLbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
		phoneLbl.setBounds(216, 343, 274, 32);
		centeredPanel.add(phoneLbl);
		
		phoneField = new JTextField();
		phoneField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		phoneField.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(0, 1, 1, 0)));
		phoneField.setBounds(216, 378, 274, 44);
		centeredPanel.add(phoneField);
		
		JLabel mailLbl = new JLabel("Correo electrónico");
		mailLbl.setForeground(new Color(0, 75, 72));
		mailLbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
		mailLbl.setBounds(548, 343, 274, 32);
		centeredPanel.add(mailLbl);
		
		mailField = new JTextField();
		mailField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		mailField.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(0, 1, 1, 0)));
		mailField.setBounds(548, 378, 366, 44);
		centeredPanel.add(mailField);
		
		MainButton insertBtn = new MainButton("Añadir proveedor");
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertProvider();
			}
		});
		insertBtn.setBounds(401, 447, 310, 44);
		centeredPanel.add(insertBtn);
		
		MainButton backBtn = new MainButton("Volver");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu_Controller.openProviders(connection, userDni);
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
		if (nifField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "El campo NIF está vacío.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (!Menu_Controller.checkDni(nifField.getText())) {
			JOptionPane.showMessageDialog(this, "El NIF introducido no es válido.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (nameField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "El campo nombre está vacío.", "Error",
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
	
	// efectúa la inserción del proveedor en función de los campos rellenos
	private void insertProvider() {
		if (checkFields()) {
			Proveedor_Controller controller = new Proveedor_Controller(connection);
			
			String nif = nifField.getText();
			String nombre = nameField.getText();
			int telefono = Integer.parseInt(phoneField.getText());
			
			int affectedRows = 0;
			
			try {
				if (mailField.getText().isEmpty()) {
					affectedRows = controller.insertNewProveedor(nif, nombre, telefono);
				} else {
					String correo = mailField.getText();
					affectedRows = controller.insertNewProveedor(nif, nombre, telefono, correo);
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			
			if (affectedRows == 0) {
				JOptionPane.showMessageDialog(this, "No se ha podido añadir al proveedor. Es posible que el NIF ya esté en uso.", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "El proveedor ha sido incorporado con éxito.", "Éxito",
						JOptionPane.INFORMATION_MESSAGE);
				emptyFields();
			}
		}
	}
	
	// permite vaciar los campos tras la inserción
	private void emptyFields() {
		nifField.setText("");
		nameField.setText("");
		phoneField.setText("");
		mailField.setText("");
	}
}
