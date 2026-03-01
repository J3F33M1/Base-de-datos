import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * DAO para la entidad Sucursal.
 * Implementa operaciones CRUD sobre archivos CSV.
 */
public class SucursalDAO {

    private static final String RUTA = "datos/sucursales.csv";

    public void agregar(Sucursal sucursal) throws IOException {
        if (buscarPorId(sucursal.getIdSucursal()) != null) {
            throw new IllegalArgumentException("Ya existe una sucursal con el ID: " + sucursal.getIdSucursal());
        }
        GestorCSV.agregarLinea(RUTA, Sucursal.ENCABEZADO, sucursal.aCSV());
        System.out.println("Sucursal agregada.");
    }

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
