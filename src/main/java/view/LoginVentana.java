package view;

import model.Cliente;
import model.Sesion;
import repositories.ClienteRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginVentana extends JFrame {
    private JTextField correoField;
    private JPasswordField passwordField;
    private JTextField nombreField;
    private JTextField telefonoField;
    private ClienteRepository clienteRepository = new ClienteRepository();

    public LoginVentana()  {
        setTitle("Iniciar sesión");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3,2));

        //crear componentes

        JLabel correoLabel = new JLabel("Correo:");
        correoField = new JTextField();
        JLabel passwordLabel = new JLabel("Contraseña:");
        passwordField = new JPasswordField();
        JButton loginBoton = new JButton("Iniciar sesión");
        JButton registroBoton = new JButton("Registrar");

        //agregarmos los componentes a la ventana
        add(correoLabel);
        add(correoField);
        add(passwordLabel);
        add(passwordField);
        add(loginBoton);
        add(registroBoton);

        //acciones del boton

        loginBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });

        registroBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirRegistro();

            }
        });
        setLocationRelativeTo(null);

        setVisible(true);



    }

    private void iniciarSesion(){

        String correo = correoField.getText();
        String pass = new String(passwordField.getPassword());

        // Llamamos a relizarLoging() y obtenemos un objeto Cliente si las credenciales son correctas
        Cliente cliente = clienteRepository.relizarLoging(correo,pass);

        if (cliente != null){
            Sesion.setClienteLogiado(cliente);
            JOptionPane.showMessageDialog(this, "Inicio de sesión exitoso.");
            abrirMenuPricipal();
        }else {
            JOptionPane.showMessageDialog(this, "Correo o contraseña incorrectos.");

        }
    }


    private void abrirMenuPricipal(){
        new MenuPrincipalVentana();
        this.dispose();//cierra la ventana actual
    }

    private void abrirRegistro(){
        new RegistroClienteVentana();
        this.dispose();
    }
}
