package uy.edu.um.tic1;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("mainMenu.fxml")
public class MainMenuController {

    @Autowired
    JavaFxApplication javaFxApplication;

    @FXML
    private ImageView image;

    @FXML
    private MenuItem hombreRemera;

    @FXML
    void homreRemera(ActionEvent event) {
        /*image.setImage("/images/spikelentes.png");*/
    }

    public MainMenuController() {
    }

}
