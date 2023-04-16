package com.example.projet_rooaar_2;

import java.sql.SQLException;

public class User {
    private int id;

    private String lastName;

    private String firstName;
    private String email;
    private String password;

    private String address;

    private String status;

    public User(int id, String lastName, String firstName, String email, String password, String address, String status) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.status = status;
    }

    public User(String lastName, String firstName, String email, String password, String address) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.status = "User";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password=" + password +
                ", status='" + status + '\'' +
                '}';
    }

    public boolean isAdmin() {
        return status.equals("admin");
    }

    public boolean isUnique() throws SQLException {
        if (DatabaseConnection.getUser(email) != null) {
            return false;
        }
        else {
            return true;
        }
    }
}
