package model;

public class Proveedor {
	
	private String nif;
	private String nombre;
	private int telefono;
	private String correo;
	private boolean baja;
	
	public Proveedor(String nif, String nombre, int telefono, String correo, boolean baja) {
		this.nif = nif;
		this.nombre = nombre;
		this.telefono = telefono;
		this.correo = correo;
		this.baja = baja;
	}

	public String getNif() {
		return nif;
	}

	public String getNombre() {
		return nombre;
	}

	public int getTelefono() {
		return telefono;
	}

	public String getCorreo() {
		return correo;
	}

	public boolean isBaja() {
		return baja;
	}

}
