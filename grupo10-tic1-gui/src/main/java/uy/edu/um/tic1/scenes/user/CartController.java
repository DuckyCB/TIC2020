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
import uy.edu.um.tic1.entitites.SizeAndColorDTO;
import uy.edu.um.tic1.entitites.cart.CartDTO;
import uy.edu.um.tic1.entitites.cart.CartItemDTO;
import uy.edu.um.tic1.entitites.cart.PurchaseDTO;
import uy.edu.um.tic1.entitites.cart.PurchaseItemDTO;
import uy.edu.um.tic1.entitites.product.ProductDTO;
import uy.edu.um.tic1.entitites.users.AppUserDTO;
import uy.edu.um.tic1.entitites.users.ClientDTO;
import uy.edu.um.tic1.entitites.users.StoreUserDTO;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Set;

@Component
@FxmlView("/uy/edu/um/tic1/scenes/user/cart.fxml")
public class CartController implements Initializable {

    @Autowired
    StoreApplication storeApplication;

    AppUserDTO user;

    @FXML
    private FlowPane flowPaneProducts;

    @FXML
    private Button inicio;

    @FXML
    void inicioPressed(ActionEvent event) {
        storeApplication.sceneMain();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        user = storeApplication.getAppUser();
        if (user instanceof ClientDTO) setProducts(Objects.requireNonNull(requestCartList()).toArray());
        else if (user instanceof StoreUserDTO) setStoreSales();
        else setProducts(Objects.requireNonNull(requestCartList()).toArray());

    }

    private Label getQuantityLabel(String quantity) {

        Label numberQuantity = new Label(quantity);
        numberQuantity.setFont(Font.font("Cambria", FontWeight.BOLD, 32));
        numberQuantity.setLayoutX(513);
        numberQuantity.setLayoutY(87);
        return numberQuantity;

    }

    // ****************************************************************************************************************
    //                  USER
    // ****************************************************************************************************************

    private void setUserCart() {

        Set<CartItemDTO> cartItemSet = requestCartList();

        if (cartItemSet != null) {

            float finalPrice = 0.0f;

            for (CartItemDTO cartItem : cartItemSet) {

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

                Label numberQuantity = getQuantityLabel(cartItem.getQuantity().toString());
                pane.getChildren().add(numberQuantity);

                Pane close = new Pane();
                close.setPrefSize(40, 40);
                close.setLayoutX(625);
                close.setLayoutY(56);
                close.setStyle("-fx-background-color: #ff0000");
                close.setOnMouseClicked(event -> {
                    cartItem.setQuantity(cartItem.getQuantity()-1);
                    if (cartItem.getQuantity() == 0) {
                        pane.getChildren().remove(numberQuantity);
                        pane.getChildren().add(getQuantityLabel(cartItem.getQuantity().toString()));
                    } else {
                        flowPaneProducts.getChildren().remove(pane);
                        cartItemSet.remove(cartItem);
                    }
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

        } else flowPaneProducts.getChildren().add(getNoMesaggesPane("Carrito vacío"));

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

    // ****************************************************************************************************************
    //                  STORE
    // ****************************************************************************************************************

    private void setStoreSales() {

        //TODO : recuperar lista de purchases

        List<PurchaseDTO> sales = null;

        //if (!sales.isEmpty()) {
        if (sales != null) {

            for (PurchaseDTO sale : sales) {

                Pane paneProduct = new Pane();
                paneProduct.setPrefSize(685, 150);
                paneProduct.setStyle("-fx-background-color: #E2E2E2");

                // NAME:
                Label labelName = new Label("Nombre: " + sale.getClient().getFirstName());
                labelName.setFont(Font.font("Cambria", FontWeight.BOLD, 18));
                labelName.setLayoutX(28);
                labelName.setLayoutY(21);
                paneProduct.getChildren().add(labelName);

                // LAST NAME
                Label labelLastName = new Label("Apellido: " + sale.getClient().getLastName());
                labelLastName.setFont(Font.font("Cambria", FontWeight.BOLD, 18));
                labelLastName.setLayoutX(28);
                labelLastName.setLayoutY(64);
                paneProduct.getChildren().add(labelLastName);

                // ADDRESS
                Label labelAdress = new Label("Dirección: " + sale.getClient().getAddress());
                labelAdress.setFont(Font.font("Cambria", FontWeight.BOLD, 18));
                labelAdress.setLayoutX(28);
                labelAdress.setLayoutY(105);
                paneProduct.getChildren().add(labelAdress);

                // PRICE
                int price = 0;
                for (PurchaseItemDTO item: sale.getPurchaseItems()) price += item.getPrice();
                Label labelPrice = new Label("Precio total: " + price + "$");
                labelPrice.setFont(Font.font("Cambria", FontWeight.BOLD, 26));
                labelPrice.setLayoutX(241);
                labelPrice.setLayoutY(16);
                paneProduct.getChildren().add(labelPrice);

                // SEE PRODUCTS
                Button viewProducts = new Button("Ver productos");
                viewProducts.setFont(Font.font("Cambria", FontWeight.BOLD, 12));
                viewProducts.setLayoutX(295);
                viewProducts.setLayoutY(52);
                viewProducts.setOnAction( event -> {
                    setProducts(Objects.requireNonNull(sale.getPurchaseItems()).toArray());
                });
                paneProduct.getChildren().add(viewProducts);

                // ACCEPT PURCHASE
                if (!sale.getDelivered()) {

                    Button acceptPurchase = new Button("Aceptar Compra");
                    acceptPurchase.setFont(Font.font("Cambria", FontWeight.BOLD, 12));
                    acceptPurchase.setLayoutX(519);
                    acceptPurchase.setLayoutY(60);
                    acceptPurchase.setOnAction(event -> {
                        sale.setDelivered(true);
                        sale.setDeliveryDate(LocalDate.now());
                        sale.setDeliveryTime(LocalTime.now());
                        acceptPurchase.setVisible(false);
                    });
                    paneProduct.getChildren().add(acceptPurchase);

                } else setPurchaseDelivered(paneProduct, sale);

                flowPaneProducts.getChildren().add(paneProduct);

            }

        } else flowPaneProducts.getChildren().add(getNoMesaggesPane("No hay compras"));

    }

    private void setPurchaseDelivered(Pane paneProduct, PurchaseDTO sale) {

        // DELIVERED
        Label labelDelivered = new Label("ENTREGADO");
        labelDelivered.setFont(Font.font("Cambria", FontWeight.BOLD, 20));
        labelDelivered.setLayoutX(28);
        labelDelivered.setLayoutY(105);
        paneProduct.getChildren().add(labelDelivered);

        // DATE
        Label labelDate = new Label("Fecha: " + sale.getDeliveryDate());
        labelDate.setFont(Font.font("Cambria", FontPosture.ITALIC, 15));
        labelDate.setLayoutX(500);
        labelDate.setLayoutY(48);
        paneProduct.getChildren().add(labelDate);

        // TIME
        Label labelTime = new Label("Hora: " + sale.getDeliveryTime());
        labelTime.setFont(Font.font("Cambria", FontPosture.ITALIC, 15));
        labelTime.setLayoutX(500);
        labelTime.setLayoutY(76);
        paneProduct.getChildren().add(labelTime);

    }

    // ****************************************************************************************************************
    //                  PRODUCTS
    // ****************************************************************************************************************

    private void setProducts(Object[] itemSet) {

        if (itemSet != null) {

            float finalPrice = 0.0f;

            for (Object newItem : itemSet) {

                ProductDTO product;
                String color;
                String size;
                String quantity;
                float price;
                if (newItem instanceof PurchaseItemDTO) {
                    PurchaseItemDTO item = (PurchaseItemDTO) newItem;
                    product = item.getProduct();
                    color = item.getSizeAndColor().getColor();
                    size = item.getSizeAndColor().getSize();
                    quantity = item.getQuantity().toString();
                    price = item.getPrice().floatValue();
                }
                else {
                    CartItemDTO item = (CartItemDTO) newItem;
                    product = item.getProduct();
                    color = item.getSizeAndColor().getColor();
                    size = item.getSizeAndColor().getSize();
                    quantity = item.getQuantity().toString();
                    price = item.getPrice().floatValue();
                }

                byte[] image = product.getImage();
                Image productImg;
                if (image != null) {
                    productImg = new Image(new ByteArrayInputStream(image));
                } else {
                    productImg = new Image("/uy/edu/um/tic1/images/no_image.jpg");
                }

                Pane pane = PaneProduct.createCartItem(productImg, product.getName(), product.getBrand().getName(), product.getPrice().floatValue(),
                        color, size);

                Label numberQuantity = getQuantityLabel(quantity);
                pane.getChildren().add(numberQuantity);

                Pane close = new Pane();
                close.setPrefSize(40, 40);
                close.setLayoutX(625);
                close.setLayoutY(56);
                close.setStyle("-fx-background-color: #ff0000");
                close.setOnMouseClicked(event -> {
                    setItemQuantity(newItem, getItemQuantity(newItem)-1);
                    if (getItemQuantity(newItem) <= 0) {
                        pane.getChildren().remove(numberQuantity);
                        pane.getChildren().add(getQuantityLabel(getItemQuantity(newItem).toString()));
                    } else {
                        flowPaneProducts.getChildren().remove(pane);
                        removeSizeAndColor(itemSet, newItem);
                    }
                });
                pane.getChildren().add(close);

                flowPaneProducts.getChildren().add(pane);

                finalPrice += price * Integer.parseInt(quantity);
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

        } else flowPaneProducts.getChildren().add(getNoMesaggesPane("Carrito vacío"));

    }

    private void removeSizeAndColor(Object itemSet, Object item) {

        if (item instanceof PurchaseItemDTO) {
            Set<PurchaseItemDTO> purchaseItemSet = (Set<PurchaseItemDTO>) itemSet;
            PurchaseItemDTO itemp = (PurchaseItemDTO) item;
            purchaseItemSet.remove(itemp);
        }
        else {
            Set<CartItemDTO> cartItemSet = (Set<CartItemDTO>) itemSet;
            CartItemDTO itemc = (CartItemDTO) item;
            cartItemSet.remove(itemc);
        }

    }

    private Integer getItemQuantity (Object item) {

        if (item instanceof PurchaseItemDTO) {
            PurchaseItemDTO itemp = (PurchaseItemDTO) item;
            return itemp.getQuantity();
        }
        else {
            CartItemDTO itemc = (CartItemDTO) item;
            return itemc.getQuantity();
        }

    }

    private void setItemQuantity (Object item, Integer quantity) {

        if (item instanceof PurchaseItemDTO) {
            PurchaseItemDTO itemp = (PurchaseItemDTO) item;
            itemp.setQuantity(quantity);
        }
        else {
            CartItemDTO itemc = (CartItemDTO) item;
            itemc.setQuantity(quantity);
        }

    }

    private StackPane getNoMesaggesPane(String string) {

        Label label = new Label(string + " ¯\\_(ツ)_/¯");
        label.setFont(Font.font("Cambria", FontWeight.BOLD, 32));
        label.setWrapText(true);

        StackPane paneProduct = new StackPane(label);
        paneProduct.setPrefSize(685, 150);
        paneProduct.setStyle("-fx-background-color: #cdcdcd");

        return paneProduct;

    }


}
