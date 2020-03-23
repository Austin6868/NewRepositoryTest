package sample;

import javafx.beans.property.*;

import java.sql.Date;

public class Patient {

    private final StringProperty name;
    private final IntegerProperty phone;
    private final StringProperty patientAddress;
    private final IntegerProperty ID;
    private final StringProperty gender;
    private final DoubleProperty height;
    private final DoubleProperty weight;
    private final StringProperty bloodType;
    private final ObjectProperty<Date> dateOfBirth;
    private final IntegerProperty bedNumber;
    private final StringProperty assignedBy;

    public Patient(String name, int phone, String address, int ID, double height, double weight, String bloodType, String gender, Date dateOfBirth, int bedNumber, String assignedBy){
        this.name = new SimpleStringProperty(name);
        this.phone = new SimpleIntegerProperty(phone);
        this.patientAddress = new SimpleStringProperty(address);
        this.ID = new SimpleIntegerProperty(ID);
        this.gender = new SimpleStringProperty(gender);
        this.height = new SimpleDoubleProperty(height);
        this.weight = new SimpleDoubleProperty(weight);
        this.bloodType = new SimpleStringProperty(bloodType);
        this.dateOfBirth = new SimpleObjectProperty<>(dateOfBirth);
        this.bedNumber = new SimpleIntegerProperty(bedNumber);
        this.assignedBy = new SimpleStringProperty(assignedBy);

    }

    public Patient(){
        this.name = new SimpleStringProperty();
        this.phone = new SimpleIntegerProperty();
        this.patientAddress = new SimpleStringProperty();
        this.ID = new SimpleIntegerProperty();
        this.gender = new SimpleStringProperty();
        this.height = new SimpleDoubleProperty();
        this.weight = new SimpleDoubleProperty();
        this.bloodType = new SimpleStringProperty();
        this.dateOfBirth = new SimpleObjectProperty<>();
        this.bedNumber = new SimpleIntegerProperty();
        this.assignedBy = new SimpleStringProperty();
    }

    public void setName(String name) {
        this.name.set(name);
    }
    public String  getName() {
        return name.get();
    }
    public void setPhone(int phone) {
        this.phone.set(phone);
    }
    public int getPhone() {
        return phone.get();
    }
    public void setPatientAddress(String address) {
        patientAddress.set(address);
    }
    public String getAddress() {
        return patientAddress.get();
    }
    public void setID(Integer ID) {
        this.ID.set(ID);
    }
    public Integer getID() {
        return ID.get();
    }
    public void setGender(String gender) {
        this.gender.set(gender);
    }
    public String  getGender() {
        return gender.get();
    }
    public void setHeight(Double height) {
        this.height.set(height);
    }
    public Double getHeight() {
        return height.get();
    }
    public void setWeight(Double weight) {
        this.weight.set(weight);
    }
    public Double getWeight() {
        return weight.get();
    }
    public void setBloodType(String bloodType) {
        this.bloodType.set(bloodType);
    }
    public String getBloodType() {
        return bloodType.get();
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth.set(dateOfBirth);
    }
    public Date getDateOfBirth( ) { return dateOfBirth.get();    }

    public void setBedNumber(Integer bedNumber) {
        this.bedNumber.set(bedNumber);
    }
    public Integer getBedNumber() {
        return bedNumber.get();
    }
    public void setAssignedBy(String assignedBy) {
        this.assignedBy.set(assignedBy);
    }
    public String getAssignedBy() {
        return assignedBy.get();
    }

}
