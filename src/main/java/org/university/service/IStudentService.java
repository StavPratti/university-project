package org.university.service;

import org.university.model.Student;

import java.util.List;

public interface IStudentService {

    Student findStudentById(Long id);

    List<Student> findAllStudents();

    void saveStudent (Student newStudent);

    void deleteStudentById(Long id);

    void updateStudent (Student updateStudent);
}
