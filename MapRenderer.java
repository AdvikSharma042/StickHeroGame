package com.example.finalstickhero;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;

public class MapRenderer {
    static int count = 0;
    static private final ImageView cherryImageContainer = new ImageView();
    static private final Random random=new Random();
    static private  Rectangle rectangle1=new Rectangle();
    static private  Rectangle rectangle2=new Rectangle();
    static private double distance;
    static public void firstRender(AnchorPane anchorPane,int mode){
        if(mode==0) {
            count=0;
            System.out.println("hello");
            ArrayList<Double> details = new ArrayList<>();
            try {
                BufferedReader in = new BufferedReader(new FileReader("C:\\AP\\finalStickHeroSPACE\\finalStickHero\\src\\main\\java\\com\\example\\finalstickhero\\SavedGameDetails.txt"));
                String l = in.readLine();
                while (l != null) {
                    System.out.println(l);
                    details.add(Double.parseDouble(l));
                    l = in.readLine();
                }
                in.close();
                if (details.get(0) != -1) {
                    for(Double d: details){
                        System.out.println(d);
                    }
                    System.out.println("Hello");
                    rectangle1.setLayoutX(details.get(0));
                    rectangle1.setWidth(details.get(1));
                    rectangle2.setLayoutX(details.get(2));
                    rectangle2.setWidth(details.get(3));
                    rectangle1.setLayoutY(565);rectangle2.setLayoutY(565);
                    rectangle1.setHeight(150);rectangle2.setHeight(150);
                    anchorPane.getChildren().add(rectangle1);
                    anchorPane.getChildren().add(rectangle2);
                    double updated_score=details.get(4);
                    int scoreToBeShown=(int) updated_score;
                    double updated_cherry=details.get(5);
                    int CherryToBeShown=(int) updated_cherry;
                    Character.setScore(scoreToBeShown);
                    Character.setCherryCount(CherryToBeShown);
                    Character.scoreManager();Character.CherryManger();
                }
            } catch (Exception ignored) {
                ignored.printStackTrace();

            }
        }
        if(mode==1) {
            RectangleFactory rf = new RectangleFactory();
            rectangle1 = rf.getRectangle("pivot");
            rectangle2 = rf.getRectangle("target", rectangle1);
            anchorPane.getChildren().add(rectangle1);
            anchorPane.getChildren().add(rectangle2);
        }
        if (count != 0) {
            count = 0;
        }
        Character.spawnCharacter(anchorPane);
        Character.spawnStick(anchorPane);
        distance = rectangle2.getLayoutX() - rectangle1.getWidth();
        System.out.println("distance is "+distance);
    }
    static private void removeRectangle(AnchorPane anchorPane){
        if(count%2 == 0){
            anchorPane.getChildren().remove(rectangle1);
        }
        else{
            anchorPane.getChildren().remove(rectangle2);
        }
    }
    static private void addRectangle(AnchorPane anchorPane){
        RectangleFactory rf = new RectangleFactory();
        if (count%2 == 0){
            rectangle1 = rf.getRectangle("target",rectangle2);
            anchorPane.getChildren().add(rectangle1);
        }
        else{
            rectangle2 = rf.getRectangle("target",rectangle1);
            anchorPane.getChildren().add(rectangle2);
        }
    }
    static public void advanceMap(AnchorPane anchorPane) {
        removeCherry(anchorPane);
        removeRectangle(anchorPane);
        Rectangle movingRectangle = (count % 2 == 0) ? rectangle2 : rectangle1;
        double distance = -1*movingRectangle.getLayoutX();
        KeyValue keyValue = new KeyValue(movingRectangle.layoutXProperty(), movingRectangle.getLayoutX() + distance);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(2), keyValue);
        Timeline timeline = new Timeline(keyFrame);
        timeline.setOnFinished(event -> {
            Character.scoreManager();
            addRectangle(anchorPane);
            count++;
            addCherry(anchorPane);
        });
        timeline.play();
        Character.forwardWalk(false,anchorPane);
    }
    public static Rectangle getPivotRectangle(){
        if(count%2 == 0){
            return rectangle1;
        }
        return rectangle2;
    }
    public static Rectangle getTargetRectangle(){
        if(count%2 == 0){
            return rectangle2;
        }
        return rectangle1;
    }
    static public void addCherry(AnchorPane anchorPane){
        if(distance > 70){
            int choice = random.nextInt(2);
            if(choice==1){
                double x_cord = getTargetRectangle().getLayoutX()+getPivotRectangle().getWidth();
                cherryImageContainer.setLayoutX(x_cord/2);
                cherryImageContainer.setLayoutY(575);
                cherryImageContainer.setFitHeight(20);cherryImageContainer.setPreserveRatio(true);
                cherryImageContainer.setImage(new Image("C:\\AP\\Stick_Hero\\src\\main\\resources\\com\\example\\stick_hero\\cherry.png"));
                anchorPane.getChildren().add(cherryImageContainer);
            }
            else{
                System.out.println("Bye");
            }
        }
    }
    static public void removeCherry(AnchorPane anchorPane){
        try{
            anchorPane.getChildren().remove(cherryImageContainer);
        }
        catch (Exception ignored){

        }
    }
    static public void catchCherry(AnchorPane anchorPane){
        double characterX = Character.getCharacterImageContainer().getLayoutX();
        double decidingDistance = characterX-cherryImageContainer.getLayoutX();
        if(decidingDistance >-10 && decidingDistance <10 && Character.isReverse()){
            Character.setCherryCount(Character.getCherryCount()+1);
            System.out.println("Cherry updated");
            Character.CherryManger();
            removeCherry(anchorPane);
        }
    }
    static public void exitMap(AnchorPane anchorPane){
        try{
            anchorPane.getChildren().remove(rectangle1);
            anchorPane.getChildren().remove(rectangle2);
            removeCherry(anchorPane);
        }
        catch (Exception ignored){
        }
    }
}

