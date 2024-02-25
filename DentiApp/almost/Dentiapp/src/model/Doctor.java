package model;

import java.sql.Date;

public class Doctor {
	
	private String dni;
	private String nombre;
	private String apellidos;
	private int telefono;
	private Date fecha_nacimiento;
	private String correo;
	private boolean baja;
	
	public Doctor(String dni, String nombre, String apellidos, int telefono, Date fecha_nacimiento, String correo,
			boolean baja) {
		super();
		this.dni = dni;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.telefono = telefono;
		this.fecha_nacimiento = fecha_nacimiento;
		this.correo = correo;
		this.baja = baja;
	}

	public String getDni() {
		return dni;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public int getTelefono() {
		return telefono;
	}

	public Date getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public String getCorreo() {
		return correo;
	}

	public boolean isBaja() {
		return baja;
	}

}
