package uy.edu.um.tic1.scenes.admin.brand;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.tic1.JavaFxApplication;
import uy.edu.um.tic1.entity.PaneProduct;
import uy.edu.um.tic1.product.ProductRequest;
import uy.edu.um.tic1.product.Products;
import uy.edu.um.tic1.scenes.ProductDisplayController;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("/uy/edu/um/tic1/scenes/admin/brand/adminBrand.fxml")
public class AdminBrandController implements Initializable {

    @Autowired
    JavaFxApplication javaFxApplication;

    @FXML
    private Button inicio;
    @FXML
    private Button buttonNewProduct;
    @FXML
    private Button buttonProductManager;
    @FXML
    private Button buttonNewStore;
    @FXML
    private Button buttonStoreManager;

    @FXML
    private FlowPane flowPaneProducts;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (ProductRequest.productsList != null) {

            for (Products product : ProductRequest.productsList) {

                Pane pane = PaneProduct.paneGeneric(product.getImage(), product.getName(), product.getBrand(), product.getPrice(), product.getColors(), product.getSizes());
                // Por aca no se si cambiar las cosas y directamente referenciar la prenda con su id en lugar de tomar uno a uno los atributos
                pane.setOnMouseClicked(event -> {
                    javaFxApplication.sceneAdminBrandProduct(product);
                });

                flowPaneProducts.setVgap(5);
                flowPaneProducts.setHgap(5);
                flowPaneProducts.setPadding(new Insets(5, 5, 5, 5));
                flowPaneProducts.getChildren().add(pane);

            }

        }

    }


    @FXML
    void pressedNewProduct(ActionEvent event) {

    }

    @FXML
    void pressedNewStore(ActionEvent event) {

    }

    @FXML
    void pressedProductManager(ActionEvent event) {

    }

    @FXML
    void pressedStoreManager(ActionEvent event) {

    }


    @FXML
    void inicioPressed(ActionEvent event) {
        javaFxApplication.sceneMainMenu();
    }


}
