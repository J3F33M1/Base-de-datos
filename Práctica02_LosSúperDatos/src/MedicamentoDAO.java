import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Objeto de Acceso a Datos (DAO) para la entidad Medicamento.
 * <p>
 * Gestiona la persistencia de medicamentos e insumos en un archivo CSV,
 * permitiendo agregar, buscar, listar, editar y eliminar registros.
 * </p>
 */
public class MedicamentoDAO {
    // Esta es la parte de la ruta donde se guardan los CSV, verificar que funcione correctamente en el directorio o de ser necesario mas robusto
    private static final String RUTA = "Práctica02_LosSúperDatos/src/medicamentos.csv";

    /**
     * Agrega un nuevo medicamento al archivo CSV.
     * @param med El objeto Medicamento a agregar.
     * @throws IOException Si ocurre un error de entrada/salida.
     * @throws IllegalArgumentException Si ya existe un medicamento con el mismo ID.
     */
    public void agregar(Medicamento med) throws IOException {
        if (buscarPorId(med.getIdMedicamento()) != null) {
            throw new IllegalArgumentException("Ya existe un medicamento con ID: " + med.getIdMedicamento());
        }
        GestorCSV.agregarLinea(RUTA, Medicamento.ENCABEZADO, med.aCSV());
        System.out.println("Medicamento agregado.");
    }

    /**
     * Busca un medicamento por su identificador.
     * @param id El ID del medicamento.
     * @return El objeto Medicamento encontrado o null si no existe.
     * @throws IOException Si ocurre un error de lectura.
     */
    public Medicamento buscarPorId(int id) throws IOException {
        List<String> lineas = GestorCSV.leerLineas(RUTA, Medicamento.ENCABEZADO);
        for (String linea : lineas) {
            try {
                Medicamento m = Medicamento.desdeCSV(linea);
                if (m.getIdMedicamento() == id) return m;
            } catch (Exception e) {
                System.err.println("Linea omitida en medicamentos: " + linea);
            }
        }
        return null;
    }

    /**
     * Recupera todos los medicamentos registrados.
     * @return Una lista de objetos Medicamento.
     * @throws IOException Si ocurre un error de lectura.
     */
    public List<Medicamento> obtenerTodos() throws IOException {
        List<Medicamento> lista = new ArrayList<>();
        List<String> lineas = GestorCSV.leerLineas(RUTA, Medicamento.ENCABEZADO);
        for (String linea : lineas) {
            try {
                lista.add(Medicamento.desdeCSV(linea));
            } catch (Exception e) {
                System.err.println("Linea omitida en medicamentos: " + linea);
            }
        }
        return lista;
    }

    /**
     * Actualiza los datos de un medicamento existente.
     * @param actualizado El objeto Medicamento con los nuevos datos.
     * @throws IOException Si ocurre un error de lectura/escritura.
     * @throws NoSuchElementException Si el medicamento no existe.
     */
    public void editar(Medicamento actualizado) throws IOException {
        List<String> lineas = GestorCSV.leerLineas(RUTA, Medicamento.ENCABEZADO);
        List<String> nuevasLineas = new ArrayList<>();
        boolean encontrado = false;

        for (String linea : lineas) {
            try {
                Medicamento m = Medicamento.desdeCSV(linea);
                if (m.getIdMedicamento() == actualizado.getIdMedicamento()) {
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
            throw new NoSuchElementException("No se encontro medicamento con ID: " + actualizado.getIdMedicamento());
        }

        GestorCSV.escribirLineas(RUTA, Medicamento.ENCABEZADO, nuevasLineas);
        System.out.println("Medicamento actualizado.");
    }

    /**
     * Elimina un medicamento del registro.
     * @param id El ID del medicamento a eliminar.
     * @throws IOException Si ocurre un error de lectura/escritura.
     * @throws NoSuchElementException Si el medicamento no existe.
     */
    public void eliminar(int id) throws IOException {
        List<String> lineas = GestorCSV.leerLineas(RUTA, Medicamento.ENCABEZADO);
        List<String> nuevasLineas = new ArrayList<>();
        boolean encontrado = false;

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
            throw new NoSuchElementException("No se encontro medicamento con ID: " + id);
        }

        GestorCSV.escribirLineas(RUTA, Medicamento.ENCABEZADO, nuevasLineas);
        System.out.println("Medicamento eliminado.");
    }
}
