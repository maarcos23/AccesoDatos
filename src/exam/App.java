package exam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

import javax.naming.ldap.LdapName;

public class App {
	static Scanner sc = new Scanner(System.in);
	static GestionContactos gc = new GestionContactos();
	
	static File f = new File ("productos.dat");
	private static int TCOD = 4; //Tamaño del codigo
	private static int LNOMBRE = 25; //Tamañp del nombre
	private static int TNOMBRE = LNOMBRE * 2; //Total de espacio del nombre
	private static int TPRECIO = 8; //tamaño del precio
	private static int TAM_REGISTRO = TNOMBRE + TCOD + TPRECIO; //Tamaño total = 62
	public static void main(String[]args) {
		int op;
		do {
	System.out.println("Menu");
	System.out.println(" 1. Escribir producto");
	System.out.println(" 2. Leer producto");
	System.out.println(" 3. Actualizar precio");
	System.out.println(" 4. Mostrar todos los productos");
	System.out.println(" 5. Agregar contacto");
	System.out.println(" 6. Listar contactos");
	System.out.println(" 7. Eliminar contacto");
	System.out.println(" 0. Salir");
	System.out.println("Elige una opcion: ");
	op = sc.nextInt();
	
	switch (op) {
	
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
		System.out.println("id del contacto: ");
		int id = sc.nextInt();
		sc.nextLine();
		System.out.println("Nombre del contacto: ");
		String nombre = sc.nextLine();
		System.out.println("Telefono del contacto: ");
		long tlf = sc.nextLong();
		sc.nextLine();
		Contactos nuevo = new Contactos(id, nombre, tlf);
		gc.agregar(nuevo);
		break;
	case 6:
		gc.lista();
		break;
	case 7:
		gc.eliminar();
		break;
	case 0:
		System.out.println("Adios");
	
	}
		}while (op != 0);

	}
	
	private static void escribir() {
		// TODO Auto-generated method stub
		
		int codigo;
		String nombre;
		double precio;
		
			System.out.println("Codido del producto: ");
		codigo = sc.nextInt();
		sc.nextLine();
			System.out.println("Nombre del producto: ");
		nombre = sc.nextLine();
			System.out.println("Precio del producto: ");
		precio = sc.nextDouble();
	
		try(RandomAccessFile raf = new RandomAccessFile(f, "rw")){
			long pos = (long) codigo* TAM_REGISTRO;
			
			raf.seek(pos);
			raf.writeInt(codigo);
			
		StringBuilder sb = new StringBuilder(nombre);
		if(sb.length()> LNOMBRE) {
			sb.setLength(LNOMBRE);
		}else {
			while(sb.length()< LNOMBRE) {
				sb.append(" ");
			}
		}
		for(int i =0; i< sb.length(); i++) {
			raf.writeChar(sb.charAt(i));
		}
		
		raf.writeDouble(precio);
		System.out.println("Producto guardado. Longitud del fichero: "+ raf.length()+ " bytes");
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static void leer() {
		// TODO Auto-generated method stub
		int cod;
		char[] nom = new char[LNOMBRE];
		double precio;
		
		if(!f.exists()) {
			System.out.println("fichero vacio");
			return;
		}
			System.out.println("Codigo del producto: ");
		cod = sc.nextInt();
		sc.nextLine();
		try(RandomAccessFile raf = new RandomAccessFile(f, "r")){
			
			long pos = (long) cod* TAM_REGISTRO;
			raf.seek(pos);
			if(pos < 0 || pos +TAM_REGISTRO > raf.length()) {
				System.out.println("Producto no encontrado");
				return;
			}
			cod= raf.readInt();
			if(cod ==0) {
				System.out.println("Producto no encontrado");
				return;
			}
			for(int i =0; i< LNOMBRE; i++) {
				nom[i] = raf.readChar();
			}
			String nomb = new String(nom).trim();
			precio= raf.readDouble();
			System.out.println("Producto "+cod+ ": "+ nomb+ " · "+ precio);
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	private static void actualizar() {
		// TODO Auto-generated method stub
		System.out.println("Codigo a actualizar: ");
		int cod = sc.nextInt();
		sc.nextLine();
		
		try(RandomAccessFile raf = new RandomAccessFile(f, "rw")){
		long pos = (long) cod* TAM_REGISTRO;
		raf.seek(pos);
		
		if(pos < 0 || pos +TAM_REGISTRO > raf.length()) {
			System.out.println("Producto no encontrado");
			return;	
			}
		System.out.println("Indica el nuevo precio: ");
		double prec = sc.nextDouble();
		raf.seek(pos + TNOMBRE + TCOD);
		raf.writeDouble(prec);
		System.out.println("Productoa ctualizado");
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	private static void mostrar() {
	    if (!f.exists() || f.length() == 0) {
	        System.out.println("Fichero vacío.");
	        return;
	    }
	        
	    try (RandomAccessFile raf = new RandomAccessFile(f, "r")) {
	        // 1. Empezar desde el inicio absoluto del archivo
	        raf.seek(0);
	        
	        char[] nom = new char[LNOMBRE];
	        
	        // 2. Recorrer mientras quede al menos un registro entero por leer
	        while (raf.getFilePointer() + TAM_REGISTRO <= raf.length()) {
	            int cod = raf.readInt();
	            
	            // 3. Leer exactamente los caracteres del nombre
	            for (int i = 0; i < LNOMBRE; i++) {
	                nom[i] = raf.readChar();
	            }
	            String nomb = new String(nom).trim();
	            
	            double precio = raf.readDouble();
	            
	            // 4. Omitir los huecos no inicializados (cod == 0)
	            if (cod != 0) {
	                System.out.println("Producto " + cod + ": " + nomb + " · " + precio + " €");
	            }
	        }
	        
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
	
}
