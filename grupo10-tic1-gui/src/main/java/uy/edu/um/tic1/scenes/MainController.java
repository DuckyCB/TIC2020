package uy.edu.um.tic1.scenes;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.tic1.entities.elements.PaneFilter;
import uy.edu.um.tic1.entitites.cart.CartDTO;
import uy.edu.um.tic1.entitites.product.HoodieDTO;
import uy.edu.um.tic1.entitites.product.ShirtDTO;
import uy.edu.um.tic1.entitites.product.TrousersDTO;
import uy.edu.um.tic1.entitites.users.*;
import uy.edu.um.tic1.requests.*;
import uy.edu.um.tic1.entities.BrandFilters;
import uy.edu.um.tic1.entities.ProductFilters;
import uy.edu.um.tic1.entities.attributes.Categories;
import uy.edu.um.tic1.entities.attributes.Colors;
import uy.edu.um.tic1.entities.attributes.Sizes;
import uy.edu.um.tic1.entities.elements.PaneProduct;
import uy.edu.um.tic1.entitites.BrandDTO;
import uy.edu.um.tic1.entitites.product.ProductDTO;
import uy.edu.um.tic1.StoreApplication;
import uy.edu.um.tic1.requests.StoreRestController;

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
    //TODO: Borrar esto para la entrega
    @Autowired
    private UserRestController userRestController;
    @Autowired
    private CartRestController cartRestController;

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

        showUsers();
        showMainPage();

    }

    //TODO: Borrar esto cuando se entrega
    private void showUsers() {

        MenuButton menuButton = new MenuButton("Usuario");
        menuButton.setLayoutX(373);
        menuButton.setLayoutY(8);

        MenuItem client = new MenuItem("Client");
        client.setOnAction(event -> {
            logOut();
            String user = "cliente";
            String password = "cliente";
            AppUserDTO userEntity = userRestController.getUser(user, password);
            storeApplication.setAppUser(userEntity);
            storeApplication.setPassword(password);
            CartDTO userCart = cartRestController.getCurrentCart();
            CartDTO savedCart = storeApplication.getCart();
            if(userCart == null || userCart.getItems().isEmpty()){
                if(userCart == null){
                    ((ClientDTO) userEntity).setCurrentCart(storeApplication.getCart());
                    cartRestController.saveCurrentCart(storeApplication.getCart());
                } else if (!savedCart.getItems().isEmpty()){
                    ((ClientDTO) userEntity).setCurrentCart(storeApplication.getCart());
                    cartRestController.saveCurrentCart(storeApplication.getCart());
                }
            }
            storeApplication.setCart(cartRestController.getCurrentCart());
            storeApplication.sceneMain();

        });
        menuButton.getItems().add(client);

        MenuItem store = new MenuItem("Store 1");
        store.setOnAction(event -> {
            logOut();
            String user = "tienda1";
            String password = "tienda1";
            AppUserDTO userEntity = userRestController.getUser(user, password);
            storeApplication.setAppUser(userEntity);
            storeApplication.setPassword(password);
            storeApplication.setPurchases(storeRestController.getStore().getPurchaseSet());
            storeApplication.sceneMain();
        });
        menuButton.getItems().add(store);

        MenuItem store1 = new MenuItem("Store 2");
        store1.setOnAction(event -> {
            logOut();
            String user = "tienda2";
            String password = "tienda2";
            AppUserDTO userEntity = userRestController.getUser(user, password);
            storeApplication.setAppUser(userEntity);
            storeApplication.setPassword(password);
            storeApplication.setPurchases(storeRestController.getStore().getPurchaseSet());
            storeApplication.sceneMain();
        });
        menuButton.getItems().add(store1);

        MenuItem brandTommys = new MenuItem("Brand Tommy");
        brandTommys.setOnAction(event -> {
            logOut();
            String user = "tommy";
            String password = "tommy";
            AppUserDTO userEntity = userRestController.getUser(user, password);
            storeApplication.setAppUser(userEntity);
            storeApplication.setPassword(password);
            storeApplication.sceneMain();
        });
        menuButton.getItems().add(brandTommys);

        MenuItem brandLevis = new MenuItem("Brand Levis");
        brandLevis.setOnAction(event -> {
            logOut();
            String user = "levis";
            String password = "levis";
            AppUserDTO userEntity = userRestController.getUser(user, password);
            storeApplication.setAppUser(userEntity);
            storeApplication.setPassword(password);
            storeApplication.sceneMain();
        });
        menuButton.getItems().add(brandLevis);

        MenuItem brandPolo = new MenuItem("Brand Polo");
        brandPolo.setOnAction(event -> {
            logOut();
            String user = "polo";
            String password = "polo";
            AppUserDTO userEntity = userRestController.getUser(user, password);
            storeApplication.setAppUser(userEntity);
            storeApplication.setPassword(password);
            storeApplication.sceneMain();
        });
        menuButton.getItems().add(brandPolo);

        paneMenuBar.getChildren().add(menuButton);

    }

    // ****************************************************************************************************************
    //                  PÁGINA DE INICIO
    // ****************************************************************************************************************

    private void showBannerPage() {

        flowPaneBackground.getChildren().clear();

        ImageView banner = BrandRestController.getBanner();
        banner.setFitWidth(1000);
        banner.setPreserveRatio(true);
        flowPaneBackground.getChildren().add(banner);

        ScrollPane brandsPane = getScroll(brandRestController.getAllBrands(brandFilters));
        brandsPane.setPrefWidth(1000);
        flowPaneBackground.getChildren().add(brandsPane);

    }

    private void showMainPage() {

        paneFiltersLeft.setVisible(false);
        closeFiltersPanel();
        menuButtonSort.setVisible(false);

        // Agrega los botones acordes al usuario
        AppUserDTO user = storeApplication.getAppUser();

        if (user instanceof ClientDTO) {

            showButtonsClient();
            loadFiltersPanel();
            showBannerPage();

        } else if (user instanceof BrandUserDTO) {

            labelBrands.setVisible(false);
            flowPaneBrands.setVisible(false);
            loadFiltersPanel();
            showButtonsBrand();
            setProducts(requestProducts());

        } else if (user instanceof StoreUserDTO) {

            buttonFilters.setVisible(false);
            showButtonsStore();
            setProducts(storeRestController.getStoreProducts(true));

        } else {

            showButtonsDefault();
            loadFiltersPanel();
            showBannerPage();

        }

    }

    /**
     * Hace un request de productos por marca, y los muestra en pantalla
     * @param brand Marca elegida
     */
    public void selectedBrand(BrandDTO brand) {

        productFilters.setBrand_id(brand.getId());
        setProducts(requestProducts());

    }

    // ****************************************************************************************************************
    //                  BOTONES
    // ****************************************************************************************************************

    /**
     * Agrega los botones de un usuario sin cuenta, Carrito e Ingresar
     */
    private void showButtonsDefault() {

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
    private void showButtonsClient() {

        flowPaneButtons.getChildren().clear();

        Button seePurchases = new Button("Ver compras");
        seePurchases.setStyle("-fx-background-color: #ffffff");
        seePurchases.setOnMouseClicked(event -> {
            ListItemsController.showCheckBox = true;
            ListItemsController.isCart = false;
            storeApplication.sceneListItems("Lista de compras");
        });
        flowPaneButtons.getChildren().add(seePurchases);

        Button cart = new Button("Carrito");
        cart.setStyle("-fx-background-color: #ffffff");
        cart.setOnMouseClicked(event ->
                storeApplication.sceneCart());
        flowPaneButtons.getChildren().add(cart);

        Button logOut = new Button("Cerrar sesión");
        logOut.setStyle("-fx-background-color: #ffffff");
        logOut.setOnAction(event -> logOut());
        flowPaneButtons.getChildren().add(logOut);

    }

    /**
     * Agrega los botones de una Marca, Nuevo Producto y Marca.
     */
    private void showButtonsBrand() {

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
    private void showButtonsStore() {

        flowPaneButtons.getChildren().clear();

        Button sales = new Button("Ventas");
        sales.setStyle("-fx-background-color: #ffffff");
        sales.setOnAction(event ->{
                ListItemsController.isCart = false;
                ListItemsController.showCheckBox = true;
                storeApplication.sceneListItems("Ventas");
                });
        flowPaneButtons.getChildren().add(sales);

        Button newProduct = new Button("Agregar producto");
        newProduct.setStyle("-fx-background-color: #ffffff");
        newProduct.setOnAction(event -> {

            List<ProductDTO> list = storeRestController.getStoreProducts(false);
            setProducts(list);

        });
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

    private void loadFiltersPanel() {

        // Setea el panel de filtros
        setCategories(Categories.getGenre());
        setColors();
        setSizes();
        setBrands();

    }

    /**
     * Agrega las categorias al panel de filtro.
     * Toma las categorias de la clase estatica Categories.
     * @see Categories
     * Si level se encuentra en el valor 1, toma la nueva lista de String
     * @see #pressedCategory(String)
     */
    private void setCategories(String[] categoryList) {

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
     * @param type Nombre de la categoria presionada
     */
    private void pressedCategory(String type) {


        // trousers, shirt, hoodie

        // int manga larga, manga corta, etc...


        if (productFilters.getGender() == null) {

            productFilters.setGender(type.charAt(0));
            setCategories(Categories.getSubCategory(type));

        } else if (productFilters.getType() == null) {

            productFilters.setType(Categories.castCategory(type));
            Label arrow = new Label(" > ");
            flowPaneCategoryLabels.getChildren().add(arrow);
            setCategories(Categories.getSubCategory(productFilters.getGender().toString(), type));

        } else {
            productFilters.setSubtype(Categories.getIntSubcategory(type));
            Label arrow = new Label(" > ");
            flowPaneCategoryLabels.getChildren().add(arrow);
            flowPaneCategory.getChildren().clear();

        }

        Label label = new Label(type);
        flowPaneCategoryLabels.getChildren().add(label);
        setSizes();

        setProducts(requestProducts());

    }


    /**
     * Agrega los colores al panel de filtro.
     * Toma los colores de la clase estatica Colors.
     * @see Colors
     * Al apretar un color, llama la funcion
     * @see #requestByColor(String)
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
            colorCircle.setOnMouseClicked(event -> requestByColor(color));
            flowPaneColors.getChildren().add(colorPane);

        }

    }


    /**
     * Hace un request de productos por color
     * @param color Color por el cual va a hacer filtro.
     */
    private void requestByColor(String color) {

        productFilters.setColor(color);
        setProducts(requestProducts());
        setColors();

    }



    /**
     * Agrega los talles al panel de filtro.
     * Toma los talles de la clase estatica Sizes.
     * @see Sizes
     * Al apretar un talle, llama la funcion
     * @see #requestBySize(String) para hacer el request
     */
    private void setSizes(){

        flowPaneSizes.getChildren().clear();

        List<String> sizes = null;



        if (productFilters.getType() == "trousers"){
            sizes = TrousersDTO.getSizes();
        } else if (productFilters.getType() == "shirt"){
            sizes = ShirtDTO.getSizes();
        } else if (productFilters.getType() == "hoodie"){
            sizes = HoodieDTO.getSizes();
        } else {
            HashMap<String, String> sizeHash = new LinkedHashMap<>();
            for (String s : ShirtDTO.getSizes()){
                sizeHash.put(s, s);
            }
            for (String s : HoodieDTO.getSizes()){
                sizeHash.put(s, s);
            }
            for (String s : TrousersDTO.getSizes()){
                sizeHash.put(s, s);
            }
            sizes = sizeHash.values().stream().collect(Collectors.toList());
        }

        for (String size: sizes) {

            Pane paneSize = Sizes.getPane(size);

            if (productFilters.getSize() != null) {

                if (productFilters.getSize().equals(size)) paneSize.setStyle("-fx-background-color: #888888");

            }

            paneSize.setOnMouseClicked(event -> requestBySize(size));
            flowPaneSizes.getChildren().add(paneSize);

        }

    }

    /**
     * Hace un request de productos por talle
     * @param size Talle por el cual va a hacer filtro.
     */
    private void requestBySize(String size) {

        menuButtonSort.setVisible(true);
        productFilters.setSize(size);
        setProducts(requestProducts());
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

                paneBrand.setOnMouseClicked(event -> requestByBrand(brandID));
                flowPaneBrands.getChildren().add(paneBrand);

            }

        }

    }

    /**
     * Hace un request de productos por marca
     * @param brand Marca por la cual va a hacer filtro.
     */
    private void requestByBrand(Integer brand) {

        productFilters.setBrand_id(brand);
        setProducts(requestProducts());
        setBrands();

    }

    // ****************************************************************************************************************
    //                  PRODUCTOS
    // ****************************************************************************************************************

    /**
     *
     */
    private void setProducts(List<ProductDTO> productsList) {

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
    //                  BOTONES FXML - FILTERS
    // ****************************************************************************************************************


    /**
     * Vuelve al home, mostrando nuevamente la pantalla con banner y marcas
     */
    @FXML
    void pressedHome(ActionEvent event) {

        showMainPage();
        productFilters = new ProductFilters();

    }

    @FXML
    void pressedHighFirst(ActionEvent event) {

        productFilters.setOrder(-1);
        setProducts(requestProducts());

    }

    @FXML
    void pressedLowFirst(ActionEvent event) {

        productFilters.setOrder(1);
        setProducts(requestProducts());

    }

    /** Abre y cierra el panel de filtros a la izquierda */
    @FXML
    void pressedFilters(ActionEvent event) {

        if (!filters) {

            AnchorPane.setLeftAnchor(paneBackground, 200d);
            paneFiltersLeft.setVisible(true);
            filters = true;

        } else closeFiltersPanel();

    }

    private void closeFiltersPanel() {

        paneFiltersLeft.setVisible(false);
        AnchorPane.setLeftAnchor(paneBackground, 0d);
        filters = false;

    }

    /** Elimina todos los filtros, y muestra la totalidad de los productos */
    @FXML
    void pressedClean(MouseEvent event) { clearFilters(); }

    private void clearFilters() {

        flowPaneBackground.getChildren().clear();
        flowPaneCategoryLabels.getChildren().clear();

        productFilters = new ProductFilters();

        setCategories(Categories.getGenre());
        setProducts(requestProducts());
        setColors();
        setSizes();

    }

    @FXML
    void enteredMaxPrice(ActionEvent event) {

        String maxStr = fieldMaxPrice.getText();

        if (!maxStr.isEmpty()) {

            try {
                Double max = Double.parseDouble(maxStr);
                productFilters.setTo(max);
                setProducts(requestProducts());
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
                setProducts(requestProducts());
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
            setProducts(requestProducts());

        }

    }

    // ****************************************************************************************************************
    //                  REQUEST
    // ****************************************************************************************************************

    public List<ProductDTO> requestProducts(){

        List<ProductDTO> productList = null;

        AppUserDTO user = storeApplication.getAppUser();
        if (user == null || user instanceof ClientDTO) productList = productRestController.getProducts(productFilters);
        else if (user instanceof BrandUserDTO) {
            productFilters.setBrand_id(((BrandUserDTO) user).getBrand().getId());
            productFilters.setHasStock(false);
            productList = productRestController.getProducts(productFilters);

        }
        else if (user instanceof StoreUserDTO) productList = storeRestController.getStoreProducts(false);

        return productList;

    }

    // ****************************************************************************************************************
    //                  PANE
    // ****************************************************************************************************************

    private ScrollPane getScroll(List<BrandDTO> brands) {

        ScrollPane scroll = new ScrollPane();
        scroll.setPrefHeight(150);
        scroll.setMinSize(500, 150);
        scroll.setMaxHeight(150);

        FlowPane flow = new FlowPane();
        flow.setPrefHeight(135);
        flow.setMaxHeight(135);
        flow.setMaxWidth(Region.USE_COMPUTED_SIZE);
        flow.setVgap(10);
        flow.setHgap(10);
        flow.setOrientation(Orientation.VERTICAL);
        flow.setPrefWrapLength(140);

        for (BrandDTO brand: brands) {

            StackPane paneBackground = new StackPane();
            paneBackground.setStyle("-fx-background-color: #e2e2e2");
            paneBackground.setMinSize(135, 135);
            paneBackground.setPrefSize(135, 135);
            paneBackground.setMaxSize(135, 135);
            Label labelBrand = new Label(brand.getName());
            labelBrand.setFont(Font.font("Cambria", FontWeight.BOLD, 28));
            labelBrand.setWrapText(true);
            paneBackground.getChildren().add(labelBrand);
            paneBackground.setOnMouseClicked(event -> selectedBrand(brand));
            flow.getChildren().add(paneBackground);

        }

        scroll.setContent(flow);

        return scroll;

    }

}
