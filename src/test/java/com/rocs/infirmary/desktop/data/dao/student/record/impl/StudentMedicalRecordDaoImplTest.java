package com.rocs.infirmary.desktop.data.dao.student.record.impl;

import com.rocs.infirmary.desktop.data.connection.ConnectionHelper;
import com.rocs.infirmary.desktop.data.dao.student.record.StudentMedicalRecordDao;
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
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentMedicalRecordDaoImplTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    private static MockedStatic<ConnectionHelper> connectionHelper;

    @BeforeEach
    void setUp() throws SQLException {
        connectionHelper = Mockito.mockStatic(ConnectionHelper.class);
        connectionHelper.when(ConnectionHelper::getConnection).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    }

    @AfterEach
    void tearDown() {
        connectionHelper.close();
    }

    @Test
    public void testUpdateStudentMedicalRecord() throws SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(1);
        String symptoms = "Headache";
        String temperatureReadings = "38°C";
        Date visitDate = java.sql.Date.valueOf("2001-04-01");
        String treatment = "Paracetamol";
        long LRN = 1234567890L;

        StudentMedicalRecordDao studentDao = new StudentMedicalRecordDaoImpl();

        boolean result = studentDao.updateStudentMedicalRecord(symptoms, temperatureReadings, visitDate, treatment, LRN);
        assertTrue(result);
        verify(connection, atLeastOnce()).prepareStatement(anyString());
        verify(preparedStatement, atLeastOnce()).setString(anyInt(), anyString());
        verify(preparedStatement, atLeastOnce()).setTimestamp(anyInt(), any());
        verify(preparedStatement, atLeastOnce()).setLong(anyInt(), eq(LRN));
        verify(preparedStatement, atLeastOnce()).executeUpdate();

        boolean noUpdateResult = studentDao.updateStudentMedicalRecord(null, null, null, null, LRN);
        assertFalse(noUpdateResult);

        boolean partialUpdateResult = studentDao.updateStudentMedicalRecord("Fever", null, null, null, LRN);
        assertTrue(partialUpdateResult);

        when(preparedStatement.executeUpdate()).thenThrow(new SQLException("Error"));
        boolean errorResult = studentDao.updateStudentMedicalRecord("Cough", null, null, null, LRN);
        assertFalse(errorResult);
    }
}
