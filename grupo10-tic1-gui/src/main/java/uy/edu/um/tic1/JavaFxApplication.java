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
        primaryStage.setMaximized(true);
        primaryStage.show();

    }

    @Override
    public void stop() {
        this.applicationContext.close();
        Platform.exit();
    }








}
