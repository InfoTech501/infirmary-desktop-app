package com.rocs.infirmary.desktop.application.data.dao.medical.record;


import com.rocs.infirmary.desktop.application.model.medical.record.StudentMedicalRecord;

public interface StudentMedicalRecordDao {
    StudentMedicalRecord getMedicalInformationByLRN(long LRN);

}
