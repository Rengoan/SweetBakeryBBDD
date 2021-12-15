package sweetbakery.principal;

import MYSQL.*;
import java.security.Principal;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
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
        int opcion;
//        int opciones = -1;
        
        Scanner lectura = new Scanner(System.in);
        Scanner dato = new Scanner(System.in);

        while (true) {
            System.out.println("\nBIENVENIDO A SWEETBAKERY: \n");
            System.out.println("==================================\n");
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
//                        menuPedido(empleadotmp);
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
                    System.out.println("Finalizar aplicación.");
                    break;
                default:
                    System.out.println("Debe seleccionar una opción entre 0 y 4");
            }
            
        }
    }

    public static void menuEmpleado(Empleado empleadotmp) throws SQLException, ParseException {
//        int opcion;
//        Scanner lectura = new Scanner(System.in);
//        Scanner dato = new Scanner(System.in);
//        while (true) {
//            System.out.println("\nPEDIDOS: ");
//            System.out.println("Elige una de las opciones: \n");
//            
//            System.out.println("1.- Realizar pedido");
//            System.out.println("2.- Gestiones");
//
//            System.out.println("0.- Salir de la aplicación");
//            System.out.println("Seleccione una de las opciones");
//
//            opcion = lectura.nextInt();
//            switch (opcion) {
//                case 1:
//                    menucliente(empleadotmp);
//                    break;
//                case 2:
//                    menuempleado(empleadotmp);
//                    break;
//                case 0:
//                    menuPrincipal();
//                    break;
//                default:
//                    System.out.println("Debe seleccionar una opción entre 0 y 2");
//            }
//        }
    }

    public static void menuCliente(Cliente clientetmp) throws SQLException, ParseException {
        int opcion;
//        String contrasena = "";
//        String usuario = "";
        Scanner datos = new Scanner(System.in);
        Scanner menu = new Scanner(System.in);
        while (true) {
            System.out.println("\nCLIENTE");
            System.out.println("==============\n");
            System.out.println("Elige una de las opciones: ");

            System.out.println("1.- Darse de baja");
            System.out.println("2.- Editar datos del cliente");
            System.out.println("3.- Iniciar pedido");
            System.out.println("0.- Menu principal");
            System.out.println("Seleccione una de las opciones");

            opcion = menu.nextInt();
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

                    clientesDAO.actualizar(new Cliente(clientetmp.getDni(),clientetmp.getNombre(),clientetmp.getApellido(), correo, telefono, usuario, contrasena));

                    break;

                case 3:
                    menuPedido(clientetmp);
                    break;

                case 0:
                    System.out.println("Volviendo al menu principal");
                    menuPrincipal();
                    break;
                default:
                    System.out.println("Debe seleccionar una opción entre 0 y 3");
            }
        }
    }

    public static void menuPedido(Cliente clientetmp) throws SQLException, ParseException {
    }
}
