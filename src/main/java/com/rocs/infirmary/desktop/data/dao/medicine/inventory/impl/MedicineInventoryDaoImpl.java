package com.rocs.infirmary.desktop.data.dao.medicine.inventory.impl;

import com.rocs.infirmary.desktop.data.connection.ConnectionHelper;
import com.rocs.infirmary.desktop.data.dao.medicine.inventory.MedicineInventoryDao;
import com.rocs.infirmary.desktop.data.model.inventory.MedicineInventory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MedicineInventoryDaoImpl implements MedicineInventoryDao {

    @Override
    public boolean addMedicine(MedicineInventory medicineInventory) {

        try (Connection con = ConnectionHelper.getConnection()) {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO INVENTORY ,(inventory_id, medicine_id," +
                    "item_type,description ,quantity_available,) VALUES (?, ?, ?, ?,?,)");
            stmt.setInt(1, medicineInventory.getInventory_id());
            stmt.setString(2, medicineInventory.getMedicine_id());
            stmt.setString(3, medicineInventory.getItem_type());
            stmt.setString(4, medicineInventory.getDescription());
            stmt.setInt(5, medicineInventory.getQuantity_available());
            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("An SQL Exception occurred." + e.getMessage());
            return false;
        }

    }
}
