package com.rocs.infirmary.desktop.data.dao.medicine.inventory;

import com.rocs.infirmary.desktop.data.model.inventory.medicine.Medicine;
import java.util.List;



public interface MedicineInventoryDao {

/*
* return list of Medicine and inventory objects with details such as medicine name, description, quantity, and expiration date.
*/
    List<Medicine> getAllMedicine();


/**
 * This is intended to delete medicine based on its Name(ItemName).
 * The ItemName is a unique identifier assigned to a medicine. This value is used to locate and delete the corresponding medicine.
 */
    boolean deleteMedicineByItemName(String itemName);
    boolean isAvailable(String itemName);
}
