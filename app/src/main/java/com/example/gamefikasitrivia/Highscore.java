package com.example.gamefikasitrivia;

public class Highscore {
    private String username;
    private int highscore;
    public int getHighscore() {
        return highscore;
    }

    public void setHighscore(int highscore) {
        this.highscore = highscore;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }




    public Highscore(int highscore, String username) {
        this.username = username;
        this.highscore = highscore;
    }
    public Highscore(){

    }
    public String toString(){
        return this.username + "\n Nilai tertinggi: " + highscore;
    }
}
