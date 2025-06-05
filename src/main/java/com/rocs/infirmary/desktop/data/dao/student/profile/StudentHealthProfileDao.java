package com.rocs.infirmary.desktop.data.dao.student.profile;

import com.rocs.infirmary.desktop.data.model.person.student.Student;

import java.util.List;

public interface StudentHealthProfileDao {

    /**
     * Retrieves all student health profiles available in the system/database.
     * @return A List of Student objects, containing all health-related information.
     */
    List<Student> findAllStudentHealthProfile();

    /**
     * Retrieves health profiles of students based on their Learner Reference Number (LRN).
     *
     * @param LRN The unique identifier assigned to each student (Learner Reference Number).
     * @return A List of Student objects whose LRN matches the given parameter.
     */
    List<Student> findStudentHealthProfileByLrn(Long LRN);
}
