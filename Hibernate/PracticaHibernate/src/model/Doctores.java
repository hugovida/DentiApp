package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Clase que representa la tabla Doctores de la base de datos.
 */
@Entity
@Table(name="doctores")
public class Doctores implements Serializable {
    //ATRIBUTOS
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
	
	// Relacionamos con la tabla usuarios
	@OneToOne(cascade={CascadeType.ALL})
    @JoinColumn(name = "dni")
    private Usuarios usuario;
	
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
    @Column(name="baja")
    private boolean baja;
    
    //CONSTRUCTORES

    public Doctores() {
    }

    public Doctores(String nombre, String apellidos, String telefono, Date fecha_nacimiento,
			String correo, boolean baja) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.fecha_nacimiento = fecha_nacimiento;
		this.correo = correo;
		this.baja = baja;
	}
    
    //GETTERS

    public int getId() {
		return id;
	}
    
    public Usuarios getUsuario() {
		return usuario;
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

    public boolean getBaja() {
        return baja;
    }
    
    //SETTERS  

    public void setId(int id) {
		this.id = id;
	}
    
    public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
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

    public void setBaja(boolean baja) {
        this.baja = baja;
    }
    
	// Relacionamos con la tabla citas
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL)
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
        c.setDoctor(this);
    }
	
	// Relacionamos con la tabla especialidades
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
      name = "doctores_especialidades",
      joinColumns = {@JoinColumn(name = "id_doctor")},
      inverseJoinColumns = {@JoinColumn(name = "id_especialidad")}
    )
    private List<Especialidades> especialidades;

	public List<Especialidades> getEspecialidades() {
		return especialidades;
	}

	public void setEspecialidades(List<Especialidades> especialidades) {
		this.especialidades = especialidades;
	}

	public void addEspecialidad(Especialidades e) {
		if (especialidades == null) {
			especialidades=new ArrayList<Especialidades>();
        }
		especialidades.add(e);
		if (e.getDoctores() == null) {
			e.setDoctores(new ArrayList<Doctores>());
        }
        e.getDoctores().add(this);
    }
    
}
