package exam;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;

public class GestionProductos {

	static final int TID = 4;
	static final int LONG_NOM = 25;
	static final int TNOMBRE = LONG_NOM * 2;
	static final int TPRECIO = 8;
	static final int TAM_REGISTRO = 62;
	
	static final Scanner sc = new Scanner (System.in);
	
	File f = new File("productos.dat");
	
	public GestionProductos() {
		// TODO Auto-generated constructor stub
	}
	
	// 1.1 Escribir un producto
	public void nProducto(Productos p) {
	    try (RandomAccessFile raf = new RandomAccessFile(f, "rw")) {
	        long pos = (long) p.getCodigo() * TAM_REGISTRO;
	        raf.seek(pos);
	        
	        raf.writeInt(p.getCodigo());
	        
	        StringBuilder sb = new StringBuilder(p.getNombre());
	        if (sb.length() > LONG_NOM) {
	            sb.setLength(LONG_NOM);
	        } else {
	            while (sb.length() < LONG_NOM) {
	                sb.append(" ");
	            }
	        }
	        
	        for (int i = 0; i < LONG_NOM; i++) {
	            raf.writeChar(sb.charAt(i));
	        }
	        
	        raf.writeDouble(p.getPrecio());
	        System.out.println("Producto guardado. Longitud del fichero: " + raf.length() + " bytes");
	        
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	// 1.2 Leer un producto
	public void lProducto(int cod) {
	    if (!f.exists()) {
	        System.out.println("Producto no encontrado.");
	        return;
	    }
	    
	    char[] nombre = new char[LONG_NOM];
	    
	    try (RandomAccessFile raf = new RandomAccessFile(f, "r")) {
	        long pos = (long) cod * TAM_REGISTRO;
	        
	        if (pos < 0 || pos + TAM_REGISTRO > raf.length()) {
	            System.out.println("Producto no encontrado.");
	            return;
	        }
	        
	        raf.seek(pos);
	        int pP = raf.readInt();
	        
	        if (pP == 0) {
	            System.out.println("Producto no encontrado.");
	            return;
	        }
	        
	        for (int i = 0; i < LONG_NOM; i++) {
	            nombre[i] = raf.readChar();
	        }
	        String nom = new String(nombre).trim();
	        
	        double precio = raf.readDouble();
	        System.out.println("Producto " + pP + ": " + nom + " · " + precio + " €");
	        
	    } catch (FileNotFoundException e) {
	        System.out.println("Producto no encontrado.");
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public void aProducto(int codi, double preci) { //actualizar PRoducto
		// TODO Auto-generated method stub
		
		try(RandomAccessFile raf = new RandomAccessFile(f, "rw")){
			
			int pos = codi *TAM_REGISTRO + TID + TNOMBRE;
			raf.seek(pos);
			double nota = raf.readDouble();
			raf.seek(pos);
			raf.writeDouble(preci);
				System.out.println("Precio actualizado: "+preci);
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public void mCProductos() {	//mostrar prooductos
		// TODO Auto-generated method stub
		
		  
		if (!f.exists()) {
	        System.out.println("Producto no encontrado.");
	        return;
		}
		try(RandomAccessFile raf = new RandomAccessFile(f, "r")){
		while(raf.getFilePointer() < raf.length()) {
			raf.readChar();
		}
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	

}
