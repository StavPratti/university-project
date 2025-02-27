package org.university.repository;

import org.university.model.Student;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class StudentRepository extends GenericRepositoryImpl<Student>{
    public StudentRepository() {
        super(Student.class);
    }
}
