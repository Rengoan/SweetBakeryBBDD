package sweetbakery.dominio;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Pedido {
    
    private Date fechaP;
    private int cantidad;
    private Cliente idClientes;
    private Cliente dniCliente;
    private Productos nombreProducto;
//    private int numeroPedido;
//    private Empleado idEmpleados;
    
    //Contructores

    public Pedido() {
    }

    public Pedido(Date fechaP, int cantidad, Cliente idClientes,
            Cliente dniCliente, Productos nombreProducto) {
        
        this.fechaP = fechaP;
        this.cantidad = cantidad;
        this.idClientes = idClientes;
        this.dniCliente = dniCliente;
        this.nombreProducto = nombreProducto;
        
        
    }
    
    //Getter and Setters
    
    public String getfechaP_toString() {
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        return df.format(fechaP);
    }

    public Date getFechaP() {
        return fechaP;
    }

    public void setFechaP(Date fechaP) {
        this.fechaP = fechaP;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Cliente getIdClientes() {
        return idClientes;
    }

    public void setIdClientes(Cliente idClientes) {
        this.idClientes = idClientes;
    }

    public Cliente getDniCliente() {
        return dniCliente;
    }

    public void setDniCliente(Cliente dniCliente) {
        this.dniCliente = dniCliente;
    }

    public Productos getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(Productos nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

//    public int getNumeroPedido() {
//        return numeroPedido;
//    }
//
//    public void setNumeroPedido(int numeroPedido) {
//        this.numeroPedido = numeroPedido;
//    }

//    public Empleado getIdEmpleados() {
//        return idEmpleados;
//    }
//
//    public void setIdEmpleados(Empleado idEmpleados) {
//        this.idEmpleados = idEmpleados;
//    }
    
   //toString

    @Override
    public String toString() {
        return "Pedido\n==================" + "\nFecha del pedido: " + fechaP 
                + "\nCantidad: " + cantidad 
                + "\nIdentificador Cliente: " + idClientes.getIdCliente() 
                + "\nDNI" + dniCliente.getDni()
                + "\nIdentificador del Producto: " + nombreProducto.getNombreProducto() + "\n";
    }
    
    
    
    
}
