package com.example.finalstickhero;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class gameRunner extends Application {
    static Parent menuRoot;

    static {
        try {
            menuRoot = new FXMLLoader(gameRunner.class.getResource("menu_screen.fxml")).load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    static Parent playRoot;

    static {
        try {
            playRoot = new FXMLLoader(gameRunner.class.getResource("playScreen.fxml")).load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static Parent getMenuRoot() {
        return menuRoot;
    }

    public static Parent getPlayRoot() {
        return playRoot;
    }
    public static AnchorPane getPlayingPane(){
        Parent root = getPlayRoot();
        return (AnchorPane) root;
    }
    @Override
    public void start(Stage stage) {
        Scene scene = new Scene(menuRoot);
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
    }
    public static void main(String[] args) {
        launch();
    }
}
