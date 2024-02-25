package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 * Clase que representa la tabla Usuarios de la base de datos.
 */
@Entity
@Table(name="usuarios")
public class Usuarios implements Serializable {
    //ATRIBUTOS
    @Id
    @Column(name="dni")
    private String dni;
    
    @Column(name="password")
    private String password;
    
    @Column(name="admin")
    private boolean admin;
    
    @Column(name="desactivado")
    private boolean desactivado;
    
    //CONSTRUCTORES

    public Usuarios() {
    }

    public Usuarios(String dni, String password, boolean admin, boolean desactivado) {
        this.dni = dni;
        this.password = password;
        this.admin = admin;
        this.desactivado = desactivado;
    }
    
    //GETTERS

    public String getDni() {
        return dni;
    }

    public String getPassword() {
        return password;
    }

    public boolean getAdmin() {
        return admin;
    }

    public boolean getDesactivado() {
        return desactivado;
    }
    
    //SETTERS

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public void setDesactivado(boolean desactivado) {
        this.desactivado = desactivado;
    }
    
    // Relacionamos con la tabla doctores
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Doctores doctor;

	public Doctores getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctores doctor) {
		this.doctor = doctor;
	}
    
}
