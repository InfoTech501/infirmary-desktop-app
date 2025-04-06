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
     *
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
            preparedStatement.setInt(1,studentMedicalRecord.getStudentId());

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
            while(resultSet.next()){
                studentMedicalRecord = new Student();
                studentMedicalRecord.setStudentId(resultSet.getInt("student_id"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return studentMedicalRecord;
    }

}

 @Override
    public boolean createMedicalRecord(MedicalRecord medicalRecords) {
        try (Connection con = ConnectionHelper.getConnection()) {
            QueryConstants queryConstants = new QueryConstants();

            String sql = queryConstants.getInsertMedicalRecord();

            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                stmt.setString(1, medicalRecords.getSymptoms());

                if (medicalRecords.getVisitDate() != null) {
                    try {
                        Timestamp ldt = medicalRecords.getVisitDate();
                        if (ldt != null) {
                            stmt.setTimestamp(2, ldt);
                        } else {
                            stmt.setTimestamp(2, null);
                        }
                    } catch (Exception e) {
                        return false;
                    }
                } else {
                    stmt.setTimestamp(2, null);
                }

                stmt.setString(3, medicalRecords.getTemperatureReadings());
                stmt.setString(4, medicalRecords.getTreatment());
                stmt.setLong(5, medicalRecords.getNurseInChargeId());

                Long ailmentId = medicalRecords.getAilmentId();
                if (ailmentId == null) {
                    stmt.setNull(6, Types.INTEGER);
                } else {
                    stmt.setLong(6, ailmentId);
                }

                stmt.setString(7, medicalRecords.getFirstName());
                stmt.setString(8, medicalRecords.getMiddleName());
                stmt.setString(9, medicalRecords.getLastName());

                stmt.setString(10, medicalRecords.getFirstName());
                stmt.setString(11, medicalRecords.getMiddleName());
                stmt.setString(12, medicalRecords.getLastName());

                stmt.setLong(13, medicalRecords.getStudentId());

                int rowsAffected = stmt.executeUpdate();
                return rowsAffected > 0;
            } catch (SQLException e) {
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
