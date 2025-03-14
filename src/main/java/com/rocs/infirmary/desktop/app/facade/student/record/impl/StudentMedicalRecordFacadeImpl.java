package com.rocs.infirmary.desktop.app.facade.student.record.impl;


import com.rocs.infirmary.desktop.app.facade.student.record.StudentMedicalRecordFacade;
import com.rocs.infirmary.desktop.data.model.person.student.Student;
import com.rocs.infirmary.desktop.data.dao.student.record.StudentMedicalRecordDao;
import com.rocs.infirmary.desktop.data.dao.student.record.impl.StudentMedicalRecordDaoImpl;

import java.util.List;

public class StudentMedicalRecordFacadeImpl implements StudentMedicalRecordFacade {
    private final StudentMedicalRecordDao studentMedRecord = new StudentMedicalRecordDaoImpl();

    public Student findMedicalInformationByLRN(long LRN) {
        return this.studentMedRecord.getMedicalInformationByLRN(LRN);
    }

    @Override
    public List<Student> readAllStudentMedicalRecords() {
        List<Student> medicalRecords = this.studentMedRecord.getAllStudentMedicalRecords();

        return medicalRecords;
    }
}




