package repositories;

import database.DBConnection;
import database.EsquemaDB;
import model.Cliente;

import javax.swing.*;
import java.sql.*;


public class ClienteRepository {
   // Scanner scanner = new Scanner(System.in);
    private Connection connection;

    public void registrarCliente(Cliente cliente){
        PreparedStatement preparedStatement ;
        String correo = cliente.getCorreo();

        // se abre la conexion
        connection = DBConnection.getConnection();

        if (!existeCorreoDB(correo)){
            try {
                String query = "INSERT INTO clientes (nombre,correo,pass,telefono) VALUES (?,?,?,?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, cliente.getNombre());
                preparedStatement.setString(2, cliente.getCorreo());
                preparedStatement.setString(3, cliente.getPassword());
                preparedStatement.setInt(4,cliente.getTelefono());

                preparedStatement.execute();
                preparedStatement.close();

                JOptionPane.showMessageDialog(null,"Cliente Registrado con exito. ✔");


            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,"Fallo en la sentencia SQL. "+e.getMessage()+"❌");
            }

        }else{
            JOptionPane.showMessageDialog(null,"El cliente no se ha podido registrar. ❌");
        }
        DBConnection.closeConnecction();



    }

    public boolean existeCorreoDB(String correo){
        connection = DBConnection.getConnection();
        boolean estaCorreo = false;


        ResultSet resultSet;
        // Consulta SQL para buscar el correo específico en la tabla
        String query = String.format("SELECT %s FROM %s WHERE %s = ?",
                EsquemaDB.COL_CORREO,
                EsquemaDB.TAB_CLIENTE,
                EsquemaDB.COL_CORREO);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,correo); // Asigna el valor del correo
            resultSet = preparedStatement.executeQuery();
            //estaCorreo = resultSet.next();
            while (resultSet.next()){
                if (resultSet.getString("correo").equalsIgnoreCase(correo)){
                    estaCorreo = true;
                    break;
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error SQL "+ e.getMessage()+"❌");
        }
        return estaCorreo;
    }

    public Cliente relizarLoging(String correo, String password){
        connection = DBConnection.getConnection();
        Cliente cliente = null;
        ResultSet resultSet;

        String query = String.format("SELECT * FROM %s WHERE %s = ? AND %s = ?",
                EsquemaDB.TAB_CLIENTE, EsquemaDB.COL_CORREO, EsquemaDB.COL_PASSWORD);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,correo);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()){
                // Si las credenciales son correctas, creamos un objeto Cliente con los datos
                cliente = new Cliente();
                cliente.setId_cliente(resultSet.getInt("id_cliente"));
                cliente.setNombre(resultSet.getString("nombre"));
                cliente.setCorreo(resultSet.getString("correo"));
                cliente.setPassword(resultSet.getString("pass"));
                cliente.setTelefono(resultSet.getInt("telefono"));
            }
            preparedStatement.close();
            resultSet.close();


        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage()+" ❌");
        }finally {
            DBConnection.closeConnecction();
            connection = null;
        }
        return cliente;


    }

    public void eliminarCliente(int id){
        connection = DBConnection.getConnection();
        PreparedStatement preparedStatement;
        String query = "DELETE FROM clientes WHERE id_cliente = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            int filasAfectada = preparedStatement.executeUpdate();
            if (filasAfectada > 0){
                JOptionPane.showMessageDialog(null,"Cliente eliminado correctamente..✔");
            }else{
                JOptionPane.showMessageDialog(null,"No se ha encontra el cliente con ese ID..❌");
            }
            preparedStatement.close();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Ha habido un error de eliminacion.. "+e.getMessage()+". ❌");
        }finally {
            DBConnection.closeConnecction();
            connection = null;
        }

    }
    /*public void actualizarCliente(Cliente cliente){
        connection = DBConnection.getConnection();
        PreparedStatement preparedStatement;

        String query = "UPDATE CLIENTES SET nombre = ?, correo = ?, telefono = ? WHERE id_cliente = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,cliente.getNombre());
            preparedStatement.setString(2, cliente.getCorreo());
            preparedStatement.setInt(3,cliente.getTelefono());
            preparedStatement.setInt(4,cliente.getId_cliente());

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0){
                JOptionPane.showMessageDialog(null,"Cliente actualizado correctamente..✔");
            }else {
                JOptionPane.showMessageDialog(null,"No se ha encontra el cliente con ese ID..❌");
            }
            preparedStatement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al actualizar.."+e.getMessage()+"❌");
        }finally {
            DBConnection.closeConnecction();
            connection = null;
        }

    }*/
    public void actualizarCliente(int id, Cliente cliente){
        connection = DBConnection.getConnection();
        PreparedStatement preparedStatement;

        String query = "UPDATE CLIENTES SET nombre = ?, correo = ?, pass = ?, telefono = ? WHERE id_cliente = ?";
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,cliente.getNombre());
            preparedStatement.setString(2, cliente.getCorreo());
            preparedStatement.setString(3, cliente.getPassword());
            preparedStatement.setInt(4,cliente.getTelefono());
            preparedStatement.setInt(5,id);

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0){
                JOptionPane.showMessageDialog(null,"Cliente actualizado correctamente..✔");
            }else {
                JOptionPane.showMessageDialog(null,"No se ha encontra el cliente con ese ID..❌");
            }
            preparedStatement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al actualizar.."+e.getMessage()+"❌");
        }finally {
            DBConnection.closeConnecction();
            connection = null;
        }

    }

}
