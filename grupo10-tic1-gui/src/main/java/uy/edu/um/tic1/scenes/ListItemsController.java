package uy.edu.um.tic1.scenes;

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
import uy.edu.um.tic1.entities.ProductFilters;
import uy.edu.um.tic1.entities.elements.PaneProduct;
import uy.edu.um.tic1.entitites.cart.CartDTO;
import uy.edu.um.tic1.entitites.cart.CartItemDTO;
import uy.edu.um.tic1.entitites.cart.PurchaseDTO;
import uy.edu.um.tic1.entitites.cart.PurchaseItemDTO;
import uy.edu.um.tic1.entitites.product.ProductDTO;
import uy.edu.um.tic1.entitites.users.AppUserDTO;
import uy.edu.um.tic1.entitites.users.ClientDTO;
import uy.edu.um.tic1.entitites.users.StoreUserDTO;
import uy.edu.um.tic1.requests.CartRestController;
import uy.edu.um.tic1.requests.ProductRestController;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Component
@FxmlView("/uy/edu/um/tic1/scenes/sceneListItems.fxml")
public class ListItemsController implements Initializable {

    @Autowired
    StoreApplication storeApplication;
    @Autowired
    private CartRestController cartRestController;
    @Autowired
    private ProductRestController productRestController;

    private AppUserDTO user;

    private Set<PurchaseDTO> purchaseSet;
    private Set<CartItemDTO> cartItemSet;


    @FXML
    private FlowPane flowPaneProducts;
    @FXML
    private Button inicio;

    // ****************************************************************************************************************
    //                  INITIALIZE
    // ****************************************************************************************************************

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        user = storeApplication.getAppUser();
        if (user instanceof ClientDTO) {

            cartItemSet = requestCartList();
            setProducts(cartItemSet.toArray());

        }
        else if (user instanceof StoreUserDTO) setStoreSales();
        else {

            purchaseSet = storeApplication.getPurchases();
            setProducts(Objects.requireNonNull(requestCartList()).toArray());

        }

    }

    private Label getQuantityLabel(String quantity) {

        Label numberQuantity = new Label(quantity);
        numberQuantity.setFont(Font.font("Cambria", FontWeight.BOLD, 32));
        numberQuantity.setLayoutX(513);
        numberQuantity.setLayoutY(87);
        return numberQuantity;

    }

    // ****************************************************************************************************************
    //                  BUTTONS FXML
    // ****************************************************************************************************************

    @FXML
    void inicioPressed(ActionEvent event) {
        storeApplication.sceneMain();
    }

    // ****************************************************************************************************************
    //                  USER
    // ****************************************************************************************************************

    /**
     * En el caso que haya un usuario ingresado, hace un request al server para tener los productos del carrito.
     * Si no hay usuario, toma los productos en ram.
     * @return products Lista de productos dentro del carrito
     */
    private Set<CartItemDTO> requestCartList() {

        CartDTO cart = storeApplication.getCart();
        if (cart != null)
            return cart.getItems();
        else return null;

    }

    // ****************************************************************************************************************
    //                  STORE
    // ****************************************************************************************************************

    private void setStoreSales() {

        if (purchaseSet != null) {

            for (PurchaseDTO sale : purchaseSet) {

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

        flowPaneProducts.getChildren().clear();

        if (itemSet != null) {

            float finalPrice = 0.0f;

            for (Object newItem : itemSet) {

                ProductDTO product;
                String color;
                String size;
                Integer quantity;
                float price;
                Boolean enoughStock = false;

                if (newItem instanceof PurchaseItemDTO) {

                    PurchaseItemDTO item = (PurchaseItemDTO) newItem;
                    product = item.getProduct();
                    color = item.getSizeAndColor().getColor();
                    size = item.getSizeAndColor().getSize();
                    quantity = item.getQuantity();
                    price = item.getPrice().floatValue();
                    enoughStock = true;

                }
                else {

                    CartItemDTO item = (CartItemDTO) newItem;
                    product = item.getProduct();
                    color = item.getSizeAndColor().getColor();
                    size = item.getSizeAndColor().getSize();
                    quantity = item.getQuantity();
                    price = item.getPrice().floatValue();
                    enoughStock = checkStock(item);

                }

                if (quantity > 0) {

                    byte[] image = product.getImage();
                    Image productImg;
                    if (image != null) {
                        productImg = new Image(new ByteArrayInputStream(image));
                    } else {
                        productImg = new Image("/uy/edu/um/tic1/images/no_image.jpg");
                    }

                    Pane pane = PaneProduct.createCartItem(productImg, product.getName(), product.getBrand().getName(),
                            product.getPrice().floatValue(), color, size, enoughStock);

                    Label numberQuantity = getQuantityLabel(quantity.toString());
                    pane.getChildren().add(numberQuantity);

                    if (newItem instanceof CartItemDTO) {

                        CartItemDTO cartItem = (CartItemDTO) newItem;

                        Button close = new Button("X");
                        close.setFont(Font.font("Cambria", FontWeight.BOLD, 15));
                        close.setLayoutX(625);
                        close.setLayoutY(56);
                        close.setStyle("-fx-background-color: #ff7373");
                        close.setOnAction(event -> {

                            boolean deleted = storeApplication.getCart().decreaseQuantity(cartItem);
                            if (user != null) cartRestController.saveCurrentCart(storeApplication.getCart());

                            pane.getChildren().remove(numberQuantity);
                            pane.getChildren().add(getQuantityLabel(cartItem.getQuantity().toString()));
                            if (deleted) flowPaneProducts.getChildren().remove(pane);

                            setProducts(storeApplication.getCart().getItems().toArray());

                        });

                        pane.getChildren().add(close);

                    }

                    flowPaneProducts.getChildren().add(pane);

                    finalPrice += price * quantity;

                }

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

                if (!cartItemSet.isEmpty()) {

                    // TODO : Realizar compra
                    buyCart();
                    storeApplication.setCart(CartDTO.builder().items(new LinkedHashSet<>()).build());
                    ((ClientDTO) storeApplication.getAppUser()).setCurrentCart(storeApplication.getCart());
                    cartRestController.saveCurrentCart(storeApplication.getCart());
                    System.out.println("Carro comprado");
                    storeApplication.sceneMain();

                }

            });

            paneProduct.getChildren().add(buy);
            flowPaneProducts.getChildren().add(paneProduct);

        } else flowPaneProducts.getChildren().add(getNoMesaggesPane("Carrito vacío"));

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

    // ****************************************************************************************************************
    //                  STOCK
    // ****************************************************************************************************************

    public Boolean checkStock(CartItemDTO cartItem){

        ProductFilters productFilters = new ProductFilters();
        productFilters.setId(cartItem.getProduct().getId());
        productFilters.setStock(cartItem.getQuantity());
        productFilters.setSize(cartItem.getSizeAndColor().getSize());
        productFilters.setColor(cartItem.getSizeAndColor().getColor());

        List<ProductDTO> products = productRestController.getProducts(productFilters);

        return !products.isEmpty();

    }

    // ****************************************************************************************************************
    //                  CART
    // ****************************************************************************************************************

    public void buyCart(){

        ClientDTO client = null;
        AppUserDTO appUserDTO = storeApplication.getAppUser();

        if (appUserDTO instanceof ClientDTO) {

            client = (ClientDTO) appUserDTO;
            CartDTO cart = storeApplication.getCart();

            Boolean stock = true;

            for (CartItemDTO item : cart.getItems()){

                if (!checkStock(item)) stock = false;
                if (!stock) break;

            }

            if (stock) cartRestController.buyCurrentCart(true);

        }

    }

}
