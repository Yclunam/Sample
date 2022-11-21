package formulario.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.*;

public class Agregar {
    @FXML
    private Button Btn_Agregar;
    @FXML
    private TextField Agre_codigo;
    @FXML
    private TextField Agre_nombre;
    @FXML
    private TextField Agre_precio;
    @FXML
    private TextField Agre_unidades;

    PreparedStatement ps;
    Connection con;
    ResultSet resultSet;
    ResultSet rs;

    public void conectar(){
        try {
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios", "root", "Luna9508");

            System.out.println("Conectado");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public void Btn_Agregar(ActionEvent actionEvent) throws SQLException {
        PreparedStatement ps;
        String sql;
        conectar();
        sql = "insert into productos(codigo, nombre, cantidad, precio) values(?,?,?,?)";
        ps = con.prepareStatement(sql);
        ps.setInt(1, Integer.parseInt(Agre_codigo.getText()));
        ps.setString(2, Agre_nombre.getText().toUpperCase());
        ps.setInt(3, Integer.parseInt(Agre_unidades.getText().toUpperCase())) ;
        ps.setInt(4, Integer.parseInt(Agre_precio.getText())) ;
        ps.executeUpdate();
        System.out.println("Se han insertado los datos");
        Agre_codigo.setText("");
        Agre_nombre.setText("");
        Agre_precio.setText("");
        Agre_unidades.setText("");
        Alert mensaje = new Alert(Alert.AlertType.CONFIRMATION);
        mensaje.setTitle("Ventana Confirmacion");
        mensaje.setContentText("El nuevo producto ha sido creado y agregado a la base de datos");
        mensaje.showAndWait();
    }
}
