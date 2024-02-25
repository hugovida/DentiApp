package model;

import java.sql.Date;
import java.sql.Time;

public class Cita {
	
	private int id;
	private String dni_doctor;
	private String nom_doctor;
	private String ape_doctor;
	private String dni_paciente;
	private String nom_paciente;
	private String ape_paciente;
	private int id_ptrat;
	private Date fecha;
	private Time hora;
	private String notas;
	
	public Cita(int id, String dni_doctor, String nom_doctor, String ape_doctor, String dni_paciente,
			String nom_paciente, String ape_paciente, int id_ptrat, Date fecha, Time hora, String notas) {
		this.id = id;
		this.dni_doctor = dni_doctor;
		this.nom_doctor = nom_doctor;
		this.ape_doctor = ape_doctor;
		this.dni_paciente = dni_paciente;
		this.nom_paciente = nom_paciente;
		this.ape_paciente = ape_paciente;
		this.id_ptrat = id_ptrat;
		this.fecha = fecha;
		this.hora = hora;
		this.notas = notas;
	}

	public int getId() {
		return id;
	}

	public String getDni_doctor() {
		return dni_doctor;
	}

	public String getNom_doctor() {
		return nom_doctor;
	}

	public String getApe_doctor() {
		return ape_doctor;
	}

	public String getDni_paciente() {
		return dni_paciente;
	}

	public String getNom_paciente() {
		return nom_paciente;
	}

	public String getApe_paciente() {
		return ape_paciente;
	}

	public int getId_ptrat() {
		return id_ptrat;
	}

	public Date getFecha() {
		return fecha;
	}

	public Time getHora() {
		return hora;
	}

	public String getNotas() {
		return notas;
	}

}
