package biblioteca;

import java.util.Scanner;

public class Main {
	
	
	public static void main(String[]args) {
	Scanner teclado = new Scanner(System.in);
	int op = 0;
	do {
	
		System.out.print("Agregar libro");
		System.out.println("Cargar libro");
		System.out.println("Salir");
		
		switch(op) {
		case 1:
				System.out.println(Biblioteca, teclado);
			break;
		case 2:
			
		break;
		
		case 3:
		
		break;
		}
		
	}while (op != 3);
	System.out.println("Saliendo...");
	teclado.close();
	
	
	private void Agregar(teclado, bilioteba b) {
		System.out.println("id");
		//usar el .trim para guardar el id
		int id = teclado.nextInt().trim();
		
		
		
	}
	}
}
