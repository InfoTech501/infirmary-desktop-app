package com.rocs.infirmary.desktop.app.facade.medicine.inventory;

import com.rocs.infirmary.desktop.data.model.inventory.medicine.Medicine;
import java.util.List;


/**
 * The MedicineInventoryFacade interface defines methods for managing(getting all) the medicine inventory objects.
 * Retrieves all available medical supplies and details.
 * This includes a list of inventory items (medicine) with details such as medicine name, stock quantity, and expiration date.
 *
 * Also includes method declaration for deleting a medicine by ItemName.
 */
public interface MedicineInventoryFacade {


    List<Medicine> findAllMedicine();


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

    boolean IsAvailable(String itemName);


    boolean addMedicine(Medicine newMedicine);
}
