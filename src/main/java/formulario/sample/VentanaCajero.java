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
    ResultSet rs;
    ObservableList<Compra> Lista = FXCollections.observableArrayList();

    public void Btn_Inventario(ActionEvent actionEvent) throws IOException {
        /**
         * Este metodo es mas un llamado a otra ventanta donde la implementacion del metodo ya esta hecha.
         */
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Agregar.fxml"));
        Parent root =fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    public void Boton_busqueda(ActionEvent actionEvent) throws IOException {
        /**
         * Este metodo es mas un llamado a otra ventanta donde la implementacion del metodo ya esta hecha.
         */
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Busqueda.fxml"));
        Parent root =fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    public void conectar(){
        /**
         * Conecta con la base de datos de mysql.
         */
        try {
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios", "root", "Luna9508");
            System.out.println("Conectado");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void Ca_Btn_agregar(ActionEvent actionEvent) {
        /**
         * Con este metodo se logra la busqueda de un producto segun su codigo el cual se ingresa en la casilla correspomdiente.
         * de igual forma una cantidad es pedida y con estos datos agregamos a la lista los datos del producto y el precio a pagar
         * por la cantidad solicitada en la casilla.
         */
        conectar();
        String codigo =Ca_codigo.getText();
        String SQL = "select * from productos where codigo = '"+codigo+"'";
        try {
            Statement st = con.createStatement();
            rs = st.executeQuery(SQL);/*Se realiza el requerimiento a mysql para que los datos del producto se llene en la lista y hacer las respectivas cuentas*/
            while(rs.next()){
                int total = Integer.parseInt(Ca_cantidad.getText())*Integer.parseInt(rs.getString("precio"));
                 Lista.add(
                        new Compra(codigo,
                                rs.getString("nombre"),
                                Integer.parseInt(Ca_cantidad.getText()),
                                Integer.parseInt(rs.getString("precio")),
                                total,
                                Integer.parseInt(rs.getString("cantidad")))
                 );/*en la lineas anteriores se agrega a una lista observable un objeto de la clase compra y asi se va agregando a la lista de compra*/
                Product_ID.setCellValueFactory(new PropertyValueFactory<Compra,String>("ID"));
                Product_Name.setCellValueFactory(new PropertyValueFactory<Compra,String>("Name"));
                Product_Cantidad.setCellValueFactory(new PropertyValueFactory<Compra,Integer>("Cantidad"));
                Product_Unitario.setCellValueFactory(new PropertyValueFactory<Compra,Integer>("Valor_Unit"));
                Product_Total.setCellValueFactory(new PropertyValueFactory<Compra,Integer>("Valor_Total"));
                Product_Disponible.setCellValueFactory(new PropertyValueFactory<Compra,Integer>("Disponibles"));
                Table_Factura.setItems(Lista);
                /*se llena las casillas de la lista con la informacion de los prodcutos*/
                Calcular();/*este metodo nos sirve para llevar la cuenta total de los productos que se van agregando a la lista de compras*/
                Ca_codigo.setText("");
                Ca_cantidad.setText("");

            }
}catch (SQLException e) {
            System.out.println("El error es: "+ e);
        }
}

    public void Ca_modificar(ActionEvent actionEvent) {
        /**
         * En este metodo se implementa la modificacion de un item de la lista de compras.
         * al seleccionar el item con el mouse de la lista de compras se cambia el valor de la cantidad solicitada.
         * el valor por el cual se remplaza es el de la casilla Modificar_cantidad.
         * y final mente se vuelve hacer la cuenta del total de la lista de compras.
         */
        int index = Table_Factura.getSelectionModel().getSelectedIndex();
        Compra elemento = Table_Factura.getSelectionModel().getSelectedItem();
        elemento.setCantidad(Integer.parseInt(Modificar_cantidad.getText()));
        elemento.setValor_Total(elemento.getValor_Unit()*elemento.getCantidad());
        Lista.set(index,elemento);
        Calcular();
        Modificar_cantidad.setText("");
    }


    public void Eliminar(ActionEvent actionEvent) {
        /**
         * Este metodo elimina un item seleccionado de la lista de compras.
         * y vuelve hacer el total de los elementos restantes en la lista de compras.
         */
        int index = Table_Factura.getSelectionModel().getSelectedIndex();
        Lista.remove(index);
        Calcular();
    }


    public void Btn_Confirmar(ActionEvent actionEvent) throws SQLException {
        /**
         * El metodo esta elaborado para realizare el requerimiento para modificar la base de datos
         * la cual se debe restar la cantidad disponible de producto con la cantidad comprada.
         */
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
        /**
         * Se realiza el requerimiento a mysql para que se actualice de la base de datos la cantidad disponible del producto comprado.
         */

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
        /**
         * hace la cuenta de los productos en la lista de compras.
         */
        int cuenta =0;
        for (int i=0;i<Lista.size();i++){
            cuenta +=Lista.get(i).getValor_Total();
        }
        Total.setText("$ "+Integer.toString(cuenta));
    }
}
