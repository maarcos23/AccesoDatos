package productosTienda;

import java.util.Scanner;

public class App {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        GestionInventario gestor = new GestionInventario();
        int opcion = -1;

        do {
            System.out.println("Menu");
            System.out.println("1. Listar productos");
            System.out.println("2. Agregar producto");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");

            if (!sc.hasNextInt()) {
                System.out.println("Error: introduce un número válido.");
                sc.nextLine();
                continue;
            }

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    gestor.listarProductos();
                    break;

                case 2:
                    System.out.print("Introduce ID del producto: ");
                    if (!sc.hasNextInt()) {
                        System.out.println("El ID debe ser numérico.");
                        sc.nextLine();
                        break;
                    }
                    int id = sc.nextInt();
                    sc.nextLine();

                    if (gestor.buscarProducto(id) != null) {
                        System.out.println("El producto con ID " + id + " ya existe.");
                        break;
                    }

                    System.out.print("Introduce nombre: ");
                    String nombre = sc.nextLine();

                    System.out.print("Introduce precio: ");
                    double precio = sc.nextDouble();

                    System.out.print("Introduce stock: ");
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
        } while (opcion != 0);
    }
}