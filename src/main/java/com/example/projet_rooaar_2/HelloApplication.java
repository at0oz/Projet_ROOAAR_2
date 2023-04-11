package com.example.projet_rooaar_2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader acceuil = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));

        Scene scene1 = new Scene(acceuil.load());

        stage.setTitle("ROOAAR");
        stage.setScene(scene1);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}