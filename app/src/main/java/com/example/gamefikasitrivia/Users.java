package com.example.gamefikasitrivia;

public class Users {
    private static Users instance = null;
    private String id;
    private String email;
    private String username;
    private String Password;
    public Users(String id, String email, String username, String password) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.Password = password;
    }
    public static Users getInstance() {
        if (instance == null) {
            instance = new Users();
        }
        return instance;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public Users(String email, String username, String password){
           this.email=email;
           this.username=username;
           this.Password=password;
    }
    public Users(){
    }
}
