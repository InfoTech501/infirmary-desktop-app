package com.rocs.infirmary.desktop.app.facade.student.record.impl;


import com.rocs.infirmary.desktop.app.facade.student.record.StudentMedicalRecordFacade;
import com.rocs.infirmary.desktop.data.model.person.student.Student;
import com.rocs.infirmary.desktop.data.dao.student.record.StudentMedicalRecordDao;
import com.rocs.infirmary.desktop.data.dao.student.record.impl.StudentMedicalRecordDaoImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;


/**
 * The StudentMedicalRecordFacadeImpl class is an implementation of the StudentMedicalRecordFacade interface.
 * It provides methods for managing students medical record.
 */

public class StudentMedicalRecordFacadeImpl implements StudentMedicalRecordFacade {

    /** The data access object for Student Medical Record. */
    private final StudentMedicalRecordDao studentMedRecord = new StudentMedicalRecordDaoImpl();
    private static final Logger logger = LoggerFactory.getLogger(StudentMedicalRecordFacadeImpl.class);

    public Student findMedicalInformationByLRN(long LRN) {
        logger.debug("Entering findMedicalInformationByLRN with LRN: {}", LRN);
      Student student = this.studentMedRecord.getMedicalInformationByLRN(LRN);
        logger.debug("Exiting findMedicalInformationByLRN with result: {}", student);
        return student;
    }

    @Override
    public List<Student> readAllStudentMedicalRecords() {
        logger.info("Entering readAllStudentMedicalRecords");
        List<Student> medicalRecords = this.studentMedRecord.getAllStudentMedicalRecords();
        logger.info("Exiting readAllStudentMedicalRecords with {} records found.", medicalRecords.size());
        return medicalRecords;
    }

    /**
     * This is used to delete a student's medical record based on their Learner Reference Number (LRN).
     *
     * boolean returns true if the deletion was successful, otherwise false.
     */
   @Override
   public boolean deleteStudentMedicalRecordByLrn(long LRN) {
       logger.warn("Entering deleteStudentMedicalRecordByLrn with LRN: {}", LRN);
       boolean isDeleted = this.studentMedRecord.deleteStudentMedicalRecordByLrn(LRN);
       logger.warn("Exiting deleteStudentMedicalRecordByLrn with result: {}", isDeleted);
       return isDeleted;
    }

    /**
    * This is used to update a student's medical record based on their Learner Reference Number (LRN).
    * */
    @Override
    public boolean updateStudentMedicalRecord(String symptoms, String temperatureReadings, Date visitDate, String treatement, long LRN) {
        return this.studentMedRecord.updateStudentMedicalRecord(symptoms,temperatureReadings,visitDate,treatement, LRN);
    }
}





