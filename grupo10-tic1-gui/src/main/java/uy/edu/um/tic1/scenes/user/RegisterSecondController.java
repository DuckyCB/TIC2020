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
@FxmlView("/uy/edu/um/tic1/scenes/user/sceneRegisterSecond.fxml")
public class RegisterSecondController {

    @Autowired
    StoreApplication storeApplication;

    @FXML
    private Button inicio;

    @FXML
    private PasswordField userStreet;
    @FXML
    private TextField userPhone;
    @FXML
    private Button buttonCreate;
    @FXML
    private TextField userZIP;
    @FXML
    private PasswordField userStreetNumber;
    @FXML
    private PasswordField userOptionalData;


    @FXML
    void pressedCreate(ActionEvent event) {
        userPhone.getCharacters();
        userZIP.getCharacters();
        userStreet.getCharacters();
        userStreetNumber.getCharacters();
        userOptionalData.getCharacters();
    }

    @FXML
    void inicioPressed(ActionEvent event) { storeApplication.sceneMain(); }

}
