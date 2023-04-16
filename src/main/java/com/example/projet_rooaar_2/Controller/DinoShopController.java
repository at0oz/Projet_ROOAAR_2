package com.example.projet_rooaar_2.Controller;

import com.example.projet_rooaar_2.Context;
import com.example.projet_rooaar_2.DatabaseConnection;
import com.example.projet_rooaar_2.Dinosaure;
import com.example.projet_rooaar_2.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.IOException;
import java.sql.*;

import static com.example.projet_rooaar_2.HelloApplication.changeScene;

public class DinoShopController {
    @FXML
    private ImageView dinoImageView;
    @FXML
    private Button addDinoButton = new Button("Ajouter");

    private int currentDinoIndex;

    @FXML
    private GridPane articlesGridPane;

    @FXML
    private Button connectionButton;

    @FXML
    private HBox navBarHBox;


    private Button profileButton = new Button("Profil");

    @FXML
    void connectionPage(ActionEvent event) throws IOException {
        NavBarController.connectionPage();
    }

    @FXML
    public void initialize() {
        System.out.println("initialization");
        initializeConnectionButton();
        addDinoButton.setOnAction(e -> {
            try {
                addDino();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        loadDinos();
        initializeProfileButton();
    }

    private void initializeProfileButton() {
        if (Context.user != null) { // if user is connected
            System.out.println("User is loged in");
            profileButton.setStyle("-fx-background-color: #000000;");
            profileButton.setTextFill(Color.WHITE);
            profileButton.setOnAction(event -> {
                try {
                    NavBarController.profile();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            navBarHBox.getChildren().add(1, profileButton); // add profile button between homepage and logout
        }
    }

    private void initializeConnectionButton() {
        if (Context.user != null) {
            connectionButton.setText("Se deconnecter");
            connectionButton.setOnAction(e -> {
                disconnect();
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

    private void disconnect() {
        Context.user = null;
        navBarHBox.getChildren().remove(profileButton);
        initialize();
    }


    private void loadDinos() {
        currentDinoIndex = 0;
        articlesGridPane.getChildren().clear(); // empty article's gridPane

        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection = dbConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            String query = "select * from dinosaures";
            ResultSet dinos = statement.executeQuery(query);
            while (dinos.next()) {
                addDino(DatabaseConnection.getDino(dinos.getInt("dino_id")));
            }
            dbConnection.closeConnection();
            addDino(null);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void addDino(Dinosaure dino) throws SQLException {
        int row = currentDinoIndex / (articlesGridPane.getRowCount() + 1);
        int col = currentDinoIndex % articlesGridPane.getColumnCount();
        if (dino != null) {
            articlesGridPane.add(dinoArticle(dino), col, row);
        } else {
            articlesGridPane.add(addDinoButton, col, row);
        }
        currentDinoIndex++;
    }

    private VBox dinoArticle(Dinosaure dino) throws SQLException {
        int dinoId = dino.getId();

        VBox layout = new VBox();

        HBox subLayout = new HBox();

        Label dinoFamilyLb = new Label(dino.getFamilyName());
        dinoFamilyLb.setTextFill(Color.web("#FFFFFF"));
        dinoFamilyLb.setFont(new Font(16));

        dinoImageView = new ImageView(dino.getImage());
        dinoImageView.setFitWidth(120);
        dinoImageView.setFitHeight(100);
        dinoImageView.setPreserveRatio(true);

        VBox dinoInfoLayout = getDinoInfoLayout(dino);

        subLayout.getChildren().addAll(dinoImageView, dinoInfoLayout);
        subLayout.setSpacing(5);
        subLayout.setAlignment(Pos.CENTER_LEFT);

        Button purchaseButton = new Button("Acheter");
        purchaseButton.setOnAction(e -> {
            try {
                purchaseDino(dinoId);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        Button delteButton = new Button("Retirer");
        delteButton.setOnAction(e -> {
            deleteDino(dinoId);
        });

        Button editButton = new Button("Modifier");
        editButton.setOnAction(e -> {
            editDino(dinoId);
        });

        HBox buttons = new HBox();
        buttons.getChildren().add(purchaseButton);
        buttons.setSpacing(5);

        if (Context.userIsAdmin() || true) {
            buttons.getChildren().addAll(editButton, delteButton);
        }

        layout.getChildren().addAll(dinoFamilyLb, subLayout, buttons);
        layout.setBackground(new Background(new BackgroundFill(Color.valueOf("#003e1c"), CornerRadii.EMPTY, Insets.EMPTY)));
        layout.setOpacity(0.93);
        layout.setSpacing(10);
        layout.setPadding(new Insets(10, 10, 10, 10));
        layout.setAlignment(Pos.TOP_CENTER);
        return layout;
    }

    private VBox getDinoInfoLayout(Dinosaure dino) throws SQLException {
        VBox dinoInfoLayout = new VBox();

        Label dinoIdLb = new Label("ID : " + dino.getId());
        dinoIdLb.setTextFill(Color.web("#FFFFFF"));

        Label dinoGenderLb = new Label("Sexe : " + dino.getGender());
        dinoGenderLb.setTextFill(Color.web("#FFFFFF"));

        Label dinoSizeLb = new Label("Taille : " + dino.getSize());
        dinoSizeLb.setTextFill(Color.web("#FFFFFF"));

        Label dinoDietLb = new Label("Diet : " + dino.getDiet());
        dinoDietLb.setTextFill(Color.web("#FFFFFF"));

        Label dinoPriceLb = new Label("Prix : " + dino.getPrice());
        dinoPriceLb.setTextFill(Color.web("#FFFFFF"));

        dinoInfoLayout.getChildren().addAll(dinoIdLb, dinoGenderLb, dinoSizeLb, dinoDietLb, dinoPriceLb);

        return dinoInfoLayout;
    }


    private void purchaseDino(int dinoId) throws IOException {
        Context.dinoId = dinoId;
        HelloApplication.changeScene("View/purchaseDino.fxml");
    }

    private void deleteDino(int dinoId) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection connection = dbConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            String deleteQuery = "DELETE FROM `dinoshop`.`dinosaures` WHERE (`dino_id` = '" + dinoId + "');";
            statement.executeUpdate(deleteQuery);
            dbConnection.closeConnection();
            loadDinos();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void addDino() throws IOException {
        HelloApplication.changeScene("View/addDino.fxml");
    }

    private void editDino(int dinoId) {
        Context.dinoId = dinoId;
        try {
            changeScene("View/editDino.fxml");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}