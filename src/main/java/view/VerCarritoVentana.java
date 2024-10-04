package view;

import model.Producto;
import repositories.ProductoRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VerCarritoVentana extends JFrame {
    public VerCarritoVentana(){
        setTitle("Carrito de compras");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //obtener los productos del carrito para el cliente logeado

        ProductoRepository productoRepository = new ProductoRepository();

        ArrayList<Producto> productosCarrito = productoRepository.verCarrito();

        if (productosCarrito.isEmpty()){
            JOptionPane.showMessageDialog(null,"El carrito esta vacio");
            return;
        }

        String []columnName = {"ID","Descripción","Precio","Titutlo","Cantidad"};
        Object[][] data = new Object[columnName.length][5];

        for (int i = 0; i < productosCarrito.size(); i++) {
            Producto producto = productosCarrito.get(i);
            data[i][0] = producto.getId();
            data[i][1] = producto.getDescription();
            data[i][2] = producto.getPrice();
            data[i][3] = producto.getTitle();
            data[i][4] = producto.getStock(); //cantidad en el carrito
        }

        JTable table = new JTable(data,columnName);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JButton volverBoton = new JButton("Volver atras");
        volverBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuPrincipalVentana();
                dispose();

            }
        });

        JPanel panelInferior = new JPanel();
        panelInferior.add(volverBoton);
        add(panelInferior, BorderLayout.SOUTH);

        setLocationRelativeTo(null);

        setVisible(true);

    }
}
