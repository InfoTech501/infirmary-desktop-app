package com.rocs.infirmary.desktop.app.facade.medicine.inventory.impl;
import com.rocs.infirmary.desktop.data.dao.medicine.inventory.MedicineInventoryDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MedicineInventoryFacadeImplTest {

    @Mock
    private MedicineInventoryDao medicineInventoryDao;
    private MedicineInventoryFacadeImpl medicineinventoryfacade;

    @BeforeEach
    public void setUp() throws Exception {
        medicineinventoryfacade = new MedicineInventoryFacadeImpl();
        Field field = MedicineInventoryFacadeImpl.class.getDeclaredField("medicineInventoryDao");
        field.setAccessible(true);
        field.set(medicineinventoryfacade, medicineInventoryDao);
    }

    @Test
    public void testDeleteMedicineByItemName() {
        String itemName = "Biogesic";
        when(medicineInventoryDao.deleteMedicineByItemName(itemName)).thenReturn(true);
        boolean result = medicineinventoryfacade.deleteMedicineByItemName(itemName);
        assertTrue(result);
        verify(medicineInventoryDao, times(1)).deleteMedicineByItemName(itemName);
    }

    @Test
    public void testIsAvailable() {
        String itemName = "Biogesic";
        when(medicineInventoryDao.isAvailable(itemName)).thenReturn(true);
        boolean result = medicineinventoryfacade.IsAvailable(itemName);
        assertTrue(result);
        verify(medicineInventoryDao, times(1)).isAvailable(itemName);
    }
}


