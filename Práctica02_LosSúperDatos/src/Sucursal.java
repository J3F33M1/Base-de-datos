/**
 * Clase Sucursal
 *
 * <p>Representa una sucursal de la cadena de farmacias "Xiao Mao".
 * Contiene los atributos principales de una sucursal y métodos
 * para convertir los datos a formato CSV y viceversa.</p>
 *
 * @author Equipo Fundamentos de Bases de Datos
 * @version 1.0
 */
public class Sucursal {

    /** Identificador único de la sucursal (llave primaria). */
    private int idSucursal;

    /** Nombre de la sucursal. */
    private String nombre;

    /** Dirección física de la sucursal. */
    private String direccion;

    /** Teléfono de contacto. */
    private String telefono;

    /** Ciudad donde se encuentra la sucursal. */
    private String ciudad;

    /** Encabezado CSV para el archivo de sucursales. */
    public static final String ENCABEZADO = "idSucursal,nombre,direccion,telefono,ciudad";

    /**
     * Constructor completo de Sucursal.
     *
     * @param idSucursal Identificador único (número entero positivo).
     * @param nombre     Nombre de la sucursal.
     * @param direccion  Dirección física.
     * @param telefono   Número telefónico.
     * @param ciudad     Ciudad de ubicación.
     */
    public Sucursal(int idSucursal, String nombre, String direccion, String telefono, String ciudad) {
        this.idSucursal = idSucursal;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ciudad = ciudad;
    }

    // ──────────────────── Getters y Setters ────────────────────

    /** @return El identificador único de la sucursal. */
    public int getIdSucursal() { return idSucursal; }

    /** @param idSucursal Nuevo identificador. */
    public void setIdSucursal(int idSucursal) { this.idSucursal = idSucursal; }

    /** @return El nombre de la sucursal. */
    public String getNombre() { return nombre; }

    /** @param nombre Nuevo nombre. */
    public void setNombre(String nombre) { this.nombre = nombre; }

    /** @return La dirección de la sucursal. */
    public String getDireccion() { return direccion; }

    /** @param direccion Nueva dirección. */
    public void setDireccion(String direccion) { this.direccion = direccion; }

    /** @return El teléfono de la sucursal. */
    public String getTelefono() { return telefono; }

    /** @param telefono Nuevo teléfono. */
    public void setTelefono(String telefono) { this.telefono = telefono; }

    /** @return La ciudad de la sucursal. */
    public String getCiudad() { return ciudad; }

    /** @param ciudad Nueva ciudad. */
    public void setCiudad(String ciudad) { this.ciudad = ciudad; }

    // ──────────────────── Métodos CSV ────────────────────

    /**
     * Convierte el objeto Sucursal a una línea en formato CSV.
     *
     * @return Cadena con los campos separados por comas.
     */
    public String aCSV() {
        return idSucursal + "," + nombre + "," + direccion + "," + telefono + "," + ciudad;
    }

    /**
     * Crea un objeto Sucursal a partir de una línea CSV.
     *
     * @param linea Línea leída del archivo CSV.
     * @return Objeto Sucursal con los datos de la línea.
     * @throws IllegalArgumentException Si la línea no tiene el formato correcto.
     * @throws NumberFormatException    Si el ID no es un número entero válido.
     */
    public static Sucursal desdeCSV(String linea) {
        String[] partes = linea.split(",", -1);
        if (partes.length != 5) {
            throw new IllegalArgumentException("Formato CSV inválido para Sucursal: " + linea);
        }
        int id = Integer.parseInt(partes[0].trim());
        return new Sucursal(id, partes[1].trim(), partes[2].trim(), partes[3].trim(), partes[4].trim());
    }

    /**
     * Representación legible de la sucursal para mostrar en consola.
     *
     * @return Cadena formateada con todos los campos de la sucursal.
     */
    @Override
    public String toString() {
        return "╔══ SUCURSAL ══════════════════════════╗\n" +
               "  ID        : " + idSucursal + "\n" +
               "  Nombre    : " + nombre + "\n" +
               "  Dirección : " + direccion + "\n" +
               "  Teléfono  : " + telefono + "\n" +
               "  Ciudad    : " + ciudad + "\n" +
               "╚══════════════════════════════════════╝";
    }
}
