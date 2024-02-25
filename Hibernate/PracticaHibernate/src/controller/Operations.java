package controller;

import java.util.List;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.query.Query;

import model.Citas;
import model.Dientes;
import model.Doctores;
import model.Especialidades;
import model.Pacientes;
import model.Pagos;
import model.Tratamientos;
import model.Usuarios;

/**
 * Clase que engloba métodos que realizan operaciones sobre los distintos objetos mediante Hibernate.
 */
public class Operations {
	
	// OPERACIONES SOBRE CITAS
	/**
	 * Permite insertar un objeto de tipo Citas en la base de datos.
	 * @param s sesión iniciada de Hibernate
	 * @param c la cita a insertar
	 * @param d el doctor con el que se relaciona la cita
	 * @param p el paciente con el que se relaciona la cita
	 */
	public static void saveCita(Session s, Citas c, Doctores d, Pacientes p) {
		c.setDoctor(d);
		c.setPaciente(p);
		
		s.beginTransaction();
		s.save(c);
		s.getTransaction().commit();
	}
	
	/**
	 * Permite modificar un objeto de tipo Citas en la base de datos.
	 * @param s sesión iniciada de Hibernate
	 * @param c la cita a modificar
	 */
	public static void updateCita(Session s, Citas c) {
		s.beginTransaction();
		s.update(c);
		s.getTransaction().commit();
	}
	
	/**
	 * Devuelve una lista con todos los objetos de tipo Citas que se pueden obtener de la base de datos.
	 * @param s sesión iniciada de Hibernate
	 * @return lista con objetos de tipo Citas
	 */
	public static List<Citas> getAllCitas(Session s) {
		String hql = "FROM Citas"; 
        Query<Citas> consulta = s.createQuery(hql,Citas.class); 
        List<Citas> results = consulta.getResultList();
        return results;
	}
	
	/**
	 * Devuelve un objeto de tipo Citas que se corresponde con el id pasado por parámetro.
	 * @param s sesión iniciada de Hibernate
	 * @param id identificador de la cita
	 * @return objeto de tipo Citas
	 */
	public static Citas getCita(Session s, int id) {
		Query<Citas> consulta = s.createQuery("FROM Citas WHERE id=:id",Citas.class); 
        consulta.setParameter("id",id);
        Citas cita = null;
        try {
        	cita = consulta.getSingleResult();
        } catch (NoResultException e) {
        	System.out.println(e.getMessage());
        }
        return cita;
	}
	
	/**
	 * Permite eliminar de la base de datos la cita que se corresponda con el id pasado por parámetro.
	 * @param s sesión iniciada de Hibernate
	 * @param id identificador de la cita
	 * @return booleano que indica si se ha producido o no la eliminación
	 */
	public static boolean deleteCita(Session s, int id) {
		boolean eliminado = false;
		
		//Previamente comprobar que existe el id de la cita a eliminar
		s.beginTransaction();
        Citas eliminar = s.get(Citas.class,id);
        if (eliminar != null){
            s.delete(eliminar);
            eliminado = true;
        }
        s.getTransaction().commit();
        
        return eliminado;
	}
	
	// OPERACIONES SOBRE DIENTES
	
	/**
	 * Permite insertar un objeto de tipo Dientes en la base de datos.
	 * @param s sesión iniciada de Hibernate
	 * @param d el diente a insertar
	 * @param p el paciente con el que se relaciona el diente
	 */
	public static void saveDiente(Session s, Dientes d, Pacientes p) {
		d.setPaciente(p);
		
		s.beginTransaction();
		s.save(d);
		s.getTransaction().commit();
	}
	
	/**
	 * Permite modificar un objeto de tipo Dientes en la base de datos.
	 * @param s sesión iniciada de Hibernate
	 * @param d el diente a modificar
	 */
	public static void updateDiente(Session s, Dientes d) {
		s.beginTransaction();
		s.update(d);
		s.getTransaction().commit();
	}
	
	/**
	 * Devuelve una lista con todos los objetos de tipo Dientes que se pueden obtener de la base de datos.
	 * @param s sesión iniciada de Hibernate
	 * @return lista con objetos de tipo Dientes
	 */
	public static List<Dientes> getAllDientes(Session s) {
		String hql = "FROM Dientes"; 
        Query<Dientes> consulta = s.createQuery(hql,Dientes.class); 
        List<Dientes> results = consulta.getResultList();
        return results;
	}
	
	/**
	 * Devuelve un objeto de tipo Dientes que se corresponde con el id pasado por parámetro.
	 * @param s sesión iniciada de Hibernate
	 * @param id identificador del diente
	 * @return objeto de tipo Dientes
	 */
	public static Dientes getDiente(Session s, int id) {
		Query<Dientes> consulta = s.createQuery("FROM Dientes WHERE id=:id",Dientes.class); 
        consulta.setParameter("id",id);
        Dientes diente = null;
        try {
        	diente = consulta.getSingleResult();
        } catch (NoResultException e) {
        	System.out.println(e.getMessage());
        }
        return diente;
	}
	
	/**
	 * Permite eliminar de la base de datos el diente que se corresponda con el id pasado por parámetro.
	 * @param s sesión iniciada de Hibernate
	 * @param id identificador del diente
	 * @return booleano que indica si se ha producido o no la eliminación
	 */
	public static boolean deleteDiente(Session s, int id) {
		boolean eliminado = false;
		
		//Previamente comprobar que existe el id del diente a eliminar
		s.beginTransaction();
        Dientes eliminar = s.get(Dientes.class,id);
        if (eliminar != null){
            s.delete(eliminar);
            eliminado = true;
        }
        s.getTransaction().commit();
        
        return eliminado;
	}
	
	// OPERACIONES SOBRE DOCTORES
	
	/**
	 * Permite insertar un objeto de tipo Doctores en la base de datos.
	 * @param s sesión iniciada de Hibernate
	 * @param d el doctor a insertar
	 * @param p el usuario con el que se relaciona el doctor
	 */
	public static void saveDoctor(Session s, Doctores d, Usuarios u) {
		d.setUsuario(u);
		
		s.beginTransaction();
		s.save(d);
		s.getTransaction().commit();
	}
	
	/**
	 * Permite modificar un objeto de tipo Doctores en la base de datos.
	 * @param s sesión iniciada de Hibernate
	 * @param d el doctor a modificar
	 */
	public static void updateDoctor(Session s, Doctores d) {
		s.beginTransaction();
		s.update(d);
		s.getTransaction().commit();
	}
	
	/**
	 * Devuelve una lista con todos los objetos de tipo Doctores que se pueden obtener de la base de datos.
	 * @param s sesión iniciada de Hibernate
	 * @return lista con objetos de tipo Doctores
	 */
	public static List<Doctores> getAllDoctores(Session s) {
		String hql = "FROM Doctores"; 
        Query<Doctores> consulta = s.createQuery(hql,Doctores.class); 
        List<Doctores> results = consulta.getResultList();
        return results;
	}
	
	/**
	 * Devuelve un objeto de tipo Doctores que se corresponde con el id pasado por parámetro.
	 * @param s sesión iniciada de Hibernate
	 * @param id identificador del doctor
	 * @return objeto de tipo Dientes
	 */
	public static Doctores getDoctor(Session s, int id) {
		Query<Doctores> consulta = s.createQuery("FROM Doctores WHERE id=:id",Doctores.class); 
        consulta.setParameter("id",id);
        Doctores doctor = null;
        try {
        	doctor = consulta.getSingleResult();
        } catch (NoResultException e) {
        	System.out.println(e.getMessage());
        }
        return doctor;
	}
	
	/**
	 * Permite eliminar de la base de datos el doctor que se corresponda con el id pasado por parámetro.
	 * @param s sesión iniciada de Hibernate
	 * @param id identificador del doctor
	 * @return booleano que indica si se ha producido o no la eliminación
	 */
	public static boolean deleteDoctor(Session s, int id) {
		boolean eliminado = false;
		
		//Previamente comprobar que existe el id del doctor a eliminar
		s.beginTransaction();
		Doctores eliminar = s.get(Doctores.class,id);
        if (eliminar != null){
            s.delete(eliminar);
            eliminado = true;
        }
        s.getTransaction().commit();
        
        return eliminado;
	}
	
	// OPERACIONES SOBRE ESPECIALIDADES
	
	/**
	 * Permite insertar un objeto de tipo Especialidades en la base de datos.
	 * @param s sesión iniciada de Hibernate
	 * @param c la especialidad a insertar
	 */
	public static void saveEspecialidad(Session s, Especialidades e) {
		s.beginTransaction();
		s.save(e);
		s.getTransaction().commit();
	}
	
	/**
	 * Permite modificar un objeto de tipo Especialidades en la base de datos.
	 * @param s sesión iniciada de Hibernate
	 * @param c la especialidad a modificar
	 */
	public static void updateEspecialidad(Session s, Especialidades e) {
		s.beginTransaction();
		s.update(e);
		s.getTransaction().commit();
	}
	
	/**
	 * Devuelve una lista con todos los objetos de tipo Especialidades que se pueden obtener de la base de datos.
	 * @param s sesión iniciada de Hibernate
	 * @return lista con objetos de tipo Especialidades
	 */
	public static List<Especialidades> getAllEspecialidades(Session s) {
		String hql = "FROM Especialidades"; 
        Query<Especialidades> consulta = s.createQuery(hql,Especialidades.class); 
        List<Especialidades> results = consulta.getResultList();
        return results;
	}
	
	/**
	 * Devuelve un objeto de tipo Especialidades que se corresponde con el id pasado por parámetro.
	 * @param s sesión iniciada de Hibernate
	 * @param id identificador de la especialidad
	 * @return objeto de tipo Especialidad
	 */
	public static Especialidades getEspecialidad(Session s, int id) {
		Query<Especialidades> consulta = s.createQuery("FROM Especialidades WHERE id=:id",Especialidades.class); 
        consulta.setParameter("id",id);
        Especialidades especialidad = null;
        try {
        	especialidad = consulta.getSingleResult();
        } catch (NoResultException e) {
        	System.out.println(e.getMessage());
        }
        return especialidad;
	}
	
	/**
	 * Permite eliminar de la base de datos la especialidad que se corresponda con el id pasado por parámetro.
	 * @param s sesión iniciada de Hibernate
	 * @param id identificador de la especialidad
	 * @return booleano que indica si se ha producido o no la eliminación
	 */
	public static boolean deleteEspecialidad(Session s, int id) {
		boolean eliminado = false;
		
		//Previamente comprobar que existe el id de la cita a eliminar
		s.beginTransaction();
		Especialidades eliminar = s.get(Especialidades.class,id);
        if (eliminar != null){
            s.delete(eliminar);
            eliminado = true;
        }
        s.getTransaction().commit();
        
        return eliminado;
	}
	
	// OPERACIONES SOBRE PACIENTES
	
	/**
	 * Permite insertar un objeto de tipo Pacientes en la base de datos.
	 * @param s sesión iniciada de Hibernate
	 * @param c el paciente a insertar
	 */
	public static void savePaciente(Session s, Pacientes p) {
		s.beginTransaction();
		s.save(p);
		s.getTransaction().commit();
	}
	
	/**
	 * Permite modificar un objeto de tipo Pacientes en la base de datos.
	 * @param s sesión iniciada de Hibernate
	 * @param c el paciente a modificar
	 */
	public static void updatePaciente(Session s, Pacientes p) {
		s.beginTransaction();
		s.update(p);
		s.getTransaction().commit();
	}
	
	/**
	 * Devuelve una lista con todos los objetos de tipo Pacientes que se pueden obtener de la base de datos.
	 * @param s sesión iniciada de Hibernate
	 * @return lista con objetos de tipo Pacientes
	 */
	public static List<Pacientes> getAllPacientes(Session s) {
		String hql = "FROM Pacientes"; 
        Query<Pacientes> consulta = s.createQuery(hql,Pacientes.class); 
        List<Pacientes> results = consulta.getResultList();
        return results;
	}
	
	/**
	 * Devuelve un objeto de tipo Pacientes que se corresponde con el dni pasado por parámetro.
	 * @param s sesión iniciada de Hibernate
	 * @param dni identificador del paciente
	 * @return objeto de tipo Pacientes
	 */
	public static Pacientes getPaciente(Session s, String dni) {
		Query<Pacientes> consulta = s.createQuery("FROM Pacientes WHERE dni=:dni",Pacientes.class); 
        consulta.setParameter("dni",dni);
        Pacientes paciente = null;
        try {
        	paciente = consulta.getSingleResult();
        } catch (NoResultException e) {
        	System.out.println(e.getMessage());
        }
        return paciente;
	}
	
	/**
	 * Permite eliminar de la base de datos el paciente que se corresponda con el dni pasado por parámetro.
	 * @param s sesión iniciada de Hibernate
	 * @param dni identificador del paciente
	 * @return booleano que indica si se ha producido o no la eliminación
	 */
	public static boolean deletePaciente(Session s, String dni) {
		boolean eliminado = false;
		
		//Previamente comprobar que existe el dni del paciente a eliminar
		s.beginTransaction();
        Pacientes eliminar = s.get(Pacientes.class,dni);
        if (eliminar != null){
            s.delete(eliminar);
            eliminado = true;
        }
        s.getTransaction().commit();
        
        return eliminado;
	}
	
	// OPERACIONES SOBRE PAGOS
	
	/**
	 * Permite insertar un objeto de tipo Pagos en la base de datos.
	 * @param s sesión iniciada de Hibernate
	 * @param pag el pago a insertar
	 * @param p el paciente con el que se relaciona el pago
	 */
	public static void savePagos(Session s, Pagos pag, Pacientes p) {
		pag.setPaciente(p);
		
		s.beginTransaction();
		s.save(pag);
		s.getTransaction().commit();
	}
	
	/**
	 * Permite modificar un objeto de tipo Pagos en la base de datos.
	 * @param s sesión iniciada de Hibernate
	 * @param pag el pago a modificar
	 */
	public static void updatePago(Session s, Pagos pag) {
		s.beginTransaction();
		s.update(pag);
		s.getTransaction().commit();
	}
	
	/**
	 * Devuelve una lista con todos los objetos de tipo Pagos que se pueden obtener de la base de datos.
	 * @param s sesión iniciada de Hibernate
	 * @return lista con objetos de tipo Pagos
	 */
	public static List<Pagos> getAllPagos(Session s) {
		String hql = "FROM Pagos"; 
        Query<Pagos> consulta = s.createQuery(hql,Pagos.class); 
        List<Pagos> results = consulta.getResultList();
        return results;
	}
	
	/**
	 * Devuelve un objeto de tipo Pagos que se corresponde con el id pasado por parámetro.
	 * @param s sesión iniciada de Hibernate
	 * @param id identificador del pago
	 * @return objeto de tipo Pagos
	 */
	public static Pagos getPago(Session s, int id) {
		Query<Pagos> consulta = s.createQuery("FROM Pagos WHERE id=:id",Pagos.class); 
        consulta.setParameter("id",id);
        Pagos pago = null;
        try {
        	pago = consulta.getSingleResult();
        } catch (NoResultException e) {
        	System.out.println(e.getMessage());
        }
        return pago;
	}
	
	/**
	 * Permite eliminar de la base de datos el pago que se corresponda con el id pasado por parámetro.
	 * @param s sesión iniciada de Hibernate
	 * @param id identificador del pago
	 * @return booleano que indica si se ha producido o no la eliminación
	 */
	public static boolean deletePago(Session s, int id) {
		boolean eliminado = false;
		
		//Previamente comprobar que existe el id del pago a eliminar
		s.beginTransaction();
        Pagos eliminar = s.get(Pagos.class,id);
        if (eliminar != null){
            s.delete(eliminar);
            eliminado = true;
        }
        s.getTransaction().commit();
        
        return eliminado;
	}
	
	// OPERACIONES SOBRE TRATAMIENTOS
	
	/**
	 * Permite insertar un objeto de tipo Tratamientos en la base de datos.
	 * @param s sesión iniciada de Hibernate
	 * @param t el tratamiento a insertar
	 * @param e la especialidad con la que se relaciona el tratamiento
	 */
	public static void saveTratamiento(Session s, Tratamientos t, Especialidades e) {
		t.setEspecialidad(e);
		
		s.beginTransaction();
		s.save(t);
		s.getTransaction().commit();
	}
	
	/**
	 * Permite modificar un objeto de tipo Tratamientos en la base de datos.
	 * @param s sesión iniciada de Hibernate
	 * @param t el tratamiento a modificar
	 */
	public static void updateTratamiento(Session s, Tratamientos t) {
		s.beginTransaction();
		s.update(t);
		s.getTransaction().commit();
	}
	
	/**
	 * Devuelve una lista con todos los objetos de tipo Tratamientos que se pueden obtener de la base de datos.
	 * @param s sesión iniciada de Hibernate
	 * @return lista con objetos de tipo Tratamientos
	 */
	public static List<Tratamientos> getAllTratamientos(Session s) {
		String hql = "FROM Tratamientos"; 
        Query<Tratamientos> consulta = s.createQuery(hql,Tratamientos.class); 
        List<Tratamientos> results = consulta.getResultList();
        return results;
	}
	
	/**
	 * Devuelve un objeto de tipo Tratamientos que se corresponde con el id pasado por parámetro.
	 * @param s sesión iniciada de Hibernate
	 * @param id identificador del tratamiento
	 * @return objeto de tipo Tratamientos
	 */
	public static Tratamientos getTratamiento(Session s, int id) {
		Query<Tratamientos> consulta = s.createQuery("FROM Tratamientos WHERE id=:id",Tratamientos.class); 
        consulta.setParameter("id",id);
        Tratamientos tratamiento = null;
        try {
        	tratamiento = consulta.getSingleResult();
        } catch (NoResultException e) {
        	System.out.println(e.getMessage());
        }
        return tratamiento;
	}
	
	/**
	 * Permite eliminar de la base de datos el tratamiento que se corresponda con el id pasado por parámetro.
	 * @param s sesión iniciada de Hibernate
	 * @param id identificador del tratamiento
	 * @return booleano que indica si se ha producido o no la eliminación
	 */
	public static boolean deleteTratamiento(Session s, int id) {
		boolean eliminado = false;
		
		//Previamente comprobar que existe el id de la cita a eliminar
		s.beginTransaction();
		Tratamientos eliminar = s.get(Tratamientos.class,id);
        if (eliminar != null){
            s.delete(eliminar);
            eliminado = true;
        }
        s.getTransaction().commit();
        
        return eliminado;
	}
	
	// OPERACIONES SOBRE USUARIOS
	
	/**
	 * Permite insertar un objeto de tipo Usuarios en la base de datos.
	 * @param s sesión iniciada de Hibernate
	 * @param u el usuario a insertar
	 */
	public static void saveUsuario(Session s, Usuarios u) {
		s.beginTransaction();
		s.save(u);
		s.getTransaction().commit();
	}
	
	/**
	 * Permite modificar un objeto de tipo Usuarios en la base de datos.
	 * @param s sesión iniciada de Hibernate
	 * @param u el usuario a modificar
	 */
	public static void updateUsuario(Session s, Usuarios u) {
		s.beginTransaction();
		s.update(u);
		s.getTransaction().commit();
	}
	
	/**
	 * Devuelve una lista con todos los objetos de tipo Usuarios que se pueden obtener de la base de datos.
	 * @param s sesión iniciada de Hibernate
	 * @return lista con objetos de tipo Usuarios
	 */
	public static List<Usuarios> getAllUsuarios(Session s) {
		String hql = "FROM Usuarios"; 
        Query<Usuarios> consulta = s.createQuery(hql,Usuarios.class); 
        List<Usuarios> results = consulta.getResultList();
        return results;
	}
	
	/**
	 * Devuelve un objeto de tipo Usuarios que se corresponde con el dni pasado por parámetro.
	 * @param s sesión iniciada de Hibernate
	 * @param dni identificador del usuario
	 * @return objeto de tipo Usuario
	 */
	public static Usuarios getUsuario(Session s, String dni) {
		Query<Usuarios> consulta = s.createQuery("FROM Usuarios WHERE dni=:dni",Usuarios.class); 
        consulta.setParameter("dni",dni);
        Usuarios usuario = null;
        try {
        	usuario = consulta.getSingleResult();
        } catch (NoResultException e) {
        	System.out.println(e.getMessage());
        }
        return usuario;
	}
	
	/**
	 * Permite eliminar de la base de datos el usuario que se corresponda con el dni pasado por parámetro.
	 * @param s sesión iniciada de Hibernate
	 * @param dni identificador del usuario
	 * @return booleano que indica si se ha producido o no la eliminación
	 */
	public static boolean deleteUsuario(Session s, String dni) {
		boolean eliminado = false;
		
		//Previamente comprobar que existe el dni del usuario a eliminar
		s.beginTransaction();
		Usuarios eliminar = s.get(Usuarios.class,dni);
        if (eliminar != null){
            s.delete(eliminar);
            eliminado = true;
        }
        s.getTransaction().commit();
        
        return eliminado;
	}

}
