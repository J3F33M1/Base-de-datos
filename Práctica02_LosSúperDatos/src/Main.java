import java.io.IOException;
import java.util.*;

/**
 * Clase Main (Punto de Entrada)
 *
 * <p>Clase principal del sistema de gestión de la cadena de farmacias "Xiao Mao".
 * Contiene el menú interactivo de consola que permite al usuario realizar
 * operaciones CRUD sobre las entidades: Sucursal, Medicamento y Cliente.</p>
 *
 * <p>La información se persiste en archivos CSV ubicados en la carpeta {@code datos/},
 * simulando el comportamiento de una base de datos para su posterior migración.</p>
 *
 * <p><b>Menú principal:</b></p>
 * <pre>
 *   1. Gestionar Sucursales
 *   2. Gestionar Medicamentos/Insumos
 *   3. Gestionar Clientes
 *   0. Salir
 * </pre>
 *
 * @author Equipo Fundamentos de Bases de Datos
 * @version 1.0
 */
public class Main {

    /** Scanner global para leer entradas del usuario. */
    private static final Scanner scanner = new Scanner(System.in);

    /** DAO para operaciones de sucursales. */
    private static final SucursalDAO sucursalDAO = new SucursalDAO();

    /** DAO para operaciones de medicamentos. */
    private static final MedicamentoDAO medicamentoDAO = new MedicamentoDAO();

    /** DAO para operaciones de clientes. */
    private static final ClienteDAO clienteDAO = new ClienteDAO();

    // ═══════════════════════════════════════════════════
    //                   PUNTO DE ENTRADA
    // ═══════════════════════════════════════════════════

    /**
     * Método principal. Inicia la aplicación y muestra el menú principal.
     *
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        imprimirBienvenida();
        menuPrincipal();
        System.out.println("\n  Hasta luego. ¡Que tenga un excelente día!\n");
        scanner.close();
    }

    // ═══════════════════════════════════════════════════
    //                   MENÚ PRINCIPAL
    // ═══════════════════════════════════════════════════

    /**
     * Muestra y gestiona el menú principal de la aplicación.
     * Permite navegar entre los submenús de cada entidad.
     */
    private static void menuPrincipal() {
        boolean activo = true;
        while (activo) {
            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║       FARMACIA XIAO MAO - MENÚ PRINCIPAL ║");
            System.out.println("╠══════════════════════════════════════════╣");
            System.out.println("║  1. Gestionar Sucursales                 ║");
            System.out.println("║  2. Gestionar Medicamentos / Insumos     ║");
            System.out.println("║  3. Gestionar Clientes                   ║");
            System.out.println("║  0. Salir                                ║");
            System.out.println("╚══════════════════════════════════════════╝");

            int opcion = Validador.leerEntero(scanner, "  Selecciona una opción: ");
            switch (opcion) {
                case 1 -> menuSucursales();
                case 2 -> menuMedicamentos();
                case 3 -> menuClientes();
                case 0 -> activo = false;
                default -> System.out.println("  ⚠ Opción no válida. Intenta de nuevo.");
            }
        }
    }

    // ═══════════════════════════════════════════════════
    //                 MENÚ SUCURSALES
    // ═══════════════════════════════════════════════════

    /**
     * Muestra y gestiona el submenú de sucursales.
     * Permite agregar, consultar, editar y eliminar sucursales.
     */
    private static void menuSucursales() {
        boolean activo = true;
        while (activo) {
            System.out.println("\n┌─────────────────────────────────────────┐");
            System.out.println("│         GESTIÓN DE SUCURSALES            │");
            System.out.println("├─────────────────────────────────────────┤");
            System.out.println("│  1. Agregar sucursal                     │");
            System.out.println("│  2. Consultar sucursal por ID            │");
            System.out.println("│  3. Listar todas las sucursales          │");
            System.out.println("│  4. Editar sucursal                      │");
            System.out.println("│  5. Eliminar sucursal                    │");
            System.out.println("│  0. Volver al menú principal             │");
            System.out.println("└─────────────────────────────────────────┘");

            int opcion = Validador.leerEntero(scanner, "  Selecciona una opción: ");
            try {
                switch (opcion) {
                    case 1 -> agregarSucursal();
                    case 2 -> consultarSucursal();
                    case 3 -> listarSucursales();
                    case 4 -> editarSucursal();
                    case 5 -> eliminarSucursal();
                    case 0 -> activo = false;
                    default -> System.out.println("  ⚠ Opción no válida.");
                }
            } catch (IOException e) {
                System.out.println("  ✖ Error de archivo: " + e.getMessage());
            } catch (IllegalArgumentException | NoSuchElementException e) {
                System.out.println("  ✖ Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("  ✖ Error inesperado: " + e.getMessage());
            }
        }
    }

    /**
     * Solicita datos al usuario y agrega una nueva sucursal al sistema.
     *
     * @throws IOException Si ocurre un error al guardar en el archivo CSV.
     */
    private static void agregarSucursal() throws IOException {
        System.out.println("\n  ── Nueva Sucursal ──");
        int id = Validador.leerEnteroPositivo(scanner, "  ID Sucursal   : ");
        String nombre = Validador.leerTextoNoVacio(scanner, "  Nombre        : ");
        String dir = Validador.leerTextoNoVacio(scanner, "  Dirección     : ");
        String tel = Validador.leerTextoNoVacio(scanner, "  Teléfono      : ");
        String ciudad = Validador.leerTextoNoVacio(scanner, "  Ciudad        : ");
        sucursalDAO.agregar(new Sucursal(id, nombre, dir, tel, ciudad));
    }

    /**
     * Solicita un ID y muestra los datos de la sucursal correspondiente.
     *
     * @throws IOException Si ocurre un error al leer el archivo CSV.
     */
    private static void consultarSucursal() throws IOException {
        int id = Validador.leerEnteroPositivo(scanner, "\n  ID a consultar: ");
        Sucursal s = sucursalDAO.buscarPorId(id);
        if (s != null) System.out.println("\n" + s);
        else System.out.println("  ✖ No se encontró sucursal con ID: " + id);
    }

    /**
     * Muestra todas las sucursales registradas en el sistema.
     *
     * @throws IOException Si ocurre un error al leer el archivo CSV.
     */
    private static void listarSucursales() throws IOException {
        List<Sucursal> lista = sucursalDAO.obtenerTodas();
        if (lista.isEmpty()) {
            System.out.println("\n  No hay sucursales registradas.");
        } else {
            System.out.println("\n  Total: " + lista.size() + " sucursal(es)");
            lista.forEach(s -> System.out.println("\n" + s));
        }
    }

    /**
     * Solicita un ID y permite editar los datos de la sucursal correspondiente.
     *
     * @throws IOException Si ocurre un error al leer/escribir el archivo CSV.
     */
    private static void editarSucursal() throws IOException {
        int id = Validador.leerEnteroPositivo(scanner, "\n  ID a editar: ");
        Sucursal existente = sucursalDAO.buscarPorId(id);
        if (existente == null) {
            System.out.println("  ✖ No se encontró sucursal con ID: " + id);
            return;
        }
        System.out.println("  (Deja vacío y presiona Enter para conservar el valor actual)");
        System.out.print("  Nombre [" + existente.getNombre() + "]: ");
        String nombre = scanner.nextLine().trim();
        System.out.print("  Dirección [" + existente.getDireccion() + "]: ");
        String dir = scanner.nextLine().trim();
        System.out.print("  Teléfono [" + existente.getTelefono() + "]: ");
        String tel = scanner.nextLine().trim();
        System.out.print("  Ciudad [" + existente.getCiudad() + "]: ");
        String ciudad = scanner.nextLine().trim();

        if (!nombre.isEmpty()) existente.setNombre(nombre);
        if (!dir.isEmpty()) existente.setDireccion(dir);
        if (!tel.isEmpty()) existente.setTelefono(tel);
        if (!ciudad.isEmpty()) existente.setCiudad(ciudad);

        sucursalDAO.editar(existente);
    }

    /**
     * Solicita un ID y elimina la sucursal correspondiente del sistema.
     *
     * @throws IOException Si ocurre un error al leer/escribir el archivo CSV.
     */
    private static void eliminarSucursal() throws IOException {
        int id = Validador.leerEnteroPositivo(scanner, "\n  ID a eliminar: ");
        System.out.print("  ¿Confirmas eliminar la sucursal con ID " + id + "? (s/n): ");
        String confirmacion = scanner.nextLine().trim().toLowerCase();
        if (confirmacion.equals("s")) {
            sucursalDAO.eliminar(id);
        } else {
            System.out.println("  Operación cancelada.");
        }
    }

    // ═══════════════════════════════════════════════════
    //              MENÚ MEDICAMENTOS
    // ═══════════════════════════════════════════════════

    /**
     * Muestra y gestiona el submenú de medicamentos/insumos.
     */
    private static void menuMedicamentos() {
        boolean activo = true;
        while (activo) {
            System.out.println("\n┌─────────────────────────────────────────┐");
            System.out.println("│     GESTIÓN DE MEDICAMENTOS / INSUMOS    │");
            System.out.println("├─────────────────────────────────────────┤");
            System.out.println("│  1. Agregar medicamento                  │");
            System.out.println("│  2. Consultar medicamento por ID         │");
            System.out.println("│  3. Listar todos los medicamentos        │");
            System.out.println("│  4. Editar medicamento                   │");
            System.out.println("│  5. Eliminar medicamento                 │");
            System.out.println("│  0. Volver al menú principal             │");
            System.out.println("└─────────────────────────────────────────┘");

            int opcion = Validador.leerEntero(scanner, "  Selecciona una opción: ");
            try {
                switch (opcion) {
                    case 1 -> agregarMedicamento();
                    case 2 -> consultarMedicamento();
                    case 3 -> listarMedicamentos();
                    case 4 -> editarMedicamento();
                    case 5 -> eliminarMedicamento();
                    case 0 -> activo = false;
                    default -> System.out.println("  ⚠ Opción no válida.");
                }
            } catch (IOException e) {
                System.out.println("  ✖ Error de archivo: " + e.getMessage());
            } catch (IllegalArgumentException | NoSuchElementException e) {
                System.out.println("  ✖ Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("  ✖ Error inesperado: " + e.getMessage());
            }
        }
    }

    /**
     * Solicita datos al usuario y agrega un nuevo medicamento/insumo al sistema.
     *
     * @throws IOException Si ocurre un error al guardar en el archivo CSV.
     */
    private static void agregarMedicamento() throws IOException {
        System.out.println("\n  ── Nuevo Medicamento/Insumo ──");
        int id = Validador.leerEnteroPositivo(scanner, "  ID Medicamento : ");
        String nombre = Validador.leerTextoNoVacio(scanner, "  Nombre         : ");
        String desc = Validador.leerTextoNoVacio(scanner, "  Descripción    : ");
        double precio = Validador.leerDoublePositivo(scanner, "  Precio ($)     : ");
        int stock = Validador.leerEnteroNoNegativo(scanner, "  Stock          : ");
        String cat = Validador.leerTextoNoVacio(scanner, "  Categoría      : ");
        int idSuc = Validador.leerEnteroPositivo(scanner, "  ID Sucursal    : ");
        medicamentoDAO.agregar(new Medicamento(id, nombre, desc, precio, stock, cat, idSuc));
    }

    /**
     * Solicita un ID y muestra los datos del medicamento correspondiente.
     *
     * @throws IOException Si ocurre un error al leer el archivo CSV.
     */
    private static void consultarMedicamento() throws IOException {
        int id = Validador.leerEnteroPositivo(scanner, "\n  ID a consultar: ");
        Medicamento m = medicamentoDAO.buscarPorId(id);
        if (m != null) System.out.println("\n" + m);
        else System.out.println("  ✖ No se encontró medicamento con ID: " + id);
    }

    /**
     * Muestra todos los medicamentos/insumos registrados en el sistema.
     *
     * @throws IOException Si ocurre un error al leer el archivo CSV.
     */
    private static void listarMedicamentos() throws IOException {
        List<Medicamento> lista = medicamentoDAO.obtenerTodos();
        if (lista.isEmpty()) {
            System.out.println("\n  No hay medicamentos registrados.");
        } else {
            System.out.println("\n  Total: " + lista.size() + " medicamento(s)");
            lista.forEach(m -> System.out.println("\n" + m));
        }
    }

    /**
     * Solicita un ID y permite editar los datos del medicamento correspondiente.
     *
     * @throws IOException Si ocurre un error al leer/escribir el archivo CSV.
     */
    private static void editarMedicamento() throws IOException {
        int id = Validador.leerEnteroPositivo(scanner, "\n  ID a editar: ");
        Medicamento existente = medicamentoDAO.buscarPorId(id);
        if (existente == null) {
            System.out.println("  ✖ No se encontró medicamento con ID: " + id);
            return;
        }
        System.out.println("  (Deja vacío y presiona Enter para conservar el valor actual)");
        System.out.print("  Nombre [" + existente.getNombre() + "]: ");
        String nombre = scanner.nextLine().trim();
        System.out.print("  Descripción [" + existente.getDescripcion() + "]: ");
        String desc = scanner.nextLine().trim();
        System.out.print("  Precio [" + existente.getPrecio() + "] (0 para no cambiar): ");
        String precioStr = scanner.nextLine().trim();
        System.out.print("  Stock [" + existente.getStock() + "] (-1 para no cambiar): ");
        String stockStr = scanner.nextLine().trim();
        System.out.print("  Categoría [" + existente.getCategoria() + "]: ");
        String cat = scanner.nextLine().trim();
        System.out.print("  ID Sucursal [" + existente.getIdSucursal() + "] (0 para no cambiar): ");
        String idSucStr = scanner.nextLine().trim();

        if (!nombre.isEmpty()) existente.setNombre(nombre);
        if (!desc.isEmpty()) existente.setDescripcion(desc);
        if (!precioStr.isEmpty()) {
            try {
                double precio = Double.parseDouble(precioStr);
                if (precio > 0) existente.setPrecio(precio);
            } catch (NumberFormatException e) {
                System.out.println("  ⚠ Precio inválido, se conserva el valor anterior.");
            }
        }
        if (!stockStr.isEmpty()) {
            try {
                int stock = Integer.parseInt(stockStr);
                if (stock >= 0) existente.setStock(stock);
            } catch (NumberFormatException e) {
                System.out.println("  ⚠ Stock inválido, se conserva el valor anterior.");
            }
        }
        if (!cat.isEmpty()) existente.setCategoria(cat);
        if (!idSucStr.isEmpty()) {
            try {
                int idSuc = Integer.parseInt(idSucStr);
                if (idSuc > 0) existente.setIdSucursal(idSuc);
            } catch (NumberFormatException e) {
                System.out.println("  ⚠ ID de sucursal inválido, se conserva el valor anterior.");
            }
        }
        medicamentoDAO.editar(existente);
    }

    /**
     * Solicita un ID y elimina el medicamento correspondiente del sistema.
     *
     * @throws IOException Si ocurre un error al leer/escribir el archivo CSV.
     */
    private static void eliminarMedicamento() throws IOException {
        int id = Validador.leerEnteroPositivo(scanner, "\n  ID a eliminar: ");
        System.out.print("  ¿Confirmas eliminar el medicamento con ID " + id + "? (s/n): ");
        String confirmacion = scanner.nextLine().trim().toLowerCase();
        if (confirmacion.equals("s")) {
            medicamentoDAO.eliminar(id);
        } else {
            System.out.println("  Operación cancelada.");
        }
    }

    // ═══════════════════════════════════════════════════
    //                  MENÚ CLIENTES
    // ═══════════════════════════════════════════════════

    /**
     * Muestra y gestiona el submenú de clientes.
     */
    private static void menuClientes() {
        boolean activo = true;
        while (activo) {
            System.out.println("\n┌─────────────────────────────────────────┐");
            System.out.println("│           GESTIÓN DE CLIENTES            │");
            System.out.println("├─────────────────────────────────────────┤");
            System.out.println("│  1. Agregar cliente                      │");
            System.out.println("│  2. Consultar cliente por ID             │");
            System.out.println("│  3. Listar todos los clientes            │");
            System.out.println("│  4. Editar cliente                       │");
            System.out.println("│  5. Eliminar cliente                     │");
            System.out.println("│  0. Volver al menú principal             │");
            System.out.println("└─────────────────────────────────────────┘");

            int opcion = Validador.leerEntero(scanner, "  Selecciona una opción: ");
            try {
                switch (opcion) {
                    case 1 -> agregarCliente();
                    case 2 -> consultarCliente();
                    case 3 -> listarClientes();
                    case 4 -> editarCliente();
                    case 5 -> eliminarCliente();
                    case 0 -> activo = false;
                    default -> System.out.println("  ⚠ Opción no válida.");
                }
            } catch (IOException e) {
                System.out.println("  ✖ Error de archivo: " + e.getMessage());
            } catch (IllegalArgumentException | NoSuchElementException e) {
                System.out.println("  ✖ Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("  ✖ Error inesperado: " + e.getMessage());
            }
        }
    }

    /**
     * Solicita datos al usuario y agrega un nuevo cliente al sistema.
     *
     * @throws IOException Si ocurre un error al guardar en el archivo CSV.
     */
    private static void agregarCliente() throws IOException {
        System.out.println("\n  ── Nuevo Cliente ──");
        int id = Validador.leerEnteroPositivo(scanner, "  ID Cliente        : ");
        String nombre = Validador.leerTextoNoVacio(scanner, "  Nombre completo   : ");
        String correo = Validador.leerTextoNoVacio(scanner, "  Correo electrónico: ");
        String tel = Validador.leerTextoNoVacio(scanner, "  Teléfono          : ");
        String fechaNac = Validador.leerTextoNoVacio(scanner, "  Fecha nacimiento  : ");
        clienteDAO.agregar(new Cliente(id, nombre, correo, tel, fechaNac));
    }

    /**
     * Solicita un ID y muestra los datos del cliente correspondiente.
     *
     * @throws IOException Si ocurre un error al leer el archivo CSV.
     */
    private static void consultarCliente() throws IOException {
        int id = Validador.leerEnteroPositivo(scanner, "\n  ID a consultar: ");
        Cliente c = clienteDAO.buscarPorId(id);
        if (c != null) System.out.println("\n" + c);
        else System.out.println("  ✖ No se encontró cliente con ID: " + id);
    }

    /**
     * Muestra todos los clientes registrados en el sistema.
     *
     * @throws IOException Si ocurre un error al leer el archivo CSV.
     */
    private static void listarClientes() throws IOException {
        List<Cliente> lista = clienteDAO.obtenerTodos();
        if (lista.isEmpty()) {
            System.out.println("\n  No hay clientes registrados.");
        } else {
            System.out.println("\n  Total: " + lista.size() + " cliente(s)");
            lista.forEach(c -> System.out.println("\n" + c));
        }
    }

    /**
     * Solicita un ID y permite editar los datos del cliente correspondiente.
     *
     * @throws IOException Si ocurre un error al leer/escribir el archivo CSV.
     */
    private static void editarCliente() throws IOException {
        int id = Validador.leerEnteroPositivo(scanner, "\n  ID a editar: ");
        Cliente existente = clienteDAO.buscarPorId(id);
        if (existente == null) {
            System.out.println("  ✖ No se encontró cliente con ID: " + id);
            return;
        }
        System.out.println("  (Deja vacío y presiona Enter para conservar el valor actual)");
        System.out.print("  Nombre [" + existente.getNombre() + "]: ");
        String nombre = scanner.nextLine().trim();
        System.out.print("  Correo [" + existente.getCorreo() + "]: ");
        String correo = scanner.nextLine().trim();
        System.out.print("  Teléfono [" + existente.getTelefono() + "]: ");
        String tel = scanner.nextLine().trim();
        System.out.print("  Fecha Nacimiento [" + existente.getFechaNacimiento() + "]: ");
        String fecha = scanner.nextLine().trim();

        if (!nombre.isEmpty()) existente.setNombre(nombre);
        if (!correo.isEmpty()) existente.setCorreo(correo);
        if (!tel.isEmpty()) existente.setTelefono(tel);
        if (!fecha.isEmpty()) existente.setFechaNacimiento(fecha);

        clienteDAO.editar(existente);
    }

    /**
     * Solicita un ID y elimina el cliente correspondiente del sistema.
     *
     * @throws IOException Si ocurre un error al leer/escribir el archivo CSV.
     */
    private static void eliminarCliente() throws IOException {
        int id = Validador.leerEnteroPositivo(scanner, "\n  ID a eliminar: ");
        System.out.print("  ¿Confirmas eliminar el cliente con ID " + id + "? (s/n): ");
        String confirmacion = scanner.nextLine().trim().toLowerCase();
        if (confirmacion.equals("s")) {
            clienteDAO.eliminar(id);
        } else {
            System.out.println("  Operación cancelada.");
        }
    }

    // ═══════════════════════════════════════════════════
    //                   UTILIDADES
    // ═══════════════════════════════════════════════════

    /**
     * Imprime el banner de bienvenida al iniciar la aplicación.
     */
    private static void imprimirBienvenida() {
        System.out.println("╔══════════════════════════════════════════════════╗");
        System.out.println("║                                                  ║");
        System.out.println("║     🏥  SISTEMA DE GESTIÓN - XIAO MAO           ║");
        System.out.println("║         Cadena de Farmacias                      ║");
        System.out.println("║                                                  ║");
        System.out.println("║     Datos persistidos en archivos .CSV           ║");
        System.out.println("║     Carpeta: datos/                              ║");
        System.out.println("║                                                  ║");
        System.out.println("╚══════════════════════════════════════════════════╝");
    }
}
