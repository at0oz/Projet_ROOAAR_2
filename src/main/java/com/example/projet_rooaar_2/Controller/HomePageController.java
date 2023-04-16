package com.example.projet_rooaar_2.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class HomePageController {
    @FXML
    private Button okButton;

    public void print(ActionEvent event) throws IOException {
        System.out.println("Ok");
    }
}
