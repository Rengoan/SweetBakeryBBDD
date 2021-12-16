package MYSQL;

import static MYSQL.Conexion.close;
import static MYSQL.Conexion.getConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.lang.model.SourceVersion;
import sweetbakery.dominio.Cliente;

public class ClientesDAOJDBC implements ClientesDAO {

    private static final String SQL_SELECT = "SELECT * FROM cliente";
    private static final String SQL_ORDENAR = "SELECT * FROM cliente ORDER BY idcliente ASC";
    private static final String SQL_INSERT = "INSERT INTO cliente"
            + "(dni,nombre,apellido,correo,telefono,usuario,contrasena) VALUES "
            + "(?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE cliente SET "
            + "correo = ?, "
            + "telefono = ?, "
            + "usuario = ?, "
            + "contrasena = ?  "
            + "WHERE dni = ?";

//    private static final String SQL_BUSCAR = "SELECT * FROM cliente WHERE usuario='?' AND dni='?'";

    private static final String SQL_DELETE = "DELETE FROM cliente  "
            + "WHERE dni = ? ";

    private Connection conexion;

    public ClientesDAOJDBC() {
    }

    public ClientesDAOJDBC(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public int actualizar(Cliente cliente) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0;

        try {
            conn = this.conexion != null ? this.conexion : getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);

            
            stmt.setString(1, cliente.getCorreo());
            stmt.setString(2, cliente.getTlf());
            stmt.setString(3, cliente.getUsuario());
            stmt.setString(4, cliente.getContrasena());
            stmt.setString(5, cliente.getDni());

            registros = stmt.executeUpdate();

        } catch (SQLException e) {
        } finally {
            close(stmt);
            if (this.conexion == null) {
                close(conn);
            }
        }
        return registros;
    }

    @Override
    public List<Cliente> OdenarID() throws SQLException {
        //CREAMOS NUESTROS OBJETOS A NULL
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Cliente> cliente = new ArrayList<>();

        try {
            conn = this.conexion != null ? this.conexion : getConnection();
            stmt = conn.prepareStatement(SQL_ORDENAR);
            rs = stmt.executeQuery();

            while (rs.next()) {

                int idCliente = rs.getInt("idcliente");
                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellido");
                String correo = rs.getString("correo");
                String telefono = rs.getString("telefono");
                String usuario = rs.getString("usuario");
                String contrasena = rs.getString("contrasena");

                cliente.add(new Cliente(idCliente, dni, nombre, apellidos, correo, telefono, usuario, contrasena));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            close(rs);
            close(stmt);
            if (this.conexion == null) {
                close(conn);
            }
        }
        return cliente;
    }

    @Override
    public List<Cliente> listar() throws SQLException {
        //CREAMOS NUESTROS OBJETOS A NULL
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //String [] arr = new String[3];
        List<Cliente> cliente = new ArrayList<>();

        try {
            conn = this.conexion != null ? this.conexion : getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {

                int idCliente = rs.getInt("idcliente");
                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellido");
                String correo = rs.getString("correo");
                String telefono = rs.getString("telefono");
                String usuario = rs.getString("usuario");
                String contrasena = rs.getString("contrasena");

                cliente.add(new Cliente(idCliente, dni, nombre, apellidos, correo, telefono, usuario, contrasena));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            close(rs);
            close(stmt);
            if (this.conexion == null) {
                close(conn);
            }
        }
        return cliente;
    }

    @Override
    public int insertar(Cliente cliente) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0; //num registros

        try {
            conn = this.conexion != null ? this.conexion : getConnection();

            stmt = conn.prepareStatement(SQL_INSERT);

            stmt.setString(1, cliente.getDni());
            stmt.setString(2, cliente.getNombre());
            stmt.setString(3, cliente.getApellido());
            stmt.setString(4, cliente.getCorreo());
            stmt.setString(5, cliente.getTlf());
            stmt.setString(6, cliente.getUsuario());
            stmt.setString(7, cliente.getContrasena());

//            String tempSQL = stmt.toString();
//            int i1 = tempSQL.indexOf(":") + 2;
//            tempSQL = tempSQL.substring(i1);
//            System.out.println(tempSQL);
            registros = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {

            close(stmt);
            if (this.conexion == null) {
                close(conn);
            }
        }

        return registros;
    }

    @Override
    public int borrarporDNI(Cliente clientes) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0; //num registros

        try {
            //1. ESTABLECER LA CONEXION
            conn = this.conexion != null ? this.conexion : getConnection();

            //2. PREPARED STATEMENT
            stmt = conn.prepareStatement(SQL_DELETE);

            stmt.setString(1, clientes.getDni());

            //ejecutamos la consulta
            registros = stmt.executeUpdate();

        } finally { //close
            close(stmt);
            if (this.conexion == null) {
                close(conn);
            }
        }
        return registros;
    }
    

    @Override
    public Cliente buscarUsuario(String usuario, String contrasena) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            //1. ESTABLECER LA CONEXION
            conn = this.conexion != null ? this.conexion : getConnection();

            //2. PREPARED STATEMENT
//             stmt = conn.prepareStatement(SQL_BUSCAR);
            String consulta = "SELECT * from cliente where usuario = ? and contrasena = ?";

            stmt = conn.prepareStatement(consulta);

            stmt.setString(1, usuario);
            stmt.setString(2, contrasena);

            ResultSet registros = stmt.executeQuery();

            if (registros.next()) {

                Cliente clientetmp = new Cliente(registros.getInt("idcliente"),
                         registros.getString("dni"),
                         registros.getString("nombre"),
                         registros.getString("apellido"),
                         registros.getString("correo"),
                         registros.getString("telefono"),
                         registros.getString("usuario"),
                         registros.getString("contrasena"));
                return clientetmp;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally { //close
            close(stmt);
            if (this.conexion == null) {
                close(conn);
            }
        }
        return null;
    }
}

