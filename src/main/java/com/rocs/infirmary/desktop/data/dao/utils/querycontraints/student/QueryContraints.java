package com.rocs.infirmary.desktop.data.dao.utils.querycontraints.student;

public class  QueryContraints {

    private final String getMedicalInformationByLRN = "SELECT " +
            "s.id AS student_id, " +
            "s.LRN, " +
            "p.first_name, " +
            "p.middle_name, " +
            "p.last_name, " +
            "p.age, " +
            "p.gender, " +
            "mr.symptoms, " +
            "mr.temperature_readings, " +
            "mr.visit_date AS visit_date, " +
            "mr.treatment " +
            "FROM student s " +
            "JOIN person p ON s.person_id = p.id " +
            "LEFT JOIN medical_record mr ON s.id = mr.student_id " +
            "WHERE s.LRN = ?";

    public String getGetMedicalInformationByLRN() {
        return getMedicalInformationByLRN;
    }
}
