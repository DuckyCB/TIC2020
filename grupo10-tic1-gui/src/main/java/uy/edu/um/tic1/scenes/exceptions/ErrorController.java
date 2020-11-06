package uy.edu.um.tic1.scenes.exceptions;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@Component
@FxmlView("/uy/edu/um/tic1/scenes/exceptions/sceneError.fxml")
public class ErrorController {

    @FXML
    private Label labelError;
    @FXML
    private Button buttonAccept;

    @FXML
    void pressedAccept(ActionEvent event) {

    }


}
