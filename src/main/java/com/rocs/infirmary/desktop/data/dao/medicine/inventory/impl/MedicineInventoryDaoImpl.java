package com.rocs.infirmary.desktop.data.dao.medicine.inventory.impl;

import com.rocs.infirmary.desktop.data.connection.ConnectionHelper;
import com.rocs.infirmary.desktop.data.dao.medicine.inventory.MedicineInventoryDao;
import com.rocs.infirmary.desktop.data.dao.utils.queryconstants.medicine.inventory.QueryConstants;
import com.rocs.infirmary.desktop.data.model.inventory.medicine.Medicine;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;


/**
 * The MedicineInventoryDaoImpl class is an implementation of the MedicineInventoryDao interface.
 * This class includes methods for setting values of both medicine and inventory objects.
 * Includes method for calling the query constants and connection helper.
 */
public class MedicineInventoryDaoImpl implements MedicineInventoryDao {
    @Override
    public List<Medicine> getAllMedicine() {
        List<Medicine> MedicineInventoryList = new ArrayList<>();


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


                MedicineInventoryList.add(medicine);
            }

        } catch (SQLException e) {
            System.out.println("An SQL Exception occurred: " + e.getMessage());
        }

        return MedicineInventoryList;
    }

    @Override
    public boolean addMedicine(Medicine medicine) {
        getAllMedicine();


        try (Connection con = ConnectionHelper.getConnection()) {

            QueryConstants queryConstants = new QueryConstants();
            String sql = queryConstants.getADD_MEDICINE_INVENTORY_QUERY();
            con.prepareStatement(sql);
            PreparedStatement stmt;
            stmt = con.prepareStatement("INSERT INTO Medicine (medicine_id, item_name, description, expiration_date) VALUES (?, ?, ?, ?)");
            stmt.setString(1, medicine.getMedicineId());
            stmt.setString(2, medicine.getItemName());
            stmt.setString(3, medicine.getDescription());
            stmt.setTimestamp(4, medicine.getExpirationDate());
            stmt.executeUpdate();
            return true;


        } catch (SQLException e) {
            System.out.println("An SQL Exception occurred: " + e.getMessage());
        }

        return false;
    }
}

