package uy.edu.um.tic1.scenes.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.tic1.Requests.RequestUser;
import uy.edu.um.tic1.StoreApplication;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("/uy/edu/um/tic1/scenes/user/sceneLogIn.fxml")
public class LogInController implements Initializable {

    @Autowired
    StoreApplication storeApplication;

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
    private StackPane errorUser;
    @FXML
    private StackPane errorPassword;



    @FXML
    void inicioPressed(ActionEvent event) { storeApplication.sceneMain(); }

    @FXML
    void logInPressed(ActionEvent event) {

        String user = String.valueOf(userField.getCharacters());
        String password = String.valueOf(passwordField.getCharacters());

        if (user.isEmpty()) {
            errorUser.setVisible(true);
            Label newUserError = new Label("El campo no puede ser vacío");
            errorUser.getChildren().add(newUserError);
        } else {
            errorUser.setVisible(false);
        }

        if (password.isEmpty()) {
            errorPassword.setVisible(true);
            Label newPasswordError = new Label("El campo no puede ser vacío");
            errorPassword.getChildren().add(newPasswordError);
        } else {
            errorPassword.setVisible(false);
        }

        if (!user.isEmpty() && !password.isEmpty()) {

            RequestUser.getUser(user, password, storeApplication);

        }
    }

    @FXML
    void registerPressed(ActionEvent event) {
        storeApplication.sceneRegister(true);
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        errorUser.setVisible(false);
        errorPassword.setVisible(false);
    }
}
