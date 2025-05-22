
package com.rocs.infirmary.desktop.data.dao.student.record.impl;

import com.rocs.infirmary.desktop.data.connection.ConnectionHelper;
import java.util.Date;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentMedicalRecordDaoImplTest {


    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

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
    public void testUpdateStudentMedicalRecord() throws SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(1);

        StudentMedicalRecordDaoImpl studentMedicalRecordDao = new StudentMedicalRecordDaoImpl();

        String symptoms = "Cough";
        String temperatureReadings = "38.0";
        Date visitDate = new Date();
        String treatment = "Rest and medicine";
        long lrn = 1234567890L;

        boolean result = studentMedicalRecordDao.updateStudentMedicalRecord(
                symptoms, temperatureReadings, visitDate, treatment, lrn
        );

        assertTrue(result);

        verify(connection, atLeastOnce()).prepareStatement(anyString());
        verify(preparedStatement, atLeastOnce()).setString(anyInt(), anyString());
        verify(preparedStatement, atLeastOnce()).setLong(eq(2), eq(lrn));
        verify(preparedStatement, atLeastOnce()).executeUpdate();
        verify(preparedStatement, atLeast(1)).close();
    }
}


