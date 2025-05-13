package com.rocs.infirmary.desktop.app.facade.medicine.inventory.impl;

import com.rocs.infirmary.desktop.data.dao.medicine.inventory.MedicineInventoryDao;
import com.rocs.infirmary.desktop.app.facade.medicine.inventory.MedicineInventoryFacade;
import com.rocs.infirmary.desktop.data.dao.medicine.inventory.impl.MedicineInventoryDaoImpl;
import com.rocs.infirmary.desktop.data.model.inventory.medicine.Medicine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;


/**
 * The MedicineInventoryFacadeImpl class is an implementation of the MedicineInventoryFacade interface.
 */
public class MedicineInventoryFacadeImpl implements MedicineInventoryFacade {

    private MedicineInventoryDao medicineInventoryDao = new MedicineInventoryDaoImpl();
    private static final Logger LOGGER = LoggerFactory.getLogger(MedicineInventoryFacadeImpl.class);

    @Override
    public List<Medicine> findAllMedicine() {
        LOGGER.info("Entering findAllMedicine");
        List<Medicine> medicines = this.medicineInventoryDao.getAllMedicine();
        LOGGER.info("Exiting findAllMedicine with {} medicines found.", medicines.size());
        return medicines;
    }
    @Override
    public boolean deleteMedicineByItemName(String itemName) {
        LOGGER.warn("Entering deleteMedicineByItemName with itemName: {}", itemName);
        boolean isDeleted = medicineInventoryDao.deleteMedicineByItemName(itemName);
        LOGGER.warn("Exiting deleteMedicineByItemName with result: {}", isDeleted);
        return isDeleted;
    }

    @Override
    public boolean IsAvailable(String itemName) {
        LOGGER.debug("Entering IsAvailable with itemName: {}", itemName);
        boolean available = medicineInventoryDao.isAvailable(itemName);
        LOGGER.debug("Exiting IsAvailable with result: {}", available);
        return available;
    }

    @Override
    public boolean addMedicine(Medicine medicine) {
        LOGGER.info("Accessing Add Medicine ");
        boolean addedMedicine = medicineInventoryDao.addMedicine(medicine);
        LOGGER.info("Exiting Add Medicine with Result :  {} ", addedMedicine );
        return addedMedicine;

    }

}
