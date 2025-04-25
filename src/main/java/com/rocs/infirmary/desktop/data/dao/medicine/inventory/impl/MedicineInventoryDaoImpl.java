package com.rocs.infirmary.desktop.data.dao.medicine.inventory.impl;
import com.rocs.infirmary.desktop.data.dao.medicine.inventory.MedicineInventoryDao;
import com.rocs.infirmary.desktop.data.connection.ConnectionHelper;
import com.rocs.infirmary.desktop.data.dao.utils.queryconstants.medicine.inventory.QueryConstants;
import com.rocs.infirmary.desktop.data.model.inventory.medicine.Medicine;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;



/**
 * The MedicineInventoryDaoImpl class is an implementation of the MedicineInventoryDao interface.
 * This class includes methods for setting values of both medicine and inventory objects.
 * Includes method for calling the query constants and connection helper.
 *
 * Includes method for deleting Medicine by ItemName.
 */
public class MedicineInventoryDaoImpl implements MedicineInventoryDao {
    @Override
    public List<Medicine> getAllMedicine() {
        List<Medicine> MedicineInventoryList = new ArrayList<>();


        QueryConstants queryConstants = new QueryConstants();
        String sql= queryConstants.getLIST_ALL_MEDICINE_INVENTORY_QUERY();



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

        return  MedicineInventoryList;
    }

    @Override
    public boolean deleteMedicineByItemName(String itemName) {
        try (Connection con = ConnectionHelper.getConnection()) {
            QueryConstants queryConstants = new QueryConstants();

            String sql = queryConstants.getDeleteMedicineQuery();
            PreparedStatement stmt = con.prepareStatement(sql);
            if(isAvailable(itemName)) {

                stmt.setString(1,itemName);

                int affectedRows = stmt.executeUpdate();
                return affectedRows > 0;

            } else {
                return false;
            }


        }catch (SQLException e) {
            throw new RuntimeException();
        }

    }

    @Override
    public boolean isAvailable(String itemName) {
        try(Connection con = ConnectionHelper.getConnection()){
            QueryConstants queryConstants = new QueryConstants();

            String sql = queryConstants.filterDeletedMedicine();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1,itemName);

           ResultSet rs =  stmt.executeQuery();
           return rs.next();

        }catch (SQLException e ) {
            System.out.println("SQL error " + e.getMessage());
        }

        return false;
    }

}



