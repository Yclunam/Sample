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

/**
 * Clase para controlar la ventana eliminar y implementar los metodos de esta misma ventana.
 */
public class Eliminar extends HelloController{
    @FXML
    private TextField Eli_codigo;
    @FXML
    private Button Eli_Buscar;
    @FXML
    private Button Eliminar;
    PreparedStatement ps;
    Connection con;
    public void Eliminar(ActionEvent actionEvent) throws SQLException {
        /**
         * Realiaza la busqueda del producto con codigo ingresado y lo elimina de la base de datos de mysql.
         */
        conectar();
        String SQL = "DELETE from productos WHERE codigo ="+Eli_codigo.getText();
        con= DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios", "root", "Luna9508");
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
        /**
         * Abre la ventana busqueda para poder estar seguro que esta eliminando el producto deseado.
         */
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Busqueda.fxml"));
        Parent root =fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();

    }

}
