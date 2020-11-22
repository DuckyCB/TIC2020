package uy.edu.um.tic1.scenes.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.tic1.StoreApplication;
import uy.edu.um.tic1.entities.elements.PaneProduct;
import uy.edu.um.tic1.entitites.cart.CartItemDTO;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("/uy/edu/um/tic1/scenes/user/sceneCompare.fxml")
public class CompareController implements Initializable {

    @Autowired
    StoreApplication storeApplication;

    @FXML
    private Button home;
    @FXML
    private Button cart;
    @FXML
    private FlowPane flowPaneProducts;

    // ****************************************************************************************************************
    //                  INITIALIZE
    // ****************************************************************************************************************

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (storeApplication.getProductsToCompare() != null) {

            for (CartItemDTO product: storeApplication.getProductsToCompare()) {

                Pane newProductPane = PaneProduct.getComparatedItem(product);

                Button close = new Button("X");
                close.setFont(Font.font("Cambria", FontWeight.BOLD, 15));
                close.setStyle("-fx-background-color: #ff7373");
                close.setLayoutX(107);
                close.setLayoutY(593);
                close.setOnAction(event -> flowPaneProducts.getChildren().remove(newProductPane));
                newProductPane.getChildren().add(close);

                flowPaneProducts.getChildren().add(newProductPane);

            }

        }

    }

    // ****************************************************************************************************************
    //                  BUTTONS FXML
    // ****************************************************************************************************************

    @FXML
    void pressedCart(ActionEvent event) {
        storeApplication.sceneCart();
    }

    @FXML
    void pressedHome(ActionEvent event) {
        storeApplication.sceneMain();
    }

}
