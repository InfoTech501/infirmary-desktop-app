package com.rocs.medical.records.application.model.medicalrecord.createmedicalrecords;

import java.util.Date;

public class MedicalRecords {

    private int id;
    private long lrn;
    private String firstName;
    private String middleName;
    private String lastName;
    private String symptoms;
    private Date visitDateTime;
    private double temperatureReadings;
    private String treatment;
    private int nurseInChargeId;
    private Integer ailmentId;

    public MedicalRecords() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getLrn() {
        return lrn;
    }

    public void setLrn(long lrn) {
        this.lrn = lrn;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public Date getVisitDateTime() {
        return visitDateTime;
    }

    public void setVisitDateTime(Date visitDateTime) {
        this.visitDateTime = visitDateTime;
    }

    public double getTemperatureReadings() {
        return temperatureReadings;
    }

    public void setTemperatureReadings(double temperatureReadings) {
        this.temperatureReadings = temperatureReadings;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public int getNurseInChargeId() {
        return nurseInChargeId;
    }

    public void setNurseInChargeId(int nurseInChargeId) {
        this.nurseInChargeId = nurseInChargeId;
    }

    public Integer getAilmentId() {
        return ailmentId;
    }

    public void setAilmentId(Integer ailmentId) {
        this.ailmentId = ailmentId;
    }

    @Override
    public String toString() {
        return "MedicalRecords{" +
                "id=" + id +
                ", lrn=" + lrn +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", symptoms='" + symptoms + '\'' +
                ", visitDateTime=" + visitDateTime +
                ", temperatureReadings=" + temperatureReadings +
                ", treatment='" + treatment + '\'' +
                ", nurseInChargeId=" + nurseInChargeId +
                ", ailmentId=" + ailmentId +
                '}';
    }
}