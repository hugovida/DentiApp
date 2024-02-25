package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import controller.Producto_Controller;
import controller.Menu_Controller;
import controller.MySQLConnection;

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
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;

public class Admin_ConsProd extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MySQLConnection connection;
	private String userDni;
	private JTable table;
	private MainButton backBtn;

	/**
	 * Create the frame.
	 */
	public Admin_ConsProd(MySQLConnection connection, String userDni) {
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
		
		JLabel lblHint = new JLabel("Panel de productos");
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
		String[] colNames = {"ID", "Nombre", "Proveedor","Stock", "Baja"};
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
	    JMenuItem menuItemModify = new JMenuItem("Modificar producto");
	    menuItemModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] rowData = Menu_Controller.getSelectedTableRowData(table);
				Menu_Controller.openModProd(connection, userDni, rowData);
				dispose();
			}
		});
	    popupMenu.add(menuItemModify);
	    
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
	    
	    // mostramos todos los proveedores en la tabla
	 	listProducts();
		
		MainButton listBtn = new MainButton("Mostrar productos");
		listBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listProducts();
			}
		});
		listBtn.setBounds(231, 558, 310, 44);
		centeredPanel.add(listBtn);
		
		MainButton findBtn = new MainButton("Buscar un producto");
		findBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				findProduct();
			}
		});
		findBtn.setBounds(563, 558, 310, 44);
		centeredPanel.add(findBtn);
		
		MainButton insertarBtn = new MainButton("Añadir nuevo producto");
		insertarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu_Controller.openInsProd(connection, userDni);
				dispose();
			}
		});
		insertarBtn.setBounds(390, 612, 310, 44);
		centeredPanel.add(insertarBtn);
		
		backBtn = new MainButton("Volver");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu_Controller.openMaterial(connection, userDni);
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
	
	// constructor para mostrar los productos de un proveedor en concreto
	public Admin_ConsProd(MySQLConnection connection, String userDni, String nif_proveedor) {
		this(connection, userDni);
		
		// obtenemos y eliminamos el ActionListener aplicado al botón de volver por el otro constructor
		ActionListener[] listeners = backBtn.getActionListeners();
		for (ActionListener listener : listeners) {
			backBtn.removeActionListener(listener);
        }
		
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu_Controller.openProviders(connection, userDni);
				dispose();
			}
		});
		
		// mostramos los productos del proveedor solicitado
		listProductsByProvider(nif_proveedor);
	}
	
	// obtiene todos los productos y los añade a la tabla
	private void listProducts() {
		Producto_Controller controller = new Producto_Controller(connection);
		DefaultTableModel currentModel = (DefaultTableModel) table.getModel();
		controller.listProducts(currentModel);
	}
	
	// obtiene todos los productos de un proveedor y los añade a la tabla
	private void listProductsByProvider(String nif_proveedor) {
		Producto_Controller controller = new Producto_Controller(connection);
		DefaultTableModel currentModel = (DefaultTableModel) table.getModel();
		controller.listProductsByProvider(currentModel, nif_proveedor);
		
		if (currentModel.getRowCount() == 0) {
			JOptionPane.showMessageDialog(this, "No se ha encontrado ningún producto.", "Sin resultados",
					JOptionPane.WARNING_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "Se han encontrado "+currentModel.getRowCount()+" productos.", "Consultar productos",
					JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	// obtiene el producto indicado y lo añade a la tabla vaciándola previamente
	private void findProduct() {
		String input = JOptionPane.showInputDialog(
				   this,
				   "Introduzca el nombre o parte del nombre del producto:",
				   "Buscar un producto",
				   JOptionPane.INFORMATION_MESSAGE);
		
		// si el usuario introduce algo, procede a buscarlo
		if (input != null && !input.isEmpty()) {
			Producto_Controller controller = new Producto_Controller(connection);
			DefaultTableModel currentModel = (DefaultTableModel) table.getModel();
			controller.findProduct(currentModel, input);
			
			if (currentModel.getRowCount() == 0) {
				JOptionPane.showMessageDialog(this, "No se ha encontrado ningún producto con este nombre.", "Sin resultados",
						JOptionPane.WARNING_MESSAGE);
			}
		} else if (input != null){
			JOptionPane.showMessageDialog(this, "El nombre introducido no es válido.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}
