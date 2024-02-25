package view;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.border.MatteBorder;

import controller.Menu_Controller;
import controller.MySQLConnection;
import controller.Usuario_Controller;
import model.Usuario;

import javax.swing.border.CompoundBorder;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Login_Main extends JFrame implements KeyListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MySQLConnection connection;
	private JTextField dniField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login_Main frame = new Login_Main();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, "No se ha podido conectar a la base de datos, vuelva a iniciar la aplicación.", "DentiApp",
							JOptionPane.ERROR_MESSAGE);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login_Main() throws SQLException {
		// conectamos a base de datos
		connection = new MySQLConnection();
		connection.connect();
		
		setTitle("DentiApp");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(96, 164, 161));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel logoPanel = new JPanel();
		logoPanel.setOpaque(false);
		logoPanel.setBounds(0, 0, 354, 194);
		contentPane.add(logoPanel);
		GridBagLayout gbl_logoPanel = new GridBagLayout();
		logoPanel.setLayout(gbl_logoPanel);
		
		JLabel dentiappLogo = new JLabel("");
		dentiappLogo.setIcon(new ImageIcon("img/logo_small.png"));
		logoPanel.add(dentiappLogo);
		
		JLabel dniLbl = new JLabel("DNI");
		dniLbl.setForeground(Color.WHITE);
		dniLbl.setFont(new Font("Tahoma", Font.BOLD, 18));
		dniLbl.setBounds(38, 222, 274, 22);
		contentPane.add(dniLbl);
		
		dniField = new JTextField();
		dniField.setBorder(new CompoundBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)), new EmptyBorder(0, 1, 1, 0)));
		dniField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		dniField.setBounds(38, 247, 274, 33);
		dniField.addKeyListener(this);
		contentPane.add(dniField);
		
		JLabel passwordLbl = new JLabel("Contraseña");
		passwordLbl.setForeground(Color.WHITE);
		passwordLbl.setFont(new Font("Tahoma", Font.BOLD, 18));
		passwordLbl.setBounds(38, 291, 274, 22);
		contentPane.add(passwordLbl);
		
		passwordField = new JPasswordField();
		passwordField.setBorder(new CompoundBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)), new EmptyBorder(0, 1, 1, 0)));
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		passwordField.setBounds(38, 316, 274, 33);
		passwordField.addKeyListener(this);
		contentPane.add(passwordField);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setOpaque(false);
		bottomPanel.setBounds(0, 390, 354, 131);
		contentPane.add(bottomPanel);
		bottomPanel.setLayout(null);
		
		JButton loginBtn = new JButton("ACCEDER");
		loginBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		loginBtn.setFocusPainted(false);
		loginBtn.setBounds(77, 31, 198, 42);
		loginBtn.setFont(new Font("Tahoma", Font.BOLD, 18));
		bottomPanel.add(loginBtn);
		
		JLabel changePassLbl = new JLabel("Cambiar contraseña");
		changePassLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Menu_Controller.openChangePass(connection, Login_Main.this);
				setVisible(false);
			}
		});
		changePassLbl.setHorizontalAlignment(SwingConstants.CENTER);
		changePassLbl.setBounds(77, 81, 198, 17);
		changePassLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		changePassLbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		bottomPanel.add(changePassLbl);
	}
	
	// comprueba que los campos son válidos antes del login e informa al usuario en caso contrario
	private boolean checkFields() {
		if (dniField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "El campo DNI está vacío.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (!Menu_Controller.checkDni(dniField.getText())) {
			JOptionPane.showMessageDialog(this, "El DNI introducido no es válido.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (String.valueOf(passwordField.getPassword()).isEmpty()) {
			JOptionPane.showMessageDialog(this, "El campo contraseña está vacío.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			return true;
		}
	}
	
	// efectúa el login del usuario comprobando sus credenciales y enviándolo al panel que le corresponde
	private void login() {
		if (checkFields()) {
			Usuario_Controller controller = new Usuario_Controller(connection);
			
			try {
				String dni = dniField.getText();
				ArrayList<Usuario> usuario = controller.getUser(dni);
				String password = String.valueOf(passwordField.getPassword());
				
				if (usuario.isEmpty()) { // si el dni no devuelve ningún usuario de la base de datos
					JOptionPane.showMessageDialog(this, "DNI o contraseña incorrectos.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					// si el usuario es admin
					if (usuario.get(0).getPassword().equals(password) && !usuario.get(0).isDesactivado() && usuario.get(0).isAdmin()) {
						Menu_Controller.openAdminMain(connection, dni);
						dispose();
					// si el usuario es doctor
					} else if (usuario.get(0).getPassword().equals(password) && !usuario.get(0).isDesactivado() && !usuario.get(0).isAdmin()) {
						Menu_Controller.openDoctorMain(connection, dni);
						dispose();
					// si el usuario ha sido desactivado
					} else if (usuario.get(0).getPassword().equals(password) && usuario.get(0).isDesactivado()) {
						JOptionPane.showMessageDialog(this, "Este usuario ha sido desactivado, contacte con el administrador.", "Usuario desactivado",
								JOptionPane.WARNING_MESSAGE);
					// si el usuario existe pero la contraseña no coincide
					} else {
						JOptionPane.showMessageDialog(this, "DNI o contraseña incorrectos.", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
			login();
		} else if (e.isControlDown() && e.isAltDown() && e.getKeyCode() == KeyEvent.VK_O) {
			Menu_Controller.openEasterEgg(Login_Main.this);
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}
}
