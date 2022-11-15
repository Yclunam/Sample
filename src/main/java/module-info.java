module formulario.sample {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens formulario.sample to javafx.fxml;
    exports formulario.sample;
}