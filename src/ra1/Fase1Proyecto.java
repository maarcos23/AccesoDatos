package ra1;

import java.io.*;
import java.util.Scanner;

public class Fase1Proyecto {

	static String f = "registros.dat";
	static  Scanner sc = new Scanner(System.in);
	//longitud nombre
	static final int LONG_NOM = 20;
	//tamaño id
	static final int TID= 4;
	//total nombre
	static final int TNOM= LONG_NOM *2;
	//tamaño nota
	static final float NOTA = 4;
	//tamaño total
	static final int TOTAL = 48;

	public static void main(String[] args) {

		int opcion;
		do {
		System.out.println("Menu de opciones");
		System.out.println("1: Escribir registro: ");
		System.out.println("2: Leer registro: ");
		System.out.println("3: actualizar nota");
		System.out.println("4: mostrar todos los registros");
		System.out.println("5: salir");
		System.out.println("Elige una respuesta: ");
		opcion = sc.nextInt();
		
		switch(opcion){
		case 1:
			escribir();
			break;
		case 2:
			leer();
			break;
		case 3:
			actualizar();
			break;
		case 4: 
			mostrar();
			break;
		case 5:
			System.out.println("Adios");
	}
		}while(opcion != 5);
	}
	public static void escribir() {
		int id;
		String nombre;
		float nota;
		
		do {
		System.out.println("Id del alumno: ");
		id = sc.nextInt();
		if(id < 0) {
			System.out.println("Error el numero tiene q ser positivo");
		}
		}while(id < 0);
		
		sc.nextLine();
		
			System.out.println("Nombre del estudiante");
			nombre = sc.nextLine();
			
		do {
			System.out.println("Nota del alumno: ");
			nota = sc.nextFloat();
				if(nota <0.0 || nota > 10.0) {
					System.out.println("Error la nota tiene q ser entre 0 y 10");
				}
		}while (nota <0.0 || nota > 10.0);
		
		try(RandomAccessFile archivo = new RandomAccessFile(f, "rw")){
			
			long pos = (long)id * TOTAL;
			archivo.seek(pos);
			archivo.writeInt(id);
			
			StringBuilder sb = new StringBuilder(nombre);
				if(sb.length() > LONG_NOM ) {
					sb.setLength(LONG_NOM);
				}else {
					while (sb.length() < LONG_NOM) {
					sb.append(" ");
					}
				}
				for(int i = 0; i < sb.length(); i++) {
					archivo.writeChar(sb.charAt(i));
				}
			
			archivo.writeFloat(nota);
			System.out.println("todo guardado");
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public static void leer() {
		
		int id;
		char[] nombre = new char[20];
		float nota;
		
		System.out.println("Id del alumno");
		id = sc.nextInt();
		
		File fi = new File(f);
		if(!fi.exists()) {
			System.out.println("Registro no encontrado");
			return;
		}
		try(RandomAccessFile raf = new RandomAccessFile(fi, "r")){
			
			long pos = id * TOTAL;
			if(pos < 0 || pos + TOTAL > raf.length()) {
				System.out.println("Archivo no encontrado");
				return;
			}
			raf.seek(pos);
			
			int idL = raf.readInt();
			if (idL == 0) {
				System.out.println("Archivo no encontrado");
				return;
			}
			
			for(int i= 0; i< LONG_NOM; i++) {
				nombre[i] = raf.readChar();
			}
			String nom = new String(nombre).trim();
			
			nota = raf.readFloat();
			
			System.out.println("El estudiante con id: "+idL+", de nombre :"+
			nom+", tiene un: "+nota);				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void actualizar() {
		
		int id;
		System.out.println("Id del alumno");
		id = sc.nextInt();
		
		File fi = new File(f);
		if(!fi.exists()) {
			System.out.println("Registro no encontrado");
			return;
		}
		
		try(RandomAccessFile raf = new RandomAccessFile(f, "rw")){
			
			long pos = id *TOTAL;
			if(pos < 0 || pos + TOTAL > f.length()) {
				System.out.println("Archivo no encontrado");
			}
			raf.seek(pos+44);
			float nota = raf.readFloat();
				System.out.println("Nota actual: "+ nota);
				System.out.println("Escriba la nota nueva: ");
				float nnota = sc.nextFloat();
				raf.seek(pos+44);
				raf.writeFloat(nnota);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	public static void mostrar() {
		
		File fi = new File(f);
		if (!fi.exists() || f.length() == 0) {
			System.out.println("No hay registros disponibles.");
			return;
		}

		try (RandomAccessFile archivo = new RandomAccessFile(fi, "r")) {
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