package com.rocs.infirmary.desktop.app.facade.student.record;


import com.rocs.infirmary.desktop.data.model.person.student.Student;


import java.util.Date;
import java.util.List;


/**
 * The StudentMedicalRecordFacade interface defines methods for managing students medical record.
 */

public interface StudentMedicalRecordFacade {

    /**
     * Retrieves a student's important details and student record.
     *
     * @param LRN The LRN (Learner Reference Number) of the student.
     */
    Student findMedicalInformationByLRN(long LRN);

    /**
     * Retrieves all student medical records from the database.
     *
     * @return A list of student medical records, or an empty list if no records are found.
     */
    List<Student> readAllStudentMedicalRecords();

    /**
     * This intended to delete a student's medical record based on their Learner Reference Number (LRN).
     * The LRN is a unique identifier assigned to each student. This value is used to locate and delete the corresponding medical record.
     */

    boolean deleteStudentMedicalRecordByLrn(long LRN);

    /**
     * This method updates a student's medical record based on their Learner Reference Number (LRN).
     * The LRN is a unique identifier assigned to each student and is used to locate the corresponding record.
     * The method updates the specified fields in the student's record: symptoms, temperature readings,
     * date of visit, and treatment.
     *
     * @param symptoms            A description of the student's symptoms.
     * @param temperatureReadings The student's temperature readings.
     * @param visitDate           The date the student visited the infirmary.
     * @param treatement         The treatment administered to the student.
     * @param LRN                 The Learner Reference Number uniquely identifying the student.
     * @return (true) if a field in student's medical record was successfully updated;
     * and (false) if no updates were done.
     */

    boolean updateStudentMedicalRecord(String symptoms, String temperatureReadings, Date visitDate , String treatement, long LRN  );

}
