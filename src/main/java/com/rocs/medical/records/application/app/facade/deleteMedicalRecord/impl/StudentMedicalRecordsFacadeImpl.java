package com.rocs.medical.records.application.app.facade.deleteMedicalRecord.impl;

import com.rocs.medical.records.application.app.facade.deleteMedicalRecord.StudentMedicalRecordsFacade;
import com.rocs.medical.records.application.data.dao.deleteMedicalRecord.impl.StudentMedicalRecordsDaoImpl;
import com.rocs.medical.records.application.data.dao.deleteMedicalRecord.StudentMedicalRecordsDao;

public class StudentMedicalRecordsFacadeImpl implements StudentMedicalRecordsFacade {

    private final StudentMedicalRecordsDao studentMedicalRecordsDao = new StudentMedicalRecordsDaoImpl();


    @Override
    public boolean deleteStudentMedicalRecordById(String Id) {
        return this.studentMedicalRecordsDao.deleteStudentMedicalRecordById(Id);

    }

}
