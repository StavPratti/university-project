package org.university.service;

import org.university.model.Classes;
import org.university.model.Enrollment;
import org.university.model.Student;

import java.util.List;

public interface IEnrollmentService {

    Enrollment findEnrollmentById(Long id);

    List<Enrollment> findAllEnrollments();

    void saveEnrollment(Enrollment newEnrollment);

    void deleteEnrollmentById(Long id);

    void updateEnrollment(Enrollment updateEnrollment);

    Enrollment setEnrollmentEntity(Classes selectedClass, Student selectedStudent);
}
