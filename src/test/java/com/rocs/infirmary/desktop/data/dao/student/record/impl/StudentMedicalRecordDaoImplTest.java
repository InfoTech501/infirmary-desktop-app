
package com.rocs.infirmary.desktop.data.dao.student.record.impl;

import com.rocs.infirmary.desktop.data.connection.ConnectionHelper;
import com.rocs.infirmary.desktop.data.model.person.student.Student;
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
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    private static MockedStatic<ConnectionHelper> connectionHelper;

    private StudentMedicalRecordDaoImpl studentMedicalRecordDao;

    private Connection connection;

    @BeforeEach
    void setUp() throws SQLException {
        connectionHelper = Mockito.mockStatic(ConnectionHelper.class);
        connection = mock(Connection.class);
        connectionHelper.when(ConnectionHelper::getConnection).thenReturn(connection);

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        studentMedicalRecordDao = new StudentMedicalRecordDaoImpl();
    }

    @AfterEach
    void tearDown() {
        connectionHelper.close();
    }

    @Test
    void testGetMedicalInformationByLRN() throws SQLException {
        long testLRN = 93152648294L;

        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("student_id")).thenReturn(1);
        when(resultSet.getLong("LRN")).thenReturn(testLRN);
        when(resultSet.getString("first_name")).thenReturn("John");
        when(resultSet.getString("middle_name")).thenReturn(null);
        when(resultSet.getString("last_name")).thenReturn("Doe");
        when(resultSet.getInt("age")).thenReturn(18);
        when(resultSet.getString("gender")).thenReturn("MALE");
        when(resultSet.getString("symptoms")).thenReturn("Severe headache");
        when(resultSet.getString("temperature_readings")).thenReturn("37.5°C");
        when(resultSet.getDate("visit_date")).thenReturn(Date.valueOf("2000-01-02"));
        when(resultSet.getString("treatment")).thenReturn("Prescribed pain reliever");

        Student result = studentMedicalRecordDao.getMedicalInformationByLRN(testLRN);

        verify(connection, times(1)).prepareStatement(anyString());
        verify(preparedStatement, times(1)).setLong(1, testLRN);
        verify(preparedStatement, times(1)).executeQuery();

        assertNotNull(result);
        assertEquals(1, result.getStudentId());
        assertEquals(testLRN, result.getLrn());
        assertEquals("John", result.getFirstName());
        assertNull(result.getMiddleName());
        assertEquals("Doe", result.getLastName());
        assertEquals(18, result.getAge());
        assertEquals("MALE", result.getGender());
        assertEquals("Severe headache", result.getSymptoms());
        assertEquals("37.5°C", result.getTemperatureReadings());
        assertEquals("Prescribed pain reliever", result.getTreatment());
    }
}


