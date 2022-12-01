package formulario.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Esta clase es una ventana en la cual se le da ha escoger al administrados la funcion que quiera desempeñar al ingresar al sistema.
 */
public class VentanaAdministrador {
    @FXML
    private Button Eliminar_Usuario;
    @FXML
    private Button Datos;
    @FXML
    private Button Cajero;
    @FXML
    private Button Crearusuario;


    public void Crearusuario(ActionEvent actionEvent) throws IOException {
        /**
         * Este metodo es mas un llamado a otra ventanta donde la implementacion del metodo ya esta hecha.
         */
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Creando_Usuario.fxml"));
        Parent root =fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void Cajero(ActionEvent actionEvent) throws IOException {
        /**
         * Este metodo es mas un llamado a otra ventanta donde la implementacion del metodo ya esta hecha.
         */
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Ventana_Cajero.fxml"));
        Parent root =fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void Datos(ActionEvent actionEvent) throws IOException {
        /**
         * Este metodo es mas un llamado a otra ventanta donde la implementacion del metodo ya esta hecha.
         */
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Modificar_Base_Datos.fxml"));
        Parent root =fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void Eliminar_Usuario(ActionEvent actionEvent) throws IOException {
        /**
         * Este metodo es mas un llamado a otra ventanta donde la implementacion del metodo ya esta hecha.
         */
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Eliminar_Usuario.fxml"));
        Parent root =fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
