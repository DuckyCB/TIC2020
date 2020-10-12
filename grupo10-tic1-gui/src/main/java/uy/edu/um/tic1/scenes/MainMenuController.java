package uy.edu.um.tic1.scenes;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.tic1.JavaFxApplication;
import uy.edu.um.tic1.product.ProductRequest;
import uy.edu.um.tic1.product.Products;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("/uy/edu/um/tic1/scenes/mainMenu.fxml")
public class MainMenuController implements Initializable {

    @Autowired
    JavaFxApplication javaFxApplication;

    private Boolean user = Boolean.FALSE;

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
    private AnchorPane menuPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (user) {

            MenuButton menuUser = new MenuButton("Usuario");
            AnchorPane.setRightAnchor(menuUser, 15.0);
            menuUser.setLayoutY(13);
            menuPane.getChildren().add(menuUser);

        } else {

            Button logInButton = new Button("Ingresar");
            AnchorPane.setRightAnchor(logInButton, 15.0);
            logInButton.setLayoutY(13);
            menuPane.getChildren().add(logInButton);
            logInButton.setOnMouseClicked(event -> {
                javaFxApplication.sceneLogIn();
            });

        }

    }


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
    }

    public MainMenuController() {
    }

}
