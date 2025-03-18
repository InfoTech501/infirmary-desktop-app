package com.rocs.infirmary.desktop.data.dao.student.record;


import com.rocs.infirmary.desktop.data.model.person.student.Student;

import java.util.List;

public interface StudentMedicalRecordDao {

    Student getMedicalInformationByLRN(long LRN);

    List<Student> getAllStudentMedicalRecords();
}
