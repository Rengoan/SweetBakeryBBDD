package sweetbakery.dominio;

import java.text.SimpleDateFormat;
import java.util.*;

public class Productos {

    private int idProducto;
    private String nombreProducto;
    private String descripcion;
    private TipoProducto tipoProducto;
    private double precio;
    private Date fechaC;
    
    


    public Productos() {
    }

    public Productos(int idProducto, String nombreProducto, String descripcion,
            TipoProducto tipoProducto, double precio, Date fechaC) {
        this();
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.tipoProducto = tipoProducto;
        this.precio = precio;
        this.fechaC = fechaC;
    }

    public Productos(String nombreProducto, String descripcion,
            TipoProducto tipoProducto, double precio, Date fechaC) {
        this();
        this.nombreProducto = nombreProducto;
        this.descripcion = descripcion;
        this.tipoProducto = tipoProducto;
        this.precio = precio;
        this.fechaC = fechaC;
    }

   //Getter and setters
    
    public String getfecha_caducidad_toString() {
        SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");
        return df.format(fechaC);
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoProducto getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(TipoProducto tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Date getFechaC() {
        return fechaC;
    }

    public void setFechaC(Date fechaC) {
        this.fechaC = fechaC;
    }

    
    
    //toString

//    @Override
//    public String toString() {
//        return "Productos\n===========" + "\nidProducto: " + idProducto 
//                + "\nnombreProducto: " + nombreProducto + "\nDescripcion: " 
//                + descripcion + "\ntipoProducto: " + tipoProducto + "\nprecio: " 
//                + precio + "\nfecha de caducidad: " + fechaC + "\n";
//    }

    @Override
    public String toString() {
        return "Productos\n===================" + "\nIdentificador de Producto: " + idProducto 
                + "\nNombre de producto: " + nombreProducto 
                + "\nDescripcion: " + descripcion + "\nTamaño: " + tipoProducto 
                + "\nPrecio" + precio + "\nFecha de compra: " + this.getfecha_caducidad_toString() + "\n";
    }
    
    
    

}