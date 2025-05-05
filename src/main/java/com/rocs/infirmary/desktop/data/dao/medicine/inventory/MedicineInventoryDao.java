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
 *
 * @param itemName is a unique identifier assigned to a medicine. This value is used to locate and delete the corresponding medicine.
 * @return (true) if the medicine was successfully deleted, (false) otherwise (not found).
 *
 */
    boolean deleteMedicineByItemName(String itemName);




/**
* This is intended to check whether a specific medicine item is available in the database.
*
* @param itemName The name of the medicine item to check.
* @return (true) if the medicine item is available and (false) otherwise (not found).
*/
    boolean isAvailable(String itemName);
}
