package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * Clase que representa la tabla Tratamientos de la base de datos.
 */
@Entity
@Table(name="tratamientos")
public class Tratamientos implements Serializable {
    //ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    @Column(name="nombre")
    private String nombre;
    
    @Column(name="precio")
    private double precio;
    
    // Relacionamos con la tabla especialidades
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "id_especialidad")
    private Especialidades especialidad;
    
    //CONSTRUCTORES

    public Tratamientos() {
    }
    
    public Tratamientos(String nombre, double precio) {
		this.nombre = nombre;
		this.precio = precio;
	}

	//GETTERS

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public double getPrecio() {
        return precio;
    }
    
    public Especialidades getEspecialidad() {
		return especialidad;
	}
    
    //SETTERS

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

	public void setEspecialidad(Especialidades especialidad) {
		this.especialidad = especialidad;
	}
    
}
