package com.example.projet_rooaar_2.Controller;

import com.example.projet_rooaar_2.Context;
import com.example.projet_rooaar_2.DatabaseConnection;
import com.example.projet_rooaar_2.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;

public class ConnectionController {


    @FXML
    private Label logiInfoLabel;

    @FXML
    private TextField mailTxtField;

    @FXML
    private PasswordField passwordTxtField;

    @FXML
    void logIn(ActionEvent event) {
        String mail = mailTxtField.getText();
        String password = passwordTxtField.getText();

        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection = dbConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = "select count(1) from users where email = '" + mail + "' and password = '" + password + "'";
            ResultSet resultSet = statement.executeQuery(query);
            while(resultSet.next()){
                if(resultSet.getInt(1) == 1) { // connection was successful
                    Context.user = DatabaseConnection.getUser(mail);
                    NavBarController.homepage();
                }
                else {  // connection failed
                    logiInfoLabel.setText("Identifiants incorrects !");
                }
            }
            dbConnection.closeConnection();
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    @FXML
    void signIn(ActionEvent event) throws IOException {
        HelloApplication.changeScene("View/inscription.fxml");
    }

}

