package com.rocs.infirmary.desktop.app.facade.student.record;

import com.rocs.infirmary.desktop.data.model.person.student.StudentMedicalRecords;

public interface CreateMedicalRecordsFacade {
    boolean AddStudentMedicalRecord(StudentMedicalRecords record);
}