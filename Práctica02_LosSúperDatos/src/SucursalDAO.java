import java.io.IOException;
import java.util.*;

/**
 * Clase SucursalDAO
 *
 * <p>Implementa las operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * para la entidad {@link Sucursal}, usando archivos CSV como mecanismo
 * de persistencia a través de {@link GestorCSV}.</p>
 *
 * <p>DAO = Data Access Object. Esta clase centraliza todo el acceso
 * a datos de sucursales, separando la lógica de negocio de la de persistencia.</p>
 *
 * @author Equipo Fundamentos de Bases de Datos
 * @version 1.0
 */
public class SucursalDAO {

    /** Ruta del archivo CSV donde se almacenan las sucursales. */
    private static final String RUTA = "datos/sucursales.csv";

    // ──────────────────── CREATE ────────────────────

    /**
     * Agrega una nueva sucursal al archivo CSV.
     *
     * <p>Verifica que no exista ya una sucursal con el mismo ID antes de guardar.</p>
     *
     * @param sucursal Objeto {@link Sucursal} a guardar.
     * @throws IOException              Si ocurre un error al escribir el archivo.
     * @throws IllegalArgumentException Si ya existe una sucursal con el mismo ID.
     */
    public void agregar(Sucursal sucursal) throws IOException {
        if (buscarPorId(sucursal.getIdSucursal()) != null) {
            throw new IllegalArgumentException(
                "Ya existe una sucursal con el ID: " + sucursal.getIdSucursal());
        }
        GestorCSV.agregarLinea(RUTA, Sucursal.ENCABEZADO, sucursal.aCSV());
        System.out.println("✔ Sucursal agregada correctamente.");
    }

    // ──────────────────── READ ────────────────────

    /**
     * Busca una sucursal por su ID (llave primaria).
     *
     * @param id Identificador único de la sucursal.
     * @return El objeto {@link Sucursal} si se encuentra, o {@code null} si no existe.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public Sucursal buscarPorId(int id) throws IOException {
        List<String> lineas = GestorCSV.leerLineas(RUTA, Sucursal.ENCABEZADO);
        for (String linea : lineas) {
            try {
                Sucursal s = Sucursal.desdeCSV(linea);
                if (s.getIdSucursal() == id) return s;
            } catch (Exception e) {
                System.err.println("⚠ Línea malformada en CSV de sucursales, se omite: " + linea);
            }
        }
        return null;
    }

    /**
     * Obtiene todas las sucursales almacenadas en el archivo CSV.
     *
     * @return Lista de objetos {@link Sucursal}. Vacía si no hay registros.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public List<Sucursal> obtenerTodas() throws IOException {
        List<Sucursal> lista = new ArrayList<>();
        List<String> lineas = GestorCSV.leerLineas(RUTA, Sucursal.ENCABEZADO);
        for (String linea : lineas) {
            try {
                lista.add(Sucursal.desdeCSV(linea));
            } catch (Exception e) {
                System.err.println("⚠ Línea malformada en CSV de sucursales, se omite: " + linea);
            }
        }
        return lista;
    }

    // ──────────────────── UPDATE ────────────────────

    /**
     * Edita los datos de una sucursal existente, identificada por su ID.
     *
     * <p>Reescribe el archivo CSV completo con los datos actualizados.</p>
     *
     * @param sucursalActualizada Objeto {@link Sucursal} con los nuevos datos
     *                           (debe tener el mismo ID que la sucursal a editar).
     * @throws IOException              Si ocurre un error al leer o escribir el archivo.
     * @throws NoSuchElementException   Si no existe una sucursal con el ID indicado.
     */
    public void editar(Sucursal sucursalActualizada) throws IOException {
        List<String> lineas = GestorCSV.leerLineas(RUTA, Sucursal.ENCABEZADO);
        boolean encontrado = false;
        List<String> nuevasLineas = new ArrayList<>();

        for (String linea : lineas) {
            try {
                Sucursal s = Sucursal.desdeCSV(linea);
                if (s.getIdSucursal() == sucursalActualizada.getIdSucursal()) {
                    nuevasLineas.add(sucursalActualizada.aCSV());
                    encontrado = true;
                } else {
                    nuevasLineas.add(linea);
                }
            } catch (Exception e) {
                nuevasLineas.add(linea); // Conservar líneas aunque estén malformadas
            }
        }

        if (!encontrado) {
            throw new NoSuchElementException(
                "No se encontró sucursal con ID: " + sucursalActualizada.getIdSucursal());
        }

        GestorCSV.escribirLineas(RUTA, Sucursal.ENCABEZADO, nuevasLineas);
        System.out.println("✔ Sucursal actualizada correctamente.");
    }

    // ──────────────────── DELETE ────────────────────

    /**
     * Elimina una sucursal del archivo CSV por su ID.
     *
     * @param id Identificador único de la sucursal a eliminar.
     * @throws IOException            Si ocurre un error al leer o escribir el archivo.
     * @throws NoSuchElementException Si no existe una sucursal con el ID indicado.
     */
    public void eliminar(int id) throws IOException {
        List<String> lineas = GestorCSV.leerLineas(RUTA, Sucursal.ENCABEZADO);
        boolean encontrado = false;
        List<String> nuevasLineas = new ArrayList<>();

        for (String linea : lineas) {
            try {
                Sucursal s = Sucursal.desdeCSV(linea);
                if (s.getIdSucursal() == id) {
                    encontrado = true; // No agregar = eliminar
                } else {
                    nuevasLineas.add(linea);
                }
            } catch (Exception e) {
                nuevasLineas.add(linea);
            }
        }

        if (!encontrado) {
            throw new NoSuchElementException("No se encontró sucursal con ID: " + id);
        }

        GestorCSV.escribirLineas(RUTA, Sucursal.ENCABEZADO, nuevasLineas);
        System.out.println("✔ Sucursal eliminada correctamente.");
    }
}
