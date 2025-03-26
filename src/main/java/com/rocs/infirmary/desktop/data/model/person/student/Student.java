package com.rocs.infirmary.desktop.data.model.person.student;

import com.rocs.infirmary.desktop.data.model.person.Person;

import java.util.Date;

public class Student extends Person {

    private int studentSectionId;
    private int studentId;
    private int studentGuardianId;
    private  long lrn;
    private String symptoms;
    private String addedremerks;
    private String temperatureReadings;
    private Date visitDate;
    private String treatment;
    private String medicationsadministered;


    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setLrn(long lrn) {
        this.lrn = lrn;
    }

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getAddedRemerks() {
        return addedremerks;
    }

    public void setAddedRemerks(String addedremerks) {
        this.addedremerks = addedremerks;
    }


    public String getTemperatureReadings() {
        return temperatureReadings;
    }

    public void setTemperatureReadings(String temperatureReadings) {
        this.temperatureReadings = temperatureReadings;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getMedicationsAdministered() { return medicationsadministered; }

    public void  setMedicationsadministered(String medicationsadministered) { this.medicationsadministered = medicationsadministered; }

}
