package com.rocs.medical.records.application.data.dao.deleteMedicalRecord.impl;


import com.rocs.medical.records.application.data.connection.ConnectionHelper;
import com.rocs.medical.records.application.data.dao.deleteMedicalRecord.StudentMedicalRecordsDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentMedicalRecordsDaoImpl implements StudentMedicalRecordsDao {

    @Override
    public boolean deleteStudentMedicalRecordById(String Id) {
        try (Connection con = ConnectionHelper.getConnection()) {


            PreparedStatement deleteFromStudentMedicalRecord = con.prepareStatement("DELETE FROM medical_record WHERE ID = ?");
            deleteFromStudentMedicalRecord.setString(1, Id);
            int affectedRows = deleteFromStudentMedicalRecord.executeUpdate();


            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("An SQL Exception occurred." + e.getMessage());
            return false;
        }
    }

}
