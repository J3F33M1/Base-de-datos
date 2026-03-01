/**
 * Sucursal entity.
 * Stores the core branch data needed by the use case document:
 * name, full address, contact info and opening hours.
 */
public class Sucursal {

    private int idSucursal;
    private String nombre;
    private String calle;
    private String numeroExterior;
    private String numeroInterior;
    private String colonia;
    private String estado;
    private String telefono;
    private String horarioApertura;
    private String horarioCierre;

    public static final String ENCABEZADO = "idSucursal,nombre,calle,numeroExterior,numeroInterior,colonia,estado,telefono,horarioApertura,horarioCierre";

    public Sucursal(int idSucursal,
                    String nombre,
                    String calle,
                    String numeroExterior,
                    String numeroInterior,
                    String colonia,
                    String estado,
                    String telefono,
                    String horarioApertura,
                    String horarioCierre) {
        this.idSucursal = idSucursal;
        this.nombre = nombre;
        this.calle = calle;
        this.numeroExterior = numeroExterior;
        this.numeroInterior = numeroInterior;
        this.colonia = colonia;
        this.estado = estado;
        this.telefono = telefono;
        this.horarioApertura = horarioApertura;
        this.horarioCierre = horarioCierre;
    }

    // Getters y setters
    public int getIdSucursal() { return idSucursal; }
    public void setIdSucursal(int idSucursal) { this.idSucursal = idSucursal; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCalle() { return calle; }
    public void setCalle(String calle) { this.calle = calle; }
    public String getNumeroExterior() { return numeroExterior; }
    public void setNumeroExterior(String numeroExterior) { this.numeroExterior = numeroExterior; }
    public String getNumeroInterior() { return numeroInterior; }
    public void setNumeroInterior(String numeroInterior) { this.numeroInterior = numeroInterior; }
    public String getColonia() { return colonia; }
    public void setColonia(String colonia) { this.colonia = colonia; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public String getHorarioApertura() { return horarioApertura; }
    public void setHorarioApertura(String horarioApertura) { this.horarioApertura = horarioApertura; }
    public String getHorarioCierre() { return horarioCierre; }
    public void setHorarioCierre(String horarioCierre) { this.horarioCierre = horarioCierre; }

    // CSV helpers
    public String aCSV() {
        return String.join(",",
                String.valueOf(idSucursal),
                nombre,
                calle,
                numeroExterior,
                numeroInterior,
                colonia,
                estado,
                telefono,
                horarioApertura,
                horarioCierre);
    }

    public static Sucursal desdeCSV(String linea) {
        String[] partes = linea.split(",", -1);
        if (partes.length != 10) {
            throw new IllegalArgumentException("Linea de sucursal invalida: " + linea);
        }
        int id = Integer.parseInt(partes[0].trim());
        return new Sucursal(
                id,
                partes[1].trim(),
                partes[2].trim(),
                partes[3].trim(),
                partes[4].trim(),
                partes[5].trim(),
                partes[6].trim(),
                partes[7].trim(),
                partes[8].trim(),
                partes[9].trim()
        );
    }

    @Override
    public String toString() {
        return "Sucursal #" + idSucursal + "\n"
                + "  Nombre          : " + nombre + "\n"
                + "  Calle           : " + calle + "\n"
                + "  Num exterior    : " + numeroExterior + "\n"
                + "  Num interior    : " + numeroInterior + "\n"
                + "  Colonia         : " + colonia + "\n"
                + "  Estado          : " + estado + "\n"
                + "  Telefono        : " + telefono + "\n"
                + "  Horario         : " + horarioApertura + " - " + horarioCierre;
    }
}
