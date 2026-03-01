/**
 * Medicamento o insumo.
 * Incluye relacion con la sucursal que lo almacena para reflejar el
 * control de inventario de cada sucursal.
 */
public class Medicamento {

    private int idMedicamento;
    private String nombreComercial;
    private String nombreGenerico;
    private String formaFarmaceutica;
    private String concentracion;
    private String viaAdministracion;
    private String tipoControl;       // controlado, libre, requiere receta
    private int stock;
    private double precioPublico;
    private double precioProveedor;
    private String fechaCaducidad;    // AAAA-MM-DD
    private int idSucursal;           // referencia a sucursal

    public static final String ENCABEZADO =
            "idMedicamento,nombreComercial,nombreGenerico,formaFarmaceutica,concentracion,viaAdministracion,tipoControl,stock,precioPublico,precioProveedor,fechaCaducidad,idSucursal";

    public Medicamento(int idMedicamento,
                       String nombreComercial,
                       String nombreGenerico,
                       String formaFarmaceutica,
                       String concentracion,
                       String viaAdministracion,
                       String tipoControl,
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
        this.viaAdministracion = viaAdministracion;
        this.tipoControl = tipoControl;
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
    public String getViaAdministracion() { return viaAdministracion; }
    public void setViaAdministracion(String viaAdministracion) { this.viaAdministracion = viaAdministracion; }
    public String getTipoControl() { return tipoControl; }
    public void setTipoControl(String tipoControl) { this.tipoControl = tipoControl; }
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
    public String aCSV() {
        return String.join(",",
                String.valueOf(idMedicamento),
                nombreComercial,
                nombreGenerico,
                formaFarmaceutica,
                concentracion,
                viaAdministracion,
                tipoControl,
                String.valueOf(stock),
                String.valueOf(precioPublico),
                String.valueOf(precioProveedor),
                fechaCaducidad,
                String.valueOf(idSucursal));
    }

    public static Medicamento desdeCSV(String linea) {
        String[] partes = linea.split(",", -1);
        if (partes.length != 12) {
            throw new IllegalArgumentException("Linea de medicamento invalida: " + linea);
        }
        int id = Integer.parseInt(partes[0].trim());
        int stock = Integer.parseInt(partes[7].trim());
        double precioPublico = Double.parseDouble(partes[8].trim());
        double precioProveedor = Double.parseDouble(partes[9].trim());
        int sucursalId = Integer.parseInt(partes[11].trim());
        return new Medicamento(
                id,
                partes[1].trim(),
                partes[2].trim(),
                partes[3].trim(),
                partes[4].trim(),
                partes[5].trim(),
                partes[6].trim(),
                stock,
                precioPublico,
                precioProveedor,
                partes[10].trim(),
                sucursalId
        );
    }

    @Override
    public String toString() {
        return "Medicamento #" + idMedicamento + "\n"
                + "  Nombre comercial : " + nombreComercial + "\n"
                + "  Nombre generico  : " + nombreGenerico + "\n"
                + "  Forma farmaceutica: " + formaFarmaceutica + "\n"
                + "  Concentracion    : " + concentracion + "\n"
                + "  Via administracion: " + viaAdministracion + "\n"
                + "  Tipo control     : " + tipoControl + "\n"
                + "  Stock            : " + stock + "\n"
                + "  Precio publico   : " + String.format("%.2f", precioPublico) + "\n"
                + "  Precio proveedor : " + String.format("%.2f", precioProveedor) + "\n"
                + "  Caducidad        : " + fechaCaducidad + "\n"
                + "  Sucursal ID      : " + idSucursal;
    }
}
