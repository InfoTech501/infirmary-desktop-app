package com.rocs.infirmary.desktop.application.data.dao.low.stock.medicine;

import com.rocs.infirmary.desktop.application.model.inventory.LowStockItem;

import java.util.List;

public interface LowStockMedicineDao {
    List<LowStockItem> getAllLowStockMedicine();
}
