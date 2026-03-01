import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Utilidades basicas para leer y escribir archivos CSV.
 * Crea el archivo con su encabezado si no existe.
 */
public class GestorCSV {

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
