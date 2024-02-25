package model;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * Clase que representa la tabla Pacientes de la base de datos.
 */
@Entity
@Table(name="pacientes")
public class Pacientes implements Serializable{
    //ATRIBUTOS
    @Id
    @Column(name="dni")
    private String dni;
    
    @Column(name="nombre")
    private String nombre;
    
    @Column(name="apellidos")
    private String apellidos;
    
    @Column(name="telefono")
    private String telefono;
    
    @Column(name="fecha_nacimiento")
    private Date fecha_nacimiento;
    
    @Column(name="correo")
    private String correo;
     
    //CONSTRUCTORES

    public Pacientes() {
    }

    public Pacientes(String dni, String nombre, String apellidos, String telefono, Date fecha_nacimiento, String correo) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.fecha_nacimiento = fecha_nacimiento;
        this.correo = correo;
    }
       

    //GETTERS

    public String getDni() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public String getCorreo() {
        return correo;
    }
    
    //SETTERS

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}

	public void setCorreo(String correo) {
        this.correo = correo;
    }
	
	// Relacionamos con la tabla citas
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Citas> citas;
    
    public List<Citas> getCitas() {
		return citas;
	}
    
    public void setCitas(List<Citas> citas) {
		this.citas = citas;
	}

	public void addCita(Citas c){
        if (citas == null) {
        	citas=new ArrayList<Citas>();
        }
        citas.add(c);
        c.setPaciente(this);
    }
	
	// Relacionamos con la tabla pagos
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Pagos> pagos;

	public List<Pagos> getPagos() {
		return pagos;
	}

	public void setPagos(List<Pagos> pagos) {
		this.pagos = pagos;
	}

	public void addPago(Pagos p){
        if (pagos == null) {
        	pagos=new ArrayList<Pagos>();
        }
        pagos.add(p);
        p.setPaciente(this);
    }
	
	// Relacionamos con la tabla dientes
    @OneToMany(mappedBy = "paciente", cascade = CascadeType.ALL)
    private List<Dientes> dientes;

	public List<Dientes> getDientes() {
		return dientes;
	}

	public void setDientes(List<Dientes> dientes) {
		this.dientes = dientes;
	}

	public void addDiente(Dientes d){
        if (dientes == null) {
        	dientes=new ArrayList<Dientes>();
        }
        dientes.add(d);
        d.setPaciente(this);
    }
    
}
