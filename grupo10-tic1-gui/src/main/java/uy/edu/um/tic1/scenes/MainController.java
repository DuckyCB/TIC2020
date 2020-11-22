package uy.edu.um.tic1.scenes;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.FileChooser;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.tic1.entities.elements.PaneFilter;
import uy.edu.um.tic1.entitites.users.*;
import uy.edu.um.tic1.requests.BrandRestController;
import uy.edu.um.tic1.requests.ProductRestController;
import uy.edu.um.tic1.entities.BrandFilters;
import uy.edu.um.tic1.entities.ProductFilters;
import uy.edu.um.tic1.entities.attributes.Categories;
import uy.edu.um.tic1.entities.attributes.Colors;
import uy.edu.um.tic1.entities.attributes.Sizes;
import uy.edu.um.tic1.entities.elements.PaneBrands;
import uy.edu.um.tic1.entities.elements.PaneProduct;
import uy.edu.um.tic1.entitites.BrandDTO;
import uy.edu.um.tic1.entitites.product.ProductDTO;
import uy.edu.um.tic1.StoreApplication;
import uy.edu.um.tic1.requests.StoreRestController;
import uy.edu.um.tic1.scenes.admin.store.ProductDisplayStoreController;
import uy.edu.um.tic1.scenes.user.ProductDisplayController;

import java.io.File;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

@Component
@FxmlView("/uy/edu/um/tic1/scenes/sceneMain.fxml")
public class MainController implements Initializable {

    @Autowired
    StoreApplication storeApplication;
    @Autowired
    private ProductRestController productRestController;
    @Autowired
    private BrandRestController brandRestController;
    @Autowired
    private StoreRestController storeRestController;

    private Boolean filters = Boolean.FALSE;

    private static ProductFilters productFilters = new ProductFilters();
    private static BrandFilters brandFilters = new BrandFilters();


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
    @FXML
    private FlowPane flowPaneBrands;
    @FXML
    private Pane labelBrands;

    // ****************************************************************************************************************
    //                  INITIALIZE
    // ****************************************************************************************************************

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        MainController.productFilters = new ProductFilters();

        // Agrega los botones acordes al usuario
        AppUserDTO user = storeApplication.getAppUser();
        if (user instanceof ClientDTO) setButtonsClient();
        else if (user instanceof BrandUserDTO) {
            labelBrands.setVisible(false);
            flowPaneBrands.setVisible(false);
            setButtonsBrand();
        }
        else if (user instanceof StoreUserDTO) setButtonsStore();
        else setButtonsDefault();

        // Setea el panel de filtros
        setCategory(Categories.getGenre());
        setColors();
        setSizes();
        setBrands();

        paneFiltersLeft.setVisible(false);
        menuButtonSort.setVisible(false);

        // Muestra la pagina de inicio, el banner y marcas
        setMainPage();

    }

    // ****************************************************************************************************************
    //                  PÁGINA DE INICIO
    // ****************************************************************************************************************

    public void setMainPage() {

        flowPaneBackground.getChildren().clear();

        ImageView banner = BrandRestController.getBanner();
        banner.setFitWidth(1000);
        banner.setPreserveRatio(true);
        flowPaneBackground.getChildren().add(banner);

        ScrollPane brandsPane = PaneBrands.getScroll(brandRestController.getAllBrands(brandFilters));
        brandsPane.setPrefWidth(1000);
        flowPaneBackground.getChildren().add(brandsPane);

    }

    /**
     * Hace un request de productos por marca, y los muestra en pantalla
     * @param brand Marca elegida
     */
    public void selectedBrand(BrandDTO brand) {

        productFilters.setBrand_id(brand.getId());
        setProducts();

    }

    // ****************************************************************************************************************
    //                  BOTONES
    // ****************************************************************************************************************

    /**
     * Agrega los botones de un usuario sin cuenta, Carrito e Ingresar
     */
    private void setButtonsDefault() {

        flowPaneButtons.getChildren().clear();

        Button cart = new Button("Carrito");
        cart.setStyle("-fx-background-color: #ffffff");
        cart.setOnAction(event -> storeApplication.sceneCart());
        flowPaneButtons.getChildren().add(cart);

        Button login = new Button("Ingresar");
        login.setStyle("-fx-background-color: #ffffff");
        login.setOnAction(event -> storeApplication.sceneLogIn());
        flowPaneButtons.getChildren().add(login);

    }

    /**
     * Agrega los botones de un cliente, Carrito y Usuario
     */
    private void setButtonsClient() {

        flowPaneButtons.getChildren().clear();

        Button cart = new Button("Carrito");
        cart.setStyle("-fx-background-color: #ffffff");
        cart.setOnMouseClicked(event -> storeApplication.sceneCart());
        flowPaneButtons.getChildren().add(cart);

        Button logOut = new Button("Cerrar sesión");
        logOut.setStyle("-fx-background-color: #ffffff");
        logOut.setOnAction(event -> logOut());
        flowPaneButtons.getChildren().add(logOut);

    }

    /**
     * Agrega los botones de una Marca, Nuevo Producto y Marca.
     */
    private void setButtonsBrand() {

        flowPaneButtons.getChildren().clear();

        Button newProduct = new Button("Nuevo producto");
        newProduct.setStyle("-fx-background-color: #ffffff");
        newProduct.setOnAction(event -> storeApplication.sceneBrandDisplayProduct(null));
        flowPaneButtons.getChildren().add(newProduct);

        Button logOut = new Button("Cerrar sesión");
        logOut.setStyle("-fx-background-color: #ffffff");
        logOut.setOnAction(event -> logOut());
        flowPaneButtons.getChildren().add(logOut);

    }

    /**
     * Agrega los botones de una tienda, Nuevo Producto, y Tienda
     */
    private void setButtonsStore() {

        flowPaneButtons.getChildren().clear();

        Button sales = new Button("Ventas");
        sales.setStyle("-fx-background-color: #ffffff");
        sales.setOnAction(event -> storeApplication.sceneListItems("Ventas"));
        flowPaneButtons.getChildren().add(sales);

        Button newProduct = new Button("Agregar producto");
        newProduct.setStyle("-fx-background-color: #ffffff");
        newProduct.setOnAction(event -> storeApplication.sceneStoreDisplayProduct(null));
        flowPaneButtons.getChildren().add(newProduct);

        Button logOut = new Button("Cerrar sesión");
        logOut.setStyle("-fx-background-color: #ffffff");
        logOut.setOnAction(event -> logOut());
        flowPaneButtons.getChildren().add(logOut);

    }


    private void logOut(){
        storeApplication.setAppUser(null);
        storeApplication.setCart(null);
        storeApplication.setPassword(null);
        storeApplication.sceneMain();
    }

    // ****************************************************************************************************************
    //                  PANEL DE FILTROS
    // ****************************************************************************************************************

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

            Pane paneCategory = PaneFilter.getPane(string, "E2E2E2");
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

        if (productFilters.getGender() == null) {

            productFilters.setGender(type.charAt(0));
            setCategory(Categories.getSubCategory(type));

        } else if (productFilters.getType() == null) {

            productFilters.setType(type);
            Label arrow = new Label(" > ");
            flowPaneCategoryLabels.getChildren().add(arrow);
            setCategory(Categories.getSubCategory(productFilters.getGender().toString(), type));

        } else {

            Label arrow = new Label(" > ");
            flowPaneCategoryLabels.getChildren().add(arrow);
            flowPaneCategory.getChildren().clear();

        }

        Label label = new Label(type);
        flowPaneCategoryLabels.getChildren().add(label);

        setProducts();

    }


    /**
     * Agrega los colores al panel de filtro.
     * Toma los colores de la clase estatica Colors.
     * @see Colors
     * Al apretar un color, llama la funcion
     * @see #colorRequest(String)
     */
    private void setColors() {

        flowPaneColors.getChildren().clear();

        for (String color: ProductDTO.getColors()) {

            Circle colorCircle = Colors.getCircle(color, 16f);
            StackPane colorPane = new StackPane(colorCircle);

            if (productFilters.getColor() != null) {

                if (productFilters.getColor().equals(color)) colorPane.setStyle("-fx-background-color: #888888");
                else colorPane.setStyle("-fx-background-color: #E2E2E2");

            } else colorPane.setStyle("-fx-background-color: #E2E2E2");

            colorPane.setPrefSize(32, 32);
            colorCircle.setOnMouseClicked(event -> colorRequest(color));
            flowPaneColors.getChildren().add(colorPane);

        }

    }


    /**
     * Hace un request de productos por color
     * @param color Color por el cual va a hacer filtro.
     */
    private void colorRequest(String color) {

        productFilters.setColor(color);
        setProducts();
        setColors();

    }



    /**
     * Agrega los talles al panel de filtro.
     * Toma los talles de la clase estatica Sizes.
     * @see Sizes
     * Al apretar un talle, llama la funcion
     * @see #sizeRequest(String) para hacer el request
     */
    private void setSizes(){

        flowPaneSizes.getChildren().clear();

        List<String> sizes = Sizes.getSizes(productFilters.getType());

        for (String size: sizes) {

            Pane paneSize = Sizes.getPane(size);

            if (productFilters.getSize() != null) {

                if (productFilters.getSize().equals(size)) paneSize.setStyle("-fx-background-color: #888888");

            }

            paneSize.setOnMouseClicked(event -> sizeRequest(size));
            flowPaneSizes.getChildren().add(paneSize);

        }

    }

    /**
     * Hace un request de productos por talle
     * @param size Talle por el cual va a hacer filtro.
     */
    private void sizeRequest(String size) {

        menuButtonSort.setVisible(true);
        productFilters.setSize(size);
        setProducts();
        setSizes();

    }

    /**
     * Agrega las marcas al panel de filtro.
     * Toma las marcas con un request.
     * @see BrandRestController
     * @see #pressedCategory(String)
     */
    private void setBrands() {

        flowPaneBrands.getChildren().clear();

        List<BrandDTO> brands = brandRestController.getAllBrands(brandFilters);

        if (brands != null) {

            for (BrandDTO brand : brands) {

                String brandName = brand.getName();
                Integer brandID = brand.getId();
                Pane paneBrand;

                if (productFilters.getBrand_id() != null) {

                    if (productFilters.getBrand_id().equals(brandID))
                        paneBrand = PaneFilter.getPane(brandName, "888888");
                    else paneBrand = PaneFilter.getPane(brandName, "E2E2E2");

                } else paneBrand = PaneFilter.getPane(brandName, "E2E2E2");

                paneBrand.setOnMouseClicked(event -> brandRequest(brandID));
                flowPaneCategory.getChildren().add(paneBrand);

            }

        }

    }

    /**
     * Hace un request de productos por marca
     * @param brand Marca por la cual va a hacer filtro.
     */
    private void brandRequest(Integer brand) {

        productFilters.setBrand_id(brand);
        setProducts();
        setBrands();

    }

    // ****************************************************************************************************************
    //                  PRODUCTOS
    // ****************************************************************************************************************

    /**
     *
     */
    private void setProducts() {

        List<ProductDTO> productsList = productsQuery();

        flowPaneBackground.getChildren().clear();
        menuButtonSort.setVisible(true);

        if (productsList != null && !productsList.isEmpty()) {

            for (ProductDTO product : productsList) {

                HashMap<String, String> uniqueSize = new HashMap<>();
                product.getSizeAndColor().stream().forEach(sc -> uniqueSize.put(sc.getSize(), sc.getSize()));
                HashMap<String, String> uniqueColors = new HashMap<>();
                product.getSizeAndColor().stream().forEach(sc -> uniqueColors.put(sc.getColor(), sc.getColor()));


                Pane pane = PaneProduct.paneGeneric(product.getImage(), product.getName(), product.getBrand().getName(),
                        product.getPrice(), uniqueColors.keySet().stream().collect(Collectors.toList()),
                        uniqueSize.keySet().stream().collect(Collectors.toList()));

                pane.setOnMouseClicked(event -> {

                    AppUserDTO user = storeApplication.getAppUser();

                    if (user instanceof ClientDTO) storeApplication.sceneProductDisplay(product);
                    else if (user instanceof BrandUserDTO) storeApplication.sceneBrandDisplayProduct(product);
                    else if (user instanceof StoreUserDTO) storeApplication.sceneStoreDisplayProduct(product);
                    else storeApplication.sceneProductDisplay(product);

                });

                flowPaneBackground.getChildren().add(pane);

            }

        } else {

            Label noProducts = new Label("No hay productos ¯\\_(ツ)_/¯");
            noProducts.setFont(Font.font("Cambria", FontWeight.BOLD, 32));
            flowPaneBackground.getChildren().add(noProducts);

        }

    }

    // ****************************************************************************************************************
    //                  BOTONES FXML
    // ****************************************************************************************************************


    /**
     * Vuelve al home, mostrando nuevamente la pantalla con banner y marcas
     */
    @FXML
    void homePressed(ActionEvent event) {

        setMainPage();
        productFilters = new ProductFilters();

    }

    @FXML
    void pressedHighFirst(ActionEvent event) {

        productFilters.setOrder(-1);
        setProducts();

    }

    @FXML
    void pressedLowFirst(ActionEvent event) {

        productFilters.setOrder(1);
        setProducts();

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
    void pressedClean(MouseEvent event) { clearFilters(); }

    private void clearFilters() {

        flowPaneBackground.getChildren().clear();
        flowPaneCategoryLabels.getChildren().clear();

        productFilters = new ProductFilters();

        setCategory(Categories.getGenre());
        setProducts();
        setColors();

    }

    @FXML
    void enteredMaxPrice(ActionEvent event) {

        String maxStr = fieldMaxPrice.getText();

        if (!maxStr.isEmpty()) {

            try {
                Double max = Double.parseDouble(maxStr);
                productFilters.setTo(max);
                setProducts();
            } catch (NumberFormatException e) {
                System.out.println("El valor debe ser un numero");
            }

        }

    }

    @FXML
    void enteredMinPrice(ActionEvent event) {

        String minStr = fieldMinPrice.getText();

        if (!minStr.isEmpty()) {

            try {
                Double min = Double.parseDouble(minStr);
                productFilters.setFrom(min);
                setProducts();
            } catch (NumberFormatException e) {
                System.out.println("El valor debe ser un numero");
            }

        }

    }

    @FXML
    void pressedSearch(ActionEvent event) {

        String text = fieldSearch.getText();

        if (!text.isEmpty()) {

            clearFilters();
            productFilters.setName(text);
            setProducts();

        }

    }

    public MainController() {
    }


    public List<ProductDTO> productsQuery(){

        List<ProductDTO> productList = null;

        AppUserDTO user = storeApplication.getAppUser();
        if (user == null || user instanceof ClientDTO) productList =productRestController.getProducts(productFilters);
        else if (user instanceof BrandUserDTO) {
            productFilters.setBrand_id(((BrandUserDTO) user).getBrand().getId());
            productList = productRestController.getProducts(productFilters);

        }
        else if (user instanceof StoreUserDTO) productList = storeRestController.getStoreProducts(false);

        return productList;

    }

}
