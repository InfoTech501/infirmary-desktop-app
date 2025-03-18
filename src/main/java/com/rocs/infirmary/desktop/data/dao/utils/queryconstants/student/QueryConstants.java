package com.rocs.infirmary.desktop.data.dao.utils.queryconstants.student;

public class QueryConstants {

    private final String GET_ALL_MEDICAL_INFORMATION_BY_LRN = "SELECT " +
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

    private final String GET_ALL_STUDENTS_MEDICAL_RECORDS = "SELECT " +
            "student.id, " +
            "person.first_name, " +
            "person.middle_name, " +
            "person.last_name, " +
            "person.age, " +
            "person.gender, " +
            "medical_record.symptoms, " +
            "medical_record.temperature_readings, " +
            "medical_record.visit_date, " +
            "medical_record.treatment " +
            "FROM medical_record " +
            "JOIN person ON medical_record.student_id = person.id " +
            "LEFT JOIN student ON medical_record.student_id = student.id";

    private final String INSERT_MEDICAL_RECORD_WITH_JOIN = "INSERT INTO medical_record " +
            "(student_id, symptoms, visit_datetime, temperature, treatment, nurse_in_charge_id, ailment_id) " +
            "SELECT p.id, ?, ?, ?, ?, ?, ? " +
            "FROM person p " +
            "WHERE p.first_name = ? AND p.middle_name = ? AND p.last_name = ? " +
            "AND EXISTS (SELECT 1 FROM person WHERE first_name = ? AND middle_name = ? AND last_name = ?)";


    public String getAllStudentMedicalRecords() {
        return GET_ALL_STUDENTS_MEDICAL_RECORDS;
    }

    public String getAllMedicalInformationByLRN() {
        return GET_ALL_MEDICAL_INFORMATION_BY_LRN;
    }

    public String getInsertMedicalRecord() { return INSERT_MEDICAL_RECORD_WITH_JOIN;}
}
