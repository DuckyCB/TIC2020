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
import uy.edu.um.tic1.entities.ProductFilters;
import uy.edu.um.tic1.entitites.SizeAndColorDTO;
import uy.edu.um.tic1.entitites.cart.CartDTO;
import uy.edu.um.tic1.entitites.cart.CartItemDTO;
import uy.edu.um.tic1.entitites.product.ProductDTO;
import uy.edu.um.tic1.entitites.product.TrousersDTO;
import uy.edu.um.tic1.requests.CartRestController;
import uy.edu.um.tic1.requests.ProductRestController;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;


@Component
@FxmlView("/uy/edu/um/tic1/scenes/user/sceneProductDisplay.fxml")
public class ProductDisplayController implements Initializable {

    @Autowired
    StoreApplication storeApplication;
    @Autowired
    private CartRestController cartRestController;
    @Autowired
    private ProductRestController productRestController;

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

    // ****************************************************************************************************************
    //                  INITIALIZE
    // ****************************************************************************************************************

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
                showColors();
            });
            menuSize.getItems().add(newItem);

        }

    }

    // ****************************************************************************************************************
    //                  BUTTONS FXML
    // ****************************************************************************************************************

    @FXML
    void inicioPressed(ActionEvent event) {
        storeApplication.sceneMain();
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
        CartDTO cart = storeApplication.getCart();
        cart.addItem(cartItem);
//        if(storeApplication.getAppUser() != null)
//            cartRestController.saveCurrentCart(storeApplication.getCart());
        storeApplication.setCart(cart);
        storeApplication.sceneCart();

    }

    @FXML
    void carritoPressed(ActionEvent event) {
        storeApplication.sceneCart();
    }

    @FXML
    void comparePressed(ActionEvent event) {

        SizeAndColorDTO sizeAndColorDTO = product.getSizeAndColorBySizeAndColor(selectedSize, selectedColor);
        CartItemDTO cartItem = CartItemDTO.builder().price(product.getPrice()).product(product).sizeAndColor(sizeAndColorDTO).quantity(selectedQuantity).build();
        if (storeApplication.getProductsToCompare() != null) storeApplication.getProductsToCompare().add(cartItem);
        else {
            List<CartItemDTO> newList = new LinkedList<>();
            newList.add(cartItem);
            storeApplication.setProductsToCompare(newList);
        }
        storeApplication.sceneCompare();

    }

    // ****************************************************************************************************************
    //                  SIZE AND COLOR
    // ****************************************************************************************************************

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

    private void showColors() {

        // Toma lista de colores disponibles del producto
        menuColor.getItems().clear();

        HashMap<String, String> uniqueColors = new HashMap<>();
        product.getSizeAndColor().stream().forEach(sc ->{
            if (sc.getSize().equals(selectedSize))
                uniqueColors.put(sc.getColor(), sc.getColor());
        });
        for (String color: uniqueColors.keySet()) {
            Integer max_stock = checkStock(product, selectedSize, color);
            if(max_stock > 0) {
                MenuItem newItem = new MenuItem("       ");
                newItem.setStyle("-fx-background-color: #" + color);
                newItem.setOnAction(event -> {
                    resetColor();
                    circleColor.setStyle("-fx-fill: #" + color);
                    circleColor.setVisible(true);
                    menuColor.setStyle("-fx-background-color: #e2e2e2");
                    menuQuantity.setStyle("-fx-background-color: #cdcdcd");
                    selectedColor = color;
                    initQuantity(max_stock);
                });
                menuColor.getItems().add(newItem);
            }

        }

    }

    private void initQuantity(Integer number) {


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

    public Integer checkStock(ProductDTO product, String size, String color){

        Integer max_stock = 0;

        ProductFilters productFilters = new ProductFilters();
        productFilters.setId(product.getId());


        productFilters.setSize(size);
        productFilters.setColor(color);
        List<ProductDTO> products = null;
        for (int i=1; i<=3; i++){
            productFilters.setStock(i);
            products = productRestController.getProducts(productFilters);
            if(!products.isEmpty()){
                max_stock = i;
            }
        }


        return max_stock;

    }

}
