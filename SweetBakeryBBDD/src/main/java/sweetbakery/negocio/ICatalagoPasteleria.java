package sweetbakery.negocio;

import java.util.List;
import sweetbakery.dominio.Pedido;
import sweetbakery.dominio.Productos;

public interface ICatalagoPasteleria {

    public String iniciar(String nombreArchivo);

    void ordenarArchivo(String nombreCatalogo, String ordenar);

    double calcularTotalPrecio(List<Pedido> arrayPasteles);

    int contadorArticulos(List<Pedido> arrayPasteles);

    double minPrecioArticulo(List<Pedido> arrayPasteles);

    String listarRecurso(String nombreRecurso);

    void agregarPastelCatalogo(Productos producto, String nombreArchivo);

    void agregarPastelPedido(Productos producto, String nombreArchivo);

    void agregarCompra(Pedido pedido, String nombreArchivo);

    Productos buscarPastel(String nombreRecurso, int idProducto);

    Productos comprarPastel(String nombreRecurso, int idProducto);
}
