package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPrincipalVentana extends JFrame {

    public MenuPrincipalVentana(){
        setTitle("Menu Principal");
        setSize(300,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6,1));

        JButton actualizarClienteBoton = new JButton("Actualizar cliente");
        JButton verProductosBoton = new JButton("Ver productos");
        JButton comprarProductosBoton = new JButton("Comprar productos");
        JButton verCarritoBoton = new JButton("Ver carrito");
        JButton finalizarCompraBoton = new JButton("Finalizar compra");
        JButton cerrarSesionBoton= new JButton("Cerrar sesión");

        //añadir los botones a la ventana
        add(actualizarClienteBoton);
        add(verProductosBoton);
        add(comprarProductosBoton);
        add(verCarritoBoton);
        add(finalizarCompraBoton);
        add(cerrarSesionBoton);

        //acciones de los botones

        verProductosBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verProductos();

            }
        });
        actualizarClienteBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCliente();
            }
        });

        cerrarSesionBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarSesion();
            }
        });
        verCarritoBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verCarrito();
            }
        });

        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void verProductos(){
        new VerProductoVentana();
        this.dispose();
    }

    private void actualizarCliente(){
        new ActualizarClienteVentana();
        this.dispose();//cierra la ventana despues de actualizar
    }

    private void cerrarSesion(){
        new LoginVentana();
        this.dispose();
    }
    private void verCarrito(){
        new VerCarritoVentana();
        this.dispose();
    }



}
