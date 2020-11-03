package uy.edu.um.tic1.entities.attributes;

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

    public static String getWhite() {
        return white;
    }
    public static String getBlack() {
        return black;
    }
    public static String getBrown() {
        return brown;
    }
    public static String getPurple() {
        return purple;
    }
    public static String getBlue() {
        return blue;
    }
    public static String getLightBlue() {
        return lightBlue;
    }
    public static String getDarkGreen() {
        return darkGreen;
    }
    public static String getLightGreen() {
        return lightGreen;
    }
    public static String getYellow() {
        return yellow;
    }
    public static String getOrange() {
        return orange;
    }
    public static String getRed() {
        return red;
    }
    public static String getPink() {
        return pink;
    }
    public static String getGrey() {
        return grey;
    }

    public static String[] getAllListed() {
        return new String[]{white, black, brown, purple, blue, lightBlue, darkGreen, lightGreen,
        yellow, orange, red, pink, grey};
    }

    public static Circle getCircle(String color, Float radius) {

        Circle circle = new Circle();
        circle.setRadius(radius);
        circle.setStyle("-fx-fill: #" + color);
        return circle;

    }

}
