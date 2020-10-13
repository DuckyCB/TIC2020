package uy.edu.um.tic1.scenes;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.tic1.JavaFxApplication;
import uy.edu.um.tic1.entity.ColorRGB;
import uy.edu.um.tic1.entity.PaneProduct;
import uy.edu.um.tic1.product.ProductRequest;
import uy.edu.um.tic1.product.Products;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("/uy/edu/um/tic1/scenes/mainMenu.fxml")
public class MainMenuController implements Initializable {

    @Autowired
    JavaFxApplication javaFxApplication;

    private Boolean user = Boolean.FALSE;
    private Boolean filters = Boolean.FALSE;

    @FXML
    private AnchorPane paneRoot;

    @FXML
    private AnchorPane paneFiltersLeft;

    @FXML
    private FlowPane flowPaneCategory;

    @FXML
    private FlowPane flowPaneColors;

    @FXML
    private FlowPane flowPaneSizes;

    @FXML
    private Label labellCleanFilters;

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
                javaFxApplication.sceneLogIn();
            });

        }

        genre();
        color();
        size();

        paneFiltersLeft.setVisible(false);

    }

    private void genre() {

        Pane men = paneCategory("Hombre");
        men.setOnMouseClicked(event -> {
            pressedMen();
        });
        flowPaneCategory.getChildren().add(men);

        Pane woman = paneCategory("Mujer");
        woman.setOnMouseClicked(event -> {
            pressedWoman();
        });
        flowPaneCategory.getChildren().add(woman);

        Pane boy = paneCategory("Niño");
        boy.setOnMouseClicked(event -> {
            pressedBoy();
        });
        flowPaneCategory.getChildren().add(boy);

        Pane girl = paneCategory("Niña");
        girl.setOnMouseClicked(event -> {
            pressedGirl();
        });
        flowPaneCategory.getChildren().add(girl);

        Pane baby = paneCategory("Bebé");
        baby.setOnMouseClicked(event -> {
            pressedBaby();
        });
        flowPaneCategory.getChildren().add(baby);

        Pane unisex = paneCategory("Unisex");
        unisex.setOnMouseClicked(event -> {
            pressedUnisex();
        });
        flowPaneCategory.getChildren().add(unisex);

    }

    private void color(){

        Circle white = paneColor(new ColorRGB(1.0f, 1.0f, 1.0f));
        white.setOnMouseClicked(event -> {
            pressedMen();
        });
        flowPaneColors.getChildren().add(white);

        Circle black = paneColor(new ColorRGB(0.0f, 0.0f, 0.0f));
        black.setOnMouseClicked(event -> {
            pressedMen();
        });
        flowPaneColors.getChildren().add(black);

        Circle red = paneColor(new ColorRGB(1.0f, 0.0f, 0.0f));
        red.setOnMouseClicked(event -> {
            pressedMen();
        });
        flowPaneColors.getChildren().add(red);

        Circle green = paneColor(new ColorRGB(0.0f, 1.0f, 0.0f));
        green.setOnMouseClicked(event -> {
            pressedMen();
        });
        flowPaneColors.getChildren().add(green);

        Circle blue = paneColor(new ColorRGB(0.0f, 0.0f, 1.0f));
        blue.setOnMouseClicked(event -> {
            pressedMen();
        });
        flowPaneColors.getChildren().add(blue);

    }

    private void size(){

        Pane s = paneSize("XS");
        s.setOnMouseClicked(event -> {
            pressedMen();
        });
        flowPaneSizes.getChildren().add(s);

        Pane xs = paneSize("S");
        xs.setOnMouseClicked(event -> {
            pressedMen();
        });
        flowPaneSizes.getChildren().add(xs);

        Pane m = paneSize("M");
        m.setOnMouseClicked(event -> {
            pressedMen();
        });
        flowPaneSizes.getChildren().add(m);

        Pane l = paneSize("L");
        l.setOnMouseClicked(event -> {
            pressedMen();
        });
        flowPaneSizes.getChildren().add(l);

        Pane xl = paneSize("XL");
        xl.setOnMouseClicked(event -> {
            pressedMen();
        });
        flowPaneSizes.getChildren().add(xl);
    }

    private Pane paneCategory(String category) {

        Label label = new Label(category);
        label.setFont(Font.font("Cambria", 16));
        label.setLayoutX(23);
        label.setLayoutY(3);

        Pane pane = new Pane();
        pane.setPrefSize(200, 30);
        pane.getChildren().add(label);
        pane.setStyle("-fx-background-color: #e2e2e2");

        return pane;

    }

    private Circle paneColor(ColorRGB color) {

        Circle circle = new Circle();
        circle.setRadius(16.0f);
        circle.setFill(new Color(color.getR(), color.getG(), color.getB(), 1.0));
        return circle;

    }

    private Pane paneSize(String size) {

        Label label = new Label(size);
        label.setFont(Font.font("Cambria", FontWeight.BOLD, 16));
        label.setLayoutX(6);
        label.setLayoutY(3);

        Pane pane = new Pane();
        pane.setPrefSize(32, 32);
        pane.getChildren().add(label);
        pane.setStyle("-fx-background-color: #e2e2e2");

        return pane;

    }

    private void pressedMen() {

        flowPaneCategory.getChildren().clear();
        categoryGeneric();
        ProductRequest.productsList = ProductRequest.listAll();
        setProducts();

    }

    private void pressedWoman() {

        flowPaneCategory.getChildren().clear();
        categoryGeneric();

    }

    private void pressedBoy() {

        flowPaneCategory.getChildren().clear();
        categoryGeneric();

    }

    private void pressedGirl() {

        flowPaneCategory.getChildren().clear();
        categoryGeneric();
        Pane dress = paneCategory("Vestido");
        dress.setOnMouseClicked(event -> {
            flowPaneCategory.getChildren().clear();
        });
        flowPaneCategory.getChildren().add(dress);

    }

    private void pressedBaby() {

        flowPaneCategory.getChildren().clear();
        categoryGeneric();

    }

    private void pressedUnisex() {

        flowPaneCategory.getChildren().clear();
        categoryGeneric();

    }

    private void categoryGeneric() {

        Pane shirt = paneCategory("Remeras");
        shirt.setOnMouseClicked(event -> {
            flowPaneCategory.getChildren().clear();
        });
        flowPaneCategory.getChildren().add(shirt);

        Pane jacket = paneCategory("Buzos");
        jacket.setOnMouseClicked(event -> {
            flowPaneCategory.getChildren().clear();
        });
        flowPaneCategory.getChildren().add(jacket);

        Pane pants = paneCategory("Pantalones");
        pants.setOnMouseClicked(event -> {
            flowPaneCategory.getChildren().clear();
        });
        flowPaneCategory.getChildren().add(pants);

        Pane underwear = paneCategory("Ropa interior");
        underwear.setOnMouseClicked(event -> {
            flowPaneCategory.getChildren().clear();
        });
        flowPaneCategory.getChildren().add(underwear);

        Pane shoes = paneCategory("Championes");
        shoes.setOnMouseClicked(event -> {
            flowPaneCategory.getChildren().clear();
        });
        flowPaneCategory.getChildren().add(shoes);

        Pane accesories = paneCategory("Accesorios");
        accesories.setOnMouseClicked(event -> {
            flowPaneCategory.getChildren().clear();
        });
        flowPaneCategory.getChildren().add(accesories);
    }

    private void setProducts() {

        if (ProductRequest.productsList != null) {

            for (Products product : ProductRequest.productsList) {

                System.out.println("producto");

                Pane pane = PaneProduct.paneGeneric(product.getImage(), product.getName(), product.getBrand(), product.getPrice(), product.getColors(), product.getSizes());

                pane.setOnMouseClicked(event -> {
                    javaFxApplication.sceneProductDisplay(product.getName());
                });

                flowPaneBackground.getChildren().add(pane);

            }

        }

    }


    @FXML
    void homePressed(ActionEvent event) {

    }

    @FXML
    void cartPressed(ActionEvent event) {

    }

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

    @FXML
    void pressedClean(MouseEvent event) {

        flowPaneCategory.getChildren().clear();
        genre();

    }

    @FXML
    void enteredMaxPrice(ActionEvent event) {

    }

    @FXML
    void enteredMinPrice(ActionEvent event) {

    }

    public MainMenuController() {
    }

}
