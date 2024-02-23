package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

import controller.Doctor_Controller;
import controller.Menu_Controller;
import controller.MySQLConnection;
import model.Doctor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import resources.LeftMenuButton;
import resources.MainButton;

import java.awt.Font;

import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class Doctor_Main extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MySQLConnection connection;
	private String userDni;

	/**
	 * Create the frame.
	 */
	public Doctor_Main(MySQLConnection connection, String userDni) {
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
		leftMenuPanel.setLayout(new GridLayout(4, 0, 0, 0));
		
		LeftMenuButton homeBtn = new LeftMenuButton("",true);
		homeBtn.setIcon(new ImageIcon("img/diente.png"));
		leftMenuPanel.add(homeBtn);
		
		LeftMenuButton appointmentsBtn = new LeftMenuButton("Citas",false);
		appointmentsBtn.setIcon(new ImageIcon("img/calendar.png"));
		leftMenuPanel.add(appointmentsBtn);
		
		LeftMenuButton patientsBtn = new LeftMenuButton("Pacientes",false);
		patientsBtn.setIcon(new ImageIcon("img/user.png"));
		leftMenuPanel.add(patientsBtn);
		
		LeftMenuButton stockBtn = new LeftMenuButton("Stock",false);
		stockBtn.setIcon(new ImageIcon("img/box.png"));
		leftMenuPanel.add(stockBtn);
		
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
		centeredPanel.setPreferredSize(new Dimension(1189, 729));
		centeredPanel.setLayout(null);
		mainPanel.add(centeredPanel, new GridBagConstraints());
		
		JLabel lblWelcome = new JLabel("Bienvenid@ Doctor");
		lblWelcome.setForeground(new Color(0, 75, 72));
		lblWelcome.setFont(new Font("Segoe UI Black", Font.PLAIN, 44));
		lblWelcome.setBounds(24, 11, 1133, 60);
		centeredPanel.add(lblWelcome);
		
		// Añadimos el nombre del doctor en el JLabel de bienvenida
		try {
			Menu_Controller.setDoctorName(connection,userDni,lblWelcome);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		JLabel lblDate = new JLabel("Hoy es 1 de enero de 1970.");
		lblDate.setForeground(new Color(0, 75, 72));
		lblDate.setFont(new Font("Segoe UI", Font.PLAIN, 28));
		lblDate.setBounds(24, 62, 560, 60);
		centeredPanel.add(lblDate);
		
		// Establecemos la fecha actual en el JLabel que indica la fecha
		Menu_Controller.setDate(lblDate);
		
		MainButton logOutBtn = new MainButton("Cerrar sesión");
		logOutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu_Controller.openLogin(connection);
				dispose();
			}
		});
		logOutBtn.setBounds(979, 674, 200, 44);
		centeredPanel.add(logOutBtn);
		
		JLabel dienteBG = new JLabel("");
		dienteBG.setLocation(216, 90);
		dienteBG.setSize(584, 541);
		dienteBG.setIcon(new ImageIcon("img/diente_lowopacity.png"));
		centeredPanel.add(dienteBG);
	
	}
}
