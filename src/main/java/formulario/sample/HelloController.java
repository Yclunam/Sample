package formulario.sample;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;


public class HelloController {

    @FXML
    private Label welcomeText;
    @FXML
    private TextField nombre;
    @FXML
    private TextField contra;

    PreparedStatement ps;
    Connection con;
    ResultSet rs;
    ResultSet rs2;
    String ventana;
    Stage stage;
    @FXML
    protected void onHelloButtonClick() {
        validarUsuario();
    }
    public  void conectar(){
        try {
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios", "root", "Luna9508");

            System.out.println("Conectado");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }
    public void validarUsuario(){
        conectar();
        int resultado = 0;
        String pass = contra.getText();
        String usuario = nombre.getText().toUpperCase();
        String SQL = "select * from lista_usuarios where nick = '"+usuario+"'and password ='"+pass+"'";

        try {
            Statement st = con.createStatement();
            rs = st.executeQuery(SQL);
            String rol = "select * from lista_usuarios where nick = '"+usuario+"'";
            if (rs.next()){
                resultado = 1;
                if (resultado==1){
                    System.out.println("Estas Dentro");
                    Alert mensaje = new Alert(Alert.AlertType.CONFIRMATION);
                    mensaje.setTitle("Ventana Confirmacion");
                    mensaje.setContentText("BIENVENIDO");
                    mensaje.showAndWait();
                    rs2 = st.executeQuery(rol);
                    while(rs2.next()){
                        ventana=(rs2.getString("rol").replace(" ","").toUpperCase());
                    }
                    if (ventana.equals("ADMINISTRADOR")){
                        System.out.println("Admin");
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Ventana_Administrador.fxml"));
                        Parent root =fxmlLoader.load();
                        VentanaAdministrador controlador = fxmlLoader.getController();
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();

                    }else{
                        System.out.println("Cajero");
                        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Ventana_Cajero.fxml"));
                        Parent root =fxmlLoader.load();
                        VentanaCajero controlador = fxmlLoader.getController();
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();
                    }

                    }

                }else {
                Alert mensaje = new Alert(Alert.AlertType.CONFIRMATION);
                mensaje.setTitle("Ventana Confirmacion");
                mensaje.setContentText("TU USUARIO O CONTRASEÑA SON INCORRECTOS");
                mensaje.showAndWait();
                System.out.println("Error de registro");
            }
            } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}