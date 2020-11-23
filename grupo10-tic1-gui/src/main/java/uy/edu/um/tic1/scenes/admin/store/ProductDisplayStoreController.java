package uy.edu.um.tic1.scenes.admin.store;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.tic1.StoreApplication;
import uy.edu.um.tic1.entities.attributes.Categories;
import uy.edu.um.tic1.entities.attributes.Colors;
import uy.edu.um.tic1.entitites.SizeAndColorDTO;
import uy.edu.um.tic1.entitites.StockDTO;
import uy.edu.um.tic1.entitites.StoreDTO;
import uy.edu.um.tic1.entitites.product.ProductDTO;
import uy.edu.um.tic1.requests.SizeAndColorRestController;
import uy.edu.um.tic1.requests.StoreRestController;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Component
@FxmlView("/uy/edu/um/tic1/scenes/admin/store/productDisplayStore.fxml")
public class ProductDisplayStoreController implements Initializable {

    @Autowired
    StoreApplication storeApplication;
    @Autowired
    private StoreRestController storeRestController;
    @Autowired
    SizeAndColorRestController sizeAndColorRestController;

    public static ProductDTO product;


    @FXML
    private Button inicio;
    @FXML
    private Label productBrand;
    @FXML
    private FlowPane flowPaneColors;
    @FXML
    private ImageView productImage;
    @FXML
    private Label labelTitle;
    @FXML
    private Label labelPrice;
    @FXML
    private Label labelGenre;
    @FXML
    private Label labelCategory;

    // ****************************************************************************************************************
    //                  INITIALIZE
    // ****************************************************************************************************************

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        if (product != null) {

            labelTitle.setText(product.getName());
            productBrand.setText(product.getBrand().getName());
            labelPrice.setText(product.getPrice().toString() + "$");
            labelGenre.setText(product.getGender().toString());
            labelCategory.setText(Categories.getCategoryFromInt(product.getSubcategory()));
            productImage.setImage(getImage(product.getImage()));
            initSizeAndColors();

        }

    }

    // ****************************************************************************************************************
    //                  BUTTONS FXML
    // ****************************************************************************************************************

    @FXML
    void inicioPressed(ActionEvent event) {
        storeApplication.sceneMain();
    }

    // ****************************************************************************************************************
    //                  SET PAGE
    // ****************************************************************************************************************

    private Image getImage(byte[] bytes) {

        if (bytes != null) return new Image(new ByteArrayInputStream(bytes));
        else return new Image("/uy/edu/um/tic1/images/no_image.jpg");

    }

    // ****************************************************************************************************************
    //                  Gestionar colores y talles
    // ****************************************************************************************************************

    private void initSizeAndColors() {

        StoreDTO store = storeRestController.getStore();

        List<String> colors = product.colorsList().stream().map(SizeAndColorDTO::getColor).collect(Collectors.toList());

        for (String color: colors) {

            Pane paneColor = new Pane();
            paneColor.setPrefSize(606, 65);
            paneColor.setStyle("-fx-background-color: #E2E2E2");

            Circle circle = Colors.getCircle(color, 16.0f);
            circle.setLayoutX(42);
            circle.setLayoutY(33);
            paneColor.getChildren().add(circle);

            Label labelSize = new Label("Talle:");
            labelSize.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.ITALIC, 14));
            labelSize.setLayoutX(102);
            labelSize.setLayoutY(6);
            paneColor.getChildren().add(labelSize);

            Label labelQuantity = new Label("Cantidad:");
            labelQuantity.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.ITALIC, 14));
            labelQuantity.setLayoutX(76);
            labelQuantity.setLayoutY(37);
            paneColor.getChildren().add(labelQuantity);

            FlowPane flowPaneSizes = new FlowPane();
            flowPaneSizes.setHgap(5);
            flowPaneSizes.setVgap(5);
            flowPaneSizes.setPrefSize(463, 63);
            flowPaneSizes.setPadding(new Insets(3, 3, 3, 3));
            flowPaneSizes.setLayoutX(143);
            flowPaneSizes.setLayoutY(0);
            flowPaneSizes.setStyle("-fx-background-color: #e2e2e2");
            paneColor.getChildren().add(flowPaneSizes);

            List<String> sizes = product.getSizeAndColorByColor(color).stream().map(SizeAndColorDTO::getSize).collect(Collectors.toList());

            for (String size: sizes) {

                Integer quantity = 0;
                StockDTO stock = store.getStockBySizeAndColor(size, color);
                if(stock != null){
                    quantity = stock.getStock();
                }


                Pane paneSize = new Pane();
                paneSize.setPrefSize(60,56);
                paneSize.setStyle("-fx-background-color: #E2E2E2");

                StackPane stackPaneSize = new StackPane();
                stackPaneSize.setPrefSize(56, 25);
                stackPaneSize.setLayoutX(2);
                stackPaneSize.setLayoutY(2);
                Label sizeLetter = new Label(size);
                sizeLetter.setFont(Font.font("Cambria", FontWeight.BOLD, 16));
                stackPaneSize.getChildren().add(sizeLetter);
                paneSize.getChildren().add(stackPaneSize);
                TextField fieldQuantity = new TextField();
                fieldQuantity.setPromptText(quantity.toString());
                fieldQuantity.setStyle("-fx-background-color: #FFFFFF");
                fieldQuantity.setLayoutX(8);
                fieldQuantity.setLayoutY(27);
                Integer finalQuantity = quantity;

                fieldQuantity.setOnAction(event -> {
                    try {
                        int newQuantity = Integer.parseInt(fieldQuantity.getCharacters().toString());

                        if (newQuantity < 0) newQuantity = 0;
                        fieldQuantity.clear();
                        fieldQuantity.setPromptText(Integer.toString(newQuantity));
                        updateStock(size, color, newQuantity);

                    } catch (NumberFormatException e) {
                        fieldQuantity.clear();
                        fieldQuantity.setPromptText(finalQuantity.toString());
                    }

                });

                paneSize.getChildren().add(fieldQuantity);
                flowPaneSizes.getChildren().add(paneSize);

            }

            flowPaneColors.getChildren().add(paneColor);

        }

    }

    public void updateStock(String size, String color, Integer quantity) {

        StoreDTO store = storeRestController.getStore();
        StockDTO stock = store.getStockBySizeAndColor(size, color);

        if (stock == null) {

            stock = StockDTO.builder()
                    .product(product)
                    .sizeAndColor( product.getSizeAndColorBySizeAndColor(size, color) )
                    .stock(quantity)
                    .build();
            store.addStock(stock);

        } else store.updateStock(size, color, quantity);

        storeRestController.save(store);

    }

}
