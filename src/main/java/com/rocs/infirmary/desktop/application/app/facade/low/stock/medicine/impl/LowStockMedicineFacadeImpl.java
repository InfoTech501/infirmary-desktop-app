package com.rocs.infirmary.desktop.application.app.facade.low.stock.medicine.impl;

import com.rocs.infirmary.desktop.application.app.facade.low.stock.medicine.LowStockMedicineFacade;
import com.rocs.infirmary.desktop.application.model.inventory.LowStockItem;
import com.rocs.infirmary.desktop.application.data.dao.low.stock.medicine.impl.LowStockMedicineDaoImpl;

import java.util.List;

public class LowStockMedicineFacadeImpl implements LowStockMedicineFacade {
    private LowStockMedicineDaoImpl lowStockMedicineDao = new LowStockMedicineDaoImpl();
    @Override
    public List<LowStockItem> findAllLowStockMedicine() {
        List<LowStockItem> lowStockItems = lowStockMedicineDao.getAllLowStockMedicine();
        return lowStockItems;
    }

}


