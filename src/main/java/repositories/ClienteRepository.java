package repositories;

import database.DBConnection;
import database.EsquemaDB;
import model.Cliente;

import javax.swing.*;
import java.sql.*;


public class ClienteRepository {
   // Scanner scanner = new Scanner(System.in);
    private Connection connection;

    public void RegistrarCliente(Cliente cliente){
        PreparedStatement preparedStatement ;
        String correo = cliente.getCorreo();

        // se abre la conexion
        connection = DBConnection.getConnection();

        if (!existeCorreoDB(correo)){
            try {
                String query = "INSERT INTO clientes (nombre,correo,pass) VALUES (?,?,?)";
                preparedStatement = connection.prepareStatement(query);
                preparedStatement.setString(1, cliente.getNombre());
                preparedStatement.setString(2, cliente.getCorreo());
                preparedStatement.setString(3, cliente.getPassword());

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

    public boolean relizarLoging(String correo, String password){
        connection = DBConnection.getConnection();
        boolean estaCliente = false;
        ResultSet resultSet;

        String query = String.format("SELECT * FROM %s WHERE %s = ? AND %s = ?",
                EsquemaDB.TAB_CLIENTE, EsquemaDB.COL_CORREO, EsquemaDB.COL_PASSWORD);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,correo);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();
            estaCliente = resultSet.next();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.getMessage()+" ❌");
        }finally {
            DBConnection.closeConnecction();
            connection = null;
        }
        return estaCliente;


    }

}
