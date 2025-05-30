package com.rocs.infirmary.desktop.app.facade.student.record.impl;

import com.rocs.infirmary.desktop.data.dao.student.record.StudentMedicalRecordDao;
import com.rocs.infirmary.desktop.data.model.person.student.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentMedicalRecordFacadeImplTest {

    @Mock
    private StudentMedicalRecordFacadeImpl facade;

    @Mock
    private StudentMedicalRecordDao studentMedicalRecordDao;

    private Student testStudent;
    private List<Student> studentList;

    @BeforeEach
    public void setUp() {
        testStudent = new Student();
        testStudent.setLrn(93152648294L);
        testStudent.setFirstName("John");
        testStudent.setLastName("Doe");

        studentList = new ArrayList<>();
        studentList.add(testStudent);
    }

    @Test
    public void testFindMedicalInformationByLRN() {
        when(facade.findMedicalInformationByLRN(anyLong())).thenReturn(testStudent);
        Student result = facade.findMedicalInformationByLRN(93152648294L);

        assertNotNull(result);
        assertEquals(93152648294L, result.getLrn());
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());

        verify(facade, times(1)).findMedicalInformationByLRN(anyLong());
    }


    @Test
    public void testUpdateStudentMedicalRecord() {
        when(facade.updateStudentMedicalRecord(anyString(), anyString(), any(Date.class), anyString(), anyLong())).thenReturn(true);
        boolean result = facade.updateStudentMedicalRecord("Cough", "37.5", new Date(), "Cough syrup", 93152648294L);

        assertTrue(result);
        verify(facade, times(1)).updateStudentMedicalRecord(anyString(), anyString(), any(Date.class), anyString(), anyLong());
    }
}
