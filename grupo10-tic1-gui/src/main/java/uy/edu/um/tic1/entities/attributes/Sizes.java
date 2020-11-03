package uy.edu.um.tic1.entities.attributes;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Sizes {

    private static final String[] listAdults = new String[]{"XS", "S", "M", "L", "XL", "XXL"};
    private static final String[] listKids = new String[]{"XXS", "S", "M", "L", "XL"};

    public static String[] getListAdults() {
        return listAdults;
    }
    public static String[] getListKids() {
        return listKids;
    }

    public static Pane getPane(String size) {

        Label label = new Label(size);
        label.setFont(Font.font("Cambria", FontWeight.BOLD, 16));
        label.setLayoutX(6);
        label.setLayoutY(3);

        StackPane pane = new StackPane();
        pane.setPrefSize(32, 32);
        pane.getChildren().add(label);
        pane.setStyle("-fx-background-color: #e2e2e2");

        return pane;

    }

}
