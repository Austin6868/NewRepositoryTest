package sample;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Image {


    private final IntegerProperty imageID;
    private final IntegerProperty size;


    public Image(){

        this.imageID = new SimpleIntegerProperty();
        this.size = new SimpleIntegerProperty();
    }

    public Image(String type, int id, int size, int resolution){
        this.imageID = new SimpleIntegerProperty(id);
        this.size = new SimpleIntegerProperty(size);
    }


    public void setImageID(Integer imageID) {
        this.imageID.set(imageID);
    }
    public Integer getImageID() {
        return imageID.get();
    }

    public void setSize(Integer size) {
        imageID.set(size);
    }
    public Integer getSize() {
        return size.get();
    }

}
