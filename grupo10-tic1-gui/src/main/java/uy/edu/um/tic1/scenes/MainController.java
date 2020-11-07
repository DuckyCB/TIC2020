package uy.edu.um.tic1.scenes;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import uy.edu.um.tic1.entities.attributes.Categories;
import uy.edu.um.tic1.entities.attributes.Colors;
import uy.edu.um.tic1.entities.attributes.Sizes;
import uy.edu.um.tic1.entities.elements.PaneBrands;
import uy.edu.um.tic1.entities.elements.PaneProduct;
import uy.edu.um.tic1.entities.products.Product;
import uy.edu.um.tic1.entitites.users.AppUserDTO;
import uy.edu.um.tic1.requests.RequestMain;
import uy.edu.um.tic1.requests.RequestProducts;
import uy.edu.um.tic1.StoreApplication;
import uy.edu.um.tic1.requests.ProductRequestOld;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("/uy/edu/um/tic1/scenes/sceneMain.fxml")
public class MainController implements Initializable {

    @Autowired
    StoreApplication storeApplication;




    private Boolean user = Boolean.FALSE;
    private Boolean filters = Boolean.FALSE;
    private String genre;
    private String category;
    private String subcategory;
    private String size;
    private String color;


    @FXML
    private AnchorPane paneRoot;
    @FXML
    private MenuButton menuButtonSort;
    @FXML
    private TextField fieldSearch;
    @FXML
    private AnchorPane paneFiltersLeft;
    @FXML
    private FlowPane flowPaneCategory;
    @FXML
    private FlowPane flowPaneCategoryLabels;
    @FXML
    private FlowPane flowPaneColors;
    @FXML
    private FlowPane flowPaneSizes;
    @FXML
    private Label labelCleanFilters;
    @FXML
    private TextField fieldMinPrice;
    @FXML
    private TextField fieldMaxPrice;
    @FXML
    private ScrollPane paneBackground;
    @FXML
    private FlowPane flowPaneBackground;
    @FXML
    private AnchorPane paneMenuBar;
    @FXML
    private Button home;
    @FXML
    private AnchorPane paneFilters;
    @FXML
    private Button buttonFilters;
    @FXML
    private FlowPane flowPaneButtons;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        // Falta la verificacion de tipo de usuario, actualmente es solo un Boolean
        if (user) setButtonsClient();
        else setButtonsDefault();

        setCategory(Categories.getGenre());
        setColors();
        setSizes();

        paneFiltersLeft.setVisible(false);
        menuButtonSort.setVisible(false);

        setMainPage();


    }

    /**
     * Agrega los botones de un usuario sin cuenta, Carrito e Ingresar
     */
    private void setButtonsDefault() {

        flowPaneButtons.getChildren().clear();

        Button login = new Button("Ingresar");
        login.setStyle("-fx-background-color: #ffffff");
        login.setOnAction(event -> storeApplication.sceneLogIn());
        flowPaneButtons.getChildren().add(login);

        Button cart = new Button("Carrito");
        cart.setStyle("-fx-background-color: #ffffff");
        cart.setOnAction(event -> storeApplication.sceneCart());
        flowPaneButtons.getChildren().add(cart);

    }

    /**
     * Agrega los botones de un cliente, Carrito y Usuario
     */
    private void setButtonsClient() {

        flowPaneButtons.getChildren().clear();

        MenuButton user = new MenuButton("Usuario");
        user.setStyle("-fx-background-color: #ffffff");
        MenuItem config = new MenuItem("Configuración");
        config.setOnAction(event -> storeApplication.sceneConfig());
        user.getItems().add(config);
        MenuItem logOut = new MenuItem("Cerrar sesión");
        logOut.setOnAction(event -> System.out.println("cerrar sesión"));
        user.getItems().add(logOut);
        flowPaneButtons.getChildren().add(user);

        Button cart = new Button();
        cart.setStyle("-fx-background-color: #ffffff");
        cart.setOnMouseClicked(event -> storeApplication.sceneCart());
        flowPaneButtons.getChildren().add(cart);

    }

    /**
     * Agrega los botones de una Marca, Nuevo Producto y Marca.
     */
    private void setButtonsBrand() {

        flowPaneButtons.getChildren().clear();

        Button logOut = new Button("Cerrar sesión");
        logOut.setStyle("-fx-background-color: #ffffff");
        logOut.setOnAction(event -> System.out.println("Cerrar sesión"));
        flowPaneButtons.getChildren().add(logOut);

        Button newProduct = new Button("Nuevo producto");
        newProduct.setStyle("-fx-background-color: #ffffff");
        newProduct.setOnAction(event -> storeApplication.sceneBrandNewProduct());
        flowPaneButtons.getChildren().add(newProduct);

    }

    /**
     * Agrega los botones de una tienda, Nuevo Producto, y Tienda
     */
    private void setButtonsStore() {

        flowPaneButtons.getChildren().clear();

        Button logOut = new Button("Cerrar sesión");
        logOut.setStyle("-fx-background-color: #ffffff");
        logOut.setOnAction(event -> System.out.println("Cerrar sesión"));
        flowPaneButtons.getChildren().add(logOut);

        Button newProduct = new Button("Agregar producto");
        newProduct.setStyle("-fx-background-color: #ffffff");
        newProduct.setOnAction(event -> storeApplication.sceneStoreAddProduct());
        flowPaneButtons.getChildren().add(newProduct);

    }

    /**
     * Agrega las categorias al panel de filtro.
     * Toma las categorias de la clase estatica Categories.
     * @see Categories
     * Si level se encuentra en el valor 1, toma la nueva lista de String
     * @see #pressedCategory(String)
     */
    private void setCategory(String[] categoryList) {

        flowPaneCategory.getChildren().clear();

        for (String string : categoryList) {

            Pane paneCategory = Categories.getPane(string);
            paneCategory.setOnMouseClicked(event -> pressedCategory(string));
            flowPaneCategory.getChildren().add(paneCategory);

        }

    }

    /**
     * Verifica si el genero y categoria son nulos, para decidir que ejecutar.
     * Agrega la categoria clickeada al panel
     * @see #flowPaneCategoryLabels
     * Cambia la lista de categorias mostradas
     * @see #flowPaneCategory
     * Tomando la lsita de
     * @see Categories#getSubCategory(String) 
     * @see Categories#getSubCategory(String, String) 
     * @param type Nombre de la categoria presionada
     */
    private void pressedCategory(String type) {

        if (genre == null) {

            genre = type;
            setCategory(Categories.getSubCategory(type));

        } else if (category == null) {

            category = type;
            Label arrow = new Label(" > ");
            flowPaneCategoryLabels.getChildren().add(arrow);
            setCategory(Categories.getSubCategory(genre, type));

        } else {

            subcategory = type;
            Label arrow = new Label(" > ");
            flowPaneCategoryLabels.getChildren().add(arrow);
            flowPaneCategory.getChildren().clear();

        }

        Label label = new Label(type);
        flowPaneCategoryLabels.getChildren().add(label);

        requestProductsCategory();
        setProducts(requestProductsCategory());

    }

    /**
     * Hace un request de productos por categoria
     */
    private Product[] requestProductsCategory() {

        menuButtonSort.setVisible(true);

        Product[] products;

        if (subcategory != null) products = RequestProducts.getByCategory(genre, category, subcategory);
        else if (category != null) products = RequestProducts.getByCategory(genre, category);
        else products = RequestProducts.getByCategory("gender=" + genre);

        return products;

    }



    /**
     * Agrega los colores al panel de filtro.
     * Toma los colores de la clase estatica Colors.
     * @see Colors
     * Al apretar un color, llama la funcion
     * @see #colorRequest(String)
     */
    private void setColors() {

        for (String color: Colors.getAllListed()) {

            Circle colorCircle = Colors.getCircle(color, 16f);
            colorCircle.setOnMouseClicked(event -> {
                colorRequest(color);
            });
            flowPaneColors.getChildren().add(colorCircle);

        }

    }

    /**
     * Hace un request de productos por color
     * @param color Color por el cual va a hacer filtro.
     */
    private void colorRequest(String color) {

        menuButtonSort.setVisible(true);
        System.out.println("Busca los productos color "+color);

    }



    /**
     * Agrega los talles al panel de filtro.
     * Toma los talles de la clase estatica Sizes.
     * @see Sizes
     * Al apretar un talle, llama la funcion
     * @see #sizeRequest(String) para hacer el request
     */
    private void setSizes(){

        for (String size: Sizes.getListAdults()) {

            Pane paneSize = Sizes.getPane(size);
            paneSize.setOnMouseClicked(event -> {
                sizeRequest(size);
            });
            flowPaneSizes.getChildren().add(paneSize);

        }

    }

    /**
     * Hace un request de productos por talle
     * @param size Talle por el cual va a hacer filtro.
     */
    private void sizeRequest(String size) {

        menuButtonSort.setVisible(true);
        System.out.println("Busca productos de talle "+size);

    }


    /**
     * Muestra una lista de productos en pantalla.
     * Borra los productos mostrados anteriormente.
     * @param productsList Lista de productos para mostrar en pantalla
     */
    private void setProducts(Product[] productsList) {

        flowPaneBackground.getChildren().clear();

        if (productsList != null) {

            for (Product product : productsList) {

                Pane pane = PaneProduct.paneGeneric(product.getImage(), product.getName(), product.getBrand(), product.getPrice(), product.getColors(), product.getSizes());

                pane.setOnMouseClicked(event -> {
                    storeApplication.sceneProductDisplay(product.getName());
                });

                flowPaneBackground.getChildren().add(pane);

            }

        } else {

            Label noProducts = new Label("No hay productos para mostrar ¯\\_(ツ)_/¯");
            noProducts.setFont(Font.font("Cambria", FontWeight.BOLD, 48));
            flowPaneBackground.getChildren().add(noProducts);

        }

    }

    /**
     * Hace un request de productos por marca, y los muestra en pantalla
     * @param brand Marca elegida
     */
    public static void selectedBrand(String brand) {

        System.out.println();

    }


    /** Vuelve al home, mostrando nuevamente la pantalla con banner y marcas */
    @FXML
    void homePressed(ActionEvent event) {

        setMainPage();

    }

    public void setMainPage() {

        flowPaneBackground.getChildren().clear();

        ImageView banner = RequestMain.getBanner();
        banner.setFitWidth(1000);
        banner.setPreserveRatio(true);
        flowPaneBackground.getChildren().add(banner);

        ScrollPane brandsPane = PaneBrands.getScroll(RequestMain.getBrands());
        brandsPane.setPrefWidth(1000);
        flowPaneBackground.getChildren().add(brandsPane);

    }

    /** Abre la scene del carrito de compras */
    void cartPressed() {

        if (user) {
            RequestProducts.getCart("Juanito Escarcha");
        } else {
            System.out.println("Usa carrito guardado en memoria");
        }
        storeApplication.sceneCart();

    }

    @FXML
    void pressedHighFirst(ActionEvent event) {

        RequestProducts.getSortedByHighFirst();

    }

    @FXML
    void pressedLowFirst(ActionEvent event) {

        RequestProducts.getSortedByLowFirst();

    }

    /** Abre y cierra el panel de filtros a la izquierda */
    @FXML
    void pressedFilters(ActionEvent event) {

        if (!filters) {

            AnchorPane.setLeftAnchor(paneBackground, 200d);
            paneFiltersLeft.setVisible(true);
            filters = true;

        } else {

            paneFiltersLeft.setVisible(false);
            AnchorPane.setLeftAnchor(paneBackground, 0d);
            filters = false;

        }

    }

    /** Elimina todos los filtros, y muestra la totalidad de los productos */
    @FXML
    void pressedClean(MouseEvent event) {

        flowPaneCategory.getChildren().clear();
        flowPaneBackground.getChildren().clear();
        flowPaneCategoryLabels.getChildren().clear();
        genre = null;
        category = null;
        subcategory = null;

        ProductRequestOld.productsList = ProductRequestOld.getAll();
        setCategory(Categories.getGenre());
        setProducts(RequestProducts.getAll());

    }

    @FXML
    void enteredMaxPrice(ActionEvent event) {

        String maxStr = fieldMaxPrice.getText();

        if (!maxStr.isEmpty()) {

            try {
                Float max = Float.parseFloat(maxStr);
                RequestProducts.getByMinPrice(max);
                System.out.println("Buscar productos de precio hasta "+max.toString());
            } catch (NumberFormatException e) {
                e.printStackTrace();
                System.out.println("El valor debe ser un numero");
            }

        }

    }

    @FXML
    void enteredMinPrice(ActionEvent event) {

        String minStr = fieldMinPrice.getText();

        if (!minStr.isEmpty()) {

            try {
                Float min = Float.parseFloat(minStr);
                RequestProducts.getByMinPrice(min);
                System.out.println("Buscar productos de precio desde "+min.toString());
            } catch (NumberFormatException e) {
                System.out.println("El valor debe ser un numero");
            }

        }

    }

    @FXML
    void pressedSearch(ActionEvent event) {

        String text = fieldSearch.getText();

        if (!text.isEmpty()) {

            //RequestProducts.setFiltersNull();
            //RequestProducts.performQuery();
            System.out.println("Busca items con el nombre "+text);

        } else {

            System.out.println("Busqueda vacia");

        }

    }

    public MainController() {
    }

}
