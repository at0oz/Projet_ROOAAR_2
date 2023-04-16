package com.example.projet_rooaar_2.Controller;

import com.example.demo.Context;
import com.example.demo.DatabaseConnection;
import com.example.demo.Dinosaure;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.sql.SQLException;

import static com.example.demo.DatabaseConnection.getDino;

public class DinoPurchaseController {

    @FXML
    private Button cancelButton;

    @FXML
    private Button connectionButton;

    @FXML
    private Label dinoDietLb;

    @FXML
    private Label dinoFamilyIdLb;

    @FXML
    private Label dinoFamilyNameLb;

    @FXML
    private Label dinoGenderLb;

    @FXML
    private Label dinoIdLb;

    @FXML
    private ImageView dinoImageView;

    @FXML
    private Label dinoPriceLb;

    @FXML
    private Label dinoSizeLb;

    @FXML
    private Label dinoStockLb;

    @FXML
    private Button homeButton;

    @FXML
    private Button purchaseButton;

    @FXML
    private TextField quantityTxtField;

    @FXML
    private Label totalPriceLb;

    @FXML
    void connectionPage(ActionEvent event) throws IOException {
        NavBarController.connectionPage();
    }

    @FXML
    void homepage(ActionEvent event) throws IOException {
        NavBarController.homepage();
    }

    @FXML
    void minusDinos(ActionEvent event) {
        int quantity = Integer.parseInt(quantityTxtField.getText());
        if (quantity > 0) {
            quantityTxtField.setText(String.valueOf(quantity - 1));
            updateTotalPrice();
        }
    }

    @FXML
    void moreDinos(ActionEvent event) {
        int quantity = Integer.parseInt(quantityTxtField.getText());
        int stock = Integer.parseInt(dinoStockLb.getText());
        if (quantity < stock) {
            quantityTxtField.setText(String.valueOf(quantity + 1));
            updateTotalPrice();
        }
    }

    @FXML
    void purchaseDino(ActionEvent event) throws SQLException, IOException {
        if (Context.user != null) {
            int quantity = Integer.parseInt(quantityTxtField.getText());
            if (quantity > 0) {
                int stock = Integer.parseInt(dinoStockLb.getText());
                int newStock = stock - quantity;
                DatabaseConnection.updateDinoStock(Context.dinoId, newStock);
                DatabaseConnection.savePurchase(Context.user.getId(), Context.dinoId, quantity);
                initialize();
            }
        }
        else {
            NavBarController.connectionPage();
        }
    }

    @FXML
    void initialize() throws SQLException {
        Dinosaure dinosaure = getDino(Context.dinoId);
        dinoIdLb.setText(String.valueOf(dinosaure.getId()));
        dinoSizeLb.setText(String.valueOf(dinosaure.getSize()));
        dinoGenderLb.setText(dinosaure.getGender());
        dinoDietLb.setText(dinosaure.getDiet());
        dinoPriceLb.setText(String.valueOf(dinosaure.getPrice()));
        dinoFamilyIdLb.setText(String.valueOf(dinosaure.getFamilyId()));
        dinoStockLb.setText(String.valueOf(dinosaure.getStock()));
        quantityTxtField.setText("0");
        updateTotalPrice();
    }

    private void updateTotalPrice() {
        float totalPrice = Float.parseFloat(dinoPriceLb.getText()) * Integer.parseInt(quantityTxtField.getText());
        totalPriceLb.setText(String.valueOf(totalPrice));
    }

}
