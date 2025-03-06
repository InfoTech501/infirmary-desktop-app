package com.rocs.infirmary.desktop.data.model.reports;

import com.rocs.infirmary.desktop.data.model.person.Person;
import com.rocs.infirmary.desktop.data.model.person.student.Student;

import java.util.Date;

public class Report extends Student {
    private String gradeLevel;
    private String strand;
    private String symptoms;

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    public String getGradeLevel() {
        return gradeLevel;
    }

    public void setGradeLevel(String gradeLevel) {
        this.gradeLevel = gradeLevel;
    }

    public String getStrand() {
        return strand;
    }

    public void setStrand(String strand) {
        this.strand = strand;
    }
}
