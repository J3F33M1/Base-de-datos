/**
 * Entidad que representa una Sucursal de la farmacia.
 * <p>
 * Almacena la informacion de ubicacion, contacto y horarios de atencion
 * de una tienda fisica.
 * </p>
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

    /** Encabezado CSV para la persistencia de esta entidad. */
    public static final String ENCABEZADO = "idSucursal,nombre,calle,numeroExterior,numeroInterior,colonia,estado,telefono,horarioApertura,horarioCierre";

    /**
     * Constructor completo de la clase Sucursal.
     * @param idSucursal Identificador unico.
     * @param nombre Nombre de la sucursal.
     * @param calle Calle del domicilio.
     * @param numeroExterior Numero exterior.
     * @param numeroInterior Numero interior.
     * @param colonia Colonia.
     * @param estado Estado o entidad federativa.
     * @param telefono Telefono de contacto.
     * @param horarioApertura Hora de apertura.
     * @param horarioCierre Hora de cierre.
     */
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

    // Objeto a CSV
    /**
     * Convierte el objeto a una cadena con formato CSV.
     * @return Cadena separada por comas con los datos de la sucursal.
     */
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

    /**
     * Crea una instancia de Sucursal a partir de una linea CSV.
     * @param linea La cadena CSV leida del archivo.
     * @return Un nuevo objeto Sucursal.
     * @throws IllegalArgumentException Si la linea no tiene el formato esperado.
     */
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
