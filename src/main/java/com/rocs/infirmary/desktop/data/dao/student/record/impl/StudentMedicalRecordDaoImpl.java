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


    /**
     * Deactivates a student's medical record based on their LRN (Learner Reference Number).
     * Instead of completely removing the data, it likely updates the status
     * of the medical record in the database to indicate it's no longer active.
     * <p>
     * A status value of 0 means the record is no longer active (deleted),
     * while a status of 1 means the record is still active and present in the system.
     */
    @Override
    public boolean deleteStudentMedicalRecordByLrn(long LRN) {
        Student studentMedicalRecord = getStudent(LRN);

        try (Connection con = ConnectionHelper.getConnection()) {

            QueryConstants queryConstants = new QueryConstants();

            String sql = queryConstants.updateMedicalRecordStatus();

            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, studentMedicalRecord.getStudentId());

            int affectedRow = preparedStatement.executeUpdate();
            return affectedRow > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static Student getStudent(long LRN) {
        Student studentMedicalRecord = null;

        try (Connection con = ConnectionHelper.getConnection()) {

            QueryConstants queryConstants = new QueryConstants();

            String sql = queryConstants.getAllMedicalInformationByLRN();

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setLong(1, LRN);

            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                studentMedicalRecord = new Student();
                studentMedicalRecord.setStudentId(resultSet.getInt("student_id"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studentMedicalRecord;
    }

    @Override
    public boolean createMedicalRecord(MedicalRecord medicalRecords) {
        try (Connection con = ConnectionHelper.getConnection()) {
            QueryConstants queryConstants = new QueryConstants();
            String sql = queryConstants.getInsertMedicalRecord();
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setLong(1, medicalRecords.getStudentId());
                stmt.setLong(2, medicalRecords.getAilmentId());
                if (medicalRecords.getMedHistoryId() != null) {
                    stmt.setLong(3, medicalRecords.getMedHistoryId());
                } else {
                    stmt.setNull(3, Types.INTEGER);
                }
                stmt.setLong(4, medicalRecords.getNurseInChargeId());
                stmt.setString(5, medicalRecords.getSymptoms());
                stmt.setString(6, medicalRecords.getTemperatureReadings());
                if (medicalRecords.getVisitDate() != null) {
                    stmt.setTimestamp(7, medicalRecords.getVisitDate());
                } else {
                    stmt.setNull(7, Types.TIMESTAMP);
                }
                stmt.setString(8, medicalRecords.getTreatment());
                stmt.setInt(9, 1);
                int rowsAffected = stmt.executeUpdate();
                return rowsAffected > 0;
            } catch (SQLException e) {
                System.err.println("SQL Error in createMedicalRecord: " + e.getMessage());
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to the database", e);
        }
    }
}













