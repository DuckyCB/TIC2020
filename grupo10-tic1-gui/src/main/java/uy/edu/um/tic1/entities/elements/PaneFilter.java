package uy.edu.um.tic1.entities.elements;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

public class PaneFilter {

    /**
     * Crea un Pane con el nombre enviado, para agregar a la lista de filtros
     * @param name Nombre de la categoria
     * @return Pane
     */
    public static Pane getPane(String name, String color) {

        Label label = new Label(name);
        label.setFont(Font.font("Cambria", 16));
        label.setLayoutX(23);
        label.setLayoutY(3);

        Pane pane = new Pane();
        pane.setPrefSize(200, 30);
        pane.getChildren().add(label);
        pane.setStyle("-fx-background-color: #"+color);

        return pane;

    }


}
