package formulario.sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Clase que nos ayuda a editar las opciones de tipo de usuario en la ventana de creacion de estos.
 */

public class comboBox extends Application {

    Stage window;
    Scene scene;
    Button button;
    ComboBox comboBox;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("ComboBox");
        button = new Button("Submit");

        comboBox = new ComboBox<>();
        comboBox.getItems().addAll(
                "Cajero",
                "Administrador"
        );

        comboBox.setPromptText("Please select one");
        button.setOnAction(e -> printPrice());

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(60, 60, 60, 60));
        layout.getChildren().addAll(comboBox, button);

        scene = new Scene(layout, 450, 350);
        window.setScene(scene);
        window.show();
    }

    private void printPrice(){
        System.out.println(comboBox.getValue());
    }
}
