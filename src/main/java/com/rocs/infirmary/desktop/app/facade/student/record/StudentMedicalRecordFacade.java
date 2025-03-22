package com.rocs.infirmary.desktop.app.facade.student.record;


import com.rocs.infirmary.desktop.data.model.person.student.Student;


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
     * This defines the contract for deleting medical records. It includes a method for deleting records by their ID.
     * <p>
     * This method is used to delete a medical record by its unique identifier (ID). It returns a boolean value indicating whether the deletion was successful.
     */
    Student deleteStudentMedicalRecordById(String Id);
}
