/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package banco;

import java.util.Scanner;

/**
 *
 * @author usuario
 */
public class Banco {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Cuenta cuenta1 = new Cuenta(100,"HUGO","12we34r5t6y");
        int cantidad,op =0;
        do{
        System.out.println("1.PARA AÑADIR,2.PARA RETIRAR,3.PARA MOSTRAR Y 4.SALIR");
        op=sc.nextInt();
        switch(op){
            case 1: System.out.println("INTRODUCE LA CANTIDAD");
                    cantidad=sc.nextInt();
                    cuenta1.anadirSaldo(cantidad);
                    break;
            case 2: System.out.println("INTRODUCE LA CANTIDAD");
                    cantidad=sc.nextInt();
                    cuenta1.retirarSaldo(cantidad);
                    break;
            case 3: System.out.println(cuenta1.toString());
                    break;
            case 4: System.out.println("SALIENDO...");
                    break;
        }    
        }while(op!=4);
    }
    
}
