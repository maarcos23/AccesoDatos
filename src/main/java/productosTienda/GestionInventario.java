package productosTienda;

import java.io.File;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

public class GestionInventario {

    private Tienda tienda;
    private final File fichero = new File("inventario.xml");

    public GestionInventario() {
        cargarXML();
    }

    public void cargarXML() {
        if (!fichero.exists() || fichero.length() == 0) {
            this.tienda = new Tienda();
            return;
        }

        try {
            JAXBContext context = JAXBContext.newInstance(Tienda.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            this.tienda = (Tienda) unmarshaller.unmarshal(fichero);
        } catch (JAXBException e) {
            System.out.println("Aviso al leer XML: " + e.getMessage());
            this.tienda = new Tienda();
        }
    }

    public void guardarXML() {
        try {
            JAXBContext context = JAXBContext.newInstance(Tienda.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(tienda, fichero);
        } catch (JAXBException e) {
            System.out.println("Error al guardar en el XML: " + e.getMessage());
        }
    }

    public Producto buscarProducto(int id) {
        for (Producto p : tienda.getListaProductos()) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    public void agregarProducto(Producto nuevo) {
        if (buscarProducto(nuevo.getId()) != null) {
            System.out.println("El producto con ID " + nuevo.getId() + " ya existe.");
            return;
        }
        tienda.getListaProductos().add(nuevo);
        guardarXML();
        System.out.println("Producto guardado con éxito en inventario.xml");
    }

    public void listarProductos() {
        if (tienda.getListaProductos().isEmpty()) {
            System.out.println("El inventario está vacío.");
            return;
        }
        System.out.println("\n--- INVENTARIO DE LA TIENDA ---");
        for (Producto p : tienda.getListaProductos()) {
            System.out.println(p);
        }
    }
}