package ejemplos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// 1. La clase debe implementar Serializable obligatoriamente
class Persona implements Serializable {
    private static final long serialVersionUID = 1L; // Identificador de versión[cite: 25, 26]
    String nombre;

    public Persona(String nombre) {
        this.nombre = nombre;
    }
}

public class EjemploObjetos {
    public static void main(String[] args) {
        Persona p1 = new Persona("Carlos");

        // Operación de Salida (Serialización)
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("persona.dat"))) { //[cite: 22, 26]
            oos.writeObject(p1); // Convierte y guarda el objeto completo en disco[cite: 22, 26]
            System.out.println("Persona guardada.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Operación de Entrada (Deserialización)
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("persona.dat"))) { //[cite: 22, 26]
            // Lee la secuencia de bytes y aplica casting a la clase Persona[cite: 22, 26]
            Persona pRecuperada = (Persona) ois.readObject(); 
            System.out.println("Nombre recuperado: " + pRecuperada.nombre);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}