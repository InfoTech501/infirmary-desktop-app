package com.rocs.infirmary.desktop.data.model.inventory.medicine;

import com.rocs.infirmary.desktop.data.model.inventory.Inventory;

import java.sql.Timestamp;

public class Medicine extends Inventory{

    private String medicineId;

    private String itemName;

    private Timestamp expirationDate;





    public String getMedicineId(){ return medicineId; }

    public void setMedicineId(String medicineId) { this.medicineId = medicineId; }


    public String getItemName(){ return itemName; }
    public void setItemName (String itemName) { this.itemName = itemName; }


    public Timestamp getExpirationDate(){ return expirationDate; }
    public void setExpirationDate(Timestamp expirationDate) {
        this.expirationDate = expirationDate;
    }

}

