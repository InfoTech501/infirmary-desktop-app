package com.rocs.infirmary.desktop.app.facade.medicine.inventory.impl;

import com.rocs.infirmary.desktop.data.dao.medicine.inventory.MedicineInventoryDao;
import com.rocs.infirmary.desktop.app.facade.medicine.inventory.MedicineInventoryFacade;
import com.rocs.infirmary.desktop.data.dao.medicine.inventory.impl.MedicineInventoryDaoImpl;
import com.rocs.infirmary.desktop.data.model.inventory.Inventory;
import com.rocs.infirmary.desktop.data.model.inventory.medicine.Medicine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;


/**
 * The MedicineInventoryFacadeImpl class is an implementation of the MedicineInventoryFacade interface.
 */
public class MedicineInventoryFacadeImpl implements MedicineInventoryFacade {

    private MedicineInventoryDao medicineInventoryDao = new MedicineInventoryDaoImpl();
    private static final Logger logger = LoggerFactory.getLogger(MedicineInventoryFacadeImpl.class);

    @Override
    public List<Medicine> findAllMedicine() {
        logger.info("Entering findAllMedicine");
        List<Medicine> medicines = this.medicineInventoryDao.getAllMedicine();
        logger.info("Exiting findAllMedicine with {} medicines found.", medicines.size());
        return medicines;
    }
    @Override
    public boolean deleteMedicineByItemName(String itemName) {
        logger.info("Entering deleteMedicineByItemName with itemName: {}", itemName);
        boolean isDeleted = medicineInventoryDao.deleteMedicineByItemName(itemName);
        logger.warn("Exiting deleteMedicineByItemName : This action cannot be undone Medicine: {}: ", itemName);
        return isDeleted;
    }

    @Override
    public boolean IsAvailable(String itemName) {
        logger.debug("Entering IsAvailable with itemName: {}", itemName);
        boolean available = medicineInventoryDao.isAvailable(itemName);
        logger.debug("Exiting IsAvailable with result: {}", available);
        return available;
    }

    @Override
    public boolean addMedicine(Medicine medicine) {
        logger.info("Accessing Add Medicine ");
        return this.medicineInventoryDao.addMedicine(medicine);
    }

    @Override
    public boolean addInventory(String medicineId , String itemType, int quantity ) {
        logger.info("Accessing Add Inventory");
        return this.medicineInventoryDao.addInventory(medicineId,itemType,quantity);
     }

    @Override
    public List<Medicine> getMedicine(){
        logger.info("Accessing Facade Get Medicine ");
         List <Medicine> MedicineList = medicineInventoryDao.findMedicine();
        logger.info("Exiting Facade Get Medicine ");
        return MedicineList;

    }


}
