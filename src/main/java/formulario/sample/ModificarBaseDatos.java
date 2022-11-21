package formulario.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class ModificarBaseDatos {
    @FXML
    private Button Eliminar;
    @FXML
    private Button Modificar_Base;
    @FXML
    private Button Btn_Agregar;
    @FXML
    private TextField Agre_codigo;
    @FXML
    private TextField Agre_nombre;
    @FXML
    private TextField Agre_precio;
    @FXML
    private TextField Agre_cantidad;
    @FXML

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
        ps.setInt(3, Integer.parseInt(Agre_cantidad.getText().toUpperCase())) ;
        ps.setInt(4, Integer.parseInt(Agre_precio.getText())) ;
        ps.executeUpdate();
        System.out.println("Se han insertado los datos");
        Agre_codigo.setText("");
        Agre_nombre.setText("");
        Agre_precio.setText("");
        Agre_cantidad.setText("");
        Alert mensaje = new Alert(Alert.AlertType.CONFIRMATION);
        mensaje.setTitle("Ventana Confirmacion");
        mensaje.setContentText("El nuevo producto ha sido creado y agregado a la base de datos");
        mensaje.showAndWait();
    }

    public void Modificar_Base(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Agregar.fxml"));
        Parent root =fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void Eliminar(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Eliminar.fxml"));
        Parent root =fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
