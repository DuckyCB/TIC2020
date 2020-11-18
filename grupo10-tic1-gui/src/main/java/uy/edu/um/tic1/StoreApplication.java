package uy.edu.um.tic1;

import javafx.application.Application;
import javafx.application.Platform;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import uy.edu.um.tic1.entitites.cart.CartDTO;
import uy.edu.um.tic1.entitites.product.ProductDTO;
import uy.edu.um.tic1.entitites.users.AppUserDTO;
import uy.edu.um.tic1.entitites.users.ClientDTO;
import uy.edu.um.tic1.scenes.*;
import uy.edu.um.tic1.scenes.admin.brand.ProductDisplayBrandController;
import uy.edu.um.tic1.scenes.admin.store.ProductDisplayStoreController;
import uy.edu.um.tic1.scenes.user.*;

import java.util.LinkedHashSet;

@Component
public class StoreApplication extends Application {

    @Autowired
    private ConfigurableApplicationContext applicationContext;
    private AppUserDTO appUser;
    private String password;
    private CartDTO cart;
    static Stage primaryStage;
    static Stage stageComparator;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CartDTO getCart() {
        if (cart == null) {
            this.cart = new CartDTO();
            this.cart.setItems(new LinkedHashSet<>());
        }
        return cart;
    }

    public void setCart(CartDTO cart) {
        this.cart = cart;
    }

    public AppUserDTO getAppUser() {
        return appUser;
    }

    public void setAppUser(AppUserDTO appUser) {
        this.appUser = appUser;
    }

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

    // ****************************************************************************************************************
    //                  USUARIO
    // ****************************************************************************************************************

    public void sceneLogIn() {
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(LogInController.class);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Ingresar");
        primaryStage.show();

    }

    public void sceneRegister() {

        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(RegisterController.class);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Register");
        primaryStage.show();


    }

    public void sceneConfig() {



    }

    // ****************************************************************************************************************
    //                  CLIENTE
    // ****************************************************************************************************************

    public void sceneProductDisplay(String productName) {

        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(ProductDisplayController.class);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Mostrando producto "+productName);
        primaryStage.show();

    }

    public void sceneCart() {

        CartDTO cart = null;

        if (getAppUser() != null && getAppUser() instanceof ClientDTO) {
            ClientDTO client = (ClientDTO) getAppUser();
            if (client.getCurrentCart() != null)
                this.setCart(client.getCurrentCart());
        }

        setCart(this.getCart());

        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(CartController.class);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Carrito");
        primaryStage.show();

    }

    // ****************************************************************************************************************
    //                  ADMIN
    // ****************************************************************************************************************

    public void sceneBrandDisplayProduct(ProductDTO product) {

        ProductDisplayBrandController.product = product;
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(ProductDisplayBrandController.class);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Brand admin");
        primaryStage.show();

    }

    public void sceneStoreDisplayProduct() {

        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(ProductDisplayStoreController.class);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Store admin");
        primaryStage.show();

    }

    public void sceneStoreSales() {

        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(ProductDisplayStoreController.class);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Ventas");
        primaryStage.show();

    }

}
