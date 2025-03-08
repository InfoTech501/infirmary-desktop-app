package com.rocs.medical.records.application.app.facade.deleteMedicalRecord;


public interface StudentMedicalRecordsFacade {

    /** This interface defines the contract for deleting medical records. It includes a method for deleting records by their ID.
     *
     * This method is used to delete a medical record by its unique identifier (ID). It returns a boolean value indicating whether the deletion was successful.
     *
     */

    boolean deleteStudentMedicalRecordById(String Id);

}
