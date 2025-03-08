package com.rocs.medical.records.application.model.deleteMedicalRecord;

public class StudentMedicalRecords {

    private int Id;

    private int StudentId;

    private int AilmentId;

    private String MedHistoryId;

    private int NurseInChargeIn;

    private String Symptoms;

    private int TemperatureReadings;

    private int VisitDate;

    private String Treatment;

    public StudentMedicalRecords() {
    }

    public StudentMedicalRecords(int id, int studentId, int ailmentId, String medHistoryId, int nurseInChargeIn, String symptoms, int temperatureReadings, int visitDate, String treatment) {
        this.Id = id;
        this.StudentId = studentId;
        this.AilmentId = ailmentId;
        this.MedHistoryId = medHistoryId;
        this.NurseInChargeIn = nurseInChargeIn;
        this.Symptoms = symptoms;
        this.TemperatureReadings = temperatureReadings;
        this.VisitDate = visitDate;
        this.Treatment = treatment;
    }

    public int getId() { return Id; }

    public void setId(int id) { this.Id = id; }

    public int getStudentId() {
        return StudentId;
    }

    public void setStudentId(int studentId) {
        this.StudentId = studentId;
    }

    public int getAilmentId() {
        return AilmentId;
    }

    public void setAilmentId(int ailmentId) { this.AilmentId = ailmentId; }

    public String getMedHistoryId() {
        return MedHistoryId;
    }

    public void setMedHistoryId(String medHistoryId) { this.MedHistoryId = medHistoryId; }

    public int getNurseInChargeIn() {
        return NurseInChargeIn;
    }

    public void setNurseInChargeIn(int nurseInChargeIn) { this.NurseInChargeIn = nurseInChargeIn; }

    public String getSymptoms() {
        return Symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.Symptoms = symptoms;
    }

    public int getTemperatureReadings() {
        return TemperatureReadings;
    }

    public void setTemperatureReadings(int temperatureReadings) {
        this.TemperatureReadings = temperatureReadings;
    }

    public int getVisitDate() {
        return VisitDate;
    }

    public void setVisitDate(int visitDate) {
        this.VisitDate = visitDate;
    }

    public String getTreatment() {
        return Treatment;
    }

    public void setTreatment(String treatment) { this.Treatment = treatment; }

}


