package com.rocs.infirmary.desktop.data.dao.medicine.impl;

import com.rocs.infirmary.desktop.data.connection.ConnectionHelper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MedicineDaoImpl implements MedicineDao {

    @Override
    public boolean deleteMedicineByName(String medicineName) {
        String sql = "DELETE FROM INVENTORY WHERE ITEM_NAME = ?";

        try (Connection con = ConnectionHelper.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, medicineName);
            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting medicine: " + e.getMessage());
        }

        return false;
    }
}