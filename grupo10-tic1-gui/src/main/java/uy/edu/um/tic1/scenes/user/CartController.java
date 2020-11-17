package uy.edu.um.tic1.scenes.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.tic1.StoreApplication;
import uy.edu.um.tic1.entities.elements.PaneProduct;
import uy.edu.um.tic1.entitites.cart.CartDTO;
import uy.edu.um.tic1.entitites.cart.CartItemDTO;
import uy.edu.um.tic1.entitites.product.ProductDTO;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;

@Component
@FxmlView("/uy/edu/um/tic1/scenes/user/cart.fxml")
public class CartController implements Initializable {

    @Autowired
    StoreApplication storeApplication;

    @FXML
    private FlowPane flowPaneProducts;

    @FXML
    private Button inicio;

    @FXML
    void inicioPressed(ActionEvent event) {
        storeApplication.sceneMain();
    }

    /**
     * En el caso que haya un usuario ingresado, hace un request al server para tener los productos del carrito.
     * Si no hay usuario, toma los productos en ram.
     * @return products Lista de productos dentro del carrito
     */
    private Set<CartItemDTO> requestCartList() {

        CartDTO cart = storeApplication.getCart();
        if (cart != null) return cart.getItems();
        else return null;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Set<CartItemDTO> cartItemDTO = requestCartList();

        if (cartItemDTO != null) {

            CartItemDTO[] cartItemList = cartItemDTO.toArray(new CartItemDTO[0]);

            float finalPrice = 0.0f;

            for (CartItemDTO cartItem : cartItemList) {

                ProductDTO product = cartItem.getProduct();

                byte[] image = product.getImage();
                Image productImg;
                if (image != null) {
                    productImg = new Image(new ByteArrayInputStream(image));
                } else {
                    productImg = new Image("/uy/edu/um/tic1/images/no_image.jpg");
                }

                Pane pane = PaneProduct.createCartItem(productImg, product.getName(), product.getBrand().getName(), product.getPrice().floatValue(),
                        cartItem.getSizeAndColor().getColor(), cartItem.getSizeAndColor().getSize());

                Pane close = new Pane();
                close.setPrefSize(50, 50);
                close.setLayoutX(587);
                close.setLayoutY(51);
                close.setStyle("-fx-background-color: #ff0000");
                close.setOnMouseClicked(event -> {
                    flowPaneProducts.getChildren().remove(pane);
                });
                pane.getChildren().add(close);

                flowPaneProducts.getChildren().add(pane);

                finalPrice += cartItem.getPrice().floatValue() * cartItem.getQuantity().floatValue();
            }

            Pane paneProduct = new Pane();
            paneProduct.setPrefSize(685, 150);
            paneProduct.setStyle("-fx-background-color: #cdcdcd");

            // PRICE LABEL
            Label labelPrice = new Label("Total:");
            labelPrice.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.ITALIC, 22));
            labelPrice.setLayoutX(316);
            labelPrice.setLayoutY(14);
            paneProduct.getChildren().add(labelPrice);

            // PRICE TOTAL
            StackPane stackPane = new StackPane();
            stackPane.setPrefSize(200, 40);
            stackPane.setLayoutX(243);
            stackPane.setLayoutY(46);
            Label totalPrice = new Label(finalPrice + " $UY");
            totalPrice.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.ITALIC, 22));
            stackPane.getChildren().add(totalPrice);
            paneProduct.getChildren().add(stackPane);

            // BUY BUTTON
            Button buy = new Button();
            buy.setText("COMPRAR");
            buy.setFont(Font.font("Cambria", FontWeight.BOLD, 20));
            buy.setLayoutX(282);
            buy.setLayoutY(98);
            buy.setOnMouseClicked(event -> {
                // TODO : scene buy details
            });
            paneProduct.getChildren().add(buy);

            flowPaneProducts.getChildren().add(paneProduct);

        } else {

            Label label = new Label("Carrito vacío ¯\\_(ツ)_/¯");
            label.setFont(Font.font("Cambria", FontWeight.BOLD, 32));
            label.setWrapText(true);

            StackPane paneProduct = new StackPane(label);
            paneProduct.setPrefSize(685, 150);
            paneProduct.setStyle("-fx-background-color: #cdcdcd");

            flowPaneProducts.getChildren().add(paneProduct);

        }

    }


}
