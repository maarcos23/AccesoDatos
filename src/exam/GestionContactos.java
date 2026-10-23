package exam;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GestionContactos {

	private List<Contactos> listC;
	static File fi = new File("contactos.dat");
	static Scanner sc = new Scanner(System.in);

	
	public GestionContactos() {
		// TODO Auto-generated constructor stub
		listC = new ArrayList<>();
		cargar();
	}

	public void cargar() {
		if(!fi.exists()) {
			System.out.println("No hay fichero aun");
		}
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fi))){
			try {
				listC = (List<Contactos>) ois.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void agregar(Contactos nuevo) {
		// TODO Auto-generated method stub
		
		for(Contactos c : listC ) {
			if(c.getId() == nuevo.getId()) {
				System.out.println("Ese id ya esta en uso");
				return;
			}
		}
		listC.add(nuevo);
		guardar();
		System.out.println("Contacto guardado");
	}
	
	public void guardar() {
		try(ObjectOutput oos = new ObjectOutputStream(new FileOutputStream(fi))){
			oos.writeObject(listC);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void lista() {
		// TODO Auto-generated method stub
		if(!fi.exists()) {
			System.out.println("No hay fichero");
			return;
		}
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fi))){
			cargar();
			for(Contactos c: listC) {
				if(!c.isEliminado() ) {
				System.out.println(c.toString());
				}
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void eliminar() {
		// TODO Auto-generated method stub
		
			
			System.out.println("Id del contacto a eliminar");
			int id = sc.nextInt();
			sc.nextLine();
			for(Contactos c : listC) {
				if(c.getId() == id && !c.isEliminado()) {
					c.setEliminado(true);
					guardar();
					System.out.println("Contacto eliminado");
				return;			
			}		
		}	
	}
}
