package com.rocs.infirmary.desktop.data.dao.medicine.inventory;

import com.rocs.infirmary.desktop.data.model.inventory.MedicineInventory;

public interface MedicineInventoryDao {
    boolean addMedicine(MedicineInventory medicineInventory);
}
