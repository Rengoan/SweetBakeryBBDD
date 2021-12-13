package sweetbakery.datos;

import java.util.*;
import sweetbakery.dominio.*;
import sweetbakery.excepciones.*;

public interface IAccesoDatos {
    
    
    boolean existe(String nombreArchivo)throws AccesoDatosEx;
    
    void crearArchivo(String nombreArchivo) throws AccesoDatosEx;
    
    List<Productos> ListarP(String nombreArchivo) throws LecturaDatosEx;
    
    void agregarProducto(Productos producto, String nombreArchivo,
            boolean anexar) throws EscrituraDatosEx;
    
    void agregarPedido(Pedido pedido, String nombreArchivo,
            boolean anexar) throws EscrituraDatosEx;
    
    String borrarArchivo(String nombreArchivo) throws AccesoDatosEx;
    
    
}
