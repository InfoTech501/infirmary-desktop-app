package com.rocs.medical.records.application.data.dao;

import com.rocs.medical.records.application.data.model.LowStockItem;

import java.util.List;

/**
 * The LowStockMedicineDao interface defines the contract for data access operations related to inventory items.
 * It provides methods for retrieving low stock items.
 */
public interface LowStockMedicineDao {
    /**
     * Retrieves a list of inventory items that are low in stock.
     *
     * @return a list of LowStockItem objects that are low in stock.
     */
    List<LowStockItem> getLowStockItems();
}
