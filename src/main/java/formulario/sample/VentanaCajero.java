package formulario.sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.*;

public class VentanaCajero  {
    @FXML
    private Button Btn_Calcular;
    @FXML
    private Button Btn_Inventario;
    @FXML
    private Button Btn_Confirmar;
    @FXML
    private Button Eliminar;
    @FXML
    private TextField Modificar_cantidad;
    @FXML
    private TextField Modificar_codigo;
    @FXML
    private TableView<Compra> Table_Factura;
    @FXML
    private TableColumn<Compra,String> Product_ID ;
    @FXML
    private TableColumn<Compra,String> Product_Name;
    @FXML
    private TableColumn<Compra, Integer> Product_Cantidad;
    @FXML
    private TableColumn<Compra,Integer> Product_Unitario;
    @FXML
    private TableColumn<Compra,Integer> Product_Total;
    @FXML
    private TableColumn<Compra,Integer> Product_Disponible;
    @FXML
    private Button Ca_agre_inventario;
    @FXML
    private TextField Ca_Eliminar;
    @FXML
    private Button Ca_modificar;
    @FXML
    private TextField Total;

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
    ObservableList<Compra> Lista = FXCollections.observableArrayList();

//    DefaultTableModel model = (DefaultTableModel) Table_Factura.getModel()
    public void Btn_Inventario(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Agregar.fxml"));
        Parent root =fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
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
            while(rs.next()){
                int total = Integer.parseInt(Ca_cantidad.getText())*Integer.parseInt(rs.getString("precio"));
                 Lista.add(
                        new Compra(codigo,
                                rs.getString("nombre"),
                                Integer.parseInt(Ca_cantidad.getText()),
                                Integer.parseInt(rs.getString("precio")),
                                total,
                                Integer.parseInt(rs.getString("cantidad")))
                 );
                System.out.println("Cantidad de elementos en la lista:" + Lista.size());
                Product_ID.setCellValueFactory(new PropertyValueFactory<Compra,String>("ID"));
                Product_Name.setCellValueFactory(new PropertyValueFactory<Compra,String>("Name"));
                Product_Cantidad.setCellValueFactory(new PropertyValueFactory<Compra,Integer>("Cantidad"));
                Product_Unitario.setCellValueFactory(new PropertyValueFactory<Compra,Integer>("Valor_Unit"));
                Product_Total.setCellValueFactory(new PropertyValueFactory<Compra,Integer>("Valor_Total"));
                Product_Disponible.setCellValueFactory(new PropertyValueFactory<Compra,Integer>("Disponibles"));
                Table_Factura.setItems(Lista);
                Calcular();
                Ca_codigo.setText("");
                Ca_cantidad.setText("");

            }
}catch (SQLException e) {
            System.out.println("El error es: "+ e);
        }
}

    public void Ca_modificar(ActionEvent actionEvent) {
        int index = Table_Factura.getSelectionModel().getSelectedIndex();
        Compra elemento = Table_Factura.getSelectionModel().getSelectedItem();
        elemento.setCantidad(Integer.parseInt(Modificar_cantidad.getText()));
        elemento.setValor_Total(elemento.getValor_Unit()*elemento.getCantidad());
        Lista.set(index,elemento);
        Calcular();
        Modificar_cantidad.setText("");
    }


    public void Eliminar(ActionEvent actionEvent) {
        int index = Table_Factura.getSelectionModel().getSelectedIndex();
        Lista.remove(index);
        Calcular();
    }


    public void Btn_Confirmar(ActionEvent actionEvent) throws SQLException {
        for (int i=0;i<Lista.size();i++){

            modificar_base_datos(
                    Lista.get(i).getName(),
                    Integer.toString(Lista.get(i).getDisponibles()-Lista.get(i).getCantidad()),
                    Integer.toString(Lista.get(i).getValor_Unit()),
                    Lista.get(i).getID()
            );
        }
        Total.setText("");
    }

    public  void modificar_base_datos (String nombre,String cantidad,String precio,String codigo) throws SQLException {

        conectar();
        String SQL = "UPDATE productos SET nombre = ?,cantidad=?,precio=? WHERE codigo = ?";
        ps=con.prepareStatement(SQL);
        ps.setString(1,nombre);
        ps.setInt(2,Integer.parseInt(cantidad));
        ps.setInt(3,Integer.parseInt(precio));
        ps.setInt(4,Integer.parseInt(codigo));
        try {
            ps.executeUpdate();
            Alert mensaje = new Alert(Alert.AlertType.CONFIRMATION);
            mensaje.setTitle("Ventana Advertencia");
            mensaje.setContentText("Se actualizaron las existencias.");
            mensaje.showAndWait();

        } catch (Exception e) {System.out.println("Error: "+e);}
        Lista.clear();
    }


    public void Calcular() {
        int cuenta =0;
        for (int i=0;i<Lista.size();i++){
            cuenta +=Lista.get(i).getValor_Total();
        }
        Total.setText("$ "+Integer.toString(cuenta));
    }
}
