package uy.edu.um.tic1.scenes.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.tic1.JavaFxApplication;

@Component
@FxmlView("/uy/edu/um/tic1/scenes/user/sceneLogIn.fxml")
public class LogInController {

    @Autowired
    JavaFxApplication javaFxApplication;

    @FXML
    private Button inicio;

    @FXML
    private TextField userField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button logInButton;
    @FXML
    private Button registerButton;


    @FXML
    void inicioPressed(ActionEvent event) { javaFxApplication.sceneMainMenu(); }

    @FXML
    void logInPressed(ActionEvent event) {
        // De aca se toma el user y la password ingresados
        userField.getCharacters();
        passwordField.getCharacters();
    }

    @FXML
    void registerPressed(ActionEvent event) {
        javaFxApplication.sceneRegister(true);
    }



}
