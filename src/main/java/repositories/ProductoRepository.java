package repositories;



import database.DBConnection;


import model.Cliente;
import model.Producto;
import model.Sesion;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepository {
    private Producto producto;

    public void cargarProductos(){
      BufferedReader br ;
      Connection connection = DBConnection.getConnection();
      PreparedStatement preparedStatement = null;

            //Crear la conexion
        URL url = null;
        try {
            url = new URL("https://dummyjson.com/products");
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            //BufferedReader --> leer la contestacion de la pagina -> TXT
            br = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            String lectura = br.readLine();
            JSONObject objeto = new JSONObject(lectura);
            JSONArray products = objeto.getJSONArray("products");
            String query = "INSERT INTO productos " +
                    "(id,availabilityStatus,category,description,dimensions_width,dimensions_height,dimensions_depth,discountPercentage,images,price,rating,shippingInformation,sku,stock,thumbnail, title,warrantyInformation, weight)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

            for (int i = 0; i <products.length() ; i++) {
                JSONObject product = products.getJSONObject(i);

                int id = product.getInt("id");
                String availabilityStatus = product.getString("availabilityStatus");
                String category = product.getString("category");
                String description = product.getString("description");
                JSONObject dimensions = product.getJSONObject("dimensions");
                double dimensions_width = dimensions.getDouble("width");
                double dimensions_height = dimensions.getDouble("height");
                double dimensions_depth = dimensions.getDouble("depth");
                double discountPercentage = product.getDouble("discountPercentage");
                JSONArray imagesArray =product.getJSONArray("images");
                String images = imagesArray.toString();
                double price = product.getDouble("price");
                double rating = product.getDouble("rating");
                String shippingInformation = product.getString("shippingInformation");
                String sku = product.getString("sku");
                int stock = product.getInt("stock");
                String thumbnail = product.getString("thumbnail");
                String title = product.getString("title");
                String warrantyInformation = product.getString("warrantyInformation");
                double weight = product.getDouble("weight");

                //preparar y ejecutar el statement
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, availabilityStatus);
                preparedStatement.setString(3, category);
                preparedStatement.setString(4, description);
                preparedStatement.setDouble(5, dimensions_width);
                preparedStatement.setDouble(6, dimensions_height);
                preparedStatement.setDouble(7, dimensions_depth);
                preparedStatement.setDouble(8, discountPercentage);
                preparedStatement.setString(9, images);
                preparedStatement.setDouble(10, price);
                preparedStatement.setDouble(11, rating);
                preparedStatement.setString(12, shippingInformation);
                preparedStatement.setString(13, sku);
                preparedStatement.setInt(14, stock);
                preparedStatement.setString(15, thumbnail);
                preparedStatement.setString(16, title);
                preparedStatement.setString(17, warrantyInformation);
                preparedStatement.setDouble(18, weight);

                preparedStatement.executeUpdate();
                JOptionPane.showMessageDialog(null,"Productos cargados en la Base de datos");








            }

        } catch (MalformedURLException e) {
            JOptionPane.showMessageDialog(null,"Error en la codificacion de la URL "+e.getMessage());
        } catch (IOException e) {

            JOptionPane.showMessageDialog(null,"Error al ejecutar"+e.getMessage());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al insertar los datos en la base de datos "+e.getMessage());
        }finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) DBConnection.closeConnecction();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Error al cerrar recursos");
                System.out.println(e.getMessage());
            }
        }


    }

    public ArrayList<Producto> verProductos(){
        Connection connection = DBConnection.getConnection();
        ArrayList<Producto> productos = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        String query = "SELECT id, title, price, stock, category, description FROM productos;";
        try {
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                producto = new Producto();
                producto.setId(resultSet.getInt("id"));
                producto.setTitle(resultSet.getString("title"));
                producto.setPrice(resultSet.getDouble("price"));
                producto.setStock(resultSet.getInt("stock"));
                producto.setCategory(resultSet.getString("category"));
                producto.setDescription(resultSet.getString("description"));
                productos.add(producto);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al obtener los productos");
        }finally {
            DBConnection.closeConnecction();
        }

        return productos;

    }

    public void borrarProductos(int idProducto){
        Connection connection = DBConnection.getConnection();
        PreparedStatement pr = null;

        try {
            String query = "DELETE FROM productos WHERE id = ?;";
            pr = connection.prepareStatement(query);
            pr.setInt(1, idProducto);//seteamos el id del producto
             int filasAfectadas = pr.executeUpdate();
             if (filasAfectadas > 0){
                 JOptionPane.showMessageDialog(null,"Producto con ID "+idProducto+" eliminado correctamente");
             }else {
                 JOptionPane.showMessageDialog(null,"No se encontro un producto con el ID "+idProducto);
             }


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al borrar los productos: " + e.getMessage());
        }finally {
            try {
                if (pr != null) pr.close();
                if (connection != null) DBConnection.closeConnecction();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error al cerrar los recursos: " + e.getMessage());
            }

        }
    }

    public void agregarProductosCarrito(int idCliente, int idProducto){
        Connection connection = DBConnection.getConnection();
        PreparedStatement ps;

        String query = "INSERT INTO carrito (id_cliente,id_producto,cantidad) VALUES (?,?,1)"+
                "ON DUPLICATE KEY UPDATE cantidad = cantidad + 1";//incrementa la cantidad si ya esta en el carrito


        try {
            ps = connection.prepareStatement(query);
            ps.setInt(1,idCliente);
            ps.setInt(2,idProducto);
            ps.executeUpdate();
            ps.close();
            JOptionPane.showMessageDialog(null,"Producto añadido correctamente. ✔");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al añadir producto al carrito. ❌ "+e.getMessage());
        }finally {
            DBConnection.closeConnecction();
        }

    }

    public ArrayList<Producto> verCarrito(){
        Connection connection = DBConnection.getConnection();
        ArrayList<Producto> productos = new ArrayList<>();

        //obtener el cliente logeado desde la sesion
        Cliente clienteLogueado = Sesion.getClienteLogiado();

        if (clienteLogueado == null){
            JOptionPane.showMessageDialog(null,"Debes iniciar sesion primero para ver los productos en el carrito");
            return productos;
        }
        String query = "SELECT productos.id, productos.description, productos.price, productos.title, carrito.cantidad " +
                "FROM carrito " +
                "JOIN productos ON carrito.id_producto = productos.id " +
                "WHERE carrito.id_cliente = ?";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, clienteLogueado.getId_cliente());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Producto producto1 = new Producto();
                producto1.setId(resultSet.getInt("id"));
                producto1.setDescription(resultSet.getString("description"));
                producto1.setPrice(resultSet.getDouble("price"));
                producto1.setTitle(resultSet.getString("title"));
                producto1.setStock(resultSet.getInt("cantidad"));
                productos.add(producto1);
            }
            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al obtener los productos del carrito. ❌ "+e.getMessage());
        }finally {
            DBConnection.closeConnecction();
        }


        return productos;

    }

}

