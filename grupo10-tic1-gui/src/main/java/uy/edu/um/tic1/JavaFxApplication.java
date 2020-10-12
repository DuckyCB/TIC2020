package uy.edu.um.tic1;

import javafx.application.Application;
import javafx.application.Platform;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import uy.edu.um.tic1.entity.ColorRGB;
import uy.edu.um.tic1.product.ProductRequest;
import uy.edu.um.tic1.scenes.*;
import uy.edu.um.tic1.product.Products;
import uy.edu.um.tic1.scenes.admin.brand.AdminBrandController;
import uy.edu.um.tic1.scenes.admin.brand.AdminProductBrandController;
import uy.edu.um.tic1.scenes.admin.store.AdminProductStoreController;
import uy.edu.um.tic1.scenes.admin.store.AdminStoreController;
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

    public void sceneListing(Products[] list) {

        ProductRequest.productsList = list;
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(ProductListingController.class);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        //primaryStage.setTitle(productsClass);
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

    public static void sceneRegister(Boolean first) {

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

    public void sceneAdminBrand() {

        ProductRequest.productsList = ProductRequest.listAll();
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(AdminBrandController.class);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Brand admin");
        primaryStage.show();

    }

    public void sceneAdminBrandProduct(Products product) {

        AdminProductBrandController.setProduct(product);
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(AdminProductBrandController.class);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Brand admin");
        primaryStage.show();

    }

    public void sceneAdminStore() {

        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(AdminStoreController.class);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Brand admin");
        primaryStage.show();

    }

    public void sceneAdminStoreProduct() {

        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(AdminProductStoreController.class);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Brand admin");
        primaryStage.show();

    }

}
