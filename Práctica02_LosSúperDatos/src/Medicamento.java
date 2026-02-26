/**
 * Clase Medicamento
 *
 * <p>Representa un medicamento o insumo disponible en la cadena de farmacias "Xiao Mao".
 * Almacena información como nombre, precio, stock, categoría y la sucursal a la que pertenece.</p>
 *
 * @author Equipo Fundamentos de Bases de Datos
 * @version 1.0
 */
public class Medicamento {

    /** Identificador único del medicamento (llave primaria). */
    private int idMedicamento;

    /** Nombre comercial del medicamento o insumo. */
    private String nombre;

    /** Descripción del medicamento (uso, presentación, etc.). */
    private String descripcion;

    /** Precio de venta al público (valor numérico, mayor a 0). */
    private double precio;

    /** Cantidad disponible en inventario (número entero, mayor o igual a 0). */
    private int stock;

    /** Categoría del medicamento (ej. analgésico, antibiótico, insumo médico). */
    private String categoria;

    /** ID de la sucursal a la que pertenece este medicamento. */
    private int idSucursal;

    /** Encabezado CSV para el archivo de medicamentos. */
    public static final String ENCABEZADO = "idMedicamento,nombre,descripcion,precio,stock,categoria,idSucursal";

    /**
     * Constructor completo de Medicamento.
     *
     * @param idMedicamento Identificador único.
     * @param nombre        Nombre del medicamento.
     * @param descripcion   Descripción del medicamento.
     * @param precio        Precio de venta (double).
     * @param stock         Cantidad en inventario (int).
     * @param categoria     Categoría del producto.
     * @param idSucursal    ID de la sucursal relacionada.
     */
    public Medicamento(int idMedicamento, String nombre, String descripcion,
                       double precio, int stock, String categoria, int idSucursal) {
        this.idMedicamento = idMedicamento;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
        this.idSucursal = idSucursal;
    }

    // ──────────────────── Getters y Setters ────────────────────

    /** @return El identificador único del medicamento. */
    public int getIdMedicamento() { return idMedicamento; }

    /** @param idMedicamento Nuevo identificador. */
    public void setIdMedicamento(int idMedicamento) { this.idMedicamento = idMedicamento; }

    /** @return El nombre del medicamento. */
    public String getNombre() { return nombre; }

    /** @param nombre Nuevo nombre. */
    public void setNombre(String nombre) { this.nombre = nombre; }

    /** @return La descripción del medicamento. */
    public String getDescripcion() { return descripcion; }

    /** @param descripcion Nueva descripción. */
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    /** @return El precio del medicamento. */
    public double getPrecio() { return precio; }

    /** @param precio Nuevo precio (debe ser mayor a 0). */
    public void setPrecio(double precio) { this.precio = precio; }

    /** @return El stock disponible. */
    public int getStock() { return stock; }

    /** @param stock Nuevo stock (debe ser >= 0). */
    public void setStock(int stock) { this.stock = stock; }

    /** @return La categoría del medicamento. */
    public String getCategoria() { return categoria; }

    /** @param categoria Nueva categoría. */
    public void setCategoria(String categoria) { this.categoria = categoria; }

    /** @return El ID de la sucursal asociada. */
    public int getIdSucursal() { return idSucursal; }

    /** @param idSucursal Nuevo ID de sucursal. */
    public void setIdSucursal(int idSucursal) { this.idSucursal = idSucursal; }

    // ──────────────────── Métodos CSV ────────────────────

    /**
     * Convierte el objeto Medicamento a una línea en formato CSV.
     *
     * @return Cadena con los campos separados por comas.
     */
    public String aCSV() {
        return idMedicamento + "," + nombre + "," + descripcion + "," +
               precio + "," + stock + "," + categoria + "," + idSucursal;
    }

    /**
     * Crea un objeto Medicamento a partir de una línea CSV.
     *
     * @param linea Línea leída del archivo CSV.
     * @return Objeto Medicamento con los datos de la línea.
     * @throws IllegalArgumentException Si la línea no tiene el formato correcto.
     * @throws NumberFormatException    Si algún campo numérico no es válido.
     */
    public static Medicamento desdeCSV(String linea) {
        String[] partes = linea.split(",", -1);
        if (partes.length != 7) {
            throw new IllegalArgumentException("Formato CSV inválido para Medicamento: " + linea);
        }
        int id = Integer.parseInt(partes[0].trim());
        double precio = Double.parseDouble(partes[3].trim());
        int stock = Integer.parseInt(partes[4].trim());
        int idSuc = Integer.parseInt(partes[6].trim());
        return new Medicamento(id, partes[1].trim(), partes[2].trim(), precio, stock, partes[5].trim(), idSuc);
    }

    /**
     * Representación legible del medicamento para mostrar en consola.
     *
     * @return Cadena formateada con todos los campos.
     */
    @Override
    public String toString() {
        return "╔══ MEDICAMENTO / INSUMO ══════════════╗\n" +
               "  ID          : " + idMedicamento + "\n" +
               "  Nombre      : " + nombre + "\n" +
               "  Descripción : " + descripcion + "\n" +
               "  Precio      : $" + String.format("%.2f", precio) + "\n" +
               "  Stock       : " + stock + " unidades\n" +
               "  Categoría   : " + categoria + "\n" +
               "  Sucursal ID : " + idSucursal + "\n" +
               "╚══════════════════════════════════════╝";
    }
}
