import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase utilitaria para la gestion de archivos CSV.
 * <p>
 * Provee metodos estaticos para leer, escribir y agregar lineas a archivos de texto,
 * manejando automaticamente la creacion de archivos y directorios si no existen.
 * </p>
 */
public class GestorCSV {

    /**
     * Lee todas las lineas de un archivo CSV, omitiendo la primera linea (encabezado).
     * @param rutaArchivo La ruta relativa o absoluta del archivo.
     * @param encabezado El encabezado que se escribira si el archivo debe crearse.
     * @return Una lista de cadenas con el contenido del archivo.
     * @throws IOException Si ocurre un error de entrada/salida.
     */
    public static List<String> leerLineas(String rutaArchivo, String encabezado) throws IOException {
        File archivo = new File(rutaArchivo);

        if (!archivo.exists()) {
            archivo.getParentFile().mkdirs();
            try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
                pw.println(encabezado);
            }
            return new ArrayList<>();
        }

        List<String> lineas = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            boolean primera = true;
            while ((linea = br.readLine()) != null) {
                if (primera) {
                    primera = false; // saltar encabezado
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
     * Sobrescribe un archivo CSV con una lista de lineas, incluyendo el encabezado.
     * @param rutaArchivo La ruta del archivo.
     * @param encabezado La primera linea del archivo.
     * @param lineas La lista de datos a escribir.
     * @throws IOException Si ocurre un error de escritura.
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
     * Agrega una nueva linea al final de un archivo CSV existente.
     * @param rutaArchivo La ruta del archivo.
     * @param encabezado El encabezado a usar si el archivo se crea por primera vez.
     * @param nuevaLinea La cadena de datos a agregar.
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
