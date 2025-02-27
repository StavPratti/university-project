package org.university.service;

import org.university.model.Teacher;
import org.university.repository.TeacherRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Stateless
public class TeacherServiceImpl implements ITeacherService {

    @Inject
    private TeacherRepository teacherRepository;

    @Inject
    public TeacherServiceImpl() {
    }

    @Override
    public Teacher findTeacherById(Long id) {
        return teacherRepository.findById(id);
    }

    @Override
    public List<Teacher> findAllTeachers() {
        return teacherRepository.findAll();
    }

    @Override
    public void saveTeacher(Teacher newTeacher) {
        teacherRepository.save(newTeacher);
    }

    @Override
    public void deleteTeacherById(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public void updateTeacher(Teacher updateTeacher) {
        teacherRepository.update(updateTeacher);
    }

    @Override
    public Teacher findTeacherWithClasses(Long id) {
        return teacherRepository.findTeacherWithClasses(id);
    }

    @Override
    public Set<Teacher> findTeachersWithoutClasses() {
        return teacherRepository.findTeachersWithoutClasses();
    }
}

