import java.io.*;
import java.nio.file.*;
import java.util.*;

/**
 * Clase GestorCSV
 *
 * <p>Proporciona métodos genéricos y reutilizables para leer y escribir
 * archivos CSV. Maneja la creación del archivo si no existe, la lectura
 * de todas las líneas (omitiendo el encabezado) y la escritura completa
 * del archivo con encabezado incluido.</p>
 *
 * <p>Esta clase actúa como capa de acceso a datos (DAO) a nivel de archivo,
 * separando la lógica de persistencia de la lógica de negocio.</p>
 *
 * @author Equipo Fundamentos de Bases de Datos
 * @version 1.0
 */
public class GestorCSV {

    /**
     * Lee todas las líneas de datos de un archivo CSV, omitiendo el encabezado.
     *
     * <p>Si el archivo no existe, se crea automáticamente con el encabezado
     * proporcionado y se devuelve una lista vacía.</p>
     *
     * @param rutaArchivo Ruta del archivo CSV a leer.
     * @param encabezado  Encabezado que se escribirá si el archivo se crea de nuevo.
     * @return Lista de cadenas, una por cada línea de datos (sin encabezado).
     * @throws IOException Si ocurre un error de lectura o escritura.
     */
    public static List<String> leerLineas(String rutaArchivo, String encabezado) throws IOException {
        File archivo = new File(rutaArchivo);

        // Crear el archivo con encabezado si no existe
        if (!archivo.exists()) {
            archivo.getParentFile().mkdirs(); // Crear directorios si es necesario
            try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
                pw.println(encabezado);
            }
            return new ArrayList<>();
        }

        List<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            boolean primeraLinea = true;
            while ((linea = br.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false; // Omitir encabezado
                    continue;
                }
                if (!linea.trim().isEmpty()) {
                    lineas.add(linea.trim());
                }
            }
        }
        return lineas;
    }

    /**
     * Escribe todas las líneas de datos en un archivo CSV, incluyendo el encabezado.
     *
     * <p>Este método sobreescribe el archivo completo, lo que permite implementar
     * operaciones de edición y eliminación de registros.</p>
     *
     * @param rutaArchivo Ruta del archivo CSV a escribir.
     * @param encabezado  Primera línea del archivo (nombres de columnas).
     * @param lineas      Lista de cadenas con los datos a escribir.
     * @throws IOException Si ocurre un error al escribir el archivo.
     */
    public static void escribirLineas(String rutaArchivo, String encabezado, List<String> lineas) throws IOException {
        File archivo = new File(rutaArchivo);
        archivo.getParentFile().mkdirs();

        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo, false))) {
            pw.println(encabezado);
            for (String linea : lineas) {
                pw.println(linea);
            }
        }
    }

    /**
     * Agrega una sola línea de datos al final de un archivo CSV.
     *
     * <p>Si el archivo no existe, lo crea con el encabezado antes de agregar la línea.
     * Este método es más eficiente que reescribir todo el archivo cuando solo se agrega.</p>
     *
     * @param rutaArchivo Ruta del archivo CSV.
     * @param encabezado  Encabezado para el caso en que el archivo deba crearse.
     * @param nuevaLinea  Línea de datos a agregar.
     * @throws IOException Si ocurre un error de escritura.
     */
    public static void agregarLinea(String rutaArchivo, String encabezado, String nuevaLinea) throws IOException {
        File archivo = new File(rutaArchivo);

        if (!archivo.exists()) {
            archivo.getParentFile().mkdirs();
            try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
                pw.println(encabezado);
            }
        }

        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo, true))) {
            pw.println(nuevaLinea);
        }
    }
}
