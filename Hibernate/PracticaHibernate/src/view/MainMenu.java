package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.hibernate.HibernateException;

import controller.Menu_Controller;
import controller.HibernateConnection;

import java.awt.Color;
import java.awt.EventQueue;

import resources.MainButton;

import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

/**
 * Clase que corresponde al JFrame del menú principal de la aplicación.
 * Permite a los usuarios acceder a diferentes funcionalidades del programa.
 */
public class MainMenu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private HibernateConnection connection;
	
	/**
     * Método principal que se ejecuta al iniciar el programa.
     * Crea una instancia de MainMenu y la hace visible en el centro de la pantalla.
     * 
     * @param args los argumentos de la línea de comandos
     */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenu frame = new MainMenu();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
     * Constructor de la clase MainMenu.
     * Inicializa y configura el JFrame.
     */
	public MainMenu() {
		// instanciamos la clase para conexión a base de datos
		connection = new HibernateConnection();
					
		setTitle("Práctica Hibernate");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1173, 729);
		setResizable(false);
		contentPane = new JPanel();

		setContentPane(contentPane);
		contentPane.setBackground(new Color(249, 249, 249));
		contentPane.setLayout(null);
		
		JLabel lblTabName = new JLabel("Gestión de base de datos con Hibernate");
		lblTabName.setForeground(new Color(0, 75, 72));
		lblTabName.setFont(new Font("Segoe UI Black", Font.PLAIN, 44));
		lblTabName.setBounds(27, 11, 1000, 60);
		contentPane.add(lblTabName);
		
		JLabel lblHint = new JLabel("Seleccione una opción");
		lblHint.setForeground(new Color(0, 75, 72));
		lblHint.setFont(new Font("Segoe UI", Font.PLAIN, 28));
		lblHint.setBounds(27, 62, 776, 60);
		contentPane.add(lblHint);
		
		MainButton consultarBtn = new MainButton("Realizar consulta");
		consultarBtn.setEnabled(false);
		consultarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu_Controller.openQuery(MainMenu.this,connection);
			}
		});
		consultarBtn.setBounds(411, 285, 310, 44);
		contentPane.add(consultarBtn);
		
		MainButton insertarBtn = new MainButton("Realizar inserción");
		insertarBtn.setEnabled(false);
		insertarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu_Controller.openSave(MainMenu.this, connection);
			}
		});
		insertarBtn.setBounds(411, 339, 310, 44);
		contentPane.add(insertarBtn);
		
		MainButton modificarBtn = new MainButton("Realizar modificación");
		modificarBtn.setEnabled(false);
		modificarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu_Controller.openUpdate(MainMenu.this, connection);
			}
		});
		modificarBtn.setBounds(411, 393, 310, 44);
		contentPane.add(modificarBtn);
		
		MainButton eliminarBtn = new MainButton("Realizar eliminación");
		eliminarBtn.setEnabled(false);
		eliminarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu_Controller.openDelete(MainMenu.this, connection);
			}
		});
		eliminarBtn.setBounds(411, 448, 310, 44);
		contentPane.add(eliminarBtn);
		
		MainButton conectarBtn = new MainButton("Conectar");
		conectarBtn.setBounds(411, 230, 136, 44);
		contentPane.add(conectarBtn);
		
		MainButton desconectarBtn = new MainButton("Desconectar");
		desconectarBtn.setEnabled(false);
		desconectarBtn.setBounds(551, 230, 170, 44);
		contentPane.add(desconectarBtn);
		
		MainButton salirBtn = new MainButton("Salir");
		salirBtn.setBounds(949, 638, 200, 44);
		contentPane.add(salirBtn);
		
		conectarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connection.connect();
					
					JOptionPane.showMessageDialog(MainMenu.this, "Conexión realizada con éxito.", "Éxito",
							JOptionPane.INFORMATION_MESSAGE);
					
					conectarBtn.setEnabled(false);
					desconectarBtn.setEnabled(true);
					
					consultarBtn.setEnabled(true);
					insertarBtn.setEnabled(true);
					modificarBtn.setEnabled(true);
					eliminarBtn.setEnabled(true);
				} catch (HibernateException ex) {
					System.out.println(ex.getMessage());
					JOptionPane.showMessageDialog(MainMenu.this, "No se ha podido conectar a la base de datos, reinicie la aplicación.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		desconectarBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connection.disconnect();
					
					JOptionPane.showMessageDialog(MainMenu.this, "Desconexión realizada con éxito.", "Éxito",
							JOptionPane.INFORMATION_MESSAGE);
					
					desconectarBtn.setEnabled(false);
					conectarBtn.setEnabled(true);
					
					consultarBtn.setEnabled(false);
					insertarBtn.setEnabled(false);
					modificarBtn.setEnabled(false);
					eliminarBtn.setEnabled(false);

				} catch (HibernateException ex) {
					JOptionPane.showMessageDialog(MainMenu.this, "No se ha podido realizar la desconexión de la base de datos.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		salirBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					connection.disconnect();
					dispose();
				} catch (HibernateException ex) {
					JOptionPane.showMessageDialog(MainMenu.this, "No se ha podido realizar la desconexión de la base de datos.", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
	}
}
