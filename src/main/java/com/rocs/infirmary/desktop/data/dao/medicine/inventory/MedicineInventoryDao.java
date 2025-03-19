package com.rocs.infirmary.desktop.data.dao.medicine.inventory;

import com.rocs.infirmary.desktop.data.model.inventory.medicine.Medicine;
import java.util.List;


/*
 * return list of Medicine and inventory objects with details such as medicine name, description, quantity, and expiration date.
 */
public interface MedicineInventoryDao {

    List<Medicine> getAllMedicine();

}
