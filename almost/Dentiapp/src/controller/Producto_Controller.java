package controller;

import java.sql.*;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;

import model.Producto;

public class Producto_Controller {
    
    private MySQLConnection connection;

    public Producto_Controller(MySQLConnection connection) {
        this.connection = connection;
    }
    
    // devuelve una lista con los productos cuyo nombre coincide con el pasado por parámetro
    public ArrayList<Producto> getProducto(String nombreProd) throws SQLException {
        ArrayList<Producto> list=new ArrayList<>();
        String query="SELECT prod.*, prov.nombre "
        		+ "FROM productos prod "
        		+ "INNER JOIN proveedores prov ON prov.nif = prod.nif_proveedor "
        		+ "WHERE prod.nombre LIKE '"+nombreProd+"%"+"'";
        ResultSet rset=connection.execSelect(query);
        
        while (rset.next()){
        	int id=rset.getInt("id");
        	String nombre=rset.getString("prod.nombre");
        	String nif_proveedor=rset.getString("nif_proveedor");
        	String nom_proveedor=rset.getString("prov.nombre");
        	int stock=rset.getInt("stock");
        	boolean baja=rset.getBoolean("baja");
            Producto producto=new Producto(id,nombre,nif_proveedor,nom_proveedor,stock,baja);
            list.add(producto);
        }
        
        return list;
    }
    
    // devuelve una lista con todos los productos ordenados por stock y nombre
    public ArrayList<Producto> getProductos() throws SQLException {
        ArrayList<Producto> list=new ArrayList<>();
        String query="SELECT prod.*, prov.nombre "
        		+ "FROM productos prod "
        		+ "INNER JOIN proveedores prov ON prov.nif = prod.nif_proveedor "
        		+ "ORDER BY prod.stock DESC, prod.nombre";
        ResultSet rset=connection.execSelect(query);
        
        while (rset.next()){
        	int id=rset.getInt("id");
        	String nombre=rset.getString("prod.nombre");
        	String nif_proveedor=rset.getString("nif_proveedor");
        	String nom_proveedor=rset.getString("prov.nombre");
        	int stock=rset.getInt("stock");
        	boolean baja=rset.getBoolean("baja");
            Producto producto=new Producto(id,nombre,nif_proveedor,nom_proveedor,stock,baja);
            list.add(producto);
        }
        
        return list;
    }
    
    // devuelve una lista con todos los productos de un proveedor ordenados por nombre
    public ArrayList<Producto> getProductosByProvider(String nif_prov) throws SQLException {
        ArrayList<Producto> list=new ArrayList<>();
        String query="SELECT prod.*, prov.nombre "
        		+ "FROM productos prod "
        		+ "INNER JOIN proveedores prov ON prov.nif = prod.nif_proveedor "
        		+ "WHERE prod.nif_proveedor = '"+nif_prov+"' "
        		+ "ORDER BY prod.nombre";
        ResultSet rset=connection.execSelect(query);
        
        while (rset.next()){
        	int id=rset.getInt("id");
        	String nombre=rset.getString("prod.nombre");
        	String nif_proveedor=rset.getString("nif_proveedor");
        	String nom_proveedor=rset.getString("prov.nombre");
        	int stock=rset.getInt("stock");
        	boolean baja=rset.getBoolean("baja");
            Producto producto=new Producto(id,nombre,nif_proveedor,nom_proveedor,stock,baja);
            list.add(producto);
        }
        
        return list;
    }
    
    // devuelve el modelo pasado por parámetro relleno con la lista pasada por parámetro o vacío en caso de que la lista esté vacía
    public DefaultTableModel fillTable(DefaultTableModel model, ArrayList<Producto> lista) {
    	model.setRowCount(0);
    	
    	for (int i = 0; i < lista.size(); i++) {
			String[] row = new String[5];
			
			row[0] = String.valueOf(lista.get(i).getId());
			row[1] = lista.get(i).getNombre();
			row[2] = lista.get(i).getNom_proveedor();
			row[3] = String.valueOf(lista.get(i).getStock());
			
			boolean baja = lista.get(i).isBaja();
			
			if (baja) {
				row[4] = "SI";
			} else {
				row[4] = "NO";
			}
			
			model.addRow(row);
		}
    	
    	return model;
    }
    
    // rellena el modelo pasado por parámetro con todos los productos ordenados por stock y nombre
    public void listProducts(DefaultTableModel model) {
    	try {
    		ArrayList<Producto> productos = getProductos();
    		fillTable(model, productos);
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    // rellena el modelo pasado por parámetro con todos los productos de un proveedor ordenados por nombre
    public void listProductsByProvider(DefaultTableModel model, String nif_proveedor) {
    	try {
    		ArrayList<Producto> productos = getProductosByProvider(nif_proveedor);
    		fillTable(model, productos);
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    // rellena el modelo pasado por parámetro con los productos cuyos nombres coincidan con el pasado por parámetro
    public void findProduct(DefaultTableModel model, String nombreProd) {
    	try {
    		ArrayList<Producto> producto = getProducto(nombreProd);
    		fillTable(model, producto);
    	} catch (SQLException e) {
    		System.out.println(e.getMessage());
    	}
    }
    
    // método para insertar un producto con los campos obligatorios
    public int insertNewProducto(String nombre, String nif_proveedor) 
    		throws SQLException {
    	return connection.execInsDelUp("INSERT INTO productos (nombre, nif_proveedor) VALUES ('"+nombre+"','"+nif_proveedor+"')");
    }
    
    // método para insertar un producto con los campos obligatorios y el stock
    public int insertNewProducto(String nombre, String nif_proveedor, int stock) throws SQLException {
    	return connection.execInsDelUp("INSERT INTO productos (nombre,nif_proveedor,stock) VALUES ('"+nombre+"','"+nif_proveedor+"',"+stock+")");
    }
    
    // método para modificar el nombre de un producto
    public int modifyName(String id, String nombre) throws SQLException {
    	return connection.execInsDelUp("UPDATE productos SET nombre ='"+nombre+"' WHERE id="+id);
    }
    
    // método para modificar el proveedor de un producto
    public int modifyProvider(String id, String nif_proveedor) throws SQLException {
    	return connection.execInsDelUp("UPDATE productos SET nif_proveedor ='"+nif_proveedor+"' WHERE id="+id);
    }
    
    // método para modificar el stock de un producto
    public int modifyStock(String id, int stock) throws SQLException {
    	return connection.execInsDelUp("UPDATE productos SET stock ="+stock+" WHERE id="+id);
    }
    
    // método para modificar la baja de un producto
    public int modifyLeave(String id, boolean baja) throws SQLException {
    	return connection.execInsDelUp("UPDATE productos SET baja ="+baja+" WHERE id="+id);
    }
    
    // método para dar de baja todos los productos de un proveedor
    public int delistAllProdsFromProv(String nif_proveedor) throws SQLException {
    	return connection.execInsDelUp("UPDATE productos SET baja = true WHERE nif_proveedor='"+nif_proveedor+"'");
    }
    
}
