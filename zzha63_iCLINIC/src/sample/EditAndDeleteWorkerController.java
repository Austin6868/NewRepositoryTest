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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class EditAndDeleteWorkerController implements Initializable {

    final ObservableList<String> data = FXCollections.observableArrayList();
    final ObservableList<String> professionList = FXCollections.observableArrayList();
    final ObservableList<String> rolesList = FXCollections.observableArrayList();
    @FXML
    Button exitButton;
    @FXML
    Button deleteButton;
    @FXML
    Button updateButton;
    @FXML
    Button clearButton;
    @FXML
    ComboBox findWorker;
    @FXML
    ComboBox role;
    @FXML
    ComboBox profession;
    @FXML
    TextField name;
    @FXML
    TextField passwordField;
    @FXML
    TextField userAccountName;
    private WorkerAdapter workerAdapter;


    public void setModel(WorkerAdapter worker){
        workerAdapter = worker;
        buildWorkerNameComboBoxData();
        buildProfessionComboBoxData();
        buildRoleComboBoxData();

    }

    public void buildProfessionComboBoxData() {
        try {
            professionList.addAll("Doctor", "Nurse");
        }catch(Exception e){
            displayAlert("ERROR: " +e.getMessage());
        }
    }

    public void buildWorkerNameComboBoxData() {
        try {
            data.addAll(workerAdapter.getWorkersNamesList());
        }catch(Exception e){
            displayAlert("ERROR: " +e.getMessage());
        }
    }

    public void buildRoleComboBoxData() {
        try {
            rolesList.addAll("Administrator", "Physician");
        }catch(Exception e){
            displayAlert("ERROR: " +e.getMessage());
        }
    }

    @FXML
    public void exit() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void remove() {
        try {
            workerAdapter.deleteWorker(findWorker.getSelectionModel().getSelectedItem().toString());
        } catch (SQLException e) {
        }
        name.setDisable(true);
        userAccountName.setDisable(true);
        passwordField.setDisable(true);
        role.setDisable(true);
        profession.setDisable(true);
        clearButton.setDisable(true);
        deleteButton.setDisable(true);
        updateButton.setDisable(true);
        findWorker.setDisable(false);
        findWorker.getSelectionModel().clearSelection();
        name.clear();
        userAccountName.clear();
        passwordField.clear();
        role.getSelectionModel().clearSelection();
        profession.getSelectionModel().clearSelection();
        data.clear();
        buildWorkerNameComboBoxData();
    }

    @FXML
    public void update() {
        try {
            workerAdapter.update(name.getText(), role.getSelectionModel().getSelectedItem().toString(), profession.getSelectionModel().getSelectedItem().toString(), findWorker.getSelectionModel().getSelectedItem().toString(), passwordField.getText(), userAccountName.getText());

        } catch (SQLException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
        Stage stage = (Stage) exitButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void clear(){
        try{
            name.setDisable(true);
            userAccountName.setDisable(true);
            passwordField.setDisable(true);
            role.setDisable(true);
            profession.setDisable(true);
            clearButton.setDisable(true);
            deleteButton.setDisable(true);
            updateButton.setDisable(true);
            findWorker.setDisable(false);
            findWorker.getSelectionModel().clearSelection();
            name.clear();
            userAccountName.clear();
            passwordField.clear();
            role.getSelectionModel().clearSelection();
            profession.getSelectionModel().clearSelection();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
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

    public void findWorkerBoxFunction() throws SQLException {
        if ((findWorker.getSelectionModel().getSelectedItem() != null)) {
            findWorker.setDisable(true);
            name.setDisable(false);
            userAccountName.setDisable(false);
            passwordField.setDisable(false);
            role.setDisable(false);
            profession.setDisable(false);
            clearButton.setDisable(false);
            deleteButton.setDisable(false);
            updateButton.setDisable(false);
            exitButton.setDisable(false);
            String[] list;
            list = workerAdapter.getWorkersInfo(findWorker.getSelectionModel().getSelectedItem().toString());
            name.setText(list[0]);
            userAccountName.setText(list[3]);
            passwordField.setText(list[4]);



            if (list[1].contains("Administrator")) {role.getSelectionModel().select(0);
            }
            else {role.getSelectionModel().select(1); }

            if (list[2].contains("Doctor")){ profession.getSelectionModel().select(0);}
            else {profession.getSelectionModel().select(1);}

            
        }
            else{
                name.setDisable(true);
                userAccountName.setDisable(true);
                passwordField.setDisable(true);
                role.setDisable(true);
                profession.setDisable(true);
                clearButton.setDisable(true);
                deleteButton.setDisable(true);
                updateButton.setDisable(true);
                exitButton.setDisable(false);
            }
        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        profession.setItems(professionList);
        role.setItems(rolesList);
        findWorker.setItems(data);
        name.setDisable(true);
        userAccountName.setDisable(true);
        passwordField.setDisable(true);
        role.setDisable(true);
        profession.setDisable(true);
        clearButton.setDisable(true);
        deleteButton.setDisable(true);
        updateButton.setDisable(true);
        exitButton.setDisable(false);
    }
}
