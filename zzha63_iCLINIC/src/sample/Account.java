package sample;

import javafx.beans.property.*;

import java.sql.Date;

public class Account {

    private final StringProperty username;
    private final StringProperty password;

    public Account(String username , String password){
        this.username = new SimpleStringProperty(username);
        this.password = new SimpleStringProperty(password);

    }

    public Account(){
        this.username = new SimpleStringProperty();
        this.password = new SimpleStringProperty();
    }


    public void setUsername(String address) {
        username.set(address);
    }
    public String getAddress() {
        return username.get();
    }
    public void setGender(String gender) {
        this.password.set(gender);
    }
    public String  getGender() {
        return password.get();
    }

}
