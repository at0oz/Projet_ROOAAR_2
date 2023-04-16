package com.example.demo;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.sql.SQLException;

public class Dinosaure {
    private int id;
    private int size;
    private String gender;
    private String diet;
    private String imagePath;
    private int familyId;
    private String familyName;
    private float price;
    private int stock;
    public Dinosaure(int id, int size, String gender, String diet, String imagePath, int familyId, int stock) {
        this.id = id;
        this.size = size;
        this.gender = gender;
        this.diet = diet;
        this.imagePath = imagePath;
        this.familyId = familyId;
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDiet() {
        return diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    public Image getImage() {
        Image dinoImage;
        try { // load dino's image
            dinoImage = new Image(new FileInputStream(imagePath));
        } catch (Exception e) { // use default image
            dinoImage = new Image(getClass().getResourceAsStream("/com/example/demo/images/dinoDefaultImage.png"));
        }
        return dinoImage;
    }

    public String getImagePath() { return imagePath; }
    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    public int getFamilyId() { return familyId; }

    public void setFamilyId(int familyId) { this.familyId = familyId; }

    public String getFamilyName() throws SQLException {
        return DatabaseConnection.getDinoFamilyName(familyId);
    }

    public void setFamilyName(String familyName) { this.familyName = familyName; }

    public float getPrice() throws SQLException { return DatabaseConnection.getDinoPrice(id); }

    public void setPrice(float price) { this.price = price; }

    public int getStock() { return stock; }

    public void setStock(int stock) { this.stock = stock; }
}
