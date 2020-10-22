package uy.edu.um.tic1.entity;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Colors {

    private static final String white = "FDFEFE";
    private static final String black = "17202A";
    private static final String brown = "6E2C00";
    private static final String purple = "6C3483";
    private static final String blue = "154360";
    private static final String lightBlue = "3498DB";
    private static final String darkGreen = "145A32";
    private static final String lightGreen = "58D68D";
    private static final String yellow = "F4D03F";
    private static final String orange = "E67E22";
    private static final String red = "E74C3C";
    private static final String pink = "F5B7B1";
    private static final String grey = "5D6D7E";

    public static String[] getAllListed() {
        return new String[]{white, black, brown, purple, blue, lightBlue, darkGreen, lightGreen,
        yellow, orange, red, pink, grey};
    }

    public static Circle getCircle(String color, Float radius) {

        Circle circle = new Circle();
        circle.setRadius(radius);
        circle.setStyle("-fx-background-color: #" + color);
        //circle.setFill(new Color(color.getR(), color.getG(), color.getB(), 1.0));
        return circle;

    }

}
