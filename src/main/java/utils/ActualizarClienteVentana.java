package utils;

import model.Cliente;
import repositories.ClienteRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActualizarClienteVentana extends JFrame {
    private JTextField idField;
    private JTextField nombreField;
    private JTextField correoField;
    private JPasswordField passwordField;
    private JTextField telefonoField;
    private ClienteRepository clienteRepository;
    public ActualizarClienteVentana(){

        setTitle("Actualizar cliente");
        setSize(400,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6,2));

        //creamos componentes
        JLabel idLabel = new JLabel("ID del cliente");
        idField = new JTextField();
        JLabel nombreLabel = new JLabel("Nuevo nombre");
        nombreField = new JTextField();
        JLabel correoLabel = new JLabel("Nuevo correo");
        correoField = new JTextField();
        JLabel passwordLabel = new JLabel("Nueva contraseña");
        passwordField =  new JPasswordField();
        JLabel telefonoLabel = new JLabel("Nuevo telefono");
        telefonoField = new JTextField();
        JButton actualizarBoton = new JButton("Actualizar cliente");
        JButton volverBoton = new JButton("Volver");

        //agregamos los componentes

        add(idLabel);
        add(idField);
        add(nombreLabel);
        add(nombreField);
        add(correoLabel);
        add(correoField);
        add(passwordLabel);
        add(passwordField);
        add(telefonoLabel);
        add(telefonoField);
        add(actualizarBoton, BorderLayout.CENTER);
        add(volverBoton);


        volverBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuPrincipalVentana();
                dispose();
            }
        });

        actualizarBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCliente();
            }
        });


        setLocationRelativeTo(null);
        setVisible(true);


    }

    private void actualizarCliente(){
        int id = Integer.parseInt(idField.getText());
        String nombre = nombreField.getText();
        String correo = correoField.getText();
        String password = new String(passwordField.getPassword());
        int telefono = Integer.parseInt(telefonoField.getText());

        //crear el cliente actualizado
        Cliente clienteActualizado = new Cliente(nombre,correo,password,telefono);
        clienteRepository = new ClienteRepository();
        clienteRepository.actualizarCliente(id,clienteActualizado);
        JOptionPane.showMessageDialog(this, "Cliente actualizado correctamente. ✔");



    }
}
