package relations;

import java.sql.Date;

import controller.HibernateConnection;
import model.Doctores;
import model.Especialidades;
import model.Usuarios;

public class ManyToMany {
	
    public static void main(String[] args) {
    	HibernateConnection hc = new HibernateConnection();
    	hc.connect();
    	
    	// Creamos el doctor (con su correspondiente usuario) y las especialidades
    	Usuarios u = new Usuarios("00000000A","1234",false,false);
    	Date fecha_nacimiento = Date.valueOf("1983-09-13");
    	Doctores d = new Doctores("Felipe","Sánchez","600000000",fecha_nacimiento,
    			"felipe@gmail.com",false);
    	d.setUsuario(u);
    	
		Especialidades e1 = new Especialidades("Ortodoncia");
		Especialidades e2 = new Especialidades("Periodoncia");
		
		// Asociamos doctor y especialidades
		d.addEspecialidad(e1);
		e2.addDoctor(d);
		
		// Guardamos doctor y especialidades
		hc.getSession().beginTransaction();
		hc.getSession().save(d);
		hc.getSession().save(e1);
		hc.getSession().save(e2);
		hc.getSession().getTransaction().commit();
		
		// Consultamos las especialidades de un doctor
		Doctores doctor = hc.getSession().get(Doctores.class,1);
		for (Especialidades e:doctor.getEspecialidades()) {
			System.out.println(e.getNombre());
		}
		
		// Borramos el doctor
		hc.getSession().beginTransaction();
		Doctores doctorEliminar = hc.getSession().get(Doctores.class,1);
		hc.getSession().delete(doctorEliminar);
		hc.getSession().getTransaction().commit();
	}
}
