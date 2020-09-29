package uy.edu.um.tic1.product;


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

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("/uy/edu/um/tic1/product/productPage.fxml")
public class ProductPageController implements Initializable {

    @Autowired
    JavaFxApplication javaFxApplication;

    public ProductPageController() {
    }

    // Estos valores determinan lo que se va a mostrar en Product Page
    private Image constImage;
    private String constName;
    private String constBrand;
    private String constPrice;

    public void setConstImage(Image constImage) {
        this.constImage = constImage;
    }
    public void setConstName(String constName) {
        this.constName = constName;
    }
    public void setConstBrand(String constBrand) {
        this.constBrand = constBrand;
    }
    public void setConstPrice(String constPrice) {
        this.constPrice = constPrice;
    }

    // Esta funcion determina las cosas al inicio de la carga del Stage
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        productImage.setImage(constImage);
        productName.setText(constName);
        productBrand.setText(constBrand);
        productPrice.setText(constPrice);
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
    private Label productName;

    @FXML
    private Label productBrand;

    @FXML
    private Label productPrice;

    @FXML
    private ImageView productImage;

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
    void carritoPressed(ActionEvent event) {
    }

    @FXML
    void inicioPressed(ActionEvent event) {
        javaFxApplication.inicioPressed();
    }

}
