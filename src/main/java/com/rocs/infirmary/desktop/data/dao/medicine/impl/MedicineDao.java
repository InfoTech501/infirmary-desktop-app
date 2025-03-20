package com.rocs.infirmary.desktop.data.dao.medicine.impl;

public interface MedicineDao {
    /**
     * Deletes a medicine record from the inventory by name.
     *
     * @param medicineName The name of the medicine to delete.
     * @return true if the deletion was successful, false otherwise.
     */
    boolean deleteMedicineByName(String medicineName);
}
