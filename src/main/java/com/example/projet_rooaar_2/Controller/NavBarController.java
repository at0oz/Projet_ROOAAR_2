package com.example.projet_rooaar_2.Controller;

import javafx.fxml.FXML;

import java.io.IOException;

import static com.example.projet_rooaar_2.HelloApplication.changeScene;

public class NavBarController {
    @FXML
    public static void connectionPage() throws IOException {
        changeScene("View/connection.fxml");
    }

    @FXML
    public static void homepage() throws IOException {
        changeScene("View/dinoShop.fxml");
    }

    public static void profile() throws IOException {
        changeScene("View/profile.fxml");
    }
}
