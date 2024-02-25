package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import controller.HibernateConnection;
import controller.Query_Controller;
import resources.MainButton;

/**
 * Clase que corresponde al JDialog de realización de consultas de la aplicación.
 * Permite a los usuarios realizar diferentes consultas a la base de datos y obtener el resultado.
 */
public class Query extends JDialog {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private HibernateConnection connection;
	private JTable table;

	/**
     * Constructor de la clase Query.
     * Inicializa y configura el JDialog.
     * @param m instancia de la clase MainMenu de la que depende esta ventana de diálogo
     * @param connection instancia de la clase MySQLConnection que permite a esta ventana comunicarse con la base de datos
     */
	public Query(MainMenu m, HibernateConnection connection) {
		super(m,true);
		
		// asignamos a la variable connection el argumento pasado al crear la ventana
		this.connection = connection;
				
		setTitle("Realizar consulta");
		setBounds(100, 100, 1173, 729);
		setResizable(false);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();

		setContentPane(contentPane);
		contentPane.setBackground(new Color(249, 249, 249));
		contentPane.setLayout(null);
		
		JLabel lblTabName = new JLabel("Realizar consulta");
		lblTabName.setForeground(new Color(0, 75, 72));
		lblTabName.setFont(new Font("Segoe UI Black", Font.PLAIN, 44));
		lblTabName.setBounds(27, 11, 776, 60);
		contentPane.add(lblTabName);
		
		MainButton backBtn = new MainButton("Volver");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		backBtn.setBounds(949, 11, 200, 44);
		contentPane.add(backBtn);
		
		table = new JTable();
		table.setRowHeight(20);
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		JPanel tablePanel = new JPanel();
		tablePanel.setLocation(55, 113);
		tablePanel.setSize(1061, 447);
		tablePanel.setLayout(new BorderLayout(0, 0));
		tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);
		contentPane.add(tablePanel);
		
	    // modelo de la tabla con las columnas
		DefaultTableModel model = new DefaultTableModel() {

	        @Override
	        public boolean isCellEditable(int row, int column) {
	           return false; // impide que las tablas puedan ser editadas
	        }
	    };
	    table.setModel(model);
		
	    MainButton findBtn = new MainButton("Buscar en...");
	    findBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DefaultTableModel currentModel = (DefaultTableModel) table.getModel();
				Query_Controller.find(Query.this, connection, currentModel);
			}
		});
	    findBtn.setBounds(430, 570, 320, 44);
		contentPane.add(findBtn);

	}

}
