package com.rocs.infirmary.desktop.data.dao.student.record.impl;

import com.rocs.infirmary.desktop.data.connection.ConnectionHelper;
import com.rocs.infirmary.desktop.data.dao.student.record.CreateMedicalRecordsDao;
import com.rocs.infirmary.desktop.data.model.person.student.StudentMedicalRecords;

import java.sql.*;
import java.util.Date;

public class CreateMedicalRecordsDaoImpl implements CreateMedicalRecordsDao {

    @Override
    public boolean createMedicalRecord(StudentMedicalRecords studentMedicalRecords) {
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