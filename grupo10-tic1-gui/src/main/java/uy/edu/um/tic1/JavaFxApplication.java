package uy.edu.um.tic1;

import javafx.application.Application;
import javafx.application.Platform;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import uy.edu.um.tic1.scenes.ComparatorPageController;
import uy.edu.um.tic1.product.Products;
import uy.edu.um.tic1.scenes.ProductDisplayController;
import uy.edu.um.tic1.scenes.ProductListingController;
import uy.edu.um.tic1.scenes.MainMenuController;
import uy.edu.um.tic1.scenes.user.LogInController;
import uy.edu.um.tic1.scenes.user.RegisterFirstController;
import uy.edu.um.tic1.scenes.user.RegisterSecondController;

@Component
public class JavaFxApplication extends Application {

    private static ConfigurableApplicationContext applicationContext;

    static Stage primaryStage;
    static Stage stageComparator;


    @Override
    public void init() {
        String[] args = getParameters().getRaw().toArray(new String[0]);

        this.applicationContext = new SpringApplicationBuilder()
                .sources(ApplicationGui.class)
                .run(args);
    }

    @Override
    public void start(Stage primaryStage) {

        JavaFxApplication.primaryStage = primaryStage;
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(MainMenuController.class);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Página principal");
        //primaryStage.setMaximized(true);
        primaryStage.show();

    }

    @Override
    public void stop() {
        this.applicationContext.close();
        Platform.exit();
    }

    public void sceneMainMenu(){

        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(MainMenuController.class);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Página Principal");
        primaryStage.show();

    }

    private void sceneListing(String productsClass) {

        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(ProductListingController.class);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle(productsClass);
        primaryStage.show();

    }

    public void sceneProductDisplay(String productName) {

        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(ProductDisplayController.class);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hombre - Pantalones - "+productName);
        primaryStage.show();

    }

    public void sceneComparator(String image, String name, String brand, String price, Boolean comparatorA) {
        if (comparatorA) {
            ComparatorPageController.setImageA(new Image(image));
            ComparatorPageController.setNameA(name);
            ComparatorPageController.setBrandA(brand);
            ComparatorPageController.setPriceA(price);
            Stage stageComparator = new Stage();
            FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
            Parent root = fxWeaver.loadView(ComparatorPageController.class);
            Scene scene = new Scene(root);
            stageComparator.setScene(scene);
            stageComparator.show();
        } else {
            ComparatorPageController.setImageB(new Image(image));
            ComparatorPageController.setNameB(name);
            ComparatorPageController.setBrandB(brand);
            ComparatorPageController.setPriceB(price);
            //stageComparator.
        }

    }

    public void sceneLogIn() {

        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(LogInController.class);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Ingresar");
        primaryStage.show();

    }

    public void sceneRegister(Boolean first) {

        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root;

        if (first) {

            root = fxWeaver.loadView(RegisterFirstController.class);

        } else {

            root = fxWeaver.loadView(RegisterSecondController.class);

        }

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Register");
        primaryStage.show();


    }

    public void hombrePantalones() {

        Products product1 = new Products(new ImageView("/uy/edu/um/tic1/images/Men/Jean/black.jpg"), "Jean Black", "Levi's", "499,99 $UY");
        Products.getProducts().push(product1);
        Products product2 = new Products(new ImageView("/uy/edu/um/tic1/images/Men/Jean/blue.jpg"), "Jean Blue", "Levi's", "449,99 $UY");
        Products.getProducts().push(product2);
        Products product3 = new Products(new ImageView("/uy/edu/um/tic1/images/Men/Jean/lightBlue.jpg"), "Jean Light Blue", "Levi's", "429,99 $UY");
        Products.getProducts().push(product3);
        Products.setProductsCategory("P A N T A L O N E S");
        sceneListing("Hombre - Pantalones");

    }

    public void hombreRemeras() {

        Products product1 = new Products(new ImageView("/uy/edu/um/tic1/images/Men/Shirt/black.jpg"), "Camisa Black", "Levi's", "389,99 $UY");
        Products.getProducts().push(product1);
        Products product2 = new Products(new ImageView("/uy/edu/um/tic1/images/Men/Shirt/brown.jpg"), "Camisa Orange", "Levi's", "349,99 $UY");
        Products.getProducts().push(product2);
        Products product3 = new Products(new ImageView("/uy/edu/um/tic1/images/Men/Shirt/green.jpg"), "Remera Green", "Levi's", "299,99 $UY");
        Products.getProducts().push(product3);
        Products product4 = new Products(new ImageView("/uy/edu/um/tic1/images/Men/Shirt/pink.jpg"), "Remera Pink", "Levi's", "249,99 $UY");
        Products.getProducts().push(product4);
        Products.setProductsCategory("R E M E R A S");
        sceneListing("Hombre - Remeras");

    }

    public void hombreBuzos() {

        Products product1 = new Products(new ImageView("/uy/edu/um/tic1/images/Men/Sweatshirt/black.jpg"), "Pullover Black Leaves", "Levi's", "699,99 $UY");
        Products.getProducts().push(product1);
        Products product2 = new Products(new ImageView("/uy/edu/um/tic1/images/Men/Sweatshirt/lightBlue.jpg"), "Pullover Light Blue", "Levi's", "399,99 $UY");
        Products.getProducts().push(product2);
        Products product3 = new Products(new ImageView("/uy/edu/um/tic1/images/Men/Sweatshirt/white.jpg"), "Canguro White", "Levi's", "599,99 $UY");
        Products.getProducts().push(product3);
        Products.setProductsCategory("B U Z O S");
        sceneListing("Hombre - Buzos");

    }

}
