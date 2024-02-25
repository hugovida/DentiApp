package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * Clase que representa la tabla Especialidades de la base de datos.
 */
@Entity
@Table(name="especialidades")
public class Especialidades implements Serializable{
    //ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="nombre")
    private String nombre;
    
    //CONSTRUCTORES

    public Especialidades() {
    }

    public Especialidades(String nombre) {
        this.nombre = nombre;
    }
    
    //GETTERS

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
    
    //SETTERS

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    // Relacionamos con la tabla doctores
    @ManyToMany(mappedBy = "especialidades")
    private List<Doctores> doctores;

    public List<Doctores> getDoctores() {
		return doctores;
	}

	public void setDoctores(List<Doctores> doctores) {
		this.doctores = doctores;
	}

	public void addDoctor(Doctores d) {
		if (doctores == null) {
        	doctores=new ArrayList<Doctores>();
        }
        doctores.add(d);
        if (d.getEspecialidades() == null) {
        	d.setEspecialidades(new ArrayList<Especialidades>());
        }
        d.getEspecialidades().add(this);
    }
	
	// Relacionamos con la tabla tratamientos
    @OneToMany(mappedBy = "especialidad", cascade = CascadeType.ALL)
    private List<Tratamientos> tratamientos;

	public List<Tratamientos> getTratamientos() {
		return tratamientos;
	}

	public void setTratamientos(List<Tratamientos> tratamientos) {
		this.tratamientos = tratamientos;
	}

	public void addTratamiento(Tratamientos t){
        if (tratamientos == null) {
        	tratamientos=new ArrayList<Tratamientos>();
        }
        tratamientos.add(t);
        t.setEspecialidad(this);
    }
    
}
