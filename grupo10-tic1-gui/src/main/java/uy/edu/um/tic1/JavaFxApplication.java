package uy.edu.um.tic1;

import javafx.application.Application;
import javafx.application.Platform;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;
import uy.edu.um.tic1.categories.HombreBuzosController;
import uy.edu.um.tic1.categories.HombrePantalonesController;
import uy.edu.um.tic1.categories.HombreRemerasController;
import uy.edu.um.tic1.product.ProductPageController;

@Component
public class JavaFxApplication extends Application {

    private static ConfigurableApplicationContext applicationContext;

    static Stage primaryStage;


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

    public void hombrePantalones(){

        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(HombrePantalonesController.class);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hombre - Pantalones");
        primaryStage.show();

    }

    public void hombreBuzos(){

        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(HombreBuzosController.class);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hombre - Buzos");
        primaryStage.show();

    }

    public void hombreRemeras(){

        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(HombreRemerasController.class);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hombre - Remeras");
        primaryStage.show();

    }

    public void inicioPressed(){

        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(MainMenuController.class);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Página Principal");
        primaryStage.show();

    }

    public void productPage(String image, String name, String brand, String price) {

        FxWeaver fxWeaver = applicationContext.getBean(FxWeaver.class);
        Parent root = fxWeaver.loadView(ProductPageController.class);
        Scene scene = new Scene(root);
        // De alguna forma hay que mandar los atributos image, name, band y price
        // a las variables dentro de ProductPageController, asi al iniciar la ventana,
        // la funcion initialize se encarga de usar esos valores.
        primaryStage.setScene(scene);
        primaryStage.setTitle("Hombre - Pantalones - "+name);
        primaryStage.show();

    }






}
