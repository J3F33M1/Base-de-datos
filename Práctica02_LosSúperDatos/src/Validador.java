import java.util.Scanner;

/**
 * Clase utilitaria para la validacion de entradas del usuario por consola.
 * <p>
 * Contiene metodos para leer y validar tipos de datos especificos (enteros, doubles, cadenas),
 * asegurando que la entrada cumpla con ciertos criterios antes de continuar.
 * </p>
 */
public class Validador {

    /**
     * Lee un numero entero de la entrada estandar.
     * @param scanner El objeto Scanner para leer la entrada.
     * @param mensaje El mensaje a mostrar al usuario.
     * @return El entero ingresado.
     */
    public static int leerEntero(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("  Error: ingresa un numero entero valido.");
            }
        }
    }

    /**
     * Lee un numero entero estrictamente positivo (> 0).
     * @param scanner El objeto Scanner.
     * @param mensaje El mensaje a mostrar.
     * @return El entero positivo ingresado.
     */
    public static int leerEnteroPositivo(Scanner scanner, String mensaje) {
        while (true) {
            int valor = leerEntero(scanner, mensaje);
            if (valor > 0) return valor;
            System.out.println("  Error: el valor debe ser mayor que cero.");
        }
    }

    /**
     * Lee un numero entero no negativo (>= 0).
     * @param scanner El objeto Scanner.
     * @param mensaje El mensaje a mostrar.
     * @return El entero no negativo ingresado.
     */
    public static int leerEnteroNoNegativo(Scanner scanner, String mensaje) {
        while (true) {
            int valor = leerEntero(scanner, mensaje);
            if (valor >= 0) return valor;
            System.out.println("  Error: el valor no puede ser negativo.");
        }
    }

    /**
     * Lee un numero decimal positivo (> 0.0).
     * @param scanner El objeto Scanner.
     * @param mensaje El mensaje a mostrar.
     * @return El valor double positivo ingresado.
     */
    public static double leerDoublePositivo(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            try {
                double valor = Double.parseDouble(entrada);
                if (valor > 0) return valor;
                System.out.println("  Error: el valor debe ser mayor que cero.");
            } catch (NumberFormatException e) {
                System.out.println("  Error: ingresa un numero decimal valido, ejemplo 11.50");
            }
        }
    }

    /**
     * Lee una cadena de texto que no este vacia.
     * @param scanner El objeto Scanner.
     * @param mensaje El mensaje a mostrar.
     * @return La cadena de texto ingresada.
     */
    public static String leerTextoNoVacio(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            if (!entrada.isEmpty()) return entrada;
            System.out.println("  Error: el texto no puede estar vacio.");
        }
    }
}
