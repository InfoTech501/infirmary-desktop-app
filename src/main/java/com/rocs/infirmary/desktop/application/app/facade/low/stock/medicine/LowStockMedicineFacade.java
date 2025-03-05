package com.rocs.infirmary.desktop.application.app.facade.low.stock.medicine;

import com.rocs.infirmary.desktop.application.model.inventory.LowStockItem;

import java.util.List;
public interface LowStockMedicineFacade {
    List<LowStockItem> findAllLowStockMedicine();
}
