package uy.edu.um.tic1.scenes.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


import javafx.scene.shape.Circle;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.tic1.StoreApplication;
import uy.edu.um.tic1.entities.attributes.Colors;
import uy.edu.um.tic1.entities.attributes.Sizes;
import uy.edu.um.tic1.entities.products.Product;

import java.net.URL;
import java.util.ResourceBundle;


@Component
@FxmlView("/uy/edu/um/tic1/scenes/user/productDisplay.fxml")
public class ProductDisplayController implements Initializable {

    @Autowired
    StoreApplication storeApplication;

    private String selectedColor;
    private String selectedSize;
    private Integer selectedQuantity;

    public static Product product;

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

        productImage.setImage(new Image(product.getImage()));
        productName.setText(product.getName());
        productBrand.setText(product.getBrand());
        productPrice.setText(product.getPrice().toString());

        /*productImage.setImage(constImage);
        productName.setText(constName);
        productBrand.setText(constBrand);
        productPrice.setText(constPrice);*/

        circleColor.setVisible(false);
        labelSize.setVisible(false);
        labelQuantity.setVisible(false);

        // Toma una lista de talles disponibles del producto
        menuSize.getItems().clear();
        for (String size: Sizes.getListAdults()){

            MenuItem newItem = new MenuItem(size);
            newItem.setOnAction(event -> {
                resetSize();
                labelSize.setText(size);
                labelSize.setVisible(true);
                menuSize.setStyle("-fx-background-color: #e2e2e2");
                menuColor.setStyle("-fx-background-color: #cdcdcd");
                selectedSize = size;
                initColors();
            });
            menuSize.getItems().add(newItem);

        }

    }

    private void resetSize() {

        circleColor.setVisible(false);
        labelQuantity.setVisible(false);
        menuColor.setStyle("-fx-background-color: #ffffff");
        menuQuantity.setStyle("-fx-background-color: #ffffff");
        addToCart.setStyle("-fx-background-color: #ffffff");
        compare.setStyle("-fx-background-color: #ffffff");

    }

    private void resetColor() {

        labelQuantity.setVisible(false);
        menuQuantity.setStyle("-fx-background-color: #ffffff");
        addToCart.setStyle("-fx-background-color: #ffffff");
        compare.setStyle("-fx-background-color: #ffffff");

    }

    private void initColors() {

        // Toma lista de colores disponibles del producto
        menuColor.getItems().clear();
        for (String color: Colors.getAllListed()) {

            MenuItem newItem = new MenuItem("       ");
            newItem.setStyle("-fx-background-color: #"+color);
            newItem.setOnAction(event -> {
                resetColor();
                circleColor.setStyle("-fx-fill: #" + color);
                circleColor.setVisible(true);
                menuColor.setStyle("-fx-background-color: #e2e2e2");
                menuQuantity.setStyle("-fx-background-color: #cdcdcd");
                selectedColor = color;
                initQuantity();
            });
            menuColor.getItems().add(newItem);

        }

    }

    private void initQuantity() {

        // Cantidad de productos disponibles
        int quantity = 4;

        int number;
        if (quantity > 5) number = 5;
        else number = quantity;

        menuQuantity.getItems().clear();
        for (int i=1; i < number+1; i++) {

            MenuItem newItem = new MenuItem(Integer.toString(i));
            int finalI = i;
            newItem.setOnAction(event -> {
                labelQuantity.setText(Integer.toString(finalI));
                labelQuantity.setVisible(true);
                menuQuantity.setStyle("-fx-background-color: #e2e2e2");
                addToCart.setStyle("-fx-background-color: #cdcdcd");
                compare.setStyle("-fx-background-color: #e2e2e2");
                selectedQuantity = finalI;
            });
            menuQuantity.getItems().add(newItem);

        }

    }


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
    private MenuButton menuColor;
    @FXML
    private Label labelSize;
    @FXML
    private Label labelQuantity;
    @FXML
    private Circle circleColor;
    @FXML
    private Button compare;
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
