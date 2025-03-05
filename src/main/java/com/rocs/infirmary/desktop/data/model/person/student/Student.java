package com.rocs.infirmary.desktop.data.model.person.student;

import com.rocs.infirmary.desktop.data.model.person.Person;

import java.util.Date;

public class Student extends Person {

    private int studentSectionId;
    private int studentId;
    private int studentGuardianId;
    private  long lrn;
    private String symptoms;

    private String temperatureReadings;

    private Date visitDate;

    private String treatment;
    private String ailment;

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getStudentSectionId() {
        return studentSectionId;
    }

    public void setStudentSectionId(int studentSectionId) {
        this.studentSectionId = studentSectionId;
    }

    public int getStudentGuardianId() {
        return studentGuardianId;
    }

    public void setStudentGuardianId(int studentGuardianId) {
        this.studentGuardianId = studentGuardianId;
    }

    public long getLrn() {
        return lrn;
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

    public String getAilment() {
        return ailment;
    }

    public void setAilment(String ailment) {
        this.ailment = ailment;
    }
}
