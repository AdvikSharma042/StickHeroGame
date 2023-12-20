package com.example.finalstickhero;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;


public class playController {
    public void switch2HomeScreen(ActionEvent event){
        if(Character.isWalking()) return;
        Character.reset(1,gameRunner.getPlayingPane());
        Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
        Scene menuScene = ((Node)event.getSource()).getScene();
        menuScene.setRoot(gameRunner.getMenuRoot());
        stage.setScene(menuScene);
        stage.show();
    }

}
