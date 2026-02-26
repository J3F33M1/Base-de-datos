import java.util.Scanner;

/**
 * Clase Validador
 *
 * <p>Proporciona métodos utilitarios para leer y validar entradas del usuario
 * desde la consola. Garantiza que los campos numéricos solo acepten números
 * y maneja los errores de formato de manera amigable.</p>
 *
 * <p>Esta clase previene que el sistema falle por entradas inválidas, cumpliendo
 * el requisito de verificar que los campos numéricos solo almacenen ese tipo de dato.</p>
 *
 * @author Equipo Fundamentos de Bases de Datos
 * @version 1.0
 */
public class Validador {

    /**
     * Lee un número entero del usuario, reintentando hasta obtener un valor válido.
     *
     * <p>Si el usuario ingresa algo que no sea un entero, se le notifica
     * y se le pide que ingrese el dato nuevamente.</p>
     *
     * @param scanner Scanner activo para leer desde consola.
     * @param mensaje Mensaje a mostrar al usuario antes de leer.
     * @return Un entero válido ingresado por el usuario.
     */
    public static int leerEntero(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("  ⚠ Error: Debes ingresar un número entero válido. Intenta de nuevo.");
            }
        }
    }

    /**
     * Lee un número entero positivo (mayor a 0) del usuario.
     *
     * @param scanner Scanner activo para leer desde consola.
     * @param mensaje Mensaje a mostrar al usuario.
     * @return Un entero mayor a 0.
     */
    public static int leerEnteroPositivo(Scanner scanner, String mensaje) {
        while (true) {
            int valor = leerEntero(scanner, mensaje);
            if (valor > 0) return valor;
            System.out.println("  ⚠ Error: El valor debe ser mayor a 0.");
        }
    }

    /**
     * Lee un número entero no negativo (mayor o igual a 0) del usuario.
     *
     * @param scanner Scanner activo para leer desde consola.
     * @param mensaje Mensaje a mostrar al usuario.
     * @return Un entero mayor o igual a 0.
     */
    public static int leerEnteroNoNegativo(Scanner scanner, String mensaje) {
        while (true) {
            int valor = leerEntero(scanner, mensaje);
            if (valor >= 0) return valor;
            System.out.println("  ⚠ Error: El valor no puede ser negativo.");
        }
    }

    /**
     * Lee un número decimal (double) positivo del usuario, reintentando hasta obtener valor válido.
     *
     * @param scanner Scanner activo para leer desde consola.
     * @param mensaje Mensaje a mostrar al usuario.
     * @return Un double mayor a 0.
     */
    public static double leerDoublePositivo(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            try {
                double valor = Double.parseDouble(entrada);
                if (valor > 0) return valor;
                System.out.println("  ⚠ Error: El precio debe ser mayor a 0.");
            } catch (NumberFormatException e) {
                System.out.println("  ⚠ Error: Debes ingresar un número decimal válido (ej. 12.50). Intenta de nuevo.");
            }
        }
    }

    /**
     * Lee una cadena de texto no vacía del usuario.
     *
     * <p>Reintenta la lectura si el usuario ingresa una cadena vacía o solo espacios.</p>
     *
     * @param scanner Scanner activo para leer desde consola.
     * @param mensaje Mensaje a mostrar al usuario.
     * @return Cadena de texto no vacía ingresada por el usuario.
     */
    public static String leerTextoNoVacio(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            if (!entrada.isEmpty()) return entrada;
            System.out.println("  ⚠ Error: Este campo no puede estar vacío. Intenta de nuevo.");
        }
    }
}
