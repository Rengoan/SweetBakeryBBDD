package MYSQL;

import static MYSQL.Conexion.close;
import static MYSQL.Conexion.getConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sweetbakery.dominio.*;

public class EmpleadosDAOJDBC implements EmpleadosDAO {

    private static final String SQL_SELECT = "SELECT * FROM empleado";
    private static final String SQL_ORDENARDESC = "SELECT * FROM empleado ORDER BY apellido DESC";
    private static final String SQL_INSERT = "INSERT INTO empleado"
            + "(dni, nombre, apellido,correo,telefono,usuario,contrasena,ssocial,cbancaria) VALUES "
            + "(?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE empleado SET "
            + "nombre= ?, "
            + "apellidos= ? "
            + "correo= ? "
            + "telefono= ? "
            + "cbancaria= ? "
            + "WHERE dni=?";

    private static final String SQL_DELETE = "DELETE FROM empleado  "
            + "WHERE telefono=?";

    private static final String SQL_BUSCAR = "SELECT * FROM empleado WHERE dni='?' ";

    private Connection conexion;

    public EmpleadosDAOJDBC() {
    }

    public EmpleadosDAOJDBC(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public int actualizar(Empleado empleado) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0; //num registros

        try {
            conn = this.conexion != null ? this.conexion : getConnection();
            stmt = conn.prepareStatement(SQL_UPDATE);

            //Ejecutar los insert con los interrogantes
            stmt.setString(1, empleado.getDni()); //segundo interrogante
            stmt.setString(2, empleado.getNombre());
            stmt.setString(3, empleado.getApellido());
            stmt.setString(4, empleado.getCorreo());
            stmt.setString(5, empleado.getCbancaria());

            //ejecutamos la consulta
            registros = stmt.executeUpdate();

        } finally {
            close(stmt);
            if (this.conexion == null) {
                close(conn);
            }
        }
        return registros;
    }

    @Override
    public List<Empleado> listar() throws SQLException {
        //CREAMOS NUESTROS OBJETOS A NULL
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //String [] arr = new String[3];
        List<Empleado> empleado = new ArrayList<>();

        try {
            conn = this.conexion != null ? this.conexion : getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("idEmpleado");
                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String correo = rs.getString("correo");
                String telefono = rs.getString("telefono");
                String usuario = rs.getString("usuario");
                String contrasena = rs.getString("contrasena");
                String ssocial = rs.getString("ssocial");
                String cbancaria = rs.getString("cbancaria");

                empleado.add(new Empleado(id, dni, nombre, apellido, correo, telefono, usuario, contrasena, ssocial, cbancaria));
            }
        } finally { //ejecuta siempre
            close(rs);
            close(stmt);
            //Si la conn es nula, quiere decir que viene desde dento, asi que la cierro
            if (this.conexion == null) {
                close(conn);
            }
        }

        return empleado;
    }

    @Override
    public int insertar(Empleado empleado) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0; //num registros

        try {
            //1. ESTABLECER LA CONEXION
            conn = this.conexion != null ? this.conexion : getConnection();

            //2. PREPARED STATEMENT
            stmt = conn.prepareStatement(SQL_INSERT);

            //Ejecutar los insert con los interrogantes
            stmt.setString(1, empleado.getDni()); //segundo interrogante
            stmt.setString(2, empleado.getNombre());
            stmt.setString(3, empleado.getApellido());
            stmt.setString(4, empleado.getCorreo());
            stmt.setString(5, empleado.getTlf());
            stmt.setString(6, empleado.getUsuario());
            stmt.setString(7, empleado.getContrasena());
            stmt.setString(8, empleado.getSsocial());
            stmt.setString(9, empleado.getCbancaria());

            //ejecutamos la consulta
            registros = stmt.executeUpdate();

        } finally { //close
//            stmt.close();
//            conn.close();
            close(stmt);
            if (this.conexion == null) {
                close(conn);
            }
        }
        return registros;
    }

    @Override
    public int borrarporId(Empleado empleado) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        int registros = 0; //num registros

        try {
            //1. ESTABLECER LA CONEXION
            conn = this.conexion != null ? this.conexion : getConnection();

            //2. PREPARED STATEMENT
            stmt = conn.prepareStatement(SQL_DELETE);

            stmt.setInt(1, empleado.getIdEmp());

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
    public List<Empleado> OrdenarDescApellido() throws SQLException {
        //CREAMOS NUESTROS OBJETOS A NULL
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        //String [] arr = new String[3];
        List<Empleado> empleado = new ArrayList<>();

        try {
            conn = this.conexion != null ? this.conexion : getConnection();
            stmt = conn.prepareStatement(SQL_ORDENARDESC);
            rs = stmt.executeQuery();

            while (rs.next()) {

                int id = rs.getInt("idEmpleado");
                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String correo = rs.getString("correo");
                String telefono = rs.getString("telefono");
                String usuario = rs.getString("usuario");
                String contrasena = rs.getString("contrasena");
                String ssocial = rs.getString("ssocial");
                String cbancaria = rs.getString("cbancaria");

                empleado.add(new Empleado(id, dni, nombre, apellido, correo, telefono, usuario, contrasena, ssocial, cbancaria));
            }
        } finally { //ejecuta siempre
            close(rs);
            close(stmt);
            //Si la conn es nula, quiere decir que viene desde dento, asi que la cierro
            if (this.conexion == null) {
                close(conn);
            }
        }

        return empleado;
    }

    @Override
    public Empleado buscarEmpleado(String usuario) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            //1. ESTABLECER LA CONEXION
            conn = this.conexion != null ? this.conexion : getConnection();

            //2. PREPARED STATEMENT
            stmt = conn.prepareStatement(SQL_BUSCAR);
//            String consulta = "SELECT * from cliente where usuario = ? and dni = ?";

            stmt.setString(1, usuario);

            ResultSet registros = stmt.executeQuery();

            if (registros.next()) {

                Empleado empleadotmp = new Empleado(registros.getInt("idEmpleado"),
                        registros.getString("dni"),
                        registros.getString("nombre"),
                        registros.getString("apellido"),
                        registros.getString("correo"),
                        registros.getString("telefono"),
                        registros.getString("usuario"),
                        registros.getString("contrasena"),
                        registros.getString("ssocial"),
                        registros.getString("cbancaria")
                );

                return empleadotmp;
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
