package formulario.sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.sql.*;

public class Busqueda {
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
    PreparedStatement ps;
    Connection con;
    ResultSet resultSet;
    ResultSet rs;
    String SQL;
    public void buscar(ActionEvent actionEvent) {
        conectar();
        if (nombreproducto.getText().length()!=0){
            String pr = nombreproducto.getText().toUpperCase();
            SQL = "select * from productos where nombre = '"+pr+"'";
            nombreproducto.setText("");
        }else{
            String pr = codigoproducto.getText();
            SQL = "select * from productos where codigo = '"+pr+"'";
            codigoproducto.setText("");
        }
        try{
            Statement st = con.createStatement();
            rs = st.executeQuery(SQL);
            while(rs.next()){
                Bcodigo.setText(rs.getString("codigo"));
                Bnombre.setText(rs.getString("nombre"));
                Bprecio.setText(rs.getString("precio"));
                Bunidades.setText(rs.getString("cantidad"));

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
    public void conectar(){
        try {
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/usuarios", "root", "Luna9508");

            System.out.println("Conectado");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
