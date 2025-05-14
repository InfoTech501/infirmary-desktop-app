package com.rocs.infirmary.desktop.data.dao.student.record;


import com.rocs.infirmary.desktop.data.model.person.student.Student;

import java.util.Date;
import java.util.List;

public interface StudentMedicalRecordDao {

    Student getMedicalInformationByLRN(long LRN);

    List<Student> getAllStudentMedicalRecords();


    /**
     * This intended to delete a student's medical record based on their Learner Reference Number (LRN).
     * The LRN is a unique identifier assigned to each student. This value is used to locate and delete the corresponding medical record.
     *
     */

    boolean deleteStudentMedicalRecordByLrn (long LRN);

    /**
     * This is intended to update a student's medical record based on their Learner Reference Number (LRN).
     * The LRN is a unique identifier assigned to each student. This value is used to locate and update
     * the corresponding medical record. The method updates fields such as symptoms, temperature readings,
     * date of visit, and treatment.
     *
     * @param symptoms            A description of the student's symptoms.
     * @param temperatureReadings The student's temperature readings.
     * @param visitDate           The date the student visited the infirmary.
     * @param treatment           The treatment administered to the student.
     * @param LRN                 The Learner Reference Number uniquely identifying the student.
     * @return (true) if at least one field in the student's medical record was successfully updated;
     * and (false) if no updates were performed.
     */

    boolean updateStudentMedicalRecord(String symptoms, String temperatureReadings, Date visitDate , String treatment, long LRN  );


}


