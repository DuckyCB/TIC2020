package uy.edu.um.tic1.entities.elements;

import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uy.edu.um.tic1.StoreApplication;
import uy.edu.um.tic1.entitites.BrandDTO;
import uy.edu.um.tic1.scenes.MainController;

import java.util.List;

public class PaneBrands {

    @Autowired
    private static MainController mainController;



    public static ScrollPane getScroll(List<BrandDTO> brands) {

        ScrollPane scroll = new ScrollPane();
        scroll.setPrefHeight(150);
        scroll.setMinSize(500, 150);
        scroll.setMaxHeight(150);

        FlowPane flow = new FlowPane();
        flow.setPrefHeight(135);
        flow.setMinHeight(135);
        flow.setMaxHeight(135);
        flow.setMinWidth(490);
        flow.setPrefWidth(1200);
        flow.setMaxWidth(Region.USE_COMPUTED_SIZE);
        flow.setVgap(10);
        flow.setHgap(10);

        for (BrandDTO brand: brands) {

            StackPane paneBackground = new StackPane();
            paneBackground.setStyle("-fx-background-color: #e2e2e2");
            paneBackground.setMinSize(135, 135);
            paneBackground.setPrefSize(135, 135);
            paneBackground.setMaxSize(135, 135);
            Label labelBrand = new Label(brand.getName());
            labelBrand.setFont(Font.font("Cambria", FontWeight.BOLD, 28));
            labelBrand.setWrapText(true);
            paneBackground.getChildren().add(labelBrand);
            paneBackground.setOnMouseClicked(event -> mainController.selectedBrand(brand));
            flow.getChildren().add(paneBackground);

        }

        scroll.setContent(flow);

        return scroll;


    }

}
