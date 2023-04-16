package com.example.projet_rooaar_2;

public class Context { // shares data between every controllers
    private final static Context instance = new Context();
    public static Context getInstance() {
        return instance;
    }

    public static User user = null;
    public static int dinoId = 8;

    public static boolean userIsAdmin() {
        return (user != null && user.isAdmin());
    }


    public int getDinoId() {
        return dinoId;
    }

    public void setDinoId(int dinoId) {
        this.dinoId = dinoId;
    }
}
