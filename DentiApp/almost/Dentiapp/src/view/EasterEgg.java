package view;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;

public class EasterEgg extends JDialog {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the dialog.
	 */
	public EasterEgg(Login_Main parent, boolean modal) {
		
		super(parent,modal);
		setTitle("Shhh, esto es un secreto");
		setResizable(false);
		
		setBounds(100, 100, 630, 410);
		getContentPane().setLayout(null);
		
		JLabel medacFoto = new JLabel("");
		medacFoto.setIcon(new ImageIcon("img/medac.jpg"));
		medacFoto.setLocation(0, 0);
		medacFoto.setSize(614, 371);
		getContentPane().add(medacFoto);

	}

}
