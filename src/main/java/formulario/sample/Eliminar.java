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


public class Eliminar {
    @FXML
    private TextField Eli_codigo;
    @FXML
    private Button Eli_Buscar;
    @FXML
    private Button Eliminar;
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
    public void Eliminar(ActionEvent actionEvent) throws SQLException {
        conectar();
        String SQL = "DELETE from productos WHERE codigo ="+Eli_codigo.getText();
        ps=con.prepareStatement(SQL);
        try {
            ps.executeUpdate();
            Alert mensaje = new Alert(Alert.AlertType.CONFIRMATION);
            mensaje.setTitle("Ventana Advertencia");
            mensaje.setContentText("Se ha ELIMINADO los datos del producto con codigo: "+Eli_codigo.getText());
            mensaje.showAndWait();
            Eli_codigo.setText("");

        } catch (Exception e) {System.out.println("Error: "+e);}
    }

    public void Eli_Buscar(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Busqueda.fxml"));
        Parent root =fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
