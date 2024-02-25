package model;

import java.sql.Date;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Clase que representa la tabla Citas de la base de datos.
 */
@Entity
@Table(name="citas")
public class Citas implements Serializable {
    //ATRIBUTOS
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
	
	// Relacionamos con la tabla doctores
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "id_doctor")
    private Doctores doctor;
    
    // Relacionamos con la tabla pacientes
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "dni_paciente")
    private Pacientes paciente;
    
    @Column(name="fecha")
    private Date fecha;
    
    @Column(name="notas")
    private String notas;

    
    //CONSTRUCTORES
    public Citas() {
    }
     
    public Citas(Date fecha, String notas) {
		this.fecha = fecha;
		this.notas = notas;
	}

	//GETTERS
    public int getId() {
        return id;
    }

    public Doctores getDoctor() {
		return doctor;
	}

	public Pacientes getPaciente() {
		return paciente;
	}

	public Date getFecha() {
        return fecha;
    }

    public String getNotas() {
        return notas;
    }
     
    //SETTERS
    public void setId(int id) {
        this.id = id;
    }

    public void setDoctor(Doctores doctor) {
		this.doctor = doctor;
	}

	public void setPaciente(Pacientes paciente) {
		this.paciente = paciente;
	}

	public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

}
