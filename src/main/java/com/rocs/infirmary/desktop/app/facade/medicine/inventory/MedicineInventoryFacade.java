package com.rocs.infirmary.desktop.app.facade.medicine.inventory;

import com.rocs.infirmary.desktop.data.model.inventory.medicine.Medicine;
import java.util.List;


    /**
    * The MedicineInventoryFacade interface defines methods for managing(getting all) the medicine inventory objects.
    */
public interface MedicineInventoryFacade {

     /**
     * Retrieves all available medical supplies and details.
     * This includes a list of inventory items (medicine) with details such as medicine name, stock quantity, and expiration date.
     */
    List<Medicine> findAllMedicine();
    /**
    * @return A list of Medicine objects.
    */

}
