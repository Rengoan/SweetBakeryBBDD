package sweetbakery.principal;

import MYSQL.*;
import java.security.Principal;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import sweetbakery.negocio.*;
import sweetbakery.dominio.*;

public class Pantalla {

    static Connection conexion = null;

    static EmpleadosDAO empleadosDAO = new EmpleadosDAOJDBC();
    static ClientesDAO clientesDAO = new ClientesDAOJDBC();
    static ICatalagoPasteleria catalogo = new PasteleriaImp();

    static String pedido = "pedido.txt";
    static String producto = "CatalogoProductos.txt";
    static String ventas = "ArchivoVentas.txt";
    static String ordenado = "ArchivoOrdenado.txt";

    public static void main(String[] args) throws SQLException, ParseException {
        //CONEXIONES

        conexion = Conexion.getConnection();

        if (conexion.getAutoCommit()) {
            conexion.setAutoCommit(false);
        }

        ClientesDAOJDBC clientesDao = new ClientesDAOJDBC(conexion);
        EmpleadosDAOJDBC empleadosDao = new EmpleadosDAOJDBC(conexion);

        menuPrincipal();
    }

    public static void menuPrincipal() throws ParseException, SQLException {
        int opcion = -1;
        boolean repetir = true;
//        int opciones = -1;

        Scanner lectura = new Scanner(System.in);
        Scanner dato = new Scanner(System.in);

//        while (opcion!=0) 
        while (opcion != 0) {
            System.out.println("\nBIENVENIDO A SWEETBAKERY: ");
            System.out.println("==================================");
            System.out.println("Elige una de las opciones: \n");

            System.out.println("1.- Iniciar sesion como empleado");
            System.out.println("2.- Registrarse como nuevo empleado");
            System.out.println("3.- Iniciar sesion como cliente");
            System.out.println("4.- Registrarse como nuevo cliente");
            System.out.println("0.- Salir de la aplicación");
            System.out.println("Seleccione una de las opciones");

            opcion = lectura.nextInt();
            switch (opcion) {
                case 1:
                    try {
                    System.out.println("Escriba su nombre de usuario");
                    System.out.println("=============================");
                    System.out.println("Usuario: ");
                    String usuario = dato.nextLine();
                    Empleado empleadotmp = empleadosDAO.buscarEmpleado(usuario);

                    if (empleadotmp != null) {
                        menuEmpleado(empleadotmp);
                    } else {
                        System.out.println("Usuario del empleado no registrado, debe crear un usuario para realizar gestiones");
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;
                case 2:
                    //Crear nuevo empleado
                    System.out.println("Introduzca sus datos para crear un nuevo usuario con su información");

                    System.out.println("Introduce tu DNI: ");
                    String dni = dato.nextLine();

                    System.out.println("Introduce tu Nombre: ");
                    String nombre = dato.nextLine();

                    System.out.println("Introduce tu Apellido: ");
                    String apellido = dato.nextLine();

                    System.out.println("Introduce tu correo: ");
                    String correo = dato.nextLine();

                    System.out.println("Introduce tu Telefono: ");
                    String tlf = dato.nextLine();

                    System.out.println("Introduce tu nombre de Usuario: ");
                    String user = dato.nextLine();

                    System.out.println("Introduce tu contraseña: ");
                    String password = dato.nextLine();

                    System.out.println("Introduce tu numero de la Seguridad Social: ");
                    String ssocial = dato.nextLine();

                    System.out.println("Introduce tu numero de Cuenta Bancaria: ");
                    String cbancaria = dato.nextLine();

                    empleadosDAO.insertar(new Empleado(dni, nombre, apellido, correo, tlf, user, password, ssocial, cbancaria));

                    break;
                case 3:
                    try {
                    System.out.println("Escriba su usuario y contraseña");
                    System.out.println("==============================");

                    System.out.println("Usuario: ");
                    String usuarioC = dato.nextLine();

                    System.out.println("Introduce tu contraseña: ");
                    String passwordC = dato.nextLine();
                    Cliente clientetmp = clientesDAO.buscarUsuario(usuarioC, passwordC);

                    if (clientetmp != null) {
                        menuCliente(clientetmp);
                    } else {
                        System.out.println("Usuario de cliente no registrado, debe crear un usuario para realizar pedidos");
                    }

                } catch (SQLException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }

                break;
                case 4:
                    //Crear nuevo cliente
                    System.out.println("Introduzca sus datos para crear un nuevo usuario con su información:");
                    System.out.println("=============================================================");

                    System.out.println("Introduce tu DNI: ");
                    String dniC = dato.nextLine();

                    System.out.println("Introduce tu Nombre: ");
                    String nombreC = dato.nextLine();

                    System.out.println("Introduce tu Apellido: ");
                    String apellidoC = dato.nextLine();

                    System.out.println("Introduce tu correo: ");
                    String correoC = dato.nextLine();

                    System.out.println("Introduce tu Telefono: ");
                    String tlfC = dato.nextLine();

                    System.out.println("Introduce tu nombre de Usuario: ");
                    String userC = dato.nextLine();

                    System.out.println("Introduce tu contraseña: ");
                    String passwordC = dato.nextLine();

                    clientesDAO.insertar(new Cliente(dniC, nombreC, apellidoC, correoC, tlfC, userC, passwordC));
                    //Me gustaria que al crear el cliente se loggease automaticamente y fuese al menuCliente

                    break;

                case 0:
                    System.out.println("Vuelva pronto!!");
                    break;
                default:
                    System.out.println("Debe seleccionar una opción entre 0 y 4");
            }

        }
    }

    public static void menuEmpleado(Empleado empleadotmp) throws SQLException, ParseException {
        int opcion;
        boolean opcionW = true;
        Scanner lectura = new Scanner(System.in);
        Scanner datos = new Scanner(System.in);
        Scanner nProducto = new Scanner(System.in);
        while (opcionW) {
            System.out.println("\nEMPLEADO: ");

            System.out.println("1.- Listar los empleados de la tienda");
            System.out.println("2.- Borrar empleado de la base de datos");
            System.out.println("3.- Ordenar empleados descendentemente por apellido");
            System.out.println("4.- Listar los clientes de la tienda");
            System.out.println("5.- Ordenar clientes ascendendentemente por ID");
            System.out.println("6.- Iniciar catalogo de articulos");
            System.out.println("7.- Añadir articulos al catalogo");
            System.out.println("8.- Listar los articulos del catalogo");
            System.out.println("9.- Buscar articulo del catalogo");
            System.out.println("10.- Ordenar catalogo ascendentemente por referencia");
            System.out.println("11.- Iniciar el fichero ventas");
            System.out.println("0.- Volver al menu principal");
            System.out.println("Seleccione una de las opciones");

            opcion = lectura.nextInt();
            switch (opcion) {
                case 1:
                    //LISTAR EMPLEADOS
                    System.out.println("\nEmpleados registrados");
                    System.out.println("===============================");
                    try {
                        List<Empleado> empl = empleadosDAO.listar();
                        empl.forEach(empleado -> {
                            System.out.println(empleado);
                        });
                        System.out.println("\n");
                    } catch (SQLException ex) {
                        Logger.getLogger(Pantalla.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;

                case 2:
                    //BORRAR EMPLEADO
                    System.out.println("\nIntroduzca el ID del empleado que quiere borrar...");

                    System.out.println("ID Empleado: ");
                    int borrarUser = datos.nextInt();
                    datos.nextLine();

                    try {
                        empleadosDAO.borrarporId(new Empleado(borrarUser));
                        System.out.println("\n");
                    } catch (SQLException ex) {
                        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;
                case 3:
                    //ORDENAR EMPLEADOS DESCENDENTEMENTE POR NOMBRE
                    System.out.println("\nEmpleados ordenados por apellido descendente");
                    System.out.println("===============================");
                    List<Empleado> empl = empleadosDAO.OrdenarDescApellido();
                    empl.forEach(empleado -> {
                        System.out.println(empleado);
                    });
                    System.out.println("\n");
                    break;
                case 4:
                    //LISTADO DE CLIENTES
                    System.out.println("\nClientes registrados");
                    System.out.println("===============================");
                    try {
                        List<Cliente> cliente = clientesDAO.listar();
                        cliente.forEach(clientes -> {
                            System.out.println(clientes);
                        });
                        System.out.println("\n");
                    } catch (SQLException ex) {
                        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    break;

                case 5:
                    //ORDENAR LISTADO CLIENTES ASCENDENTE
                    System.out.println("\nClientes ordenado por ID ascendentemente");
                    System.out.println("===============================");

                    List<Cliente> cliente = clientesDAO.OdenarID();
                    cliente.forEach(clientes -> {
                        System.out.println(clientes);
                    });
                    System.out.println("\n");
                    break;
                case 6:
                    //INICIAR CATALOGO DE PRODUCTOS
                    catalogo.iniciar(producto);
                    break;
                case 7:
                    //AÑADIR ARTICULOS AL CATALOGO
                    System.out.println("\nIntroduzca LOS datos para agregar un nuevo producto al catálogo...");

                    System.out.println("Escriba el identificar del producto: ");
                    int id = nProducto.nextInt();
                    nProducto.nextLine();

                    System.out.println("Escriba el nombre del producto: ");
                    String nombreProducto = nProducto.nextLine();

                    System.out.println("Descripcion del producto: ");
                    String descripcion = nProducto.nextLine();

                    System.out.println("Selecciona el tamaño: ");
                    TipoProducto tipo = menuTipo();

                    System.out.println("Escriba el precio del producto: ");
                    double precio = nProducto.nextDouble();
                    nProducto.nextLine();

                    System.out.println("Escriba la fecha de caducidad del producto (dd/mm/yyyy): ");
                    String fechaString = nProducto.nextLine();
                    SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");
                    Date fecha = df.parse(fechaString);

                    catalogo.agregarPastelCatalogo(new Productos(id, nombreProducto, descripcion, tipo, precio, fecha), producto);
                    System.out.println("\n");
                    break;
//                case 8:
//                    //LISTAR ARTICULOS DEL CATALOGO
//                    System.out.println("\nProductos del catalogo");
//                    System.out.println("===============================");
//
//                    catalogo.listarRecurso(producto);
//                    System.out.println("\n ");
//                    break;
//                case 9:
//                    //INICIAR CATALOGO DE PRODUCTOS
//                    catalogo.iniciar(producto);
//                    break;
//                case 10:
//                    //INICIAR CATALOGO DE PRODUCTOS
//                    catalogo.iniciar(producto);
//                    break;
                case 11:
                    //INICIAR CATALOGO DE PRODUCTOS
                    catalogo.iniciar(ventas);
                    break;
                case 0:
                    opcionW = false;
                    break;
                default:
                    System.out.println("Debe seleccionar una opción entre 0 y 2");
            }
        }
    }

    public static void menuCliente(Cliente clientetmp) throws SQLException, ParseException {
        int opcion;
        boolean opcionW = true;
//        String contrasena = "";
//        String usuario = "";
        Scanner datos = new Scanner(System.in);
        Scanner lectura = new Scanner(System.in);
        while (opcionW) {
            System.out.println("\nCLIENTE");
            System.out.println("==============\n");
            System.out.println("Elige una de las opciones: ");

            System.out.println("1.- Darse de baja");
            System.out.println("2.- Editar datos del cliente");
            System.out.println("3.- Iniciar pedido");
            System.out.println("0.- Menu principal");
            System.out.println("Seleccione una de las opciones");

            opcion = lectura.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("Para darse de baja en la aplicación, introduzca su DNI...");

                    System.out.println("DNI: ");
                    String borrar = datos.nextLine();

                    clientesDAO.borrarporDNI(new Cliente(borrar));
                    menuPrincipal();

                    break;

                case 2:
                    System.out.println("Introduzca sus datos para modificar su usuario...");
                    System.out.println("=============================================\n");

                    System.out.println("Correo actual: " + clientetmp.getCorreo() + " | Introduce tu email: ");
                    String correo = datos.nextLine();

                    System.out.println("Telefono actual: " + clientetmp.getTlf() + " | Introduce tu telefono: ");
                    String telefono = datos.nextLine();

                    System.out.println("Nombre de usuario actual: " + clientetmp.getUsuario() + " | Introduce tu usuario: ");
                    String usuario = datos.nextLine();

                    System.out.println("Contraseña actual: " + clientetmp.getContrasena() + " | Introduce tu contraseña: ");
                    String contrasena = datos.nextLine();

                    clientesDAO.actualizar(new Cliente(clientetmp.getDni(), clientetmp.getNombre(), clientetmp.getApellido(), correo, telefono, usuario, contrasena));

                    break;

                case 3:
                    menuPedido(clientetmp);
                    break;

                case 0:
                    System.out.println("Volviendo al menu principal");
                    opcionW = false;
                    break;
                default:
                    System.out.println("Debe seleccionar una opción entre 0 y 3");
            }
        }
    }

    public static void menuPedido(Cliente clientetmp) throws SQLException, ParseException {
        int cantidadTotal = 0;
        int opcion;
        boolean opcionW = true;

        Scanner datos = new Scanner(System.in);
        Scanner menu = new Scanner(System.in);

        Productos productoEncontrado = null;

        while (opcionW) {
            System.out.println("\nPEDIDO");
            System.out.println("==============\n");
            System.out.println("Elige una de las opciones: ");
            System.out.println("1.- Inicializar fichero");
            System.out.println("2.- Añadir productos al pedido");
            System.out.println("0.- Volver al menu cliente");
            System.out.println("Seleccione una de las opciones");

            opcion = menu.nextInt();

            switch (opcion) {
                case 1:
                    catalogo.iniciar(pedido);
                    System.out.println("Pedido iniciado...");

                    break;

                case 2:
                    System.out.println("\nCATALOGO PRODUCTOS");
                    System.out.println("=======================\n");
                    System.out.println("Escriba la palabra 'Terminar' para terminar la compra\n");

                    catalogo.listarRecurso(producto);//Crear menu empleados para que se cree el archivo
                    boolean repetircompra = true;

                    List<Pedido> arrayProductos = new ArrayList<>();
                    Date fecha_compra = new Date();
                    SimpleDateFormat df = new SimpleDateFormat("dd/mm/yyyy");

                    while (repetircompra) {
                        String producto;

                        System.out.println("\t\t=================");

                        System.out.println("\nEscriba el nombre del producto que desea agregar al pedido: ");
                        producto = datos.nextLine();

                        if (producto.equalsIgnoreCase("Terminar")) {
                            repetircompra = false;
                            System.out.println("\nREF\tARTICULO\tPRECIO\tDESCRIPCION\tTEMPORADA\tTALLA");
                            System.out.println("---\t--------\t------\t-----------\t---------\t-----");

                            for (Pedido n_compra : arrayProductos) {
                                catalogo.agregarCompra(n_compra, ventas);

                                System.out.println(n_compra.getNombreProducto().toString());
                            }

                            double total = catalogo.calcularTotalPrecio(arrayProductos);
                            System.out.println("\nEl precio total es: " + total + " € ");

                            double min = catalogo.minPrecioArticulo(arrayProductos);
                            System.out.println("El producto mas barato ha sido " + min + " € ");

                            int cont = catalogo.contadorArticulos(arrayProductos);

                            System.out.println("Ha comprado un total de " + cont + " productos");

                            catalogo.iniciar(pedido);

                        } else {

                            System.out.println("Indique la cantidad de que quiere del producto " + producto);
                            Integer cantidad = datos.nextInt();
                            datos.nextLine();

                            productoEncontrado = catalogo.comprarPastel(pedido, producto);

                            catalogo.agregarPastelPedido(productoEncontrado, pedido);

                            if (productoEncontrado != null) {
                                System.out.println("\tSe ha agregado a su pedido " + cantidad + " unidades del producto " + productoEncontrado.getNombreProducto());

                                Pedido pedidotmp = new Pedido(fecha_compra, cantidad, clientetmp, clientetmp, productoEncontrado);
                                arrayProductos.add(pedidotmp);

                            } else {
                                System.out.println("Producto no encontrado, no se pudo agregar al carrito");
                            }
                        }
                    }
                    break;

                case 0:
                    menuCliente(clientetmp);
                    opcionW = false;
                    break;

                default:
                    System.out.println("Debe seleccionar una opción entre 0 y 2");
            }
        }
    }

    public static TipoProducto menuTipo() {
        int opcion;
        Scanner lectura = new Scanner(System.in);

//        TipoProducto tipo = null;
        while (true) {
            System.out.println("------------------\n");
            System.out.println("1.- Pequeño");
            System.out.println("2.- Mediano");
            System.out.println("3.- Grande");
            System.out.println("0.- Salir");
            opcion = lectura.nextInt();
            switch (opcion) {
                case 1:
                    return TipoProducto.PEQUENIA;
                case 2:
                    return TipoProducto.MEDIANA;
                case 3:
                    return TipoProducto.GRANDE;

            }

        }

    }

}
