package sample;

import javafx.beans.property.*;

import java.sql.Date;

public class Document {

    private final StringProperty documentName;
    private final IntegerProperty documentID;

    public Document( String address, int documentID){
        this.documentName = new SimpleStringProperty(address);
        this.documentID = new SimpleIntegerProperty(documentID);

    }

    public Document(){
        this.documentName = new SimpleStringProperty();
        this.documentID = new SimpleIntegerProperty();
    }

    public void setDocumentName(String address) {
        documentName.set(address);
    }
    public String getAddress() {
        return documentName.get();
    }
    public void setDocumentID(Integer documentID) {
        this.documentID.set(documentID);
    }
    public Integer getDocumentID() {
        return documentID.get();
    }
}
