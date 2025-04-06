package com.rocs.infirmary.desktop.data.model.person.student;

import java.sql.Timestamp;

public class MedicalRecord {

    private long studentId;
    private Long ailmentId;
    private long nurseInChargeId;
    private String symptoms;
    private String temperatureReadings;
    private Timestamp visitDate;
    private String treatment;
    private Long medHistoryId;

    public MedicalRecord() {
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public Long getAilmentId() {
        return ailmentId;
    }

    public void setAilmentId(Long ailmentId) {
        this.ailmentId = ailmentId;
    }

    public long getNurseInChargeId() {
        return nurseInChargeId;
    }

    public void setNurseInChargeId(long nurseInChargeId) {
        this.nurseInChargeId = nurseInChargeId;
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

    public Timestamp getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Timestamp visitDate) {
        this.visitDate = visitDate;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public Long getMedHistoryId() {
        return medHistoryId;
    }

    public void setMedHistoryId(Long medHistoryId) {
        this.medHistoryId = medHistoryId;
    }

    @Override
    public String toString() {
        return "MedicalRecord{" +
                "studentId=" + studentId +
                ", ailmentId=" + ailmentId +
                ", nurseInChargeId=" + nurseInChargeId +
                ", symptoms='" + symptoms + '\'' +
                ", temperatureReadings='" + temperatureReadings + '\'' +
                ", visitDate=" + visitDate +
                ", treatment='" + treatment + '\'' +
                ", medHistoryId=" + medHistoryId +
                '}';
    }
}