package com.rocs.infirmary.desktop.data.dao.student.record;


import com.rocs.infirmary.desktop.data.model.person.student.Student;

public interface StudentMedicalRecordDao {
    Student getMedicalInformationByLRN(long LRN);

}
