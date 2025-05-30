package com.rocs.infirmary.desktop.data.dao.student.profile.Impl;

import com.rocs.infirmary.desktop.data.connection.ConnectionHelper;
import com.rocs.infirmary.desktop.data.dao.student.profile.StudentHealthProfileDao;
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
        when(resultSet.getString("middle_name")).thenReturn("A.");
        when(resultSet.getString("last_name")).thenReturn("Doe");
        when(resultSet.getString("section")).thenReturn("Section A");
        when(resultSet.getString("grade_level")).thenReturn("Grade 10");
        when(resultSet.getString("adviser_first_name")).thenReturn("Ms. Smith");

        StudentHealthProfileDao dao = new StudentHealthProfileDaoImpl();
        List<Student> result = dao.findAllStudentHealthProfile();

        assertNotNull(result);
        assertEquals(1, result.size());

        Student student = result.get(0);
        assertEquals(1234567890L, student.getLrn());
        assertEquals("John", student.getFirstName());
        assertEquals("A.", student.getMiddleName());
        assertEquals("Doe", student.getLastName());
        assertEquals("Section A", student.getSection());
        assertEquals("Grade 10", student.getGradeLevel());
        assertEquals("Ms. Smith", student.getStudentAdviser());

        verify(connection, times(1)).prepareStatement(anyString());
        verify(preparedStatement, times(1)).executeQuery();
    }
}
