import model.Cliente;
import repositories.ClienteRepository;

import javax.swing.*;

public class Entrada {
    public static void main(String[] args) {
        ClienteRepository clienteRepository = new ClienteRepository();
        /*clienteRepository.RegistrarCliente(new Cliente("Damaris Bonilla", "damisgonz@gmail.com", "4556677DCR"));
        clienteRepository.RegistrarCliente(new Cliente("Jorge Amor", "jorgeAmor@gmail.com", "89788595LOP"));

        if (clienteRepository.relizarLoging("jorgeAmor@gmail.com", "89788595LOP")){
            JOptionPane.showMessageDialog(null,"Login realizado con exito");
        }else {
            JOptionPane.showMessageDialog(null, "No se ha podido hacer el login correctamente");
        }*/
        //clienteRepository.eliminarCliente(3);
        Cliente cliente = new Cliente();
        cliente.setId_cliente(8);
        cliente.setNombre("Jorge Morales");
        cliente.setCorreo("jorge.morales@gmail.com");
        cliente.setTelefono(678490983);
        clienteRepository.actualizarCliente(cliente);





    }
}
