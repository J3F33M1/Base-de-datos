import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * DAO para clientes.
 */
public class ClienteDAO {

    private static final String RUTA = "datos/clientes.csv";

    public void agregar(Cliente cliente) throws IOException {
        if (buscarPorId(cliente.getIdCliente()) != null) {
            throw new IllegalArgumentException("Ya existe un cliente con ID: " + cliente.getIdCliente());
        }
        GestorCSV.agregarLinea(RUTA, Cliente.ENCABEZADO, cliente.aCSV());
        System.out.println("Cliente agregado.");
    }

    public Cliente buscarPorId(int id) throws IOException {
        List<String> lineas = GestorCSV.leerLineas(RUTA, Cliente.ENCABEZADO);
        for (String linea : lineas) {
            try {
                Cliente c = Cliente.desdeCSV(linea);
                if (c.getIdCliente() == id) return c;
            } catch (Exception e) {
                System.err.println("Linea omitida en clientes: " + linea);
            }
        }
        return null;
    }

    public List<Cliente> obtenerTodos() throws IOException {
        List<Cliente> lista = new ArrayList<>();
        List<String> lineas = GestorCSV.leerLineas(RUTA, Cliente.ENCABEZADO);
        for (String linea : lineas) {
            try {
                lista.add(Cliente.desdeCSV(linea));
            } catch (Exception e) {
                System.err.println("Linea omitida en clientes: " + linea);
            }
        }
        return lista;
    }

    public void editar(Cliente actualizado) throws IOException {
        List<String> lineas = GestorCSV.leerLineas(RUTA, Cliente.ENCABEZADO);
        List<String> nuevasLineas = new ArrayList<>();
        boolean encontrado = false;

        for (String linea : lineas) {
            try {
                Cliente c = Cliente.desdeCSV(linea);
                if (c.getIdCliente() == actualizado.getIdCliente()) {
                    nuevasLineas.add(actualizado.aCSV());
                    encontrado = true;
                } else {
                    nuevasLineas.add(linea);
                }
            } catch (Exception e) {
                nuevasLineas.add(linea);
            }
        }

        if (!encontrado) {
            throw new NoSuchElementException("No se encontro cliente con ID: " + actualizado.getIdCliente());
        }

        GestorCSV.escribirLineas(RUTA, Cliente.ENCABEZADO, nuevasLineas);
        System.out.println("Cliente actualizado.");
    }

    public void eliminar(int id) throws IOException {
        List<String> lineas = GestorCSV.leerLineas(RUTA, Cliente.ENCABEZADO);
        List<String> nuevasLineas = new ArrayList<>();
        boolean encontrado = false;

        for (String linea : lineas) {
            try {
                Cliente c = Cliente.desdeCSV(linea);
                if (c.getIdCliente() == id) {
                    encontrado = true;
                } else {
                    nuevasLineas.add(linea);
                }
            } catch (Exception e) {
                nuevasLineas.add(linea);
            }
        }

        if (!encontrado) {
            throw new NoSuchElementException("No se encontro cliente con ID: " + id);
        }

        GestorCSV.escribirLineas(RUTA, Cliente.ENCABEZADO, nuevasLineas);
        System.out.println("Cliente eliminado.");
    }
}
