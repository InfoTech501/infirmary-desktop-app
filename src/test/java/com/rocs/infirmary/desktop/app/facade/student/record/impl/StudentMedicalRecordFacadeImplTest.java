package com.rocs.infirmary.desktop.app.facade.student.record.impl;

import com.rocs.infirmary.desktop.data.dao.student.record.StudentMedicalRecordDao;
import com.rocs.infirmary.desktop.data.model.person.student.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentMedicalRecordFacadeImplTest {

    @InjectMocks
    private StudentMedicalRecordFacadeImpl facade;

    @Mock
    private StudentMedicalRecordDao studentMedicalRecordDao;

    private List<Student> studentList;

    @BeforeEach
    public void setUp() throws Exception {
        facade = new StudentMedicalRecordFacadeImpl();
        Field daoField = StudentMedicalRecordFacadeImpl.class.getDeclaredField("studentMedRecord");
        daoField.setAccessible(true);
        daoField.set(facade, studentMedicalRecordDao);

        studentList = new ArrayList<>();
        Student student = new Student();
        student.setStudentId(1);
        student.setLrn(123456789L);
        student.setSymptoms("Headache");
        student.setTemperatureReadings("38.5°C");
        student.setVisitDate(new Date());
        student.setTreatment("Paracetamol");
        studentList.add(student);
    }

    @Test
    public void testUpdateStudentMedicalRecord() {
        when(studentMedicalRecordDao.updateStudentMedicalRecord(
                anyString(), anyString(), any(Date.class), anyString(), anyLong())).thenReturn(true);

        Student testStudent = studentList.get(0);

        boolean updated = facade.updateStudentMedicalRecord(
                testStudent.getSymptoms(),
                testStudent.getTemperatureReadings(),
                testStudent.getVisitDate(),
                testStudent.getTreatment(),
                testStudent.getLrn());

        assertTrue(updated);

        verify(studentMedicalRecordDao, times(1)).updateStudentMedicalRecord(
                eq(testStudent.getSymptoms()),
                eq(testStudent.getTemperatureReadings()),
                eq(testStudent.getVisitDate()),
                eq(testStudent.getTreatment()),
                eq(testStudent.getLrn()));
    }
}

