package sample;

import javafx.beans.property.*;

import java.sql.Date;

public class Metadata {

    private final IntegerProperty patientID;
    private final ObjectProperty<Date> creationDate;
    private final ObjectProperty<Date> modificationDate;
    private final IntegerProperty modifier;
    private final StringProperty modificationLog;

    public Metadata(int patientID, Date creationDate, int userID, Date modificationDate, String description){
        this.patientID = new SimpleIntegerProperty(patientID);
        this.creationDate = new SimpleObjectProperty<>(creationDate);
        this.modificationDate = new SimpleObjectProperty<>(modificationDate);
        this.modifier = new SimpleIntegerProperty(userID);
        this.modificationLog = new SimpleStringProperty(description);
    }

    public Metadata(){
        this.patientID = new SimpleIntegerProperty();
        this.creationDate = new SimpleObjectProperty<>();
        this.modificationDate = new SimpleObjectProperty<>();
        this.modifier = new SimpleIntegerProperty();
        this.modificationLog = new SimpleStringProperty();
    }

    public void setPatientID(Integer patientID) {
        this.patientID.set(patientID);
    }
    public Integer getPatientID() {
        return patientID.get();
    }
    public void setCreationDate(Date creationDate) {
        this.creationDate.set(creationDate);
    }
    public Date getCreationDate( ) { return creationDate.get();    }

    public void setModificationDate(Date modificationDate) {
        this.modificationDate.set(modificationDate);
    }
    public Date getModificationDate( ) { return modificationDate.get();    }

    public void setModifier(Integer modifier) {
        this.modifier.set(modifier);
    }
    public Integer getModifier() {
        return modifier.get();
    }

    public void setModificationLog(String modificationLog) {
        this.modificationLog.set(modificationLog);
    }
    public String getModificationLog() {
        return modificationLog.get();
    }

}
