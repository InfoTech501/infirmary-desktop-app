package com.rocs.infirmary.desktop.data.dao.medicine.inventory.impl;
import com.rocs.infirmary.desktop.data.dao.medicine.inventory.MedicineInventoryDao;
import com.rocs.infirmary.desktop.data.connection.ConnectionHelper;
import com.rocs.infirmary.desktop.data.dao.utils.queryconstants.medicine.inventory.QueryConstants;
import com.rocs.infirmary.desktop.data.model.inventory.medicine.Medicine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;



/**
 * The MedicineInventoryDaoImpl class is an implementation of the MedicineInventoryDao interface.
 * This class includes methods for setting values of both medicine and inventory objects.
 * Includes method for calling the query constants and connection helper.
 */
public class MedicineInventoryDaoImpl implements MedicineInventoryDao {
    Logger LOGGER = LoggerFactory.getLogger(MedicineInventoryDaoImpl.class);
    @Override
    public List<Medicine> getAllMedicine() {
        LOGGER.info("get all medicine started");
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

                LOGGER.info("Data retrieved: "+"Inventory ID: "+medicine.getInventoryId()+"\n"
                        +"Medicine  ID: "+medicine.getMedicineId()+"\n"
                        +"Item type   : "+medicine.getItemType()+"\n"
                        +"Quantity    : "+medicine.getQuantity()+"\n"
                        +"Item Name   : "+medicine.getItemName()+"\n"
                        +"Description : "+medicine.getDescription()+"\n"
                        +"Expiration  : "+medicine.getExpirationDate());

                MedicineInventoryList.add(medicine);
            }

        } catch (SQLException e) {
            LOGGER.error("An SQL Exception occurred: " + e.getMessage());
            System.out.println("An SQL Exception occurred: " + e.getMessage());
        }
        LOGGER.info("Data retrieved successfully");
        return  MedicineInventoryList;
    }

    @Override
    public boolean deleteMedicineByItemName(String itemName) {
        LOGGER.info("Delete medicine started");
        try (Connection con = ConnectionHelper.getConnection()) {
            QueryConstants queryConstants = new QueryConstants();

            String sql = queryConstants.getDeleteMedicineQuery();
            PreparedStatement stmt = con.prepareStatement(sql);
            if(isAvailable(itemName)) {

                stmt.setString(1,itemName);

                int affectedRows = stmt.executeUpdate();
                LOGGER.info(itemName+" successfully deleted");
                return affectedRows > 0;

            } else {
                return false;
            }


        }catch (SQLException e) {
            LOGGER.error("Sql exception occurred: "+e);
            throw new RuntimeException();
        }

    }

    @Override
    public boolean isAvailable(String itemName) {
        LOGGER.info("availability check started");
        try(Connection con = ConnectionHelper.getConnection()){
            QueryConstants queryConstants = new QueryConstants();

            String sql = queryConstants.filterDeletedMedicine();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1,itemName);

           ResultSet rs =  stmt.executeQuery();
           return rs.next();

        }catch (SQLException e ) {
            LOGGER.error("Sql exception occurred: "+e);
            System.out.println("SQL error " + e.getMessage());
        }
        LOGGER.info("availability check ended successfully");
        return false;
    }

}



