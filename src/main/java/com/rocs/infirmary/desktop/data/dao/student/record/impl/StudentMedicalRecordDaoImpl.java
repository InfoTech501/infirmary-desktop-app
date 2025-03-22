package com.rocs.infirmary.desktop.data.dao.student.record.impl;

import com.rocs.infirmary.desktop.data.connection.ConnectionHelper;
import com.rocs.infirmary.desktop.data.dao.utils.queryconstants.student.QueryConstants;
import com.rocs.infirmary.desktop.data.model.person.student.MedicalRecord;
import com.rocs.infirmary.desktop.data.model.person.student.Student;
import com.rocs.infirmary.desktop.data.dao.student.record.StudentMedicalRecordDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The StudentMedicalRecordDaoImpl class implements the StudentMedicalRecordDao interface
 * it provides methods for interacting with the infirmary database.
 * It includes methods for retrieving, adding, updating, and deleting student medical records.
 */
public class StudentMedicalRecordDaoImpl implements StudentMedicalRecordDao {


    public Student getMedicalInformationByLRN(long LRN) {

        Student studentMedicalRecord = null;
        try (Connection con = ConnectionHelper.getConnection()) {

            QueryConstants queryConstants = new QueryConstants();

            String sql = queryConstants.getAllMedicalInformationByLRN();

            PreparedStatement stmt = con.prepareStatement(sql);


            stmt.setLong(1, LRN);
            ResultSet rs = stmt.executeQuery();


            if (rs.next()) {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studentMedicalRecord;


    }

    @Override
    public List<Student> getAllStudentMedicalRecords() {
        List<Student> medicalRecords = new ArrayList<>();
        try (Connection con = ConnectionHelper.getConnection()) {

            QueryConstants queryConstants = new QueryConstants();

            String sql = queryConstants.getAllStudentMedicalRecords();

            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Student studentMedicalRecord = new Student();

                studentMedicalRecord.setFirstName(rs.getString("first_name"));
                studentMedicalRecord.setMiddleName(rs.getString("middle_name"));
                studentMedicalRecord.setLastName(rs.getString("last_name"));
                studentMedicalRecord.setAge(rs.getInt("age"));
                studentMedicalRecord.setGender(rs.getString("gender"));
                studentMedicalRecord.setSymptoms(rs.getString("symptoms"));
                studentMedicalRecord.setTemperatureReadings(rs.getString("temperature_readings"));
                studentMedicalRecord.setVisitDate(rs.getDate("visit_date"));
                studentMedicalRecord.setTreatment(rs.getString("treatment"));

                medicalRecords.add(studentMedicalRecord);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching student medical records", e);
        }

        return medicalRecords;
    }

    @Override
    public boolean createMedicalRecord(MedicalRecord medicalRecords) {
        try (Connection con = ConnectionHelper.getConnection()) {
            QueryConstants queryConstants = new QueryConstants();
            String sql = queryConstants.getInsertMedicalRecord();

            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setLong(1, medicalRecords.getStudentId());
                stmt.setString(2, medicalRecords.getSymptoms());
                if (medicalRecords.getVisitDate() != null) {
                    stmt.setTimestamp(3, medicalRecords.getVisitDate());
                } else {
                    stmt.setTimestamp(3, null);
                }
                stmt.setString(4, medicalRecords.getTemperatureReadings());
                stmt.setString(5, medicalRecords.getTreatment());
                stmt.setLong(6, medicalRecords.getNurseInChargeId());
                Long ailmentId = medicalRecords.getAilmentId();
                if (ailmentId == null) {
                    stmt.setNull(7, Types.INTEGER);
                } else {
                    stmt.setLong(7, ailmentId);
                }
                Long medHistoryId = medicalRecords.getMedHistoryId();
                if (medHistoryId == null) {
                    stmt.setNull(8, Types.INTEGER);
                } else {
                    stmt.setLong(8, medHistoryId);
                }

                int rowsAffected = stmt.executeUpdate();
                return rowsAffected > 0;
            } catch (SQLException e) {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Student getStudentById(long studentId) {
        Student student = null;
        try (Connection con = ConnectionHelper.getConnection()) {
            QueryConstants queryConstants = new QueryConstants();
            String sql = queryConstants.getStudentById();
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setLong(1, studentId);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {
                    student = new Student();
                    student.setFirstName(rs.getString("first_name"));
                    student.setMiddleName(rs.getString("middle_name"));
                    student.setLastName(rs.getString("last_name"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error fetching student by ID", e);
        }
        return student;
    }
}



