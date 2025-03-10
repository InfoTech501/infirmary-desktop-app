package com.rocs.infirmary.desktop.app.facade.student.record.impl;

import com.rocs.infirmary.desktop.app.facade.student.record.CreateMedicalRecordsFacade;
import com.rocs.infirmary.desktop.data.dao.student.record.impl.CreateMedicalRecordsDaoImpl;
import com.rocs.infirmary.desktop.data.dao.student.record.CreateMedicalRecordsDao;
import com.rocs.infirmary.desktop.data.model.person.student.StudentMedicalRecords;

public class CreateMedicalRecordsFacadeImpl implements CreateMedicalRecordsFacade {

    private final CreateMedicalRecordsDao medicalRecordsDao = new CreateMedicalRecordsDaoImpl();

    @Override
    public boolean AddStudentMedicalRecord(StudentMedicalRecords record) {
        return medicalRecordsDao.createMedicalRecord(record);
    }
}