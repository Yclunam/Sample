package formulario.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VentanaCajero {
    @FXML
    private Button Ca_agre_inventario;
    @FXML
    private TextField Ca_Eliminar;
    @FXML
    private Button Ca_modificar;
    @FXML
    private TextField Total;
    @FXML
    private Button Btn_Calcular;
    @FXML
    private ListView Ventana_Calculadora;
    @FXML
    private TextField Ca_codigo;
    @FXML
    private TextField Ca_cantidad;
    @FXML
    public Button Ca_Btn_agregar;
    PreparedStatement ps;
    Connection con;
    ResultSet resultSet;
    ResultSet rs;
    List <Integer> lista = new ArrayList<>();

    public void Boton_busqueda(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Busqueda.fxml"));
        Parent root =fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    public void conectar(){
        try {
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios", "root", "Luna9508");
            System.out.println("Conectado");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void Ca_Btn_agregar(ActionEvent actionEvent) {
        conectar();

        String codigo =Ca_codigo.getText();
        String SQL = "select * from productos where codigo = '"+codigo+"'";
        try {
            Statement st = con.createStatement();
            rs = st.executeQuery(SQL);
            if(rs.next()){
                String mensaje = "Codigo: "+rs.getString("codigo")+" / Nombre: "+rs.getString("nombre")+" / Cantidad Venta: "+Ca_cantidad.getText()+" / Precio UND:  "+rs.getString("precio");
                Ventana_Calculadora.getItems().add(mensaje);
                lista.add(Integer.parseInt(Ca_cantidad.getText())*Integer.parseInt(rs.getString("precio")));

            }else{
                Alert mensaje = new Alert(Alert.AlertType.ERROR);
                mensaje.setTitle("Ventana Advertencia");
                mensaje.setContentText("NO TENEMOS NINGUN PRODUCTO CON ESTE CODIGO: "+Ca_codigo.getText());
                mensaje.showAndWait();}
            System.out.println(lista);
            Ca_codigo.setText("");
            Ca_cantidad.setText("");
        } catch (SQLException e) {
            System.out.println("El error es: "+ e);
        }


    }

    public void Btn_Calcular(ActionEvent actionEvent) {
        int cuenta = lista.stream().mapToInt(Integer::intValue).sum();
        Total.setText(Integer.toString(cuenta));
    }

    public void Ca_modificar(ActionEvent actionEvent) {
        lista.remove(Integer.parseInt(Ca_Eliminar.getText()));
    }

    public void Ca_agre_inventario(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Agregar.fxml"));
        Parent root =fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
