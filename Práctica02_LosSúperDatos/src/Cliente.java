/**
 * Clase Cliente
 * Representa un cliente registrado en la cadena de farmacias "Xiao Mao".
 * Almacena información personal y de contacto del cliente.</p>
 */
public class Cliente {

    /** Identificador único del cliente (llave primaria). */
    private int idCliente;

    /** Nombre completo del cliente. */
    private String nombre;

    /** Correo electrónico del cliente. */
    private String correo;

    /** Número de teléfono del cliente. */
    private String telefono;

    /** Fecha de nacimiento del cliente en formato AAAA-MM-DD. */
    private String fechaNacimiento;

    /** Encabezado CSV para el archivo de clientes. */
    public static final String ENCABEZADO = "idCliente,nombre,correo,telefono,fechaNacimiento";

    /**
     * Constructor completo de Cliente.
     *
     * @param idCliente       Identificador único.
     * @param nombre          Nombre completo.
     * @param correo          Correo electrónico.
     * @param telefono        Número de teléfono.
     * @param fechaNacimiento Fecha de nacimiento (AAAA-MM-DD).
     */
    public Cliente(int idCliente, String nombre, String correo, String telefono, String fechaNacimiento) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.correo = correo;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
    }

    /** @return El identificador único del cliente. */
    public int getIdCliente() { return idCliente; }

    /** @param idCliente Nuevo identificador. */
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    /** @return El nombre del cliente. */
    public String getNombre() { return nombre; }

    /** @param nombre Nuevo nombre. */
    public void setNombre(String nombre) { this.nombre = nombre; }

    /** @return El correo del cliente. */
    public String getCorreo() { return correo; }

    /** @param correo Nuevo correo. */
    public void setCorreo(String correo) { this.correo = correo; }

    /** @return El teléfono del cliente. */
    public String getTelefono() { return telefono; }

    /** @param telefono Nuevo teléfono. */
    public void setTelefono(String telefono) { this.telefono = telefono; }

    /** @return La fecha de nacimiento del cliente. */
    public String getFechaNacimiento() { return fechaNacimiento; }

    /** @param fechaNacimiento Nueva fecha de nacimiento. */
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    // ──────────────────── Métodos CSV ────────────────────

    /**
     * Convierte el objeto Cliente a una línea en formato CSV.
     *
     * @return Cadena con los campos separados por comas.
     */
    public String aCSV() {
        return idCliente + "," + nombre + "," + correo + "," + telefono + "," + fechaNacimiento;
    }

    /**
     * Crea un objeto Cliente a partir de una línea CSV.
     *
     * @param linea Línea leída del archivo CSV.
     * @return Objeto Cliente con los datos de la línea.
     * @throws IllegalArgumentException Si la línea no tiene el formato correcto.
     * @throws NumberFormatException    Si el ID no es un número entero válido.
     */
    public static Cliente desdeCSV(String linea) {
        String[] partes = linea.split(",", -1);
        if (partes.length != 5) {
            throw new IllegalArgumentException("Formato CSV inválido para Cliente: " + linea);
        }
        int id = Integer.parseInt(partes[0].trim());
        return new Cliente(id, partes[1].trim(), partes[2].trim(), partes[3].trim(), partes[4].trim());
    }

    /**
     * Representación legible del cliente para mostrar en consola.
     *
     * @return Cadena formateada con todos los campos del cliente.
     */
    @Override
    public String toString() {
        return "╔══ CLIENTE ═══════════════════════════╗\n" +
               "  ID              : " + idCliente + "\n" +
               "  Nombre          : " + nombre + "\n" +
               "  Correo          : " + correo + "\n" +
               "  Teléfono        : " + telefono + "\n" +
               "  Fecha Nac.      : " + fechaNacimiento + "\n" +
               "╚══════════════════════════════════════╝";
    }
}
