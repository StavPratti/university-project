package org.university.service;

import org.university.model.Student;
import org.university.repository.StudentRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Stateless
public class StudentServiceImpl implements IStudentService {

    @Inject
    private StudentRepository studentRepository;

    @Inject
    public StudentServiceImpl() {
    }

    @Override
    public Student findStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void saveStudent(Student newStudent) {
        newStudent.setRegistrationDate(new Date());
        studentRepository.save(newStudent);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public void updateStudent(Student updateStudent) {
        studentRepository.update(updateStudent);
    }
}
