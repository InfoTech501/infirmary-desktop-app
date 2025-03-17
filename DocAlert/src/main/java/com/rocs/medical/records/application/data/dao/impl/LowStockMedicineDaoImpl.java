package com.rocs.medical.records.application.data.dao.impl;
import com.rocs.medical.records.application.data.connection.ConnectionHelper;
import com.rocs.medical.records.application.data.dao.LowStockMedicineDao;
import com.rocs.medical.records.application.data.model.LowStockItem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The LowStockMedicineDaoImpl class implements the InventoryDao interface.
 * It provides the actual data access logic for retrieving low stock items from the database.
 */
public class LowStockMedicineDaoImpl implements LowStockMedicineDao {
    @Override
    public List<LowStockItem> getLowStockItems() {
        List<LowStockItem> lowStockItems = new ArrayList<>();
        String query = "SELECT description, quantity_available FROM inventory WHERE quantity_available < 20";

        try (Connection connection = ConnectionHelper.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                LowStockItem item = new LowStockItem(resultSet.getString("description"), resultSet.getInt("quantity_available"));
                lowStockItems.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lowStockItems;
    }
}

