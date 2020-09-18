package uy.edu.um.tic1;


import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@FxmlView("mainMenu.fxml")
public class MainMenuController {

    @Autowired
    JavaFxApplication javaFxApplication;

    public MainMenuController() {
    }

}
