package com.rocs.infirmary.desktop.data.dao.student.profile.Impl;

import com.rocs.infirmary.desktop.data.connection.ConnectionHelper;
import com.rocs.infirmary.desktop.data.dao.student.profile.StudentHealthProfileDao;
import com.rocs.infirmary.desktop.data.dao.utils.queryconstants.medicine.inventory.QueryConstants;
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
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentHealthProfileDaoImplTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    private MockedStatic<ConnectionHelper> connectionHelper;

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
    public void testFindAllStudentHealthProfile() throws SQLException {
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true, false);
        when(resultSet.getLong("LRN")).thenReturn(1234567890L);
        when(resultSet.getString("first_name")).thenReturn("John");
        when(resultSet.getString("middle_name")).thenReturn("");
        when(resultSet.getString("last_name")).thenReturn("Doe");
        when(resultSet.getString("section")).thenReturn("gumamela");
        when(resultSet.getString("grade_level")).thenReturn("Grade 11");
        when(resultSet.getString("adviser_first_name")).thenReturn("Ms. Smith");

        StudentHealthProfileDao dao = new StudentHealthProfileDaoImpl();
        List<Student> result = dao.findAllStudentHealthProfile();

        assertNotNull(result);
        assertEquals(1, result.size());

        Student student = result.get(0);
        assertEquals(1234567890L, student.getLrn());
        assertEquals("John", student.getFirstName());
        assertEquals("", student.getMiddleName());
        assertEquals("Doe", student.getLastName());
        assertEquals("gumamela", student.getSection());
        assertEquals("Grade 11", student.getGradeLevel());
        assertEquals("Ms. Smith", student.getStudentAdviser());

        verify(connection, times(1)).prepareStatement(anyString());
        verify(preparedStatement, times(1)).executeQuery();
    }

    @Test
    public void testFindStudentHealthProfileByLrn() throws SQLException {
        long lrn = 1234567890L;

        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
        when(preparedStatement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true).thenReturn(false);

        when(resultSet.getInt("contact_number")).thenReturn(912345678);
        when(resultSet.getString("email")).thenReturn("johndoe@gmail.com");
        when(resultSet.getString("address")).thenReturn("Silang, Cavite");
        when(resultSet.getString("first_name")).thenReturn("John");
        when(resultSet.getString("middle_name")).thenReturn("");
        when(resultSet.getString("last_name")).thenReturn("Doe");
        when(resultSet.getString("symptoms")).thenReturn("Headache");
        when(resultSet.getString("temperature_readings")).thenReturn("37.8°C");
        when(resultSet.getString("treatment")).thenReturn("Paracetamol and rest");
        when(resultSet.getTimestamp("visit_date")).thenReturn(Timestamp.valueOf("2000-01-01 09:00:00"));
        when(resultSet.getString("nurse_in_charge")).thenReturn("Nurse Reyes");

        StudentHealthProfileDao dao = new StudentHealthProfileDaoImpl();
        List<Student> result = dao.findStudentHealthProfileByLrn(lrn);

        assertNotNull(result);
        assertEquals(1, result.size());

        Student student = result.get(0);
        assertEquals("John", student.getFirstName());
        assertEquals("", student.getMiddleName());
        assertEquals("Doe", student.getLastName());
        assertEquals(912345678, student.getContactNumber());
        assertEquals("Silang, Cavite", student.getAddress());
        assertEquals("johndoe@gmail.com", student.getEmail());
        assertEquals("Headache", student.getSymptoms());
        assertEquals("37.8°C", student.getTemperatureReadings());
        assertEquals("Paracetamol and rest", student.getTreatment());
        assertEquals("Nurse Reyes", student.getNurseInCharge());


        verify(connection, times(1)).prepareStatement(anyString());
        verify(preparedStatement, times(1)).setLong(1, lrn);
        verify(preparedStatement, times(1)).executeQuery();
    }

}

