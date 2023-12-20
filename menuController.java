package com.example.finalstickhero;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
public class menuController {
    public void loadGame(){
        MapRenderer.firstRender(gameRunner.getPlayingPane(),1);
    }
    public void loadSavedGame(ActionEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene playscene = ((Node) event.getSource()).getScene();
        playscene.setRoot(gameRunner.getPlayRoot());
        gameRunner.getPlayRoot().requestFocus();
        playscene.setOnKeyPressed(keyBinds.getInstance());
        playscene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.SPACE && !(Character.isWalking())) {
                afterLoad();
            }
        });
        stage.setScene(playscene);
        stage.show();
        MapRenderer.firstRender(gameRunner.getPlayingPane(),0);
    }

    public void afterLoad(){
        Character.rotateStick();
    }
    public void switch2PlayScreen(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene playscene = ((Node) event.getSource()).getScene();
        playscene.setRoot(gameRunner.getPlayRoot());
        gameRunner.getPlayRoot().requestFocus();
        playscene.setOnKeyPressed(keyBinds.getInstance());
        playscene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.SPACE && !(Character.isWalking())) {
                afterLoad();
            }
        });
        stage.setScene(playscene);
        stage.show();
        loadGame();
    }
}
