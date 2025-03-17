package com.rocs.medical.records.application.app.facade.impl;

import com.rocs.medical.records.application.app.facade.LowStockMedicineFacade;
import com.rocs.medical.records.application.data.dao.impl.LowStockMedicineDaoImpl;
import com.rocs.medical.records.application.data.model.LowStockItem;

import java.util.List;

/**
 * The LowStockMedicineFacadeImpl class implements the LowStockMedicineFacade interface.
 * It provides the functionality to check for low stock items and send notifications.
 */
public class LowStockMedicineFacadeImpl implements LowStockMedicineFacade {
    private LowStockMedicineDaoImpl lowStockMedicineDao;

    /**
     * Constructor to initialize the InventoryDaoImpl instance.
     */
    public LowStockMedicineFacadeImpl() {
        this.lowStockMedicineDao = new LowStockMedicineDaoImpl();
    }

    @Override
    public List<LowStockItem> checkLowStockAndNotify() {
        List<LowStockItem> lowStockItems = lowStockMedicineDao.getLowStockItems();
        for (LowStockItem item : lowStockItems) {
            sendNotification(item.getDescription(), item.getQuantityAvailable());
        }
        return lowStockItems;
    }

    private void sendNotification(String description, int quantityAvailable) {
        System.out.println("Medicine Name: " + description);
        System.out.println("Current Stock Level: " + quantityAvailable);
        System.out.println("Notification: The stock level of " + description + " is low. Current stock level: " + quantityAvailable + ". Please reorder supplies.");
    }

}
