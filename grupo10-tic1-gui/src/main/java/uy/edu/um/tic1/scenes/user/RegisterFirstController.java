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
@FxmlView("/uy/edu/um/tic1/scenes/user/sceneRegisterFirst.fxml")
public class RegisterFirstController {

    @Autowired
    JavaFxApplication javaFxApplication;

    @FXML
    private Button inicio;

    @FXML
    private PasswordField userMail;

    @FXML
    private TextField userName;

    @FXML
    private Button buttonContinue;

    @FXML
    private TextField userLastName;

    @FXML
    private PasswordField userPassword;

    @FXML
    void inicioPressed(ActionEvent event) { javaFxApplication.sceneMainMenu(); }

    @FXML
    void pressedContinue(ActionEvent event) {
        userName.getCharacters();
        userLastName.getCharacters();
        userMail.getCharacters();
        userPassword.getCharacters();
        javaFxApplication.sceneRegister(false);
    }

}
