package sweetbakery.dominio;

public class Empleado {
    private int idEmp;
    private String dni;
    private String nombre;
    private String apellido;
    private String correo;
    private String tlf;
    private String usuario;
    private String contrasena;
    private String Ssocial;
    private String Cbancaria;
    
    
    //Constructor
    public Empleado() {
    }

    public Empleado(int idEmp, String dni, String nombre, String apellido, String correo, String tlf, String usuario, String contrasena, String Ssocial, String Cbancaria) {
        this.idEmp = idEmp;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.tlf = tlf;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.Ssocial = Ssocial;
        this.Cbancaria = Cbancaria;
    }

    public Empleado(int idEmp) {
        this.idEmp = idEmp;
    }

    public Empleado(String dni) {
        this.dni = dni;
    }

    public Empleado(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }

    
    
    //Getter and Setter

    public int getIdEmp() {
        return idEmp;
    }

    public void setIdEmp(int idEmp) {
        this.idEmp = idEmp;
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

    public String getSsocial() {
        return Ssocial;
    }

    public void setSsocial(String Ssocial) {
        this.Ssocial = Ssocial;
    }

    public String getCbancaria() {
        return Cbancaria;
    }

    public void setCbancaria(String Cbancaria) {
        this.Cbancaria = Cbancaria;
    }

    
    
    
    //toString
    
    

//    @Override
//    public String toString() {
//        return "Empleado\n========= " + "\n idEmp: " + idEmp + "\n nombre: " 
//                + nombre + "\n apellidos: " + apellidos + "\n correo: " 
//                + correo + "\n tlfEmp: " + tlfEmp + "\n Ssocial: " + Ssocial 
//                + "\n Cbancaria: " + Cbancaria+"\n";
//    }

    @Override
    public String toString() {
        return "Empleado\n=========================" + "\nIdentificador de Empleado: " + idEmp 
                + "\nDNI: " + dni 
                + "\nNombre: " + nombre 
                + "\nApellido: " + apellido + "\nCorreo: " + correo + "\nTelefono: " 
                + tlf + "\nUsuario: " + usuario + "\nContraseña: " + contrasena 
                + "\nSeguridad Social: " + Ssocial + "\nCuenta bancaria: " + Cbancaria + "\n";
    }
    
    
    
    
    
}
