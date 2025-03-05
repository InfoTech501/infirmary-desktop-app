package com.rocs.infirmary.desktop.application.data.dao.low.stock.medicine.impl;

import com.rocs.infirmary.desktop.application.data.connection.ConnectionHelper;
import com.rocs.infirmary.desktop.application.model.inventory.LowStockItem;
import com.rocs.infirmary.desktop.application.data.dao.low.stock.medicine.LowStockMedicineDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class LowStockMedicineDaoImpl implements LowStockMedicineDao {
    @Override
    public List<LowStockItem> getAllLowStockMedicine() {
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

