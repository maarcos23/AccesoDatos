package ejemplos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class EjemploBuffer {
    public static void main(String[] args) {
        // Operación de Salida (Escritura con buffer)
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("frases.txt"))) {
            bw.write("Primera frase");
            bw.newLine(); // Salto de línea independiente del sistema operativo
            bw.write("Segunda frase");
            System.out.println("Líneas guardadas.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Operación de Entrada (Lectura línea a línea con buffer)
        try (BufferedReader br = new BufferedReader(new FileReader("frases.txt"))) {
            String linea;
            // Lee líneas completas hasta que no queden más (devuelve null)
            while ((linea = br.readLine()) != null) {
                System.out.println("Leído: " + linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}