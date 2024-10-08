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

public class VerProductoVentana extends JFrame {

   private JTable table;
   private ArrayList<Producto> productos;

    public VerProductoVentana(){
        setTitle("Lista de productos 📝");
        setSize(800,400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);//no cierra la aplicacion entera

        ProductoRepository productoRepository = new ProductoRepository();
         productos = productoRepository.verProductos();

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
         table = new JTable(data,nombreColumnas);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        //crear panel vertical a la derecha para los botones

        //crear boton para añadir productos al carrito
        JButton agregarProductosBoton = new JButton("Anadir al carrito");
        agregarProductosBoton.setAlignmentX(Component.CENTER_ALIGNMENT);//botones alineados verticales




        agregarProductosBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarProductosAlCarrito();

            }
        });

        JButton volverBoton = new JButton("Volver");
        volverBoton.setAlignmentX(Component.CENTER_ALIGNMENT);//centrar el boton en el panel

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
        panelInferior.add(agregarProductosBoton);
        add(panelInferior,BorderLayout.SOUTH);


        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void agregarProductosAlCarrito(){
        int selecionarFilas = table.getSelectedRow();//obtener la fila seleccionada

        if (selecionarFilas == -1){
            JOptionPane.showMessageDialog(this, "Por favor, selecciona un producto");
            return;
        }

        Producto productoSeleccionado = productos.get(selecionarFilas); //obtener el producto de la fila seleccionada
        Cliente clienteLogueado = Sesion.getClienteLogiado(); //obtener al cliente logueado

        if (clienteLogueado != null){
            ProductoRepository productoRepository = new ProductoRepository();
            productoRepository.agregarProductosCarrito(clienteLogueado.getId_cliente(),productoSeleccionado.getId());

        }else {
            JOptionPane.showMessageDialog(this, "Inicia sesión para agregar productos al carrito");
        }
    }
}
