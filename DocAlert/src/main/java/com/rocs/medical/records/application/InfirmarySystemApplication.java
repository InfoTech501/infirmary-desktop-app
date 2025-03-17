package com.rocs.medical.records.application;

import com.rocs.medical.records.application.app.facade.LowStockMedicineFacade;
import com.rocs.medical.records.application.app.facade.impl.LowStockMedicineFacadeImpl;
import com.rocs.medical.records.application.data.model.LowStockItem;

import java.util.List;

public class InfirmarySystemApplication {
    public static void main(String[] args) {
    LowStockMedicineFacade lowStockMedicineFacade = new LowStockMedicineFacadeImpl();
                 try {
        List<LowStockItem> lowStockItems = lowStockMedicineFacade.checkLowStockAndNotify();
    } catch (RuntimeException e) {
        System.err.println("Error checking low stock items: " + e.getMessage());
    }
}
}
