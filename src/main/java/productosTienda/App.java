package productosTienda;

import java.util.Scanner;

public class App {

    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        GestionInventario gestor = new GestionInventario();
        int opcion = -1;

        do {
            System.out.println("\n===== GESTIÓN DE TIENDA (JAXB XML) =====");
            System.out.println("1. Listar productos");
            System.out.println("2. Agregar producto");
            System.out.println("3. Buscar producto por ID");
            System.out.println("4. Actualizar cantidad de un producto");
            System.out.println("5. Actualizar precio de un producto");
            System.out.println("6. Eliminar producto");
            System.out.println("7. Calcular valor total del inventario");
            System.out.println("8. Filtrar productos por precio (mínimo o máximo)");
            System.out.println("9. Encontrar productos con stock menor a un valor");
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

                    gestor.agregarProducto(new Producto(id, nombre, precio, stock));
                    break;

                case 3:
                    System.out.print("Introduce el ID a buscar: ");
                    int idBuscar = sc.nextInt();
                    sc.nextLine();

                    Producto encontrado = gestor.buscarProducto(idBuscar);
                    if (encontrado != null) {
                        System.out.println("Producto encontrado: " + encontrado);
                    } else {
                        System.out.println("No existe ningún producto con ID " + idBuscar);
                    }
                    break;

                case 4:
                    System.out.print("ID del producto a modificar cantidad: ");
                    int idCant = sc.nextInt();
                    System.out.print("Nueva cantidad (stock): ");
                    int nuevaCant = sc.nextInt();
                    sc.nextLine();

                    gestor.actualizarCantidad(idCant, nuevaCant);
                    break;

                case 5:
                    System.out.print("ID del producto a modificar precio: ");
                    int idPrecio = sc.nextInt();
                    System.out.print("Nuevo precio: ");
                    double nuevoPrecio = sc.nextDouble();
                    sc.nextLine();

                    gestor.actualizarPrecio(idPrecio, nuevoPrecio);
                    break;

                case 6:
                    System.out.print("ID del producto a eliminar: ");
                    int idElim = sc.nextInt();
                    sc.nextLine();

                    gestor.eliminarProducto(idElim);
                    break;

                case 7:
                    double valorTotal = gestor.calcularValorTotal();
                    System.out.printf("Valor total del inventario: %.2f €%n", valorTotal);
                    break;

                case 8:
                    System.out.println("1. Filtrar por precio MÍNIMO (mayor o igual)");
                    System.out.println("2. Filtrar por precio MÁXIMO (menor o igual)");
                    System.out.print("Elige tipo de filtro: ");
                    int tipoFiltro = sc.nextInt();

                    if (tipoFiltro == 1) {
                        System.out.print("Introduce precio mínimo: ");
                        double min = sc.nextDouble();
                        sc.nextLine();
                        gestor.filtrarPorPrecioMinimo(min);
                    } else if (tipoFiltro == 2) {
                        System.out.print("Introduce precio máximo: ");
                        double max = sc.nextDouble();
                        sc.nextLine();
                        gestor.filtrarPorPrecioMaximo(max);
                    } else {
                        sc.nextLine();
                        System.out.println("Opción de filtro no válida.");
                    }
                    break;

                case 9:
                    System.out.print("Mostrar productos con stock menor a: ");
                    int limite = sc.nextInt();
                    sc.nextLine();
                    gestor.filtrarPorStockMenor(limite);
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