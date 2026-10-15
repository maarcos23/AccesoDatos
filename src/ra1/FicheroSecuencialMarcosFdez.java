package ra1;

import java.io.*;
import java.util.Scanner;

public class FicheroSecuencialMarcosFdez {

    private final static int MIN = 0;
    private final static int MAX = 100;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        String respuesta;

        do {
            int contador = 0;
            int n = (int) (Math.random() * (MAX - MIN + 1)) + MIN;
            int num;

            // Bucle para adivinar el número
            do {
                System.out.println("¿Qué número crees que ha salido?");
                num = sc.nextInt();
                contador++;

                if (num > n) {
                    System.out.println("El número que has escrito es mayor");
                } else if (num < n) {
                    System.out.println("El número que has escrito es menor");
                }

            } while (num != n);

            System.out.println("¡Has acertado!");
            System.out.println("Número de intentos: " + contador);

            
            record(contador);

            // Preguntar si quiere jugar otra vez
            System.out.println("¿Quieres volver a jugar? (s/n)");
            respuesta = sc.next();

        } while (respuesta.equalsIgnoreCase("s"));

        System.out.println("Fin del juego.");
    }

    // Método que lee compara y actualiza record.txt
    public static void record(int intentos) {
        File f = new File("record.txt"); 
        int recordA = -1; // -1 significa que no hay récord previo
        if (f.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(f))) {
                String linea = br.readLine();
                if (linea != null) {
                    recordA = Integer.parseInt(linea.trim());
                }
            } catch (IOException e) {
                System.out.println("Error al leer el récord anterior.");
            }
        }
        if (recordA == -1) {
            System.out.println("¡Primer récord registrado con " + intentos + " intentos!");
            guardarEnFichero(f, intentos);
        } else if (intentos < recordA) {
            System.out.println("¡NUEVO RÉCORD! Has superado el récord anterior de " + recordA + " intentos.");
            guardarEnFichero(f, intentos);
        } else {
            System.out.println("No has superado el récord. El récord actual sigue siendo de: " + recordA + " intentos.");
        }
    }

    public static void guardarEnFichero(File f, int nuevoRecord) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
            bw.write(String.valueOf(nuevoRecord));
        } catch (IOException e) {
            System.out.println("Error al guardar el nuevo récord.");
        }
    }
}