package com.rocs.infirmary.desktop.app.facade.medicine.inventory.impl;

import com.rocs.infirmary.desktop.data.dao.medicine.inventory.MedicineInventoryDao;
import com.rocs.infirmary.desktop.app.facade.medicine.inventory.MedicineInventoryFacade;
import com.rocs.infirmary.desktop.data.dao.medicine.inventory.impl.MedicineInventoryDaoImpl;
import com.rocs.infirmary.desktop.data.model.inventory.medicine.Medicine;
import java.util.List;


/**
 * The MedicineInventoryFacadeImpl class is an implementation of the MedicineInventoryFacade interface.
 */
public class MedicineInventoryFacadeImpl implements MedicineInventoryFacade {

    private MedicineInventoryDao medicineInventoryDao = new MedicineInventoryDaoImpl();

    @Override
    public List<Medicine> findAllMedicine() {
        return this.medicineInventoryDao.getAllMedicine();
    }
    @Override
    public boolean deleteMedicineByItemName(String itemName) {
        return medicineInventoryDao.deleteMedicineByItemName(itemName);
    }

    @Override
    public boolean IsAvailable(String itemName) {
        return medicineInventoryDao.isAvailable(itemName);
    }

    @Override
    public boolean addMedicine(Medicine medicine) {
        return medicineInventoryDao.addMedicine(medicine);
    }

}
