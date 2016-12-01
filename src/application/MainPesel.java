package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class MainPesel extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent parent = (Parent) FXMLLoader.load(getClass().getResource("/application/PeselPane.fxml"));
        Scene scene = new Scene(parent);
        primaryStage.setScene(scene);
        primaryStage.setTitle("PESEL Authorization");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
