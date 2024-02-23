package view;
import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

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
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Login_PassChange extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private MySQLConnection connection;
	private Login_Main lm;
	private JTextField dniField;
	private JPasswordField currentPwField;
	private JPasswordField newPassField;
	private JPasswordField newPassField2;

	/**
	 * Create the frame.
	 */
	public Login_PassChange(MySQLConnection connection, Login_Main lm) {
		// asignamos a la variable connection y lm los argumentos pasado al crear la ventana
		this.connection = connection;
		this.lm = lm;
		
		setTitle("DentiApp");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 370, 560);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(96, 164, 161));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel dniLbl = new JLabel("DNI");
		dniLbl.setForeground(Color.WHITE);
		dniLbl.setFont(new Font("Tahoma", Font.BOLD, 18));
		dniLbl.setBounds(38, 86, 274, 22);
		contentPane.add(dniLbl);
		
		dniField = new JTextField();
		dniField.setBorder(new CompoundBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)), new EmptyBorder(0, 1, 1, 0)));
		dniField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		dniField.setBounds(38, 111, 274, 33);
		contentPane.add(dniField);
		
		JLabel currentPwLbl = new JLabel("Contraseña actual");
		currentPwLbl.setForeground(Color.WHITE);
		currentPwLbl.setFont(new Font("Tahoma", Font.BOLD, 18));
		currentPwLbl.setBounds(38, 155, 274, 22);
		contentPane.add(currentPwLbl);
		
		currentPwField = new JPasswordField();
		currentPwField.setTransferHandler(null); // deshabilita copiar-pegar en el campo
		currentPwField.setBorder(new CompoundBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)), new EmptyBorder(0, 1, 1, 0)));
		currentPwField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		currentPwField.setBounds(38, 180, 274, 33);
		contentPane.add(currentPwField);
		
		JLabel newPassLbl = new JLabel("Nueva contraseña");
		newPassLbl.setForeground(Color.WHITE);
		newPassLbl.setFont(new Font("Tahoma", Font.BOLD, 18));
		newPassLbl.setBounds(38, 224, 274, 22);
		contentPane.add(newPassLbl);
		
		newPassField = new JPasswordField();
		newPassField.setTransferHandler(null); // deshabilita copiar-pegar en el campo
		newPassField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		newPassField.setBorder(new CompoundBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)), new EmptyBorder(0, 1, 1, 0)));
		newPassField.setBounds(38, 249, 274, 33);
		contentPane.add(newPassField);
		
		JLabel newPassLbl2 = new JLabel("Repita la contraseña");
		newPassLbl2.setForeground(Color.WHITE);
		newPassLbl2.setFont(new Font("Tahoma", Font.BOLD, 18));
		newPassLbl2.setBounds(38, 292, 274, 22);
		contentPane.add(newPassLbl2);
		
		newPassField2 = new JPasswordField();
		newPassField2.setTransferHandler(null); // deshabilita copiar-pegar en el campo
		newPassField2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		newPassField2.setBorder(new CompoundBorder(new MatteBorder(0, 0, 1, 0, (Color) new Color(0, 0, 0)), new EmptyBorder(0, 1, 1, 0)));
		newPassField2.setBounds(38, 318, 274, 33);
		contentPane.add(newPassField2);
		
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(null);
		bottomPanel.setOpaque(false);
		bottomPanel.setBounds(0, 390, 354, 131);
		contentPane.add(bottomPanel);
		
		JButton btnChange = new JButton("CAMBIAR");
		btnChange.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changePass();
			}
		});
		btnChange.setFocusPainted(false);
		btnChange.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnChange.setBounds(77, 31, 198, 42);
		bottomPanel.add(btnChange);
		
		JLabel backLbl = new JLabel("Volver");
		backLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				lm.setVisible(true);
				dispose();
			}
		});
		backLbl.setHorizontalAlignment(SwingConstants.CENTER);
		backLbl.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		backLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		backLbl.setBounds(77, 81, 198, 17);
		bottomPanel.add(backLbl);
	}
	
	// comprueba que los campos son válidos antes del cambio de contraseña e informa al usuario en caso contrario
	private boolean checkFields() {
		if (dniField.getText().isEmpty()) {
			JOptionPane.showMessageDialog(this, "El campo DNI está vacío.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (dniField.getText().length() != 9 || !Menu_Controller.checkDni(dniField.getText())) {
			JOptionPane.showMessageDialog(this, "El DNI introducido no es válido.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (String.valueOf(currentPwField.getPassword()).isEmpty()) {
			JOptionPane.showMessageDialog(this, "El campo contraseña actual está vacío.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (String.valueOf(newPassField.getPassword()).isEmpty()) {
			JOptionPane.showMessageDialog(this, "El campo nueva contraseña está vacío.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (!String.valueOf(newPassField.getPassword()).isEmpty() &&
				(String.valueOf(newPassField.getPassword()).length()<8 || String.valueOf(newPassField.getPassword()).length()>16)) {
			JOptionPane.showMessageDialog(this, "La contraseña debe tener una longitud mayor a 8 y menor a 16.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (String.valueOf(newPassField2.getPassword()).isEmpty()) {
			JOptionPane.showMessageDialog(this, "El campo repita la contraseña está vacío.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else if (!String.valueOf(newPassField.getPassword()).equals(String.valueOf(newPassField2.getPassword()))) {
			JOptionPane.showMessageDialog(this, "La nueva contraseña no coincide.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			return true;
		}
	}
	
	// efectúa el cambio de contraseña comprobando que las credenciales introducidas sean válidas
	private void changePass() {
		if (checkFields()) {
			Usuario_Controller controller = new Usuario_Controller(connection);
			
			try {
				String dni = dniField.getText();
				ArrayList<Usuario> usuario = controller.getUser(dni);
				String password = String.valueOf(currentPwField.getPassword());
				
				if (usuario.isEmpty()) { // si el dni no devuelve ningún usuario de la base de datos
					JOptionPane.showMessageDialog(this, "DNI o contraseña incorrectos.", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else {
					// si las credenciales son válidas, se efectúa el cambio de contraseña
					if (usuario.get(0).getPassword().equals(password) && !usuario.get(0).isDesactivado()) {
						String newPass = String.valueOf(newPassField.getPassword());
						int modifiedRows = controller.modifyPass(dni,newPass);
						
						if (modifiedRows > 0) {
							JOptionPane.showMessageDialog(this, "Contraseña cambiada con éxito.", "Contraseña cambiada",
									JOptionPane.INFORMATION_MESSAGE);
							lm.setVisible(true);
							dispose();
						}
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
}
