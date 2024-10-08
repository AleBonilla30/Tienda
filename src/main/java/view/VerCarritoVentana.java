package view;

import model.Cliente;
import model.Producto;
import model.Sesion;
import repositories.ProductoRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VerCarritoVentana extends JFrame {

    private JTable table;
    private ArrayList<Producto> productosCarrito;

    public VerCarritoVentana(){
        setTitle("Carrito de compras");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //obtener los productos del carrito para el cliente logeado

        ProductoRepository productoRepository = new ProductoRepository();

         productosCarrito = productoRepository.verCarrito();

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

         table = new JTable(data,columnName);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JButton volverBoton = new JButton("Volver atras");
        JButton eliminarProductosBoton = new JButton("Borrar productos");
        volverBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuPrincipalVentana();
                dispose();

            }
        });
        eliminarProductosBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto();
            }
        });

        JPanel panelInferior = new JPanel();
        panelInferior.add(volverBoton);
        panelInferior.add(eliminarProductosBoton);
        add(panelInferior, BorderLayout.SOUTH);

        setLocationRelativeTo(null);

        setVisible(true);

    }

    private void eliminarProducto(){
        int selecionarFilas = table.getSelectedRow(); //obtener la fila

        if (selecionarFilas == -1){
            JOptionPane.showMessageDialog(this,"Por favor, selecciona un producto para eliminar");
            return;
        }
        int idProducto = productosCarrito.get(selecionarFilas).getId();
        Cliente clienteLogueado = Sesion.getClienteLogiado();
        if (clienteLogueado != null){
            ProductoRepository productoRepository = new ProductoRepository();
            productoRepository.diminuirProductosCarrito(clienteLogueado.getId_cliente(),idProducto);
            productosCarrito = productoRepository.verCarrito();//vuelve a cargar los productos en el carrito
        }else {
            JOptionPane.showMessageDialog(this, "Inicia sesión para eliminar productos del carrito");
        }
    }
}
