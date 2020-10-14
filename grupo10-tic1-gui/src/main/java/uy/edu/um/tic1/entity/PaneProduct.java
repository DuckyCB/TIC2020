package uy.edu.um.tic1.entity;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class PaneProduct {

    public static Pane paneGeneric(String image, String name, String brand, Float price, ColorRGB[] colors, String[] sizes) {

        Pane paneProduct = new Pane();
        paneProduct.setPrefSize(500, 320);
        paneProduct.setMinSize(500, 320);
        paneProduct.setMaxSize(500, 320);
        paneProduct.setStyle("-fx-background-color: #e2e2e2");

        // IMAGE
        ImageView productImage = new ImageView(image);
        productImage.setFitWidth(225);
        productImage.setFitHeight(300);
        productImage.setLayoutX(15);
        productImage.setLayoutY(10);
        paneProduct.getChildren().add(productImage);

        // NAME
        StackPane paneName = new StackPane();
        paneName.setPrefSize(235, 70);
        paneName.setLayoutX(250);
        paneName.setLayoutY(10);
        Label productName = new Label(name);
        productName.setFont(Font.font("Cambria", FontWeight.BOLD, 28));
        productName.setWrapText(true);
        paneName.getChildren().add(productName);
        paneProduct.getChildren().add(paneName);

        // BRAND
        Label productBrand = new Label(brand);
        productBrand.setFont(Font.font("Cambria", FontPosture.ITALIC, 24));
        productBrand.setLayoutX(250);
        productBrand.setLayoutY(75);
        paneProduct.getChildren().add(productBrand);

        // PRICE
        StackPane panePrice = new StackPane();
        panePrice.setPrefSize(200, 45);
        panePrice.setLayoutX(268);
        panePrice.setLayoutY(110);
        Label productPrice = new Label(price.toString()+" $UY");
        productPrice.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.ITALIC, 28));
        panePrice.getChildren().add(productPrice);
        paneProduct.getChildren().add(panePrice);

        // SIZE
        FlowPane flowPaneSize = new FlowPane();
        flowPaneSize.setPrefSize(235, 35);
        flowPaneSize.setLayoutX(250);
        flowPaneSize.setLayoutY(177);
        flowPaneSize.setVgap(5);
        flowPaneSize.setHgap(5);
        flowPaneSize.setPadding(new Insets(3, 3, 3, 3));

        for (String size: sizes) {

            StackPane paneSize = new StackPane();
            paneSize.setPrefSize(32,32);
            paneSize.setStyle("-fx-background-color: #cdcdcd");
            /*paneSize.setBackground(new Background(new BackgroundFill
                    (new Color(0.8, 0.8, 0.8, 1.0), CornerRadii.EMPTY, Insets.EMPTY)));*/
            Label sizeLetter = new Label(size);
            sizeLetter.setFont(Font.font("Cambria", FontWeight.BOLD, 26));
            paneSize.getChildren().add(sizeLetter);
            flowPaneSize.getChildren().add(paneSize);

        }

        paneProduct.getChildren().add(flowPaneSize);

        // COLORS
        FlowPane flowPaneColors = new FlowPane();
        flowPaneColors.setPrefSize(235, 68);
        flowPaneColors.setLayoutX(250);
        flowPaneColors.setLayoutY(235);
        flowPaneColors.setVgap(5);
        flowPaneColors.setHgap(5);
        flowPaneColors.setPadding(new Insets(3, 3, 3, 3));

        for (ColorRGB rgb: colors) {

            Circle circle = new Circle();
            circle.setRadius(16.0f);
            Color color = new Color(rgb.getR(), rgb.getG(), rgb.getB(), 1.0);
            circle.setFill(color);
            flowPaneColors.getChildren().add(circle);

        }

        paneProduct.getChildren().add(flowPaneColors);

        return paneProduct;

    }


}
