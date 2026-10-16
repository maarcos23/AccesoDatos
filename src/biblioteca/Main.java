package biblioteca;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

	private static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		// Lista de bibliotecas para cumplir con el enunciado
		List<Biblioteca> listaBibliotecas = new ArrayList<>();
		Biblioteca biblioteca = new Biblioteca();
		listaBibliotecas.add(biblioteca);
		
		int op = 0;
		
		do {
			System.out.println("1: Agregar nuevo libro");
			System.out.println("2: Buscar libro");
			System.out.println("3: Modificar libro");
			System.out.println("4: Eliminar libro");
			System.out.println("5: Listar libros");
			System.out.println("6: Salir");
			System.out.println("Seleccione una opción: ");
			
			if(sc.hasNextInt()) {
				op = sc.nextInt();
				sc.nextLine();
			} else {
				System.out.println("Error, introduce un numero entre el 1 y el 6");
				sc.nextLine();
				continue;
			}
			
			switch(op) {
			case 1:
				System.out.println("Introduce el ID del libro: ");
				int id = sc.nextInt();
				sc.nextLine();
				
				if(biblioteca.consultarLibro(id) != null) {
					System.out.println("Ya existe un libro con el ID: " + id);
					break;
				}
				
				System.out.println("Introduce el titulo del libro: ");
				String titulo = sc.nextLine();
				
				System.out.println("Introduce el Autor: ");
				String autor = sc.nextLine();
				
				System.out.println("Introduce el precio: ");
				double precio = sc.nextDouble();
				sc.nextLine();
				
				Libro nuevo = new Libro(id, titulo, autor, precio);
				biblioteca.agregarLibros(nuevo);
				break;
			
			case 2:
				System.out.println("Id del libro que buscas: ");
				int idB = sc.nextInt();
				sc.nextLine();
				
				Libro libroE = biblioteca.consultarLibro(idB);
				if(libroE != null) {
					System.out.println("Libro encontrado: " + libroE.toString());
				} else {
					System.out.println("El libro que buscas no existe");
				}
				break;

			case 3:
				System.out.println("Introduce el ID del libro a modificar: ");
				int idMod = sc.nextInt();
				sc.nextLine();

				if(biblioteca.consultarLibro(idMod) == null) {
					System.out.println("El libro con ID " + idMod + " no existe");
					break;
				}

				System.out.println("Introduce el nuevo precio: ");
				double nuevoPrecio = sc.nextDouble();
				sc.nextLine();

				biblioteca.modificarPrecioLibro(idMod, nuevoPrecio);
				break;

			case 4:
				System.out.println("Introduce el ID del libro a eliminar: ");
				int idElim = sc.nextInt();
				sc.nextLine();

				biblioteca.eliminarLibro(idElim);
				break;

			case 5:
				biblioteca.listarLibros();
				break;
			
			case 6:
				System.out.println("Saliendo...");
				break;
			
			default: 
				System.out.println("Opcion no valida, prueba otra vez");
			}
		} while(op != 6);	
	}
}