package uy.edu.um.tic1.scenes.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.tic1.StoreApplication;

@Component
@FxmlView("/uy/edu/um/tic1/scenes/user/sceneLogIn.fxml")
public class LogInController {

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
    void inicioPressed(ActionEvent event) { storeApplication.sceneMain(); }

    @FXML
    void logInPressed(ActionEvent event) {
        // De aca se toma el user y la password ingresados
        String user = null;
        String password = null;
        try {
            user = String.valueOf(userField.getCharacters());
            password = String.valueOf(passwordField.getCharacters());
            if ( (user.equals("brand")) && (password.equals("1234")) ) {
                storeApplication.sceneAdminBrand();
            }
            if ( (user.equals("store")) && (password.equals("1234")) ) {
                storeApplication.sceneAdminStore();
            }
        } catch (NullPointerException e) {
            System.out.println("user/pass void");
        }
    }

    @FXML
    void registerPressed(ActionEvent event) {
        storeApplication.sceneRegister(true);
    }



}
