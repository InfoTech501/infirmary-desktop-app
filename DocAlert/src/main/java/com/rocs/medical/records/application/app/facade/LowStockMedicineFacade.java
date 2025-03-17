package com.rocs.medical.records.application.app.facade;

import com.rocs.medical.records.application.data.model.LowStockItem;

import java.util.List;
/**
 * The LowStockMedicineFacade interface defines the contract for inventory management operations.
 * It provides methods for checking low stock items and sending notifications.
 */
public interface LowStockMedicineFacade {
    /**
     * Checks for low stock items and sends notifications for each item.
     *
     * @return a list of LowStockItem objects that are low in stock.
     */
    List<LowStockItem> checkLowStockAndNotify();
}
