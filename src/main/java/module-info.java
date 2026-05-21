module com.remonado.numbermatch {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.remonado.numbermatch to javafx.fxml;
    exports com.remonado.numbermatch;
}