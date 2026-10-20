package ejemplos;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class EjemploBytes {
    public static void main(String[] args) {
        // Operación de Salida (Escritura de un byte)
        try (FileOutputStream fos = new FileOutputStream("byte.dat")) {
            fos.write(65); // Guarda el número 65 (en código ASCII representa la letra 'A')
            System.out.println("Byte guardado.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Operación de Entrada (Lectura del byte)
        try (FileInputStream fis = new FileInputStream("byte.dat")) {
            int dato = fis.read(); // Lee el byte almacenado
            System.out.println("Número leído: " + dato);
            System.out.println("Como letra es: " + (char) dato);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}