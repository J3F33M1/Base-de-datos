import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Clase principal de la aplicacion Farmacia Xiao Mao.
 * <p>
 * Contiene el punto de entrada (main) y la logica de la interfaz de usuario en consola
 * para gestionar el CRUD de sucursales, medicamentos y clientes.
 * </p>
 */
public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final SucursalDAO sucursalDAO = new SucursalDAO();
    private static final MedicamentoDAO medicamentoDAO = new MedicamentoDAO();
    private static final ClienteDAO clienteDAO = new ClienteDAO();

    /**
     * Punto de entrada de la aplicacion.
     * @param args Argumentos de la linea de comandos.
     */
    public static void main(String[] args) {
        System.out.println("=== Farmacia Xiao Mao ===");
        boolean activo = true;
        while (activo) {
            System.out.println("\nMenu principal");
            System.out.println(" 1) Gestionar sucursales");
            System.out.println(" 2) Gestionar medicamentos / insumos");
            System.out.println(" 3) Gestionar clientes");
            System.out.println(" 0) Salir");
            int opcion = Validador.leerEntero(scanner, "Elige una opcion: ");
            switch (opcion) {
                case 1 -> menuSucursales();
                case 2 -> menuMedicamentos();
                case 3 -> menuClientes();
                case 0 -> activo = false;
                default -> System.out.println("Opcion no valida.");
            }
        }
        System.out.println("Fin del programa.");
        scanner.close();
    }

    // Parte de Sucursales
    /**
     * Muestra el submenu para la gestion de sucursales.
     */
    private static void menuSucursales() {
        boolean activo = true;
        while (activo) {
            System.out.println("\nSucursales");
            System.out.println(" 1) Agregar");
            System.out.println(" 2) Consultar por ID");
            System.out.println(" 3) Listar todas");
            System.out.println(" 4) Editar");
            System.out.println(" 5) Eliminar");
            System.out.println(" 0) Volver");
            int opcion = Validador.leerEntero(scanner, "Opcion: ");
            try {
                switch (opcion) {
                    case 1 -> agregarSucursal();
                    case 2 -> consultarSucursal();
                    case 3 -> listarSucursales();
                    case 4 -> editarSucursal();
                    case 5 -> eliminarSucursal();
                    case 0 -> activo = false;
                    default -> System.out.println("Opcion no valida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    /**
     * Solicita datos al usuario y agrega una nueva sucursal.
     * @throws IOException Si ocurre un error al guardar.
     */
    private static void agregarSucursal() throws IOException {
        System.out.println("\nNueva sucursal");
        int id = Validador.leerEnteroPositivo(scanner, "ID: ");
        String nombre = Validador.leerTextoNoVacio(scanner, "Nombre: ");
        String calle = Validador.leerTextoNoVacio(scanner, "Calle: ");
        String numExt = Validador.leerTextoNoVacio(scanner, "Numero exterior: ");
        String numInt = Validador.leerTextoNoVacio(scanner, "Numero interior (NA si no aplica): ");
        String colonia = Validador.leerTextoNoVacio(scanner, "Colonia: ");
        String estado = Validador.leerTextoNoVacio(scanner, "Estado: ");
        String tel = Validador.leerTextoNoVacio(scanner, "Telefono: ");
        String horaIni = Validador.leerTextoNoVacio(scanner, "Horario apertura (ej 08:00): ");
        String horaFin = Validador.leerTextoNoVacio(scanner, "Horario cierre (ej 21:00): ");
        sucursalDAO.agregar(new Sucursal(id, nombre, calle, numExt, numInt, colonia, estado, tel, horaIni, horaFin));
    }

    /**
     * Consulta y muestra una sucursal por su ID.
     * @throws IOException Si ocurre un error de lectura.
     */
    private static void consultarSucursal() throws IOException {
        int id = Validador.leerEnteroPositivo(scanner, "ID a consultar: ");
        Sucursal s = sucursalDAO.buscarPorId(id);
        if (s == null) {
            System.out.println("No existe la sucursal.");
        } else {
            System.out.println("\n" + s);
        }
    }

    /**
     * Lista todas las sucursales registradas.
     * @throws IOException Si ocurre un error de lectura.
     */
    private static void listarSucursales() throws IOException {
        List<Sucursal> lista = sucursalDAO.obtenerTodas();
        if (lista.isEmpty()) {
            System.out.println("No hay sucursales registradas.");
            return;
        }
        System.out.println("Total: " + lista.size());
        for (Sucursal s : lista) {
            System.out.println("\n" + s);
        }
    }

    /**
     * Permite editar los campos de una sucursal existente.
     * @throws IOException Si ocurre un error de lectura/escritura.
     */
    private static void editarSucursal() throws IOException {
        int id = Validador.leerEnteroPositivo(scanner, "ID a editar: ");
        Sucursal existente = sucursalDAO.buscarPorId(id);
        if (existente == null) {
            System.out.println("No existe la sucursal.");
            return;
        }
        System.out.println("Deja vacio para conservar el valor actual.");
        System.out.print("Nombre [" + existente.getNombre() + "]: ");
        String nombre = scanner.nextLine().trim();
        System.out.print("Calle [" + existente.getCalle() + "]: ");
        String calle = scanner.nextLine().trim();
        System.out.print("Numero exterior [" + existente.getNumeroExterior() + "]: ");
        String numExt = scanner.nextLine().trim();
        System.out.print("Numero interior [" + existente.getNumeroInterior() + "]: ");
        String numInt = scanner.nextLine().trim();
        System.out.print("Colonia [" + existente.getColonia() + "]: ");
        String colonia = scanner.nextLine().trim();
        System.out.print("Estado [" + existente.getEstado() + "]: ");
        String estado = scanner.nextLine().trim();
        System.out.print("Telefono [" + existente.getTelefono() + "]: ");
        String tel = scanner.nextLine().trim();
        System.out.print("Horario apertura [" + existente.getHorarioApertura() + "]: ");
        String horaIni = scanner.nextLine().trim();
        System.out.print("Horario cierre [" + existente.getHorarioCierre() + "]: ");
        String horaFin = scanner.nextLine().trim();

        if (!nombre.isEmpty()) existente.setNombre(nombre);
        if (!calle.isEmpty()) existente.setCalle(calle);
        if (!numExt.isEmpty()) existente.setNumeroExterior(numExt);
        if (!numInt.isEmpty()) existente.setNumeroInterior(numInt);
        if (!colonia.isEmpty()) existente.setColonia(colonia);
        if (!estado.isEmpty()) existente.setEstado(estado);
        if (!tel.isEmpty()) existente.setTelefono(tel);
        if (!horaIni.isEmpty()) existente.setHorarioApertura(horaIni);
        if (!horaFin.isEmpty()) existente.setHorarioCierre(horaFin);

        sucursalDAO.editar(existente);
    }

    /**
     * Elimina una sucursal si no tiene dependencias (medicamentos o clientes).
     * @throws IOException Si ocurre un error de lectura/escritura.
     */
    private static void eliminarSucursal() throws IOException {
        int id = Validador.leerEnteroPositivo(scanner, "ID a eliminar: ");
        // proteger relacion: no se elimina si hay registros dependientes
        List<Medicamento> meds = medicamentoDAO.obtenerTodos();
        boolean tieneMeds = meds.stream().anyMatch(m -> m.getIdSucursal() == id);
        List<Cliente> clientes = clienteDAO.obtenerTodos();
        boolean tieneClientes = clientes.stream().anyMatch(c -> c.getIdSucursal() == id);
        if (tieneMeds || tieneClientes) {
            System.out.println("No se puede eliminar. Hay medicamentos o clientes ligados a esta sucursal.");
            return;
        }
        sucursalDAO.eliminar(id);
    }

    // Parte de Medicamentos / Insumos
    /**
     * Muestra el submenu para la gestion de medicamentos.
     */
    private static void menuMedicamentos() {
        boolean activo = true;
        while (activo) {
            System.out.println("\nMedicamentos / Insumos");
            System.out.println(" 1) Agregar");
            System.out.println(" 2) Consultar por ID");
            System.out.println(" 3) Listar todos");
            System.out.println(" 4) Editar");
            System.out.println(" 5) Eliminar");
            System.out.println(" 0) Volver");
            int opcion = Validador.leerEntero(scanner, "Opcion: ");
            try {
                switch (opcion) {
                    case 1 -> agregarMedicamento();
                    case 2 -> consultarMedicamento();
                    case 3 -> listarMedicamentos();
                    case 4 -> editarMedicamento();
                    case 5 -> eliminarMedicamento();
                    case 0 -> activo = false;
                    default -> System.out.println("Opcion no valida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    /**
     * Solicita datos al usuario y agrega un nuevo medicamento.
     * @throws IOException Si ocurre un error al guardar.
     */
    private static void agregarMedicamento() throws IOException {
        System.out.println("\nNuevo medicamento/insumo");
        int id = Validador.leerEnteroPositivo(scanner, "ID: ");
        String nombreCom = Validador.leerTextoNoVacio(scanner, "Nombre comercial: ");
        String nombreGen = Validador.leerTextoNoVacio(scanner, "Nombre generico: ");
        String forma = Validador.leerTextoNoVacio(scanner, "Forma farmaceutica (tableta, jarabe, etc): ");
        String conc = Validador.leerTextoNoVacio(scanner, "Concentracion (ej 500 mg): ");
        String via = Validador.leerTextoNoVacio(scanner, "Via de administracion: ");
        String control = Validador.leerTextoNoVacio(scanner, "Tipo de control (receta, venta libre, controlado): ");
        int stock = Validador.leerEnteroNoNegativo(scanner, "Stock: ");
        double precioPub = Validador.leerDoublePositivo(scanner, "Precio publico: ");
        double precioProv = Validador.leerDoublePositivo(scanner, "Precio proveedor: ");
        String cad = Validador.leerTextoNoVacio(scanner, "Fecha de caducidad (AAAA-MM-DD): ");
        int idSucursal = Validador.leerEnteroPositivo(scanner, "ID de sucursal: ");
        validarSucursalExiste(idSucursal);
        medicamentoDAO.agregar(new Medicamento(id, nombreCom, nombreGen, forma, conc, via, control,
                stock, precioPub, precioProv, cad, idSucursal));
    }

    /**
     * Consulta y muestra un medicamento por su ID.
     * @throws IOException Si ocurre un error de lectura.
     */
    private static void consultarMedicamento() throws IOException {
        int id = Validador.leerEnteroPositivo(scanner, "ID a consultar: ");
        Medicamento m = medicamentoDAO.buscarPorId(id);
        if (m == null) System.out.println("No existe el medicamento.");
        else System.out.println("\n" + m);
    }

    /**
     * Lista todos los medicamentos registrados.
     * @throws IOException Si ocurre un error de lectura.
     */
    private static void listarMedicamentos() throws IOException {
        List<Medicamento> lista = medicamentoDAO.obtenerTodos();
        if (lista.isEmpty()) {
            System.out.println("No hay medicamentos registrados.");
            return;
        }
        System.out.println("Total: " + lista.size());
        for (Medicamento m : lista) {
            System.out.println("\n" + m);
        }
    }

    /**
     * Permite editar los campos de un medicamento existente.
     * @throws IOException Si ocurre un error de lectura/escritura.
     */
    private static void editarMedicamento() throws IOException {
        int id = Validador.leerEnteroPositivo(scanner, "ID a editar: ");
        Medicamento existente = medicamentoDAO.buscarPorId(id);
        if (existente == null) {
            System.out.println("No existe el medicamento.");
            return;
        }
        System.out.println("Deja vacio para conservar el valor actual.");
        System.out.print("Nombre comercial [" + existente.getNombreComercial() + "]: ");
        String nombreCom = scanner.nextLine().trim();
        System.out.print("Nombre generico [" + existente.getNombreGenerico() + "]: ");
        String nombreGen = scanner.nextLine().trim();
        System.out.print("Forma farmaceutica [" + existente.getFormaFarmaceutica() + "]: ");
        String forma = scanner.nextLine().trim();
        System.out.print("Concentracion [" + existente.getConcentracion() + "]: ");
        String conc = scanner.nextLine().trim();
        System.out.print("Via administracion [" + existente.getViaAdministracion() + "]: ");
        String via = scanner.nextLine().trim();
        System.out.print("Tipo control [" + existente.getTipoControl() + "]: ");
        String control = scanner.nextLine().trim();
        System.out.print("Stock [" + existente.getStock() + "]: ");
        String stockTxt = scanner.nextLine().trim();
        System.out.print("Precio publico [" + existente.getPrecioPublico() + "]: ");
        String precioPubTxt = scanner.nextLine().trim();
        System.out.print("Precio proveedor [" + existente.getPrecioProveedor() + "]: ");
        String precioProvTxt = scanner.nextLine().trim();
        System.out.print("Caducidad [" + existente.getFechaCaducidad() + "]: ");
        String cad = scanner.nextLine().trim();
        System.out.print("ID sucursal [" + existente.getIdSucursal() + "]: ");
        String idSucTxt = scanner.nextLine().trim();

        if (!nombreCom.isEmpty()) existente.setNombreComercial(nombreCom);
        if (!nombreGen.isEmpty()) existente.setNombreGenerico(nombreGen);
        if (!forma.isEmpty()) existente.setFormaFarmaceutica(forma);
        if (!conc.isEmpty()) existente.setConcentracion(conc);
        if (!via.isEmpty()) existente.setViaAdministracion(via);
        if (!control.isEmpty()) existente.setTipoControl(control);
        if (!stockTxt.isEmpty()) existente.setStock(Integer.parseInt(stockTxt));
        if (!precioPubTxt.isEmpty()) existente.setPrecioPublico(Double.parseDouble(precioPubTxt));
        if (!precioProvTxt.isEmpty()) existente.setPrecioProveedor(Double.parseDouble(precioProvTxt));
        if (!cad.isEmpty()) existente.setFechaCaducidad(cad);
        if (!idSucTxt.isEmpty()) {
            int nuevoIdSuc = Integer.parseInt(idSucTxt);
            validarSucursalExiste(nuevoIdSuc);
            existente.setIdSucursal(nuevoIdSuc);
        }

        medicamentoDAO.editar(existente);
    }

    /**
     * Elimina un medicamento por su ID.
     * @throws IOException Si ocurre un error de lectura/escritura.
     */
    private static void eliminarMedicamento() throws IOException {
        int id = Validador.leerEnteroPositivo(scanner, "ID a eliminar: ");
        medicamentoDAO.eliminar(id);
    }

    // Parte de Clientes
    /**
     * Muestra el submenu para la gestion de clientes.
     */
    private static void menuClientes() {
        boolean activo = true;
        while (activo) {
            System.out.println("\nClientes");
            System.out.println(" 1) Agregar");
            System.out.println(" 2) Consultar por ID");
            System.out.println(" 3) Listar todos");
            System.out.println(" 4) Editar");
            System.out.println(" 5) Eliminar");
            System.out.println(" 0) Volver");
            int opcion = Validador.leerEntero(scanner, "Opcion: ");
            try {
                switch (opcion) {
                    case 1 -> agregarCliente();
                    case 2 -> consultarCliente();
                    case 3 -> listarClientes();
                    case 4 -> editarCliente();
                    case 5 -> eliminarCliente();
                    case 0 -> activo = false;
                    default -> System.out.println("Opcion no valida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    /**
     * Solicita datos al usuario y agrega un nuevo cliente.
     * @throws IOException Si ocurre un error al guardar.
     */
    private static void agregarCliente() throws IOException {
        System.out.println("\nNuevo cliente");
        int id = Validador.leerEnteroPositivo(scanner, "ID: ");
        String nombre = Validador.leerTextoNoVacio(scanner, "Nombre: ");
        String apPat = Validador.leerTextoNoVacio(scanner, "Apellido paterno: ");
        String apMat = Validador.leerTextoNoVacio(scanner, "Apellido materno: ");
        String tel = Validador.leerTextoNoVacio(scanner, "Telefono: ");
        String correo = Validador.leerTextoNoVacio(scanner, "Correo: ");
        String fechaNac = Validador.leerTextoNoVacio(scanner, "Fecha de nacimiento (AAAA-MM-DD): ");
        String calle = Validador.leerTextoNoVacio(scanner, "Calle: ");
        String numExt = Validador.leerTextoNoVacio(scanner, "Numero exterior: ");
        String numInt = Validador.leerTextoNoVacio(scanner, "Numero interior (NA si no aplica): ");
        String colonia = Validador.leerTextoNoVacio(scanner, "Colonia: ");
        String estado = Validador.leerTextoNoVacio(scanner, "Estado: ");
        String metodoPago = Validador.leerTextoNoVacio(scanner, "Metodo de pago (efectivo/tarjeta): ");
        int visitas = Validador.leerEnteroNoNegativo(scanner, "Visitas al ano a la sucursal: ");
        int idSucursal = Validador.leerEnteroPositivo(scanner, "ID de sucursal: ");
        validarSucursalExiste(idSucursal);

        clienteDAO.agregar(new Cliente(id, nombre, apPat, apMat, tel, correo, fechaNac,
                calle, numExt, numInt, colonia, estado, metodoPago, visitas, idSucursal));
    }

    /**
     * Consulta y muestra un cliente por su ID.
     * @throws IOException Si ocurre un error de lectura.
     */
    private static void consultarCliente() throws IOException {
        int id = Validador.leerEnteroPositivo(scanner, "ID a consultar: ");
        Cliente c = clienteDAO.buscarPorId(id);
        if (c == null) System.out.println("No existe el cliente.");
        else System.out.println("\n" + c);
    }

    /**
     * Lista todos los clientes registrados.
     * @throws IOException Si ocurre un error de lectura.
     */
    private static void listarClientes() throws IOException {
        List<Cliente> lista = clienteDAO.obtenerTodos();
        if (lista.isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }
        System.out.println("Total: " + lista.size());
        for (Cliente c : lista) {
            System.out.println("\n" + c);
        }
    }

    /**
     * Permite editar los campos de un cliente existente.
     * @throws IOException Si ocurre un error de lectura/escritura.
     */
    private static void editarCliente() throws IOException {
        int id = Validador.leerEnteroPositivo(scanner, "ID a editar: ");
        Cliente existente = clienteDAO.buscarPorId(id);
        if (existente == null) {
            System.out.println("No existe el cliente.");
            return;
        }
        System.out.println("Deja vacio para conservar el valor actual.");
        System.out.print("Nombre [" + existente.getNombre() + "]: ");
        String nombre = scanner.nextLine().trim();
        System.out.print("Apellido paterno [" + existente.getApellidoPaterno() + "]: ");
        String apPat = scanner.nextLine().trim();
        System.out.print("Apellido materno [" + existente.getApellidoMaterno() + "]: ");
        String apMat = scanner.nextLine().trim();
        System.out.print("Telefono [" + existente.getTelefono() + "]: ");
        String tel = scanner.nextLine().trim();
        System.out.print("Correo [" + existente.getCorreo() + "]: ");
        String correo = scanner.nextLine().trim();
        System.out.print("Fecha nacimiento [" + existente.getFechaNacimiento() + "]: ");
        String fecha = scanner.nextLine().trim();
        System.out.print("Calle [" + existente.getCalle() + "]: ");
        String calle = scanner.nextLine().trim();
        System.out.print("Numero exterior [" + existente.getNumeroExterior() + "]: ");
        String numExt = scanner.nextLine().trim();
        System.out.print("Numero interior [" + existente.getNumeroInterior() + "]: ");
        String numInt = scanner.nextLine().trim();
        System.out.print("Colonia [" + existente.getColonia() + "]: ");
        String colonia = scanner.nextLine().trim();
        System.out.print("Estado [" + existente.getEstado() + "]: ");
        String estado = scanner.nextLine().trim();
        System.out.print("Metodo pago [" + existente.getMetodoPago() + "]: ");
        String metodo = scanner.nextLine().trim();
        System.out.print("Visitas al ano [" + existente.getVisitasAnuales() + "]: ");
        String visitasTxt = scanner.nextLine().trim();
        System.out.print("ID sucursal [" + existente.getIdSucursal() + "]: ");
        String idSucTxt = scanner.nextLine().trim();

        if (!nombre.isEmpty()) existente.setNombre(nombre);
        if (!apPat.isEmpty()) existente.setApellidoPaterno(apPat);
        if (!apMat.isEmpty()) existente.setApellidoMaterno(apMat);
        if (!tel.isEmpty()) existente.setTelefono(tel);
        if (!correo.isEmpty()) existente.setCorreo(correo);
        if (!fecha.isEmpty()) existente.setFechaNacimiento(fecha);
        if (!calle.isEmpty()) existente.setCalle(calle);
        if (!numExt.isEmpty()) existente.setNumeroExterior(numExt);
        if (!numInt.isEmpty()) existente.setNumeroInterior(numInt);
        if (!colonia.isEmpty()) existente.setColonia(colonia);
        if (!estado.isEmpty()) existente.setEstado(estado);
        if (!metodo.isEmpty()) existente.setMetodoPago(metodo);
        if (!visitasTxt.isEmpty()) existente.setVisitasAnuales(Integer.parseInt(visitasTxt));
        if (!idSucTxt.isEmpty()) {
            int nuevoId = Integer.parseInt(idSucTxt);
            validarSucursalExiste(nuevoId);
            existente.setIdSucursal(nuevoId);
        }

        clienteDAO.editar(existente);
    }

    /**
     * Elimina un cliente por su ID.
     * @throws IOException Si ocurre un error de lectura/escritura.
     */
    private static void eliminarCliente() throws IOException {
        int id = Validador.leerEnteroPositivo(scanner, "ID a eliminar: ");
        clienteDAO.eliminar(id);
    }

    /**
     * Verifica si una sucursal existe antes de asignarla a otra entidad.
     * @param idSucursal El ID de la sucursal a verificar.
     * @throws IOException Si ocurre un error de lectura.
     * @throws NoSuchElementException Si la sucursal no existe.
     */
    private static void validarSucursalExiste(int idSucursal) throws IOException {
        Sucursal s = sucursalDAO.buscarPorId(idSucursal);
        if (s == null) {
            throw new NoSuchElementException("La sucursal " + idSucursal + " no existe, registra una primero.");
        }
    }
}