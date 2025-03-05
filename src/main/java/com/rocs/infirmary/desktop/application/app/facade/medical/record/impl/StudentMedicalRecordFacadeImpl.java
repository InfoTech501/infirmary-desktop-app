package com.rocs.infirmary.desktop.application.app.facade.medical.record.impl;


import com.rocs.infirmary.desktop.application.app.facade.medical.record.StudentMedicalRecordFacade;
import com.rocs.infirmary.desktop.application.model.medical.record.StudentMedicalRecord;
import com.rocs.infirmary.desktop.application.data.dao.medical.record.StudentMedicalRecordDao;
import com.rocs.infirmary.desktop.application.data.dao.medical.record.impl.StudentMedicalRecordDaoImpl;

public class StudentMedicalRecordFacadeImpl implements StudentMedicalRecordFacade {
    private final StudentMedicalRecordDao studentMedRecord = new StudentMedicalRecordDaoImpl();
    public StudentMedicalRecord findMedicalInformationByLRN(long LRN) {
        return this.studentMedRecord.getMedicalInformationByLRN(LRN);
    }
}




