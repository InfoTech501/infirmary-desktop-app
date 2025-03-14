package com.rocs.infirmary.desktop.app.facade.student.record.impl;


import com.rocs.infirmary.desktop.app.facade.student.record.StudentMedicalRecordFacade;
import com.rocs.infirmary.desktop.data.model.person.student.Student;
import com.rocs.infirmary.desktop.data.dao.student.record.StudentMedicalRecordDao;
import com.rocs.infirmary.desktop.data.dao.student.record.impl.StudentMedicalRecordDaoImpl;

public class StudentMedicalRecordFacadeImpl implements StudentMedicalRecordFacade {
    private final StudentMedicalRecordDao studentMedRecord = new StudentMedicalRecordDaoImpl();
    public Student findMedicalInformationByLRN(long LRN) {
        return this.studentMedRecord.getMedicalInformationByLRN(LRN);
    }

    public static class CreateMedicalRecordsFacadeImpl implements CreateMedicalRecordsFacade {

        private final StudentMedicalRecordDao.CreateMedicalRecordsDao medicalRecordsDao = new StudentMedicalRecordDaoImpl.CreateMedicalRecordsDaoImpl();

        @Override
        public boolean AddStudentMedicalRecord(Student.StudentMedicalRecords record) {
            return medicalRecordsDao.createMedicalRecord(record);
        }

    }
}




