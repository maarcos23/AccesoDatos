package exam;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class GestionContactos {

	File f = new File("contactos.dat");
	private List<Contactos> listC;
	
	public GestionContactos() {
		// TODO Auto-generated constructor stub
		this.listC = new ArrayList<>();
		
		cContactos();
	}

	private void cContactos() {	//cargar Contatcos
		// TODO Auto-generated method stub
		if(!f.exists() || f.length() == 0) {
			return;
		}
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))) {
	        listC = (List<Contactos>) ois.readObject();
	    } catch (IOException | ClassNotFoundException e) {
	        System.out.println("Error al cargar contactos: " + e.getMessage());
	    }
		
	}

		public void gContacto() {	//guardar Contacto
			try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f))){
				oos.writeObject(listC);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 		
		}
		
		public void nContacto(Contactos c) {	//nuevo contacto
				// TODO Auto-generated method stub
				for(Contactos co : listC) {
					if(co.getId() == c.getId()) {
						System.out.println("Ese id de contacto ya existe");
						return;
					}
				}
				listC.add(c);
				gContacto();
				System.out.println("Contacto guardado");
			
			}

		public void mContactos() {	//mostrar contactos
			// TODO Auto-generated method stub
			
			boolean hc = false;//hay contactos
				for (Contactos co: listC) {
					if (!co.isEliminado()) {
						System.out.println(co.toString());
						hc = true;
					}
				}
				if(!hc) {
					System.out.println("No hay contactos activos");
				}
			
			
		}

		public void eContactos(int idE) {	//Eliminar Contactos
			// TODO Auto-generated method stub
			for (Contactos c : listC) {
				if(c.getId() == idE && !c.isEliminado()) {
					c.setEliminado(true);
					gContacto();
					System.out.println("Contacto eliminado");
					return;
				}
			}
			System.out.println("No se ha podido eliminar");
		}
		public Contactos bId(int id) {
			for (Contactos c: listC) {
				if(c.getId() == id && !c.isEliminado()) {
					return c;
				}
			}
			return null;
		}
		
}
