package controller;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import model.Citas;
import model.Doctores;
import model.Especialidades;
import model.Pacientes;
import model.Pagos;
import model.Tratamientos;
import model.Usuarios;

/**
 * Clase de utilidad para Hibernate que proporciona métodos para conectar y desconectar de la base de datos.
 */
public class HibernateConnection {
	private SessionFactory factory; 
	private Session session;
     
     public HibernateConnection() {
    	 session = null;
     }

    /**
     * Método para conectar a la base de datos utilizando Hibernate.
     */
    public void connect() throws HibernateException {
    	factory = (SessionFactory) new Configuration().configure("hibernate.cfg.xml")
    			.buildSessionFactory();
        session = factory.openSession();
    }

    /**
     * Método para desconectar de la base de datos.
     */
    public void disconnect() throws HibernateException {
        if (session != null) {
            session.close();
            factory.close();
        }
    }

	public Session getSession() {
		return session;
	}
}
