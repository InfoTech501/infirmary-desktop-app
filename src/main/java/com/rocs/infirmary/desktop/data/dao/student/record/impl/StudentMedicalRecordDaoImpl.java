package com.rocs.infirmary.desktop.data.dao.student.record.impl;

import com.rocs.infirmary.desktop.data.connection.ConnectionHelper;
import com.rocs.infirmary.desktop.data.model.person.student.Student;
import com.rocs.infirmary.desktop.data.dao.student.record.StudentMedicalRecordDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;




public class StudentMedicalRecordDaoImpl implements StudentMedicalRecordDao {


    public Student getMedicalInformationByLRN(long LRN) {

       Student studentMedicalRecord = null;
        try (Connection con = ConnectionHelper.getConnection()) {

            String sql = "SELECT " +
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



            PreparedStatement stmt = con.prepareStatement(sql);


            stmt.setLong(1, LRN);
            ResultSet rs = stmt.executeQuery();


            if(rs.next()) {
                studentMedicalRecord = new Student();
                studentMedicalRecord.setStudentId(rs.getInt("student_id"));
                studentMedicalRecord.setLrn(rs.getLong("LRN"));
                studentMedicalRecord.setFirstName(rs.getString("first_name"));
                studentMedicalRecord.setMiddleName(rs.getString("middle_name"));
                studentMedicalRecord.setLastName(rs.getString("last_name"));
                studentMedicalRecord.setAge(rs.getInt("age"));
                studentMedicalRecord.setGender(rs.getString("gender"));
                studentMedicalRecord.setSymptoms(rs.getString("symptoms"));
                studentMedicalRecord.setTemperatureReadings(rs.getString("temperature_readings"));
                studentMedicalRecord.setVisitDate(rs.getDate("visit_date"));
                studentMedicalRecord.setTreatment(rs.getString("treatment"));
            }
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return  studentMedicalRecord;


    }
}



