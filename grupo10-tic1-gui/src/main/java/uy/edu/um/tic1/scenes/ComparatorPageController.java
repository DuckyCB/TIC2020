package uy.edu.um.tic1.scenes;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.tic1.JavaFxApplication;
import uy.edu.um.tic1.product.ProductRequest;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("/uy/edu/um/tic1/scenes/comparatorPage.fxml")
public class ComparatorPageController implements Initializable {

    @Autowired
    JavaFxApplication javaFxApplication;

    public ComparatorPageController() {
    }
    
    private static Image imageA;
    private static String nameA;
    private static String brandA;
    private static String priceA;
    private static Image imageB;
    private static String nameB;
    private static String brandB;
    private static String priceB;

    public static void setImageA(Image image) {
        imageA = image;
    }
    public static void setNameA(String name) {
        nameA = name;
    }
    public static void setBrandA(String brand) {
        brandA = brand;
    }
    public static void setPriceA(String price) {
        priceA = price;
    }
    public static void setImageB(Image image) {
        imageB = image;
    }
    public static void setNameB(String name) {
        nameB = name;
    }
    public static void setBrandB(String brand) {
        brandB = brand;
    }
    public static void setPriceB(String price) {
        priceB = price;
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productImageA.setImage(imageA);
        productNameA.setText(nameA);
        productBrandA.setText(brandA);
        productPriceA.setText(priceA);
    }

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
    private Label productNameA;

    @FXML
    private Label productBrandA;

    @FXML
    private Label productPriceA;

    @FXML
    private ImageView productImageA;

    @FXML
    private Label productNameB;

    @FXML
    private Label productBrandB;

    @FXML
    private Label productPriceB;

    @FXML
    private ImageView productImageB;

    @FXML
    void hombreBuzo(ActionEvent event) {
        javaFxApplication.sceneListing(ProductRequest.listBuzos());
    }

    @FXML
    void hombrePantalon(ActionEvent event) {
        javaFxApplication.sceneListing(ProductRequest.listPants());
    }

    @FXML
    void hombreRemera(ActionEvent event) {
        javaFxApplication.sceneListing(ProductRequest.listShirts());
    }

    @FXML
    void carritoPressed(ActionEvent event) {
    }

    @FXML
    void inicioPressed(ActionEvent event) {
        javaFxApplication.sceneMainMenu();
    }

    void updateProductB() {
        productImageB.setImage(imageB);
        productNameB.setText(nameB);
        productBrandB.setText(brandB);
        productPriceB.setText(priceB);
    }

}
