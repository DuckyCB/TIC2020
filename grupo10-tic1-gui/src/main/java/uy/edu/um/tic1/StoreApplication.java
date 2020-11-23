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
import uy.edu.um.tic1.entitites.cart.CartItemDTO;
import uy.edu.um.tic1.entitites.cart.PurchaseDTO;
import uy.edu.um.tic1.entitites.product.ProductDTO;
import uy.edu.um.tic1.entitites.users.AppUserDTO;
import uy.edu.um.tic1.entitites.users.ClientDTO;
import uy.edu.um.tic1.requests.CartRestController;
import uy.edu.um.tic1.scenes.*;
import uy.edu.um.tic1.scenes.admin.brand.ProductDisplayBrandController;
import uy.edu.um.tic1.scenes.admin.store.ProductDisplayStoreController;
import uy.edu.um.tic1.scenes.user.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Component
public class StoreApplication extends Application {

    // WIRES
    @Autowired
    private ConfigurableApplicationContext applicationContext;
    @Autowired
    private CartRestController cartRestController;

    // USER
    private AppUserDTO appUser;
    private String password;

    // CLIENT
    private CartDTO cart;
    private List<CartItemDTO> productsToCompare;

    // STORE
    private Set<PurchaseDTO> purchases;

    // SCENE
    static Stage primaryStage;

    // ****************************************************************************************************************

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

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
        if (this.appUser != null && this.appUser instanceof ClientDTO && this.cart != null){
            cartRestController.saveCurrentCart(this.cart);
        }
    }

    public List<CartItemDTO> getProductsToCompare() {
        return productsToCompare;
    }
    public void setProductsToCompare(List<CartItemDTO> productsToCompare) {
        this.productsToCompare = productsToCompare;
    }

    public Set<PurchaseDTO> getPurchases() {
        return purchases;
    }
    public void setPurchases(Set<PurchaseDTO> purchases) {
        this.purchases = purchases;
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

    // ****************************************************************************************************************
    //                  START AND STOP
    // ****************************************************************************************************************

    @Override
    public void start(Stage primaryStage) {

        StoreApplication.primaryStage = primaryStage;
        sceneMain();

    }

    @Override
    public void stop() {
        this.applicationContext.close();
        Platform.exit();
    }

    // ****************************************************************************************************************
    //                  MAIN
    // ****************************************************************************************************************

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

    // ****************************************************************************************************************
    //                  CLIENTE
    // ****************************************************************************************************************

    public void sceneProductDisplay(ProductDTO product) {

        ProductDisplayController.product = product;
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(ProductDisplayController.class);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Mostrando "+product.getName());
        primaryStage.show();

    }

    public void sceneCart() {

        ListItemsController.isCart = true;

        if (getAppUser() != null && getAppUser() instanceof ClientDTO) {

            ClientDTO client = (ClientDTO) getAppUser();
            if (client.getCurrentCart() != null) this.setCart(client.getCurrentCart());

        }

        setCart(this.getCart());
        sceneListItems("Carrito");

    }

    public void sceneListItems(String sceneName) {

        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(ListItemsController.class);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle(sceneName);
        primaryStage.show();

    }

    public void sceneCompare() {

        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(CompareController.class);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Comparar productos");
        primaryStage.show();

    }

    // ****************************************************************************************************************
    //                  ADMIN
    // ****************************************************************************************************************

    public void sceneBrandDisplayProduct(ProductDTO product) {

        ProductDisplayBrandController.tempProduct = product;
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(ProductDisplayBrandController.class);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Brand admin");
        primaryStage.show();

    }

    public void sceneStoreDisplayProduct(ProductDTO product) {

        ProductDisplayStoreController.product = product;
        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(ProductDisplayStoreController.class);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Store admin");
        primaryStage.show();

    }

}
