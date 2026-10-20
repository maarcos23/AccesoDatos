package ra1;

import java.io.*;
import java.util.Scanner;

public class FicheroSecuencialMarcosFdez {
	
	private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

    	String seguir="";
    	do {
    	int contador =0;
    	int n = (int) (Math.random()* (100 - 0 + 1)) +0;
    	int respuesta;
    	do {
	    	System.out.println("¿Que numero crees que ha salido?");
	    	respuesta = sc.nextInt();
	    	contador++;
    	
    		if (respuesta < n) {
    			System.out.println("El numero que has escrito es menor");
    			
    		}else if(respuesta > n) {
    			System.out.println("El numero que has escrito es mayor");
    		}
    	}while(respuesta != n);
    	System.out.println("El numero de intentas ha sido: "+ contador);
    	System.out.println("¿Quieres volver a jugar? s/n");
    	seguir= sc.next();
    }while(seguir.equalsIgnoreCase("s"));
    	System.out.println("Fin del juego"); 	
    }
}