package com.example.finalstickhero;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.*;
import java.util.ArrayList;

public class ScoreKeeper {
    public boolean isAdded() {
        return isAdded;
    }

    public void setAdded(boolean added) {
        isAdded = added;
    }

    private  boolean isAdded=false;
    private final Rectangle scoreBar = new Rectangle();
    private final Label scoreNameLabel=new Label();
    private final Label scoreValue = new Label();
    private final ImageView cherryDisplay=new ImageView();
    private final Label cherryCount=new Label();
    private final Button SaveButton = new Button();
    public void removeAll(AnchorPane anchorPane){
        anchorPane.getChildren().remove(scoreBar);
        anchorPane.getChildren().remove(scoreNameLabel);
        anchorPane.getChildren().remove(cherryDisplay);
        anchorPane.getChildren().remove(scoreValue);
        anchorPane.getChildren().remove(cherryCount);
        anchorPane.getChildren().remove(SaveButton);
        isAdded=false;
    }
    public void SaveFunction() throws IOException {
        System.out.println("Button Pressed");
        PrintWriter out;
        out = new PrintWriter(new FileWriter("C:\\AP\\finalStickHeroSPACE\\finalStickHero\\src\\main\\java\\com\\example\\finalstickhero\\SavedGameDetails.txt"));
        ArrayList<String> gameDetails = new ArrayList<>();
        gameDetails.add(String.valueOf(MapRenderer.getPivotRectangle().getLayoutX()));
        gameDetails.add(String.valueOf(MapRenderer.getPivotRectangle().getWidth()));
        gameDetails.add(String.valueOf(MapRenderer.getTargetRectangle().getLayoutX()));
        gameDetails.add(String.valueOf(MapRenderer.getTargetRectangle().getWidth()));
        gameDetails.add(String.valueOf(Character.getCurrScore()));
        gameDetails.add(String.valueOf(Character.getCherryCount()));
        for(String l: gameDetails){
            System.out.println(l);
            out.println(l);
        }
        out.close();
        gameRunner.getPlayRoot().requestFocus();
    }
    ScoreKeeper(){
        Initialize();
    }
    public void Initialize() {
        double widthFactor = 1000.0 / 700.0;  // Width scaling factor
        double heightFactor = 715.0 / 880.0;  // Height scaling factor

        scoreBar.setWidth(372 * widthFactor);
        scoreBar.setHeight(37 * heightFactor);
        scoreBar.setLayoutX(51 * widthFactor);
        scoreBar.setLayoutY(0);
        scoreBar.setLayoutX(scoreBar.getLayoutX()-20);

        // Initialize Score Name Label
        scoreNameLabel.setText("Score");
        scoreNameLabel.setTextFill(Color.WHITE);
        scoreNameLabel.setLayoutX(77 * widthFactor);
        scoreNameLabel.setLayoutY(8 * heightFactor);
        scoreNameLabel.setPrefWidth(40 * widthFactor);
        scoreNameLabel.setPrefHeight(20 * heightFactor);

        // Initialize Score Value Label
        scoreValue.setText("0");
        scoreValue.setTextFill(Color.WHITE);
        scoreValue.setLayoutX(120 * widthFactor);
        scoreValue.setLayoutY(8 * heightFactor);
        scoreValue.setPrefWidth(9 * widthFactor);
        scoreValue.setPrefHeight(21 * heightFactor);
        //Initialize SaveButton
        SaveButton.setText("Save Game");
        SaveButton.setLayoutX(190*widthFactor);
        SaveButton.setLayoutY(0);
        SaveButton.setPrefWidth(100);
        SaveButton.setPrefHeight(5);
        SaveButton.setOnAction(event -> {
            try {
                SaveFunction();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        // Initialize Cherry ImageView
        Image cherryImage = new Image("C:\\AP\\finalStickHeroSPACE\\finalStickHero\\src\\main\\resources\\com\\example\\finalstickhero\\cherry.png"); // Replace with the actual path
        cherryDisplay.setImage(cherryImage);
        cherryDisplay.setFitWidth(21 * widthFactor);
        cherryDisplay.setFitHeight(24 * heightFactor);
        cherryDisplay.setLayoutX(310 * widthFactor);
        cherryDisplay.setLayoutY(8 * heightFactor);

        // Cherry Count Label
        cherryCount.setText("0");
        cherryCount.setTextFill(Color.WHITE);
        cherryCount.setLayoutX(343 * widthFactor);
        cherryCount.setLayoutY(8 * heightFactor);
        cherryCount.setPrefWidth(9 * widthFactor);
        cherryCount.setPrefHeight(21 * heightFactor);
    }
    public void addElements(AnchorPane anchorPane){
        anchorPane.getChildren().add(scoreBar);
        anchorPane.getChildren().add(scoreNameLabel);
        anchorPane.getChildren().add(scoreValue);
        anchorPane.getChildren().add(cherryDisplay);
        anchorPane.getChildren().add(cherryCount);
        anchorPane.getChildren().add(SaveButton);
        isAdded=true;
    }
    public void updateCherryCount(){
        cherryCount.setText(String.valueOf(Character.getCherryCount()));
    }
    public void updateScoreCount(){
        scoreValue.setText(String.valueOf(Character.getCurrScore()));
    }
}


