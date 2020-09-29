package uy.edu.um.tic1.categories;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.tic1.JavaFxApplication;

@Component
@FxmlView("/uy/edu/um/tic1/categories/hombreRemeras.fxml")
public class HombreRemerasController {

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
    void hombreBuzo(ActionEvent event) {
        javaFxApplication.hombreBuzos();
    }

    @FXML
    void hombrePantalon(ActionEvent event) {
        javaFxApplication.hombrePantalones();
    }

    @FXML
    void hombreRemera(ActionEvent event) {
    }

    @FXML
    void carritoPressed(ActionEvent event) {
    }

    @FXML
    void inicioPressed(ActionEvent event) {
        javaFxApplication.inicioPressed();
    }

    public HombreRemerasController() {
    }
}
