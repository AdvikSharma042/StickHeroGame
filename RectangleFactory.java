package com.example.finalstickhero;
import javafx.scene.shape.Rectangle;
import java.util.Random;
public class RectangleFactory {
    private Rectangle makePivotRectangle(){
        double width = (new Random()).nextDouble(70,130);
        double layY = 565;
        double layX=0;
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(width);rectangle.setHeight(150);
        rectangle.setLayoutX(layX);rectangle.setLayoutY(layY);
        return rectangle;
    }
    private Rectangle makeTargetRectangle(Rectangle r1){
        double width = (new Random()).nextDouble(70,100);
        double layY = 565;
        double layX=(new Random()).nextDouble(r1.getWidth()+50,400);
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(width);rectangle.setHeight(150);
        rectangle.setLayoutX(layX);rectangle.setLayoutY(layY);
        return rectangle;
    }
    public Rectangle getRectangle(String type){
        if(type.equals("pivot")){
            return makePivotRectangle();
        }
        return null;
    }
    public Rectangle getRectangle(String type,Rectangle r1){
        if(type.equals("target")){
            return makeTargetRectangle(r1);
        }
        return null;
    }
}
