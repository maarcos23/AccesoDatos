package exam;

import java.io.Serializable;

public class Contactos implements Serializable{
	private static final long  serialVersionUID = 1L;

	private int id;
	private String nombre;
	private long tlf;
	private boolean eliminado;
	public Contactos(int id, String nombre, long tlf) {
<<<<<<< HEAD
		this.id=id;
		this.nombre=nombre;
		this.tlf=tlf;
		this.eliminado=false;
=======
		// TODO Auto-generated constructor stub
		
		this.id= id;
		this.nombre= nombre;
		this.tlf = tlf;
		this.eliminado= false;
>>>>>>> branch 'master' of https://github.com/maarcos23/AccesoDatos
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public long getTlf() {
		return tlf;
	}
	public void setTlf(long tlf) {
		this.tlf = tlf;
	}
	public boolean isEliminado() {
		return eliminado;
	}
	public void setEliminado(boolean eliminado) {
		this.eliminado = eliminado;
	}
	@Override
	public String toString() {
		return "Contactos [id=" + id + ", nombre=" + nombre + ", tlf=" + tlf + ", eliminado=" + eliminado + "]";
	}
	
	
	

}
