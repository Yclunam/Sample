package formulario.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


import java.sql.*;

/**
 * Esta clase controla la ventana de agregar, su funcion principal es modificar los datos de los productos en la base de datos,
 * valga aclarar que algunas funciones estan deshabilitadas para el usuario cajero, pero en esta clase no se hace, si no que desde la ventana.
 */
public class Agregar extends HelloController {
    /**
    *se inician los campos de texto y botenes que se utilizaran para obtener informacion o ejecutar una funcion.

     */
    @FXML
    private TextField Cantidad_Agregar;
    @FXML
    private Button Agre_consulta;
    @FXML
    private Button Btn_Agregar;
    @FXML
    private TextField Agre_codigo;
    @FXML
    private TextField Agre_nombre;
    @FXML
    private TextField Agre_precio;
    @FXML
    private TextField Agre_unidades;

    /** librerias necesarias para la conexion con MySQL tambien se debe instalar un modulo*/
    PreparedStatement ps;
    ResultSet rs;


    public void Btn_Agregar(ActionEvent actionEvent) throws SQLException {
        conectar();
        String nombre = Agre_nombre.getText().toUpperCase();
        int cantidadAgregar = Integer.parseInt(Cantidad_Agregar.getText());
        if(cantidadAgregar <0){
            Alert mensaje = new Alert(Alert.AlertType.ERROR);
            mensaje.setTitle("Ventana Advertencia");
            mensaje.setContentText("No puedes descontar porductos desde esta ventana. ");
            mensaje.showAndWait();
            cantidadAgregar=0;
        }
        /**
         * Este condicional ayuda a que no se disminuyan las existencias ya que estas solo se deben disminuir cuando se realice una compra.
         */
        int cantidad = Integer.parseInt(Agre_unidades.getText().toUpperCase())+cantidadAgregar;
        String precio = Agre_precio.getText().toUpperCase();
        String codigo = Agre_codigo.getText().toUpperCase();
        String SQL = "UPDATE productos SET nombre = ?,cantidad=?,precio=? WHERE codigo = ?";
        ps=con.prepareStatement(SQL);
        ps.setString(1,nombre);
        ps.setInt(2,cantidad);
        ps.setInt(3,Integer.parseInt(precio));
        ps.setInt(4,Integer.parseInt(codigo));
        try {
            ps.executeUpdate();
            Alert mensaje = new Alert(Alert.AlertType.CONFIRMATION);
            mensaje.setTitle("Ventana Advertencia");
            mensaje.setContentText("Se ha modificado los datos del producto con codigo: "+Agre_codigo.getText());
            mensaje.showAndWait();
            Agre_nombre.setText("");
            Agre_precio.setText("");
            Agre_unidades.setText("");
            Cantidad_Agregar.setText("");
            /**
             * En este try se actualiza la informacion y se da el aviso que los datos se han aplicado a la base de datos.
             */
        } catch (Exception e) {System.out.println("Error: "+e);}
        /**
         * Esta funcion aumenta la cantidad en el inventario del producto que se busco la base de datos en mysql.
         * Segun el numero ingresado en Cantidad_Agregar se actualiza el numero de existentes en la base de datos en mysql.
         * Tambien las casillas de nombre, precio y unidades se llenan automaticamente segun el codigo de producto ingresado en la casilla Agre_codigo.
         * @param cantidad numero de unidades que se desea sumar a las existentes.
         *
         * */
    }

    public void Agre_consulta(ActionEvent actionEvent) {
        conectar();
        String codigo =Agre_codigo.getText();
        String SQL = "select * from productos where codigo = '"+codigo+"'";
        try {
            Statement st = con.createStatement();
            rs = st.executeQuery(SQL);
            if(rs.next()){
                Agre_nombre.setText(rs.getString("nombre"));
                Agre_precio.setText(rs.getString("precio"));
                Agre_unidades.setText(rs.getString("cantidad"));
            }else {
                Agre_nombre.setText("NONE");
                Agre_precio.setText("NONE");
                Agre_unidades.setText("NONE");
                Alert mensaje = new Alert(Alert.AlertType.ERROR);
                mensaje.setTitle("Ventana Advertencia");
                mensaje.setContentText("NO TENEMOS NINGUN PRODUCTO CON ESTE CODIGO: "+Agre_codigo.getText());
                mensaje.showAndWait();}
        }catch (Exception e) {System.out.println("El error es: "+e);}
    }
    /**
     * El boton Agre_consulta sirve para que las casillas vacias se llenen segun los datos que correspondan al codigo ingresado.
     * Si no existe el codigo en la base de datos, se envia un mensaje de advertencia y se pone NONE en las casillas.
     */
}


