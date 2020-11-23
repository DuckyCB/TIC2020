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
import uy.edu.um.tic1.StoreApplication;
import uy.edu.um.tic1.entitites.cart.CartDTO;
import uy.edu.um.tic1.entitites.contact.AddressDTO;
import uy.edu.um.tic1.entitites.contact.TelephoneNumberDTO;
import uy.edu.um.tic1.entitites.users.AppUserDTO;
import uy.edu.um.tic1.entitites.users.ClientDTO;
import uy.edu.um.tic1.requests.CartRestController;
import uy.edu.um.tic1.requests.UserRestController;

import java.net.URL;
import java.util.ResourceBundle;

@Component
@FxmlView("/uy/edu/um/tic1/scenes/user/sceneRegister.fxml")
public class RegisterController implements Initializable {

    @Autowired
    StoreApplication storeApplication;
    @Autowired
    private UserRestController userRestController;
    @Autowired
    private CartRestController cartRestController;

    private String newUserName;
    private String newUserLastName;
    private String newUserUserName;
    private String newUserPassword;


    @FXML
    private Button inicio;
    @FXML
    private TextField a;
    @FXML
    private TextField b;
    @FXML
    private TextField c;
    @FXML
    private TextField d;
    @FXML
    private TextField e;
    @FXML
    private PasswordField pass;
    @FXML
    private Button buttonNext;
    @FXML
    private StackPane errorA;
    @FXML
    private StackPane errorB;
    @FXML
    private StackPane errorC;
    @FXML
    private StackPane errorE;
    @FXML
    private StackPane errorD;
    @FXML
    private Button buttonCreate;

    // ****************************************************************************************************************
    //                  INITIALIZE
    // ****************************************************************************************************************

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        errorA.setVisible(false);
        errorB.setVisible(false);
        errorC.setVisible(false);
        errorD.setVisible(false);
        errorE.setVisible(false);

        a.setPromptText("Nombre");
        b.setPromptText("Apellido");
        c.setPromptText("Nombre de Usuario");
        d.setVisible(false);
        e.setVisible(false);
        pass.setPromptText("Contraseña");

        buttonCreate.setVisible(false);

    }

    // ****************************************************************************************************************
    //                  BUTTONS FXML
    // ****************************************************************************************************************

    @FXML
    void inicioPressed(ActionEvent event) { storeApplication.sceneMain(); }

    @FXML
    void pressedNext(ActionEvent event) {

        String name = String.valueOf(a.getCharacters());
        if (name.isEmpty()) {
            errorA.setVisible(true);
            Label newNameError = new Label("El campo no puede ser vacío");
            errorA.getChildren().add(newNameError);
        } else errorA.setVisible(false);

        String lastName = String.valueOf(b.getCharacters());
        if (lastName.isEmpty()) {
            errorB.setVisible(true);
            Label newLastNameError = new Label("El campo no puede ser vacío");
            errorB.getChildren().add(newLastNameError);
        } else errorB.setVisible(false);

        String userName = String.valueOf(c.getCharacters());
        boolean userNameUsed = false;
        if (userName.isEmpty()) {
            errorC.setVisible(true);
            Label newUserError = new Label("El campo no puede ser vacío");
            errorC.getChildren().add(newUserError);
        } else {
            userNameUsed = userRestController.checkUsername(userName);
            if (userNameUsed) {
                errorC.setVisible(true);
                Label newUserError = new Label("Nombre de usuario ya usado");
                errorC.getChildren().add(newUserError);
            } else errorC.setVisible(false);
        }

        String password = String.valueOf(pass.getCharacters());
        if (password.isEmpty()) {
            errorE.setVisible(true);
            Label newPasswordError = new Label("El campo no puede ser vacío");
            errorE.getChildren().add(newPasswordError);
        } else errorE.setVisible(false);

        if (!name.isEmpty() && !lastName.isEmpty() && !userName.isEmpty() && !userNameUsed && !password.isEmpty()) {

            newUserName = name;
            newUserLastName = lastName;
            newUserUserName = userName;
            newUserPassword = password;

            setSecond();
            buttonNext.setVisible(false);
            buttonCreate.setVisible(true);

        }

    }

    @FXML
    void pressedCreate(ActionEvent event) {

        String phone = String.valueOf(a.getCharacters());
        if (phone.isEmpty()) {
            errorA.setVisible(true);
            Label newPhoneError = new Label("El campo no puede ser vacío");
            errorA.getChildren().add(newPhoneError);
        } else errorA.setVisible(false);

        String zip = String.valueOf(b.getCharacters());
        if (zip.isEmpty()) {
            errorB.setVisible(true);
            Label newZIPError = new Label("El campo no puede ser vacío");
            errorB.getChildren().add(newZIPError);
        } else errorB.setVisible(false);

        String street = String.valueOf(c.getCharacters());
        if (street.isEmpty()) {
            errorC.setVisible(true);
            Label newStreetError = new Label("El campo no puede ser vacío");
            errorC.getChildren().add(newStreetError);
        } else errorC.setVisible(false);

        String streetNumber = String.valueOf(d.getCharacters());
        if (streetNumber.isEmpty()) {
            errorD.setVisible(true);
            Label newStreetNumberError = new Label("El campo no puede ser vacío");
            errorD.getChildren().add(newStreetNumberError);
        } else errorD.setVisible(false);

        String aditional = String.valueOf(e.getCharacters());

        if (!phone.isEmpty() && !zip.isEmpty() && !street.isEmpty() && !streetNumber.isEmpty()) {


            ClientDTO newClient = ClientDTO.builder()
                    .username(newUserUserName)
                    .firstName(newUserName)
                    .lastName(newUserLastName)
                    .password(newUserPassword)
                    .address(AddressDTO.builder().street(street).doorNumber(streetNumber).optional(aditional).zipCode(Integer.valueOf(zip)).build())
                    .telephoneNumber(TelephoneNumberDTO.builder().number(Integer.valueOf(phone)).build())
                    .build();

            userRestController.registerClient(newClient);

            AppUserDTO userEntity = userRestController.getUser(newUserUserName, newUserPassword);
            storeApplication.setAppUser(userEntity);
            storeApplication.setPassword(newUserPassword);
            CartDTO userCart = cartRestController.getCurrentCart();
            CartDTO savedCart = storeApplication.getCart();
            if(userCart == null || userCart.getItems().isEmpty()){
                if(userCart == null){
                    ((ClientDTO) userEntity).setCurrentCart(storeApplication.getCart());
                    cartRestController.saveCurrentCart(storeApplication.getCart());
                } else if (!savedCart.getItems().isEmpty()){
                    ((ClientDTO) userEntity).setCurrentCart(storeApplication.getCart());
                    cartRestController.saveCurrentCart(storeApplication.getCart());
                }
            }
            storeApplication.setCart(cartRestController.getCurrentCart());
            storeApplication.sceneMain();
        }





        storeApplication.sceneMain();
    }

    // ****************************************************************************************************************
    //                  SET SECOND SCENE INFORMATION
    // ****************************************************************************************************************

    private void setSecond(){

        a.clear();
        b.clear();
        c.clear();

        d.setVisible(true);
        pass.setVisible(false);
        e.setVisible(true);

        a.setPromptText("Celular");
        b.setPromptText("Código ZIP");
        c.setPromptText("Calle");
        d.setPromptText("Número de domicilio");
        e.setPromptText("Datos Adicionales (opcional)");

    }

}
