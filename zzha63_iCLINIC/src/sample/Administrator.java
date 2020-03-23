package sample;

import javafx.beans.property.*;

public class Administrator {

    private final StringProperty name;
    private final IntegerProperty password;

    public Administrator(){
        this.name = new SimpleStringProperty();
        this.password = new SimpleIntegerProperty();
    }

    public Administrator(String name, int password){
        this.name = new SimpleStringProperty(name);
        this.password = new SimpleIntegerProperty(password);
    }

    public void setName(String name) {
        this.name.set(name);
    }
    public String  getName() {
        return name.get();
    }

    public void setPassword(int password) {
        this.password.set(password);
    }
    public int getPassword( ) { return password.get();    }

}
