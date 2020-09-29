package uy.edu.um.tic1.scenes;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.tic1.JavaFxApplication;
import uy.edu.um.tic1.product.Products;

import java.net.URL;
import java.util.EmptyStackException;
import java.util.ResourceBundle;

@Component
@FxmlView("/uy/edu/um/tic1/scenes/productListing.fxml")
public class ProductListingController implements Initializable {

    @Autowired
    JavaFxApplication javaFxApplication;

    public ProductListingController() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        boolean vacio = false;
        Products product = null;
        while (!vacio) {
            try {
                product = Products.getProducts().pop();
            } catch (EmptyStackException e) {
                vacio = true;
            }
            if (!vacio) {

                sceneName.setText(Products.getProductsCategory());

                ImageView productImage = product.getImage();
                productImage.setFitWidth(225);
                productImage.setFitHeight(300);
                productImage.setLayoutX(15);
                productImage.setLayoutY(10);

                Label productName = new Label(product.getName());
                productName.setFont(Font.font("Arial", FontWeight.BOLD, 28));
                productName.setLayoutX(255);
                productName.setLayoutY(70);
                productName.setWrapText(true);

                Label productBrand = new Label(product.getBrand());
                productBrand.setFont(Font.font("Arial", FontPosture.ITALIC, 18));
                productBrand.setLayoutX(255);
                productBrand.setLayoutY(100);

                Label productPrice = new Label(product.getPrice());
                productPrice.setFont(Font.font("Arial", 28));
                productPrice.setLayoutX(340);
                productPrice.setLayoutY(185);

                Button compareButton = new Button("Comparar");
                compareButton.setLayoutX(340);
                compareButton.setLayoutY(230);

                AnchorPane productPane = new AnchorPane();
                productPane.setPrefSize(500, 320);
                productPane.setMinSize(500, 320);
                productPane.setMaxSize(500, 320);
                productPane.setStyle("-fx-background-color: #e2e2e2");
                productPane.getChildren().add(productImage);
                productPane.getChildren().add(productName);
                productPane.getChildren().add(productBrand);
                productPane.getChildren().add(productPrice);
                productPane.getChildren().add(compareButton);
                productPane.setOnMouseClicked(event -> {
                    ProductDisplayController.setConstImage(productImage.getImage());
                    ProductDisplayController.setConstName(productName.getText());
                    ProductDisplayController.setConstBrand(productBrand.getText());
                    ProductDisplayController.setConstPrice(productPrice.getText());
                    javaFxApplication.productDisplayPage(productName.getText());
                });

                //flowPaneListing.setPadding(new Insets(5,5,5,5));
                flowPaneListing.setVgap(5);
                flowPaneListing.setHgap(5);
                flowPaneListing.getChildren().add(productPane);

            }

        }
    }

    @FXML
    private FlowPane flowPaneListing;

    @FXML
    private MenuItem hombreRemeraButton;

    @FXML
    private MenuItem hombrePantalonButton;

    @FXML
    private MenuItem hombreBuzoButton;

    @FXML
    private Button inicio;

    @FXML
    private Button carrito;

    @FXML
    private Label sceneName;

    @FXML
    void hombreBuzo(ActionEvent event) {
        javaFxApplication.hombreBuzos();
    }

    @FXML
    void hombrePantalon(ActionEvent event) {
        javaFxApplication.hombrePantalones();
    }

    @FXML
    void hombreRemera(ActionEvent event) {
        javaFxApplication.hombreRemeras();
    }

    @FXML
    void inicioPressed(ActionEvent event) {
    }

    @FXML
    void carritoPressed(ActionEvent event) {
    }

}
