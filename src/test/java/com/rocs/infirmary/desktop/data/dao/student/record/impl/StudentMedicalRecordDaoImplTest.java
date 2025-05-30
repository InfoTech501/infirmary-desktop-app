package com.rocs.infirmary.desktop.data.dao.student.record.impl;

import com.rocs.infirmary.desktop.data.connection.ConnectionHelper;
import com.rocs.infirmary.desktop.data.dao.student.record.StudentMedicalRecordDao;
import com.rocs.infirmary.desktop.data.model.person.student.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentMedicalRecordDaoImplTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    private static MockedStatic<ConnectionHelper> connectionHelper;
    private StudentMedicalRecordDao dao;

    @BeforeEach
    public void setUp() throws SQLException {
        connectionHelper = Mockito.mockStatic(ConnectionHelper.class);
        connectionHelper.when(ConnectionHelper::getConnection).thenReturn(connection);

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);

        dao = new StudentMedicalRecordDaoImpl();
    }

    @AfterEach
    public void tearDown() {
        connectionHelper.close();
    }

    @Test
    public void testGetMedicalInformationByLRN() throws SQLException {
        long testLRN = 93152648294L;

        when(preparedStatement.executeQuery()).thenReturn(resultSet);
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

        Student result = dao.getMedicalInformationByLRN(testLRN);

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

        verify(connection, times(1)).prepareStatement(anyString());
        verify(preparedStatement, times(1)).setLong(1, testLRN);
        verify(preparedStatement, times(1)).executeQuery();
    }



    @Test
    public void testUpdateStudentMedicalRecord() throws SQLException {
        when(preparedStatement.executeUpdate()).thenReturn(1);
        String symptoms = "Headache";
        String temperatureReadings = "38°C";
        Date visitDate = new Date(2001-04-01);
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

        when(preparedStatement.executeUpdate()).thenThrow(new SQLException("Simulated error"));
        boolean errorResult = studentDao.updateStudentMedicalRecord("Cough", null, null, null, LRN);
        assertFalse(errorResult);

        }
    }




