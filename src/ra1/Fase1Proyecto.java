package ra1;

import java.io.*;
import java.util.Scanner;

public class Fase1Proyecto {

	static Scanner sc = new Scanner(System.in);
	
	static String fichero = "C:\\Users\\Tarde\\Downloads\\registros.dat";
	static final int LONG_NOM = 20;
	static final int TID = 4;
	static final int TAM_NOM = LONG_NOM * 2; // 20 caracteres * 2 bytes = 40 bytes
	static final float TNOTA = 4;
	static final int TF = 48; // Tamaño total del registro (4 + 40 + 4)
	
	public static void main(String[] args) {
		
		int op;
		
		do {
			System.out.println("\nREGISTRO DE ESTUDIANTES");
			System.out.println("1: Escribir registro");
			System.out.println("2: Leer registro");
			System.out.println("3: Actualizar nota de un estudiante");
			System.out.println("4: Mostrar todos los registros");
			System.out.println("5: Salir del programa");
			System.out.print("Elige una opción: ");
			op = sc.nextInt();
			
			switch(op) {
			case 1:
				escribir();
				break;
			case 2:
				lectura();
				break;
			case 3:
				actualizarNota();
				break;
			case 4:
				mostrarTodos();
				break;
			case 5:
				System.out.println("Saliendo del programa...");
				System.exit(0);
				break;
			default:
				System.out.println("Opción no válida.");
			}
		} while (op != 5);
	}

	public static void escribir() {	
		int id;
		String nom;
		float nota;
		
		do {
			System.out.print("Id del estudiante: ");
			id = sc.nextInt();
			if (id <= 0) {
				System.out.println("Error, el id tiene que ser un número positivo");
			}
		} while (id <= 0);
		sc.nextLine(); 
		
		System.out.print("Nombre del estudiante: ");
		nom = sc.nextLine();				

		do {
			System.out.print("Nota del estudiante: ");
			nota = sc.nextFloat();
			if (nota < 0.0 || nota > 10.0) {
				System.out.println("Error, la nota tiene que ser entre 0 y 10");
			}
		} while (nota < 0.0 || nota > 10.0);
		
		try (RandomAccessFile archivo = new RandomAccessFile(fichero, "rw")) {
			long pos = (long) id * TF;
			archivo.seek(pos);
			
			archivo.writeInt(id);
			
			StringBuilder sb = new StringBuilder(nom);
			if (sb.length() > LONG_NOM) {
				sb.setLength(LONG_NOM);
			} else {
				while (sb.length() < LONG_NOM) {
					sb.append(" ");
				}
			}

			for (int i = 0; i < LONG_NOM; i++) {
				archivo.writeChar(sb.charAt(i));
			}

			archivo.writeFloat(nota);
			
			System.out.println("Registro guardado con éxito.");
			
		} catch (IOException e) {
			System.out.println("Error al escribir el registro: " + e.getMessage());
		}
	}

	public static void lectura() {
		System.out.print("Introduce el ID del estudiante a leer: ");
		int id = sc.nextInt();
		
		File f = new File(fichero);
		if (!f.exists()) {
			System.out.println("Registro no encontrado.");
			return;
		}
		
		try (RandomAccessFile archivo = new RandomAccessFile(f, "r")) {
			long pos = (long) id * TF;		
	
			if (pos < 0 || pos + TF > archivo.length()) {
				System.out.println("Registro no encontrado.");
				return;
			}
			
			archivo.seek(pos);

			int idL = archivo.readInt();
			if (idL == 0) {
				System.out.println("Registro no encontrado.");
				return;
			}

			char[] carac = new char[LONG_NOM];
			for (int i = 0; i < LONG_NOM; i++) {
				carac[i] = archivo.readChar();
			}
			String nomL = new String(carac).trim();

			float nL = archivo.readFloat();
			
			System.out.println("El estudiante con el ID: " + idL +", de nombre " + nomL + " tiene un: " + nL);
			
		} catch (IOException e) {
			System.out.println("Error al leer el registro: " + e.getMessage());
		}
	}

	public static void actualizarNota() {
		System.out.print("Introduce el ID del estudiante a actualizar: ");
		int id = sc.nextInt();

		float nuevaNota;
		do {
			System.out.print("Introduce la nueva nota (0 a 10): ");
			nuevaNota = sc.nextFloat();
			if (nuevaNota < 0.0 || nuevaNota > 10.0) {
				System.out.println("Error, la nota tiene que ser entre 0 y 10");
			}
		} while (nuevaNota < 0.0 || nuevaNota > 10.0);

		File f = new File(fichero);
		if (!f.exists()) {
			System.out.println("Registro no encontrado");
			return;
		}

		try (RandomAccessFile archivo = new RandomAccessFile(f, "rw")) {
			long pos = (long) id * TF + 44;

			if (pos >= archivo.length() || pos < 0) {
				System.out.println("Registro no encontrado");
				return;
			}

			archivo.seek(pos);
			archivo.writeFloat(nuevaNota);

			System.out.println("Nota actualizada con éxito");

		} catch (IOException e) {
			System.out.println("Error al actualizar la nota: " + e.getMessage());
		}
	}

	public static void mostrarTodos() {
		File f = new File(fichero);
		if (!f.exists() || f.length() == 0) {
			System.out.println("No hay registros disponibles.");
			return;
		}

		try (RandomAccessFile archivo = new RandomAccessFile(f, "r")) {
			archivo.seek(0);

			while (archivo.getFilePointer() < archivo.length()) {
				int id = archivo.readInt();

				char[] carac = new char[LONG_NOM];
				for (int i = 0; i < LONG_NOM; i++) {
					carac[i] = archivo.readChar();
				}
				String nombre = new String(carac).trim();

				float nota = archivo.readFloat();

				if (id != 0) {
					System.out.println("Estudiante " + id + ", de nombre " + nombre + " calificado con un " + nota);
				}
			}

		} catch (IOException e) {
			System.out.println("Error al mostrar los registros: " + e.getMessage());
		}
	}
}