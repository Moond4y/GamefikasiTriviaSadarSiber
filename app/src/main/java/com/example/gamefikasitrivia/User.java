package com.example.gamefikasitrivia;

public class User {
    private static User instance = null;
    private String email;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private String username;

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

    public User(String email, String password, String username){
        this.email = email;
        this.password = password;
        this.username = username;
    }
    public User(){
    }
    public static User getInstance() {
        if (instance == null) {
            instance = new User();
        }
        return instance;
    }
}
