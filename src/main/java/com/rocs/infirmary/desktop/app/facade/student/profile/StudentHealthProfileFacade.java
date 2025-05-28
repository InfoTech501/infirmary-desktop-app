package com.rocs.infirmary.desktop.app.facade.student.profile;

import com.rocs.infirmary.desktop.data.model.person.student.Student;

import java.util.List;

/**
 * The StudentHealthProfileFacade interface defines methods for managing (getting) student health profile data.
 * This includes methods for:
 * - Retrieving all student health profiles.
 * - Retrieving student health profiles by their unique Learner Reference Number (LRN).
 */

public interface StudentHealthProfileFacade {
    /**
     * Retrieves all student health profiles available in the system/database.
     * @return A List of Student objects, each containing health-related information.
     */
    List<Student> getAllStudentHealthProfile();

    /**
     * Retrieves health profiles of students based on their Learner Reference Number (LRN).
     *
     * @param LRN The unique identifier assigned to each student (Learner Reference Number).
     * @return A List of Student objects whose LRN matches the given parameter.
     */
    List<Student> getStudentHealthProfileByLRN(Long LRN);

}
