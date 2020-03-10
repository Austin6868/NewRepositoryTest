package TennisBallGames;

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
import org.apache.derby.impl.sql.catalog.SYSROUTINEPERMSRowFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class AddScoreController implements Initializable {

//declare the scene objects we want to use
    @FXML
    ComboBox matchBox;
    @FXML
    TextField homeTeamField;
    @FXML
    TextField visitorTeamField;
    @FXML
    Button cancelBtn;
    @FXML
    Button saveBtn;
    final ObservableList<String> data = FXCollections.observableArrayList();

    private MatchesAdapter matchesAdapter;
    private TeamsAdapter teamsAdapter;

    public void setModel(MatchesAdapter match, TeamsAdapter team) {
        matchesAdapter = match;
        teamsAdapter = team;
        buildComboBoxData();
    }
    public void buildComboBoxData() {
        try {
            data.addAll(matchesAdapter.getMatchesNamesList());
        } catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        }
    }
    @FXML
    public void cancel() {
        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void save(){
        try{
            String hName = "";
            String vName = "";
            String text = matchBox.getSelectionModel().getSelectedItem().toString();
            int comboBoxIndex = Integer.valueOf(text.charAt(0))-48;//extract the team names from the combo box
            int hNameIndex = matchBox.getSelectionModel().getSelectedItem().toString().indexOf("-");
            int vNameIndex = matchBox.getSelectionModel().getSelectedItem().toString().indexOf("    -");
            hName = text.substring(hNameIndex + 1, vNameIndex).replaceAll("\\s","");
            vName = text.substring(vNameIndex+5 ).replaceAll("\\s","");
            //System.out.println("hName:" + hName + "vName:" + vName);
            //System.out.println("hName index is " + hNameIndex + "vName index is" + vNameIndex);
            int hScore = Integer.valueOf(homeTeamField.getText());
            int vScore = Integer.valueOf(visitorTeamField.getText());
            teamsAdapter.setStatus(hName , vName , hScore , vScore);//update the team status
            matchesAdapter.setTeamsScore( comboBoxIndex,hScore , vScore);//update the team scores
            //System.out.println(Integer.valueOf(text.charAt(0)));
        } catch (SQLException ex) {
            displayAlert("ERROR: " + ex.getMessage());
        }

        Stage stage = (Stage) cancelBtn.getScene().getWindow();
        stage.close();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        matchBox.setItems(data);
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
}
