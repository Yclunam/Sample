package formulario.sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;

public class Creando_UsuarioController {
    @FXML
    private Label Confirmar;
    @FXML
    private TextField Nick;
    @FXML
    private TextField password;

    @FXML
    private TextField documento;

    @FXML
    private TextField rol;



    PreparedStatement ps;
    Connection con;
    ResultSet resultSet;

    String sql;
    Statement stmt;

    public void conectar(){
        try {
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios", "root", "Luna9508");

            System.out.println("Conectado");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public void Pruebalogin(ActionEvent actionEvent)  {
        PreparedStatement ps;
        String sql;

        try{
            conectar();

            sql = "insert into lista_usuarios(documento, rol, nick, password) values(?,?,?,?)";
            ps = con.prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(documento.getText()));
            ps.setString(2, rol.getText().toUpperCase());
            ps.setString(3, Nick.getText().toUpperCase());
            ps.setString(4, password.getText());
            ps.executeUpdate();
            System.out.println("Se han insertado los datos");
            documento.setText("");
            rol.setText("");
            Nick.setText("");
            password.setText("");
            Alert mensaje = new Alert(Alert.AlertType.CONFIRMATION);
            mensaje.setTitle("Ventana Confirmacion");
            mensaje.setContentText("El usuario ha sido creado y agregado a la base de datos");
            mensaje.showAndWait();
            resultSet = ps.executeQuery("select * from lista_usuarios");

            while (resultSet.next()) {
                System.out.println(resultSet.getString("nick"));
            }
        }catch(SQLException e){
            System.out.println("Error de conexión:" + e.getMessage());
        }
    }

    private boolean revision(String x, String z) {
        String old;
        String nuevo = x;
        String y = nuevo.replaceAll("\\s+", "");
        boolean supervisor = revisarnumero(y);

        if (supervisor == false) {
                old = z.toString().replaceAll("\\s+", "");
                if (y.equals(old)) {
                    supervisor = true;
                }

        }
        return supervisor;
    }

    private boolean revisarnumero(String x) {

        boolean aprueba = false;
        char[] arreglo = x.toCharArray();
        String r = String.valueOf(arreglo[0]);
        if (aprueba = (r != null && r.matches("[0-9]+"))) {
            aprueba = true;
        }
        return aprueba;
    }
}


