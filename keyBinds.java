package com.example.finalstickhero;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class keyBinds implements EventHandler<KeyEvent> {   //singleTon design pattern

    private static final keyBinds instance = new keyBinds();

    private keyBinds() {
    }
    public static keyBinds getInstance() {       //getInstance method
        return instance;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        if(keyEvent.getCode()==KeyCode.SPACE && !Character.isWalking()){
            Character.enlargeStick();
        } else if (keyEvent.getCode()==KeyCode.SPACE && !Character.isReverse()) {
            Character.upsideDown();
        }
        else{
            Character.downSideUp();
        }

    }
}
