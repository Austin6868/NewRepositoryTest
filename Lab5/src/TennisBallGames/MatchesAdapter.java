package TennisBallGames;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Abdelkader
 */
public class MatchesAdapter {

    Connection connection;

    public MatchesAdapter(Connection conn, Boolean reset) throws SQLException {
        connection = conn;
        if (reset) {
            Statement stmt = connection.createStatement();
            try {
                // Remove tables if database tables have been created.
            // This will throw an exception if the tables do not exist
            stmt.execute("DROP TABLE Matches");
                // then do finally
            } catch (SQLException ex) {
                // No need to report an error.
                // The table simply did not exist.
                // do finally to create it
            } finally {
                // Create the table of Matches
                stmt.execute("CREATE TABLE Matches ("
                        + "MatchNumber INT NOT NULL PRIMARY KEY, "
                        + "HomeTeam CHAR(15) NOT NULL REFERENCES Teams (TeamName), "
                        + "VisitorTeam CHAR(15) NOT NULL REFERENCES Teams (TeamName), "
                        + "HomeTeamScore INT, "
                        + "VisitorTeamScore INT "
                        + ")");
                populateSamples();
            }
        }
    }
    
    private void populateSamples() throws SQLException{
            // Create a listing of the matches to be played
            this.insertMatch(1, "Astros", "Brewers");
            this.insertMatch(2, "Brewers", "Cubs");
            this.insertMatch(3, "Cubs", "Astros");
    }
        
    
    public int getMax() throws SQLException {
        int num = 0;
        ResultSet rs;

        // Create a Statement object
        Statement stmt = connection.createStatement();

        // Create a string with a SELECT statement
        String sqlStatement = "SELECT MAX(MatchNumber) FROM Matches";

        // Execute the statement and return the result
        rs = stmt.executeQuery(sqlStatement);

        // Add your work code here for Task #3
        while(rs.next())
        num = rs.getInt(1);

        return num;
    }
    
    public void insertMatch(int num, String home, String visitor) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO Matches (MatchNumber, HomeTeam, VisitorTeam, HomeTeamScore, VisitorTeamScore) "
                + "VALUES (" + num + " , '" + home + "' , '" + visitor + "', 0, 0)");
    }
    
    // Get all Matches
    public ObservableList<Matches> getMatchesList() throws SQLException {
        ObservableList<Matches> matchesList = FXCollections.observableArrayList();
        ResultSet rs;
        // Create a Statement object
        Statement stmt = connection.createStatement();
        // Create a string with a SELECT statement
        String sqlStatement = "SELECT * FROM Matches";
        // Execute the statement and return the result
        rs = stmt.executeQuery(sqlStatement);
        // Add your code here for Task #2
        while (rs.next()) {
            matchesList.add(new Matches(rs.getInt("matchNumber"),
                    rs.getString("homeTeam"),
                    rs.getString("visitorTeam"),
                    rs.getInt("homeTeamScore") ,
                    rs.getInt("visitorTeamScore")));
        }
        
        return matchesList;
    }

    // Get a String list of matches to populate the ComboBox used in Task #4.
    public ObservableList<String> getMatchesNamesList() throws SQLException {
        ObservableList<String> list = FXCollections.observableArrayList();

        ResultSet rsScore;
        
        // Create a Statement object
        Statement stmt = connection.createStatement();

        // Create a string with a SELECT statement
        String sqlStatementScores = "SELECT * FROM Matches";



        // Execute the statement and return the result

        rsScore = stmt.executeQuery(sqlStatementScores);
        
        // Loop the entire rows of rs and set the string values of list
       while (rsScore.next()){
            list.add(rsScore.getInt("MatchNumber") + "-" +rsScore.getString("HomeTeam") + "    -" + rsScore.getString("VisitorTeam"));
       }
        
        return list;
    }
    
    
    public void setTeamsScore(int matchNumber, int hScore, int vScore) throws SQLException
   {
        // Add your code here for Task #4
       Statement stmt = connection.createStatement();
       String sqlStatement = "SELECT * FROM Matches";
       stmt.executeUpdate("UPDATE Matches SET HomeTeamScore = "+hScore+", VisitorTeamScore = "+vScore+" WHERE MatchNumber = "+matchNumber);


   }

}
