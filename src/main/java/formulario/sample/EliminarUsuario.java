package formulario.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.DriverManager;
import java.sql.Statement;

public class EliminarUsuario extends HelloController{
    @FXML
    private Button Eliminar;
    @FXML
    private TextField Eli_nick;
    @FXML
    private TextField Eli_codigo;
    String SQL;
    String SQL_Aviso;
    public void Eliminar(ActionEvent actionEvent) {
        conectar();

        if (Eli_nick.getText().length()!=0){
            String pr = Eli_nick.getText().toUpperCase();
            SQL = "DELETE from lista_usuarios WHERE nick = '"+pr+"'";
            SQL_Aviso = "con Nick: " + pr;
        }else{
            String pr = Eli_codigo.getText();
            SQL = "DELETE from lista_usuarios WHERE documento = '"+pr+"'";
            SQL_Aviso = "con documento:  " + pr;
        }
        try {
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios", "root", "Luna9508");
            ps=con.prepareStatement(SQL);
            ps.executeUpdate();
            Alert mensaje = new Alert(Alert.AlertType.CONFIRMATION);
            mensaje.setTitle("Ventana Advertencia");
            mensaje.setContentText("Se ha ELIMINADO los datos del Usuario "+SQL_Aviso);
            mensaje.showAndWait();
            Eli_codigo.setText("");
            Eli_nick.setText("");

        } catch (Exception e) {
            Alert mensaje = new Alert(Alert.AlertType.ERROR);
            mensaje.setTitle("Advertencia");
            mensaje.setContentText("No existe el usuario "+SQL_Aviso);
            mensaje.showAndWait();
            Eli_codigo.setText("");
            Eli_nick.setText("");
            System.out.println(e);
        }
    }
}
