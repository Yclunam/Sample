package formulario.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.stage.Stage;
import java.io.IOException;



public class HelloApplication extends Application {



    @Override
    public void start(Stage stage) throws IOException {
        //Ventana_Login
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        stage.setTitle("Login");
//        stage.setScene(scene);
//        stage.show();
//        //Ventana_Crear_Usuario
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Creando_Usuario.fxml"));
//        Scene scene2 = new Scene(fxmlLoader.load(), 470, 410);
//        stage.setTitle("Creando Usuario");
//        stage.setScene(scene2);
//        stage.show();

        //Ventana_Administrados
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Ventana_Administrador.fxml"));
        //Scene scene2 = new Scene(fxmlLoader.load(), 470, 410);
        //stage.setTitle("Administrador");
        //stage.setScene(scene2);
        //stage.show();
//
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Ventana_Cajero.fxml"));
//        Scene scene2 = new Scene(fxmlLoader.load(), 650, 450);
//        stage.setTitle("Creando Usuario");
//        stage.setScene(scene2);
//        stage.show();

//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Busqueda.fxml"));
//        Scene scene = new Scene(fxmlLoader.load());
//        stage.setTitle("Busqueda");
//        stage.setScene(scene);
//        stage.show();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Agregar.fxml"));
        Scene scene2 = new Scene(fxmlLoader.load());
        stage.setTitle("Creando Usuario");
        stage.setScene(scene2);
        stage.show();
    }



    public static void main(String[] args) {launch();}


}