package com.rocs.infirmary.desktop.data.dao.student.record;

import com.rocs.infirmary.desktop.data.model.person.student.StudentMedicalRecords;

public interface CreateMedicalRecordsDao {
    boolean createMedicalRecord(StudentMedicalRecords studentMedicalRecords);


}