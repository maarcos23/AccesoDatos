package biblioteca;

import java.io.*;

public class Libro implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String titulo;
	private String autor;
	private double precio;
	private boolean eliminado;
	
	public Libro(int id, String titulo, String autor, double precio) {
		// TODO Auto-generated constructor stub
	
		this.id= id;
		this.titulo=titulo;
		this.autor=autor;
		this.precio=precio;
		this.eliminado=false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public boolean isEliminado() {
		return eliminado;
	}

	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}

	@Override
	public String toString() {
		return "Main [id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", precio=" + precio + ", eliminado="
				+ eliminado + "]";
	}
}