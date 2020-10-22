package uy.edu.um.tic1.scenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.tic1.StoreApplication;
import uy.edu.um.tic1.product.ProductRequest;
import uy.edu.um.tic1.product.Products;

import java.net.URL;
import java.util.ResourceBundle;


@Component
@FxmlView("/uy/edu/um/tic1/scenes/productDisplay.fxml")
public class ProductDisplayController implements Initializable {

    @Autowired
    StoreApplication storeApplication;

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
    private AnchorPane backgroundPane;
    @FXML
    private Label productBrand;
    @FXML
    private ImageView productImage;
    @FXML
    private Label productName;
    @FXML
    private Label productPrice;
    @FXML
    private Button addToCart;
    @FXML
    private MenuButton menuQuantity;
    @FXML
    private MenuButton menuSize;
    @FXML
    private Button compare;
    @FXML
    private Label productDescription;
    @FXML
    private Button inicio;
    @FXML
    private Button carrito;

    @FXML
    void addToCartPressed(ActionEvent event) {

    }

    @FXML
    void carritoPressed(ActionEvent event) {

    }

    @FXML
    void comparePressed(ActionEvent event) {

    }

    @FXML
    void inicioPressed(ActionEvent event) {
        storeApplication.sceneMain();
    }


}
