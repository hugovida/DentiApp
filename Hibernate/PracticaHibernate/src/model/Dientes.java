package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * Clase que representa la tabla Dientes de la base de datos.
 */
@Entity
@Table(name="dientes")
public class Dientes implements Serializable{
    //ATRIBUTOS
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
	
	// Relacionamos con la tabla pacientes
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "dni_paciente")
    private Pacientes paciente;

    @Column(name="num_diente")
    private int num_diente;
    
    @Column(name="notas")
    private String notas;
    
    //CONSTRUCTORES

    public Dientes() {
    }
    
    public Dientes(int num_diente, String notas) {
		this.num_diente = num_diente;
		this.notas = notas;
	}

	//GETTERS
    
    public int getId() {
		return id;
	}

    public Pacientes getPaciente() {
		return paciente;
	}

	public int getNum_diente() {
        return num_diente;
    }

	public String getNotas() {
        return notas;
    }
    
    //SETTERS

	public void setId(int id) {
		this.id = id;
	}
	
	public void setPaciente(Pacientes paciente) {
		this.paciente = paciente;
	}

	public void setNum_diente(int num_diente) {
        this.num_diente = num_diente;
    }

	public void setNotas(String notas) {
        this.notas = notas;
    }
    
    
}
