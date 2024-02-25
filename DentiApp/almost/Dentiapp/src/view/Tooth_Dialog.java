package view;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.Diente_Controller;
import controller.MySQLConnection;
import model.Diente;
import resources.MainButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Font;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

public class Tooth_Dialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private MySQLConnection connection;
	private String id_cita;
	private String dni_paciente;
	private int num_diente;
	private ArrayList<Diente> observaciones;
	private int currentIndex;
	private JLabel lblObservaciones;
	private JTextArea textArea;
	private MainButton previousBtn;
	private MainButton nextBtn;
	private JTextArea textArea1;
	private MainButton insertBtn;

	public Tooth_Dialog(JDialog parent, MySQLConnection connection, String id_cita,
			String dni_paciente, int num_diente) {
		// establecemos la ventana de diálogo como modal
		super(parent,true);
		
		// asignamos a los atributos de la clase los argumentos pasados al crear la ventana
		this.connection = connection;
		this.id_cita = id_cita;
		this.dni_paciente = dni_paciente;
		this.num_diente = num_diente;
		
		obtenerObservaciones(); // obtenemos todos los registros correspondientes a este diente
				
		setTitle("DentiApp: Odontograma");
		setBounds(100, 100, 800, 670);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JLabel lblHint = new JLabel("Diente");
		lblHint.setForeground(new Color(0, 75, 72));
		lblHint.setFont(new Font("Segoe UI", Font.BOLD, 28));
		lblHint.setBounds(15, 10, 761, 38);
		lblHint.setText("Diente "+num_diente);
		getContentPane().add(lblHint);
		
		lblObservaciones = new JLabel("Observaciones previas");
		lblObservaciones.setForeground(new Color(0, 75, 72));
		lblObservaciones.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblObservaciones.setBounds(15, 60, 761, 27);
		getContentPane().add(lblObservaciones);
		
		textArea = new JTextArea();
		textArea.setBorder(new CompoundBorder(null, new EmptyBorder(0, 5, 0, 5)));
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 18));
		textArea.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane.setBounds(10, 100, 666, 230);
		getContentPane().add(scrollPane);
		
		previousBtn = new MainButton("<<");
		previousBtn.setEnabled(false);
		previousBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				anteriorObservacion();
			}
		});
		previousBtn.setBounds(686, 165, 90, 44);
		getContentPane().add(previousBtn);
		
		nextBtn = new MainButton(">>");
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				siguienteObservacion();
			}
		});
		nextBtn.setBounds(686, 219, 90, 44);
		getContentPane().add(nextBtn);
		
		JLabel lblAddObservacion = new JLabel("Añadir observación");
		lblAddObservacion.setForeground(new Color(0, 75, 72));
		lblAddObservacion.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblAddObservacion.setBounds(15, 340, 761, 27);
		getContentPane().add(lblAddObservacion);
		
		textArea1 = new JTextArea();
		textArea1.setBorder(new CompoundBorder(null, new EmptyBorder(0, 5, 0, 5)));
		textArea1.setFont(new Font("Monospaced", Font.PLAIN, 18));
		
		JScrollPane scrollPane1 = new JScrollPane(textArea1);
		scrollPane1.setBounds(10, 380, 766, 185);
		getContentPane().add(scrollPane1);
		
		insertBtn = new MainButton("Añadir");
		insertBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertarObservacion();
			}
		});
		insertBtn.setBounds(189, 575, 200, 44);
		getContentPane().add(insertBtn);
		
		MainButton backBtn = new MainButton("Volver");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		backBtn.setBounds(399, 575, 200, 44);
		getContentPane().add(backBtn);
		
		if (observaciones.isEmpty()) {
			nextBtn.setEnabled(false);
			textArea.setText("No se han encontrado observaciones previas.");
		} else if (observaciones.size()==1) {
			nextBtn.setEnabled(false);
			lblObservaciones.setText("Observaciones previas (1/1)");
			mostrarObservacion();
		} else {
			lblObservaciones.setText("Observaciones previas (1/"+observaciones.size()+")");
			mostrarObservacion();
		}
		
		comprobarObservacion();
	}
	
	// permite comprobar si ya se ha realizado una observación sobre este diente en la actual cita,
	// para así bloquear el botón de añadir observación
	public void comprobarObservacion() {
		Diente_Controller controller = new Diente_Controller(connection);
		ArrayList<Diente> resultados = new ArrayList<Diente>();
		try {
			resultados = controller.getDientes(num_diente,id_cita);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		if (!resultados.isEmpty()) {
			insertBtn.setEnabled(false);
		}
	}
	
	// permite obtener todos los registros correspondientes a este diente
	public void obtenerObservaciones() {
		Diente_Controller controller = new Diente_Controller(connection);
		try {
			observaciones = controller.getDientes(dni_paciente,num_diente);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	// permite pasar a la observación previa
	public void anteriorObservacion() {
		if (currentIndex-1==0) {
			previousBtn.setEnabled(false);
		}
		
		if (currentIndex-1==observaciones.size()-2) {
			nextBtn.setEnabled(true);
		}
		
		currentIndex-=1;
		lblObservaciones.setText("Observaciones previas ("+(currentIndex+1)+"/"+observaciones.size()+")");
		mostrarObservacion();
	}
	
	// permite continuar a la siguiente observación
	public void siguienteObservacion() {
		if (currentIndex+1==1) {
			previousBtn.setEnabled(true);
		}
		
		if (currentIndex+1==observaciones.size()-1) {
			nextBtn.setEnabled(false);
		}
		
		currentIndex+=1;
		lblObservaciones.setText("Observaciones previas ("+(currentIndex+1)+"/"+observaciones.size()+")");
		mostrarObservacion();
	}
	
	// prepara y muestra la observación en el área designada
	public void mostrarObservacion() {
		String observacion = observaciones.get(currentIndex).getNotas()
				+"\n\n("+observaciones.get(currentIndex).getNom_doctor()+" "
				+observaciones.get(currentIndex).getApe_doctor()+", "
				+observaciones.get(currentIndex).getFecha()+")";
		
		textArea.setText(observacion);
		textArea.setCaretPosition(0);
	}
	
	// permite añadir una observación sobre el diente en cuestión
	public void insertarObservacion() {
		Diente_Controller controller = new Diente_Controller(connection);
		try {
			controller.insertNewObservacion(id_cita, num_diente, textArea1.getText());
			JOptionPane.showMessageDialog(this, "La observación ha sido añadida con éxito.", "Éxito",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "No se ha podido añadir la observación.", "Error",
					JOptionPane.ERROR_MESSAGE);
		}
		
		dispose();
	}
}
