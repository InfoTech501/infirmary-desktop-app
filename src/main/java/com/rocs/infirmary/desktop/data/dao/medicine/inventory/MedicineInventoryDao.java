package com.rocs.infirmary.desktop.data.dao.medicine.inventory;
import com.rocs.infirmary.desktop.data.model.inventory.medicine.Medicine;

import java.util.List;
public interface MedicineInventoryDao {


    List<Medicine> getAllMedicine();

    /*
     * return list of Medicine with details such as medicine name, description, quantity, and expiration date.
     */
}
