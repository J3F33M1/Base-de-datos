import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * DAO para medicamentos o insumos.
 */
public class MedicamentoDAO {

    private static final String RUTA = "datos/medicamentos.csv";

    public void agregar(Medicamento med) throws IOException {
        if (buscarPorId(med.getIdMedicamento()) != null) {
            throw new IllegalArgumentException("Ya existe un medicamento con ID: " + med.getIdMedicamento());
        }
        GestorCSV.agregarLinea(RUTA, Medicamento.ENCABEZADO, med.aCSV());
        System.out.println("Medicamento agregado.");
    }

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
