package uy.edu.um.tic1.scenes;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.tic1.StoreApplication;
import uy.edu.um.tic1.entity.*;
import uy.edu.um.tic1.product.ProductRequest;
import uy.edu.um.tic1.product.Products;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("/uy/edu/um/tic1/scenes/sceneMain.fxml")
public class MainController implements Initializable {

    @Autowired
    StoreApplication storeApplication;

    private Boolean user = Boolean.FALSE;
    private Boolean filters = Boolean.FALSE;
    private Integer level = 0;
    private String genre;
    private String category;
    private String subcategory;

    @FXML
    private AnchorPane paneRoot;
    @FXML
    private MenuButton menuButtonSort;
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
    private Button cart;
    @FXML
    private AnchorPane paneFilters;
    @FXML
    private Button buttonFilters;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (user) {

            MenuButton menuUser = new MenuButton("Usuario");
            AnchorPane.setRightAnchor(menuUser, 15.0);
            menuUser.setLayoutY(8);
            menuUser.setStyle("-fx-background-color: #ffffff");
            paneMenuBar.getChildren().add(menuUser);

        } else {

            Button logInButton = new Button("Ingresar");
            AnchorPane.setRightAnchor(logInButton, 15.0);
            logInButton.setLayoutY(8);
            logInButton.setStyle("-fx-background-color: #ffffff");
            paneMenuBar.getChildren().add(logInButton);
            logInButton.setOnMouseClicked(event -> {
                storeApplication.sceneLogIn();
            });

        }

        setCategory(Categories.getGenre());
        setColors();
        setSizes();

        paneFiltersLeft.setVisible(false);
        menuButtonSort.setVisible(false);

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
        setProducts();

    }

    /**
     * Hace un request de productos por categoria
     */
    private void requestProductsCategory() {

        menuButtonSort.setVisible(true);

        if (subcategory != null) {

            System.out.println("Busca productos de"+genre+" - "+category+" - "+subcategory);

        } else if (category != null) {

            System.out.println("Busca productos de"+genre+" - "+category);

        } else {

            System.out.println("Busca productos de"+genre);

        }



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
        System.out.println("Busca los productos color"+color);

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
        System.out.println("Busca productos de talle"+size);

    }


    private void setProducts() {

        if (ProductRequest.productsList != null) {

            for (Products product : ProductRequest.productsList) {

                Pane pane = PaneProduct.paneGeneric(product.getImage(), product.getName(), product.getBrand(), product.getPrice(), product.getColors(), product.getSizes());

                pane.setOnMouseClicked(event -> {
                    storeApplication.sceneProductDisplay(product.getName());
                });

                flowPaneBackground.getChildren().add(pane);

            }

        }

    }


    /** Vuelve al home, mostrando nuevamente la pantalla con banner y marcas */
    @FXML
    void homePressed(ActionEvent event) {

    }

    /** Abre la scene del carrito de compras */
    @FXML
    void cartPressed(ActionEvent event) {
        storeApplication.sceneCart();
    }

    @FXML
    void pressedHighFirst(ActionEvent event) {

        System.out.println("Ordenar por precio de alto a bajo");

    }

    @FXML
    void pressedLowFirst(ActionEvent event) {

        System.out.println("Ordenar por precio de bajo a alto");

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

        ProductRequest.productsList = ProductRequest.getAll();
        setCategory(Categories.getGenre());
        setProducts();

    }

    @FXML
    void enteredMaxPrice(ActionEvent event) {

        // Request products con precio maximo

    }

    @FXML
    void enteredMinPrice(ActionEvent event) {

        // Request products con precio minimo

    }

    public MainController() {
    }

}
