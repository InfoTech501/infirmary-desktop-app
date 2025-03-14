package com.rocs.infirmary.desktop.data.dao.student.record.impl;

import com.rocs.infirmary.desktop.data.connection.ConnectionHelper;
import com.rocs.infirmary.desktop.data.dao.utils.queryconstants.student.QueryConstants;
import com.rocs.infirmary.desktop.data.model.person.student.Student;
import com.rocs.infirmary.desktop.data.dao.student.record.StudentMedicalRecordDao;

import java.sql.*;
import java.util.Date;


public class StudentMedicalRecordDaoImpl implements StudentMedicalRecordDao {


    public Student getMedicalInformationByLRN(long LRN) {

       Student studentMedicalRecord = null;
        try (Connection con = ConnectionHelper.getConnection()) {

            QueryConstants queryConstants  = new QueryConstants();

            String sql = queryConstants.getAllMedicalInformationByLRN();

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

    public static class CreateMedicalRecordsDaoImpl implements CreateMedicalRecordsDao {

        @Override
        public boolean createMedicalRecord(Student.StudentMedicalRecords studentMedicalRecords) {
            try (Connection con = ConnectionHelper.getConnection()) {
                int studentId = getStudentIdByName(con, studentMedicalRecords.getFirstName(), studentMedicalRecords.getMiddleName(), studentMedicalRecords.getLastName());
                if (studentId == -1) {
                    return false;
                }

                String sql = "INSERT INTO medical_record (student_id, symptoms, visit_datetime, temperature, treatment, nurse_in_charge_id, ailment_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
                try (PreparedStatement stmt = con.prepareStatement(sql)) {

                    stmt.setInt(1, studentId);
                    stmt.setString(2, studentMedicalRecords.getSymptoms());

                    if (studentMedicalRecords.getVisitDateTime() != null) {
                        try {
                            Date ldt = studentMedicalRecords.getVisitDateTime();
                            if (ldt != null) {
                                stmt.setTimestamp(3, new Timestamp(ldt.getTime()));
                            } else {
                                stmt.setTimestamp(3, null);
                            }
                        } catch (Exception e) {
                            return false;
                        }
                    }

                    stmt.setDouble(4, studentMedicalRecords.getTemperatureReadings());
                    stmt.setString(5, studentMedicalRecords.getTreatment());
                    stmt.setInt(6, studentMedicalRecords.getNurseInChargeId());

                    Integer ailmentId = studentMedicalRecords.getAilmentId();
                    if (ailmentId == null) {
                        throw new IllegalArgumentException("Ailment ID cannot be null.");
                    } else {
                        stmt.setInt(7, ailmentId);
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

        private int getStudentIdByName(Connection con, String firstName, String middleName, String lastName) throws SQLException {
            String sql = "SELECT id FROM person WHERE first_name = ? AND middle_name = ? AND last_name = ?";
            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
                pstmt.setString(1, firstName);
                pstmt.setString(2, middleName);
                pstmt.setString(3, lastName);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        return rs.getInt("id");
                    } else {
                        return -1;
                    }
                }
            }
        }
    }
}



