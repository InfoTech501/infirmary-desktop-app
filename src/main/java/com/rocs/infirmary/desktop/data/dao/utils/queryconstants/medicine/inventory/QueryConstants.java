package com.rocs.infirmary.desktop.data.dao.utils.queryconstants.medicine.inventory;

public class QueryConstants {

    private final  String LIST_ALL_MEDICINE_INVENTORY_QUERY  = "SELECT i.INVENTORY_ID, i.MEDICINE_ID, i.ITEM_TYPE, i.QUANTITY, m.ITEM_NAME, m.DESCRIPTION, m.EXPIRATION_DATE " +
            "FROM INVENTORY i " +
            "JOIN MEDICINE m ON i.MEDICINE_ID = m.MEDICINE_ID";

    private final String ADD_MEDICINE_INVENTORY_QUERY = "SELECT i.MEDICINE_ID, m.ITEM_NAME, m.DESCRIPTION, m.EXPIRATION_DATE"+
            "FROM MEDICINE i";
    public String getLIST_ALL_MEDICINE_INVENTORY_QUERY(){return  LIST_ALL_MEDICINE_INVENTORY_QUERY;}

    public String getADD_MEDICINE_INVENTORY_QUERY(){return ADD_MEDICINE_INVENTORY_QUERY ;}

}
