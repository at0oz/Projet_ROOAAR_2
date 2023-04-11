package com.example.projet_rooaar_2;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    private Button Btn_Connection;

    @FXML
    private Button Btn_aller_Inscription;

    @FXML
    void Se_connecter(ActionEvent event) {

    }

    @FXML
    void aller_inscription(ActionEvent event) throws IOException {
        FXMLLoader inscriptionLoader = new FXMLLoader(getClass().getResource("inscription.fxml"));
        Parent inscriptionRoot = inscriptionLoader.load();
        Scene inscriptionScene = new Scene(inscriptionRoot);

        // Obtenez une référence à la scène actuelle
        Scene currentScene = ((Node) event.getSource()).getScene();

        // Obtenez la fenêtre actuelle
        Stage stage = (Stage) currentScene.getWindow();

        // Chargez la nouvelle scène dans la même fenêtre
        stage.setScene(inscriptionScene);
        stage.show();
    }

}