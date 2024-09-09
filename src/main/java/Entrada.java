import model.Cliente;
import repositories.ClienteRepository;

import javax.swing.*;

public class Entrada {
    public static void main(String[] args) {
        ClienteRepository clienteRepository = new ClienteRepository();
       // clienteRepository.RegistrarCliente(new Cliente("Damaris Bonilla", "damisgonz@gmail.com", "4556677DCR"));
        //clienteRepository.RegistrarCliente(new Cliente("Jorge Amor", "jorgeAmor@gmail.com", "89788595LOP"));

        if (clienteRepository.relizarLoging("jorgeAmor@gmail.com", "89788595LOP")){
            JOptionPane.showMessageDialog(null,"Login realizado con exito");
        }else {
            JOptionPane.showMessageDialog(null, "No se ha podido hacer el login correctamente");
        }





    }
}
