package formulario.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.*;

public class Agregar {
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
    public void Btn_Agregar(ActionEvent actionEvent) throws SQLException {
        conectar();
        String nombre = Agre_nombre.getText().toUpperCase();
        String cantidad = Agre_unidades.getText().toUpperCase();
        String precio = Agre_precio.getText().toUpperCase();
        String codigo = Agre_codigo.getText().toUpperCase();
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
            mensaje.setContentText("Se ha modificado los datos del producto con codigo: "+Agre_codigo.getText());
            mensaje.showAndWait();
            Agre_nombre.setText("");
            Agre_precio.setText("");
            Agre_unidades.setText("");


        } catch (Exception e) {System.out.println("Error: "+e);}
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
}


