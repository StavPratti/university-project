package org.university.controller;

import lombok.Data;
import org.university.model.Classes;
import org.university.model.Enrollment;
import org.university.model.EnrollmentId;
import org.university.model.Student;
import org.university.service.*;
import org.university.utility.SemesterEnum;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Named
@ApplicationScoped
@Data
public class StudentController implements Serializable {

    @Inject
    private IStudentService studentService;

    @Inject
    private IClassesService classesService;

    @Inject
    private IEnrollmentService enrollmentService;

    private List<Student> students;
    private Student selectedStudent;
    private Student newStudent;
    private List<Classes> classes;
    private Classes selectedClass;
    private Enrollment enrollment;
    private EnrollmentId enrollmentId;
    private List<Classes> enrolledClasses;
    private Long studentId;

    @PostConstruct
    public void init() {
        students = studentService.findAllStudents();
        newStudent = new Student();
        classes = classesService.findAllClasses(); // Fetch actual classes
    }

    public void deleteStudent(Student student) {
        this.selectedStudent = student;
        studentService.deleteStudentById(student.getId());
        students.remove(this.selectedStudent);
        this.selectedStudent = null;
    }

    public void updateStudent() {
        if (selectedStudent != null) {
            studentService.updateStudent(selectedStudent);
        }
    }

    // Set selected student
    public void selectStudent(Student student) {
        this.selectedStudent = student;
        enrolledClasses= classesService.findAllClassesByStudentId(selectedStudent.getId()); //return enrolled classes of student
        findClasses(student); //find classes by semester
    }

    public void findClasses(Student student) {
        int currentMonth = LocalDate.now().getMonthValue();
        SemesterEnum semester = (currentMonth >= 2 && currentMonth <= 8) ? SemesterEnum.B : SemesterEnum.A;
        this.classes = classesService.findClassesBySemester(semester);
        this.classes.removeAll(this.enrolledClasses); // Remove already enrolled student classes
    }                                                 // To list only non - selected classes

    public void enrollStudent(Long classId) throws IOException {
        // Fetch the class using the provided class ID
        selectedClass = classesService.findClassesById(classId);
        Enrollment enrollment = enrollmentService.setEnrollmentEntity(selectedClass,selectedStudent);

        try { // Save the enrollment
            enrollmentService.updateEnrollment(enrollment);
            System.out.println("Enrollment successful for student ID: " + selectedStudent.getId() + " in class ID: " + selectedClass.getId());
        } catch (Exception e) {
            System.err.println("Error saving enrollment: " + e.getMessage());
        }
        selectedClass = null; // reset the selectedClass
    }

    public void addNewStudent() {
        studentService.saveStudent(newStudent); // Save the new student to the database
        students.add(newStudent); // Add to the in-memory list
        newStudent = new Student(); // Reset for next entry
    }

}
