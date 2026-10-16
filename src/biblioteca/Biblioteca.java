package biblioteca;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {


	private List<Libro> listaL;
	private static final String FICHERO = "libros.dat";	
	
	public Biblioteca() {
		this.listaL = new ArrayList<>();
		cargarLibros();
	}

	@SuppressWarnings("unchecked")
	public void cargarLibros() {
		File file = new File(FICHERO);
		if(!file.exists() || file.length() == 0) {
			return;
		}
		
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
			listaL = (List<Libro>) ois.readObject();
		} catch(IOException | ClassNotFoundException e) {
			System.out.println("Aviso al cargar libros: " + e.getMessage());
		}
	}
	
	public void guardarLibros() {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FICHERO))){
			oos.writeObject(listaL);
		} catch(IOException e) {
			System.out.println("Error al guardar en el fichero: " + e.getMessage());
		}
	}
	
	public void agregarLibros(Libro nLibro) {
		for (Libro l : listaL) {
			if (l.getId() == nLibro.getId()) {
				System.out.println("El libro con ID " + nLibro.getId() + " ya existe y no se puede agregar");
				return;
			}
		}
		listaL.add(nLibro);
		guardarLibros();
		System.out.println("Libro guardado");
	}
	
	public Libro consultarLibro(int id) {
		for (int i = 0; i < listaL.size(); i++) {
			Libro l = listaL.get(i);
			if(l.getId() == id && !l.isEliminado()) {
				return l;
			}
		}
		return null;
	}

	// ================= OPERACIONES NUEVAS FASE 2 =================

	// Modificar el precio de un libro por su ID
	public void modificarPrecioLibro(int id, double nuevoPrecio) {
		Libro l = consultarLibro(id);
		if (l != null) {
			l.setPrecio(nuevoPrecio);
			guardarLibros();
			System.out.println("Precio modificado correctamente.");
		} else {
			System.out.println("El libro con ID " + id + " no existe en el archivo.");
		}
	}

	// Eliminar un libro (borrado lógico) por su ID
	public void eliminarLibro(int id) {
		Libro l = consultarLibro(id);
		if (l != null) {
			l.setEliminado(true);
			guardarLibros();
			System.out.println("Libro eliminado con éxito.");
		} else {
			System.out.println("No se puede eliminar el libro");
		}
	}

	// Listar todos los libros activos (que no estén eliminados)
	public void listarLibros() {
		boolean hayLibros = false;
		System.out.println("\n--- LISTA DE LIBROS ACTIVOS ---");
		for (Libro l : listaL) {
			if (!l.isEliminado()) {
				System.out.println(l.toString());
				hayLibros = true;
			}
		}
		if (!hayLibros) {
			System.out.println("No hay libros activos para mostrar.");
		}
	}
}