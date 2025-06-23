package com.rocs.infirmary.desktop.data.dao.medicine.inventory.impl;

import com.rocs.infirmary.desktop.data.connection.ConnectionHelper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
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

    private static MockedStatic<ConnectionHelper> connectionHelper;

    @BeforeEach
    public void setUp() throws SQLException {
        connectionHelper = Mockito.mockStatic(ConnectionHelper.class);
        connectionHelper.when(ConnectionHelper::getConnection).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    }

    @AfterEach
    public void tearDown() {
        connectionHelper.close();
    }

    @Test
    public void testIsAvailable() throws SQLException {
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        MedicineInventoryDaoImpl med = new MedicineInventoryDaoImpl();
        boolean available = med.isAvailable("Aspirin");

        assertTrue(available);

        verify(connection, times(1)).prepareStatement(anyString());
        verify(preparedStatement, times(1)).setString(1, "Aspirin");
        verify(preparedStatement, times(1)).executeQuery();
    }

    @Test
    public void testDeleteMedicineByItemName() throws SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(1);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn( true);
        MedicineInventoryDaoImpl med = new MedicineInventoryDaoImpl();
        boolean deleted = med.deleteMedicineByItemName("Aspirin");

        assertTrue(deleted);

        verify(connection, atLeastOnce()).prepareStatement(anyString());
        verify(preparedStatement, atLeastOnce()).setString(1, "Aspirin");
        verify(preparedStatement, atLeastOnce()).executeUpdate();
        verify(preparedStatement, atLeastOnce()).executeQuery();
    }
}
