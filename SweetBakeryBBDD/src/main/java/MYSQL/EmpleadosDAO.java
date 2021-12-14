package MYSQL;

import java.sql.SQLException;
import java.util.List;
import sweetbakery.dominio.Empleado;

public interface EmpleadosDAO {
    
    int actualizar(Empleado empleado) throws SQLException;
    
    List<Empleado> listar() throws SQLException;
    
    int insertar(Empleado empleado) throws SQLException ;
    
    int borrarporId(Empleado empleado) throws SQLException;
    
    List<Empleado> OrdenarDescApellido() throws SQLException;
    
    Empleado buscarEmpleado(String usuario) throws SQLException ;
}
