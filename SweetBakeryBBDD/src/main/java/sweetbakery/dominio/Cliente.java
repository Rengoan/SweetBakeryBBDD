package sweetbakery.dominio;

public class Cliente {

    private int idCliente;
    private String dni;
    private String nombre;
    private String apellido;
    private String correo;
    private String tlf;
    private String usuario;
    private String contrasena;

    public Cliente() {
    }

    public Cliente(int idCliente, String dni, String nombre, String apellido, String correo, String tlf, String usuario, String contrasena) {
        this.idCliente = idCliente;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.tlf = tlf;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    public Cliente(String dni, String nombre, String apellido, String correo, String tlf, String usuario, String contrasena) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.tlf = tlf;
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    
    
    public Cliente(String dni) {
        this.dni = dni;
    }

    public Cliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    //Getter and Setter
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTlf() {
        return tlf;
    }

    public void setTlf(String tlf) {
        this.tlf = tlf;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    //toString
//    @Override
//    public String toString() {
//        return "Cliente\n========" + "\nid: " + idCliente + "\nnombre: " + nombre 
//                + "\napellido: " + apellido + "\ncorreo: " + correo + "\ntlf: " + tlf + "\n";
//    }
    @Override
    public String toString() {
        return "Cliente\n=============" +"\nIdentificador del cliente: "+idCliente+"\nDNI: "
                + dni + "\nNombre:" + nombre + "\nApellido: " + apellido
                + "\nCorreo: " + correo + "\nTelefono: " + tlf + "\nUsuario: "
                + usuario + "\nContraseña: " + contrasena + "\n";
    }

}
