package sample;

import javafx.beans.property.*;

import java.sql.Date;

public class Drug {

    private final StringProperty drugName;
    private final IntegerProperty drugID;
    private final StringProperty drugType;

    public Drug( String address, int drugID,String drugType){
        this.drugName = new SimpleStringProperty(address);
        this.drugID = new SimpleIntegerProperty(drugID);
        this.drugType = new SimpleStringProperty(drugType);

    }

    public Drug(){

        this.drugName = new SimpleStringProperty();
        this.drugID = new SimpleIntegerProperty();
        this.drugType = new SimpleStringProperty();

    }


    public void setDrugName(String address) {
        drugName.set(address);
    }
    public String getAddress() {
        return drugName.get();
    }
    public void setDrugID(Integer drugID) {
        this.drugID.set(drugID);
    }
    public Integer getDrugID() {
        return drugID.get();
    }
    public void setDrugType(String drugType) {
        this.drugType.set(drugType);
    }
    public String getDrugType() {
        return drugType.get();
    }

}
