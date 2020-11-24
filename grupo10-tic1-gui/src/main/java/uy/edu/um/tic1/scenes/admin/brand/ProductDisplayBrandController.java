package uy.edu.um.tic1.scenes.admin.brand;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.tic1.StoreApplication;
import uy.edu.um.tic1.entities.attributes.Categories;
import uy.edu.um.tic1.entities.attributes.Colors;
import uy.edu.um.tic1.entities.attributes.Sizes;
import uy.edu.um.tic1.entitites.BrandDTO;
import uy.edu.um.tic1.entitites.SizeAndColorDTO;
import uy.edu.um.tic1.entitites.product.HoodieDTO;
import uy.edu.um.tic1.entitites.product.ProductDTO;
import uy.edu.um.tic1.entitites.product.ShirtDTO;
import uy.edu.um.tic1.entitites.product.TrousersDTO;
import uy.edu.um.tic1.entitites.users.BrandUserDTO;
import uy.edu.um.tic1.requests.ProductRestController;
import uy.edu.um.tic1.requests.SizeAndColorRestController;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

@Component
@FxmlView("/uy/edu/um/tic1/scenes/admin/brand/productDisplayBrand.fxml")
public class ProductDisplayBrandController implements Initializable {

    @Autowired
    StoreApplication storeApplication;
    @Autowired
    SizeAndColorRestController sizeAndColorRestController;
    @Autowired
    private ProductRestController productRestController;

    public static ProductDTO tempProduct;
    private Character genre;
    private String selectedCategory;

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
    private Button uploadImage;
    @FXML
    private Button save;
    @FXML
    private MenuButton menuButtonGenre;
    @FXML
    private Label labelGenre;
    @FXML
    private MenuButton menuButtonCategory;
    @FXML
    private MenuButton menuButtonSubcategory;
    @FXML
    private Label labelCategory;
    @FXML
    private Label labelSubcategory;
    @FXML
    private StackPane errorMessage;
    @FXML
    private Button deleteProduct;
    @FXML
    private TextArea productDescription;

    // ****************************************************************************************************************
    //                  INITIALIZE
    // ****************************************************************************************************************

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        BrandUserDTO brandUserDTO = (BrandUserDTO) storeApplication.getAppUser();
        productBrand.setText(brandUserDTO.getBrand().getName());

        errorMessage.setVisible(false);

        labelGenre.setText("");
        labelCategory.setText("");

        if (tempProduct == null){
            setGenre();
        }
        else setPage();

    }

    // ****************************************************************************************************************
    //                  BOTONES FXML
    // ****************************************************************************************************************

    @FXML
    void inicioPressed(ActionEvent event) {
        storeApplication.sceneMain();
    }

    @FXML
    void pressedUploadImage(ActionEvent event) {

        if (tempProduct != null) {

            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(StoreApplication.getPrimaryStage());
            if (file != null) {
                byte[] bytes;
                try {
                    bytes = Files.readAllBytes(file.toPath());
                    productImage.setImage(getImage(bytes));
                    tempProduct.setImage(bytes);
                } catch (IOException e) {
                    System.out.println("Error en la imagen");
                }

            }

        } else errorMessage.setVisible(true);

    }

    @FXML
    void pressedSave(ActionEvent event) {

        if (tempProduct != null) {

            if (!productTitle.getText().isEmpty()) tempProduct.setName(productTitle.getText());
            if (!productPrice.getText().isEmpty()) {
                double price = 0d;
                try {
                    price = Double.parseDouble(productPrice.getText());
                    tempProduct.setPrice(price);
                } catch (NumberFormatException e) {
                    System.out.println("El valor no es un número");
                }
            }
            if (!productDescription.getText().isEmpty()) tempProduct.setDescription(productDescription.getText());

            if (hasAttributeNull()) {
                errorMessage.setVisible(true);
            } else {
                productRestController.saveProduct(tempProduct);
                storeApplication.sceneMain();
            }

        }
        else errorMessage.setVisible(true);

    }

    @FXML
    void pressedDeleteProduct(ActionEvent event) {

        if (tempProduct != null) productRestController.deleteProduct(tempProduct);

    }

    // ****************************************************************************************************************
    //                  PAGE
    // ****************************************************************************************************************

    private void setPage() {

        productTitle.setText(tempProduct.getName());
        productPrice.setText(String.valueOf(tempProduct.getPrice()));
        productTitle.setText(tempProduct.getName());
        byte[] image = tempProduct.getImage();
        Image productImg = getImage(image);
        productImage.setImage(productImg);
        productDescription.setText(tempProduct.getDescription());

        labelGenre.setText(tempProduct.getGender().toString());
        labelCategory.setText(Categories.getCategoryFromInt(tempProduct.getSubcategory()));

        setGenre();
        initSizeAndColors();

    }

    private Image getImage(byte[] image) {

        if (image != null) {
            return new Image(new ByteArrayInputStream(image));
        } else {
            return new Image("/uy/edu/um/tic1/images/no_image.jpg");
        }

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

        for (SizeAndColorDTO sizeAndColor: tempProduct.getSizeAndColor()) {

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

        for (SizeAndColorDTO sizeAndColor: tempProduct.getSizeAndColor()) {

            if (sizeAndColor.getColor().equals(color) && sizeAndColor.getSize().equals(size)) return true;

        }

        return false;

    }

    // ****************************************************************************************************************
    //                  Gestionar colores y talles
    // ****************************************************************************************************************

    private void initSizeAndColors() {

        flowPaneColors.getChildren().clear();

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

            List<String> sizes = tempProduct.allSizes();

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

        tempProduct.removeSizeAndColorByColor(color);

    }

    private void addSize(String color, String size) {

        SizeAndColorDTO newSizeAndColor = null;
        List<SizeAndColorDTO> sizeColorList = sizeAndColorRestController.getSizeAndColor(size, color);

        if(sizeColorList.isEmpty()){
            newSizeAndColor = SizeAndColorDTO.builder().color(color).size(size).build();
        }else{
            newSizeAndColor = sizeColorList.get(0);
        }
        tempProduct.addSizeAndColor(newSizeAndColor);

    }

    private void removeSize(String color, String size) {

        tempProduct.removeSizeAndColorBySizeAndColor(size, color);

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
                labelGenre.setText(Character.toString(string.charAt(0)));
                labelCategory.setText("");
                genre = string.charAt(0);
                setCategory();
            });
            menuButtonGenre.getItems().add(item);

        }

    }

    private void setCategory() {

        menuButtonCategory.getItems().clear();

        for (String string : Categories.getSubCategory(genre.toString())) {

            MenuItem item = new MenuItem(string);
            item.setOnAction(event -> {

                menuButtonCategory.setStyle("-fx-background-color: #E2E2E2");
                menuButtonSubcategory.setStyle("-fx-background-color: #CDCDCD");
                labelCategory.setText(string);
                String category = Categories.castCategory(string);
                selectedCategory = category;

                if (tempProduct == null) tempProduct = initNewProduct(category);
                else {
                    ProductDTO newProduct = initNewProduct(string);
                    if (newProduct.getClass() != tempProduct.getClass() ) {
                        cloneToNewProduct(newProduct);
                    }
                }
                errorMessage.setVisible(false);

                setSubcategory();
                initSizeAndColors();
            });
            menuButtonCategory.getItems().add(item);

        }

    }

    private void setSubcategory() {

        menuButtonSubcategory.getItems().clear();

        for (String string : Categories.getSubCategory(genre.toString(), selectedCategory)) {

            MenuItem item = new MenuItem(string);
            item.setOnAction(event -> {

                menuButtonSubcategory.setStyle("-fx-background-color: #E2E2E2");
                labelSubcategory.setText(string);
                tempProduct.setSubcategory(Categories.getIntSubcategory(string));
                initSizeAndColors();

            });
            menuButtonSubcategory.getItems().add(item);

        }

    }

    // ****************************************************************************************************************
    //                  Producto
    // ****************************************************************************************************************

    private ProductDTO initNewProduct(String category) {

        BrandUserDTO brandUserDTO = (BrandUserDTO) storeApplication.getAppUser();
        BrandDTO brand = brandUserDTO.getBrand();

        ProductDTO product;
        switch (category) {
            case "hoodie":
                product = HoodieDTO.builder().build();
                break;
            case "trousers":
                product = TrousersDTO.builder().build();
                break;
            case "shirt":
                product = ShirtDTO.builder().build();
                break;
            default:
                product = ShirtDTO.builder().build();
        }

        product.setGender(genre);
        product.setSizeAndColor(new LinkedHashSet<>());
        product.setBrand(brand);

        return product;

    }

    private void cloneToNewProduct(ProductDTO newProduct) {

        newProduct.setImage(tempProduct.getImage());
        newProduct.setSubcategory(tempProduct.getSubcategory());
        newProduct.setGender(tempProduct.getGender());
        newProduct.setPrice(tempProduct.getPrice());
        newProduct.setName(tempProduct.getName());
        newProduct.setBrand(tempProduct.getBrand());
        newProduct.setId(tempProduct.getId());
        newProduct.setSizeAndColor(tempProduct.getSizeAndColor());

        tempProduct = newProduct;

    }

    private Boolean hasAttributeNull() {

        return tempProduct.getName().isEmpty() || tempProduct.getDescription().isEmpty() ||
                tempProduct.getSubcategory().toString().isEmpty() || tempProduct.getPrice().toString().isEmpty();

    }

}
