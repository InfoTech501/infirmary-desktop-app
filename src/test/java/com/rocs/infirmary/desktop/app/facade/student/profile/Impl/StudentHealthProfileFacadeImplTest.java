package com.rocs.infirmary.desktop.app.facade.student.profile.Impl;

import com.rocs.infirmary.desktop.data.dao.student.profile.StudentHealthProfileDao;
import com.rocs.infirmary.desktop.data.model.person.student.Student;
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
        student.setMiddleName("A.");
        student.setLastName("Doe");
        student.setSection("Section A");
        student.setGradeLevel("Grade 10");
        student.setStudentAdviser("Ms. Smith");
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
        assertEquals("A.", student.getMiddleName());
        assertEquals("Doe", student.getLastName());
        assertEquals("Section A", student.getSection());
        assertEquals("Grade 10", student.getGradeLevel());
        assertEquals("Ms. Smith", student.getStudentAdviser());

        verify(studentHealthProfileFacade, times(1)).getAllStudentHealthProfile();
    }



}
