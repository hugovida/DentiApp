/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package banco;

/**
 *
 * @author usuario
 */
public class Cuenta {
    private int saldo;
    private String nombre;
    private String contrasena;

    public Cuenta(int saldo, String nombre, String contrasena) {
        this.saldo = saldo;
        this.nombre = nombre;
        this.contrasena = contrasena;
    }

    public int getSaldo() {
        System.out.println("LE QUEDAN "+saldo+" EUROS");
        return saldo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    public void anadirSaldo(int cantidad){
        System.out.println("TRANSACCION REALIZADA CORRECTAMENTE");
        saldo=saldo+cantidad;
    }
    
    public void retirarSaldo(int cantidad){
        if(saldo-cantidad>=0){
        System.out.println("TRANSACCION REALIZADA CORRECTAMENTE");
        saldo=saldo-cantidad;
        }
        else if(saldo<0){
            System.out.println("SALDO NEGATIVO");
        }
        else if (saldo-cantidad<0){
            System.out.println("NO PUEDES RETIRAR "+saldo+" EUROS");
        }
    }

    @Override
    public String toString() {
        return "Cuenta{" + "saldo=" + saldo + ", nombre=" + nombre + ", contrasena=" + contrasena + '}';
    }
    
}
