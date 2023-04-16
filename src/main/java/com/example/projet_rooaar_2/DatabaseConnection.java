package com.example.projet_rooaar_2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class DatabaseConnection {
    public static Connection databaseLink;

    public static Connection getConnection() {
        String url= "jdbc:mysql://localhost:3306/dinoshop";
        String username="root";
        String password ="";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLink = DriverManager.getConnection(url, username, password);
            System.out.println("zz");
        }
        catch (Exception e){
            System.out.println(e);
        }
        return databaseLink;
    }

    public static void closeConnection() throws SQLException {
        databaseLink.close();
    }

    public static User getUser(String userEmail) throws SQLException {
        User user = null; // if no user if found, null will be returned

        Connection connection = getConnection();
        PreparedStatement pst = connection.prepareStatement("SELECT * FROM users WHERE email = ?;");
        pst.setString(1, userEmail);
        ResultSet queryResult = pst.executeQuery();
        if (queryResult.next()) { // A user matches with the provided userId
            int userId = queryResult.getInt("id");
            String userLastName = queryResult.getString("lastName");
            String userFirstName = queryResult.getString("firstName");
            String userPassword = queryResult.getString("password");
            String userAddress = queryResult.getString("address");
            String userStatus = queryResult.getString("status");
            user = new User(userId, userLastName, userFirstName, userEmail, userPassword, userAddress, userStatus);
        }
        closeConnection();

        return user;
    }

    public static void addUser(User newUser) throws SQLException {
        System.out.println("Adding user");
        Connection connection = getConnection();
        String addUserQuery = "INSERT INTO `dinoshop`.`users` " +
                "(`lastName`, `firstName`, `email`, `password`, `address`, `status`) " +
                "VALUES (?, ?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(addUserQuery);
        preparedStatement.setString(1, newUser.getLastName());
        preparedStatement.setString(2, newUser.getFirstName());
        preparedStatement.setString(3, newUser.getEmail());
        preparedStatement.setString(4, newUser.getPassword());
        preparedStatement.setString(5, newUser.getAddress());
        preparedStatement.setString(6, newUser.getStatus());
        preparedStatement.executeUpdate();
        System.out.println(preparedStatement);
        closeConnection();
    }
    public static void addDino(Dinosaure dinosaure) throws SQLException {
        System.out.println("Adding dino");
        Connection connection = getConnection();
        String addDinoQuery = "INSERT INTO `dinoshop`.`dinosaures` " +
                "(`dino_id`, `dino_taille`, `dino_sexe`, `dino_diet`, `dino_image`, `dino_familyId`, `dino_stock`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(addDinoQuery);
        preparedStatement.setInt(1, dinosaure.getId());
        preparedStatement.setInt(2, dinosaure.getSize());
        preparedStatement.setString(3, dinosaure.getGender());
        preparedStatement.setString(4, dinosaure.getDiet());
        preparedStatement.setString(5, dinosaure.getImagePath());
        preparedStatement.setInt(6, dinosaure.getFamilyId());
        preparedStatement.setInt(7, dinosaure.getStock());
        preparedStatement.executeUpdate();
        closeConnection();
    }

    public static Dinosaure getDino(int dinoId) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String query = "select * from dinosaures where dino_id = '" + dinoId + "'";
        ResultSet dino = statement.executeQuery(query);
        Dinosaure dinosaure = null;
        if (dino.next()) { // A dinosaure matches with the provided dinoId
            int dinoSize = dino.getInt("dino_taille");
            String dinoGender = dino.getString("dino_sexe");
            String dinoDiet = dino.getString("dino_diet");
            String dinoImage = dino.getString("dino_image");
            int dinoFamilyId = dino.getInt("dino_familyId");
            int dinoStock = dino.getInt("dino_stock");
            dinosaure = new Dinosaure(dinoId, dinoSize, dinoGender, dinoDiet, dinoImage, dinoFamilyId, dinoStock);
        }
        closeConnection();

        return dinosaure;
    }

    public static void saveDino(Dinosaure dinosaure) throws SQLException {
        System.out.println("Saving changes");
        Connection connection = getConnection();
        String editDinoQuery = "UPDATE `dinoshop`.`dinosaures`" +
                "SET `dino_id` = ?, `dino_taille` = ?, `dino_sexe` = ?, " +
                "`dino_diet` = ?, `dino_image` = ? , `dino_familyId` = ? , `dino_stock` = ? " +
                "WHERE (`dino_id` = ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(editDinoQuery);
        preparedStatement.setInt(1, dinosaure.getId());
        preparedStatement.setInt(2, dinosaure.getSize());
        preparedStatement.setString(3, dinosaure.getGender());
        preparedStatement.setString(4, dinosaure.getDiet());
        preparedStatement.setString(5, dinosaure.getImagePath());
        preparedStatement.setInt(6, dinosaure.getFamilyId());
        preparedStatement.setInt(7, dinosaure.getStock());
        preparedStatement.setInt(8, Context.dinoId);
        preparedStatement.executeUpdate();
        System.out.println(preparedStatement);
        closeConnection();
    }

    public static String getDinoFamilyName(int familyId) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String query = "select * from families where family_id = '" + familyId + "'";
        ResultSet family = statement.executeQuery(query);
        String family_name = null;
        if (family.next()) { // A family matches with the provided dinoId
            family_name = family.getString("family_name");
        }
        closeConnection();

        return family_name;
    }

    public static float getDinoPrice(int dinoId) throws SQLException {
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String query = "select * from prices where dino_id = '" + dinoId + "'";
        ResultSet family = statement.executeQuery(query);
        Float dinoPrice = null;
        if (family.next()) { // A price matches with the provided dinoId
            dinoPrice = family.getFloat("dino_price");
        }
        closeConnection();

        return dinoPrice;
    }

    public static void updateDinoPrice(Float dinoPrice) throws SQLException {
        System.out.println("Updating price");
        Connection connection = getConnection();
        String editDinoQuery = "UPDATE `dinoshop`.`prices` SET `dino_price` = ? WHERE (`dino_id` = ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(editDinoQuery);
        preparedStatement.setFloat(1, dinoPrice);
        preparedStatement.setInt(2, Context.dinoId);
        preparedStatement.executeUpdate();
        System.out.println(preparedStatement);
        closeConnection();
    }

    public static void addDinoPrice(int dinoId, Float dinoPrice) throws SQLException {
        System.out.println("Adding price");
        Connection connection = getConnection();
        String addPriceQuery = "INSERT INTO `dinoshop`.`prices` (`dino_id`, `dino_price`) VALUES (?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(addPriceQuery);
        preparedStatement.setInt(1, dinoId);
        preparedStatement.setFloat(2, dinoPrice);
        preparedStatement.executeUpdate();
        System.out.println(preparedStatement);
        closeConnection();
    }

    public static ObservableList<Integer> getFamiliesId() throws SQLException {
        // return a list with the id of each family
        ObservableList<Integer> familiesId = FXCollections.observableArrayList();
        Connection connection = getConnection();
        Statement statement = connection.createStatement();
        String query = "select family_id from families";
        ResultSet family = statement.executeQuery(query);
        while (family.next()) {
            familiesId.add(family.getInt("family_id"));
        }
        closeConnection();

        return familiesId;
    }

    public static void updateDinoStock(int dinoId, int newStock) throws SQLException {
        System.out.println("Updating stock");
        Connection connection = getConnection();
        String updateDinoStockQuery = "UPDATE `dinoshop`.`dinosaures` SET `dino_stock` = ? WHERE (`dino_id` = ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(updateDinoStockQuery);
        preparedStatement.setInt(1, newStock);
        preparedStatement.setInt(2, dinoId);
        preparedStatement.executeUpdate();
        System.out.println(preparedStatement);
        closeConnection();
    }

    public static void savePurchase(int userId, int dinoId, int quantity) throws SQLException {
        System.out.println("Adding purchase");
        Connection connection = getConnection();
        String addPriceQuery = "INSERT INTO `dinoshop`.`purchases` (`userId`, `dinoId`, `dinoQuantity`)" +
                               " VALUES (?, ?, ?);";
        PreparedStatement preparedStatement = connection.prepareStatement(addPriceQuery);
        preparedStatement.setInt(1, userId);
        preparedStatement.setInt(2, dinoId);
        preparedStatement.setInt(3, quantity);
        preparedStatement.executeUpdate();
        System.out.println(preparedStatement);
        closeConnection();
    }
}
