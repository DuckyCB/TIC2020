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
import uy.edu.um.tic1.scenes.user.CartController;

@Component
public class StoreApplication extends Application {

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

        StoreApplication.primaryStage = primaryStage;
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(MainController.class);
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

    public void sceneMain(){

        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(MainController.class);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Página Principal");
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

    public void sceneCart() {

        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(CartController.class);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Carrito");
        primaryStage.show();

    }

    public void sceneAdminBrand() {

        ProductRequest.productsList = ProductRequest.getAll();
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
