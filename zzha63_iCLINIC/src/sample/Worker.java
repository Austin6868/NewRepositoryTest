package sample;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Worker {
    private final StringProperty name;
    private final StringProperty Role;
    private final StringProperty Profession;
    private final StringProperty UserName;
    private final StringProperty Password;

    public Worker(String name, String role, String profession, String userAccountName, String password){
        this.name = new SimpleStringProperty(name);
        this.Role = new SimpleStringProperty(role);
        this.Profession = new SimpleStringProperty(profession);
        this.UserName = new SimpleStringProperty(userAccountName);
        this.Password = new SimpleStringProperty(password);
    }

    public void setName(String name) {
        this.name.set(name);
    }
    public String getName() {
        return name.get();
    }

    public void setRole(String role) {
        Role.set(role);
    }
    public String getRole() {
        return Role.get();
    }

    public void setProfession(String profession) {
        Profession.set(profession);
    }
    public String getProfession() {
        return Profession.get();
    }

    public void setUserName(String userName) {
        UserName.set(userName);
    }
    public String getUserName() {
        return UserName.get();
    }

    public void setPassword(String password) {
        Password.set(password);
    }
    public String getPassword() {
        return Password.get();
    }

}
