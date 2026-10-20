package biblioteca;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	private static final Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Biblioteca biblioteca = new Biblioteca();
		int op;
		
		do {
		System.out.println("Menu de opciones");
		System.out.println("1: Agregar nuevo libro");
		System.out.println("2: consultar libro");
		System.out.println("3: salir");
		System.out.println("seleccione un opcion");
		op = sc.nextInt();
		
		switch(op) {
		case 1:
		
			System.out.println("Id del libro: ");
			int id = sc.nextInt();
			sc.nextLine();
			if(biblioteca.consultarLibro(id)!= null) {
				System.out.println("Ya existe un numero con ese ID, pruebe otro");
				break;
			}
			
			System.out.println("Nombre del libro: ");
			String titulo = sc.nextLine();
			System.out.println("Autor del libro: ");
			String autor = sc.nextLine();
			System.out.println("Precio del libro: ");
			double precio = sc.nextDouble();			
			sc.nextLine();
			
			Libro nuevo = new Libro(id, titulo, autor, precio);
			biblioteca.agregarLibro(nuevo);
			break;
		
		case 2:
			System.out.println("Id del libro que buscas: ");
			int idb = sc.nextInt();
			sc.nextLine();
			Libro libroB = biblioteca.consultarLibro(idb);
			if(libroB != null) {
				System.out.println("libro encontrado: "+libroB.toString());
			}else {
				System.out.println("Libro no encontrado");
			}
			break;
			
		case 3:
			System.out.println("Adios");
		}
		
		
		}while (op != 3);
		
	}
}