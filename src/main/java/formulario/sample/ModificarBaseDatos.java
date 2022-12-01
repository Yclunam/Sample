package formulario.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

/**
 * La clase Modificar_BaseDatos se implemento para modificar las existencias de productos en mysql.
 * se puede aumentar existencias.
 * crear nuevo producto.
 * y eliminar un producto.
 */
public class ModificarBaseDatos extends HelloController{
    @FXML
    private Button Buscar_producto;
    @FXML
    private Button Eliminar;
    @FXML
    private Button Modificar_Base;
    @FXML
    private Button Btn_Agregar;
    @FXML
    private TextField Agre_codigo;
    @FXML
    private TextField Agre_nombre;
    @FXML
    private TextField Agre_precio;
    @FXML
    private TextField Agre_cantidad;
    Connection con;
    String SQL;

    public void Btn_Agregar(ActionEvent actionEvent) throws SQLException {
        /**
         * Este metodo crea un nuevo producto en la base de datos, toma la informacion de las casillas correspondientes y verifica
         * que no exista un producto con el codigo ingresado, si ya el codigo esta ocupado, envia un mensaje de advertencia y queda
         * a la espera que se le de otro codigo.
         */
        PreparedStatement ps;
        String sql;
        conectar();
        sql = "insert into productos(codigo, nombre, cantidad, precio) values(?,?,?,?)";
        con= DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios", "root", "Luna9508");
        String pr = Agre_codigo.getText();
        SQL = "select * from productos where codigo = '"+pr+"'";
        try {
            Statement st = con.createStatement();
            rs = st.executeQuery(SQL);
            if (rs.next()) {
                Alert mensaje = new Alert(Alert.AlertType.ERROR);
                mensaje.setTitle("Ventana Advertencia");
                mensaje.setContentText("El codigo ya le pertenece al producto: "+rs.getString("nombre"));
                mensaje.showAndWait();
                Agre_codigo.setText("");
            }
            else {
                ps = con.prepareStatement(sql);
                ps.setInt(1, Integer.parseInt(Agre_codigo.getText()));
                ps.setString(2, Agre_nombre.getText().toUpperCase());
                ps.setInt(3, Integer.parseInt(Agre_cantidad.getText().toUpperCase())) ;
                ps.setInt(4, Integer.parseInt(Agre_precio.getText())) ;
                ps.executeUpdate();
                System.out.println("Se han insertado los datos");
                Agre_codigo.setText("");
                Agre_nombre.setText("");
                Agre_precio.setText("");
                Agre_cantidad.setText("");
                Alert mensaje = new Alert(Alert.AlertType.CONFIRMATION);
                mensaje.setTitle("Ventana Confirmacion");
                mensaje.setContentText("El nuevo producto ha sido creado y agregado a la base de datos");
                mensaje.showAndWait();
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public void Modificar_Base(ActionEvent actionEvent) throws IOException {
        /**
         * Este metodo es mas un llamado a otra ventanta donde la implementacion del metodo ya esta hecha.
         */
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Agregar_Admin.fxml"));
        Parent root =fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void Eliminar(ActionEvent actionEvent) throws IOException {
        /**
         * Este metodo es mas un llamado a otra ventanta donde la implementacion del metodo ya esta hecha.
         */
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Eliminar.fxml"));
        Parent root =fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    public void Buscar_producto(ActionEvent actionEvent) throws IOException {
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
}
