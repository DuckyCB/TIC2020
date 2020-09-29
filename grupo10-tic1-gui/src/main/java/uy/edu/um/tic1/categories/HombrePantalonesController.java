package uy.edu.um.tic1.categories;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.tic1.JavaFxApplication;

@Component
@FxmlView("/uy/edu/um/tic1/categories/hombrePantalones.fxml")
public class HombrePantalonesController {

    @Autowired
    JavaFxApplication javaFxApplication;

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
    private Button compareJeanBlack;

    @FXML
    private Button compareJeanBlue;

    @FXML
    void hombreBuzo(ActionEvent event) {
        javaFxApplication.hombreBuzos();
    }

    @FXML
    void hombrePantalon(ActionEvent event) {
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

    @FXML
    void jeanBlackPressed(MouseEvent event) {
        javaFxApplication.productPage("/uy/edu/um/tic1/images/Men/Jean/black.jpg", "Jean Black", "Levi's", "499.99 $UY");
    }

    @FXML
    void compareJeanBlackPressed(ActionEvent event) {
        javaFxApplication.comparator("/uy/edu/um/tic1/images/Men/Jean/black.jpg", "Jean Black", "Levi's", "499.99 $UY", true);
    }

    @FXML
    void compareJeanBluePressed(ActionEvent event) {
        javaFxApplication.comparator("/uy/edu/um/tic1/images/Men/Jean/blue.jpg", "Jean Blue", "Levi's", "449.99 $UY", false);
    }

    public HombrePantalonesController() {
    }
}
