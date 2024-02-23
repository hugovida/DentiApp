package model;

public class Usuario {
	
	private String dni;
	private String password;
	private boolean admin;
	private boolean desactivado;
	
	public Usuario(String dni, String password, boolean admin, boolean desactivado) {
		this.dni = dni;
		this.password = password;
		this.admin = admin;
		this.desactivado = desactivado;
	}

	public String getDni() {
		return dni;
	}

	public String getPassword() {
		return password;
	}

	public boolean isAdmin() {
		return admin;
	}

	public boolean isDesactivado() {
		return desactivado;
	}

}
