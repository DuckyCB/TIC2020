package uy.edu.um.tic1.scenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.SwipeEvent;
import javafx.scene.layout.Pane;


import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.tic1.JavaFxApplication;
import uy.edu.um.tic1.product.Products;

import java.net.URL;
import java.util.ResourceBundle;


@Component
@FxmlView("/uy/edu/um/tic1/scenes/productDisplay.fxml")
public class ProductDisplayController implements Initializable {

    @Autowired
    JavaFxApplication javaFxApplication;

    private static Image constImage;
    private static String constName;
    private static String constBrand;
    private static String constPrice;

    public static void setConstImage(Image image) {
        constImage = image;
    }
    public static void setConstName(String name) {
        constName = name;
    }
    public static void setConstBrand(String brand) {
        constBrand = brand;
    }
    public static void setConstPrice(String price) {
        constPrice = price;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productImage.setImage(constImage);
        productName.setText(constName);
        productBrand.setText(constBrand);
        productPrice.setText(constPrice);
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    private Products product;

    @FXML
    private Button inicio;
    @FXML
    private Button carrito;
    @FXML
    private MenuItem hombreRemeraButton;
    @FXML
    private MenuItem hombrePantalonButton;
    @FXML
    private MenuItem hombreBuzoButton;

    @FXML
    private ImageView productImage;
    @FXML
    private Label productName;
    @FXML
    private Label productBrand;
    @FXML
    private Label productPrice;
    @FXML
    private MenuButton quantity;
    @FXML
    private TableView table;
    @FXML
    private Button addToCart;
    @FXML
    private Button compare;
    @FXML
    private MenuButton size;
    @FXML
    private Label description;
    @FXML
    private Pane MiniProductDisplay1;
    @FXML
    private Pane MiniProductDisplay0;
    @FXML
    private Pane MiniProductDisplay2;
    @FXML
    void addToCart(MouseEvent event) {
    }
    @FXML
    void addToCompare(MouseEvent event) {
    }
    @FXML
    void nextImage(SwipeEvent event) {
    }
    @FXML
    void previousImage(SwipeEvent event) {
    }
    @FXML
    void zoomImage(MouseEvent event) {
    }


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
        javaFxApplication.inicioPressed();
    }


}
