package view;

import model.Cliente;
import repositories.ClienteRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroClienteVentana extends JFrame {
    private JTextField nombreField;
    private JTextField correoField;
    private JPasswordField passwordField;
    private JTextField telefonoField;
    private ClienteRepository cliente;

    public RegistroClienteVentana(){
       cliente = new ClienteRepository();

        setTitle("Gestión de Clientes");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        //creamos el panel central del formulario

        JPanel formularioPanel = new JPanel(new GridLayout(4,2));

        // Crear componentes de la interfaz
        JLabel nombreLabel = new JLabel("Nombre:");
        nombreField = new JTextField();
        JLabel correoLabel = new JLabel("Correo:");
        correoField = new JTextField();
        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordField = new JPasswordField();
        JLabel telefonoLabel = new JLabel("Teléfono:");
        telefonoField = new JTextField();


        formularioPanel.add(nombreLabel);
        formularioPanel.add(nombreField);
        formularioPanel.add(correoLabel);
        formularioPanel.add(correoField);
        formularioPanel.add(passwordLabel);
        formularioPanel.add(passwordField);
        formularioPanel.add(telefonoLabel);
        formularioPanel.add(telefonoField);
        //add(registrarButton);
        add(formularioPanel,BorderLayout.CENTER);


        //agregamos los botones
        JButton registrarButton = new JButton("Registrar");
        JButton volverBoton = new JButton("Volver atras");

        JPanel botonInferior = new JPanel();
        botonInferior.add(registrarButton);
        botonInferior.add(volverBoton);
        add(botonInferior, BorderLayout.SOUTH);

        volverBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginVentana();
                dispose();
            }
        });

        //accion del boton para registrar clientes
        registrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarCliente();
            }
        });



        setLocationRelativeTo(null);

        setVisible(true);


    }

    private void registrarCliente(){
        String nombre = nombreField.getText();
        String correo = correoField.getText();
        String password = new String(passwordField.getPassword());
        int telefono = Integer.parseInt(telefonoField.getText());

        Cliente nuevoCliente = new Cliente(nombre, correo, password, telefono);

        cliente.registrarCliente(nuevoCliente);
    }


}
