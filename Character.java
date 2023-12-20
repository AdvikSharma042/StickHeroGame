package com.example.finalstickhero;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.util.Duration;
import java.util.ArrayList;


public class Character {
    private static final ScoreKeeper scoreKeeper = new ScoreKeeper();
    private static final ImageView characterImageContainer = new ImageView();
    private static int currScore=0;
    private static int cherryCount=0;
    private static final Line myLine = new Line();
    private static boolean isReverse=false;
    private static boolean isWalking=false;
    static private final double imageHeight=40;
    static private final double imageWidth=55;
    public static void  spawnCharacter(AnchorPane anchorPane){
        if(!scoreKeeper.isAdded()) scoreKeeper.addElements(anchorPane);
        if(isReverse) isReverse=false;
        if(isWalking) isWalking=false;
        double x_coord=MapRenderer.getPivotRectangle().getWidth()-imageWidth;
        double y_cord=565-imageHeight;
        Image characterImage = new Image("C:\\AP\\finalStickHeroSPACE\\finalStickHero\\src\\main\\resources\\com\\example\\finalstickhero\\stickman.png");
        characterImageContainer.setLayoutX(x_coord);characterImageContainer.setLayoutY(y_cord);
        characterImageContainer.setFitHeight(imageHeight);characterImageContainer.setFitWidth(imageWidth);
        characterImageContainer.setImage(characterImage);
        anchorPane.getChildren().add(characterImageContainer);
    }
    public static void spawnStick(AnchorPane anchorPane){
        double startX = MapRenderer.getPivotRectangle().getWidth();
        double startY = 565;
        myLine.setStartX(startX);myLine.setStartY(startY);
        myLine.setEndX(startX);myLine.setEndY(startY);
        myLine.setStroke(Color.RED);myLine.setStrokeWidth(5);
        anchorPane.getChildren().add(myLine);
    }
    public static void enlargeStick(){
        myLine.setEndY(myLine.getEndY()-5);
    }
    public static void rotateStick(){
        System.out.println("rotation is called");
        isWalking=true;
        Timeline timeline = new Timeline();
        double length = myLine.getStartY()-myLine.getEndY();
        KeyValue endXvalue = new KeyValue(myLine.endXProperty(),myLine.getStartX()+length);
        KeyValue endYvalue = new KeyValue(myLine.endYProperty(),565);
        KeyFrame keyframe = new KeyFrame(Duration.seconds(0.5),endXvalue,endYvalue);
        timeline.getKeyFrames().add(keyframe);
        timeline.setOnFinished(event -> {Character.walk(gameRunner.getPlayingPane());});
        timeline.play();
    }
    public static void fall(AnchorPane anchorPane){
        System.out.println("fall called");
        KeyValue fall = new KeyValue(characterImageContainer.layoutYProperty(),650);
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.5),fall);
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(keyFrame);
        timeline.setOnFinished(event -> {
            EndLabel endLabel = new EndLabel();
            endLabel.addEndLabel(anchorPane);
            reset(0,anchorPane);});
        timeline.play();
    }
    public static void forwardWalk(boolean b,AnchorPane anchorPane){
        isWalking=true;
        if(b){
            KeyValue walk = new KeyValue(characterImageContainer.layoutXProperty(),myLine.getEndX());
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(2),walk);
            KeyFrame checkForCherry = new KeyFrame(Duration.millis(100),event -> {
                MapRenderer.catchCherry(anchorPane);
            });
            Timeline cherryChecker = new Timeline();
            cherryChecker.getKeyFrames().add(checkForCherry);
            cherryChecker.setCycleCount(Animation.INDEFINITE);
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(keyFrame);
            cherryChecker.play();
            timeline.setOnFinished(event -> {
                if(isReverse){
                    anchorPane.getChildren().remove(characterImageContainer);
                    anchorPane.getChildren().remove(myLine);
                    MapRenderer.exitMap(anchorPane);
                    EndLabel endLabel = new EndLabel();
                    endLabel.addEndLabel(anchorPane);
                }else {
                    MapRenderer.advanceMap(anchorPane);
                }
                cherryChecker.stop();
            });
            timeline.play();
        }
        else{
            anchorPane.getChildren().remove(myLine);
            KeyValue walk = new KeyValue(characterImageContainer.layoutXProperty(),MapRenderer.getTargetRectangle().getWidth()-imageWidth);
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(2),walk);
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(keyFrame);
            timeline.setOnFinished(event -> {
                Character.spawnStick(anchorPane);
                isWalking=false;});
            timeline.play();
        }
    }
    public static void walk(AnchorPane anchorPane){
        ArrayList<Double> lengthBounds = new ArrayList<>();
        lengthBounds.add(MapRenderer.getTargetRectangle().getLayoutX()-MapRenderer.getPivotRectangle().getWidth());
        lengthBounds.add(lengthBounds.get(0)+MapRenderer.getTargetRectangle().getWidth());
        double stickLength=-myLine.getStartX()+myLine.getEndX();
        if(lengthBounds.get(0)<=stickLength && lengthBounds.get(1)>= stickLength){
            Character.forwardWalk(true,anchorPane);
            Character.currScore++;
        }
        else{
            isWalking=true;
            KeyValue walk = new KeyValue(characterImageContainer.layoutXProperty(),myLine.getEndX());
            KeyFrame keyFrame = new KeyFrame(Duration.seconds(2),walk);
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(keyFrame);
            timeline.setOnFinished(event -> {Character.fall(anchorPane);});
            timeline.play();
        }
    }
    public static void upsideDown(){
        if(!(isWalking)){
            return;
        }
        isReverse=true;
        double new_ycord=565;
        characterImageContainer.setLayoutY(new_ycord);
        characterImageContainer.setImage(new Image("C:\\AP\\finalStickHeroSPACE\\finalStickHero\\src\\main\\resources\\com\\example\\finalstickhero\\inv_stickman.png"));
    }
    public static void downSideUp(){
        if(!(isWalking)){
            return;
        }
        isReverse=false;
        double new_ycord=565-imageHeight;
        characterImageContainer.setLayoutY(new_ycord);
        characterImageContainer.setImage(new Image("C:\\AP\\Stick_Hero\\src\\main\\resources\\com\\example\\stick_hero\\stickman.png"));
    }
    public static void scoreManager(){
        scoreKeeper.updateScoreCount();
    }
    public static void CherryManger(){
        scoreKeeper.updateCherryCount();
    }
    public static ImageView getCharacterImageContainer() {
        return characterImageContainer;
    }
    public static int getCurrScore() {
        return currScore;
    }
    public static void setScore(int currScore) {
        Character.currScore = currScore;
    }
    public static int getCherryCount() {
        return cherryCount;
    }
    public static void setCherryCount(int num) {
        Character.cherryCount=num;
    }
    public static boolean isWalking() {
        return isWalking;
    }
    public static boolean isReverse() {
        return isReverse;
    }
    public static void reset(int mode,AnchorPane anchorPane) {
        scoreKeeper.removeAll(anchorPane);
        if(mode==0) {
            try {
                anchorPane.getChildren().remove(myLine);
                anchorPane.getChildren().remove(characterImageContainer);
                MapRenderer.exitMap(anchorPane);
            } catch (Exception ignored) {

            }
        }
        else{
            Character.setCherryCount(0);
            Character.CherryManger();
            Character.setScore(0);
            Character.scoreManager();
            anchorPane.getChildren().remove(myLine);
            anchorPane.getChildren().remove(characterImageContainer);
            MapRenderer.exitMap(anchorPane);
        }

    }
}
