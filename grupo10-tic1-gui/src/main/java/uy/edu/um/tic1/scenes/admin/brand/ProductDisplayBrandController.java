package uy.edu.um.tic1.scenes.admin.brand;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.tic1.StoreApplication;
import uy.edu.um.tic1.entities.attributes.Categories;
import uy.edu.um.tic1.entities.attributes.Colors;
import uy.edu.um.tic1.entities.attributes.Sizes;
import uy.edu.um.tic1.entitites.SizeAndColorDTO;
import uy.edu.um.tic1.entitites.product.ProductDTO;
import uy.edu.um.tic1.requests.SizeAndColorRestController;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

@Component
@FxmlView("/uy/edu/um/tic1/scenes/admin/brand/productDisplayBrand.fxml")
public class ProductDisplayBrandController implements Initializable {

    @Autowired
    StoreApplication storeApplication;

    private Set<SizeAndColorDTO> sizeAndColorSet;
    public static ProductDTO product;
    private Character genre;
    private int category = -1;

    @Autowired
    SizeAndColorRestController sizeAndColorRestController;


    @FXML
    private Button inicio;

    @FXML
    private TextField productTitle;
    @FXML
    private Label productBrand;
    @FXML
    private FlowPane flowPaneColors;
    @FXML
    private TextField productPrice;
    @FXML
    private ImageView productImage;
    @FXML
    private Pane productNewImage;
    @FXML
    private Button save;
    @FXML
    private MenuButton menuButtonGenre;
    @FXML
    private Label labelGenre;
    @FXML
    private MenuButton menuButtonCategory;
    @FXML
    private Label labelCategory;


    @FXML
    void inicioPressed(ActionEvent event) {
        storeApplication.sceneMain();
    }

    @FXML
    void droppedNewImage(DragEvent event) {

    }

    @FXML
    void pressedSave(ActionEvent event) {

        product.setName(productTitle.getText());
        try {
            Double price = Double.parseDouble(productPrice.getText());
            product.setPrice(price);
        } catch (NumberFormatException e) {
            System.out.println("El precio agregado no es un número");
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        this.menuButtonGenre = new MenuButton();
        this.menuButtonCategory = new MenuButton();


        if (product == null)
            setGenre();

        else {

            setPage();
            sizeAndColorSet = product.getSizeAndColor();

        }

    }

    private void setPage() {

        productTitle.setText(product.getName());
        productBrand.setText(product.getBrand().getName());
        productPrice.setText(String.valueOf(product.getPrice()));
        productTitle.setText(product.getName());
        byte[] image = product.getImage();
        Image productImg;
        if (image != null) {
            productImg = new Image(new ByteArrayInputStream(image));
        } else {
            productImg = new Image("/uy/edu/um/tic1/images/no_image.jpg");
        }
        productImage.setImage(productImg);

        setGenre();
        initSizeAndColors();

    }

    // ****************************************************************************************************************
    //                  Verificar existencia de talles y colores
    // ****************************************************************************************************************

    /**
     * Verifica si se encuentra un color en la lista SizeAndColorDTO del producto
     * @param color Color por el que va a verificar
     * @return Booleano verificando que el color se encuentra en la lista
     */
    private Boolean isColor(String color){

        for (SizeAndColorDTO sizeAndColor: sizeAndColorSet) {

            if (sizeAndColor.getColor().equals(color)) return true;

        }

        return false;

    }

    /**
     * Verifica si se encuentra un color junto con un talle en la lista SizeAndColorDTO del producto
     * @param color Color por el que va a verificar
     * @param size Talle por el que va a verificar
     * @return Booleano verificando que el color y talle se encuentra en la lista
     */
    private Boolean isSize(String color, String size){

        for (SizeAndColorDTO sizeAndColor: sizeAndColorSet) {

            if (sizeAndColor.getColor().equals(color) && sizeAndColor.getSize().equals(size)) return true;

        }

        return false;

    }

    // ****************************************************************************************************************
    //                  Gestionar colores y talles
    // ****************************************************************************************************************

    private void initSizeAndColors() {

        for (String color: ProductDTO.getColors()) {

            Pane paneColor = new Pane();
            paneColor.setPrefSize(565, 60);

            if (isColor(color)) paneColor.setStyle("-fx-background-color: #CDCDCD");
            else paneColor.setStyle("-fx-background-color: #E2E2E2");

            Circle circle = Colors.getCircle(color, 16.0f);
            circle.setLayoutX(44);
            circle.setLayoutY(30);
            circle.setOnMouseClicked(event -> {
                String colorStyle = paneColor.getStyle().substring(paneColor.getStyle().length() - 6);
                if (colorStyle.equals("CDCDCD")) {
                    paneColor.setStyle("-fx-background-color: #E2E2E2");
                    removeColor(color);
                    setPage();
                } else paneColor.setStyle("-fx-background-color: #CDCDCD");
            });
            paneColor.getChildren().add(circle);

            FlowPane flowPaneSizes = new FlowPane();
            flowPaneSizes.setHgap(5);
            flowPaneSizes.setVgap(5);
            flowPaneSizes.setPrefSize(482, 60);
            flowPaneSizes.setPadding(new Insets(3, 3, 3, 3));
            flowPaneSizes.setLayoutX(81);
            flowPaneSizes.setLayoutY(0);
            flowPaneSizes.setStyle("-fx-background-color: #e2e2e2");
            paneColor.getChildren().add(flowPaneSizes);

            List<String> sizes = Sizes.getSizes(null);

            for (String size: sizes) {

                Pane paneSize = new Pane();
                paneSize.setPrefSize(48,50);
                paneSize.setOnMouseClicked(event -> {
                    String colorStyle = paneSize.getStyle().substring(paneSize.getStyle().length() - 6);
                    if (colorStyle.equals("BBBBBB")) {
                        paneSize.setStyle("-fx-background-color: #E2E2E2");
                        removeSize(color, size);
                    } else {
                        paneSize.setStyle("-fx-background-color: #BBBBBB");
                        addSize(color, size);
                    }
                });

                if (isSize(color, size)) paneSize.setStyle("-fx-background-color: #BBBBBB");
                else paneSize.setStyle("-fx-background-color: #E2E2E2");

                Label sizeLetter = new Label(size);
                sizeLetter.setFont(Font.font("Cambria", 25));
                StackPane stackPaneSizeLabel = new StackPane(sizeLetter);
                stackPaneSizeLabel.setPrefSize(45, 28);
                stackPaneSizeLabel.setLayoutX(2);
                stackPaneSizeLabel.setLayoutY(0);
                paneSize.getChildren().add(stackPaneSizeLabel);

                flowPaneSizes.getChildren().add(paneSize);

            }

            flowPaneColors.getChildren().add(paneColor);

        }

    }

    private void removeColor(String color) {

        product.removeSizeAndColorByColor(color);

    }

    private void addSize(String color, String size) {

        SizeAndColorDTO newSizeAndColor = sizeAndColorRestController.getSizeAndColor(size, color).get(0);
        product.addSizeAndColor(newSizeAndColor);

    }

    private void removeSize(String color, String size) {

        product.removeSizeAndColorBySizeAndColor(size, color);

    }

    // ****************************************************************************************************************
    //                  Menús de genero y categoría
    // ****************************************************************************************************************

    private void setGenre() {

        menuButtonGenre.getItems().clear();

        for (String string : Categories.getGenre()) {

            MenuItem item = new MenuItem(string);
            item.setOnAction(event -> {
                menuButtonGenre.setStyle("-fx-background-color: #E2E2E2");
                menuButtonCategory.setStyle("-fx-background-color: #CDCDCD");
                product.setGender(string.charAt(0));
                setCategory();
            });
            menuButtonGenre.getItems().add(item);

        }

    }

    private void setCategory() {

        menuButtonCategory.getItems().clear();

        for (String string : Categories.getSubCategory(product.getGender().toString())) {

            MenuItem item = new MenuItem(string);
            item.setOnAction(event -> {
                menuButtonCategory.setStyle("-fx-background-color: #E2E2E2");
                product.setSubcategory(Categories.getIntCategory(string));
                initSizeAndColors();
            });
            menuButtonGenre.getItems().add(item);

        }

    }

}
