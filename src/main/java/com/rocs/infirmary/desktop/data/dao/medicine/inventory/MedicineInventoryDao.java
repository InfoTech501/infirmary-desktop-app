package com.rocs.infirmary.desktop.data.dao.medicine.inventory;

import com.rocs.infirmary.desktop.data.model.inventory.medicine.Medicine;
import java.util.List;



public interface MedicineInventoryDao {

/*
* return list of Medicine and inventory objects with details such as medicine name, description, quantity, and expiration date.
*/
    List<Medicine> getAllMedicine();

    boolean addMedicine(Medicine medicine);

}
