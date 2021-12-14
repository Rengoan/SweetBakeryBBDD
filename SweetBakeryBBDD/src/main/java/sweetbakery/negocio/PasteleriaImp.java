package sweetbakery.negocio;

import MYSQL.ClientesDAO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import sweetbakery.datos.IAccesoDatos;
import sweetbakery.dominio.*;
import sweetbakery.excepciones.AccesoDatosEx;
import sweetbakery.excepciones.EscrituraDatosEx;
import sweetbakery.excepciones.LecturaDatosEx;

public class PasteleriaImp implements ICatalagoPasteleria{

    private IAccesoDatos datos;
    private List<Cliente> clientes = null;
    private List<Productos> productos = null;
    private List<Pedido> pedidos = null;

    public PasteleriaImp(IAccesoDatos datos) {
        this.datos = datos;
    }
    
    
    
    
    @Override
    public String iniciar(String nombreArchivo) {
        try {
            if (this.datos.existe(nombreArchivo)) {
                this.datos.borrarArchivo(nombreArchivo); 
                this.datos.crearArchivo(nombreArchivo);
            } else {
                this.datos.crearArchivo(nombreArchivo);
            }
        } catch (AccesoDatosEx ex) {
            ex.printStackTrace();
            System.out.println("Error al inicializar el catalogo");
        }
        return "Catalogo incializado correctamente";
    }
    

    @Override
    public void ordenarArchivo(String nombreCatalogo, String ordenar) {
        File f_entrada = new File(nombreCatalogo);
        File f_salida = new File(ordenar);
        LinkedList<String> lista = new LinkedList<String>();
        try {
            FileReader fr = new FileReader(f_entrada);
            BufferedReader br = new BufferedReader(fr);
            FileWriter fw = new FileWriter(f_salida);
            PrintWriter pw = new PrintWriter(fw);

            String linea = null;
            while ((linea = br.readLine()) != null) {
                lista.add(linea);
            }
            Collections.sort(lista);
            Iterator iter = lista.iterator();
            String cadena;
             
            System.out.println("\n--CATALOGO ORDENADO ASCENDENTEMENTE POR REFERENCIA--");
            System.out.println("-----------------------------------------------------\n");
               
            while (iter.hasNext()) {
                cadena = (String) iter.next();
                pw.println(cadena);
                System.out.println(cadena);
            }

            br.close();
            fr.close();
            pw.close();
            fw.close();
        } catch (FileNotFoundException e) {

            System.out.println("No se ha encontrado el Archivo");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public double calcularTotalPrecio(List<Pedido> arrayPasteles) {
        List<Productos> productos = new ArrayList<>();
        double total = 0.0;

        for (Pedido pedidos : arrayPasteles) {
            double precioPedido = pedidos.getCantidad() * pedidos.getIdProductos().getPrecio();
            total += precioPedido;
        }
        return total;
    }

    @Override
    public int contadorArticulos(List<Pedido> arrayPasteles) {
        List<Productos> producto = new ArrayList<>();
        int cont = 0;

        for (Pedido pedidos : arrayPasteles) {
            cont += pedidos.getCantidad();
        }

        return cont;
    }

    @Override
    public double minPrecioArticulo(List<Pedido> arrayPasteles) {
        List<Productos> producto = new ArrayList<>();
        double minPrecio = 1000.0;

        for (Pedido pedidos : arrayPasteles) {

            if (pedidos.getIdProductos().getPrecio() < minPrecio) {
                minPrecio = pedidos.getIdProductos().getPrecio();
            }
        }

        return minPrecio;
    }

    @Override
    public String listarRecurso(String nombreRecurso) {
        List<Productos> producto = new ArrayList<>();
        try {
            producto = datos.ListarP(nombreRecurso);
            producto.forEach(productos -> {
                productos.toString();
//                System.out.println(prenda.getReferencia() + " ; " + prenda.getNombre() + " ; " + prenda.getPrecio() + " ;" + prenda.getDescripcion() + " ;" + prenda.getfecha_temporada_toString() + " ;" + prenda.getTalla());
            });

        } catch (LecturaDatosEx e) {
            System.out.println("Error listando el catalogo");
            e.printStackTrace(System.out);
        }
        return "";
    }

    @Override
    public void agregarPastelCatalogo(Productos producto, String nombreArchivo) {
        try {
            if (this.datos.existe(nombreArchivo)) {
                this.datos.agregarProducto(producto, nombreArchivo, true);
            } else {
                System.out.println("Catalogo no inicializado");
            }
        } catch (EscrituraDatosEx ex) {
            System.out.println("Error al agregar un nuevo producto al catálogo");
            ex.printStackTrace(System.out);
        } catch (AccesoDatosEx ex) {
            System.out.println("Error al agregar un nuevo producto al catálogo");
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void agregarPastelPedido(Productos producto, String nombreArchivo) {
        try {
            if (this.datos.existe(nombreArchivo)) {
                this.datos.agregarProducto(producto, nombreArchivo, true);
            } else {
                System.out.println("Catalogo no inicializado");
            }
        } catch (EscrituraDatosEx ex) {
            System.out.println("Error al agregar un nuevo producto al catálogo");
            ex.printStackTrace(System.out);
        } catch (AccesoDatosEx ex) {
            System.out.println("Error al agregar un nuevo producto al catálogo");
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void agregarCompra(Pedido pedido, String nombreArchivo) {
        try {
            if (this.datos.existe(nombreArchivo)) {
                this.datos.agregarPedido(pedido, nombreArchivo, true);
            } else {
                System.out.println("Catalogo no inicializado");
            }
        } catch (EscrituraDatosEx ex) {
            System.out.println("Error al agregar un nuevo pedido al catálogo");
            ex.printStackTrace(System.out);
        } catch (AccesoDatosEx ex) {
            System.out.println("Error al agregar un nuevo pedido al catálogo");
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public Productos buscarPastel(String nombreRecurso, int idProducto) {
        
        File archivo = new File(nombreRecurso);
        Productos producto = null;

        List<Productos> ArrayProductos= new ArrayList<>();
        
        try {
            ArrayProductos = this.datos.ListarP(nombreRecurso);
            int i_ArrayProductos = -1;
            while (i_ArrayProductos < ArrayProductos.size() - 1 && producto == null) {
                i_ArrayProductos++;
                if (ArrayProductos.get(i_ArrayProductos).getIdProducto() == idProducto){
                    producto = ArrayProductos.get(i_ArrayProductos);
                }
            }
        } catch (LecturaDatosEx ex) {
            ex.printStackTrace();
            System.out.println("Error al buscar el cliente a través del DNI");

        }
        return producto;
    }

    @Override
    public Productos comprarPastel(String nombreRecurso, int idProducto) {
        File archivo = new File(nombreRecurso);
        List<Productos> productos = null;
        Productos pastelEncontrado = null;
        

        try {
            if (productos == null) {
                productos = this.datos.ListarP(nombreRecurso);
            }

            int arrayPproductos = 0;
            while (arrayPproductos < productos.size() && pastelEncontrado == null) {

                if (productos.get(arrayPproductos).getIdProducto() == idProducto) {
                    pastelEncontrado = productos.get(arrayPproductos);
                }
                arrayPproductos++;
            }

        } catch (LecturaDatosEx ex) {
            ex.printStackTrace();
            System.out.println("Error al buscar la referencia");

        }
        return pastelEncontrado;
    }
    
}
