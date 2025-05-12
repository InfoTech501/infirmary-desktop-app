package com.rocs.infirmary.desktop.data.dao.student.profile;

import com.rocs.infirmary.desktop.data.model.person.student.Student;

import java.util.List;

public interface StudentHealthProfileDao {
    List<Student> findAllStudentHealthProfile();
}
