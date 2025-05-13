package com.rocs.infirmary.desktop.app.facade.student.profile;

import com.rocs.infirmary.desktop.data.model.person.student.Student;

import java.util.List;

public interface StudentHealthProfileFacade {
    List<Student> getAllStudentHealthProfile();
    List<Student> getStudentHealthProfileByLRN(Long LRN);

}
