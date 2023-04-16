package com.example.projet_rooaar_2.Controller;

import com.example.demo.Context;
import com.example.demo.Controller.NavBarController;
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
import javafx.stage.Window;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import static com.example.demo.DatabaseConnection.getDino;

public class DinoEditController {

    @FXML
    private Label dinoFamilyNameLb;

    @FXML
    private ImageView dinoImageView;

    @FXML
    private Button connectionButton;

    @FXML
    private RadioButton genderRdButtonF;

    @FXML
    private RadioButton genderRdButtonM;

    @FXML
    private TextField idTxtField;

    @FXML
    private TextField sizeTxtField;

    @FXML
    private ChoiceBox<String> dietChoiceBox;

    @FXML
    private TextField priceTxtField;

    @FXML
    private TextField stockTxtField;

    @FXML
    private ChoiceBox<Integer> familyIdChoiceBox;

    @FXML
    void connectionPage(ActionEvent event) throws IOException {
        NavBarController.connectionPage();
    }

    @FXML
    void homepage(ActionEvent event) throws IOException {
        NavBarController.homepage();
    }


    @FXML
    private void importImage(ActionEvent event) {
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

    private String dinoImagePath;
    private ToggleGroup group = new ToggleGroup();

    @FXML
    public void initialize() throws SQLException {
        initializeConnectionButton();
        fillDinoData();
    }

    private void fillDinoData() throws SQLException {
        genderRdButtonM.setToggleGroup(group);
        genderRdButtonF .setToggleGroup(group);
        final ObservableList<String> dietList = FXCollections.observableArrayList("C", "H");
        dietChoiceBox.setItems(dietList);
        familyIdChoiceBox.setItems(DatabaseConnection.getFamiliesId());

        Dinosaure dinosaure = getDino(Context.dinoId);

        dinoFamilyNameLb.setText(dinosaure.getFamilyName());
        dinoImagePath = dinosaure.getImagePath();
        dinoImageView.setImage(dinosaure.getImage());

        idTxtField.setText(String.valueOf(dinosaure.getId()));
        sizeTxtField.setText(String.valueOf(dinosaure.getSize()));
        if (dinosaure.getGender().equals("M")){
            genderRdButtonM.setSelected(true);
        } else {
            genderRdButtonF.setSelected(true);
        }
        dietChoiceBox.setValue(dinosaure.getDiet());
        priceTxtField.setText(String.valueOf(dinosaure.getPrice()));
        familyIdChoiceBox.setValue(dinosaure.getFamilyId());
        stockTxtField.setText(String.valueOf(dinosaure.getStock()));
    }

    private void initializeConnectionButton() {
        if (Context.user != null) {
            connectionButton.setText("Se deconnecter");
            connectionButton.setOnAction(e -> {
                try {
                    disconnect();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
        } else {
            connectionButton.setText("Connection");
            connectionButton.setOnAction(e -> {
                try {
                    connectionPage(e);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });
        }
    }

    private void disconnect() throws IOException {
        Context.user = null;
        NavBarController.homepage();
    }

    @FXML
    private void saveDino() {
        try {
            int dinoId = Integer.parseInt(idTxtField.getText());
            int dinoSize = Integer.parseInt(sizeTxtField.getText());
            RadioButton selectedGenderRdButton = (RadioButton) group.getSelectedToggle();
            String dinoGender = selectedGenderRdButton.getText();
            String dinoDiet = dietChoiceBox.getValue();
            int familyId = familyIdChoiceBox.getValue();
            int dinoStock = Integer.parseInt(stockTxtField.getText());
            if ((dinoId != Context.dinoId // dino's id have been changed
                    && getDino(dinoId) == null) // and new id is unique
                    || dinoId == Context.dinoId) { // id didn't change
                DatabaseConnection.saveDino(new Dinosaure(dinoId, dinoSize, dinoGender, dinoDiet, dinoImagePath, familyId, dinoStock));
            }
            Float dinoPrice = Float.parseFloat(priceTxtField.getText());
            DatabaseConnection.updateDinoPrice(dinoPrice);
            initialize();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}