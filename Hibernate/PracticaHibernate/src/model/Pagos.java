package model;

import java.sql.Date;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * Clase que representa la tabla Pagos de la base de datos.
 */
@Entity
@Table(name="pagos")
public class Pagos implements Serializable {
    //ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    // Relacionamos con la tabla pacientes
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "dni_paciente")
    private Pacientes paciente;
    
    @Column(name="mensualidad")
    private Integer mensualidad;
    
    @Column(name="tipo")
    private Integer tipo;
    
    @Column(name="importe")
    private double importe;
    
    @Column(name="fecha_registro")
    private Date fecha_registro;
    
    @Column(name="fecha_pago")
    private Date fecha_pago;
    
    //CONSTRUCTORES

    public Pagos() {
    }
    
    public Pagos(Integer mensualidad, Integer tipo, double importe, Date fecha_registro,
			Date fecha_pago) {
		this.mensualidad = mensualidad;
		this.tipo = tipo;
		this.importe = importe;
		this.fecha_registro = fecha_registro;
		this.fecha_pago = fecha_pago;
	}

	//GETTERS

    public int getId() {
        return id;
    }
    
    public Pacientes getPaciente() {
		return paciente;
	}

    public Integer getMensualidad() {
        return mensualidad;
    }

    public Integer getTipo() {
        return tipo;
    }

    public double getImporte() {
        return importe;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public Date getFecha_pago() {
        return fecha_pago;
    }
    
    //SETTERS

    public void setId(int id) {
        this.id = id;
    }
    
    public void setPaciente(Pacientes paciente) {
		this.paciente = paciente;
	}

    public void setMensualidad(Integer mensualidad) {
        this.mensualidad = mensualidad;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public void setFecha_pago(Date fecha_pago) {
        this.fecha_pago = fecha_pago;
    }
    
}
