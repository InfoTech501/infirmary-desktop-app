package com.rocs.infirmary.desktop.data.model.person.student;

import com.rocs.infirmary.desktop.data.model.person.Person;

import java.util.Date;

public class Student extends Person {

    private int studentSectionId;
    private int studentId;
    private int studentGuardianId;
    private long lrn;
    private String symptoms;
    private String temperatureReadings;
    private Date visitDate;
    private String treatment;
    private long id;
    private int ailmentId;
    private String medHistoryId;
    private int nurseInChargeId;
    private int setMedicalRecordId ;
    private int getMedicaRecordStatus;


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

    public int getStudentId() {
        return studentId;
    }

    public long getLrn() {
        return lrn;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setAilmentId(int ailmentId) {
        this.ailmentId = ailmentId;
    }

    public void setMedHistoryId(String medHistoryId) {
        this.medHistoryId = medHistoryId;
    }

    public void setNurseInChargeId(int nurseInChargeId) {
        this.nurseInChargeId = nurseInChargeId;
    }

    public void setMedicalRecordId(int medicalRecordIdId) {
        this.setMedicalRecordId = medicalRecordIdId;
    }

    public int getMedicalRecordStatus() {
        return getMedicaRecordStatus;
    }
}


