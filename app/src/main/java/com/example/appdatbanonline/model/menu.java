package com.example.appdatbanonline.model;

public class menu {
    private int hinhmenu;
    private String tenmenu;

    public menu(int hinhmenu, String tenmenu) {
        this.hinhmenu = hinhmenu;
        this.tenmenu = tenmenu;
    }

    public int getHinhmenu() {
        return hinhmenu;
    }

    public void setHinhmenu(int hinhmenu) {
        this.hinhmenu = hinhmenu;
    }

    public String getTenmenu() {
        return tenmenu;
    }

    public void setTenmenu(String tenmenu) {
        this.tenmenu = tenmenu;
    }
}
