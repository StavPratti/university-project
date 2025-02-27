package org.university.service;

import org.university.model.Teacher;

import java.util.List;
import java.util.Set;

public interface ITeacherService {

    Teacher findTeacherById(Long id);

    List<Teacher> findAllTeachers();

    void saveTeacher (Teacher newTeacher);

    void deleteTeacherById(Long id);

    void updateTeacher (Teacher updateTeacher);

    Teacher findTeacherWithClasses(Long id);

    Set<Teacher> findTeachersWithoutClasses();
}
