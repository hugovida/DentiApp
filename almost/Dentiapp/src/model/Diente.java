package model;

import java.sql.Date;

public class Diente {
	
	private int id_cita;
	private int num_diente;
	private String notas;
	private Date fecha;
	private String nom_doctor;
	private String ape_doctor;
	
	public Diente(int num_diente) {
		this.num_diente = num_diente;
	}

	public Diente(int id_cita, int num_diente, String notas) {
		super();
		this.id_cita = id_cita;
		this.num_diente = num_diente;
		this.notas = notas;
	}

	public Diente(String notas, Date fecha, String nom_doctor, String ape_doctor) {
		super();
		this.notas = notas;
		this.fecha = fecha;
		this.nom_doctor = nom_doctor;
		this.ape_doctor = ape_doctor;
	}

	public int getId_cita() {
		return id_cita;
	}

	public int getNum_diente() {
		return num_diente;
	}

	public String getNotas() {
		return notas;
	}

	public Date getFecha() {
		return fecha;
	}

	public String getNom_doctor() {
		return nom_doctor;
	}

	public String getApe_doctor() {
		return ape_doctor;
	}

}
