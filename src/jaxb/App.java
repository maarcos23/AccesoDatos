package jaxb;

import java.util.Scanner;

public class App {

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        GestionInventario gestor = new GestionInventario();
        int op = 0;

        do {
            System.out.println("\n===== GESTIÓN DE TIENDA (JAXB) =====");
            System.out.println("1: Listar productos");
            System.out.println("2: Agregar nuevo producto");
            System.out.println("0: Salir");
            System.out.print("Seleccione una opción: ");

            if (sc.hasNextInt()) {
                op = sc.nextInt();
                sc.nextLine();
            } else {
                System.out.println("Error, introduce un número válido.");
                sc.nextLine();
                continue;
            }

            switch (op) {
                case 1:
                    gestor.listarProductos();
                    break;

                case 2:
                    System.out.print("ID del producto: ");
                    int id = sc.nextInt();
                    sc.nextLine();

                    if (gestor.buscarProducto(id) != null) {
                        System.out.println("Ya existe un producto con el ID: " + id);
                        break;
                    }

                    System.out.print("Nombre del producto: ");
                    String nombre = sc.nextLine();

                    System.out.print("Precio: ");
                    double precio = sc.nextDouble();

                    System.out.print("Stock: ");
                    int stock = sc.nextInt();
                    sc.nextLine();

                    Producto nuevo = new Producto(id, nombre, precio, stock);
                    gestor.agregarProducto(nuevo);
                    break;

                case 0:
                    System.out.println("Saliendo del programa...");
                    break;

                default:
                    System.out.println("Opción no válida.");
            }

        } while (op != 0);
    }
}
