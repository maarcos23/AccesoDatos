package jaxb;

import java.io.File;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class GestionInventario {

    private Tienda tienda;
    private final File fichero = new File("inventario.xml");

    public GestionInventario() {
        this.tienda = new Tienda();
        cargarXML();
    }

    // Leer el archivo XML y convertirlo a objetos (Unmarshalling)
    public void cargarXML() {
        if (!fichero.exists() || fichero.length() == 0) {
            return;
        }

        try {
            JAXBContext context = JAXBContext.newInstance(Tienda.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            this.tienda = (Tienda) unmarshaller.unmarshal(fichero);
        } catch (JAXBException e) {
            System.out.println("Aviso al leer XML: " + e.getMessage());
        }
    }

    // Convertir los objetos a XML y guardarlos en disco (Marshalling)
    public void guardarXML() {
        try {
            JAXBContext context = JAXBContext.newInstance(Tienda.class);
            Marshaller marshaller = context.createMarshaller();
            // Formatear el XML para que quede legible con sangrías
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(tienda, fichero);
        } catch (JAXBException e) {
            System.out.println("Error al guardar en XML: " + e.getMessage());
        }
    }

    // Comprobar si existe por ID
    public Producto buscarProducto(int id) {
        for (Producto p : tienda.getListaProductos()) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    // Agregar producto comprobando que el ID no esté duplicado
    public void agregarProducto(Producto nuevo) {
        if (buscarProducto(nuevo.getId()) != null) {
            System.out.println("El producto con ID " + nuevo.getId() + " ya existe y no se puede agregar.");
            return;
        }
        tienda.getListaProductos().add(nuevo);
        guardarXML();
        System.out.println("Producto guardado con éxito en inventario.xml");
    }

    // Listar todos los productos registrados
    public void listarProductos() {
        if (tienda.getListaProductos().isEmpty()) {
            System.out.println("El inventario está vacío.");
            return;
        }

        System.out.println("\n--- LISTADO DE PRODUCTOS ---");
        for (Producto p : tienda.getListaProductos()) {
            System.out.println(p);
        }
    }
}