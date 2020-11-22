package uy.edu.um.tic1.entities.elements;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import org.springframework.beans.factory.annotation.Autowired;
import uy.edu.um.tic1.StoreApplication;
import uy.edu.um.tic1.entities.attributes.Colors;
import uy.edu.um.tic1.entitites.cart.CartItemDTO;

import java.io.ByteArrayInputStream;

import java.util.List;


public class PaneProduct {

    @Autowired
    private static StoreApplication storeApplication;

    public static Pane paneGeneric(byte[] image, String name, String brand, Double price, List<String> colors, List<String>  sizes) {

        Pane paneProduct = new Pane();
        paneProduct.setPrefSize(500, 320);
        paneProduct.setMinSize(500, 320);
        paneProduct.setMaxSize(500, 320);
        paneProduct.setStyle("-fx-background-color: #e2e2e2");

        // IMAGE
//        ByteArrayInputStream bis = new ByteArrayInputStream(image);
//        Iterator<?> readers = ImageIO.getImageReadersByFormatName("jpg");
//        ImageReader reader = (ImageReader) readers.next();
//        Object source = bis;
//        ImageInputStream iis = null;
//        try {
//            iis = ImageIO.createImageInputStream(source);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        reader.setInput(iis, true);
//        ImageReadParam param = reader.getDefaultReadParam();
//        Image imageFile = null;
//        try {
//            imageFile = reader.read(0, param);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        ImageView productImage = new ImageView(imageFile);
        ImageView  productImage = null;
        if (image != null){
            Image img = new Image(new ByteArrayInputStream(image));
            productImage = new ImageView(img);
        }else{
            productImage = new ImageView("/uy/edu/um/tic1/images/no_image.jpg");
        }

        productImage.setFitWidth(225);
        productImage.setFitHeight(300);
        productImage.setLayoutX(15);
        productImage.setLayoutY(10);
        paneProduct.getChildren().add(productImage);

        // NAME
        StackPane paneName = new StackPane();
        paneName.setPrefSize(235, 70);
        paneName.setLayoutX(250);
        paneName.setLayoutY(10);
        Label productName = new Label(name);
        productName.setFont(Font.font("Cambria", FontWeight.BOLD, 28));
        productName.setWrapText(true);
        paneName.getChildren().add(productName);
        paneProduct.getChildren().add(paneName);

        // BRAND
        Label productBrand = new Label(brand);
        productBrand.setFont(Font.font("Cambria", FontPosture.ITALIC, 24));
        productBrand.setLayoutX(250);
        productBrand.setLayoutY(75);
        paneProduct.getChildren().add(productBrand);

        // PRICE
        StackPane panePrice = new StackPane();
        panePrice.setPrefSize(200, 45);
        panePrice.setLayoutX(268);
        panePrice.setLayoutY(110);
        Label productPrice = new Label(price.toString()+" $UY");
        productPrice.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.ITALIC, 28));
        panePrice.getChildren().add(productPrice);
        paneProduct.getChildren().add(panePrice);

        // SIZE
        FlowPane flowPaneSize = new FlowPane();
        flowPaneSize.setPrefSize(235, 35);
        flowPaneSize.setLayoutX(250);
        flowPaneSize.setLayoutY(177);
        flowPaneSize.setVgap(5);
        flowPaneSize.setHgap(5);
        flowPaneSize.setPadding(new Insets(3, 3, 3, 3));

        for (String size: sizes) {

            StackPane paneSize = new StackPane();
            paneSize.setPrefSize(32,32);
            paneSize.setStyle("-fx-background-color: #cdcdcd");
            Label sizeLetter = new Label(size);
            sizeLetter.setFont(Font.font("Cambria", FontWeight.BOLD, 26));
            paneSize.getChildren().add(sizeLetter);
            flowPaneSize.getChildren().add(paneSize);

        }

        paneProduct.getChildren().add(flowPaneSize);

        // COLORS
        FlowPane flowPaneColors = new FlowPane();
        flowPaneColors.setPrefSize(235, 68);
        flowPaneColors.setLayoutX(250);
        flowPaneColors.setLayoutY(235);
        flowPaneColors.setVgap(5);
        flowPaneColors.setHgap(5);
        flowPaneColors.setPadding(new Insets(3, 3, 3, 3));

        for (String color: colors) {

            Circle circle = new Circle();
            circle.setRadius(16.0f);
            circle.setStyle("-fx-fill: #" + color);
            flowPaneColors.getChildren().add(circle);

        }

        paneProduct.getChildren().add(flowPaneColors);

        return paneProduct;

    }

    public static Pane createCartItem(Image image, String name, String brand, Float price, String color, String size, Boolean enoughStock) {

        Pane paneProduct = new Pane();
        paneProduct.setPrefSize(685, 150);
        paneProduct.setStyle("-fx-background-color: #e2e2e2");

        // IMAGE
        ImageView productImage = new ImageView(image);
        productImage.setFitWidth(111);
        productImage.setFitHeight(148);
        productImage.setLayoutX(15);
        productImage.setLayoutY(1);
        paneProduct.getChildren().add(productImage);

        // NAME
        StackPane paneName = new StackPane();
        paneName.setPrefSize(450, 40);
        paneName.setLayoutX(139);
        paneName.setLayoutY(14);
        Label productName = new Label(name);
        productName.setFont(Font.font("Cambria", FontWeight.BOLD, 24));
        productName.setWrapText(true);
        paneName.getChildren().add(productName);
        paneProduct.getChildren().add(paneName);

        // BRAND
        Label productBrand = new Label(brand);
        productBrand.setFont(Font.font("Cambria", FontPosture.ITALIC, 20));
        productBrand.setLayoutX(139);
        productBrand.setLayoutY(64);
        paneProduct.getChildren().add(productBrand);

        // PRICE
        Label labelPrice = new Label("Precio:");
        labelPrice.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.ITALIC, 20));
        labelPrice.setLayoutX(137);
        labelPrice.setLayoutY(112);
        paneProduct.getChildren().add(labelPrice);
        Label productPrice = new Label(price.toString()+" $UY");
        productPrice.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.ITALIC, 20));
        productPrice.setLayoutX(210);
        productPrice.setLayoutY(112);
        paneProduct.getChildren().add(productPrice);

        // COLORS
        Label labelColor = new Label("Color:");
        labelColor.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.ITALIC, 20));
        labelColor.setLayoutX(336);
        labelColor.setLayoutY(64);
        paneProduct.getChildren().add(labelColor);
        Circle circleColor = Colors.getCircle(color, 23f);
        circleColor.setLayoutX(364);
        circleColor.setLayoutY(112);
        paneProduct.getChildren().add(circleColor);

        // SIZE
        Label labelSize = new Label("Talle:");
        labelSize.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.ITALIC, 20));
        labelSize.setLayoutX(427);
        labelSize.setLayoutY(64);
        paneProduct.getChildren().add(labelSize);
        StackPane stackPane = new StackPane();
        stackPane.setPrefSize(50, 50);
        stackPane.setLayoutX(427);
        stackPane.setLayoutY(87);
        Label letterSize = new Label(size);
        letterSize.setFont(Font.font("Cambria", FontWeight.BOLD, 32));
        stackPane.getChildren().add(letterSize);
        paneProduct.getChildren().add(stackPane);

        // QUANTITY
        Label labelQuantity = new Label("Cantidad");
        labelQuantity.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.ITALIC, 20));
        labelQuantity.setLayoutX(513);
        labelQuantity.setLayoutY(64);
        if(!enoughStock){
            Label noStockLabel = new Label("SIN STOCK!");
            noStockLabel.setFont(Font.font("Cambria", FontWeight.BOLD, 20));
            noStockLabel.setLayoutX(513);
            noStockLabel.setLayoutY(45);
            paneProduct.getChildren().add(noStockLabel);
        }



        paneProduct.getChildren().add(labelQuantity);



        return paneProduct;

    }

    public static Pane getComparatedItem(CartItemDTO product) {

        Pane paneProduct = new Pane();
        paneProduct.setPrefSize(240, 645);
        paneProduct.setStyle("-fx-background-color: #e2e2e2");

        // IMAGE
        ImageView  productImage;
        byte[] image = product.getProduct().getImage();
        if (image != null){
            Image img = new Image(new ByteArrayInputStream(image));
            productImage = new ImageView(img);
        }else{
            productImage = new ImageView("/uy/edu/um/tic1/images/no_image.jpg");
        }

        productImage.setFitWidth(225);
        productImage.setFitHeight(300);
        productImage.setLayoutX(8);
        productImage.setLayoutY(4);
        paneProduct.getChildren().add(productImage);

        // NAME
        StackPane paneName = new StackPane();
        paneName.setPrefSize(225, 64);
        paneName.setLayoutX(8);
        paneName.setLayoutY(323);
        paneName.setAlignment(Pos.TOP_CENTER);
        Label productName = new Label(product.getProduct().getName());
        productName.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.ITALIC, 16));
        productName.setWrapText(true);
        paneName.getChildren().add(productName);
        paneProduct.getChildren().add(paneName);

        // BRAND
        Label productBrand = new Label(product.getProduct().getBrand().getName());
        productBrand.setFont(Font.font("Cambria", FontPosture.ITALIC, 14));
        productBrand.setLayoutX(14);
        productBrand.setLayoutY(387);
        paneProduct.getChildren().add(productBrand);

        // COLOR
        Label labelColor = new Label("Color:");
        labelColor.setFont(Font.font("Cambria", 13));
        labelColor.setLayoutX(27);
        labelColor.setLayoutY(442);
        paneProduct.getChildren().add(labelColor);
        Circle circleColor = Colors.getCircle(product.getSizeAndColor().getColor(), 16f);
        circleColor.setLayoutX(87);
        circleColor.setLayoutY(451);
        paneProduct.getChildren().add(circleColor);

        // SIZE
        Label labelSize = new Label("Talle:");
        labelSize.setFont(Font.font("Cambria", 13));
        labelSize.setLayoutX(121);
        labelSize.setLayoutY(434);
        paneProduct.getChildren().add(labelSize);
        Label letterSize = new Label(product.getSizeAndColor().getSize());
        letterSize.setFont(Font.font("Cambria", 13));
        letterSize.setLayoutX(159);
        letterSize.setLayoutY(442);
        paneProduct.getChildren().add(letterSize);

        // QUANTITY
        Label labelQuantity = new Label("Cantidad:");
        labelQuantity.setFont(Font.font("Cambria", 13));
        labelQuantity.setLayoutX(121);
        labelQuantity.setLayoutY(459);
        paneProduct.getChildren().add(labelQuantity);
        Label numberQUantity = new Label(product.getQuantity().toString());
        numberQUantity.setFont(Font.font("Cambria", 13));
        numberQUantity.setLayoutX(159);
        numberQUantity.setLayoutY(442);
        paneProduct.getChildren().add(numberQUantity);

        // PRICE
        StackPane panePrice = new StackPane();
        panePrice.setPrefSize(225, 25);
        panePrice.setLayoutX(9);
        panePrice.setLayoutY(496);
        Label productPrice = new Label(product.getPrice().toString()+" $UY");
        productPrice.setFont(Font.font("Cambria", FontWeight.BOLD, FontPosture.ITALIC, 30));
        panePrice.getChildren().add(productPrice);
        paneProduct.getChildren().add(panePrice);

        Button addToCart = new Button("Agregar al carrito");
        addToCart.setFont(Font.font("Cambria", FontWeight.BOLD, 16));
        addToCart.setLayoutX(44);
        addToCart.setLayoutY(543);
        addToCart.setOnAction(event -> {
            storeApplication.getCart().addItem(product);
        });
        paneProduct.getChildren().add(addToCart);

        return paneProduct;

    }


}
