package com.rocs.medical.records.application.model.medicineInventory;

import java.sql.Timestamp;

public class Inventory {

    private int inventoryId;

    private String medicineId;

    private String itemType;

    private int quantity;

    private Timestamp expirationDate;

    private String description;

    private String itemName;



    public Inventory() {
    }


    public Inventory(int InventoryId, String MedicineId, String ItemType, String Description,int Quantity,Timestamp ExpirationDate, String itemName) {
        this.inventoryId = InventoryId;
        this.medicineId = MedicineId;
        this.itemType = ItemType;
        this.description = Description;
        this.quantity = Quantity;
        this.expirationDate = ExpirationDate;
        this.itemName = itemName;


    }


    public int getInventoryId() {
        return inventoryId;
    }
    public void setInventoryId(int InventoryId) {
        this.inventoryId = InventoryId;
    }

    public String getMedicineId() {
        return medicineId;
    }
    public void setMedicineId(String MedicineId) {
        this.medicineId = MedicineId;
    }


    public String getItemType() {
        return itemType;
    }
    public void setItemType(String ItemType) {
        this.itemType = ItemType;
    }




    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int Quantity) {
        this.quantity = Quantity;
    }



    public Timestamp getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Timestamp ExpirationDate) {
        this.expirationDate = ExpirationDate;
    }


    public String getDescription() {
        return description;
    }
    public void setDescription(String Description) {
        this.description = Description;
    }


    public String getItemName(){
        return itemName;
    }

    public void setItemName(String ItemName){
        this.itemName = ItemName;
    }
}

