package com.rocs.infirmary.desktop.data.dao.medicine.inventory.impl;
import com.rocs.infirmary.desktop.data.dao.medicine.inventory.MedicineInventoryDao;
import com.rocs.infirmary.desktop.data.connection.ConnectionHelper;
import com.rocs.infirmary.desktop.data.dao.utils.queryconstants.medicine.inventory.QueryConstants;
import com.rocs.infirmary.desktop.data.model.inventory.Inventory;
import com.rocs.infirmary.desktop.data.model.inventory.medicine.Medicine;
import oracle.jdbc.proxy.annotation.Pre;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;



/**
 * The MedicineInventoryDaoImpl class is an implementation of the MedicineInventoryDao interface.
 * This class includes methods for setting values of both medicine and inventory objects.
 * Includes method for calling the query constants and connection helper.
 *
 * Includes method for deleting Medicine by ItemName and method for checking if medicine
 * is not deleted (available) from the database.
 */
public class MedicineInventoryDaoImpl implements MedicineInventoryDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(MedicineInventoryDaoImpl.class);

    @Override
    public List<Medicine> getAllMedicine() {
        LOGGER.info("get all medicine started");
        List<Medicine> MedicineInventoryList = new ArrayList<>();


        QueryConstants queryConstants = new QueryConstants();
        String sql = queryConstants.getLIST_ALL_MEDICINE_INVENTORY_QUERY();


        try (Connection con = ConnectionHelper.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            LOGGER.info("Query in use" + sql);

            while (rs.next()) {

                Medicine medicine = new Medicine();

                medicine.setInventoryId(rs.getInt("INVENTORY_ID"));
                medicine.setMedicineId(rs.getString("MEDICINE_ID"));
                medicine.setItemType(rs.getString("ITEM_TYPE"));
                medicine.setQuantityAvailable(rs.getInt("QUANTITY"));
                medicine.setItemName(rs.getString("ITEM_NAME"));
                medicine.setDescription(rs.getString("DESCRIPTION"));
                medicine.setExpirationDate(rs.getTimestamp("EXPIRATION_DATE"));

                LOGGER.info("Data retrieved: " + "\n"
                        + "Inventory ID: " + medicine.getInventoryId() + "\n"
                        + "Medicine  ID: " + medicine.getMedicineId() + "\n"
                        + "Item type   : " + medicine.getItemType() + "\n"
                        + "Quantity    : " + medicine.getQuantity() + "\n"
                        + "Item Name   : " + medicine.getItemName() + "\n"
                        + "Description : " + medicine.getDescription() + "\n"
                        + "Expiration  : " + medicine.getExpirationDate()
                );

                MedicineInventoryList.add(medicine);
            }

        } catch (SQLException e) {
            LOGGER.error("SQLException Occurred: " + e.getMessage());
            System.out.println("An SQL Exception occurred: " + e.getMessage());
        }
        LOGGER.info("Data retrieved successfully");
        return MedicineInventoryList;
    }

    @Override
    public boolean deleteMedicineByItemName(String itemName) {
        LOGGER.info("Delete medicine started");
        try (Connection con = ConnectionHelper.getConnection()) {
            QueryConstants queryConstants = new QueryConstants();

            String sql = queryConstants.getDeleteMedicineQuery();
            PreparedStatement stmt = con.prepareStatement(sql);
            LOGGER.info("Query in use" + sql);
            LOGGER.info("data inserted: " + "Item Name: " + itemName);
            if (isAvailable(itemName)) {

                stmt.setString(1, itemName);

                int affectedRows = stmt.executeUpdate();
                LOGGER.info(itemName + " successfully deleted");
                return affectedRows > 0;

            } else {
                LOGGER.info(itemName + " Failed to delete");
                return false;
            }


        } catch (SQLException e) {
            LOGGER.error("SqlException Occurred: " + e.getMessage());
            throw new RuntimeException();
        }

    }


    @Override
    public boolean isAvailable(String itemName) {
        LOGGER.info("availability check started");
        try (Connection con = ConnectionHelper.getConnection()) {
            QueryConstants queryConstants = new QueryConstants();

            String sql = queryConstants.filterDeletedMedicine();
            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, itemName);

            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            LOGGER.error("SqlException Occurred: " + e.getMessage());
            System.out.println("SQL error " + e.getMessage());
        }
        LOGGER.info("availability check ended successfully");
        return false;
    }


    /**
     * Adds a new medicine record to the database.
     * <p>
     * This method inserts a new row into the medicine inventory table using the provided
     * Medicine object. It sets the values for medicine ID, item name, description,
     * expiration date, and a default availability status (set to 1, indicating available).
     * Includes method for calling the query constants and connection helper.
     */
    @Override
    public boolean addMedicine(Medicine medicine) {
        QueryConstants queryConstants = new QueryConstants();
        String sql = queryConstants.addMedicine();

        try (Connection con = ConnectionHelper.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, medicine.getMedicineId());
            stmt.setString(2, medicine.getItemName());
            stmt.setString(3, medicine.getDescription());
            stmt.setTimestamp(4, new Timestamp(medicine.getExpirationDate().getTime()));
            stmt.setInt(5, 1);
            int affectedRow = stmt.executeUpdate();

            return affectedRow > 0;

        } catch (SQLException e) {
            System.out.println("Medicine ID already exist");
        }

        return false;
    }

    @Override
    public boolean addInventory(String medicineId, String itemType, int quantity) {
        LOGGER.info("Accessing Add Inventory DAO");
        QueryConstants queryConstants = new QueryConstants();

        try {
            Connection con = ConnectionHelper.getConnection();
            String sql = queryConstants.addMedicineToInventory();
            LOGGER.info("query in use : {}", sql);

            PreparedStatement stmt = con.prepareStatement(sql);

            stmt.setString(1, medicineId);
            stmt.setString(2, itemType);
            stmt.setInt(3, quantity);

            LOGGER.info("Retrieved Data : " + " \n"
                    + "Medicine ID : " + medicineId + "\n"
                    + "ItemType   : " + itemType + "\n"
                    + "Quantity  : " + quantity
            );

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            LOGGER.error("SQL Exception Occurred {}", e.getMessage());
        }
        return false;
    }

    @Override
    public List<Medicine> findAllMedicine() {
        LOGGER.info("Accessing Find Medicine");
        List<Medicine> medicineList = new ArrayList<>();

        try (Connection con = ConnectionHelper.getConnection()) {
            QueryConstants queryConstants = new QueryConstants();
            String sql = queryConstants.retrieveAllMedicine();
            PreparedStatement stmt = con.prepareStatement(sql);
            LOGGER.info("Query in use  : {}",sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Medicine medicine = new Medicine();
                medicine.setMedicineId(rs.getString("MEDICINE_ID"));
                medicine.setItemName(rs.getString("ITEM_NAME"));
                medicineList.add(medicine);
            }

        } catch (SQLException e) {
            LOGGER.info("SQL Exception Occurred " + e.getMessage());
        }
        LOGGER.info("Retrieved Successfully");
        return medicineList;
    }


}















