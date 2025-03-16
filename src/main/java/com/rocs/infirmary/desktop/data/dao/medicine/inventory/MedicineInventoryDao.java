package com.rocs.infirmary.desktop.data.dao.medicine.inventory;

import com.rocs.infirmary.desktop.data.model.inventory.medicine.Medicine;
import java.util.List;

public interface MedicineInventoryDao {

    List<Medicine> getAllMedicine();

    boolean deleteMedicineByName(String medicineName); // Added this method
}