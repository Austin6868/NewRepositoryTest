package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class WorkerAdapter {
    Connection connection;

    public WorkerAdapter(Connection conn, Boolean reset) throws SQLException {


        connection = conn;
        Statement stmt = connection.createStatement();
        if (reset){
            try {
                stmt.executeUpdate("DROP TABLE Workers");
            } catch (SQLException ex) {

            } finally {
                stmt.executeUpdate("CREATE TABLE Workers(" +
                        "WorkerName VARCHAR(20) NOT NULL PRIMARY KEY," +
                        "Role VARCHAR(20)," + "Profession VARCHAR(20)," +
                        "AccountName VARCHAR(20) NOT NULL," + "Password VARCHAR(20) NOT NULL" +")");
            }
    }
}

public void addWorker(String name, String role, String profession, String accountName, String password) throws SQLException{
        Statement stmt = connection.createStatement();
    try {
        stmt.executeUpdate("INSERT INTO Workers (WorkerName, Role, Profession, AccountName, Password) VALUES " + "('"
                + name + "',"
                + "'" + role + "',"
                + "'" + profession + "',"
                + "'" + accountName + "',"
                + "'" + password + "')");
    } catch (SQLException e) {

    }
}

    public void deleteWorker(String workerName) throws SQLException {

        Statement stmt = connection.createStatement();

            stmt.executeUpdate("DELETE FROM Workers WHERE WorkerName='" + workerName + "'");
    }

    public void update(String name, String role, String profession, String oldName, String password,String newAccountName) throws SQLException{
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("Update Workers SET WorkerName='" + name + "' WHERE WorkerName='" + oldName + "'");
        stmt.executeUpdate("Update Workers SET Role='" + role + "' WHERE WorkerName='" + oldName + "'");
        stmt.executeUpdate("Update Workers SET Password='" + password + "' WHERE WorkerName='" + oldName + "'");
        stmt.executeUpdate("Update Workers SET AccountName='" + newAccountName + "' WHERE WorkerName='" + oldName + "'");
        stmt.executeUpdate("Update Workers SET Profession='" + profession + "' WHERE WorkerName='" + oldName + "'");

    }

    public ObservableList<Worker> getWorkersList(String s) throws SQLException {
        ObservableList<Worker> list = FXCollections.observableArrayList();
        ResultSet rs;

        Statement stmt = connection.createStatement();
        String sqlStatement = "SELECT * FROM Workers ";
        rs = stmt.executeQuery(sqlStatement);
        while (rs.next()) {
            list.add(new Worker(rs.getString("WorkerName"), rs.getString("Role"), rs.getString("Profession"), rs.getString("AccountName"), rs.getString("Password")));
        }
        return list;
    }


 public String[] getWorkersInfo(String name) throws SQLException {
        String[] workers = new String[5];

     ResultSet rs;
     Statement stmt = connection.createStatement();
     String sqlStatement = "SELECT * FROM Workers  WHERE WorkerName='" + name + "'";
     System.out.println(name);
     rs = stmt.executeQuery(sqlStatement);
     while (rs.next()) {
      workers[0] = rs.getString(1);
      workers[1] = rs.getString(2);
      workers[2] = rs.getString(3);
      workers[3] = rs.getString(4);
      workers[4] = rs.getString(5);
     }
     return workers;
 }
    public ObservableList<String> getWorkersNamesList() throws SQLException {

        ObservableList<String> list = FXCollections.observableArrayList();

        Statement stmt = connection.createStatement();

        ResultSet rs = stmt.executeQuery("SELECT * FROM Workers");

        while (rs.next()) {
            list.add(rs.getString("WorkerName"));
        }
        return list;

    }

}