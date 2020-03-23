package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("iClinic.fxml"));
        primaryStage.setTitle("iClinic");
        primaryStage.setScene(new Scene(root, 850, 500));
        primaryStage.getIcons().add(new Image("/sample/resources/WesternLogo.png"));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
