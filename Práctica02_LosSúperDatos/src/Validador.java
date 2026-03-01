import java.util.Scanner;

/**
 * Utilidades de validacion de entrada por consola.
 * Solo imprime mensajes en ASCII para evitar problemas de encoding.
 */
public class Validador {

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

    public static int leerEnteroPositivo(Scanner scanner, String mensaje) {
        while (true) {
            int valor = leerEntero(scanner, mensaje);
            if (valor > 0) return valor;
            System.out.println("  Error: el valor debe ser mayor que cero.");
        }
    }

    public static int leerEnteroNoNegativo(Scanner scanner, String mensaje) {
        while (true) {
            int valor = leerEntero(scanner, mensaje);
            if (valor >= 0) return valor;
            System.out.println("  Error: el valor no puede ser negativo.");
        }
    }

    public static double leerDoublePositivo(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            try {
                double valor = Double.parseDouble(entrada);
                if (valor > 0) return valor;
                System.out.println("  Error: el valor debe ser mayor que cero.");
            } catch (NumberFormatException e) {
                System.out.println("  Error: ingresa un numero decimal valido, ejemplo 12.50");
            }
        }
    }

    public static String leerTextoNoVacio(Scanner scanner, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String entrada = scanner.nextLine().trim();
            if (!entrada.isEmpty()) return entrada;
            System.out.println("  Error: el texto no puede estar vacio.");
        }
    }
}
