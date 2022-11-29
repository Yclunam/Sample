package formulario.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.*;

/**
 * La clase busqueda controla la ventana busqueda la cual nos ayuda a consultar en la base de datos
 * la informacion de un producto segun su codigo o nombre.
 */
public class Busqueda extends HelloController{
    @FXML
    private TextField Bcodigo;
    @FXML
    private TextField Bnombre;
    @FXML
    private TextField Bprecio;
    @FXML
    private TextField Bunidades;
    @FXML
    private TextField nombreproducto;
    @FXML
    private TextField codigoproducto;
    ResultSet rs;
    String SQL;
    String SQL_Aviso;
    public void buscar(ActionEvent actionEvent) {
        conectar();
        if (nombreproducto.getText().length()!=0){
            String pr = nombreproducto.getText().toUpperCase();
            SQL_Aviso = "Con nombre: "+pr;
            SQL = "select * from productos where nombre = '"+pr+"'";
            nombreproducto.setText("");
        }else{
            String pr = codigoproducto.getText();
            SQL_Aviso = "Con codigo: "+pr;
            SQL = "select * from productos where codigo = '"+pr+"'";
            codigoproducto.setText("");
        }
        try{
            Statement st = con.createStatement();
            rs = st.executeQuery(SQL);
            if(rs.next()){
                Bcodigo.setText(rs.getString("codigo"));
                Bnombre.setText(rs.getString("nombre"));
                Bprecio.setText("$ "+rs.getString("precio"));
                Bunidades.setText(rs.getString("cantidad"));

            }else {
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION);
                mensaje.setTitle("Advertencia");
                mensaje.setContentText("no existe el producto: "+SQL_Aviso);
                mensaje.showAndWait();
                nombreproducto.setText("");
                codigoproducto.setText("");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        /**
         * Se detecta si el campo que se ingreso es codigo o nombre y despues se hace la busqueda
         * en la base de datos con esa informacion. Se llenan las casillas con la informacion obtenida de la base de datos.
         * de no existir un producto con ese nombre o codigo no se llenan las casillas.
         */
    }

}
