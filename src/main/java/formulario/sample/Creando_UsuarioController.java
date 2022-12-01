package formulario.sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.ResourceBundle;

/**
 * Controla la ventana de crear usuario.
 * realiza la conexion y agrega la informacion a la base de datos.
 */
public class Creando_UsuarioController extends HelloController implements Initializable {
    @FXML
    private ComboBox comboBoxRol;

    @FXML
    private TextField Nick;
    @FXML
    private TextField password;

    @FXML
    private TextField documento;

    PreparedStatement ps;
    Connection con;
    ResultSet resultSet;
    public void Pruebalogin(ActionEvent actionEvent)  {

        try{
            conectar();
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios", "root", "Luna9508");
            //Revisando Nick
            boolean supervisor = false;
            ps = con.prepareStatement("select * from lista_usuarios");
            resultSet = ps.executeQuery("select * from lista_usuarios");
            while (resultSet.next()&&supervisor==false) {
                    supervisor=(revision(Nick.getText(),resultSet.getString("nick")));
                }
            if (supervisor==false){
                agregarusuario();
            }else{
                Nick.setText("");
                Alert mensaje = new Alert(Alert.AlertType.ERROR);
                mensaje.setTitle("Ventana Error");
                mensaje.setContentText("Este Nick ya esta en uso, por favor ingresa uno nuevo");
                mensaje.showAndWait();
            }

            //Mostrando base de datos actualizada
            resultSet = ps.executeQuery("select * from lista_usuarios");
            while (resultSet.next()) {
                    System.out.println(resultSet.getString("nick"));
                }

        }catch(SQLException e){
            System.out.println("Error de conexión:" + e.getMessage());
        }
        /**
         * Este boton realiza la comprobacion de las reglas propuestas para crear un nuevo usuario.
         * si se cumplen las condiciones, se hace la creacion de usuario en la base de datos.
         * se muestra en consola los usuarios existentes.
         */
    }

    private boolean revision(String x, String z) {
        String old= z.replaceAll(" ","").toUpperCase();
        String nuevo = x.replaceAll(" ","").toUpperCase();
        boolean supervisor = revisarnumero(nuevo);

        if (supervisor == false) {

                if (nuevo.equals(old)) {
                    supervisor = true;
                }
        }
        return supervisor;
        /**
         * Esta funcion revisa el que los String ingresados no sean iguales.
         * @param x una variable tipo String.
         * @param z una variable tipo String.
         * @return Devuelve un boolean supervisor(false si no son iguales y true si lo son).
         */
    }

    private boolean revisarnumero(String x) {

        boolean aprueba = false;
        char[] arreglo = x.toCharArray();
        String r = String.valueOf(arreglo[0]);
        if (aprueba = (r != null && r.matches("[0-9]+"))) {
            aprueba = true;
        }
        return aprueba;
        /**
         * Esta funcion revisa si el String ingresado no comienza con numeros.
         * @param x una variable tipo String.
         * @return Devuelve un boolean supervisor(false-no contiene numeros, true-si contiene numeros).
         */
    }
    public void agregarusuario() throws SQLException {
        //Agregando usuario a la base de datos
        PreparedStatement ps;
        String sql;
        conectar();
        sql = "insert into lista_usuarios(documento, rol, nick, password) values(?,?,?,?)";
        ps = con.prepareStatement(sql);
        ps.setInt(1, Integer.parseInt(documento.getText()));
        ps.setString(2, comboBoxRol.getValue().toString().toUpperCase());
        ps.setString(3, Nick.getText().toUpperCase());
        ps.setString(4, password.getText());
        ps.executeUpdate();
        System.out.println("Se han insertado los datos");
        documento.setText("");
        Nick.setText("");
        password.setText("");
        Alert mensaje = new Alert(Alert.AlertType.CONFIRMATION);
        mensaje.setTitle("Ventana Confirmacion");
        mensaje.setContentText("El usuario ha sido creado y agregado a la base de datos");
        mensaje.showAndWait();
        /**
         * Esta funcion agrega al nuevo usuario a la base de datos de mysql.
         * da un mensaje de confirmacion.
         */
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        comboBoxRol.getItems().add("Administrador");
        comboBoxRol.getItems().add("Cajero");
        /**
         * Se inician los valores de comboBox.
         */
    }
}


