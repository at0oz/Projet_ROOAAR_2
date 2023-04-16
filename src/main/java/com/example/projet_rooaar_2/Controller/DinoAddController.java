package com.example.projet_rooaar_2.Controller;

import com.example.demo.Context;
import com.example.demo.DatabaseConnection;
import com.example.demo.Dinosaure;
import com.example.demo.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import static com.example.demo.DatabaseConnection.getDino;

public class DinoAddController {

    @FXML
    private Button addButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Button connectionButton;

    @FXML
    private ChoiceBox<String> dietChoiceBox;

    @FXML
    private Label dinoFamilyNameLb;

    @FXML
    private ImageView dinoImageView;

    @FXML
    private TextField familyIdTxtField;

    @FXML
    private RadioButton genderRdButtonF;

    @FXML
    private RadioButton genderRdButtonM;

    @FXML
    private Button homeButton;

    @FXML
    private TextField idTxtField;

    @FXML
    private Button imageImportButton;

    @FXML
    private TextField priceTxtField;

    @FXML
    private TextField sizeTxtField;

    @FXML
    private TextField stockTxtField;

    @FXML
    private ChoiceBox<Integer> familyIdChoiceBox;

    @FXML
    void addDino(ActionEvent event) throws SQLException, IOException {
        int dinoId = Integer.parseInt(idTxtField.getText());
        int dinoSize = Integer.parseInt(sizeTxtField.getText());
        RadioButton selectedGenderRdButton = (RadioButton) group.getSelectedToggle();
        String dinoGender = selectedGenderRdButton.getText();
        String dinoDiet = dietChoiceBox.getValue();
        int familyId = familyIdChoiceBox.getValue();
        int dinoStock = Integer.parseInt(stockTxtField.getText());

        if (DatabaseConnection.getDino(dinoId) == null) { // id is unique
            DatabaseConnection.addDino(
                    new Dinosaure(dinoId, dinoSize, dinoGender, dinoDiet, dinoImagePath, familyId, dinoStock));
        }
        Float dinoPrice = Float.parseFloat(priceTxtField.getText());
        DatabaseConnection.addDinoPrice(dinoId, dinoPrice);
        NavBarController.homepage();
    }

    @FXML
    void connectionPage(ActionEvent event) throws IOException {
        NavBarController.connectionPage();
    }

    @FXML
    void homepage(ActionEvent event) throws IOException {
        NavBarController.homepage();
    }

    @FXML
    void importImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        File selectedFile = fileChooser.showOpenDialog(HelloApplication.getStage());
        if (selectedFile != null) {
            Path currentDir = Paths.get("").toAbsolutePath();
            dinoImagePath = currentDir.relativize(selectedFile.toPath().toAbsolutePath()).toString();
            Image image = new Image(selectedFile.toURI().toString());

            dinoImageView.setImage(image);
        }
    }

    private ToggleGroup group = new ToggleGroup();

    @FXML
    private void initialize() throws SQLException {
        genderRdButtonM.setToggleGroup(group);
        genderRdButtonF .setToggleGroup(group);

        ObservableList<String> dietList = FXCollections.observableArrayList("C", "H");
        dietChoiceBox.setItems(dietList);
        familyIdChoiceBox.setItems(DatabaseConnection.getFamiliesId());
    }


    private String dinoImagePath = null;

}
