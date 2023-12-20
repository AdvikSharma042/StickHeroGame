package com.example.finalstickhero;

public class Records {
    private static int highScore=-1;
    private static int lastScore=-1;
    private static int totalCherries=0;

    public static int getHighScore() {
        return highScore;
    }

    public static int getLastScore() {
        return lastScore;
    }

    public static int getTotalCherries() {
        return totalCherries;
    }

    public void updateHighScore(){
        if(Character.getCurrScore() >= highScore){
            highScore=Character.getCurrScore();
        }
    }
    public void updateLastScore(){
        lastScore=Character.getCurrScore();
    }
    public void updateCherry(){
        totalCherries+=Character.getCherryCount();
    }
}