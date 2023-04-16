package com.example.projet_rooaar_2.Controller;

import com.example.projet_rooaar_2.DatabaseConnection;
import com.example.projet_rooaar_2.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.SQLException;

public class SignInController {

    @FXML
    private TextField addressTxtField;

    @FXML
    private CheckBox condtonCheckBox;

    @FXML
    private TextField emailTxtField;

    @FXML
    private TextField firstNameTxtField;

    @FXML
    private TextField lastNameTxtField;

    @FXML
    private PasswordField passwordTxtField;

    @FXML
    private Button signInButton;

    @FXML
    void signIn(ActionEvent event) throws SQLException, IOException {
        String lastName = lastNameTxtField.getText();
        String firstName = firstNameTxtField.getText();
        String email = emailTxtField.getText();
        String password = passwordTxtField.getText();
        String address = addressTxtField.getText();
        User newUser = new User(lastName, firstName, email, password, address);
        if (condtonCheckBox.isSelected() && newUser.isUnique()) {
            DatabaseConnection.addUser(newUser);
            NavBarController.connectionPage();
        }
    }

    @FXML
    void logIn() throws IOException {
        NavBarController.connectionPage();
    }
}
