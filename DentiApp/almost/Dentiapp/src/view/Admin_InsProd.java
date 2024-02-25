package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;

import controller.Producto_Controller;
import controller.Proveedor_Controller;
import model.Proveedor;
import controller.Menu_Controller;
import controller.MySQLConnection;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JComboBox;

import resources.LeftMenuButton;
import resources.MainButton;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class Admin_InsProd extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MySQLConnection connection;
	private String userDni;
	private JTextField nameField;
	private JComboBox provCb;
	private JTextField stockField;
	ArrayList<Proveedor> proveedorList;

	/**
	 * Create the frame.
	 */
	public Admin_InsProd(MySQLConnection connection, String userDni) {
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
		
		JLabel lblHint = new JLabel("Añadir nuevo producto");
		lblHint.setForeground(new Color(0, 75, 72));
		lblHint.setFont(new Font("Segoe UI", Font.PLAIN, 28));
		lblHint.setBounds(24, 62, 776, 60);
		centeredPanel.add(lblHint);
		
		JLabel nameLbl = new JLabel("Nombre *");
		nameLbl.setForeground(new Color(0, 75, 72));
		nameLbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
		nameLbl.setBounds(280, 240, 330, 32);
		centeredPanel.add(nameLbl);
		
		nameField = new JTextField();
		nameField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		nameField.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(0, 1, 1, 0)));
		nameField.setBounds(280, 275, 330, 44);
		centeredPanel.add(nameField);
		
		JLabel provLbl = new JLabel("Proveedor *");
		provLbl.setForeground(new Color(0, 75, 72));
		provLbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
		provLbl.setBounds(340, 329, 426, 32);
		centeredPanel.add(provLbl);
		
		provCb = new JComboBox();
		provCb.setFont(new Font("Tahoma", Font.PLAIN, 20));
		provCb.setBounds(340, 364, 440, 44);
		centeredPanel.add(provCb);
		
		// rellenamos el combobox de proveedores
		setProvCb();
		
		JLabel stockLbl = new JLabel("Stock");
		stockLbl.setForeground(new Color(0, 75, 72));
		stockLbl.setFont(new Font("Segoe UI", Font.BOLD, 24));
		stockLbl.setBounds(641, 240, 200, 32);
		centeredPanel.add(stockLbl);
		
		stockField = new JTextField();
		stockField.setFont(new Font("Tahoma", Font.PLAIN, 24));
		stockField.setBorder(new CompoundBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)), new EmptyBorder(0, 1, 1, 0)));
		stockField.setBounds(641, 275, 200, 44);
		centeredPanel.add(stockField);
		
		MainButton insertBtn = new MainButton("Añadir producto");
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertProduct();
			}
		});
		insertBtn.setBounds(401, 427, 310, 44);
		centeredPanel.add(insertBtn);
		
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
			proveedorList = controller.getProveedores(true);
			
			provCb.addItem("");
			for (int i = 0; i < proveedorList.size(); i++) {
				provCb.addItem(proveedorList.get(i).getNombre());
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// comprueba que los campos son válidos antes de la inserción e informa al usuario en caso contrario
	private boolean checkFields() {
		if (nameField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "El campo nombre está vacío.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (provCb.getSelectedItem().equals("")) {
			JOptionPane.showMessageDialog(this, "Es necesario seleccionar un proveedor.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (!stockField.getText().isEmpty() && !Menu_Controller.checkNumber(stockField.getText())) {
			JOptionPane.showMessageDialog(this, "El campo stock solo puede contener números.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			return true;
		}
	}
	
	// efectúa la inserción del producto en función de los campos rellenos
	private void insertProduct() {
		if (checkFields()) {
			Producto_Controller controller = new Producto_Controller(connection);
			
			String nombre = nameField.getText();
			String nif_proveedor = proveedorList.get(provCb.getSelectedIndex()-1).getNif();
			
			int affectedRows = 0;
			
			try {
				if (stockField.getText().isEmpty()) {
					affectedRows = controller.insertNewProducto(nombre, nif_proveedor);
				} else {
					int stock = Integer.parseInt(stockField.getText());
					affectedRows = controller.insertNewProducto(nombre, nif_proveedor, stock);
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
			
			if (affectedRows == 0) {
				JOptionPane.showMessageDialog(this, "No se ha podido añadir el producto. Es posible que el nombre ya esté en uso.", "Error",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "El producto ha sido incorporado con éxito.", "Éxito",
						JOptionPane.INFORMATION_MESSAGE);
				emptyFields();
			}
		}
	}
	
	// permite vaciar los campos tras la inserción
	private void emptyFields() {
		nameField.setText("");
		provCb.setSelectedIndex(0);
		stockField.setText("");
	}
}
