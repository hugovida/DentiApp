package model;

public class Producto {
	
	private int id;
	private String nombre;
	private String nif_proveedor;
	private String nom_proveedor;
	private int stock;
	private boolean baja;
	
	public Producto(int id, String nombre, String nif_proveedor, String nom_proveedor, int stock, boolean baja) {
		this.id = id;
		this.nombre = nombre;
		this.nif_proveedor = nif_proveedor;
		this.nom_proveedor = nom_proveedor;
		this.stock = stock;
		this.baja = baja;
	}

	public int getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public String getNif_proveedor() {
		return nif_proveedor;
	}

	public String getNom_proveedor() {
		return nom_proveedor;
	}

	public int getStock() {
		return stock;
	}

	public boolean isBaja() {
		return baja;
	}

}
