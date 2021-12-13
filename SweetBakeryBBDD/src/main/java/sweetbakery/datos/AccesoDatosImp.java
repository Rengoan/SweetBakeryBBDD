package sweetbakery.datos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import sweetbakery.dominio.Cliente;
import sweetbakery.dominio.Empleado;
import sweetbakery.dominio.Pedido;
import sweetbakery.dominio.Productos;
import sweetbakery.dominio.TipoProducto;
import sweetbakery.excepciones.AccesoDatosEx;
import sweetbakery.excepciones.EscrituraDatosEx;
import sweetbakery.excepciones.LecturaDatosEx;

public class AccesoDatosImp implements IAccesoDatos {

    @Override
    public boolean existe(String nombreArchivo) throws AccesoDatosEx {
        
        File archivo = new File(nombreArchivo);

        return archivo.exists();
    }

    @Override
    public void crearArchivo(String nombreArchivo) throws AccesoDatosEx {
        File archivo = new File(nombreArchivo);

        try {
            PrintWriter salida = new PrintWriter(new FileWriter(archivo));
            salida.close();
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            throw new AccesoDatosEx("Excepción intentando crear el archivo");
        }
    }

    @Override
    public List<Productos> ListarP(String nombreArchivo) throws LecturaDatosEx {
        File archivo = new File(nombreArchivo);
        Productos productoN = null;
        String[] producto = new String[5];
        List<Productos> productos = new ArrayList<>();

        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/mm/yyyy");

        try {
            BufferedReader entrada = new BufferedReader(new FileReader(archivo));
            String lectura = null;
            while ((lectura = entrada.readLine()) != null) {
                
                producto = lectura.split(";");
                
                int idProducto = Integer.parseInt(producto[0]);
                String nombreProducto = producto[1];
                String descripcion = producto[2];
                TipoProducto tamanio = TipoProducto.valueOf(producto[3]);
                double precio = Double.parseDouble(producto[4]);
                
                Date fechaC = formatoFecha.parse(producto[4]);
                
                productoN = new Productos(idProducto, nombreProducto, 
                        descripcion, tamanio, precio, fechaC); 
                productos.add(productoN);

            }
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);

        } catch (IOException ex) {
            ex.printStackTrace(System.out);
            throw new LecturaDatosEx("Excepción leyendo el fichero...");
        } catch (ParseException ex) {
            ex.printStackTrace(System.out); //***
            throw new LecturaDatosEx("Excepcion listando el archivo");  
        }
        return productos;
    }

    @Override
    public void agregarProducto(Productos producto, String nombreArchivo, boolean anexar) throws EscrituraDatosEx {
        
        File archivo = new File(nombreArchivo); 
        try {
            if (archivo.exists()) {
                PrintWriter salida = new PrintWriter(new FileWriter(nombreArchivo, anexar));
                salida.println(producto.toString());
                salida.close();
            }
        } catch (IOException ex) {
            System.out.println("No se pudo escribir sobre el archivo");
            throw new EscrituraDatosEx("Excepción escribiendo un nuevo artículo");
        }
    }

    @Override
    public void agregarPedido(Pedido pedido, String nombreArchivo, boolean anexar) throws EscrituraDatosEx {
        File archivo = new File(nombreArchivo); 
        try {
            if (archivo.exists()) {
                PrintWriter salida = new PrintWriter(new FileWriter(nombreArchivo, anexar));
                salida.println(pedido.toString());
                salida.close();
            }

        } catch (IOException ex) {
            System.out.println("No se pudo escribir sobre el archivo");
            throw new EscrituraDatosEx("Excepción escribiendo un nuevo artículo");
        }
    }

    @Override
    public String borrarArchivo(String nombreArchivo) throws AccesoDatosEx {
        File archivo = new File(nombreArchivo);
        String mensaje = "";

        if (existe(nombreArchivo)) {
            archivo.delete();
            mensaje = "Recurso borrado con éxito";
        } else {
            mensaje = "No se ha podido borrar el recurso";
        }
        return mensaje;
    }

}
