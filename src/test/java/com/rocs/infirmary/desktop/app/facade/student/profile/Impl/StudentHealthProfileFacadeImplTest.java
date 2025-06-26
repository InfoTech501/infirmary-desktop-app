package com.rocs.infirmary.desktop.app.facade.student.profile.Impl;

import com.rocs.infirmary.desktop.data.dao.student.profile.StudentHealthProfileDao;
import com.rocs.infirmary.desktop.data.dao.student.record.StudentMedicalRecordDao;
import com.rocs.infirmary.desktop.data.model.person.student.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentHealthProfileFacadeImplTest {

    @Mock
    private StudentHealthProfileFacadeImpl studentHealthProfileFacade;

    @Mock
    private StudentHealthProfileDao studentHealthProfileDao;

    private List<Student> studentList;


    @BeforeEach
    public void setUp() {
        studentList = new ArrayList<>();
        Student student = new Student();
        student.setLrn(1234567890L);
        student.setFirstName("John");
        student.setMiddleName("");
        student.setLastName("Doe");
        student.setSection("gumamela");
        student.setGradeLevel("Grade 11");
        student.setStudentAdviser("Ms. Smith");

        student.setAddress("Silang, Cavite");
        student.setEmail("johndoe@gmail.com");
        student.setContactNumber(912345678);
        student.setSymptoms("Headache");
        student.setTemperatureReadings("37.5°C");
        student.setTreatment("Prescribed pain reliever");
        student.setVisitDate(new java.util.Date());
        student.setNurseInCharge("Nurse Reyes");

        studentList.add(student);

    }

    @Test
    public void testGetAllStudentHealthProfile() {
        when(studentHealthProfileFacade.getAllStudentHealthProfile()).thenReturn(studentList);

        List<Student> result = studentHealthProfileFacade.getAllStudentHealthProfile();

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

        verify(studentHealthProfileFacade, times(1)).getAllStudentHealthProfile();
    }


    @Test
    public void testGetStudentHealthProfileByLRN() {
        when(studentHealthProfileFacade.getStudentHealthProfileByLRN(anyLong())).thenReturn(studentList);

        List<Student> result = studentHealthProfileFacade.getStudentHealthProfileByLRN(1234567890L);

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
        assertEquals(912345678, student.getContactNumber());
        assertEquals("johndoe@gmail.com", student.getEmail());
        assertEquals("Silang, Cavite", student.getAddress());
        assertEquals("Headache", student.getSymptoms());
        assertEquals("37.5°C", student.getTemperatureReadings());
        assertEquals("Prescribed pain reliever", student.getTreatment());
        assertNotNull(student.getVisitDate());
        assertEquals("Nurse Reyes", student.getNurseInCharge());

        verify(studentHealthProfileFacade, times(1)).getStudentHealthProfileByLRN(anyLong());
    }

}
