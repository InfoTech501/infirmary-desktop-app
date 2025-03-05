package com.rocs.infirmary.desktop.application.app.facade.medical.record;


import com.rocs.infirmary.desktop.application.model.medical.record.StudentMedicalRecord;

public interface StudentMedicalRecordFacade {

    /**
     * Retrieves a student's important details and medical record.
     *
     * @param LRN The LRN (Learner Reference Number) of the student.
     */
    StudentMedicalRecord findMedicalInformationByLRN(long LRN);
}
