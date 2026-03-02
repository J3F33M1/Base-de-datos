/**
 * Entidad que representa a un Cliente o paciente de la farmacia.
 * <p>
 * Almacena informacion personal, de contacto, domicilio, metodo de pago
 * y la sucursal de preferencia. Incluye logica para calcular descuentos.
 * </p>
 */
public class Cliente {
    // Atributos
    private int idCliente;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private String correo;
    private String fechaNacimiento; // AAAA-MM-DD
    private String calle;
    private String numeroExterior;
    private String numeroInterior;
    private String colonia;
    private String estado;
    private String metodoPago; // efectivo, tarjeta
    private int visitasAnuales;
    private int idSucursal; // relacion con sucursal

    /** Encabezado CSV para la persistencia de esta entidad. */
    public static final String ENCABEZADO =
            "idCliente,nombre,apellidoPaterno,apellidoMaterno,telefono,correo,fechaNacimiento,calle,numeroExterior,numeroInterior,colonia,estado,metodoPago,visitasAnuales,idSucursal";

    /**
     * Constructor completo de la clase Cliente.
     * @param idCliente Identificador unico.
     * @param nombre Nombre de pila.
     * @param apellidoPaterno Apellido paterno.
     * @param apellidoMaterno Apellido materno.
     * @param telefono Numero de telefono.
     * @param correo Correo electronico.
     * @param fechaNacimiento Fecha de nacimiento (AAAA-MM-DD).
     * @param calle Calle del domicilio.
     * @param numeroExterior Numero exterior.
     * @param numeroInterior Numero interior (puede ser NA).
     * @param colonia Colonia.
     * @param estado Estado o entidad federativa.
     * @param metodoPago Metodo de pago preferido.
     * @param visitasAnuales Numero de visitas realizadas en el año.
     * @param idSucursal ID de la sucursal asociada.
     */
    public Cliente(int idCliente,
                   String nombre,
                   String apellidoPaterno,
                   String apellidoMaterno,
                   String telefono,
                   String correo,
                   String fechaNacimiento,
                   String calle,
                   String numeroExterior,
                   String numeroInterior,
                   String colonia,
                   String estado,
                   String metodoPago,
                   int visitasAnuales,
                   int idSucursal) {
        this.idCliente = idCliente;
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        setTelefono(telefono);
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.calle = calle;
        this.numeroExterior = numeroExterior;
        this.numeroInterior = numeroInterior;
        this.colonia = colonia;
        this.estado = estado;
        this.metodoPago = metodoPago;
        this.visitasAnuales = visitasAnuales;
        this.idSucursal = idSucursal;
    }

    // Getters y setters
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellidoPaterno() { return apellidoPaterno; }
    public void setApellidoPaterno(String apellidoPaterno) { this.apellidoPaterno = apellidoPaterno; }
    public String getApellidoMaterno() { return apellidoMaterno; }
    public void setApellidoMaterno(String apellidoMaterno) { this.apellidoMaterno = apellidoMaterno; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { 
        if (telefono == null) { 
            throw new IllegalArgumentException("Tienes que ingresar un número de teléfono");   
        }
        String soloDigitos = telefono.replaceAll("\\D", "");
        if(!soloDigitos.matches("\\d{10,15}")){
            throw new IllegalArgumentException("Telefono invalido : " + telefono);
        }
        this.telefono = soloDigitos;

 }
    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
    public String getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
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
    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }
    public int getVisitasAnuales() { return visitasAnuales; }
    public void setVisitasAnuales(int visitasAnuales) { this.visitasAnuales = visitasAnuales; }
    public int getIdSucursal() { return idSucursal; }
    public void setIdSucursal(int idSucursal) { this.idSucursal = idSucursal; }

    /**
     * Calcula el descuento aplicable basado en la frecuencia de visitas anuales.
     * @return El porcentaje de descuento como decimal (0.25 para 25%).
     */
    public double calcularDescuento() {
        if (visitasAnuales >= 6) return 0.25;
        if (visitasAnuales >= 4) return 0.10;
        if (visitasAnuales >= 2) return 0.05;
        return 0.0;
    }

    // Objeto a CSV
    /**
     * Convierte el objeto a una cadena con formato CSV.
     * @return Cadena separada por comas con los datos del cliente.
     */
    public String aCSV() {
        return String.join(",",
                String.valueOf(idCliente),
                nombre,
                apellidoPaterno,
                apellidoMaterno,
                telefono,
                correo,
                fechaNacimiento,
                calle,
                numeroExterior,
                numeroInterior,
                colonia,
                estado,
                metodoPago,
                String.valueOf(visitasAnuales),
                String.valueOf(idSucursal));
    }

    /**
     * Crea una instancia de Cliente a partir de una linea CSV.
     * @param linea La cadena CSV leida del archivo.
     * @return Un nuevo objeto Cliente.
     * @throws IllegalArgumentException Si la linea no tiene el formato esperado.
     */
    public static Cliente desdeCSV(String linea) {
        String[] partes = linea.split(",", -1);
        if (partes.length != 15) {
            throw new IllegalArgumentException("Linea de cliente invalida: " + linea);
        }
        int id = Integer.parseInt(partes[0].trim());
        int visitas = Integer.parseInt(partes[13].trim());
        int sucursalId = Integer.parseInt(partes[14].trim());
        return new Cliente(
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
                partes[11].trim(),
                partes[12].trim(),
                visitas,
                sucursalId
        );
    }
    // Imprimir objeto
    @Override
    public String toString() {
        double descuento = calcularDescuento() * 100;
        return "Cliente #" + idCliente + "\n"
                + "  Nombre          : " + nombre + " " + apellidoPaterno + " " + apellidoMaterno + "\n"
                + "  Telefono        : " + telefono + "\n"
                + "  Correo          : " + correo + "\n"
                + "  Fecha nacimiento: " + fechaNacimiento + "\n"
                + "  Domicilio       : " + calle + " " + numeroExterior + " " + numeroInterior + ", " + colonia + ", " + estado + "\n"
                + "  Metodo pago     : " + metodoPago + "\n"
                + "  Visitas anuales : " + visitasAnuales + "\n"
                + "  Descuento       : " + descuento + "%\n"
                + "  Sucursal ID     : " + idSucursal;
    }
}
