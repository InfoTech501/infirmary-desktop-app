package com.rocs.infirmary.desktop.data.dao.medicine.inventory.impl;

import com.rocs.infirmary.desktop.data.connection.ConnectionHelper;
import com.rocs.infirmary.desktop.data.dao.medicine.inventory.MedicineInventoryDao;
import com.rocs.infirmary.desktop.data.model.inventory.medicine.Medicine;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.*;
import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MedicineInventoryDaoImplTest {

   @Mock
   private Connection connection;


    @Mock
    private PreparedStatement preparedStatement;


    @Mock
    private ResultSet resultSet;



    @BeforeEach
    void setUp() throws SQLException {
        MockedStatic<ConnectionHelper> connectionHelper = Mockito.mockStatic(ConnectionHelper.class);
        connectionHelper.when(ConnectionHelper::getConnection).thenReturn(connection);

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        doNothing().when(preparedStatement).setString(anyInt(),anyString());
        doNothing().when(preparedStatement).setInt(anyInt(),anyInt());


        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(preparedStatement.executeUpdate()).thenReturn(1);

        when(resultSet.next()).thenReturn(Boolean.TRUE, Boolean.FALSE);


    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void getAllMedicine()throws SQLException{
        MedicineInventoryDao medicineInventoryDao = new MedicineInventoryDaoImpl();
        List<Medicine> list = medicineInventoryDao.getAllMedicine();

        verify(connection,times(1)).prepareStatement(anyString());
}
}