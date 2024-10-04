package view;

import model.Producto;
import repositories.ProductoRepository;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VerProductoVentana extends JFrame {

    public VerProductoVentana(){
        setTitle("Lista de productos 📝");
        setSize(800,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//no cierra la aplicacion entera

        ProductoRepository productoRepository = new ProductoRepository();
        ArrayList<Producto> productos = productoRepository.verProductos();

        //crear una tabla para mostrar los productos
        String[] nombreColumnas = {"ID","Titulo","Precio","Stock","Categoria","Descripción"};
        Object [][] data = new Object[productos.size()][6];

        //llenar los datos para la tabla
        for (int i = 0; i < productos.size(); i++) {
            Producto producto = productos.get(i);
            data[i][0] = producto.getId();
            data[i][1] = producto.getTitle();
            data[i][2] = producto.getPrice();
            data[i][3] = producto.getStock();
            data[i][4] = producto.getCategory();
            data[i][5] = producto.getDescription();

        }
        JTable table = new JTable(data,nombreColumnas);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JButton volverBoton = new JButton("Volver");
        volverBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuPrincipalVentana();
                dispose();

            }
        });

        //Agrega el boton volver al panel
        JPanel panelInferior = new JPanel();
        panelInferior.add(volverBoton);
        add(panelInferior,BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }
}
