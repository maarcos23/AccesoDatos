package ejemplos;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class EjemploCaracteres {
    public static void main(String[] args) {
        // Operación de Salida (Escritura de texto)
        try (FileWriter fw = new FileWriter("texto.txt")) {
            fw.write("Hola"); // Escribe la palabra directamente en el fichero
            System.out.println("Texto guardado.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Operación de Entrada (Lectura carácter a carácter)
        try (FileReader fr = new FileReader("texto.txt")) {
            int letra;
            // Lee secuencialmente hasta que finaliza el archivo (devuelve -1)
            while ((letra = fr.read()) != -1) {
                System.out.print((char) letra);
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}