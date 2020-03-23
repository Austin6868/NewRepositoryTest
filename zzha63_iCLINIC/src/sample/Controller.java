package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Connection conn;
    private WorkerAdapter workers;


    public void listenerEvent(javafx.event.ActionEvent event) throws IOException {                      //This is the listenerEvent to load the "About Us" Window.
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AboutUsWindow.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL); //Init modality blocks the user from performing any other functionality unitl that window is closed.
        stage.setTitle("About Us");  //Setting the tile of the window.
        stage.getIcons().add(new Image("/sample/resources/WesternLogo.png")); //Setting the icon image of the window.
        stage.setScene(new Scene(root1, 400, 320)); //Making the window 400 by 320.
        stage.show();
    }
    @FXML
    private Button button; //Declaring a button from the Button class for the "Ok" button in the About Us Window.

    public void closeProgram(ActionEvent event) {
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();              //Using this to close the "About Us" window on the press of the "Ok" button.
    }

    public void closeProgramEvent(ActionEvent event) {
        Platform.exit();
    }  //This closeProgram Event closes the entire application when the "Close" button is pressed.

    private void displayAlert(String msg) {
        try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("Alert.fxml"));
            Parent ERROR = loader.load();
            AlertController controller = (AlertController) loader.getController();

            Scene scene = new Scene(ERROR);
            Stage stage = new Stage();
            stage.setScene(scene);

            stage.getIcons().add(new Image("file:src/TennisBallGames/WesternLogo.png"));
            controller.setAlertText(msg);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

        } catch (IOException ex1) {

        }
    }

    public void resetDB() {
        try {
            // create Teams model
            workers = new WorkerAdapter(conn, true);
            displayAlert("Workers table has been created");

        } catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        }

    }
    public void showAddWorker() throws Exception{
        workers = new WorkerAdapter(conn,false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AddWorker.fxml"));
        Parent AddProfile = (Parent) fxmlLoader.load();
        AddWorkerController addWorkerController = (AddWorkerController) fxmlLoader.getController();
        addWorkerController.setModel(workers);

        Scene scene = new Scene(AddProfile);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image("/sample/resources/WesternLogo.png"));
        stage.setTitle("Add Worker");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    public void showEditAndDeleteWorker () throws Exception{
        workers = new WorkerAdapter(conn,false);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("EditAndDeleteWorker.fxml"));
        Parent EditAndDeleteProfile = (Parent) fxmlLoader.load();
        EditAndDeleteWorkerController editAndDeleteWorkerController = (EditAndDeleteWorkerController) fxmlLoader.getController();
        editAndDeleteWorkerController.setModel(workers);

        Scene scene = new Scene(EditAndDeleteProfile);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image("/sample/resources/WesternLogo.png"));
        stage.setTitle("Add Worker");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            // Create a named constant for the URL
            // NOTE: This value is specific for Java DB
            String DB_URL = "jdbc:derby:iCLINICDB;create=true";
            // Create a connection to the database
            conn = DriverManager.getConnection(DB_URL);

        } catch (SQLException ex) {
            displayAlert(ex.getMessage());
        }
    }

}
