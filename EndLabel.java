package com.example.finalstickhero;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.Map;

public class EndLabel {
    private final Rectangle mainLabel=new Rectangle();
    private final Label messageLabel=new Label();
    private final Label yesLabel=new Label();
    private final Label noLabel=new Label();
    private final Button yesButton=new Button();
    private final Button noButton=new Button();
    private final double widthFactor= (double) 600 /1000;
    public void setMainLabel(){
        mainLabel.setLayoutX(260 * widthFactor);
        mainLabel.setLayoutY(185);
        mainLabel.setFill(Color.rgb(41, 164, 144));
        mainLabel.setWidth(350);
        mainLabel.setHeight(315);
        mainLabel.setArcHeight(100);
        mainLabel.setArcWidth(100);
    }
    public void setMessageLabel(){
        messageLabel.setText(" Do you want a revive?");
        messageLabel.setFont(javafx.scene.text.Font.font("System", 25));
        messageLabel.setTextFill(Color.WHITE);
        messageLabel.setPrefWidth(275);
        messageLabel.setPrefHeight(70);
        messageLabel.setLayoutX(335 * widthFactor);
        messageLabel.setLayoutY(305);
    }
    public void setYesLabel(){
        yesLabel.setText("Yes");
        yesLabel.setFont(javafx.scene.text.Font.font("System", 20));
        yesLabel.setTextFill(Color.WHITE);
        yesLabel.setPrefWidth(50);
        yesLabel.setPrefHeight(30);
        yesLabel.setLayoutX(335 * widthFactor);
        yesLabel.setLayoutY(405);
    }
    public void setNoLabel(){
        noLabel.setText("New Game");
        noLabel.setFont(javafx.scene.text.Font.font("System", 20));
        noLabel.setTextFill(Color.WHITE);
        noLabel.setPrefWidth(150);
        noLabel.setPrefHeight(30);
        noLabel.setLayoutX(555 * widthFactor);
        noLabel.setLayoutY(405);
    }
    public void setYesButton(){
        yesButton.setOpacity(0.33);
        yesButton.setPrefWidth(50);
        yesButton.setPrefHeight(30);
        yesButton.setLayoutX(330 * widthFactor);
        yesButton.setLayoutY(405);
    }
    public void setNoButton(){
        noButton.setOpacity(0.33);
        noButton.setPrefWidth(100);
        noButton.setPrefHeight(30);
        noButton.setLayoutX(555 * widthFactor);
        noButton.setLayoutY(405);
    }
    public void setLabel() {
        setMainLabel();
        setMessageLabel();
        setYesLabel();
        setNoLabel();
        setYesButton();
        setNoButton();
    }

    public void removeEndLabel(AnchorPane anchorPane){
        try {
            anchorPane.getChildren().remove(mainLabel);
            anchorPane.getChildren().remove(messageLabel);
            anchorPane.getChildren().remove(yesLabel);
            anchorPane.getChildren().remove(noLabel);
            anchorPane.getChildren().remove(yesButton);
            anchorPane.getChildren().remove(noButton);
        }
        catch (Exception ignored){

        }
    }
    public void addEndLabel(AnchorPane anchorPane){
        setLabel();
        anchorPane.getChildren().add(mainLabel);
        anchorPane.getChildren().add(messageLabel);
        anchorPane.getChildren().add(yesLabel);
        anchorPane.getChildren().add(noLabel);
        anchorPane.getChildren().add(yesButton);
        yesButton.setOnAction(event -> {
            System.out.println("Cherries available are "+Character.getCherryCount());
            removeEndLabel(anchorPane);
            if(Character.getCherryCount()>=3){
                Character.setCherryCount(Character.getCherryCount()-3);
                Character.CherryManger();
                System.out.println("score of the player at death is "+Character.getCurrScore());
            }
            else{
                Character.setScore(0);
            }
            Character.scoreManager();
            MapRenderer.firstRender(anchorPane,1);
        });
        anchorPane.getChildren().add(noButton);
        noButton.setOnAction(event -> {Character.setCherryCount(0);
            removeEndLabel(anchorPane);
            gameRunner.getPlayRoot().requestFocus();
            Character.CherryManger();
            Character.setScore(0);
            Character.scoreManager();
            MapRenderer.firstRender(anchorPane,1);
        });
    }


}
