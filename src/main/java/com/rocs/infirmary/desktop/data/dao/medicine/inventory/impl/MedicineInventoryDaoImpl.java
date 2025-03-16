package com.rocs.infirmary.desktop.data.dao.medicine.inventory.impl;

import com.rocs.infirmary.desktop.data.dao.medicine.inventory.MedicineInventoryDao;
import com.rocs.infirmary.desktop.data.connection.ConnectionHelper;
import com.rocs.infirmary.desktop.data.dao.utils.queryconstants.medicine.inventory.QueryConstants;
import com.rocs.infirmary.desktop.data.model.inventory.medicine.Medicine;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicineInventoryDaoImpl implements MedicineInventoryDao {

    @Override
    public List<Medicine> getAllMedicine() {
        List<Medicine> medicineInventoryList = new ArrayList<>();
        QueryConstants queryConstants = new QueryConstants();
        String sql = queryConstants.getLIST_ALL_MEDICINE_INVENTORY_QUERY();

        try (Connection con = ConnectionHelper.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Medicine medicine = new Medicine();
                medicine.setInventoryId(rs.getInt("INVENTORY_ID"));
                medicine.setMedicineId(rs.getString("MEDICINE_ID"));
                medicine.setItemType(rs.getString("ITEM_TYPE"));
                medicine.setQuantityAvailable(rs.getInt("QUANTITY"));
                medicine.setItemName(rs.getString("ITEM_NAME"));
                medicine.setDescription(rs.getString("DESCRIPTION"));
                medicine.setExpirationDate(rs.getTimestamp("EXPIRATION_DATE"));

                medicineInventoryList.add(medicine);
            }
        } catch (SQLException e) {
            System.out.println("An SQL Exception occurred: " + e.getMessage());
        }

        return medicineInventoryList;
    }

    @Override
    public boolean deleteMedicineByName(String medicineName) {
        String sql = "DELETE FROM INVENTORY WHERE ITEM_NAME = ?";

        try (Connection con = ConnectionHelper.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, medicineName);
            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0; // Return true if rows were affected
        } catch (SQLException e) {
            System.out.println("Error deleting medicine: " + e.getMessage());
            return false;
        }
    }
}