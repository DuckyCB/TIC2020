package uy.edu.um.tic1.scenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.SwipeEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;


import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.tic1.JavaFxApplication;
import uy.edu.um.tic1.product.Products;


@Component
@FxmlView("productDisplay.fxml")
public class ProductDisplayController {

    @Autowired
    JavaFxApplication javaFxApplication;

    public void setProduct(Products product) {
        this.product = product;
    }

    private Products product;

    @FXML
    private MenuItem hombreRemeraButton;

    @FXML
    private MenuItem hombrePantalonButton;

    @FXML
    private MenuItem hombreBuzoButton;

    @FXML
    private Button carrito;

    @FXML
    private Button inicio;

    @FXML
    private MenuButton size;
    @FXML
    private ImageView image;
    @FXML
    private Label productName;
    @FXML
    private MenuButton quantity;
    @FXML
    private TableView table;
    @FXML
    private Button addToCart;
    @FXML
    private Button compare;
    @FXML
    private Label price;
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


    public void start() {
        productName.setText(product.getName());
        price.setText(product.getPrice().toString());
    }
}
