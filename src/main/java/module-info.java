module com.example.projet_rooaar_2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.projet_rooaar_2 to javafx.fxml;
    exports com.example.projet_rooaar_2;
}