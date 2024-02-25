package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Admin_UsuariosMain extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MySQLConnection connection;
	private String userDni;

	/**
	 * Create the frame.
	 */
	public Admin_UsuariosMain(MySQLConnection connection, String userDni) {
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
		
		JLabel lblHint = new JLabel("Seleccione un tipo de usuario");
		lblHint.setForeground(new Color(0, 75, 72));
		lblHint.setFont(new Font("Segoe UI", Font.PLAIN, 28));
		lblHint.setBounds(24, 62, 776, 60);
		centeredPanel.add(lblHint);
		
		MainButton doctoresBtn = new MainButton("Doctores");
		doctoresBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu_Controller.openConsDoc(connection, userDni);
				dispose();
			}
		});
		doctoresBtn.setBounds(386, 300, 310, 44);
		centeredPanel.add(doctoresBtn);
		
		MainButton pacientesBtn = new MainButton("Pacientes");
		pacientesBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu_Controller.openConsPac(connection, userDni);
				dispose();
			}
		});
		pacientesBtn.setBounds(386, 354, 310, 44);
		centeredPanel.add(pacientesBtn);
		
		JLabel dienteBG = new JLabel("");
		dienteBG.setLocation(216, 90);
		dienteBG.setSize(584, 541);
		dienteBG.setIcon(new ImageIcon("img/diente_lowopacity.png"));
		centeredPanel.add(dienteBG);
	}
}
