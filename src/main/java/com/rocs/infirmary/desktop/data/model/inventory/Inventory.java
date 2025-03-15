package com.rocs.infirmary.desktop.data.model.inventory;

public class Inventory {
    private int inventoryId;
    private String description;
    private int quantityAvailable;
    private String medicineId;
    private String itemType;



    public int getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(int inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public String getMedicineId() {return medicineId; }

    public void setMedicineId(String medicineId) { this.medicineId = medicineId; }

    public String getItemType () { return itemType;}

    public void setItemType(String itemType) { this.itemType = itemType; }

}
