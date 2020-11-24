package uy.edu.um.tic1.scenes.user;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uy.edu.um.tic1.entitites.cart.CartDTO;
import uy.edu.um.tic1.entitites.cart.CartItemDTO;
import uy.edu.um.tic1.entitites.users.AppUserDTO;
import uy.edu.um.tic1.entitites.users.ClientDTO;
import uy.edu.um.tic1.entitites.users.StoreUserDTO;
import uy.edu.um.tic1.requests.CartRestController;
import uy.edu.um.tic1.requests.StoreRestController;
import uy.edu.um.tic1.requests.UserRestController;
import uy.edu.um.tic1.StoreApplication;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

@Component
@FxmlView("/uy/edu/um/tic1/scenes/user/sceneLogIn.fxml")
public class LogInController implements Initializable {

    @Autowired
    private StoreApplication storeApplication;
    @Autowired
    private UserRestController userRestController;
    @Autowired
    private CartRestController cartRestController;
    @Autowired
    private StoreRestController storeRestController;

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

    // ****************************************************************************************************************
    //                  INITIALIZE
    // ****************************************************************************************************************

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        errorUser.setVisible(false);
        errorPassword.setVisible(false);

    }

    // ****************************************************************************************************************
    //                  BUTTONS FXML
    // ****************************************************************************************************************

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

        } else errorUser.setVisible(false);

        if (password.isEmpty()) {

            errorPassword.setVisible(true);
            Label newPasswordError = new Label("El campo no puede ser vacío");
            errorPassword.getChildren().clear();
            errorPassword.getChildren().add(newPasswordError);

        } else errorPassword.setVisible(false);


        if (!user.isEmpty() && !password.isEmpty()) {

            AppUserDTO userEntity = userRestController.getUser(user, password);

            if (userEntity == null) {

                errorUser.setVisible(true);
                errorUser.getChildren().clear();
                Label newUserError = new Label("Posible usuario incorrecto");
                errorUser.getChildren().add(newUserError);

                errorPassword.setVisible(true);
                errorPassword.getChildren().clear();
                Label newPasswordError = new Label("Posible contraseña incorrecta");
                errorPassword.getChildren().add(newPasswordError);

            } else {

                storeApplication.setAppUser(userEntity);
                storeApplication.setPassword(password);

                if (userEntity instanceof ClientDTO){

                    CartDTO userCart = cartRestController.getCurrentCart();
                    CartDTO savedCart = storeApplication.getCart();


                    if(userCart == null){
                        ((ClientDTO) userEntity).setCurrentCart(savedCart);
                        storeApplication.setCart(savedCart);
                    } else {
                        if (! savedCart.getItems().isEmpty()) {
                            savedCart.getItems().stream().forEach(item -> {
                                userCart.addItem(item);
                            });
                        }
                        storeApplication.setCart(userCart);
                    }

                } else if (userEntity instanceof StoreUserDTO)
                    storeApplication.setPurchases(storeRestController.getStore().getPurchaseSet());

                storeApplication.sceneMain();

            }

        }

    }

    @FXML
    void registerPressed(ActionEvent event) {
        storeApplication.sceneRegister();
    }

}
