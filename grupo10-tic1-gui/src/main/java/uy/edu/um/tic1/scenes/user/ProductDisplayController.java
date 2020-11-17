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
import uy.edu.um.tic1.entitites.SizeAndColorDTO;
import uy.edu.um.tic1.entitites.cart.CartItemDTO;
import uy.edu.um.tic1.entitites.product.ProductDTO;
import uy.edu.um.tic1.requests.CartRestController;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;


@Component
@FxmlView("/uy/edu/um/tic1/scenes/user/productDisplay.fxml")
public class ProductDisplayController implements Initializable {

    @Autowired
    StoreApplication storeApplication;
    @Autowired
    private CartRestController cartRestController;

    private String selectedColor;
    private String selectedSize;
    private Integer selectedQuantity;

    public static ProductDTO product;

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        byte[] image = product.getImage();
        Image  productImg;
        if (image != null){
            productImg = new Image(new ByteArrayInputStream(image));
        }else{
            productImg = new Image("/uy/edu/um/tic1/images/no_image.jpg");
        }
        productImage.setImage(productImg);
        productName.setText(product.getName());
        productBrand.setText(product.getBrand().getName());
        productPrice.setText(product.getPrice().toString());

        circleColor.setVisible(false);
        labelSize.setVisible(false);
        labelQuantity.setVisible(false);

        // Toma una lista de talles disponibles del producto
        menuSize.getItems().clear();

        HashMap<String, String> uniqueSize = new HashMap<>();
        product.getSizeAndColor().stream().forEach(sc ->{
            uniqueSize.put(sc.getSize(), sc.getSize());
        });

        for (String size: uniqueSize.keySet()){

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

        HashMap<String, String> uniqueColors = new HashMap<>();
        product.getSizeAndColor().stream().forEach(sc ->{
            if (sc.getSize().equals(selectedSize))
                uniqueColors.put(sc.getColor(), sc.getColor());
        });
        for (String color: uniqueColors.keySet()) {

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

            //TODO: Chequear que hay stock. Se hace haciendo una consulta que filtre este producto con
            //      la variable stock=selectQuantity

            menuQuantity.getItems().add(newItem);

        }

    }

    /**
     * Crea una instancia de
     * @see CartItemDTO Crea una instancia de CartItemDTO y la añade al carrito.
     * @see #storeApplication
     */
    @FXML
    void addToCartPressed(ActionEvent event) {


        SizeAndColorDTO sizeAndColorDTO = product.getSizeAndColorBySizeAndColor(selectedSize, selectedColor);
        CartItemDTO cartItem = CartItemDTO.builder().price(product.getPrice()).product(product).sizeAndColor(sizeAndColorDTO).quantity(selectedQuantity).build();
        storeApplication.getCart().addItem(cartItem);
        cartRestController.saveCurrentCart(storeApplication.getCart());

    }

    @FXML
    void carritoPressed(ActionEvent event) {
        storeApplication.sceneCart();
    }

    @FXML
    void comparePressed(ActionEvent event) {
        System.out.println("En construccion");
    }

    @FXML
    void inicioPressed(ActionEvent event) {
        storeApplication.sceneMain();
    }


}
