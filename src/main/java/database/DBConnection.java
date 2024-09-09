package database;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    static Connection connection = null;

    //Crear conexion
    private static void createConecction(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String uri = "jdbc:mysql://127.0.0.1:3306/almacen";
            connection = DriverManager.getConnection(uri,"root","");

            if (connection != null) {
                JOptionPane.showMessageDialog(null,"Conexión establecida correctamente. ✔");
            } else {
                JOptionPane.showMessageDialog(null,"No se pudo establecer la conexión. ❌");
            }

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,"Error al cargar el Driver. ❌");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Error en la ejecucion SQL. ❌");
        }
    }

    //Pedir conexion

    public static Connection getConnection(){
        if (connection == null){
            createConecction();
        }
        return connection;
    }

    public static void closeConnecction(){

        try {
            connection.close();
            connection = null;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"Error al cerrar la Base de Datos. ❌");
        }

    }
}
