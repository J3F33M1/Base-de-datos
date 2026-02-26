import java.io.IOException;
import java.util.*;

/**
 * Clase ClienteDAO
 * Implementa las operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * para la entidad {@link Cliente}, usando archivos CSV como mecanismo
 * de persistencia a través de {@link GestorCSV}.</p>
 */
public class ClienteDAO {

    /** Ruta del archivo CSV donde se almacenan los clientes. */
    private static final String RUTA = "datos/clientes.csv";


    /**
     * Agrega un nuevo cliente al archivo CSV.
     *
     * @param cliente Objeto {@link Cliente} a guardar.
     * @throws IOException              Si ocurre un error de escritura.
     * @throws IllegalArgumentException Si ya existe un cliente con el mismo ID.
     */
    public void agregar(Cliente cliente) throws IOException {
        if (buscarPorId(cliente.getIdCliente()) != null) {
            throw new IllegalArgumentException(
                "Ya existe un cliente con el ID: " + cliente.getIdCliente());
        }
        GestorCSV.agregarLinea(RUTA, Cliente.ENCABEZADO, cliente.aCSV());
        System.out.println("✔ Cliente agregado correctamente.");
    }


    /**
     * Busca un cliente por su ID (llave primaria).
     *
     * @param id Identificador único del cliente.
     * @return El objeto {@link Cliente} si se encuentra, o {@code null} si no existe.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public Cliente buscarPorId(int id) throws IOException {
        List<String> lineas = GestorCSV.leerLineas(RUTA, Cliente.ENCABEZADO);
        for (String linea : lineas) {
            try {
                Cliente c = Cliente.desdeCSV(linea);
                if (c.getIdCliente() == id) return c;
            } catch (Exception e) {
                System.err.println("⚠ Línea malformada en CSV de clientes, se omite: " + linea);
            }
        }
        return null;
    }

    /**
     * Obtiene todos los clientes almacenados en el archivo CSV.
     *
     * @return Lista de objetos {@link Cliente}. Vacía si no hay registros.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public List<Cliente> obtenerTodos() throws IOException {
        List<Cliente> lista = new ArrayList<>();
        List<String> lineas = GestorCSV.leerLineas(RUTA, Cliente.ENCABEZADO);
        for (String linea : lineas) {
            try {
                lista.add(Cliente.desdeCSV(linea));
            } catch (Exception e) {
                System.err.println("⚠ Línea malformada en CSV de clientes, se omite: " + linea);
            }
        }
        return lista;
    }


    /**
     * Edita los datos de un cliente existente.
     *
     * @param clienteActualizado Objeto {@link Cliente} con los nuevos datos.
     * @throws IOException            Si ocurre un error de lectura/escritura.
     * @throws NoSuchElementException Si no existe un cliente con el ID indicado.
     */
    public void editar(Cliente clienteActualizado) throws IOException {
        List<String> lineas = GestorCSV.leerLineas(RUTA, Cliente.ENCABEZADO);
        boolean encontrado = false;
        List<String> nuevasLineas = new ArrayList<>();

        for (String linea : lineas) {
            try {
                Cliente c = Cliente.desdeCSV(linea);
                if (c.getIdCliente() == clienteActualizado.getIdCliente()) {
                    nuevasLineas.add(clienteActualizado.aCSV());
                    encontrado = true;
                } else {
                    nuevasLineas.add(linea);
                }
            } catch (Exception e) {
                nuevasLineas.add(linea);
            }
        }

        if (!encontrado) {
            throw new NoSuchElementException(
                "No se encontró cliente con ID: " + clienteActualizado.getIdCliente());
        }

        GestorCSV.escribirLineas(RUTA, Cliente.ENCABEZADO, nuevasLineas);
        System.out.println("✔ Cliente actualizado correctamente.");
    }


    /**
     * Elimina un cliente del archivo CSV por su ID.
     *
     * @param id Identificador único del cliente a eliminar.
     * @throws IOException            Si ocurre un error de lectura/escritura.
     * @throws NoSuchElementException Si no existe un cliente con el ID indicado.
     */
    public void eliminar(int id) throws IOException {
        List<String> lineas = GestorCSV.leerLineas(RUTA, Cliente.ENCABEZADO);
        boolean encontrado = false;
        List<String> nuevasLineas = new ArrayList<>();

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
            throw new NoSuchElementException("No se encontró cliente con ID: " + id);
        }

        GestorCSV.escribirLineas(RUTA, Cliente.ENCABEZADO, nuevasLineas);
        System.out.println("✔ Cliente eliminado correctamente.");
    }
}
