package view;

import java.awt.Color;
import java.awt.Cursor;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JSeparator;

import controller.Cita_Controller;
import controller.Diente_Controller;
import controller.MySQLConnection;
import model.Cita;
import model.Diente;
import resources.MainButton;

public class Odontograma extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private MySQLConnection connection;
	private String id_cita;
	private String dni_paciente;
	private ArrayList<JButton> teeth;
	private ArrayList<Integer> teethId;

	public Odontograma(JFrame parent, MySQLConnection connection, String id_cita) {
		// establecemos la ventana de diálogo como modal
		super(parent,true);
		
		// asignamos a los atributos de la clase los argumentos pasados al crear la ventana
		this.connection = connection;
		this.id_cita = id_cita;
				
		setTitle("DentiApp: Odontograma");
		setBounds(100, 100, 700, 515);
		setResizable(false);
		getContentPane().setLayout(null);
		
		getDNIPaciente(); // obtenemos el DNI del paciente de la cita
		
		teeth = new ArrayList<JButton>();
		teethId = new ArrayList<Integer>();
		createTeeth(); // creamos los botones para cada diente
		configTeeth(); // configuramos y preparamos los botones para cada diente
		placeTeeth(); // colocamos los botones en el panel
		markTeethWithObservations(dni_paciente); // marcamos los dientes con observaciones
		
		JLabel lblNewLabel = new JLabel("      43              42               41              31               32              33");
		lblNewLabel.setForeground(new Color(0, 75, 72));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(74, 217, 784, 13);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("      13              12               11              21               22              23");
		lblNewLabel_1.setForeground(new Color(0, 75, 72));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel_1.setBounds(74, 185, 784, 13);
		getContentPane().add(lblNewLabel_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(92, 208, 500, 2);
		getContentPane().add(separator);
		
		MainButton backBtn = new MainButton("Volver");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		backBtn.setBounds(243, 420, 200, 44);
		getContentPane().add(backBtn);
	}
	
	// crea botones para cada diente y los añade a la lista "teeth" para trabajar con ellos, y
	// añade el ID de cada diente en el mismo orden a la lista "teethId"
	public void createTeeth() {
		// parte superior
		JButton tooth_13 = new JButton();
		tooth_13.setBounds(74, 30, 80, 125);
		tooth_13.setIcon(new ImageIcon("img/upper-canino.png"));
		teeth.add(tooth_13);
		teethId.add(13);
		
		JButton tooth_12 = new JButton();
		tooth_12.setBounds(164, 40, 80, 125);
		tooth_12.setIcon(new ImageIcon("img/upper-incisivo.png"));
		teeth.add(tooth_12);
		teethId.add(12);
		
		JButton tooth_11 = new JButton();
		tooth_11.setBounds(254, 50, 80, 125);
		tooth_11.setIcon(new ImageIcon("img/upper-incisivo.png"));
		teeth.add(tooth_11);
		teethId.add(11);
		
		JButton tooth_21 = new JButton();
		tooth_21.setBounds(344, 50, 80, 125);
		tooth_21.setIcon(new ImageIcon("img/upper-incisivo.png"));
		teeth.add(tooth_21);
		teethId.add(21);
		
		JButton tooth_22 = new JButton();
		tooth_22.setBounds(434, 40, 80, 125);
		tooth_22.setIcon(new ImageIcon("img/upper-incisivo.png"));
		teeth.add(tooth_22);
		teethId.add(22);
		
		JButton tooth_23 = new JButton();
		tooth_23.setBounds(524, 30, 80, 125);
		tooth_23.setIcon(new ImageIcon("img/upper-canino.png"));
		teeth.add(tooth_23);
		teethId.add(23);
		
		// parte inferior
		JButton tooth_43 = new JButton();
		tooth_43.setBounds(74, 260, 80, 125);
		tooth_43.setIcon(new ImageIcon("img/lower-canino.png"));
		teeth.add(tooth_43);
		teethId.add(43);
		
		JButton tooth_42 = new JButton();
		tooth_42.setBounds(164, 250, 80, 125);
		tooth_42.setIcon(new ImageIcon("img/lower-incisivo.png"));
		teeth.add(tooth_42);
		teethId.add(42);
		
		JButton tooth_41 = new JButton();
		tooth_41.setBounds(254, 240, 80, 125);
		tooth_41.setIcon(new ImageIcon("img/lower-incisivo.png"));
		teeth.add(tooth_41);
		teethId.add(41);
		
		JButton tooth_31 = new JButton();
		tooth_31.setBounds(344, 240, 80, 125);
		tooth_31.setIcon(new ImageIcon("img/lower-incisivo.png"));
		teeth.add(tooth_31);
		teethId.add(31);
		
		JButton tooth_32 = new JButton();
		tooth_32.setBounds(434, 250, 80, 125);
		tooth_32.setIcon(new ImageIcon("img/lower-incisivo.png"));
		teeth.add(tooth_32);
		teethId.add(32);
		
		JButton tooth_33 = new JButton();
		tooth_33.setBounds(524, 260, 80, 125);
		tooth_33.setIcon(new ImageIcon("img/lower-canino.png"));
		teeth.add(tooth_33);
		teethId.add(33);
	}
	
	// cambia el cursor al colocarse encima de cada uno de los botones de cada diente y añade
	// el ActionListener a cada uno de ellos
	public void configTeeth() {
		for (int i = 0; i < teeth.size(); i++) {
			teeth.get(i).setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			teeth.get(i).addActionListener(this);
		}
	}
	
	// añade el botón de cada diente al panel
	public void placeTeeth() {
		for (int i = 0; i < teeth.size(); i++) {
			getContentPane().add(teeth.get(i));
		}
	}
	
	// permite obtener el dni del paciente de la cita
	public void getDNIPaciente() {
		Cita_Controller controller = new Cita_Controller(connection);
		
		try {
			ArrayList<Cita> cita = controller.getCitaByID(id_cita);
			dni_paciente = cita.get(0).getDni_paciente();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// cambia el icono de los botones cuyos dientes tienen observaciones
	public void markTeethWithObservations(String dni_paciente) {
		Diente_Controller controller = new Diente_Controller(connection);
		
		try {
			ArrayList<Diente> dientes = controller.getDientes(dni_paciente);
			
			for (int i = 0; i < dientes.size(); i++) {
				boolean found = false;
				for (int j = 0; j < teethId.size() && !found; j++) {
					if (dientes.get(i).getNum_diente() == teethId.get(j)) {
						switch (teethId.get(j)) {
							case 11, 12, 21, 22:
								teeth.get(j).setIcon(new ImageIcon("img/upper-incisivo-tocado.png"));
								break;
							case 13, 23:
								teeth.get(j).setIcon(new ImageIcon("img/upper-canino-tocado.png"));
								break;
							case 31, 32, 41, 42:
								teeth.get(j).setIcon(new ImageIcon("img/lower-incisivo-tocado.png"));
								break;
							case 33, 43:
								teeth.get(j).setIcon(new ImageIcon("img/lower-canino-tocado.png"));
								break;
						}
						
						found = true;
					}
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		boolean found = false;
		int toothId = 0;
		for (int i = 0; i < teeth.size() && !found; i++) {
			if (e.getSource() == teeth.get(i)) {
				toothId = teethId.get(i);
				found = true;
			}
		}
		
		Tooth_Dialog td = new Tooth_Dialog(Odontograma.this,connection,id_cita,dni_paciente,toothId);
		td.setLocationRelativeTo(null);
		td.setVisible(true);
		markTeethWithObservations(dni_paciente);
		
	}
}
