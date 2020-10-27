package uy.edu.um.tic1.scenes.admin.store;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.tic1.StoreApplication;

@Component
@FxmlView("/uy/edu/um/tic1/scenes/admin/brand/adminProductStore.fxml")
public class AdminProductStoreController {

    @Autowired
    StoreApplication storeApplication;

    @FXML
    private Button inicio;

    @FXML
    private Button buttonNewProduct;
    @FXML
    private Button buttonProductManager;
    @FXML
    private TextField productTitle;
    @FXML
    private Label productBrand;
    @FXML
    private FlowPane paneColor;
    @FXML
    private Button buttonColorNew;
    @FXML
    private ScrollPane paneSizeStock;
    @FXML
    private Label pageTitle;


    @FXML
    void pressedNewProduct(ActionEvent event) {

    }

    @FXML
    void pressedProductManager(ActionEvent event) {

    }

    @FXML
    void inicioPressed(ActionEvent event) {
        storeApplication.sceneMain();
    }

    @FXML
    void pressedNewColour(ActionEvent event) {

    }

}
