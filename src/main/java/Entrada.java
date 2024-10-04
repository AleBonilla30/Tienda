import view.LoginVentana;
import repositories.ProductoRepository;

public class Entrada {
    public static void main(String[] args) {
        ProductoRepository productoRepository = new ProductoRepository();
        //productoRepository.cargarProductos();
        productoRepository.borrarProductos(31);

         new LoginVentana();





    }
}
