import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Objeto de Acceso a Datos (DAO) para la entidad Cliente.
 * <p>
 * Esta clase gestiona las operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * para los clientes, persistiendo la informacion en un archivo CSV.
 * </p>
 */
public class ClienteDAO {
    // Esta es la parte de la ruta donde se guardan los CSV, verificar que funcione correctamente en el directorio o de ser necesario mas robusto
    private static final String RUTA = "Práctica02_LosSúperDatos/src/clientes.csv";

    /**
     * Agrega un nuevo cliente al archivo CSV.
     * @param cliente El objeto Cliente a agregar.
     * @throws IOException Si ocurre un error de entrada/salida al escribir en el archivo.
     * @throws IllegalArgumentException Si ya existe un cliente con el mismo ID.
     */
    public void agregar(Cliente cliente) throws IOException {
        if (buscarPorId(cliente.getIdCliente()) != null) {
            throw new IllegalArgumentException("Ya existe un cliente con ID: " + cliente.getIdCliente());
        }
        GestorCSV.agregarLinea(RUTA, Cliente.ENCABEZADO, cliente.aCSV());
        System.out.println("Cliente agregado.");
    }

    /**
     * Busca un cliente por su identificador unico.
     * @param id El ID del cliente a buscar.
     * @return El objeto Cliente si se encuentra, o null si no existe.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
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

    /**
     * Obtiene una lista de todos los clientes registrados.
     * @return Una lista de objetos Cliente.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
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

    /**
     * Actualiza la informacion de un cliente existente.
     * @param actualizado El objeto Cliente con la informacion actualizada.
     * @throws IOException Si ocurre un error al leer o escribir en el archivo.
     * @throws NoSuchElementException Si no se encuentra un cliente con el ID proporcionado.
     */
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

    /**
     * Elimina un cliente del registro por su ID.
     * @param id El ID del cliente a eliminar.
     * @throws IOException Si ocurre un error al leer o escribir en el archivo.
     * @throws NoSuchElementException Si no se encuentra un cliente con el ID proporcionado.
     */
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
