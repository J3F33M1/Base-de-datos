import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Objeto de Acceso a Datos (DAO) para la entidad Sucursal.
 * <p>
 * Gestiona la persistencia de sucursales en un archivo CSV, permitiendo
 * agregar, buscar, listar, editar y eliminar registros.
 * </p>
 */
public class SucursalDAO {
    // Esta es la parte de la ruta donde se guardan los CSV, verificar que funcione correctamente en el directorio o de ser necesario mas robusto
    private static final String RUTA = "Práctica02_LosSúperDatos/src/sucursales.csv";

    /**
     * Agrega una nueva sucursal al archivo CSV.
     * @param sucursal El objeto Sucursal a agregar.
     * @throws IOException Si ocurre un error de entrada/salida.
     * @throws IllegalArgumentException Si ya existe una sucursal con el mismo ID.
     */
    public void agregar(Sucursal sucursal) throws IOException {
        if (buscarPorId(sucursal.getIdSucursal()) != null) {
            throw new IllegalArgumentException("Ya existe una sucursal con el ID: " + sucursal.getIdSucursal());
        }
        GestorCSV.agregarLinea(RUTA, Sucursal.ENCABEZADO, sucursal.aCSV());
        System.out.println("Sucursal agregada.");
    }

    /**
     * Busca una sucursal por su identificador.
     * @param id El ID de la sucursal.
     * @return El objeto Sucursal encontrado o null si no existe.
     * @throws IOException Si ocurre un error de lectura.
     */
    public Sucursal buscarPorId(int id) throws IOException {
        List<String> lineas = GestorCSV.leerLineas(RUTA, Sucursal.ENCABEZADO);
        for (String linea : lineas) {
            try {
                Sucursal s = Sucursal.desdeCSV(linea);
                if (s.getIdSucursal() == id) return s;
            } catch (Exception e) {
                System.err.println("Linea omitida en sucursales: " + linea);
            }
        }
        return null;
    }

    /**
     * Recupera todas las sucursales registradas.
     * @return Una lista de objetos Sucursal.
     * @throws IOException Si ocurre un error de lectura.
     */
    public List<Sucursal> obtenerTodas() throws IOException {
        List<Sucursal> lista = new ArrayList<>();
        List<String> lineas = GestorCSV.leerLineas(RUTA, Sucursal.ENCABEZADO);
        for (String linea : lineas) {
            try {
                lista.add(Sucursal.desdeCSV(linea));
            } catch (Exception e) {
                System.err.println("Linea omitida en sucursales: " + linea);
            }
        }
        return lista;
    }

    /**
     * Actualiza los datos de una sucursal existente.
     * @param sucursalActualizada El objeto Sucursal con los nuevos datos.
     * @throws IOException Si ocurre un error de lectura/escritura.
     * @throws NoSuchElementException Si la sucursal no existe.
     */
    public void editar(Sucursal sucursalActualizada) throws IOException {
        List<String> lineas = GestorCSV.leerLineas(RUTA, Sucursal.ENCABEZADO);
        List<String> nuevasLineas = new ArrayList<>();
        boolean encontrado = false;

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
                nuevasLineas.add(linea);
            }
        }

        if (!encontrado) {
            throw new NoSuchElementException("No se encontro sucursal con ID: " + sucursalActualizada.getIdSucursal());
        }

        GestorCSV.escribirLineas(RUTA, Sucursal.ENCABEZADO, nuevasLineas);
        System.out.println("Sucursal actualizada.");
    }

    /**
     * Elimina una sucursal del registro.
     * @param id El ID de la sucursal a eliminar.
     * @throws IOException Si ocurre un error de lectura/escritura.
     * @throws NoSuchElementException Si la sucursal no existe.
     */
    public void eliminar(int id) throws IOException {
        List<String> lineas = GestorCSV.leerLineas(RUTA, Sucursal.ENCABEZADO);
        List<String> nuevasLineas = new ArrayList<>();
        boolean encontrado = false;

        for (String linea : lineas) {
            try {
                Sucursal s = Sucursal.desdeCSV(linea);
                if (s.getIdSucursal() == id) {
                    encontrado = true;
                } else {
                    nuevasLineas.add(linea);
                }
            } catch (Exception e) {
                nuevasLineas.add(linea);
            }
        }

        if (!encontrado) {
            throw new NoSuchElementException("No se encontro sucursal con ID: " + id);
        }

        GestorCSV.escribirLineas(RUTA, Sucursal.ENCABEZADO, nuevasLineas);
        System.out.println("Sucursal eliminada.");
    }
}
