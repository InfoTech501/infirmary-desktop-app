package com.rocs.infirmary.desktop.data.model.inventory;

public class MedicineInventory {
    private int inventory_id;

    private String medicine_id;

    private String item_type;

    private String description;

    private int quantity_available;

    public MedicineInventory() {
    }

    public MedicineInventory(int inventory_id, String medicine_id, String item_type,
                             String description, int quantity_available) {
        this.inventory_id = inventory_id;
        this.medicine_id = medicine_id;
        this.item_type = item_type;
        this.description = description;
        this.quantity_available = quantity_available;

    }

    public int getInventory_id() {
        return inventory_id;
    }

    public void setInventory_id(int inventory_id) {
        this.inventory_id = inventory_id;
    }

    public String getMedicine_id() {
        return medicine_id;
    }

    public void setMedicine_id(String medicine_id) {
        this.medicine_id = medicine_id;
    }

    public String getItem_type() {
        return item_type;
    }

    public void setItem_type(String item_type) {
        this.item_type = item_type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getQuantity_available() {
        return quantity_available;
    }

    public void setQuantity_available(int quantity_available) {
        this.quantity_available = quantity_available;

    }

    @Override
    public String toString(){
        return "Medicine Inventory{" +
                ", inventory_id='" + inventory_id + '\'' +
                ", medicine_id=" + medicine_id +
                ", item_type=" + item_type +
                ", description=" + description +
                ", quantity_available=" + quantity_available +
                '}';
    }
}
