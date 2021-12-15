package MYSQL;

import java.sql.SQLException;
import java.util.List;
import sweetbakery.dominio.Cliente;

public interface ClientesDAO {
    
    int actualizar(Cliente cliente) throws SQLException;
    
    List<Cliente> OdenarID() throws SQLException;
    
    List<Cliente> listar() throws SQLException;
    
    int insertar(Cliente cliente) throws SQLException ;
    
    int borrarporDNI(Cliente clientes) throws SQLException ;
    
    Cliente buscarUsuario(String usuario, String contrasena) throws SQLException ;
}
