import java.io.IOException;
import java.util.*;

/**
 * Clase MedicamentoDAO
 *
 * <p>Implementa las operaciones CRUD (Crear, Leer, Actualizar, Eliminar)
 * para la entidad {@link Medicamento}, usando archivos CSV como mecanismo
 * de persistencia a través de {@link GestorCSV}.</p>
 *
 * @author Equipo Fundamentos de Bases de Datos
 * @version 1.0
 */
public class MedicamentoDAO {

    /** Ruta del archivo CSV donde se almacenan los medicamentos. */
    private static final String RUTA = "datos/medicamentos.csv";

    // ──────────────────── CREATE ────────────────────

    /**
     * Agrega un nuevo medicamento al archivo CSV.
     *
     * @param medicamento Objeto {@link Medicamento} a guardar.
     * @throws IOException              Si ocurre un error de escritura.
     * @throws IllegalArgumentException Si ya existe un medicamento con el mismo ID.
     */
    public void agregar(Medicamento medicamento) throws IOException {
        if (buscarPorId(medicamento.getIdMedicamento()) != null) {
            throw new IllegalArgumentException(
                "Ya existe un medicamento con el ID: " + medicamento.getIdMedicamento());
        }
        GestorCSV.agregarLinea(RUTA, Medicamento.ENCABEZADO, medicamento.aCSV());
        System.out.println("✔ Medicamento agregado correctamente.");
    }

    // ──────────────────── READ ────────────────────

    /**
     * Busca un medicamento por su ID (llave primaria).
     *
     * @param id Identificador único del medicamento.
     * @return El objeto {@link Medicamento} encontrado, o {@code null} si no existe.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public Medicamento buscarPorId(int id) throws IOException {
        List<String> lineas = GestorCSV.leerLineas(RUTA, Medicamento.ENCABEZADO);
        for (String linea : lineas) {
            try {
                Medicamento m = Medicamento.desdeCSV(linea);
                if (m.getIdMedicamento() == id) return m;
            } catch (Exception e) {
                System.err.println("⚠ Línea malformada en CSV de medicamentos, se omite: " + linea);
            }
        }
        return null;
    }

    /**
     * Obtiene todos los medicamentos almacenados en el archivo CSV.
     *
     * @return Lista de objetos {@link Medicamento}. Vacía si no hay registros.
     * @throws IOException Si ocurre un error al leer el archivo.
     */
    public List<Medicamento> obtenerTodos() throws IOException {
        List<Medicamento> lista = new ArrayList<>();
        List<String> lineas = GestorCSV.leerLineas(RUTA, Medicamento.ENCABEZADO);
        for (String linea : lineas) {
            try {
                lista.add(Medicamento.desdeCSV(linea));
            } catch (Exception e) {
                System.err.println("⚠ Línea malformada en CSV de medicamentos, se omite: " + linea);
            }
        }
        return lista;
    }

    // ──────────────────── UPDATE ────────────────────

    /**
     * Edita los datos de un medicamento existente.
     *
     * @param medicamentoActualizado Objeto {@link Medicamento} con los nuevos datos.
     * @throws IOException            Si ocurre un error de lectura/escritura.
     * @throws NoSuchElementException Si no existe un medicamento con el ID indicado.
     */
    public void editar(Medicamento medicamentoActualizado) throws IOException {
        List<String> lineas = GestorCSV.leerLineas(RUTA, Medicamento.ENCABEZADO);
        boolean encontrado = false;
        List<String> nuevasLineas = new ArrayList<>();

        for (String linea : lineas) {
            try {
                Medicamento m = Medicamento.desdeCSV(linea);
                if (m.getIdMedicamento() == medicamentoActualizado.getIdMedicamento()) {
                    nuevasLineas.add(medicamentoActualizado.aCSV());
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
                "No se encontró medicamento con ID: " + medicamentoActualizado.getIdMedicamento());
        }

        GestorCSV.escribirLineas(RUTA, Medicamento.ENCABEZADO, nuevasLineas);
        System.out.println("✔ Medicamento actualizado correctamente.");
    }

    // ──────────────────── DELETE ────────────────────

    /**
     * Elimina un medicamento del archivo CSV por su ID.
     *
     * @param id Identificador único del medicamento a eliminar.
     * @throws IOException            Si ocurre un error de lectura/escritura.
     * @throws NoSuchElementException Si no existe un medicamento con el ID indicado.
     */
    public void eliminar(int id) throws IOException {
        List<String> lineas = GestorCSV.leerLineas(RUTA, Medicamento.ENCABEZADO);
        boolean encontrado = false;
        List<String> nuevasLineas = new ArrayList<>();

        for (String linea : lineas) {
            try {
                Medicamento m = Medicamento.desdeCSV(linea);
                if (m.getIdMedicamento() == id) {
                    encontrado = true;
                } else {
                    nuevasLineas.add(linea);
                }
            } catch (Exception e) {
                nuevasLineas.add(linea);
            }
        }

        if (!encontrado) {
            throw new NoSuchElementException("No se encontró medicamento con ID: " + id);
        }

        GestorCSV.escribirLineas(RUTA, Medicamento.ENCABEZADO, nuevasLineas);
        System.out.println("✔ Medicamento eliminado correctamente.");
    }
}
