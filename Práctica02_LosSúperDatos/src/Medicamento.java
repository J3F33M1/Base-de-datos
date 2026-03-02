/**
 * Entidad que representa un Medicamento o insumo medico.
 * <p>
 * Contiene detalles del producto, precios, stock y la relacion con la
 * sucursal donde se encuentra almacenado.
 * </p>
 */
public class Medicamento {

    private int idMedicamento;
    private String nombreComercial;
    private String nombreGenerico;
    private String formaFarmaceutica;
    private String concentracion;
    private String presentacion;      // Nuevo: caja, frasco, etc.
    private String viaAdministracion;
    private String clasificacion;     // Nuevo: antibiotico, analgesico, etc.
    private String tipoControl;       // controlado, libre, requiere receta
    private String laboratorio;       // Nuevo: fabricante
    private String descripcion;       // Nuevo: color, forma, etc.
    private int stock;
    private double precioPublico;
    private double precioProveedor;
    private String fechaCaducidad;    // AAAA-MM-DD
    private int idSucursal;           // referencia a sucursal

    /** Encabezado CSV para la persistencia de esta entidad. */
    public static final String ENCABEZADO =
            "idMedicamento,nombreComercial,nombreGenerico,formaFarmaceutica,concentracion,presentacion,viaAdministracion,clasificacion,tipoControl,laboratorio,descripcion,stock,precioPublico,precioProveedor,fechaCaducidad,idSucursal";

    /**
     * Constructor completo de la clase Medicamento.
     * @param idMedicamento Identificador unico.
     * @param nombreComercial Nombre comercial del producto.
     * @param nombreGenerico Nombre generico o sustancia activa.
     * @param formaFarmaceutica Forma (tableta, jarabe, etc.).
     * @param concentracion Concentracion del activo.
     * @param presentacion Presentacion (caja con X, frasco, etc.).
     * @param viaAdministracion Via de administracion.
     * @param clasificacion Clasificacion terapeutica.
     * @param tipoControl Tipo de control (libre, receta, controlado).
     * @param laboratorio Laboratorio fabricante.
     * @param descripcion Descripcion fisica.
     * @param stock Cantidad disponible.
     * @param precioPublico Precio de venta al publico.
     * @param precioProveedor Costo de adquisicion.
     * @param fechaCaducidad Fecha de caducidad (AAAA-MM-DD).
     * @param idSucursal ID de la sucursal donde esta el stock.
     */
    public Medicamento(int idMedicamento,
                       String nombreComercial,
                       String nombreGenerico,
                       String formaFarmaceutica,
                       String concentracion,
                       String presentacion,
                       String viaAdministracion,
                       String clasificacion,
                       String tipoControl,
                       String laboratorio,
                       String descripcion,
                       int stock,
                       double precioPublico,
                       double precioProveedor,
                       String fechaCaducidad,
                       int idSucursal) {
        this.idMedicamento = idMedicamento;
        this.nombreComercial = nombreComercial;
        this.nombreGenerico = nombreGenerico;
        this.formaFarmaceutica = formaFarmaceutica;
        this.concentracion = concentracion;
        this.presentacion = presentacion;
        this.viaAdministracion = viaAdministracion;
        this.clasificacion = clasificacion;
        this.tipoControl = tipoControl;
        this.laboratorio = laboratorio;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precioPublico = precioPublico;
        this.precioProveedor = precioProveedor;
        this.fechaCaducidad = fechaCaducidad;
        this.idSucursal = idSucursal;
    }

    // Getters y setters
    public int getIdMedicamento() { return idMedicamento; }
    public void setIdMedicamento(int idMedicamento) { this.idMedicamento = idMedicamento; }
    public String getNombreComercial() { return nombreComercial; }
    public void setNombreComercial(String nombreComercial) { this.nombreComercial = nombreComercial; }
    public String getNombreGenerico() { return nombreGenerico; }
    public void setNombreGenerico(String nombreGenerico) { this.nombreGenerico = nombreGenerico; }
    public String getFormaFarmaceutica() { return formaFarmaceutica; }
    public void setFormaFarmaceutica(String formaFarmaceutica) { this.formaFarmaceutica = formaFarmaceutica; }
    public String getConcentracion() { return concentracion; }
    public void setConcentracion(String concentracion) { this.concentracion = concentracion; }
    public String getPresentacion() { return presentacion; }
    public void setPresentacion(String presentacion) { this.presentacion = presentacion; }
    public String getViaAdministracion() { return viaAdministracion; }
    public void setViaAdministracion(String viaAdministracion) { this.viaAdministracion = viaAdministracion; }
    public String getClasificacion() { return clasificacion; }
    public void setClasificacion(String clasificacion) { this.clasificacion = clasificacion; }
    public String getTipoControl() { return tipoControl; }
    public void setTipoControl(String tipoControl) { this.tipoControl = tipoControl; }
    public String getLaboratorio() { return laboratorio; }
    public void setLaboratorio(String laboratorio) { this.laboratorio = laboratorio; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }
    public double getPrecioPublico() { return precioPublico; }
    public void setPrecioPublico(double precioPublico) { this.precioPublico = precioPublico; }
    public double getPrecioProveedor() { return precioProveedor; }
    public void setPrecioProveedor(double precioProveedor) { this.precioProveedor = precioProveedor; }
    public String getFechaCaducidad() { return fechaCaducidad; }
    public void setFechaCaducidad(String fechaCaducidad) { this.fechaCaducidad = fechaCaducidad; }
    public int getIdSucursal() { return idSucursal; }
    public void setIdSucursal(int idSucursal) { this.idSucursal = idSucursal; }

    // CSV helpers
    /**
     * Convierte el objeto a una cadena con formato CSV.
     * @return Cadena separada por comas con los datos del medicamento.
     */
    public String aCSV() {
        return String.join(",",
                String.valueOf(idMedicamento),
                nombreComercial,
                nombreGenerico,
                formaFarmaceutica,
                concentracion,
                presentacion,
                viaAdministracion,
                clasificacion,
                tipoControl,
                laboratorio,
                descripcion,
                String.valueOf(stock),
                String.valueOf(precioPublico),
                String.valueOf(precioProveedor),
                fechaCaducidad,
                String.valueOf(idSucursal));
    }

    /**
     * Crea una instancia de Medicamento a partir de una linea CSV.
     * @param linea La cadena CSV leida del archivo.
     * @return Un nuevo objeto Medicamento.
     * @throws IllegalArgumentException Si la linea no tiene el formato esperado.
     */
    public static Medicamento desdeCSV(String linea) {
        String[] partes = linea.split(",", -1);
        if (partes.length != 16) {
            throw new IllegalArgumentException("Linea de medicamento invalida: " + linea);
        }
        int id = Integer.parseInt(partes[0].trim());
        int stock = Integer.parseInt(partes[11].trim());
        double precioPublico = Double.parseDouble(partes[12].trim());
        double precioProveedor = Double.parseDouble(partes[13].trim());
        int sucursalId = Integer.parseInt(partes[15].trim());
        return new Medicamento(
                id,
                partes[1].trim(),
                partes[2].trim(),
                partes[3].trim(),
                partes[4].trim(),
                partes[5].trim(),
                partes[6].trim(),
                partes[7].trim(),
                partes[8].trim(),
                partes[9].trim(),
                partes[10].trim(),
                stock,
                precioPublico,
                precioProveedor,
                partes[14].trim(),
                sucursalId
        );
    }

    // Imprimir objeto
    @Override
    public String toString() {
        return "Medicamento #" + idMedicamento + "\n"
                + "  Nombre comercial : " + nombreComercial + "\n"
                + "  Nombre generico  : " + nombreGenerico + "\n"
                + "  Forma farmaceutica: " + formaFarmaceutica + "\n"
                + "  Concentracion    : " + concentracion + "\n"
                + "  Presentacion     : " + presentacion + "\n"
                + "  Via administracion: " + viaAdministracion + "\n"
                + "  Clasificacion    : " + clasificacion + "\n"
                + "  Tipo control     : " + tipoControl + "\n"
                + "  Laboratorio      : " + laboratorio + "\n"
                + "  Descripcion      : " + descripcion + "\n"
                + "  Stock            : " + stock + "\n"
                + "  Precio publico   : " + precioPublico + "\n"
                + "  Precio proveedor : " + precioProveedor + "\n"
                + "  Caducidad        : " + fechaCaducidad + "\n"
                + "  Sucursal ID      : " + idSucursal;
    }
}
