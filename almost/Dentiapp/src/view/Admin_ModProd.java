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
import controller.Producto_Controller;
import controller.Proveedor_Controller;
import model.Proveedor;
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

public class Admin_ModProd extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MySQLConnection connection;
	private String userDni;
	private String[] rowData;
	private JTextField idField;
	private JTextField nameField;
	private JComboBox provCb;
	private JTextField stockField;
	private JCheckBox leaveChckbx;
	private JCheckBox rejoinChckbx;
	ArrayList<Proveedor> proveedorList;

	/**
	 * Create the frame.
	 */
	public Admin_ModProd(MySQLConnection connection, String userDni, String[] rowData) {
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
		
		JLabel lblHint = new JLabel("Modificar producto");
		lblHint.setForeground(new Color(0, 75, 72));
		lblHint.setFont(new Font("Segoe UI", Font.PLAIN, 28));
		lblHint.setBounds(24, 62, 776, 60);
		centeredPanel.add(lblHint);
		
		JLabel idLbl = new JLabel("ID");
		idLbl.setForeground(new Color(0, 75, 72));
		idLbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
		idLbl.setBounds(250, 200, 274, 32);
		centeredPanel.add(idLbl);
		
		idField = new JTextField();
		idField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		idField.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(0, 1, 1, 0)));
		idField.setBounds(250, 235, 274, 44);
		centeredPanel.add(idField);
		
		JLabel nameLbl = new JLabel("Nombre");
		nameLbl.setForeground(new Color(0, 75, 72));
		nameLbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
		nameLbl.setBounds(562, 200, 330, 32);
		centeredPanel.add(nameLbl);
		
		nameField = new JTextField();
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		nameField.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(0, 1, 1, 0)));
		nameField.setBounds(562, 235, 330, 44);
		centeredPanel.add(nameField);
		
		JLabel provLbl = new JLabel("Proveedor");
		provLbl.setForeground(new Color(0, 75, 72));
		provLbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
		provLbl.setBounds(340, 288, 426, 32);
		centeredPanel.add(provLbl);
		
		provCb = new JComboBox();
		provCb.setFont(new Font("Tahoma", Font.PLAIN, 20));
		provCb.setBounds(340, 323, 440, 44);
		centeredPanel.add(provCb);
		
		// rellenamos el combobox de proveedores
		setProvCb();
		
		JLabel stockLbl = new JLabel("Stock");
		stockLbl.setForeground(new Color(0, 75, 72));
		stockLbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
		stockLbl.setBounds(455, 377, 200, 32);
		centeredPanel.add(stockLbl);
		
		stockField = new JTextField();
		stockField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		stockField.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(0, 1, 1, 0)));
		stockField.setBounds(455, 412, 200, 44);
		centeredPanel.add(stockField);
		
		leaveChckbx = new JCheckBox("Dar de baja");
		leaveChckbx.setOpaque(false);
		leaveChckbx.setFocusPainted(false);
		leaveChckbx.setForeground(new Color(0, 75, 72));
		leaveChckbx.setFont(new Font("Segoe UI", Font.BOLD, 24));
		leaveChckbx.setBounds(396, 462, 160, 44);
		centeredPanel.add(leaveChckbx);
		
		rejoinChckbx = new JCheckBox("Dar de alta");
		rejoinChckbx.setOpaque(false);
		rejoinChckbx.setFocusPainted(false);
		rejoinChckbx.setForeground(new Color(0, 75, 72));
		rejoinChckbx.setFont(new Font("Segoe UI", Font.BOLD, 24));
		rejoinChckbx.setBounds(562, 462, 160, 44);
		centeredPanel.add(rejoinChckbx);
		
		// rellenamos los campos con los datos del producto
		fillFields();
		
		MainButton modifyBtn = new MainButton("Modificar producto");
		modifyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modifyProduct();
			}
		});
		modifyBtn.setBounds(401, 512, 310, 44);
		centeredPanel.add(modifyBtn);
		
		MainButton backBtn = new MainButton("Volver");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu_Controller.openProducts(connection, userDni);
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
	
	// permite rellenar el combobox de proveedores
	private void setProvCb() {
		Proveedor_Controller controller = new Proveedor_Controller(connection);
		try {
			proveedorList = controller.getProveedores(false);
			
			for (int i = 0; i < proveedorList.size(); i++) {
				provCb.addItem(proveedorList.get(i).getNombre());
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// comprueba que ciertos campos son válidos antes de la modificación e informa al usuario en caso contrario
	private boolean checkFields() {
		if (!stockField.getText().isEmpty() && !Menu_Controller.checkNumber(stockField.getText())) {
			JOptionPane.showMessageDialog(this, "El campo stock solo puede contener números.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			return true;
		}
	}
	
	// ejecuta las modificaciones solicitadas
	private void modifyProduct() {
		if (checkFields()) {
			Producto_Controller controller = new Producto_Controller(connection);
			
			String id = idField.getText();
						
			try {
				
				if (!nameField.getText().isEmpty()) {
					String nombre = nameField.getText();
					controller.modifyName(id,nombre);
				}
				
				if (!provCb.getSelectedItem().equals(rowData[2])) {
					controller.modifyProvider(id, proveedorList.get(provCb.getSelectedIndex()).getNif());
				}
				
				if (!stockField.getText().isEmpty()) {
					int stock = Integer.parseInt(stockField.getText());
					controller.modifyStock(id,stock);
				}
				
				if (leaveChckbx.isSelected()) {
					controller.modifyLeave(id,true);
				}

				if (rejoinChckbx.isSelected()) {
					controller.modifyLeave(id,false);
				}
				
				JOptionPane.showMessageDialog(this, "El producto ha sido modificado con éxito.", "Éxito",
						JOptionPane.INFORMATION_MESSAGE);
				Menu_Controller.openProducts(connection, userDni);
				dispose();
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, "No ha sido posible modificar el producto.", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	
	// permite rellenar los campos con los datos del producto
	private void fillFields() {
		idField.setText(rowData[0]);
		idField.setEnabled(false);
		nameField.setText(rowData[1]);
		provCb.setSelectedItem(rowData[2]);
		stockField.setText(rowData[3]);
		
		if (rowData[4].equalsIgnoreCase("SI")) {
			leaveChckbx.setEnabled(false);
		} else {
			rejoinChckbx.setEnabled(false);
		}
	}
}
