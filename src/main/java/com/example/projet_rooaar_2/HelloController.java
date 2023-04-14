package com.example.projet_rooaar_2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class HelloController {

    @FXML
    private Button Btn_Inscription;

    @FXML
    private CheckBox Btn_accepter_conditions;

    @FXML
    private Button Btn_Connection;

    @FXML
    private Button Btn_aller_Inscription;
    @FXML
    private Button Btn_Iventaire_Ajouter;

    @FXML
    private Button Btn_Iventaire_Clients;

    @FXML
    private Button Btn_Iventaire_Importer;

    @FXML
    private Button Btn_Iventaire_Inventaire;

    @FXML
    private Button Btn_Iventaire_Maj;

    @FXML
    private Button Btn_Iventaire_Statistiques;

    @FXML
    private Button Btn_Iventaire_supp;

    @FXML
    private Button Btn_Iventaire_vider;

    @FXML
    private ImageView inventaire_Image;

    @FXML
    private TableColumn<?, ?> inventaire_col_Id;

    @FXML
    private TableColumn<?, ?> inventaire_col_Nom;

    @FXML
    private TableColumn<?, ?> inventaire_col_Regime;
    @FXML
    private ComboBox<?> Inventaire_Regime;


    @FXML
    private TableColumn<?, ?> inventaire_col_Prix;

    @FXML
    private TableColumn<?, ?> inventaire_col_Stock;

    @FXML
    private TableColumn<?, ?> inventaire_col_Type;

    @FXML
    private AnchorPane main_page_admin;

    @FXML
    private AnchorPane page_inventaire;

    @FXML
    private TableView<?> tab_inventaire;
    @FXML
    private TextField Inventaire_stock;

    @FXML
    private ComboBox<?> Inventaire_type;

    @FXML
    private TextField inventaire_ID;

    @FXML
    private TextField inventaire_Nom;

    private String [] regimeList = {"Herbivore", "Carnivore","Homme-nivore"};
    private String [] typeList = {"Volant", "Terrestre", "Marin"};



    @FXML
    public void Inventaire_Regime_Liste(){

        List<String> regimeL = new ArrayList<>();
        for (String data : regimeList) {
            regimeL.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(regimeL);
        Inventaire_Regime.setItems(listData);
    }

    @FXML
    public void Inventaire_Type_Liste(){

        List<String> typeL = new ArrayList<>();
        for (String data : typeList) {
            typeL.add(data);
        }

        ObservableList listData = FXCollections.observableArrayList(typeL);
        Inventaire_type.setItems(listData);
    }


    @FXML
    public void afficher_menu_deroulant() {
        Inventaire_Regime_Liste();
        Inventaire_Type_Liste();
    }

    public





    @FXML
        ///Si le bouton inscription est cliquer alors...

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





    @FXML
    void Se_connecter(ActionEvent event) {

    }
    @FXML
    void S_inscrire(ActionEvent event) {

    }

    @FXML
    void accepter_conditions(ActionEvent event) {

    }



}
