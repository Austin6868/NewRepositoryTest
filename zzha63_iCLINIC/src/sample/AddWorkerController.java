package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class AddWorkerController implements Initializable {

    @FXML
    Button cancel;
    @FXML
    Button save;
    @FXML
    ComboBox role;
    @FXML
    ComboBox profession;
    @FXML
    TextField name;
    @FXML
    TextField userName;
    @FXML
    PasswordField password;


    private WorkerAdapter workerAdapter;
    final ObservableList<String> professionData = FXCollections.observableArrayList();
    final ObservableList<String> roleData = FXCollections.observableArrayList();

    public void buildProfessionComboBoxData() {
        try {
            professionData.addAll("Doctor", "Nurse");
        }catch(Exception e){
            displayAlert("ERROR: " +e.getMessage());
        }
    }



    public void setModel(WorkerAdapter worker){

        workerAdapter = worker;
        buildProfessionComboBoxData();
        buildRoleComboBoxData();
    }

    public void buildRoleComboBoxData() {
        try {
            roleData.addAll("Administrator", "Physician");
        }catch(Exception e){
            displayAlert("ERROR: " +e.getMessage());
        }
    }

    @FXML


    public void save() throws SQLException {
        try {
            workerAdapter.addWorker(name.getText(), role.getSelectionModel().getSelectedItem().toString(), profession.getSelectionModel().getSelectedItem().toString(), userName.getText(),password.getText());

        }
        catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }

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

    public void cancel() {
        Stage stage = (Stage) cancel.getScene().getWindow();
        stage.close();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        profession.setItems(professionData);
        role.setItems(roleData);
    }
}
