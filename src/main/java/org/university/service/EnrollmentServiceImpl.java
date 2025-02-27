package org.university.service;

import org.university.model.Classes;
import org.university.model.Enrollment;
import org.university.model.EnrollmentId;
import org.university.model.Student;
import org.university.repository.EnrollmentRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Stateless
public class EnrollmentServiceImpl implements IEnrollmentService {

    @Inject
    private EnrollmentRepository enrollmentRepository;

//    @Inject
    ////    public EnrollmentServiceImpl(EnrollmentRepository enrollmentRepository) {
    ////        this.enrollmentRepository = enrollmentRepository;
    ////    }

    public EnrollmentServiceImpl() {
    }

    @Override
    public Enrollment findEnrollmentById(Long id) {
        return enrollmentRepository.findById(id);
    }

    @Override
    public List<Enrollment> findAllEnrollments() {
        return enrollmentRepository.findAll();
    }

    @Override
    public void saveEnrollment(Enrollment newEnrollment) {
        enrollmentRepository.save(newEnrollment);
    }

    @Override
    public void deleteEnrollmentById(Long id) {
        enrollmentRepository.deleteById(id);
    }

    @Override
    public void updateEnrollment(Enrollment updateEnrollment) {
        enrollmentRepository.update(updateEnrollment);
    }

    // Maybe unnecessary
    public Enrollment setEnrollmentEntity(Classes selectedClass, Student selectedStudent){
        // Create a new enrollment
        Enrollment enrollment = new Enrollment();
        EnrollmentId enrollmentId = new EnrollmentId(); // Create a new EnrollmentId

        // Set student ID and class ID
        enrollmentId.setStudentId(selectedStudent.getId());
        enrollmentId.setClassId(selectedClass.getId());

        enrollment.setId(enrollmentId); // Set the composite ID
        enrollment.setStudent(selectedStudent); // Set the student
        enrollment.setClasses(selectedClass); // Set the class
        enrollment.setEnrollmentDate(new java.util.Date()); // Set the enrollment date

        return enrollment;
    }
}
