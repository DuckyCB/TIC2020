package uy.edu.um.tic1.scenes.admin.brand;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.tic1.StoreApplication;
import uy.edu.um.tic1.entity.ColorRGB;
import uy.edu.um.tic1.product.Products;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("/uy/edu/um/tic1/scenes/admin/brand/adminProductBrand.fxml")
public class AdminProductBrandController implements Initializable {

    @Autowired
    StoreApplication storeApplication;

    private static Products product;

    public static void setProduct(Products product) {
        AdminProductBrandController.product = product;
    }

    @FXML
    private Button inicio;
    @FXML
    private Button buttonNewProduct;
    @FXML
    private Button buttonProductManager;

    @FXML
    private TextField productTitle;
    @FXML
    private Label productBrand;
    @FXML
    private FlowPane flowPaneColors;
    @FXML
    private Label pageTitle;
    @FXML
    private TextField productPrice;
    @FXML
    private ImageView productImage;
    @FXML
    private Pane productNewImage;


    @FXML
    void pressedNewProduct(ActionEvent event) {

    }

    @FXML
    void pressedProductManager(ActionEvent event) {

    }

    @FXML
    void inicioPressed(ActionEvent event) {
        storeApplication.sceneMain();
    }

    @FXML
    void pressedNewColour(ActionEvent event) {

    }

    @FXML
    void droppedNewImage(DragEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        productTitle.setText(product.getName());
        productBrand.setText(product.getBrand());
        productPrice.setText(String.valueOf(product.getPrice()));
        pageTitle.setText(product.getName());
        productImage.setImage(new Image(product.getImage()));

        for (ColorRGB rgb: product.getColors()) {

            Pane paneColor = new Pane();
            paneColor.setPrefSize(565, 60);
            paneColor.setStyle("-fx-background-color: #cdcdcd");

            Circle circle = new Circle();
            circle.setRadius(16.0f);
            Color color = new Color(rgb.getR(), rgb.getG(), rgb.getB(), 1.0);
            circle.setFill(color);
            circle.setLayoutX(30);
            circle.setLayoutY(30);
            paneColor.getChildren().add(circle);

            Label colorName = new Label("Color");
            colorName.setFont(Font.font("Cambria",15));
            colorName.setLayoutX(52);
            colorName.setLayoutY(20);
            paneColor.getChildren().add(colorName);

            FlowPane flowPaneSizes = new FlowPane();
            flowPaneSizes.setHgap(5);
            flowPaneSizes.setVgap(5);
            flowPaneSizes.setPrefSize(456, 41);
            flowPaneSizes.setPadding(new Insets(3, 3, 3, 3));
            flowPaneSizes.setLayoutX(95);
            flowPaneSizes.setLayoutY(10);
            flowPaneSizes.setStyle("-fx-background-color: #e2e2e2");
            paneColor.getChildren().add(flowPaneSizes);

            for (String size: product.getSizes()) {

                //StackPane paneSize = new StackPane();
                Pane paneSize = new Pane();
                paneSize.setPrefSize(60,34);
                paneSize.setStyle("-fx-background-color: #cdcdcd");
                /*paneSize.setBackground(new Background(new BackgroundFill
                        (new Color(0.8, 0.8, 0.8, 1.0), CornerRadii.EMPTY, Insets.EMPTY)));*/

                Label sizeLetter = new Label(size);
                sizeLetter.setFont(Font.font("Cambria", 25));
                sizeLetter.setLayoutX(2);
                sizeLetter.setLayoutY(2);
                paneSize.getChildren().add(sizeLetter);

                Pane close = new Pane();
                close.setPrefSize(25, 25);
                close.setLayoutX(34);
                close.setLayoutY(5);
                close.setStyle("-fx-background-color: #ff0000");

                Line a = new Line();
                a.setStartX(0);
                a.setStartY(0);
                a.setEndX(15);
                a.setEndY(15);
                a.setLayoutX(5);
                a.setLayoutY(5);
                a.setStrokeWidth(5);
                close.getChildren().add(a);

                Line b = new Line();
                b.setStartX(0);
                b.setStartY(15);
                b.setEndX(15);
                b.setEndY(0);
                b.setLayoutX(5);
                b.setLayoutY(5);
                b.setStrokeWidth(5);
                close.getChildren().add(b);

                close.setOnMouseClicked(event -> {
                    flowPaneSizes.getChildren().remove(paneSize);
                });

                paneSize.getChildren().add(close);
                flowPaneSizes.getChildren().add(paneSize);

            }


            flowPaneColors.getChildren().add(paneColor);


        }




    }

}
