package biblioteca;

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

public class Biblioteca {

	private List<Libro> listL;
	private File f = new File("libros.dat");
	public Biblioteca() throws FileNotFoundException, IOException {

		this.listL = new ArrayList<Libro>();
		cargarLibros();
	}
	
	public void cargarLibros() throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		if (!f.exists() || f.length() == 0) {
			return;
		}
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f))){

			listL = (List<Libro>) ois.readObject();
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("Aviso al cargar libros: "+e.getMessage());
			}
		}

	public void guardarLibro() {
		// TODO Auto-generated method stub
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f))){
			oos.writeObject(listL);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error: " +e.getMessage());
		} 
	}
	
	public void agregarLibro(Libro nuevo) {
		// TODO Auto-generated method stub
		for(Libro l : listL) {
			if (l.getId() == nuevo.getId()) {
				System.out.println("EL libro con id "+ nuevo.getId()+ "ya existe");
				return;
			}
		}

			listL.add(nuevo);
			guardarLibro();
			System.out.println("Libro guardado");
	}

	public Libro consultarLibro(int id) {
		// TODO Auto-generated method stub
		for (int i =0; i< listL.size(); i++) {
			Libro l = listL.get(i);
			if(l.getId() == id && !l.isEliminado()) {
				return l;
			}
		}
		
		return null;
	}



	
}