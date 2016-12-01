package application;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class MainPaneController implements Initializable {

    @FXML
    private Button button;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        button.addEventFilter(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            Random random = new Random();

            @Override
            public void handle(MouseEvent event) {
                int x = random.nextInt(540);
                int y = random.nextInt(375);
                button.relocate(x, y);
            }
        });
    }

}
