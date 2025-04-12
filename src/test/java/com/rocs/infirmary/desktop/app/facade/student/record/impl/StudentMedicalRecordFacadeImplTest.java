package com.rocs.infirmary.desktop.app.facade.student.record.impl;

import com.rocs.infirmary.desktop.data.dao.student.record.StudentMedicalRecordDao;
import com.rocs.infirmary.desktop.data.model.person.student.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentMedicalRecordFacadeImplTest {

    @Mock
    private StudentMedicalRecordFacadeImpl facade;

    @Mock
    private StudentMedicalRecordDao mockDao;

    private Student mockStudent;

    @BeforeEach
    void setUp() {
        mockStudent = new Student();
        mockStudent.setLrn(93152648294L);
        mockStudent.setFirstName("John");
        mockStudent.setLastName("Doe");
        when(facade.findMedicalInformationByLRN(anyLong())).thenReturn(mockStudent);
    }

    @Test
    void testFindMedicalInformationByLRN() {
        long testLRN = 93152648294L;
        Student result = facade.findMedicalInformationByLRN(testLRN);

        assertNotNull(result);
        assertEquals(93152648294L, result.getLrn());
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        verify(facade, times(1)).findMedicalInformationByLRN(anyLong());
    }
}

